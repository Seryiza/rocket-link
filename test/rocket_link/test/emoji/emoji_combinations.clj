(ns rocket-link.test.emoji.emoji-combinations
  (:require [rocket-link.emoji.emoji-combinations :as emoji]
            [clojure.test :refer [deftest are]]))

(deftest emoji-combinations
  (are [id emojis] (= emojis (emoji/get-emojis-combination ["😀" "😍"] id))
       1 "😀"
       2 "😍"
       3 "😀😍"))
