(ns heylon.handler
  (:use [clojure.java.io :only [resource]])
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [heylon.db.login :as login]))

(defroutes app-routes
  (route/files "heylon/login.html")
  (route/files "heylon/index.html")
  (POST "/heylon/login" {params :params} (str (login/check-login (:email params) (:password params))))
  (route/not-found "Not Found"))

(def my-site-defaults
  (assoc site-defaults :security
         (assoc (site-defaults :security) :anti-forgery false)))

(def app
  (wrap-defaults app-routes my-site-defaults))
