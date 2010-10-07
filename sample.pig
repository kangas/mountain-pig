-- Quick demo of custom UDF

REGISTER mountain-pig-0.1.0.jar;

A = LOAD 'sample.txt' as (key:chararray, val:chararray);
B = FOREACH A
	GENERATE mtnpig.UPPER($1);
DUMP B;

describe A;
describe B;
