package nl.magnomodulus.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.magnomodulus.rest.config.WorkspaceConfig;

import org.springframework.util.StringUtils;

/**
 * This class is optional and represents the configuration for the magno-rest module.
 * By exposing simple getter/setter/adder methods, this bean can be configured via content2bean
 * using the properties and node from <tt>config:/modules/magno-rest</tt>.
 * If you don't need this, simply remove the reference to this class in the module descriptor xml.
 */
public class RestModule {
	
	private static RestModule instance;
	
	private Map<String, WorkspaceConfig> workspaces = new HashMap<String, WorkspaceConfig>();
	
	public RestModule() {
		instance = this;
	}
	
	public static RestModule getInstance() {
		return instance;
	}

	public Map<String, WorkspaceConfig> getWorkspaces() {
		return workspaces;
	}

	public void setWorkspaces(Map<String, WorkspaceConfig> workspaces) {
		this.workspaces = workspaces;
	}

	public String[] getWorkspacePropertyFilter(String workspacename) {
		String[] defaultFilter = new String[] {""};
		if (!StringUtils.isEmpty(workspacename)) {
			Collection<String> values = workspaces.get(workspacename).getPropertyFilter().values();
			if (!values.isEmpty()) {
				return values.toArray(defaultFilter);
			}
		}
		return new String[] {""};
	}

	public List<String> getWorkspaceNodeFilter(String workspacename) {
		List<String> filter = new ArrayList<String>(0);
		if (!StringUtils.isEmpty(workspacename)) {
			Collection<String> values = workspaces.get(workspacename).getNodetypeFilter().values();
			if (!values.isEmpty()) {
				return new ArrayList<String>(values);
			}
		}
		return filter;
	}
	
}
