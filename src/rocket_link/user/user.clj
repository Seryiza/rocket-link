(ns rocket-link.user.user
  (:require [rocket-link.db :refer [db]]
            [rocket-link.common-specs :as common-specs]
            [clojure.java.jdbc :as jdbc]
            [buddy.hashers :as hashers]
            [clojure.string :as str]
            [clojure.spec.alpha :as s]))

(s/def :user/email (s/and :common-specs/ne-string
                          #(re-matches #"^\S+\@\S+\.\S+$" %)))
(s/def :user/password :common-specs/ne-string)
(s/def :user/user (s/keys :req-un [:user/email :user/password]))

(defn find-by-email [email]
  (let [lowercased-email (str/lower-case email)]
    (first (jdbc/query db ["SELECT * FROM users WHERE email = ?" lowercased-email]))))

(defn has-user-this-password? [user check-password]
  (:valid (hashers/verify check-password (:password user))))

(defn login [{:keys [email password]}]
  (when-let [user (find-by-email email)]
    (when (has-user-this-password? user password) user)))

(defn create! [user]
  (let [{:keys [email password]} user
        hashed-password (hashers/derive password)]
    (jdbc/insert! db :users {:email email
                             :password hashed-password})))
