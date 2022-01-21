(ns rocket-link.emoji.emoji-generator
  (:require [clojure.string :refer [join]]))

(defn get-emojis-indexes [emojis-count id]
  (loop [current-id (dec id)
         indexes []]
    (let [next-id       (quot current-id emojis-count)
          current-index (mod current-id emojis-count)]
      (if (pos? next-id)
        (recur next-id (conj indexes current-index))
        (conj indexes current-index)))))

(defn generate-emojis-by-id [emojis id]
  (->> id
       (get-emojis-indexes (count emojis))
       (map emojis)
       join))


