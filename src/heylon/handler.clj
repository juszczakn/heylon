(ns heylon.handler
  (:require [clojure.java.io :as io])
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.json :refer [wrap-json-params wrap-json-response]]
            [heylon.controllers.login :as login]
            [heylon.db.entity-get :refer [get-entity]]
            [heylon.db.entity-set :refer [insert-entity! update-entity!]]
            [heylon.api.json :refer [wrap-in-json]]))

(defroutes app-routes
  (GET "/" [] (io/resource "public/heylon/index.html"))
  (POST "/api/login" request (login/login request))
  (POST "/api/register" request (login/register request))
  (GET "/api/:entity" {params :params} (wrap-in-json get-entity (get params :entity) (dissoc params :entity)))
  (POST "/api/:entity" {params :params} (wrap-in-json insert-entity! (get params :entity) (dissoc params :entity)))
  (PUT "/api/:entity" {params :params} (wrap-in-json update-entity! (get params :entity) (dissoc params :entity)))
  (route/not-found "Not Found"))

(def my-site-defaults
  (assoc site-defaults :security
         (assoc (site-defaults :security) :anti-forgery false)))

(def app
  (-> app-routes
      (wrap-defaults my-site-defaults)
      (wrap-keyword-params)
      (wrap-json-params)
      (wrap-json-response)))
