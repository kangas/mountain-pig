(ns mtnpig.daylife.dlcore
  (:import [java.net URLDecoder])
  (:require [clojure.string :as string])
  (:use [clojure.test])
  (:import [java.util List Date]
	   [javax.xml.bind DatatypeConverter]
	   [java.text DateFormat]))


(defn parse-date
  "Daylife API accepts dates in the following forms:
   - epoch value: 1286581148
   - ISO 8601: 2004-03-05 23:00:00
   - abbreviations: 2004-03, 2004-03-05 23, etc
   Returns: java.util.Date"
  [#^String datestr]
  (let [epoch-regex #"^[\d]{8,10}$"]
    (if (.matches (.matcher epoch-regex datestr))
      ;; parse as epoch
      (Date. (* (Long/parseLong datestr) 1000))
      ;; parse as ISO8601
      ;; xsd:dateTime expects "T" as time delimiter, we use space, so fixup
      (.getTime (DatatypeConverter/parseDateTime
		 (.replace datestr \space \T))))))

(defn day-from-date
  "Return days since epoch"
  [#^Date date]
  (let [miliseconds-per-day (* 1000 60 60 24)]
    (/ (.getTime date) miliseconds-per-day)))

;;; tests

(deftest test-parse-date
  (let [df (DateFormat/getDateTimeInstance)]
    (is (= (.parse df "Sep 02, 2010 5:00:01 PM EDT")
	   (parse-date "1283461201")))
    (is (= (.parse df "Sep 02, 2010 12:00:00 AM EDT")
	   (parse-date "2010-09-02")))
    (is (= (.parse df "Sep 02, 2010 10:00:20 AM EDT")
	   (parse-date "2010-09-02 10:00:20")))
    ;; oops, we don't support all of the abbrev forms yet
    ;; (prn (parse-date "2004-03-05 23"))
    ))

