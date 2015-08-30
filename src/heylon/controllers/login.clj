(ns heylon.controllers.login
  (:use [heylon.db.login :only [valid-user? register-user]]
        [heylon.api.json]))

(defn login
  "if success, store session data"
  [request]
  (let [{params :params} request
        {session :session} request
        {email :email password :password} params
        logged-in (valid-user? email password)
        {name :name userid :userid} logged-in]
    (if logged-in
      (json-ok (assoc session :userid userid :name name))
      (json-err {:error "Invalid login credentials."}))))

(defn register
  [request]
  (let [{params :params} request
        {email :email name :name password :password} params
        registered? (register-user email name password)]
    (if registered?
      (json-ok {:name name})
      (json-err {:error "Invalid registration."}))))
