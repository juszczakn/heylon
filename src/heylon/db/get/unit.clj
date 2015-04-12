(ns heylon.db.get.unit
  (:require [heylon.db.info :as info]
            [clojure.java.jdbc :as sql]))

(def ^:const get-village-groups-query
  "
select ug.groupid from unit_group ug
  join village v on v.villageid = ug.villageid 
where ug.villageid = ?
")

(defn get-village-groups
  [villageid]
  (sql/query info/heylon-db [get-village-groups-query villageid]))

(defn get-village-parcel
  ([villageid]
   (sql/query info/heylon-db ["select parcelid from village where villageid=?" villageid]))
  ([kingdomid villageid]
   (let [q "select parcelid from village 
where kingdomid=? and villageid=?"]
     (sql/query info/heylon-db [q kingdomid villageid]))))
