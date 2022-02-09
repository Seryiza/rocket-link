(ns rocket-link.user.routes
  (:require [rocket-link.html :as html]
            [rocket-link.punycode :as punycode]
            [rocket-link.url :as url]
            [rocket-link.user.user :as user]
            [rocket-link.message :as message]))

(defn show-login-handler [request]
  (html/render request "users/login.html"))

(defn login-handler [request]
  (let [{:keys [params session]} request
        {:keys [email password]} params
        user-data {:email email, :password password}
        validation-errors (message/get-explain-messages :user/user user-data)]
    (if (not-empty validation-errors)
      (html/render request "users/login.html" {:params params :errors validation-errors})
      (if (user/can-login? email password)
        (-> (punycode/redirect (url/make-project-url "/"))
            (assoc :session (assoc session :user user-data)))
        (html/render request "users/login.html" {:params params
                                                 :errors [(message/get-message :user/cannot-login)]})))))

