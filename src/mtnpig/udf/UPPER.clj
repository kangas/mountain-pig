(ns mtnpig.udf.UPPER
  (:gen-class
   ;; :extends org.apache.pig.EvalFunc
   :extends mtnpig.stub.StringEvalFunc
   ;; :init init
   )
  (:import [org.apache.pig.data Tuple]
	   [org.apache.pig.impl.util WrappedIOException]
	   ))

(defn -exec
  "input: Tuple, output: String"
  [this #^Tuple input]
  (let [str (. input get 0)]
    (if (instance? String str)
      (.toUpperCase str))
    ))

;; (defn -main [& args]
;;   (prn (format "args=%s" args)))