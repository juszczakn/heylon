(ns heylon.db.get.kingdom
  (:require [heylon.db.info :as info]
            [clojure.java.jdbc :as sql]))

(defn parcel-free?
  "is the given parcel currently occupado"
  [parcelid]
  (let [parcel-query "select villageid from village where parcelid = ?"
        data (sql/query info/heylon-db [parcel-query parcelid])]
    (if (empty? data)
      true
      false)))
