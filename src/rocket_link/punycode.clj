(ns rocket-link.punycode
  (:require [clojure.string :as str]
            [ring.util.response :as response])
  (:import [java.net URL IDN]))

(defn encode [text]
  (IDN/toASCII text IDN/ALLOW_UNASSIGNED))

(defn redirect [url-string]
  (let [url (URL. url-string)
        host (.getHost url)
        punycoded-host (encode host)
        punycoded-url (str/replace-first url-string host punycoded-host)]
    (response/redirect punycoded-url)))

