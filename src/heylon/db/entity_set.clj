(ns heylon.db.entity-set
  (:require [heylon.db.info :as info]
            [yesql.core :refer [defqueries]]))

(defqueries "sql/entity_set.sql")

(defmacro insert-entity!
  "call any insert query in sql/entity_set.sql"
  [entity & params]
  (let [query-name (resolve (symbol (str "insert-" entity "!")))]
    `(apply ~query-name info/heylon-db (list ~@params))))
