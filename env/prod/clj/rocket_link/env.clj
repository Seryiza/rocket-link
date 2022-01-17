(ns rocket-link.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[rocket-link started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[rocket-link has shut down successfully]=-"))
   :middleware identity})
