(ns mtnpig.core
  (:require [clojure.string :as string])
  (:use [clojure.test])
  (:import [java.net URLDecoder]
	   [org.apache.pig.data DataType Tuple TupleFactory]))

;;; Pig datatype utilities

;; DOESN'T WORK??
(defn make-tuple-BROKEN
  [& args]
  (let [t (.newTuple (TupleFactory/getInstance))]
    (for [arg args] (.append t arg))
    t))

(defn make-tuple
  [x y]
  (let [t (.newTuple (TupleFactory/getInstance))]
    (.append t x)
    (.append t y)
    t))


;;; URL data utilities
;;; Copied from ring.middleware.params

(defn url-decode
  "Returns the form-url-decoded version of the given string, using either a
  specified encoding or UTF-8 by default."
  [encoded & [encoding]]
  (URLDecoder/decode encoded (or encoding "UTF-8")))
 
(defn assoc-param
  "Associate a key with a value. If the key already exists in the map,
  create a vector of values."
  [map key val]
  (assoc map key
	 (if-let [cur (map key)]
	   (if (vector? cur)
	     (conj cur val)
	     [cur val])
	   val)))

(defn parse-params
  "Parse parameters from a string into a map. Assume UTF-8 input."
  [^String param-string]
  (reduce
   (fn [param-map encoded-param]
     (if-let [[_ key val] (re-matches #"([^=]+)=(.*)" encoded-param)]
       (assoc-param param-map
		    (url-decode key)
		    (url-decode (or val "")))
       param-map))
   {}
   (string/split param-string #"&")))

;;; tests

(deftest test-maketuple
  (is (= (make-tuple "Spam" "Eggs")
	 (doto (.newTuple (TupleFactory/getInstance))
	   (.append "Spam")
	   (.append "Eggs")))))

(deftest test-urldecode
  (is (= "start_time=2010-09-02 10:00:20"
	 (url-decode "start_time=2010-09-02+10%3A00%3A20"))))

(deftest test-parse-params
  (is (= {"start_time" "2010-09-02 10:00:20", "item" ["1" "2" "3"]}
	 (parse-params "item=1&item=2&item=3&start_time=2010-09-02+10%3A00%3A20"))))