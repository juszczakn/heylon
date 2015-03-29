(ns heylon.db.info)

(load-file "/home/nick/.heylon-prefs.clj")
(declare db-user db-password db-subname)

(def heylon-db
  {
   :subprotocol "postgresql"
   :classname "org.postgresql.Driver"
   :subname db-subname
   :user db-user
   :password db-password
   })
