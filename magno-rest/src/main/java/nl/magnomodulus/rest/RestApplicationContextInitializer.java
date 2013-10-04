package nl.magnomodulus.rest;

import nl.magnomodulus.rest.config.server.WebApplicationConfig;

import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class RestApplicationContextInitializer extends AbstractContextLoaderInitializer {
	
	@Override
	protected WebApplicationContext createRootApplicationContext() {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(WebApplicationConfig.class);
		return rootContext;
	}

}
