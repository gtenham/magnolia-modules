package nl.magnomodulus.rest.web;

import java.util.Map;

import org.springframework.hateoas.ResourceSupport;

public class WorkspaceNodeResource extends ResourceSupport {

	private String workspace;
	private String name;
	private String uuid;
	private String path;
	private Map<String, Object> properties;
	
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

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	
	
}
