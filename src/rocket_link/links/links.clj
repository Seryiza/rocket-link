(ns rocket-link.links.links
  (:require [rocket-link.db :refer [db]]
            [clojure.java.jdbc :as jdbc]))

(defn get-link-by-code-name [code-name]
  (first (jdbc/query db ["SELECT * FROM links WHERE code_name = ?" code-name])))

