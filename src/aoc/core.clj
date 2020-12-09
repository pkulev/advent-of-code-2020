(ns aoc.core
  (:gen-class))

;; TODO: collect all aoc.taskN/solve[-*] and call them forming table of answers
(defn -main
  [& args]
  ())

(defn read-resource%
  "Return list of lines for resource file by name."
  [file]
  (-> file
      (clojure.java.io/resource)
      (slurp)
      (clojure.string/split-lines)))

(defn read-resource
  "Return list of lines for resource file by name."
  ([file] (read-resource% file))
  ([file fn] (map fn (read-resource% file))))

(defn parse-int
  "Parse integer from string."
  [str]
  (Integer/parseInt str))

(defn bool-xor
  "Boolean xor."
  [a b]
  (Boolean/logicalXor a b))

(defn replace-char
  "Replace char in string at index."
  [s idx char]
  (str (subs s 0 idx) char (subs s (inc idx))))
