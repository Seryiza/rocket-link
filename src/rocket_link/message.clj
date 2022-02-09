(ns rocket-link.message
  (:require [clojure.spec.alpha :as s]))

(def messages
  {:user/email "Enter correct email address."
   :user/password "Enter non-empty password."
   :user/cannot-login "Email isn't registered or password is wrong. Check entered information."})

(defn get-message [message-key]
  (get messages message-key))

(defn get-problem-message [problem]
  (let [{:keys [via]} problem
        last-spec (peek via)]
    (get-message last-spec)))

(defn get-explain-messages [spec value]
  (->> (s/explain-data spec value)
       :clojure.spec.alpha/problems
       (map get-problem-message)
       (remove nil?)))

