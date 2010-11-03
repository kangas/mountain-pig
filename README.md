# mountain-pig

Extensions for Apache Pig, written in Clojure.

"Mountain Pig" is a nickname for my sister's dog Hank, a French Bulldog.
We call him this because he's a little porker who will climb anything.
Here's a picture of him in action:
<http://www.flickr.com/photos/mattkangas/2956057564>

Yes, he's ridiculous. So is the idea of embedding Clojure within Pig. 
But here it is!

## Status - November 2010

So far it's just a proof-of-concept that yes, it is possible to write
Pig user defined functions (UDFs) in Clojure. It is not yet a 
well-rounded library comparable to the Pig "piggybank".

## Requirements

1. Pig 0.6	- <http://hadoop.apache.org/pig>
2. Clojure 1.2	- <http://clojure.org/>
3. Leiningen	- <http://github.com/technomancy/leiningen>

## Build

1. lein deps
2. lein javac
3. lein jar

Note that "lein uberjar" doesn't do the right thing yet.

## Test

1. lein test

## Run

Set PIG_CLASSPATH:

	export PIG_CLASSPATH=lib/*:mountain-pig-0.1.0.jar

Run:

	pig -x local sample.pig

Output should look like:

	<...>
	2010-10-06 13:23:09,455 [main] INFO  org.apache.pig.backend.local.executionengine.LocalPigLauncher - Success!!
	(LITTLE LAMB)
	(LITTLE LAMB)
	(LITTLE LAMB)
	(WAS WHITE AS SNOW)
	A: {key: chararray,val: chararray}
	B: {val: chararray}

Hooray, you just ran a Clojure "upcase" function within Pig!

## License

Copyright (C) 2010 Matt Kangas

Distributed under the Eclipse Public License, the same as Clojure.
