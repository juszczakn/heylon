(ns heylon.db.entity-set
  (:require [heylon.db.info :as info]
            [yesql.core :refer [defqueries]]))

(defqueries "sql/entity_set.sql")

(defn- sort-params
  [params]
  (let [ks (keys params)]
    (map #(get params %) (sort ks))))

(defn insert-entity!
  "call any insert query in sql/entity_set.sql"
  [entity params]
  (println entity params)
  (let [query-name (ns-resolve 'heylon.db.entity-set (symbol (str "insert-" entity "!")))
        params (sort-params params)]
    (println params)
    (try
      (apply query-name info/heylon-db params)
      (catch Exception e
        (throw (NoSuchMethodException. (str "Invalid query. " (.getMessage e))))))))

(defn update-entity!
  "Only works with single params currently.
See get-entity."
  [entity params]
  (let [param-name (name (first (keys params)))
        param-val (first (vals params))
        query-name (ns-resolve 'heylon.db.entity-set (symbol (str "update-" entity "--" param-name "!")))]
    (try
      (query-name info/heylon-db param-val)
      (catch Exception e
        (throw (NoSuchMethodException. "Invalid query."))))))
