package com.tinkerpop.gremlin.compiler.functions.sail;

import com.tinkerpop.blueprints.pgm.Graph;
import com.tinkerpop.blueprints.pgm.impls.sail.SailGraph;
import com.tinkerpop.gremlin.BaseTest;
import com.tinkerpop.gremlin.compiler.Atom;
import com.tinkerpop.gremlin.compiler.Tokens;
import com.tinkerpop.gremlin.compiler.context.GremlinScriptContext;
import com.tinkerpop.gremlin.compiler.functions.Function;
import org.openrdf.sail.memory.MemoryStore;

import java.util.Map;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class GetNamespacesFunctionTest extends BaseTest {

    public void testGetNamespaces() {
        SailGraph graph = new SailGraph(new MemoryStore());
        GremlinScriptContext context = new GremlinScriptContext();
        context.getVariableLibrary().declare(Tokens.GRAPH_VARIABLE, new Atom<Graph>(graph));
        
        Function<Map<String, String>> function = new GetNamespacesFunction();
        this.stopWatch();
        Atom<Map<String, String>> atom = function.compute(createUnaryArgs(graph), context);
        printPerformance(function.getFunctionName() + " function", 1, "evaluation", this.stopWatch());
        assertTrue(atom.getValue().containsKey("rdf"));
        assertTrue(atom.getValue().containsKey("rdfs"));
        assertTrue(atom.getValue().containsKey("owl"));
        assertTrue(atom.getValue().containsKey("foaf"));


        this.stopWatch();
        atom = function.compute(createUnaryArgs(), context);
        printPerformance(function.getFunctionName() + " function", 1, "evaluation", this.stopWatch());
        assertTrue(atom.getValue().containsKey("rdf"));
        assertTrue(atom.getValue().containsKey("rdfs"));
        assertTrue(atom.getValue().containsKey("owl"));
        assertTrue(atom.getValue().containsKey("foaf"));

        graph.shutdown();
    }
}
