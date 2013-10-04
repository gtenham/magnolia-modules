package nl.magnomodulus.rest.service;

import info.magnolia.context.MgnlContext;
import info.magnolia.jcr.util.NodeUtil;
import info.magnolia.jcr.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.Session;

import nl.magnomodulus.rest.util.ConfigMapper;
import nl.magnomodulus.rest.util.PredicateUtil;
import nl.magnomodulus.rest.web.ResourceNotFoundException;
import nl.magnomodulus.rest.RestModule;
import nl.magnomodulus.rest.config.MagnoliaConfig;
import nl.magnomodulus.rest.config.WorkspaceConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceRepositoryImpl implements WorkspaceRepository {

	private static final Logger logger = LoggerFactory.getLogger(WorkspaceRepositoryImpl.class);
	
	private final ConfigMapper<Workspace> mapper = new WorkspaceMapper();
	
	private RestModule restModule;
	private List<Workspace> workspaces;
	
	public List<Workspace> findWorkspaces() {
		restModule = RestModule.getInstance();
		workspaces = new ArrayList<Workspace>(0);
		for (Entry<String, WorkspaceConfig> entry : restModule.getWorkspaces().entrySet()) {
			Workspace workspace = mapper.map(entry.getValue());
			workspace.setName(entry.getKey());
			workspace.setChildnodes(new ArrayList<Node>(0));
			workspaces.add(workspace);
		}
		return workspaces;
	}

	public Workspace findWorkspace(String workspacename) {
		List<Node> childNodes = new ArrayList<Node>(0);
		
		restModule = RestModule.getInstance();
		if (!restModule.getWorkspaces().containsKey(workspacename)) {
			restModule.setCurrentWorkspace(null);
			throw new ResourceNotFoundException("Unable to find workspace with name=" + workspacename);
		}
		restModule.setCurrentWorkspace(workspacename);
		
		Workspace workspace = mapper.map(restModule.getWorkspaces().get(workspacename));
		
		try {
			Session session = MgnlContext.getJCRSession(workspacename);
			//Session session = LifeTimeJCRSessionUtil.getSession(workspacename);
			
			Node root = SessionUtil.getNode(session, "/");
			// Provide all allowed nodes to be returned!
			childNodes = NodeUtil.asList(NodeUtil.getNodes(root, PredicateUtil.CONFIGURED_NODETYPE_FILTER));
			
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		workspace.setChildnodes(childNodes);
		workspace.setName(workspacename);
		return workspace;
	}

	public WorkspaceNode findWorkspaceNode(String workspacename, String nodepath) {
		restModule = RestModule.getInstance();
		if (!restModule.getWorkspaces().containsKey(workspacename)) {
			restModule.setCurrentWorkspace(null);
			throw new ResourceNotFoundException("Unable to find workspace with name=" + workspacename);
		}
		restModule.setCurrentWorkspace(workspacename);
		
		List<Node> childNodes = new ArrayList<Node>(0);
		WorkspaceNode workspaceNode = new WorkspaceNode();
		
		try {
			Session session = MgnlContext.getJCRSession(workspacename);
			//Session session = LifeTimeJCRSessionUtil.getSession(workspacename);
			
			Node node = SessionUtil.getNode(session, nodepath);
			// Provide all allowed nodes to be returned!
			childNodes = NodeUtil.asList(NodeUtil.getNodes(node, PredicateUtil.CONFIGURED_NODETYPE_FILTER));
			
			workspaceNode.setWorkspace(workspacename);
			workspaceNode.setUuid(node.getIdentifier());
			workspaceNode.setName(node.getName());
			workspaceNode.setPath(node.getPath());
			
			// Provide all allowed properties to be returned!
			PropertyIterator it = node.getProperties(restModule.getWorkspacePropertyFilter());
			List<Property> properties = new ArrayList<Property>(0);
			while (it.hasNext()) {
				properties.add(it.nextProperty());
			}
			
			workspaceNode.setProperties(properties);
			workspaceNode.setChildnodes(childNodes);
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		return workspaceNode;
	}

	private static class WorkspaceMapper implements ConfigMapper<Workspace> {
		
		public Workspace map(MagnoliaConfig config) {
			Workspace ws = new Workspace();
			
			ws.setLabel(config.getLabel());
			ws.setDescription(config.getDescription());
			
			return ws;
		}
	}
}
