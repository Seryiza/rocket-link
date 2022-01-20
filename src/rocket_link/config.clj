(ns rocket-link.config
  (:require [clojure.edn :as edn]
            [mount.core :refer [defstate]]))

(defstate config
  :start {:http-port (System/getenv "PORT")
          :base-url (System/getenv "BASE_URL")
          :database-url (System/getenv "DATABASE_URL")})
