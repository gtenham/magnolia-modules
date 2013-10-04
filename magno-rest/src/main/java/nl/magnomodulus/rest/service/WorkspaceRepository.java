package nl.magnomodulus.rest.service;

import java.util.List;

public interface WorkspaceRepository {

	List<Workspace> findWorkspaces();
	Workspace findWorkspace(String workspacename);
	WorkspaceNode findWorkspaceNode(String workspacename, String nodepath);
}
