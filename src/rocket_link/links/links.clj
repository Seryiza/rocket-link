(ns rocket-link.links.links
  (:require [rocket-link.db :refer [db]]
            [clojure.java.jdbc :as jdbc]))

(defn find-by-code-name [code-name]
  (first (jdbc/query db ["SELECT * FROM links WHERE code_name = ?" code-name])))

(defn create! [url get-code-name-fn]
  (jdbc/with-db-transaction [transation db]
    (let [link-without-code-name (first (jdbc/insert! transation :links {:url url}))
          link-id (:id link-without-code-name)
          generated-code-name (get-code-name-fn link-id)]
      (jdbc/execute! transation ["UPDATE links SET code_name = ? WHERE id = ?"
                                 generated-code-name
                                 link-id])
      generated-code-name)))

