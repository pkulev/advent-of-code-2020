(ns aoc.task1
  (:require [clojure.math.combinatorics :as combo]))

(defn solve
  []
  (let [input (aoc.core/read-resource "task1.txt" aoc.core/parse-int)]
    (println (solve-task input))
    (println (solve-* input))))

(defn solve-task
  "Solve task 1."
  [input]
  (let [target-pair (n-sum-to 2 input 2020)]
    (apply * target-pair)))

(defn solve-* [input]
  (let [target-trio (n-sum-to 3 input 2020)]
    (apply * target-trio)))

(defn n-sum-to
  "Returns list of N elements that sums to provided number."
  [n numbers to]
  (first (filter #(= (apply + %) to)
                 (combo/combinations numbers n))))
