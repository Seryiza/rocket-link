(ns rocket-link.app
  (:require [rocket-link.config]
            [rocket-link.links.links :refer [get-link-by-code-name]]
            [mount.core :as mount :refer [defstate]]
            [reitit.ring :as ring]
            [ring.util.response :refer [redirect]]))

(defn init-app []
  (mount/start))

(defn index-page-handler [_]
  {:status 200 :body "Hello!"})

(defn redirect-to-link [request]
  (let [code-name (-> request :path-params :code-name)
        link (get-link-by-code-name code-name)]
    (if (nil? link)
      {:status 404, :body "Oh, non-existing URL"}
      (redirect (:url link)))))

(defstate app
  :start (ring/ring-handler
          (ring/router
            [["/" index-page-handler]
            ["/to/:code-name" redirect-to-link]])
          (ring/create-default-handler)))

