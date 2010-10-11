(ns mtnpig.test.udf.Upper
  (:use [mtnpig.udf.Upper] :reload)
  (:use [clojure.test])
  (:import [org.apache.pig.data DefaultTuple])
  )

;; in REPL:
;; (use '[mtnpig.UPPER] :reload)
;; (import [org.apache.pig.data DefaultTuple])

(def testdata
     (doto (new DefaultTuple)
       (.append "Hello\tWorld")
       (.append "Spam\tEggs")))

(deftest test-Upper-exec
  (let [r (-exec {} testdata)]
    (is (string? r))
    (is (not (.find (.matcher #"[a-z]" r))))))
