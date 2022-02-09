(ns rocket-link.app
  (:require [rocket-link.main-page.routes :as main-page-routes]
            [rocket-link.links.routes :as links-routes]
            [rocket-link.user.routes :as user-routes]
            [mount.core :as mount :refer [defstate]]
            [reitit.ring :as ring]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.middleware.session :refer [wrap-session]]
            [ring.middleware.flash :refer [wrap-flash]]))

(defstate app
  :start (ring/ring-handler
          (ring/router
            [["/" main-page-routes/show-handler]
             ["/to/:shortcut" links-routes/redirect-to-target-handler]
             ["/links" {:post links-routes/create-handler}]
             ["/links/:shortcut" ["/created" links-routes/show-created-handler]]
             ["/login" {:get user-routes/show-login-handler
                        :post user-routes/login-handler}]])
          (ring/routes
            (ring/create-resource-handler {:path "/assets"})
            (ring/create-default-handler))
          {:middleware [wrap-params
                        wrap-keyword-params
                        wrap-session
                        wrap-flash]}))

