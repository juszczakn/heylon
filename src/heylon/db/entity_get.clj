(ns heylon.db.entity-get
  (:require [heylon.db.info :as info]
            [yesql.core :refer [defqueries]]))

(defqueries "sql/entity_get.sql")

(defn get-entity
  "Only works with single params currently.
Pattern matches against ns to pick correct defquery.
ex. kingdom {:userid 5} => get-kingdom--userid"
  [entity params]
  (let [param-name (name (first (keys params)))
        param-val (first (vals params))
        query-name (ns-resolve 'heylon.db.entity-get (symbol (str "get-" entity "--" param-name)))]
    (try
      (query-name info/heylon-db param-val)
      (catch Exception e
        (throw (NoSuchMethodException. (str "Invalid query. " (.getMessage e))))))))
