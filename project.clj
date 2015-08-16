(defproject heylon "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [compojure "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [ring/ring-json "0.4.0"]
                 [org.clojure/java.jdbc "0.4.1"]
                 [postgresql/postgresql "8.4-702.jdbc4"]
                 [crypto-password "0.1.3"]
                 ;;[org.clojure/clojurescript "0.0-2127"]
                 ]
  :plugins [[lein-ring "0.9.6"]
            ;;[lein-cljsbuild "1.0.1"]
            ]
  :ring {:handler heylon.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.2.0"]]}})
