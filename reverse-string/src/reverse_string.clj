(ns reverse-string)

(defn reverse-string [s]
  (apply str (reduce (fn [acc e] (conj acc e)) '() s)))
