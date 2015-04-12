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


