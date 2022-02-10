(ns rocket-link.config
  (:require [mount.core :refer [defstate]]))

(defstate config
  :start {:server-port (Integer/parseInt (System/getenv "PORT"))
          :project-url (System/getenv "PROJECT_URL")
          :database-url (System/getenv "DATABASE_URL")})
