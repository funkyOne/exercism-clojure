(ns rna-transcription)

(defn to-rna [dna] 
  (apply str (map #(case %
                     \G "C"
                     \C "G"
                     \T "A"
                     \A "U"
                     (throw (AssertionError. "Unknown strand"))) dna)))