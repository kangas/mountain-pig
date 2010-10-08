(ns mtnpig.test.string.Split
  (:use [mtnpig.string.Split] :reload)
  (:use [clojure.test])
  (:import [org.apache.pig.data DefaultTuple TupleFactory])
  )

;; in REPL:
;; (use '[mtnpig.UPPER] :reload)
;; (import [org.apache.pig.data DefaultTuple])

;; DOESN'T WORK?? WTF?
(defn make-tuple-BROKEN
  [& args]
  (let [t (new DefaultTuple)]
    (for [arg args] (.append t arg))
    t))

(defn make-tuple
  [x y]
  (let [t (.newTuple (TupleFactory/getInstance))]
    (.append t x)
    (.append t y)
    t))

;; (defn print-any
;;   [& args]
;;   (for [arg args] (prn arg)))

(def testdata
     (doto (new DefaultTuple)
       (.append "Hello\tWorld")
       (.append "Spam\tEggs")))

(def testdata2
     (make-tuple "Hello\tWorld" "Spam\tEggs"))

(deftest test-maketuple
  (is (= testdata testdata2)))

(deftest test-Split-exec
  (is (= (make-tuple "xx" "yy")
       (-exec {} (make-tuple "xx?yy" "[?]")))))