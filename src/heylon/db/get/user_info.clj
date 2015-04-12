(ns heylon.db.get.user-info
  (:use [ring.util.response])
  (:require [compojure.route :as route]
            [clojure.java.jdbc :as sql]
            [heylon.db.info :as info]
            [heylon.db.helpers :as helper]))

(defn- clean-id
  [userid]
  (try
    (Integer. userid)
    (catch Exception e
      (println "Error cleaning userid.") (.printStackTrace e))))

(defn get-user-name
  [userid]
  (when-let [userid (clean-id userid)]
    (let [query "select name from users where userid=? limit 1;"
          data (sql/query info/heylon-db [query userid] :as-arrays? true)]
      (helper/create-map-one-row data))))

(defn get-kingdoms
  [userid]
  (when-let [userid (clean-id userid)]
    (let [query "select k.kingdomid, k.name from kingdom k where userid=?;"
          data (sql/query info/heylon-db [query userid] :as-arrays? true)]
      (helper/simple-response-value (helper/create-vec-multi-row data)))))
