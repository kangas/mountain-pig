(defproject mountain-pig "0.1.0"
  :description "Utilities for use with Apache Pig"
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
		 [org.clojars.kangas/pig-core "0.6.0"]]
  :dev-dependencies [[swank-clojure "1.2.1"]
		     [lein-javac "1.2.1-SNAPSHOT"]]
  ;; :dev-dependencies [lein-hadoop "1.0.0"]]
  
  ;; Namespaces to compile "ahead-of-time" (AOT).
  ;; Needed for gen-class and other Java interop functionality.
  ;; Put a regex here to compile all namespaces whose names match.
  :aot [#"mtnpig\.udf.*"]

  ;; Compile java stubs using lein-javac
  ;; See github.com/antoniogarrote/lein-javac
  :java-source-path [["src"]
		     ["test" :debug "true"]]
  ;; :java-options {:debug "true"}
  ;; :source-path "src/clojure"
  )
