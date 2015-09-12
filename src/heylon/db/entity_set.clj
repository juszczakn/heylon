(ns heylon.db.entity-set
  (:require [heylon.db.info :as info]
            [yesql.core :refer [defqueries]]
            [clojure.string :as string]))

(defqueries "sql/entity_set.sql" {:connection info/heylon-db})

(defn- sort-params
  [params]
  (let [ks (keys params)]
    (map #(get params %) (sort ks))))

(defn insert-entity!
  "call any insert query in sql/entity_set.sql"
  [entity params]
  (println entity params)
  (let [query-name (ns-resolve 'heylon.db.entity-set
                               (symbol (str "insert-" entity "!")))]
    (try
      (query-name params)
      (catch Exception e
        (throw (NoSuchMethodException. (str "Invalid query. " (.getMessage e))))))))

(defn update-entity!
  "Only works with single params currently.
See get-entity."
  [entity params]
  (let [param-names (sort (map name (keys params)))
        query-name (ns-resolve 'heylon.db.entity-set
                               (symbol (str "update-" entity "--" (string/join "-" param-names) "!")))]
    (try
      (query-name params)
      (catch Exception e
        (throw (NoSuchMethodException. "Invalid query."))))))
