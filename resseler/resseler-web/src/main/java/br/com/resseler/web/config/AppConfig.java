package br.com.resseler.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "br.com.resseler.web.controller")
public class AppConfig {

}
