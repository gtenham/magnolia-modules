package nl.magnomodulus.spring.web.context;

import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * Spring based ServletContext initializer class, creating root application context with
 * a Spring ContextLoaderListener.
 * Uses package based scan for Application Context @Configuration classes.
 * 
 * @author gtenham
 *
 */
public class ApplicationContextLoaderInitializer extends AbstractContextLoaderInitializer {

	private static final String CONTEXT_SCAN_PATH = "nl.magnomodulus.application.config";
	
	@Override
	protected WebApplicationContext createRootApplicationContext() {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.scan(CONTEXT_SCAN_PATH);
		return rootContext;
	}
}
