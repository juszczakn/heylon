(ns heylon.db.info
  (:require [clojure.edn :as edn]))

(def db-prefs (edn/read-string (slurp "/home/nick/.heylon-prefs.edn")))

(def heylon-db
  {
   :subprotocol "postgresql"
   :classname "org.postgresql.Driver"
   :subname (:subname db-prefs)
   :user (:user db-prefs)
   :password (:password db-prefs)
   })
