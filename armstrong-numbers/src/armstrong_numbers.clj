(ns armstrong-numbers)

(declare char-to-int calculate expt)

(defn armstrong? [num]
  (= num (calculate (str num))))

(defn- calculate [s]  
  (let [len (count s)]
    (apply + (map (comp (partial expt len) str) s))))

(defn- char-to-int [char] (Character/digit char 10))

(defn- expt [exponent base-str] (.pow (BigInteger. base-str) exponent))