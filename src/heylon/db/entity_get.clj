(ns heylon.db.entity-get
  (:require [heylon.db.info :as info]
            [yesql.core :refer [defqueries]]
            [clojure.string :as string]))

(defqueries "sql/entity_get.sql" {:connection info/heylon-db})
(defqueries "sql/map.sql" {:connection info/heylon-db})

(defn get-entity
  "Only works with single params currently.
Pattern matches against ns to pick correct defquery.
ex. kingdom {:userid 5} => get-kingdom--userid"
  [entity params]
  (let [param-names (sort (map name (keys params)))
        query-name (ns-resolve 'heylon.db.entity-get
                               (symbol (str "get-" entity "--" (string/join "-" param-names))))]
    (try
      (println query-name params)
      (query-name params)
      (catch Exception e
        (throw (NoSuchMethodException. (str "Invalid query. " (.getMessage e))))))))
