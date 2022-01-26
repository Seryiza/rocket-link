(ns rocket-link.emoji.unicode-test-parser
  (:require [clojure.java.io :as io]
            [clojure.string :refer [starts-with? includes? split trim join]]))

(defn not-comment? [line]
  (not (starts-with? line "#")))

(defn fully-qualified? [line]
  (includes? line "fully-qualified"))

(defn correct-emoji? [line]
  (and (not-comment? line) (fully-qualified? line)))

(defn parse-emoji-code [line]
  (let [[_ code-points-with-spaces] (re-find #"(.+);" line)]
    (-> code-points-with-spaces trim (split #" "))))

(defn str-emoji [code-points]
  (->> code-points
      (map #(Integer/parseInt % 16))
      (map #(Character/toChars %))
      (map #(String. %))
      join))

(defn convert-emoji-test-to-codes []
  (let [emoji-test-path (io/resource "emoji/emoji-test.txt")
        emoji-out-path (io/resource "emoji/emoji-codes.edn")]
    (with-open [emoji-reader (io/reader emoji-test-path)]
      (->> emoji-reader
         line-seq
         (filter correct-emoji?)
         (map parse-emoji-code)
         (map str-emoji)
         vec
         (spit emoji-out-path)))))
