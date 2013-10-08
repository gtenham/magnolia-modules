package nl.magnomodulus.rest.service;

import java.util.List;

import nl.magnomodulus.rest.domain.Workspace;
import nl.magnomodulus.rest.domain.WorkspaceNode;

public interface WorkspaceRepository {

	List<Workspace> findWorkspaces();
	Workspace findWorkspace(String workspacename);
	WorkspaceNode findWorkspaceNode(String workspacename, String nodepath);
}
