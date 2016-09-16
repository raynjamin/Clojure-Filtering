(ns purchases-parser.core
(:require [clojure.string :as str] [clojure.data.json :as json])
(:gen-class))

(defn -main []
  ;let variable params will not be available outside the let
  (println "Type a category:")
  (let [purchases (slurp "purchases.csv")
        purchases (str/split-lines purchases)
        purchases (map (fn [line]
                         (str/split line #","))
                       purchases)
        header (first purchases)
        purchases (rest purchases)
        purchases (map (fn [line]
                         (zipmap header line))
                       purchases)
        category (read-line)
        purchases (filter (fn [line]
                            (= (get line "category") category))
                          purchases)
        file-text (json/write-str purchases)]
     (spit "filtered_purchases.json" file-text)))
