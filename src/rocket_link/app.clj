(ns rocket-link.app
  (:require [rocket-link.config :refer [config]]
            [rocket-link.links.links :refer [get-link-by-code-name create-link!]]
            [rocket-link.html :refer [render]]
            [rocket-link.emoji.emoji-generator :refer [generate-emojis-by-id]]
            [rocket-link.punycode :as punycode]
            [mount.core :as mount :refer [defstate]]
            [reitit.ring :as ring]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.util.response :refer [redirect]])
  (:import [java.net URLEncoder]))

(defn url-encode [text]
  (URLEncoder/encode text "UTF-8"))

(defn make-project-url [& components]
  (apply str "https://" (:base-domain config) components))

(defn index-page-handler [request]
  (render request "index.html"))

(defn create-link-handler [request]
  (let [url (-> request :params :url)
        created-shortcut (create-link! url generate-emojis-by-id)
        created-link-url (make-project-url "/links/" (url-encode created-shortcut) "/created")]
    (punycode/redirect created-link-url)))

(defn show-created-link-handler [request]
  (let [code-name (-> request :path-params :code-name)
        created-link (make-project-url "/to/" code-name)]
    (render request "links/created.html" {:created-link created-link})))

(defn redirect-to-link-handler [request]
  (let [code-name (-> request :path-params :code-name)
        link (get-link-by-code-name code-name)]
    (if (nil? link)
      {:status 404, :body "Oh, non-existing URL"}
      (punycode/redirect (:url link)))))

(defstate app
  :start (ring/ring-handler
          (ring/router
            [["/" index-page-handler]
             ["/to/:code-name" redirect-to-link-handler]
             ["/links" {:post create-link-handler}]
             ["/links/:code-name" ["/created" show-created-link-handler]]])
          (ring/create-default-handler)
          {:middleware [wrap-params
                        wrap-keyword-params]}))

