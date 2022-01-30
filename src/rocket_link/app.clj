(ns rocket-link.app
  (:require [rocket-link.links.links :as links]
            [rocket-link.html :as html]
            [rocket-link.emoji.emoji-combinations :as emoji]
            [rocket-link.punycode :as punycode]
            [rocket-link.url :as url]
            [mount.core :as mount :refer [defstate]]
            [reitit.ring :as ring]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]))

(defn index-page-handler [request]
  (html/render request "index.html"))

(defn create-link-handler [request]
  (let [url (-> request :params :url)
        created-shortcut (links/create! url emoji/get-combination-from-all-emojies)
        created-link-url (url/make-project-url "/links/" (url/encode created-shortcut) "/created")]
    (punycode/redirect created-link-url)))

(defn show-created-link-handler [request]
  (let [shortcut (-> request :path-params :shortcut)
        created-link (url/make-project-url "/to/" shortcut)]
    (html/render request "links/created.html" {:created-link created-link})))

(defn redirect-to-link-handler [request]
  (let [shortcut (-> request :path-params :shortcut)
        link (links/find-by-shortcut shortcut)]
    (if (nil? link)
      {:status 404, :body "Oh, non-existing URL"}
      (punycode/redirect (:url link)))))

(defstate app
  :start (ring/ring-handler
          (ring/router
            [["/" index-page-handler]
             ["/to/:shortcut" redirect-to-link-handler]
             ["/links" {:post create-link-handler}]
             ["/links/:shortcut" ["/created" show-created-link-handler]]])
          (ring/routes
            (ring/create-resource-handler {:path "/assets"})
            (ring/create-default-handler))
          {:middleware [wrap-params
                        wrap-keyword-params]}))

