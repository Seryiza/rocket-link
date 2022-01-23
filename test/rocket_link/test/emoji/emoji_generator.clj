(ns rocket-link.test.emoji.emoji-generator
  (:require [rocket-link.emoji.emoji-generator :refer [get-emojis-by-id]]
            [clojure.test :refer [deftest are]]))

(deftest emoji-generator-by-id
  (are [id emojis] (= emojis (get-emojis-by-id ["😀" "😍"] id))
       1 "😀"
       2 "😍"
       3 "😀😍"))
