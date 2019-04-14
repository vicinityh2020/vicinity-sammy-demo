package gr.optionsnet.vicinity.demo.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;


@Configuration
@EnableWebMvc
@EnableAutoConfiguration
@ComponentScan
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("webapp/lib/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("webapp/static/**").addResourceLocations("classpath:/webapp/static/");
        registry.addResourceHandler("mail/**").addResourceLocations("classpath:/webapp/mail/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }

    @Bean(name = "messageSource")
    public MessageSource messageSource() {
        Locale.setDefault(Locale.forLanguageTag("EN"));
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/webapp/i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @GetMapping({"favicon.ico"})
    public String favicon() {
        return "forward:/webapp/static/fav/favicon.ico";
    }

}
