(ns heylon.db.set.unit
  (:require [heylon.db.info :as info]
            [clojure.java.jdbc :as sql]))

(defn create-units
  [groupid amount unittype]
  (sql/insert! info/heylon-db :unit
               {:groupid groupid
                :amount amount
                :unittype unittype}))
