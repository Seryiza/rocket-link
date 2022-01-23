(ns rocket-link.config
  (:require [clojure.edn :as edn]
            [mount.core :refer [defstate]]))

(defstate config
  :start {:http-port (Integer/parseInt (System/getenv "PORT"))
          :base-domain (System/getenv "BASE_DOMAIN")
          :database-url (System/getenv "DATABASE_URL")})
