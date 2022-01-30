(ns rocket-repl
  (:require [rocket-link.server :as rocket-link]
            [clojure.tools.namespace.repl :as repl]))

(repl/set-refresh-dirs "src")

(defn start []
  (rocket-link/start))

(defn stop []
  (rocket-link/stop))

(defn restart []
  (stop)
  (start))

(defn refresh []
  (stop)
  (repl/refresh :after 'rocket-link.server/start))
