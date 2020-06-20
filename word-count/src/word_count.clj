(ns word-count)

(require '[clojure.string :as str :only lower-case])

(defn word-count [s]
  (->>
   s
   (str/lower-case)
   (re-seq #"\w+")
   (group-by identity)
   (map (fn [[k,v]] [k, (count v)]))
   (into {})
  ))