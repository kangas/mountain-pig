(ns mtnpig.demo
  (:gen-class))

(defn -init []
  [[] (atom [])])

(defn -main [& args]
  (prn (format "args=%s" args)))