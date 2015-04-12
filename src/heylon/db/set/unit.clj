(ns heylon.db.set.unit
  (:require [heylon.db.info :as info]
            [clojure.java.jdbc :as sql])
  (:use [heylon.db.get.unit]))

(defn- is-group-owner?
  [kingdomid groupid]
  (let [q "select kingdomid from kingdom_unitgroups 
where kingdomid=? and groupid=?"]
    (not (empty? (sql/query info/heylon-db [q kingdomid groupid])))))

(defn create-units!
  [kingdomid groupid amount unit-type]
  (if (and groupid amount unit-type
           (is-group-owner? kingdomid groupid))
    (sql/insert! info/heylon-db :unit
                 {:groupid groupid
                  :amount amount
                  :unittype unit-type})))

(defn create-unit-group!
  [kingdomid villageid]
  (if (and kingdomid villageid)
    (when-let [parcelid (:parcelid (first (get-village-parcel kingdomid villageid)))]
      (sql/insert! info/heylon-db :unit_group
                   {:villageid villageid
                    :parcelid parcelid}))))
