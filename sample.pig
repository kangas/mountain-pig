-- Quick demo of custom UDF

REGISTER mountain-pig-0.1.0.jar;

define UPPER mtnpig.udf.UPPER();

A = LOAD 'sample.txt';
B = FOREACH A
	GENERATE mtnpig.udf.UPPER($1);
DUMP B;