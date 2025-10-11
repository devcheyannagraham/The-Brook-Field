package demo.bfims.Config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FrontendSPAConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        System.out.println("\n Registering view controller for Frontend SPAConfig");
        registry.addViewController("/{path:[^\\.}*}").setViewName("forward:/");
    }
}
