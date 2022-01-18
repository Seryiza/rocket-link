(ns rocket-link.app
  (:require [reitit.ring :as ring]))

(defn index-page-handler [_]
  {:status 200 :body "Hello!"})

(def app
  (ring/ring-handler
    (ring/router
      ["/" index-page-handler])))
