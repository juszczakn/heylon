(ns heylon.api.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [heylon.db.get.user-info :as user-info]
            [heylon.db.set.dispatch :as dispatch])
  (:use [heylon.db.helpers]))

(defn- dispatch-put
  [request]
  (let [{session :session
         params :params
         {kingdomid :kingdomid} :params} request
         userid (:userid session)
         kingdomid (to-long kingdomid)]
    (println request)
    (dispatch/put-entity userid kingdomid params)))

(defroutes get-api-routes
  (GET "/api/user" {session :session} (user-info/get-user-name (:userid session)))
  (GET "/api/user/:userid" [userid] (user-info/get-user-name userid))
  (GET "/api/user/kingdoms" {session :session} (user-info/get-kingdoms (:userid session)))
  (GET "/api/user/kingdoms/:userid" [userid] (user-info/get-kingdoms userid)))

(defroutes put-api-routes
  (PUT "/api/kingdom" request ())
  (PUT "/api/:kingdomid/:entity" request (dispatch-put request)))
