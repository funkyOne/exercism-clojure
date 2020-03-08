(ns bob)
(use '[clojure.string :only (trimr)])

(defn strip-non-words [s] (clojure.string/replace s #"[^a-zA-Z]" ""))

(defn yell? [s] (re-matches #"[A-Z]+" (strip-non-words s)))

(defn question? [s] (= (last s) \?))

(defn response-for [s]
  (let [s (trimr s)]
    (cond
      (empty?     s) "Fine. Be that way!"
      (yell?      s) (if (question? s) 
                        "Calm down, I know what I'm doing!"
                        "Whoa, chill out!")
      (question?  s)  "Sure."
      :else           "Whatever.")))

