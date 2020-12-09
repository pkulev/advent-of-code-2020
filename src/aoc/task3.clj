(ns aoc.task3
  (:require [clojure.term.colors :refer :all]
            [aoc.core]))

(defn debug-line
  ([line] (println line))
  ([line pos] (debug-line (aoc.core/replace-char line pos (red (get line pos))))))

(defn count-trees
  "Recursively count trees."
  [input idx acc]
  (let [line (fnext input)
        rest-input (next (next input))
        next-idx (if line (mod (+ idx 3) (count line)) 0)
        num-trees (if (= (get line idx) \#) (+ acc 1) acc)]
    (if (nil? line)
      num-trees
      (do
        (debug-line line next-idx)
        (recur rest-input next-idx num-trees)))))

(defn solve-task
  "Solve task3."
  [input]
  (count-trees input 0 0))

(defn solve-*
  "Solve task3*."
  [input]
  (count input))

(defn solve
  "Solve whole task3."
  []
  (let [input (aoc.core/read-resource "task3.txt")]
    (solve-task input)))



    
