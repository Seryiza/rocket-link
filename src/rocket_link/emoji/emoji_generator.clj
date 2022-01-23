(ns rocket-link.emoji.emoji-generator
  (:require [clojure.string :refer [join]]
            [mount.core :refer [defstate]]
            [clojure.java.io :as io]))

(defstate all-emojis
  :start (-> "emoji/emoji-codes.edn" io/resource slurp read-string))

(defn get-emojis-indexes [emojis-count id]
  (loop [current-id (dec id)
         indexes []]
    (let [next-id       (quot current-id emojis-count)
          current-index (mod current-id emojis-count)]
      (if (pos? next-id)
        (recur next-id (conj indexes current-index))
        (conj indexes current-index)))))

(defn get-emojis-by-id [emojis id]
  (->> id
       (get-emojis-indexes (count emojis))
       (map emojis)
       join))

(defn generate-emojis-by-id [id]
  (get-emojis-by-id all-emojis id))
