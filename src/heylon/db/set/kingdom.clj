(ns heylon.db.set.kingdom
  (:require [heylon.db.info :as info]
            [clojure.java.jdbc :as sql])
  (:use [heylon.db.get.kingdom]))

(defn create-new-kingdom
  ([userid name] (create-new-kingdom userid name 1)) ;; default to human
  ([userid name raceid]
   (sql/insert! info/heylon-db :kingdom
                {:userid userid :name name :raceid raceid})
   true))

(defn- create-default-unit-group
  [villageid parcelid]
  (sql/insert! info/heylon-db :unit_group
               {:villageid villageid
                :parcelid parcelid}))

(defn- create-village-and-default-unit-group
  [kingdomid parcelid name]
  (let [data (sql/insert! info/heylon-db :village
                          {:parcelid parcelid
                           :kingdomid kingdomid
                           :name name})
        villageid (:villageid (first data))]
    (create-default-unit-group villageid parcelid)))

(defn create-new-village
  "check if parcel is free, if so create
a new village for the kingdom"
  [kingdomid parcelid name]
  (if (parcel-free? parcelid)
    (create-village-and-default-unit-group kingdomid parcelid name)
    false))
