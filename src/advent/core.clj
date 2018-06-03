(ns advent.core
  (:require [clojure.test :refer [deftest is run-tests]]
            [clojure.string :refer [split]]))

(defn process-char [state c]
  (cond
    (:ignore-next state) (assoc state :ignore-next false)
    (:in-garbage state) (case c
                          ">" (assoc state :in-garbage false)
                          "!" (assoc state :ignore-next true)
                          (update state :count inc))

    :else (case c
            "<" (assoc state :in-garbage true)
            "{" (update state :score inc)
            "}" (let [s (:score state)
                      t (:total state)
                      state (update state :score dec)]
                  (assoc state :total (+ t s)))
            state)))

(deftest test-1
  (let [test-case (split "{}" #"")
        result (reduce process-char
                       {:score 0
                        :total 0}
                       test-case)]
    (is
      (= (:total result)
         1))))

(deftest test-2
  (let [test-case (split "{{{}}}" #"")
        result (reduce process-char
                       {:score 0
                        :total 0}
                       test-case)]
    (is
      (= (:total result)
         6))))

(deftest test-3
  (let [test-case (split "{{},{}}" #"")
        result (reduce process-char
                       {:score 0
                        :total 0}
                       test-case)]
    (is
      (= (:total result)
         5))))

(deftest test-4
  (let [test-case (split "{{{},{},{{}}}}" #"")
        result (reduce process-char
                       {:score 0
                        :total 0}
                       test-case)]
    (is
      (= (:total result)
         16))))

(deftest test-5
  (let [test-case (split "{<a>,<a>,<a>,<a>}" #"")
        result (reduce process-char
                       {:score 0
                        :total 0}
                       test-case)]
    (is
      (= (:total result)
         1))))

(deftest test-6
  (let [test-case (split "{<a>,<a>,<a>,<a>}" #"")
        result (reduce process-char
                       {:score 0
                        :total 0}
                       test-case)]
    (is
      (= (:total result)
         1))))

(deftest test-7
  (let [test-case (split "{{<ab>},{<ab>},{<ab>},{<ab>}}" #"")
        result (reduce process-char
                       {:score 0
                        :total 0}
                       test-case)]
    (is
      (= (:total result)
         9))))

(deftest test-8_
  (let [test-case (split "{{<!!>},{<!!>},{<!!>},{<!!>}}" #"")
        result (reduce process-char
                       {:score 0
                        :total 0}
                       test-case)]
    (is
      (= (:total result)
         9))))

(deftest test-9
  (let [test-case (split "{{<a!>},{<a!>},{<a!>},{<ab>}}" #"")
        result (reduce process-char
                       {:score 0
                        :total 0}
                       test-case)]
    (is
      (= (:total result)
         3))))

(def input (slurp "src/advent/input.txt"))
