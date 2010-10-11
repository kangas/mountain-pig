(ns mtnpig.daylife.QueryDateRange
  "Input: HTTP GET query parameter string
   Output: delta in days between start_time/end_time
   Assume query args per http://developer.daylife.com/docs"
  
  ;; Clojure can't extend generic classes,
  ;; so extend a subclass of org.apache.pig.EvalFunc.
  (:gen-class
   :extends mtnpig.stub.TupleEvalFunc)
  (:import [org.apache.pig.data DataType Tuple TupleFactory]
	   [org.apache.pig.impl.util WrappedIOException]
	   [org.apache.pig.impl.logicalLayer.schema Schema Schema$FieldSchema]
	   [java.io IOException]
	   [java.net URLDecoder]
	   [java.util List Date]
	   [javax.xml.bind DatatypeConverter])
  (:require [mtnpig.core :as core]
	    [mtnpig.daylife.dlcore :as dlcore]))

;;; -----

(defn query-compute-dayrange
  "Return delta in days between start/end_date"
  [& querystr]
  (core/url-decode querystr)
  )

;;;
;;; Begin org.apache.pig.EvalFunc interface
;;;

(defn -exec
  "Entry point for Pig evaluation. Invoked on every Tuple of a given dataset."
  [this #^Tuple input]
  (let [[#^String s] (.get input 0)]
    (try
      (.newTuple (TupleFactory/getInstance) #^List (query-compute-dayrange s))
      (catch Exception e
	(throw (WrappedIOException/wrap "**woot**" e))))))
