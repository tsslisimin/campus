package cn.csjava.campus.common.configures;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author：hcqi .
 * des:
 * email:hechuanqi.top@gmail.com
 * date: 2017/10/17
 */
@Configuration
@EnableSwagger2
public class Swagger2Configure {
    @Value("${swagger2.title}")
    private String title;
    @Value("${swagger2.des}")
    private String des;
    @Value("${swagger2.url}")
    private String serverUrl;
    @Value("${app.version}")
    private String version;

    @Value("${swagger2.enable}")
    private boolean enable;

    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(enable)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(des)
                .termsOfServiceUrl(serverUrl)
                .version(version)
                .build();
    }


}
