package com.desafio.rh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableSwagger2
@Import({BeanValidatorPluginsConfiguration.class})
public class SwaggerConfig {


    private final ResponseMessage m201 = customMessage1();
    //    private final ResponseMessage m204put = simpleMessage(204, "Atualização ocorreu com sucesso");
//    private final ResponseMessage m204del = simpleMessage(204, "Deleção ocorreu com sucesso");
    private final ResponseMessage m403 = simpleMessage(403, "Não autorizado.");
    private final ResponseMessage m404 = simpleMessage(404, "Recurso buscado não encontrado");
    private final ResponseMessage m422 = simpleMessage(422, "Erro de validação");
    private final ResponseMessage m500 = simpleMessage(500, "Erro inesperado");

    @Bean
    public Docket api() {
        String RESOURCES_PACK = "com.desafio.rh.resources";
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, Arrays.asList(m403, m404, m500))
                .globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m403, m422, m500))
                .globalResponseMessage(RequestMethod.PUT, Arrays.asList(/*m204put,*/ m403, m404, m422, m500))
                .globalResponseMessage(RequestMethod.DELETE, Arrays.asList(/*m204del,*/ m403, m404, m500))
                .select()
                .apis(RequestHandlerSelectors.basePackage(RESOURCES_PACK))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .pathMapping("/")
                .securitySchemes(Collections.singletonList(new ApiKey("JWT", "Authorization", "header")))
                .securityContexts(Collections.singletonList(
                        SecurityContext.builder()
                                .securityReferences(
                                        Collections.singletonList(SecurityReference.builder()
                                                .reference("JWT")
                                                .scopes(new AuthorizationScope[0])
                                                .build()
                                        )
                                )
                                .build())
                );
    }


    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Recursos Humanos - API",
                "API Utilizada como projeto desafio do processo de seleção Ibyte",
                "Versão 1.0",
                "http://www.apache.org/licenses/LICENSE-2.0.txt",
                new Contact("André Vieira da Silva", "https://github.com/engcomAndre", "sgavsnake@gmail.com"),
                "Uso aberto ,não comercial",
                "http://www.apache.org/licenses/LICENSE-2.0.txt",
                Collections.emptyList() // Vendor Extensions
        );
    }

    private ResponseMessage simpleMessage(int code, String msg) {
        return new ResponseMessageBuilder().code(code).message(msg).build();
    }

    private ResponseMessage customMessage1() {
        Map<String, Header> map = new HashMap<>();
        map.put("location", new Header("location", "URI do novo recurso", new ModelRef("string")));
        return new ResponseMessageBuilder()
                .code(201)
                .message("Recurso criado com sucesso.")
                .headersWithDescription(map)
                .build();
    }


}