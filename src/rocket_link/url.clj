(ns rocket-link.url
  (:require [rocket-link.config :refer [config]])
  (:import [java.net URLEncoder]))

(defn encode [text]
  (URLEncoder/encode text "UTF-8"))

(defn make-project-url [& components]
  (apply str "https://" (:base-domain config) components))

