(ns rocket-link.config
  (:require [clojure.edn :as edn]
            [mount.core :refer [defstate]]))

(defstate config
  :start (-> "config.edn" slurp edn/read-string))
