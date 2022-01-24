(ns rocket-link.html
  (:require [selmer.parser :as html]
            [clojure.java.io :as io]))

(html/set-resource-path! (io/resource "html"))

(defn render [request template-name & [params]]
  {:status 200
   :headers {"Content-Type" "text/html; charset=utf-8"}
   :body (html/render-file template-name params)})

