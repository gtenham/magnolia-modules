package nl.magnomodulus.rest.service;

import java.util.List;

import javax.jcr.Node;
import javax.jcr.Property;

public class WorkspaceNode {
	
	private String workspace;
	private String name;
	private String uuid;
	private String path;
	private List<Property> properties;
	private List<Node> childnodes;
	
	public String getWorkspace() {
		return workspace;
	}
	public void setWorkspace(String workspace) {
		this.workspace = workspace;
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
	public List<Property> getProperties() {
		return properties;
	}
	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
	public List<Node> getChildnodes() {
		return childnodes;
	}
	public void setChildnodes(List<Node> childnodes) {
		this.childnodes = childnodes;
	}
}
