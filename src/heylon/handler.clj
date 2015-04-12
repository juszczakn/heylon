(ns heylon.handler
  (:require [clojure.java.io :as io])
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [heylon.controllers.login :as login]
            [heylon.api.handler :as api-handler]))

(defroutes app-routes
  (GET "/" [] (io/resource "public/heylon/index.html"))
  (route/files "heylon/login.html")
  (route/files "heylon/index.html")
  (route/files "heylon/create_kingdom.html")
  (POST "/heylon/login" request (login/login request))
  (POST "/heylon/register" request (login/register request))
  api-handler/get-api-routes
  api-handler/put-api-routes
  (route/not-found "Not Found"))

(def my-site-defaults
  (assoc site-defaults :security
         (assoc (site-defaults :security) :anti-forgery false)))

(def app
  (wrap-defaults app-routes my-site-defaults))
