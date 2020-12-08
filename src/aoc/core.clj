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
