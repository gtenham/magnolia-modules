package nl.magnomodulus.rest.web;

import org.springframework.hateoas.ResourceSupport;

public class WorkspaceResource extends ResourceSupport {

	private String name;
	private String label;
	private String description;
	
	public WorkspaceResource() {
	}

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
	
}
