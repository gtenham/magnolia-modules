package nl.magnodom.rest.web;

import nl.magnodom.rest.domain.PropertyMap;

import org.springframework.hateoas.ResourceSupport;

public class WorkspaceNodeResource extends ResourceSupport {

	private String workspace;
	private String name;
	private String uuid;
	private String path;
	private PropertyMap properties;
	
	public String getWorkspace() {
		return workspace;
	}

	public void setWorkspace(String workspace) {
		this.workspace = workspace;
	}

	public WorkspaceNodeResource() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public PropertyMap getProperties() {
		return properties;
	}

	public void setProperties(PropertyMap properties) {
		this.properties = properties;
	}

	
	
}
