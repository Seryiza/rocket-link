(ns rocket-link.config
  (:require [clojure.edn :as edn]
            [mount.core :refer [defstate]]))

(defstate config
  :start {:http-port (Integer/parseInt (System/getenv "PORT"))
          :base-url (System/getenv "BASE_URL")
          :database-url (System/getenv "DATABASE_URL")})
