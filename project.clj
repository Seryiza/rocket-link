(defproject rocket-link "0.0.0"
  :description "Emoji link for your link"
  :resource-paths ["resources"]
  :source-paths ["src"]
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
                 [selmer "1.12.49"]
                 [buddy/buddy-hashers "1.8.158"]
                 [funcool/struct "1.4.0"]]

  :profiles {:uberjar {:main rocket-link.server
                       :uberjar-name "rocket-link.jar"
                       :aot :all}
             :dev {:source-paths ["dev"]
                   :dependencies [[org.clojure/tools.namespace "1.2.0"]]}}

  :migratus {:store :database
             :migration-dir "migrations"
             :db ~(System/getenv "DATABASE_URL")}
  :plugins [[migratus-lein "0.7.3"]])
