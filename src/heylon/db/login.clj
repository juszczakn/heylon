(ns heylon.db.login
  (:require [heylon.db.info :as info]
            [crypto.password.bcrypt :as password]
            [yesql.core :refer [defqueries]]))

(defqueries "sql/login.sql")

(defn valid-user?
  [email password]
  (let [data (first (get-all-user-data info/heylon-db email))]
    (when-let [enc-password (:password data)]
      (if (password/check password enc-password)
        (dissoc data :password)
        false))))

(defn register-user
  [email name password]
  (let [not-registered? (empty? (get-user-data email))]
    (if not-registered?
      (enter-new-user! info/heylon-db email name (password/encrypt password))
      nil)))
