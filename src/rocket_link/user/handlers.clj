(ns rocket-link.user.handlers
  (:require [rocket-link.html :as html]
            [rocket-link.punycode :as punycode]
            [rocket-link.url :as url]
            [rocket-link.user.user :as user]
            [rocket-link.message :as message]
            [rocket-link.links.links :as links]
            [struct.core :as st]))

(def login-message
  {:email [st/required st/email]
   :password [st/required st/string]})

(defn show-login-handler [request]
  (html/render request "users/login.html"))

(defn login-handler [request]
  (let [{:keys [params session]} request
        [errors user-data] (st/validate params login-message)]
    (if (not-empty errors)
      (html/render request "users/login.html" {:params params :errors errors})
      (if-let [logged-user (user/login user-data)]
        (-> (punycode/redirect (url/make-project-url "/"))
            (assoc :session (assoc session :user logged-user)))
        (html/render request "users/login.html" {:params params
                                                 :messages [(message/get-message :user/cannot-login)]})))))

(defn logout-handler [request]
  (let [{:keys [session]} request]
    (-> (punycode/redirect (url/make-project-url "/"))
        (assoc :session (assoc session :user nil)))))

(defn show-register-handler [request]
  (html/render request "users/register.html"))

(defn register-handler [request]
  (let [{:keys [params session]} request
        [errors user-data] (st/validate params login-message)]
    (if (not-empty errors)
      (html/render request "users/register.html" {:params params :errors errors})
      (do
        (user/create! user-data)
        (-> (punycode/redirect (url/make-project-url "/"))
            (assoc :session (assoc session :user user-data)))))))

(defn- link->link-with-full-urls [{:keys [url shortcut]}]
  {:shortcut-url (url/make-project-url "/to/" shortcut)
   :origin-url url})

(defn list-user-links-handler [request]
  (let [user-id (-> request :session :user :id)
        user-links (->> (links/find-by-user-id user-id)
                       (map link->link-with-full-urls))]
    (html/render request "users/my-links.html" {:links user-links})))
