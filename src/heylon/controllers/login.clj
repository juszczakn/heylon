(ns heylon.controllers.login
  (:use [heylon.db.login :only [valid-user? register-user]]
        [ring.util.response])
  (:require [compojure.route :as route]))

(defn login
  "if success, send user to index.html and store session data"
  [request]
  (let [{params :params} request
        {session :session} request
        {email :email password :password} params
        logged-in (valid-user? email password)
        {user :user userid :userid} logged-in]
    (if logged-in
      (-> (resource-response "heylon/index.html" {:root "public"})
          (content-type "text/html")
          (assoc :session (assoc session :userid userid :user user)))
      (route/not-found "Invalid login"))))

(defn register
  [request]
  (let [{params :params} request
        {email :email name :name password :password} params
        registered? (register-user email name password)]
    (when registered?
      (-> (redirect "login.html")
          (content-type "text/html")))))
