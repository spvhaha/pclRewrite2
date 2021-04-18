package ca.ontario.health.hns.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class AppConfig {
    @Bean
    public Docket bplappApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("Batch Processing Letter").apiInfo(apiDetails()).tags(new Tag("Author", "Shital Kansara")).select()
                .apis(RequestHandlerSelectors.basePackage("ca.ontario.health.hns")).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiDetails() {
        return new ApiInfoBuilder().title("Swagger REST").description("Swagger api docs")
                .version("1.0").build();
                     }

}
