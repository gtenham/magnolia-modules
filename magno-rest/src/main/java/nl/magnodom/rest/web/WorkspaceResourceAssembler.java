package nl.magnodom.rest.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import nl.magnodom.rest.domain.Workspace;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceResourceAssembler extends ResourceAssemblerSupport<Workspace, WorkspaceResource> {

	public WorkspaceResourceAssembler() {
		super(RepositoryBrowserController.class, WorkspaceResource.class);
	}

	public WorkspaceResource toResource(Workspace workspace) {
		WorkspaceResource resource = instantiateResource(workspace);
		resource.setName(workspace.getName());
		resource.setLabel(workspace.getLabel());
		resource.setDescription(workspace.getDescription());
		
		resource.add(linkTo(RepositoryBrowserController.class).slash(workspace.getName()).withSelfRel());
		for (Node child : workspace.getChildnodes()) {
			try {
				resource.add(linkTo(RepositoryBrowserController.class).slash(workspace.getName()).slash(child.getPath()).withRel(child.getPrimaryNodeType().getName()));
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resource;
	}

}
