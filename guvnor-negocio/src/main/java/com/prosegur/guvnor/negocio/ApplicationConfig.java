package com.prosegur.guvnor.negocio;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.xml.xpath.Jaxp13XPathTemplate;
import org.springframework.xml.xpath.XPathOperations;

import com.prosegur.guvnor.negocio.utils.RestUtil;

@EnableScheduling
@Configuration
public class ApplicationConfig extends WebMvcConfigurerAdapter {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }    
    
    @Bean
    public XPathOperations xpathOperations() {
    	return new Jaxp13XPathTemplate();
    }
    
    @Bean
    //@ConfigurationProperties(prefix="guvnor.homologacion")
    @ConfigurationProperties(prefix="guvnor.local")
    public RestUtil restUtilHomologacion() {
        return new RestUtil();
    }
    
    @Bean
    @ConfigurationProperties(prefix="guvnor.produccion")
    public RestUtil restUtilProduccion() {
        return new RestUtil();
    }    
}
