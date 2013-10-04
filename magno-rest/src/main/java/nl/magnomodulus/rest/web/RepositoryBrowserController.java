package nl.magnomodulus.rest.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import nl.magnomodulus.rest.service.WorkspaceRepository;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerMapping;

@Controller
@RequestMapping(value = "/", produces = "application/json")
public class RepositoryBrowserController {

	private static final Logger logger = LoggerFactory.getLogger(RepositoryBrowserController.class);
	@Autowired
	private WorkspaceRepository workspaceRepository;
	@Autowired
	private WorkspaceResourceAssembler workspaceResourceAssembler;
	@Autowired
	private WorkspaceNodeResourceAssembler workspaceNodeResourceAssembler;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<WorkspaceResource>> getWorkspaces() {	
		logger.info("Show all Magnolia workspaces");
		List<WorkspaceResource> resources = workspaceResourceAssembler.toResources(workspaceRepository.findWorkspaces());
		return new ResponseEntity<List<WorkspaceResource>>(resources, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/{workspace}", method = RequestMethod.GET)
	public ResponseEntity<WorkspaceResource> getWorkspace(@PathVariable("workspace") String workspacename) {	
		logger.info("Show Magnolia workspace: " + workspacename);
		WorkspaceResource resource = workspaceResourceAssembler.toResource(workspaceRepository.findWorkspace(workspacename));
		return new ResponseEntity<WorkspaceResource>(resource, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/{workspace}/**", method = RequestMethod.GET)
	public ResponseEntity<WorkspaceNodeResource> getWorkspaceNodes(
			@PathVariable("workspace") String workspacename, HttpServletRequest request) {
		
		String relativeUrl = (String) request.getAttribute(
				HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		final String nodePath = StringUtils.removeStartIgnoreCase(relativeUrl, "/"+workspacename);
		
		logger.info("Magnolia workspace nodePath: " + nodePath);
		WorkspaceNodeResource resource = workspaceNodeResourceAssembler.toResource(workspaceRepository.findWorkspaceNode(workspacename, nodePath));
		return new ResponseEntity<WorkspaceNodeResource>(resource, HttpStatus.OK);
	}
	
	@ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handleException(RuntimeException e) {
        return new Error(e.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleException(ResourceNotFoundException e) {
        return new Error(e.getMessage());
    }

    public static class Error {
        private final String error;

        public Error(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }
}
