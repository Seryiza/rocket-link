(defproject rocket-link "0.0.0"
  :description "Emoji link for your link"
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [ring/ring-core "1.9.5"]
                 [ring/ring-jetty-adapter "1.9.5"]
                 [ring/ring-mock "0.4.0"]
                 [metosin/reitit-ring "0.5.15"]
                 [mount "0.1.16"]]
  :ring {:init rocket-link.app/init-app
         :handler rocket-link.app/app
         :nrepl {:start? true :port 7000}}
  :plugins [[lein-ring "0.12.6"]])
