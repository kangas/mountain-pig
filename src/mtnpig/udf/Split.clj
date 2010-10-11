(ns mtnpig.udf.Split
  "Direct translation of the Split UDF from 
   http://svn.apache.org/repos/asf/pig/tags/release-0.7.0/contrib/piggybank/java/src/main/java/org/apache/pig/piggybank/evaluation/string/Split.java
  "
  ;; Clojure can't extend generic classes,
  ;; so extend a subclass of org.apache.pig.EvalFunc.
  (:gen-class
   :extends mtnpig.stub.TupleEvalFunc)
  (:import [org.apache.pig.data DataType Tuple TupleFactory]
	   [org.apache.pig.impl.util WrappedIOException]
	   [org.apache.pig.impl.logicalLayer.schema Schema Schema$FieldSchema]
	   [java.io IOException]
	   [java.util List]
	   [java.util.regex Pattern])
  (:require clojure.string))

;; Begin org.apache.pig.EvalFunc interface

(defn -exec
  "Entry point for Pig evaluation. Invoked on every Tuple of a given dataset."
  [this #^Tuple input]
  (let [[#^String s #^String re] (.getAll input)]
    (try
      (.newTuple
       (TupleFactory/getInstance)
       #^List (clojure.string/split s (Pattern/compile re)))
      (catch Exception e
	(throw (WrappedIOException/wrap "****woot****" e))))))

;; (defn -outputSchema
;;   "Specify a name+type for our output. Else, Pig assumes bytearray"
;;   [this #^Schema input]
;;   ;; (Schema. (Schema$FieldSchema. nil DataType/TUPLE)))
;;   (let [classname (.. this getClass getName toLowerCase)]
;;     (Schema.
;;      (Schema$FieldSchema.
;;       (.getSchemaName this classname input)
;;       DataType/TUPLE))))

;; TODO: implement -getArgToFuncMapping so we can accept inputs
;; that are not explicitly typed "chararray" in Pig
