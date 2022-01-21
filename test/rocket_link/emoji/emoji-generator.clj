(ns rocket-link.emoji.emoji-generator
  (:require [rocket-link.emoji.emoji-generator :refer [generate-emojis-by-id]]
            [clojure.test :refer [deftest are]]))

(deftest emoji-generator
  (are [id emojis] (= emojis (generate-emojis-by-id ["😀" "😍"] id))
       1 "😀"
       2 "😍"
       3 "😀😍"))
