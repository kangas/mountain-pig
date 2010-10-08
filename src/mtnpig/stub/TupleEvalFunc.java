package mtnpig.stub;
import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

/**
 * Stub to specify a concrete type of EvalFunc.
 * Clojure's "gen-class" can't extend parameterized classes yet.
 */
public abstract class TupleEvalFunc extends EvalFunc <Tuple> {
}
