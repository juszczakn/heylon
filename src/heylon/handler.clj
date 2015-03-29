(ns heylon.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [heylon.controllers.login :as login]
            [heylon.api.handler :as api-handler]))

(defroutes app-routes
  (route/files "heylon/login.html")
  (route/files "heylon/index.html")
  (POST "/heylon/login" request (login/login request))
  (POST "/heylon/register" request (login/register request))
  api-handler/api-routes
  (route/not-found "Not Found"))

(def my-site-defaults
  (assoc site-defaults :security
         (assoc (site-defaults :security) :anti-forgery false)))

(def app
  (wrap-defaults app-routes my-site-defaults))
