(ns heylon.api.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [heylon.db.entity-get :refer [get-entity]]))


(defroutes get-api-routes
  (GET "/api/:entity" {session :session params :params} '();; (get-entity params params)
       )
  
  ;; (GET "/api/user" {session :session} (user-info/get-user-name (:userid session)))
  ;; (GET "/api/user/:userid" [userid] (user-info/get-user-name userid))
  ;; (GET "/api/user/kingdoms" {session :session} (user-info/get-kingdoms (:userid session)))
  ;; (GET "/api/user/kingdoms/:userid" [userid] (user-info/get-kingdoms userid))
  )

(defroutes put-api-routes
  ;;(PUT "/api/kingdom" request ())
  ;;(PUT "/api/:kingdomid/:entity" request (dispatch-put request))
  )
