package com.cesi.springbases.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
* Pour ajouter Bootstrap à notre application, il nous faut créer une classe branchée à Spring pour la configuration.
*
* Pour ce faire, on va décorer une classe des annotations @Configuration de sorte à ce que Spring MVC puisse en lire le code lors du lancement
* de l'appli. Via ce code, l'application va pouvoir brancher le dossier contenant les webjars à un dossier disponible au sein du serveur web Tomcat
* "/webjars/" => "/webjars/"
* */
@Configuration
@EnableWebMvc
public class ConfigurationMVC implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
    }
}
