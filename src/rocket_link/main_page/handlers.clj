(ns rocket-link.main-page.handlers
  (:require [rocket-link.html :as html]))

(defn show-handler [request]
  (html/render request "index.html"))
