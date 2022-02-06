(ns rocket-link.user.user
  (:require [rocket-link.db :refer [db]]
            [clojure.java.jdbc :as jdbc]
            [buddy.hashers :as hashers]
            [clojure.string :as str]))

(defn find-by-email [email]
  (let [lowercased-email (str/lower-case email)]
    (first (jdbc/query db ["SELECT * FROM users WHERE email = ?" lowercased-email]))))

(defn password-correct? [user check-password]
  (:valid (hashers/verify check-password (:password user))))

(defn can-login? [email password]
  (let [user (find-by-email email)]
    (and user (password-correct? user password))))

(defn create! [user]
  (let [{:keys [id email password]} user
        hashed-password (hashers/derive password)]
    (jdbc/insert! db :users {:id id
                             :email email
                             :password hashed-password})))
