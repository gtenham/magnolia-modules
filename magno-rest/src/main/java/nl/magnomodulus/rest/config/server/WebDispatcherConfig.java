package nl.magnomodulus.rest.config.server;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"nl.magnomodulus.rest.web"})
public class WebDispatcherConfig extends WebMvcConfigurerAdapter {

}
