(ns rocket-link.test.punycode
  (:require [rocket-link.punycode :as punycode]
            [clojure.test :refer [deftest is]]))

(deftest test-punycode-encoding
  (is (= "xn--qv8hrw.ml" (punycode/encode "🚀🔗.ml"))))

(deftest test-punycode-redirect
  (is (=
       {:body "", :status 302, :headers {"Location" "https://xn--qv8hrw.ml/to/😆"}}
       (punycode/redirect "https://🚀🔗.ml/to/😆"))))
