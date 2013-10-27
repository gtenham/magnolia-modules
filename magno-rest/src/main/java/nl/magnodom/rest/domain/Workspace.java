package nl.magnodom.rest.domain;

import java.util.List;

import javax.jcr.Node;

public class Workspace {

	private String name;
	private String label;
	private String description;
	private List<Node> childnodes;
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Node> getChildnodes() {
		return childnodes;
	}
	public void setChildnodes(List<Node> childnodes) {
		this.childnodes = childnodes;
	}
	
}
