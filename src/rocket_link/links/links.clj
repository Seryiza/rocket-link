(ns rocket-link.links.links
  (:require [rocket-link.db :refer [db]]
            [clojure.java.jdbc :as jdbc]))

(defn find-by-shortcut [shortcut]
  (first (jdbc/query db ["SELECT * FROM links WHERE shortcut = ?" shortcut])))

(defn create! [url shortcut-fn]
  (jdbc/with-db-transaction [transation db]
    (let [link-without-shortcut (first (jdbc/insert! transation :links {:url url}))
          link-id (:id link-without-shortcut)
          shortcut (shortcut-fn link-id)]
      (jdbc/execute! transation ["UPDATE links SET shortcut = ? WHERE id = ?"
                                 shortcut
                                 link-id])
      shortcut)))

