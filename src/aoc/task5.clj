(ns aoc.task5)

(defn solve-task
  "Solve task5."
  [input]
  (apply max input))

(defn solve-*
  "Solve task5*."
  [input]
  (let [input (vec (sort input))]
    (->> input
         (#(map (fn [it idx] (vector it idx)) % (range)))
         (filter (fn [[it idx]] (and (< (inc idx) (count input))
                                     (= (+ it 2) (input (inc idx))))))
         ffirst
         inc)))

(defn solve-*-map-idx
  "Append from 0 to start of progression to find first index that differs from value."
  [input]
  (let [input (vec (sort input))
        input (concat (range (first input)) input)]
    (->> input
         (#(map (fn [it idx] (vector it idx)) % (range)))
         (filter (fn [[it idx]] (not (= it idx))))
         first
         second)))

(defn solve-*-arithmetic-progression
  "Append from 1 to start of the progression and use sums to find missing value."
  [input]
  (let [input (sort input)
        input (vec (concat (range 1 (first input)) input))
        sum (apply + input)
        cnt (count input)
        last (input (dec cnt))]
    (- (* (/ last 2) (+ (* 2 (first input)) (dec last)))
       sum)))

(defn solve-*-reduce
  "Find gap and immediately return it."
  [input]
  (let [input (vec (sort input))]
    (reduce (fn [a b]
              (if (= (- b a) 2)
                (reduced (inc a))
                b))
            input)))

(defn parse-seat
  "Parse seat BSP representation."
  [seat]
  (-> seat
      (clojure.string/replace #"[FL]" "0")
      (clojure.string/replace #"[BR]" "1")
      (aoc.core/parse-int 2)))

(defn solve
  "Solve whole task5."
  []
  (let [input (aoc.core/read-resource "task5.txt" parse-seat)]
    (println (solve-task input))
    (println (solve-* input))))
