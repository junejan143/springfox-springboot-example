package com.gemini;

import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@SpringBootApplication
@EnableSwagger2
public class NecServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(NecServerApplication.class, args);
    }


    @Bean
    public Docket docketApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.any()) //1
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfo("基础框架", "这是一个项目的基础框架结构，构建新项目可以在这个基础上搭建","1.0","apiDocs",new Contact("章源辰","","13338073698@163.com"),"License","/License"))
                .pathMapping("/") //2
                .directModelSubstitute(LocalDate.class, //3
                        String.class)
                .genericModelSubstitutes(ResponseEntity.class) //4
                .useDefaultResponseMessages(false) //6
                .globalResponseMessage(RequestMethod.GET, //7
                        newArrayList(new ResponseMessageBuilder()
                                .code(500)
                                .message("500 message")
                                .responseModel(new ModelRef("Error"))
                                .build()))
                //.securitySchemes(newArrayList(new BasicAuth("name")))
                //.securityContexts(newArrayList(securityContext()))
                //.globalOperationParameters(
                //        newArrayList(new ParameterBuilder()
                //                .name("someGlobalParameter")
                //                .description("Description of someGlobalParameter")
                //                .modelRef(new ModelRef("string"))
                //                .parameterType("User")
                //                .required(true)
                //                .build()
                //        )
                //)
                //.securitySchemes(newArrayList(apiKey())) //8
                //.securityContexts(newArrayList(securityContext())) //9
                ;
        //设置请求的host
        //docket.host("http://www.520zhang.com");
        return docket;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/anyPath.*"))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(
                new SecurityReference("mykey", authorizationScopes));
    }
    //@Autowired
    //private TypeResolver typeResolver;
    //
    //private SecurityContext securityContext() {
    //    return SecurityContext.builder()
    //            .securityReferences(defaultAuth())
    //            .forPaths(PathSelectors.regex("/anyPath.*"))
    //            .build();
    //}
    //
    //List<SecurityReference> defaultAuth() {
    //    AuthorizationScope authorizationScope
    //            = new AuthorizationScope("global", "accessEverything");
    //    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    //    authorizationScopes[0] = authorizationScope;
    //    return newArrayList(
    //            new SecurityReference("mykey", authorizationScopes));
    //}
    //
    //private ApiKey apiKey() {
    //    return new ApiKey("mykey", "api_key", "header");
    //}
    //
    //@Bean
    //SecurityConfiguration security() {
    //    return new SecurityConfiguration("test-app-client-id","test-app-realm","test-app","apiKey");
    //}
    //
    //@Bean
    //UiConfiguration uiConfig() {
    //    return new UiConfiguration(
    //            "validatorUrl");
    //}
}
