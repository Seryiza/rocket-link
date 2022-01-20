(ns rocket-link.db
  (:require [mount.core :refer [defstate]]
            [hikari-cp.core :as cp]
            [rocket-link.config :refer [config]]))

(defstate db
  :start (let [{database-url :database-url} config
               pool-opts {:jdbc-url database-url}
               a (print database-url)
               datasource (cp/make-datasource pool-opts)]
           {:datasource datasource})
  :stop (-> db :datasource cp/close-datasource))
