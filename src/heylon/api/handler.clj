(ns heylon.api.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [heylon.db.user-info :as user-info]))

(defroutes api-routes
  (GET "/api/user" {session :session} (user-info/get-user-name (:userid session)))
  (GET "/api/user/:userid" [userid] (user-info/get-user-name userid))
  (GET "/api/user/kingdoms" {session :session} (user-info/get-kingdoms (:userid session)))
  (GET "/api/user/kingdoms/:userid" [userid] (user-info/get-kingdoms userid)))
