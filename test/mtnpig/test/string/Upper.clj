(ns mtnpig.test.string.Upper
  (:use [mtnpig.string.Upper] :reload)
  (:use [clojure.test])
  (:import [org.apache.pig.data DefaultTuple])
  )

;; in REPL:
;; (use '[mtnpig.UPPER] :reload)
;; (import [org.apache.pig.data DefaultTuple])

(def testdata
     (doto (new DefaultTuple)
       (.append "Hello\tWorld")
       (.append "Spam\tEggs"))
     )

(deftest test-Upper-exec
  (let [r (-exec {} testdata)]
    (prn r)
    (is (string? r))))
