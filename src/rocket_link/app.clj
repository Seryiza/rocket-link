(ns rocket-link.app
  (:require [rocket-link.main-page.handlers :as main-page]
            [rocket-link.links.handlers :as links]
            [rocket-link.user.handlers :as user]
            [mount.core :as mount :refer [defstate]]
            [reitit.ring :as ring]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.session :refer [wrap-session]]
            [ring.middleware.flash :refer [wrap-flash]]))

(defstate app
  :start (ring/ring-handler
          (ring/router
            [["/" main-page/show-handler]
             ["/to/:shortcut" links/redirect-to-target-handler]
             ["/links" {:post links/create-handler}]
             ["/links/:shortcut" ["/created" links/show-created-handler]]
             ["/login" {:get user/show-login-handler
                        :post user/login-handler}]
             ["/register" {:get user/show-register-handler
                           :post user/register-handler}]
             ["/logout" user/logout-handler]])
          (ring/routes
            (ring/create-resource-handler {:path "/assets"})
            (ring/create-default-handler))
          {:middleware [wrap-params
                        wrap-keyword-params
                        wrap-session
                        wrap-flash]}))

