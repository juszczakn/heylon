(ns heylon.db.set.dispatch
  (:require [heylon.db.info :as info]
            [clojure.java.jdbc :as sql]
            [heylon.db.set.kingdom :as kingdom]
            [heylon.db.set.unit :as unit])
  (:use [heylon.db.helpers]))

(defn- is-owner?
  [userid kingdomid]
  (let [q "select kingdomid from kingdom
 where userid=? and kingdomid=? limit 1;"]
    (not (empty? (sql/query info/heylon-db [q userid kingdomid])))))

(defmulti put-entity
  "deliver api call to correct function based
on entity and actions required"
  (fn [userid kingdomid params]
    (let [entity (keyword (:entity params))
          action (keyword (:action params))]
      (if (and userid kingdomid entity)
        [entity action]
        nil))))


;; create
(defmethod put-entity [:village :create] [userid kingdomid params]
  (let [{name :name parcelid :parcelid} params
        parcelid (to-long parcelid)]
    (kingdom/create-new-village! kingdomid parcelid name)))

(defmethod put-entity [:unit :create] [userid kingdomid params]
  (let [{groupid :groupid amount :amount unit-type :unit-type} params
        groupid (to-long groupid)
        amount (to-long amount)
        unit-type (to-long unit-type)]
    (unit/create-units! kingdomid groupid amount unit-type)))

(defmethod put-entity [:unit-group :create] [userid kingdomid params]
  (let [{villageid :villageid} params
        villageid (to-long villageid)]
    (unit/create-unit-group! kingdomid villageid)))



;; update
(defmethod put-entity [:unit :update] [userid kingdomid params]
  "stub")

(defmethod put-entity [:unit-group :update] [userid kingdomid params]
  "stub")

(defmethod put-entity [:village :update] [userid kingdomid params]
  "stub")


;; default
(defmethod put-entity :default [userid kingdomid params]
  (println "no match:" params))
