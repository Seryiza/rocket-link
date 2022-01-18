(ns rocket-link.routes.home
  (:require
   [rocket-link.layout :as layout]
   [rocket-link.db.core :as db]
   [clojure.java.io :as io]
   [rocket-link.middleware :as middleware]
   [ring.util.response]
   [ring.util.http-response :as response]))

(defn index-page [request]
  (layout/render request "index.html"))

(defn home-routes []
  ["" 
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get index-page}]])

