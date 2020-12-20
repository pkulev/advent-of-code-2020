(ns aoc.task6)

(defn solve-task
  "Solve task6."
  [input]
  (->> input
       (map #(clojure.string/replace % #"\n" ""))
       (map set)
       (sequence cat)
       frequencies
       vals
       (apply +)))

(defn solve-*
  "Solve task6*."
  [input]
  (->> input
       (map clojure.string/split-lines)
       (#(map (partial apply clojure.set/intersection)
              (map (partial map set) %)))
       (map count)
       (reduce +)))

(defn parse-input
  []
  (->> "task6.txt"
       clojure.java.io/resource
       slurp
       (#(clojure.string/split % #"\n\n"))))

(defn solve
  "Solve whole task6."
  []
  (let [input (parse-input)]
    (println (solve-task input))
    (println (solve-* input))))
