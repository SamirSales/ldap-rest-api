package oi.github.samirsales.demoLDAP.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
@SuppressWarnings("unused")
public class SwaggerConfiguration implements WebMvcConfigurer {

    @Bean
    public Docket apiV1() {
        String version = "API v1.0";
        String apis = "oi.github.samirsales.demoLDAP.api";

        ApiInfo apiInfo = buildApiInfoBase().version(version).build();
        return buildApiBase().apiInfo(apiInfo).groupName(version)
                .tags(new Tag("Users", "Management of users", 0))
                .select()
                .apis(RequestHandlerSelectors.basePackage(apis))
                .paths(PathSelectors.any())
                .build();
    }

    private Docket buildApiBase() {
        List<ResponseMessage> defaultResponseMessageList = getDefaultResponseMessageList();
        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(Resource.class, InputStream.class)
                .select()
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.POST, defaultResponseMessageList)
                .globalResponseMessage(RequestMethod.PUT, defaultResponseMessageList)
                .globalResponseMessage(RequestMethod.GET, defaultResponseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, defaultResponseMessageList)
                .globalResponseMessage(RequestMethod.PATCH, defaultResponseMessageList);
    }

    private ApiInfoBuilder buildApiInfoBase() {
        return new ApiInfoBuilder()
                .title("Demo LDAP - Service API")
                .description("API for control of requirements")
                .termsOfServiceUrl("Terms of service")
                .license("License of API")
                .extensions(Collections.emptyList())
                .contact(new Contact("Samir Sales", "http://samirsales.github.io", "samir.sribeiro@gmail.com"));
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/doc/**", "/swagger-ui.html").setKeepQueryParams(true);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private List<ResponseMessage> getDefaultResponseMessageList() {
        return Arrays.asList(
                new ResponseMessageBuilder().code(401).message("You are not authorized to view the resource.").build(),
                new ResponseMessageBuilder().code(403).message("Accessing the resource you were trying to reach is forbidden.").build(),
                new ResponseMessageBuilder().code(500).message("There was an internal error.").build());
    }
}
