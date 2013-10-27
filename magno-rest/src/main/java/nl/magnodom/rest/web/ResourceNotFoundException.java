package nl.magnodom.rest.web;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8930500927508318161L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}
