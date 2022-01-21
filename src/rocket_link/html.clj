(ns rocket-link.html
  (:require [selmer.parser :as html]
            [clojure.java.io]
            [ring.util.response :refer [content-type]]))

(html/set-resource-path! (clojure.java.io/resource "html"))

(defn render [request template-name & [params]]
  {:status 200
   :headers {"Content-Type" "text/html; charset=utf-8"}
   :body (html/render-file template-name params)})

