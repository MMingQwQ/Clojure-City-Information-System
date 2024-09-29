(ns app
  (:require [db])
  (:require [menu]))

(defn -main []
  (print "Please enter the name of the data file => ")
  (flush)
  (let [file-path (read-line)
        cities-db (db/load-data file-path)]
    (menu/menu cities-db)))

(-main)

