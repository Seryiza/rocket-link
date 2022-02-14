(ns rocket-link.links.handlers
  (:require [rocket-link.links.links :as links]
            [rocket-link.html :as html]
            [rocket-link.emoji.emoji-combinations :as emoji]
            [rocket-link.punycode :as punycode]
            [rocket-link.url :as url]))

(defn create-handler [request]
  (let [url (-> request :params :url)
        user-id (-> request :session :user :id)
        link {:url url :user-id user-id}
        created-shortcut (links/create! link emoji/get-combination-from-all-emojies)
        created-link-url (url/make-project-url "/links/" (url/encode created-shortcut) "/created")]
    (punycode/redirect created-link-url)))

(defn show-created-handler [request]
  (let [shortcut (-> request :path-params :shortcut)
        created-link (url/make-project-url "/to/" shortcut)]
    (html/render request "links/created.html" {:created-link created-link})))

(defn redirect-to-target-handler [request]
  (let [shortcut (-> request :path-params :shortcut)
        link (links/find-by-shortcut shortcut)]
    (if (nil? link)
      {:status 404, :body "Oh, non-existing URL"}
      (punycode/redirect (:url link)))))
