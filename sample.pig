-- Quick demo of custom UDF

REGISTER mountain-pig-0.1.0.jar;

A = LOAD 'sample.txt' as (key:chararray, val:chararray);
B = FOREACH A
	GENERATE mtnpig.string.Upper($1);
DUMP B;
describe A;
describe B;

C = LOAD 'sample2.txt' as (row:chararray);
D = FOREACH C
	GENERATE mtnpig.string.Split($0, '[?]');
DUMP D;
describe D;