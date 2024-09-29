(ns db
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn parse-line [line]
  (let [[city province size population area] (str/split line #"\|")]
    {:city city
     :province province
     :size size
     :population (read-string population)
     :area (read-string area)}))

(defn load-data [file-path]
  (->> (slurp file-path)
       (str/split-lines)
       (map parse-line)))

