(ns heylon.db.entity
  (:require [heylon.db.info :as info]
            [yesql.core :refer [defqueries]]))

(defqueries "sql/entity_get.sql")

(defmacro get-entity
  [entity param-name param-val]
  (let [query-name (resolve (symbol (str "get-" entity "--" param-name)))]
    `(~query-name info/heylon-db ~param-val)))
