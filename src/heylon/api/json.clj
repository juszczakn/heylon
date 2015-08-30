(ns heylon.api.json
  (:use [ring.util.response]))

(defn json-response
  ([message stat]
   (-> (response message)
       (status stat)
       (header "Content-Type" "text/json"))))

(defn json-ok
  "json 200 response"
  [message]
  (json-response message 200))

(defn json-err
  "generic json 500 response"
  [message]
  (json-response message 500))
