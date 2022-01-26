(ns rocket-link.emoji.emoji-combinations
  (:require [clojure.string :as str]
            [mount.core :refer [defstate]]
            [clojure.java.io :as io]))

(defstate all-emojis
  :start (-> "emoji/emoji-codes.edn" io/resource slurp read-string))

(defn break-id-into-emoji-indexes [emojis-count id]
  (loop [current-id (dec id)
         indexes []]
    (let [next-id       (quot current-id emojis-count)
          current-index (mod current-id emojis-count)]
      (if (pos? next-id)
        (recur next-id (conj indexes current-index))
        (conj indexes current-index)))))

(defn get-emojis-combination [emojis id]
  (->> id
       (break-id-into-emoji-indexes (count emojis))
       (map emojis)
       str/join))

(defn get-combination-from-all-emojies [id]
  (get-emojis-combination all-emojis id))
