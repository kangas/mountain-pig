package mtnpig.stub;
import org.apache.pig.EvalFunc;

/**
 * Stub to specify a concrete type of EvalFunc.
 * Clojure's "gen-class" can't extend parameterized classes yet.
 */
public abstract class StringEvalFunc extends EvalFunc <String> {
}
