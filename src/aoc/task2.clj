(ns aoc.task2)

(defn parse-input-entry
  "Parse password DB entry."
  [entry]
  (let [[rule raw-letter password] (clojure.string/split entry #" ")
        [min-count max-count] (map aoc.core/parse-int (clojure.string/split rule #"-"))
        letter (clojure.string/replace raw-letter ":" "")]
    (list min-count max-count letter password)))

(defn password-checker
  "Check password against rules for concrete letter."
  [checker-fn]
  (partial apply checker-fn))

(defn occurrence-checker
  "Count of letter in password must be in [min-count, max-count]."
  [min-count max-count letter password]
  (let [occurrences (count (re-seq (re-pattern letter) password))]
    (and (>= occurrences min-count)
         (<= occurrences max-count))))

(defn position-checker
  "Letter must be only in password at first-pos or second-pos."
  [first-pos second-pos letter password]
  (aoc.core/bool-xor (= letter (str (get password (- first-pos 1))))
                     (= letter (str (get password (- second-pos 1))))))

(defn solve
  "Solve whole task2."
  []
  (let [input (aoc.core/read-resource "task2.txt" parse-input-entry)]
    (println (solve-task input))
    (println (solve-* input))))

(defn solve-task
  "Solve task2."
  [input]
  (->> input
       (filter (password-checker occurrence-checker))
       (count)))

(defn solve-*
  "Solve task2*."
  [input]
  (->> input
       (filter (password-checker position-checker))
       (count)))
