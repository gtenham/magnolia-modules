package nl.magnodom.rest.util;

import info.magnolia.jcr.predicate.AbstractPredicate;
import info.magnolia.jcr.util.NodeUtil;

import java.util.List;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import nl.magnodom.rest.RestModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PredicateUtil {

	private static final Logger logger = LoggerFactory.getLogger(PredicateUtil.class);
	
	/**
     * Node filter accepting all nodes of a type with namespace mgnl.
     */
    public static AbstractPredicate<Node> MAGNOLIA_FILTER = NodeUtil.MAGNOLIA_FILTER;
    
    /**
     * Node filter accepting all nodes of a type configured in module configuration.
     */
    public static AbstractPredicate<Node> CONFIGURED_NODETYPE_FILTER = new AbstractPredicate<Node>() {

        @Override
        public boolean evaluateTyped(Node node) {
            try {
                String nodeTypeName = node.getPrimaryNodeType().getName();
                String nodeWorkspace = node.getSession().getWorkspace().getName();
                
                RestModule restModule = RestModule.getInstance();
                List<String> nodetypes = restModule.getWorkspaceNodeFilter(nodeWorkspace);
                
                return nodetypes.contains(nodeTypeName);
            } catch (RepositoryException e) {
            	logger.error("Unable to read nodeType for node {}", NodeUtil.getNodePathIfPossible(node));
            }
            return false;
        }
    };
    
}
