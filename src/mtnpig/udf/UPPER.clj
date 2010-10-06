(ns mtnpig.udf.UPPER
  (:gen-class
   ;; We can't extend org.apache.pig.EvalFunc directly,
   ;; so use a de-templating wrapper class instead
   :extends mtnpig.stub.StringEvalFunc)
  (:import [org.apache.pig.data Tuple]
	   [org.apache.pig.impl.util WrappedIOException]
	   [java.io IOException])
  (:require clojure.string)
  )

(defn upcase-java
  "Fastest way to upcase"
  [#^String field]
  (.toUpperCase field))

(defn upcase-clojure
  "Use Clojure libraries instead"
  [field]
  (clojure.string/upper-case field))

(defn -exec
  "Port of http://hadoop.apache.org/pig/docs/r0.7.0/udf.html#How+to+Write+a+Simple+Eval+Function"
  [this #^Tuple input]
  (let [field (.get input 0)]
    ;; check for invalid input first
    (if (not (instance? String field))
      (throw (IOException. (format "Input is not String: %s" (type field))))
      ;; else
      (try
	(upcase-clojure field)
	(catch Exception e
	  ;; this improves pig error output
	  (throw (WrappedIOException/wrap "****woot****" e)))))))
