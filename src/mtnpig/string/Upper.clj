(ns mtnpig.string.Upper
  "Direct translation of the UPPER UDF described at 
   http://hadoop.apache.org/pig/docs/r0.7.0/udf.html"
  
  ;; Clojure can't extend generic classes,
  ;; so extend a subclass of org.apache.pig.EvalFunc.
  (:gen-class
   :extends mtnpig.stub.StringEvalFunc)
  (:import [org.apache.pig.data Tuple DataType]
	   [org.apache.pig.impl.util WrappedIOException]
	   [org.apache.pig.impl.logicalLayer.schema Schema Schema$FieldSchema]
	   [java.io IOException])
  (:require clojure.string)
  )

(defn upcase-java
  "Native Java upcasing"
  [#^String field]
  (.toUpperCase field))

(defn upcase-clojure
  "Upcase via Clojure libraries"
  [field]
  (clojure.string/upper-case field))

;;; Begin org.apache.pig.EvalFunc interface

(defn -exec
  "Entry point for Pig evaluation. Invoked on every Tuple of a given dataset."
  [this #^Tuple input]
  (let [field (.get input 0)]
    ;; check for invalid input first
    (if (not (instance? String field))
      (throw (IOException. (format "Input is not String: %s" (type field))))
      (try
	(upcase-clojure field)
	(catch Exception e
	  ;; this improves pig error output
	  (throw (WrappedIOException/wrap "****woot****" e)))))))

(defn -outputSchema
  "Assign a name to the output column.
   We just clone our input schema."
  [this #^Schema input]
  (Schema. input)
  ;; (let [classname (.. this getClass getName toLowerCase)]
  ;;   (Schema.
  ;;    (Schema$FieldSchema.
  ;;     (.getSchemaName this classname input)
  ;;     DataType/CHARARRAY)))
  )

;; TODO: implement -getArgToFuncMapping so we can accept inputs
;; that are not explicitly typed "chararray" in Pig
