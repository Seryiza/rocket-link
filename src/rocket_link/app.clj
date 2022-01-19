(ns rocket-link.app
  (:require [rocket-link.config]
            [mount.core :as mount :refer [defstate]]
            [reitit.ring :as ring]))

(defn init-app []
  (mount/start))

(defn index-page-handler [_]
  {:status 200 :body "Hello!"})

(defstate app
  :start (ring/ring-handler
          (ring/router
            ["/" index-page-handler])))

