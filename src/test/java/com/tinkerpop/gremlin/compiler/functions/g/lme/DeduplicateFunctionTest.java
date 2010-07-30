package com.tinkerpop.gremlin.compiler.functions.g.lme;

import com.tinkerpop.gremlin.BaseTest;
import com.tinkerpop.gremlin.compiler.Atom;
import com.tinkerpop.gremlin.compiler.context.GremlinScriptContext;
import com.tinkerpop.gremlin.compiler.functions.Function;

import java.util.Arrays;
import java.util.List;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class DeduplicateFunctionTest extends BaseTest {

    public void testDeduplicateList() {
        GremlinScriptContext context = new GremlinScriptContext();
        
        Function<Iterable> function = new DeduplicateFunction();
        this.stopWatch();
        Atom<Iterable> atom = function.compute(createUnaryArgs(Arrays.asList(1.0d, 2.0d, 3.0d, 4.0d)), context);
        printPerformance(function.getFunctionName() + " function", 4, "arguments", this.stopWatch());
        List<Atom> list = asList(atom.getValue());
        assertEquals(list.size(), 4);

        assertTrue(list.contains(1.0d));
        assertTrue(list.contains(2.0d));
        assertTrue(list.contains(3.0d));
        assertTrue(list.contains(4.0d));

        function = new DeduplicateFunction();
        this.stopWatch();
        atom = function.compute(createUnaryArgs(Arrays.asList(1.0, 2.0, 2.0, 2.0)), context);
        printPerformance(function.getFunctionName() + " function", 4, "arguments", this.stopWatch());
        list = asList(atom.getValue());
        assertEquals(list.size(), 2);
        assertTrue(list.contains(1.0d));
        assertTrue(list.contains(2.0d));

    }
}
