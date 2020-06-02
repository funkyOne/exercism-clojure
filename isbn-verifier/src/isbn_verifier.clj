(ns isbn-verifier)

(defn valid? [s]
  (and (= (count s) 10)
       (->> s
            (drop-last)
            (every? #(Character/isDigit %)))
       (->> s
            (last)
            (#(or (Character/isDigit %) (= \X %))))))

(defn isbn? [isbn]
  (let [stripped (remove #(= % \-) isbn)]
    (if (valid? stripped)
      (->> stripped           
           (map-indexed vector)
           (map (fn [[i, char]] (* (- 10 i) (if (and (= 9 i) (= \X char)) 10 (Character/digit char 10)))))
           (reduce +)
           (#(mod % 11))
           zero?)
      false)))





