(defproject rocket-link "0.0.0"
  :description "Emoji link for your link"
  :resource-paths ["resources"]
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [ring/ring-core "1.9.5"]
                 [ring/ring-jetty-adapter "1.9.5"]
                 [ring/ring-mock "0.4.0"]
                 [metosin/reitit-ring "0.5.15"]
                 [mount "0.1.16"]
                 [migratus "1.3.5"]
                 [org.clojure/java.jdbc "0.7.12"]
                 [org.postgresql/postgresql "42.3.1"]
                 [hikari-cp "2.13.0"]
                 [selmer "1.12.49"]]

  :main rocket-link.server
  :uberjar-name "rocket-link.jar"
  :profiles {:uberjar {:aot :all}}

  :migratus {:store :database
             :migration-dir "migrations"
             :db ~(-> "config.edn" slurp clojure.edn/read-string :database-url)}
  :plugins [[migratus-lein "0.7.3"]])
