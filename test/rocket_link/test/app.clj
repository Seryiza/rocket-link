(ns rocket-link.test.app
  (:require [clojure.test :refer [deftest is use-fixtures]]
            [ring.mock.request :refer [request]]
            [rocket-link.app :refer [app]]
            [mount.core :as mount]))

(use-fixtures
  :once
  (fn [t]
    (mount/start #'rocket-link.app/app)
    (t)))

(deftest test-index-page
  (let [response (app (request :get "/"))]
    (is (= 200 (:status response)))))
