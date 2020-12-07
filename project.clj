(defproject aoc "0.1.0-SNAPSHOT"
  :description "Solutions for Advent of Code 2020"
  :url "https://github.com/pkulev/advent-of-code-2020"
  :license {:name "MIT"
            :url "https://mit-license.org/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/math.combinatorics "0.1.5"]]
  :plugins [[lein-cljfmt "0.7.0"]]
  :main ^:skip-aot aoc.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
