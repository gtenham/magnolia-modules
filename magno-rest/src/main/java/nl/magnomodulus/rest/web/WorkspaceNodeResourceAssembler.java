package nl.magnomodulus.rest.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.PropertyType;
import javax.jcr.RepositoryException;
import javax.jcr.Value;

import nl.magnomodulus.rest.service.WorkspaceNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
			Map<String, Object> properties = new HashMap<String,Object>();
			for (Property prop : workspaceNode.getProperties()) {
				properties.put(prop.getName(), getPropertyValue(prop));
			}
			resource.setProperties(properties);
			
			resource.add(linkTo(RepositoryBrowserController.class).slash(workspaceNode.getWorkspace()).slash(workspaceNode.getPath()).withSelfRel());
			
			for (Node child : workspaceNode.getChildnodes()) {
				try {
					
					resource.add(linkTo(RepositoryBrowserController.class).slash(workspaceNode.getWorkspace()).slash(child.getPath()).withRel(child.getPrimaryNodeType().getName()));
				} catch (RepositoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			
		}
		return resource;
	}

	private Object getPropertyValue(Property prop) {
        try {
            int type = prop.getType();
            if (type == PropertyType.DATE) {
                return prop.getDate();
            } else if (type == PropertyType.BOOLEAN) {
                return prop.getBoolean();
            } else if (type == PropertyType.LONG) {
                return prop.getLong();
            } else if (type == PropertyType.DOUBLE) {
                return prop.getDouble();
            } else if (prop.isMultiple()) {

                Value[] values = prop.getValues();

                String[] valueStrings = new String[values.length];

                for (int j = 0; j < values.length; j++) {
                    try {
                        valueStrings[j] = values[j].getString();
                    } catch (RepositoryException e) {
                    	logger.warn(e.getMessage());
                    }
                }

                return valueStrings;
            } else if (type == PropertyType.BINARY) {
                // TODO: Investigate how to decode binary inputstream (eg. images) to an JSON output. Is this possible? Base64 encoded string maybe?
            	//       What about large binaries? Maybe support for Types like PropertyType.REFERENCE and PropertyType.WEAKREFERENCE are better 
            	//       suited for this purpose, so a link to the node can be returned!
            } else if (type == PropertyType.NAME) {
            	// Already provided as main node property
            } else if (type == PropertyType.PATH) {
            	// Already provided as main node property
            } else if (prop.isNode()) {
            	// Node value: Are we able to create a link to this?
            } 
            // Finally provide the String value
            return prop.getString();
            

        } catch (PathNotFoundException e) {
            // ignore, property doesn't exist
        } catch (RepositoryException e) {
        	logger.warn("Failed to retrieve property value with {}", new Object[] {e.getMessage() });
        }

        return null;
    }
}
