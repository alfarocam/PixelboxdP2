package com.tienda;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration

public class ProjectConfig implements WebMvcConfigurer {

    /* Los siguiente métodos son para implementar el tema de seguridad dentro del proyecto */
    @Override

    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("index");

        registry.addViewController("/ejemplo2").setViewName("ejemplo2");
        registry.addViewController("/multimedia").setViewName("multimedia");
        registry.addViewController("/iframes").setViewName("iframes");

    }

    /* El siguiente método se utilizar para publicar en la nube, independientemente  */
    @Bean

    public SpringResourceTemplateResolver templateResolver_0() {

        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();

        resolver.setPrefix("classpath:/templates");

        resolver.setSuffix(".html");

        resolver.setTemplateMode(TemplateMode.HTML);

        resolver.setOrder(0);

        resolver.setCheckExistence(true);

        return resolver;

    }

    /*este metodo usa la config regional predeterminada del sistema y guarda la config regional y la zona horaria
en atributos de sesion personalizados*/
    @Bean
    public LocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");
        slr.setTimeZoneAttributeName("session.current.timezone");
        return slr;
    }

    /*Este método crea un interceptor que permite cambiar el idioma de la página usando un parámetro en la URL.*/
    @Bean
    public LocaleChangeInterceptor localChangeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
    //este metodo activa el cambio de idioma en la aplicacion
    @Override
    public void addInterceptors(InterceptorRegistry registro){
        registro.addInterceptor(localChangeInterceptor ());
    }
}
