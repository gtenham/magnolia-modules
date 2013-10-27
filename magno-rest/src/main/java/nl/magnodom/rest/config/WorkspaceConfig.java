package nl.magnodom.rest.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class WorkspaceConfig implements MagnoliaConfig {

	private String label = StringUtils.EMPTY;
	private String description = StringUtils.EMPTY;
	private Map<String, String> nodetypeFilter = new HashMap<String, String>();
	private Map<String, String> propertyFilter = new HashMap<String, String>();
	
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
	public Map<String, String> getNodetypeFilter() {
		return nodetypeFilter;
	}
	public void setNodetypeFilter(Map<String, String> nodetypeFilter) {
		this.nodetypeFilter = nodetypeFilter;
	}
	public Map<String, String> getPropertyFilter() {
		return propertyFilter;
	}
	public void setPropertyFilter(Map<String, String> propertyFilter) {
		this.propertyFilter = propertyFilter;
	}
}
