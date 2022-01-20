(ns rocket-link.app
  (:require [rocket-link.config :refer [config]]
            [rocket-link.links.links :refer [get-link-by-code-name]]
            [rocket-link.html :refer [render]]
            [mount.core :as mount :refer [defstate]]
            [reitit.ring :as ring]
            [ring.util.response :refer [redirect]]))

(defn make-project-url [& components]
  (apply str (:base-url config) components))

(defn index-page-handler [request]
  (render request "index.html"))

(defn create-link-handler [request]
  {:status 200, :body "here you are"})

(defn show-created-link-handler [request]
  (let [code-name (-> request :path-params :code-name)
        created-link (make-project-url "/to/" code-name)]
    (render request "links/created.html" {:created-link created-link})))

(defn redirect-to-link-handler [request]
  (let [code-name (-> request :path-params :code-name)
        link (get-link-by-code-name code-name)]
    (if (nil? link)
      {:status 404, :body "Oh, non-existing URL"}
      (redirect (:url link)))))

(defstate app
  :start (ring/ring-handler
          (ring/router
            [["/" index-page-handler]
             ["/to/:code-name" redirect-to-link-handler]
             ["/links" {:post create-link-handler}]
             ["/links/:code-name" ["/created" show-created-link-handler]]])
          (ring/create-default-handler)))

