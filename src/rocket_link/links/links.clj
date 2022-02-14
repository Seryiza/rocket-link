(ns rocket-link.links.links
  (:require [rocket-link.db :refer [db]]
            [clojure.java.jdbc :as jdbc]))

(defn find-by-shortcut [shortcut]
  (first (jdbc/query db ["SELECT * FROM links WHERE shortcut = ?" shortcut])))

(defn find-by-user-id [user-id]
  (jdbc/query db ["SELECT * FROM links WHERE user_id = ?" user-id]))

(defn create! [link shortcut-fn]
  (jdbc/with-db-transaction [transation db]
    (let [link-without-shortcut (first (jdbc/insert! transation :links link {:entities #(.replace % \- \_)}))
          link-id (:id link-without-shortcut)
          shortcut (shortcut-fn link-id)]
      (jdbc/execute! transation ["UPDATE links SET shortcut = ? WHERE id = ?" shortcut link-id])
      shortcut)))
