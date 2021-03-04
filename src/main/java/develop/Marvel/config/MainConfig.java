package develop.Marvel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:/default.properties")
@PropertySource(value = "classpath:/local.properties", ignoreResourceNotFound = true)
public class MainConfig {}