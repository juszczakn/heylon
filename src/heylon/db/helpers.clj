(ns heylon.db.helpers
  (:use [ring.util.response]))

(defn to-int
  [x]
  (try
    (Integer. x)
    (catch NumberFormatException e
      (do
        (println "Error converting " x)
        (.printStackTrace e)
        nil))))

(defn simple-response-value
  [values]
  (-> (response (str values))
      (content-type "text/html")))

(defn create-map-one-row
  [data]
  (when-let [[col-names vals] data]
    (simple-response-value (into {} (map #(hash-map % %2) col-names vals)))))

(defn- map-colnames-row
  [col-names row]
  (into {} (map #(hash-map % %2) col-names row)))

(defn create-vec-multi-row
  [data]
  (let [col-names (first data)
        data (rest data)]
    (into [] (map #(map-colnames-row col-names %) data))))
