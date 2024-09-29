(ns menu
  (:require [clojure.string :as str])
  (:require [db]))

; Display the menu and ask the user for the option
(defn show-menu []
  (println "\n\n*** City Information Menu ***")
  (println "-----------------------------\n")
  (println "1. List cities")
  (println "2. Display City Information")
  (println "3. List Provinces")
  (println "4. Display Province Information")
  (println "5. Exit")
  (do 
    (print "\nEnter an option? ") 
    (flush) 
    (read-line)))

; List all cities in the file, ordered by name
(defn option1-1 [cities-db]
  (println "*** List of Cities ***")
  (doseq [city (sort-by :city cities-db)]
    (println (:city city))))

; List all cities of a given province order by size (descending) and name (ascending)
(defn option1-2 [cities-db]
  (print "\nPlease enter the province name => ")
  (flush)
  (let [province-name (read-line)
        cities (filter #(= (:province %) province-name) cities-db)
        sorted-cities (sort-by (juxt (comp - :population) :city) cities)]
    (doseq [city sorted-cities]
      (println (str (:city city) " \"" (:size city) "\" " (:population city))))))

; List all cities of a given province ordered by population density in ascending order
(defn option1-3 [cities-db]
  (print "\nPlease enter the province name => ")
  (flush)
  (let [province-name (read-line)
        cities (filter #(= (:province %) province-name) cities-db)
        sorted-cities (sort-by #(float (/ (:population %) (:area %))) cities)]
    (doseq [city sorted-cities]
      (let [density (float (/ (:population city) (:area city)))]
        (println (str (:city city) " \"" (:size city) "\" " (:population city) " " (:area city) " " density))))))

(defn option1 [cities-db]
  (println "\n1.1 List all cities ordered by name")
  (println "1.2 List all cities of a given province ordered by size and name")
  (println "1.3 List all cities of a given province ordered by population density in ascending order")
  (print "\nEnter a sub-option: ")
  (flush)
  (let [sub-option (read-line)]
    (case sub-option
      "1.1" (option1-1 cities-db)
      "1.2" (option1-2 cities-db)
      "1.3" (option1-3 cities-db)
      (println "Invalid sub-option"))))

; Display size, population, land area, population density for a city
(defn option2 [cities-db]
  (print "\nPlease enter the city name => ") 
  (flush)
  (let [city-name (read-line)]
    (if-let [city (some #(when (= (:city %) city-name) %) cities-db)]
      (println (str "[" (:city city) " \"" (:province city) "\" \"" (:size city) "\" " (:population city) " " (:area city) "]"))
      (println "City not found"))))

; List all provinces with total number of cities
(defn option3 [cities-db]
  (println "*** List of Provinces ***")
  (let [provinces (frequencies (map :province cities-db))
        sorted-provinces (sort-by val > provinces)]
    (doseq [[index [province count]] (map-indexed vector sorted-provinces)]
      (println (str (inc index) ": \"" province "\" " count)))
    (println (str "Total: " (count provinces) " provinces, " (reduce + (vals provinces)) " cities on file."))))

; List all provinces with total population
(defn option4 [cities-db]
  (println "*** List of Provinces by Population ***")
  (let [provinces (reduce (fn [acc {:keys [province population]}]
                            (update acc province (fnil + 0) population))
                          {} cities-db)
        sorted-provinces (sort-by key provinces)]
    (doseq [[index [province population]] (map-indexed vector sorted-provinces)]
      (println (str (inc index) ": \"" province "\" " population)))))

; If the menu selection is valid, call the relevant function to process the selection
(defn process-option [option cities-db]
  (case option
    "1" (option1 cities-db)
    "2" (option2 cities-db)
    "3" (option3 cities-db)
    "4" (option4 cities-db)
    "5" (println "Good Bye")
    (println "Invalid Option, please try again")))

; Display the menu and get a menu item selection. Process the selection and then loop again to get the next menu selection
(defn menu [cities-db]
  (loop []
    (let [option (str/trim (show-menu))]
      (if (= option "5")
        (println "\nGood Bye\n")
        (do 
          (process-option option cities-db)
          (recur))))))

