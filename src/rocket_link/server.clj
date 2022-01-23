(ns rocket-link.server
  (:require [rocket-link.app :refer [app]]
            [rocket-link.config :refer [config]]
            [mount.core :as mount :refer [defstate]]
            [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))

(defn -main []
  (mount/start))

(defn reset []
  (mount/stop)
  (mount/start))

(defstate http-server
  :start (run-jetty app {:port (:http-port config)
                         :join? false})
  :stop (.stop http-server))
