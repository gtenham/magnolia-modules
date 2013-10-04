package nl.magnomodulus.rest.config.server;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"nl.magnomodulus.rest.service"})
public class WebApplicationConfig {

}
