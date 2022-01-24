(ns rocket-repl
  (:require [rocket-link.server :as rocket-link]
            [clojure.tools.namespace.repl :refer [refresh set-refresh-dirs]]))

(set-refresh-dirs "src")

(defn start []
  (rocket-link/start))

(defn stop []
  (rocket-link/stop))

(defn restart []
  (stop)
  (refresh :after 'rocket-link.server/start))
