package nl.magnomodulus.application.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"nl.magnomodulus.rest.service"})
public class RestApplicationConfig {

}
