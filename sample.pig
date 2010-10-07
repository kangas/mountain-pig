-- Quick demo of custom UDF

REGISTER mountain-pig-0.1.0.jar;

define UPPER mtnpig.udf.UPPER();

A = LOAD 'sample.txt' as (key:chararray, val:chararray);
B = FOREACH A
	GENERATE mtnpig.udf.UPPER($1);
DUMP B;

describe A;
describe B;