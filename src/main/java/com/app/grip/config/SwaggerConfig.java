package com.app.grip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
//        ParameterBuilder ParameterBuilder = new ParameterBuilder();
//        ParameterBuilder.name("Jwt") //헤더 이름
//                .description("Jwt") //설명
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")
//                .required(false)
//                .build();
//        List<Parameter> parameterList=new ArrayList<>();
//        parameterList.add(ParameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                //.globalOperationParameters(parameterList)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.app.grip"))
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }
}
