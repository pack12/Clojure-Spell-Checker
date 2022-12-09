(ns spell-checker.core
  (:require  [clojure.string  :as str]))

;;Opens a file to be read
(defn read-file [path]
  (slurp path))

;;Converts the lines of the dictionary to a set
(defn to-dict [wordstring]
  (set (clojure.string/split-lines wordstring)))

(def dict
  (to-dict (read-file "src/spell_checker/dictionary.txt")))

;;Checks if the word already exists in the file
(defn correct-word? [word]
  (contains? dict word))

;;TODO
(defn damerau–levenshtein [word dictionary]
  (+ 0 (rand 2)))

;; (defn damerau–levenshtein [word dictionary]
;;   (let [s1 word]))

;;Uses min-key to find the closest match in the dictionary
(defn min-distance [word]
  (apply min-key (partial damerau–levenshtein word) dict))

(defn main [& args]
  (println "Enter a word to spell check :")
  (let [word  (read-line)]
    (if (correct-word? word)
      (println word "Is spelled correctly. ")
      (do
        (println word "Is misspelled. ")
        (println "Did you mean -> " (min-distance word))))))

(main)