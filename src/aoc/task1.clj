(ns aoc.task1
  (:require [clojure.math.combinatorics :as combo]))

(defn solve
  "Solve task 1."
  []
  (let [input (aoc.core/read-resource "task1.txt" aoc.core/parse-int)
        target-pair (two-sum-to input 2020)]
    (apply * target-pair)))

(defn two-sum-to
  "Returns list of two elements that sums to provided number."
  [numbers to]
  (first (filter #(= (apply + %) to)
                 (combo/combinations numbers 2))))

