(ns rocket-link.html
  (:require [selmer.parser :as html]
            [clojure.java.io :as io]))

(html/set-resource-path! (io/resource "html"))

(defn render [request template-name & [params]]
  (let [user (-> request :session :user)
        errors (-> request :flash :errors (or []))
        complemented-params (merge {:is-logged-in (not-empty user)
                                    :user user
                                    :errors errors}
                                   params)]
    {:status 200
     :headers {"Content-Type" "text/html; charset=utf-8"}
     :body (html/render-file template-name complemented-params)}))

