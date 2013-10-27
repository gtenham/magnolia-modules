package nl.magnodom.rest.config.server;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"nl.magnodom.rest.web"})
public class WebDispatcherConfig extends WebMvcConfigurerAdapter {

}
