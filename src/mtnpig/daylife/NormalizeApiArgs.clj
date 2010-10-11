(ns mtnpig.daylife.NormalizeApiArgs
  "Process a string of GET query parameters per
   http://developer.daylife.com/docs/"
  
  ;; Clojure can't extend generic classes,
  ;; so extend a subclass of org.apache.pig.EvalFunc.
  (:gen-class
   :extends mtnpig.stub.TupleEvalFunc)
  (:import [org.apache.pig.data DataType Tuple TupleFactory]
	   [org.apache.pig.impl.util WrappedIOException]
	   [org.apache.pig.impl.logicalLayer.schema Schema Schema$FieldSchema]
	   [java.util List]
	   [java.io IOException])
  (:require clojure.string))

;; (def keep-tokens-regex #"^(start_time|end_time|query|offset|limit)=")
;; (def ignore-tokens-regex #"^(_uuid|signature)=")

(defn normalize-query
  "Split HTTP query string"
  [#^String query-string]
  (let [tokens (clojure.string/split query-string #"[&]")]
    (sort tokens)))

;;;
;;; Begin org.apache.pig.EvalFunc interface
;;;

(defn -exec
  "Entry point for Pig evaluation. Invoked on every Tuple of a given dataset."
  [this #^Tuple input]
  (let [[#^String s] (.get input 0)]
    (try
      (.newTuple (TupleFactory/getInstance) #^List (normalize-query s))
      (catch Exception e
	(throw (WrappedIOException/wrap "**woot**" e))))))

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
