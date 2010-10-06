(ns mtnpig.test.udf.UPPER
  (:use [mtnpig.udf.UPPER] :reload)
  (:use [clojure.test])
  (:import [org.apache.pig.data DefaultTuple])
  )

;; in REPL:
;; (use '[mtnpig.udf.UPPER] :reload)
;; (import [org.apache.pig.data DefaultTuple])

(def testdata
     (doto (new DefaultTuple)
       (.append "Hello\tWorld")
       (.append "Spam\tEggs"))
     )

(deftest test-UPPER-exec
  (let [r (-exec {} testdata)]
    (prn r)
    (is (string? r))))