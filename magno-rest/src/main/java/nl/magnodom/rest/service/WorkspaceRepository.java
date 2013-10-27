package nl.magnodom.rest.service;

import java.util.List;

import nl.magnodom.rest.domain.Workspace;
import nl.magnodom.rest.domain.WorkspaceNode;

public interface WorkspaceRepository {

	List<Workspace> findWorkspaces();
	Workspace findWorkspace(String workspacename);
	WorkspaceNode findWorkspaceNode(String workspacename, String nodepath);
}
