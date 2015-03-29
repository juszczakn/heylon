(ns heylon.db.login
  (:require [heylon.db.info :as info]
            [crypto.password.bcrypt :as password]
            [clojure.java.jdbc :as sql]))

(defn- get-user-data
  [email]
  (let [user-query "select userid, name, email, password from users where email=? limit 1;"
        data (sql/query info/heylon-db [user-query email] :as-arrays? true)]
    (when-let [[col-names vals] data]
      (into {} (map #(hash-map % %2) col-names vals)))))

(defn valid-user?
  [email password]
  (let [data (get-user-data email)]
    (when-let [enc-password (:password data)]
      (if (password/check password enc-password)
        (dissoc data :password)
        false))))
