(ns rocket-link.db
  (:require [mount.core :refer [defstate]]
            [hikari-cp.core :as cp]
            [rocket-link.config :refer [config]]
            [clojure.string :as str]))

(defn url->opts [database-url]
  (let [uri    (java.net.URI. database-url)
        path   (.getPath uri)
        host   (.getHost uri)
        port   (.getPort uri)
        [_ db] (re-find #"\/(.+)" path)
        [username password] (str/split (.getUserInfo uri) #"\:")]
    {:adapter "postgresql"
     :server-name host
     :port-number port
     :database-name db
     :username username
     :password password}))

(defstate db
  :start (let [{database-url :database-url} config
               pool-opts (url->opts database-url)
               datasource (cp/make-datasource pool-opts)]
           {:datasource datasource})
  :stop (-> db :datasource cp/close-datasource))
