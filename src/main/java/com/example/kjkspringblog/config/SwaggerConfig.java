package com.example.kjkspringblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(AuthenticationPrincipal.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.kjkspringblog.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
//테스트
    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("스웨거 설정하기")
                .description("공부공부.")
                .version("1.0")
                .build();


        // 완료가 되었으면 오른쪽 URL 로 접속 => http://localhost:8080/swagger-ui.html
    }
}
