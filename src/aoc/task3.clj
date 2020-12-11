(ns aoc.task3
  (:require [clojure.term.colors :refer :all]
            [aoc.core]))

(defn debug-line
  ([line] (println line))
  ([line pos] (debug-line (aoc.core/replace-char line pos (red (get line pos))))))

(defn count-trees
  "Recursively count trees."
  [input right down]
  (defn count-trees%
   "Recursive inner function."
   [input idx acc]
   (let [line (first input)
         rest-input (drop down input)
         next-idx (if line (mod (+ idx right) (count line)) 0)
         num-trees (if (= (get line idx) \#) (+ acc 1) acc)]
     (if (nil? line)
       num-trees
       (do
         (debug-line line idx)
         (recur rest-input next-idx num-trees)))))

  (count-trees% input 0 0))

(defn solve-task
  "Solve task3."
  [input]
  (count-trees input 3 1))

(defn solve-*
  "Solve task3*."
  [input]
  (let [args (for [[right down] [[1 1] [3 1] [5 1] [7 1] [1 2]]]
               [input right down])]
    (reduce * (map (partial apply count-trees) args))))

(defn solve
  "Solve whole task3."
  []
  (let [input (aoc.core/read-resource "task3.txt")]
    (solve-task input)
    (solve-* input)))
