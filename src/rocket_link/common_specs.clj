(ns rocket-link.common-specs
  (:require [clojure.spec.alpha :as s]
            [clojure.string :as str]))

(s/def :common-specs/ne-string (complement str/blank?))
