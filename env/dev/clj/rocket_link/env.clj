(ns rocket-link.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [rocket-link.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[rocket-link started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[rocket-link has shut down successfully]=-"))
   :middleware wrap-dev})
