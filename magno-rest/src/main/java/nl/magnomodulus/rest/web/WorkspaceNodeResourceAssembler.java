package nl.magnomodulus.rest.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import nl.magnomodulus.rest.domain.WorkspaceNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceNodeResourceAssembler extends ResourceAssemblerSupport<WorkspaceNode, WorkspaceNodeResource> {

	private static final Logger logger = LoggerFactory.getLogger(WorkspaceNodeResourceAssembler.class);
	
	public WorkspaceNodeResourceAssembler() {
		super(RepositoryBrowserController.class, WorkspaceNodeResource.class);
	}

	public WorkspaceNodeResource toResource(WorkspaceNode workspaceNode) {
		
		WorkspaceNodeResource resource = instantiateResource(workspaceNode);
		try {
			resource.setWorkspace(workspaceNode.getWorkspace());
			resource.setUuid(workspaceNode.getUuid());
			resource.setName(workspaceNode.getName());
			resource.setPath(workspaceNode.getPath());
			
			Link selfLink = linkTo(RepositoryBrowserController.class).slash(workspaceNode.getWorkspace()).slash(workspaceNode.getPath()).withSelfRel();
			//properties.put("uri",selfLink);
			resource.setProperties(workspaceNode.getProperties());
			
			resource.add(selfLink);
			
			for (Node child : workspaceNode.getChildnodes()) {
				try {
					
					resource.add(linkTo(RepositoryBrowserController.class).slash(workspaceNode.getWorkspace()).slash(child.getPath()).withRel(child.getPrimaryNodeType().getName()));
				} catch (RepositoryException e) {
					logger.warn("Repository exception",e);
				}
			}
		} catch (Exception e) {
			
		}
		return resource;
	}

}
