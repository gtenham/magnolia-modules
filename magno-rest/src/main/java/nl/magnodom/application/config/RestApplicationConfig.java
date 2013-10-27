package nl.magnodom.application.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"nl.magnodom.rest.service"})
public class RestApplicationConfig {

}
