# mountain-pig

Extensions for Apache Pig, written in Clojure.

The name is from my sister's dog, Hank, a little porker who'll climb anything.
Here's a picture of him in action:
[http://www.flickr.com/photos/mattkangas/2956057564](http://www.flickr.com/photos/mattkangas/2956057564)

Yes, he's ridiculous. So is the idea of embedding Clojure within Pig. 
But here it is!

## Requirements

1. Pig 0.6	(http://hadoop.apache.org/pig/)
2. Leiningen	(http://github.com/technomancy/leiningen)

## Build

1. lein deps
2. lein javac
3. lein jar

Note that "lein uberjar" doesn't do the right thing yet.

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
