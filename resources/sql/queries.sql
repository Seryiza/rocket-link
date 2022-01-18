-- :name get-links :? :*
-- :doc Get all links
SELECT * FROM links;

-- :name get-link :? :1
-- :doc Get the link
SELECT * FROM links
WHERE id = :id;

-- :name create-link! :! :n
-- :doc Add a new link
INSERT INTO links
(url, emojis)
VALUES (:url, :emojis)
