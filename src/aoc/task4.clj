(ns aoc.task4)

(defn validate-int
  "Validate int against simple rules: length and range (inclusive)."
  [s num-digits min-value max-value]
  (let [raw-value (first (re-seq (re-pattern (format "^\\d{%s}$" num-digits)) s))
        value (and raw-value (aoc.core/parse-int raw-value))]
    (and value
         (>= value min-value)
         (<= value max-value))))
         

(defn validate-field
  "Validate any allowed field value."
  [[field value]]
  (case field
    "byr" (validate-int value 4 1920 2002)
    "iyr" (validate-int value 4 2010 2020)
    "eyr" (validate-int value 4 2020 2030)
    "hgt" (let [[[value height unit]] (re-seq #"^(\d{2,3})(cm|in)$" value)]
            (and value
                 (case unit
                   "cm" (validate-int height 3 150 193)
                   "in" (validate-int height 2 59 76)
                   false)))
    "hcl" (some? (re-seq #"^#[0-9a-f]{6}$" value))
    "ecl" (some? (re-seq #"^amb|blu|brn|gry|grn|hzl|oth$" value))
    "pid" (some? (re-seq #"^\d{9}$" value))
    "cid" true))

(defn validate-is-passport
  "Validate that hash-map is somehow looks like passport."
  [passport]
  (let [required-fields #{"byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"}]
    (= (clojure.set/intersection required-fields (set (keys passport)))
       required-fields)))

(defn validate-passport
  "Validate passport values."
  [passport]
  (and (validate-is-passport passport)
       (every? true? (map validate-field (seq passport)))))

(defn solve-task
  "Solve task4."
  [input]
  (count (filter validate-is-passport input)))

(defn solve-*
  "Solve task4*."
  [input]
  (count (filter validate-passport input)))

(defn solve
  "Solve whole task4."
  []
  (let [input (->> "task4.txt"
                   clojure.java.io/resource
                   slurp
                   (#(clojure.string/split % #"\n\n"))
                   (map clojure.string/split-lines)
                   (map (partial clojure.string/join " "))
                   (map #(clojure.string/split % #" "))
                   (map #(mapcat (fn [it] (clojure.string/split it #":")) %))
                   (map (partial apply hash-map)))]
    (println (solve-task input))
    (println (solve-* input))))

