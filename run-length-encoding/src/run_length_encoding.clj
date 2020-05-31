(ns run-length-encoding)

(defn append [strr char n]
  (str strr (if (= n 0) char (str (inc n) char))))

(defn run-length-encode2
  "encodes a string with run-length-encoding"
  [plain-text]
  (loop [acc "" reps 0 prev nil remaining plain-text]
    (if (empty? remaining)
      (append acc prev reps)
      (let [curr (first remaining) tail (rest remaining)]
        (if (= prev curr)
          (recur acc (inc reps) curr tail)
          (recur (append acc prev reps) 0 curr tail))))))

(defn run-length-encode
  "encodes a string with run-length-encoding"
  [plain-text]
  (->> plain-text
       (partition-by identity)
       (mapcat (juxt count first))      
       (remove #{1})
       (apply str)))

(run-length-encode "aaaAAAAzzzx")

(defn run-length-decode2
  "decodes a run-length-encoded string"
  [cipher-text]
  (loop [acc "" reps "" remaining cipher-text]
    (if (empty? remaining)
      acc
      (let [curr (first remaining) tail (rest remaining)]
        (if (Character/isDigit curr)
          (recur acc (str reps curr) tail)
          (recur (apply str acc (repeat (if (empty? reps) 1 (Integer/parseInt reps)) curr)) "" tail))))))

(defn run-length-decode [cipher-text]
  (->> cipher-text
       (re-seq #"(\d*)(\D)")
       (mapcat (fn [[_ cnt char]] (repeat (if (= cnt "") 1 (Integer/parseInt cnt)) char)))
       (apply str)))

(run-length-decode "45AF4N")