(ns rocket-link.app-test
  (:require [clojure.test :refer [deftest is]]
            [ring.mock.request :refer [request]]
            [rocket-link.app :refer [app]]))

(deftest test-index-page
  (let [response (app (request :get "/"))]
    (is (= 200 (:status response)))))
