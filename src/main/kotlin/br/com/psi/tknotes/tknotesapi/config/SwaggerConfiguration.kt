package br.com.psi.tknotes.tknotesapi.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import springfox.documentation.builders.ParameterBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.schema.ModelRef
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    @Value("\${spring.application.name}")
    var name: String? = null

    @Bean
    open fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build().groupName("API")
            .globalOperationParameters(listOf(
                    ParameterBuilder().name(HttpHeaders.AUTHORIZATION).description("Authorization token").required(true)
                            .modelRef(ModelRef("string")).parameterType("header").required(true).build()))
            .apiInfo(apiInfo())

    private fun apiInfo(): ApiInfo {
        return ApiInfo(
                name,
                "API criada para expor serviços rest associados ao domínio de gestão de serviços",
                "1.0",
                "Licenciado por TKnotes",
                Contact("Tknotes", "http://www.tknodes.com.br", "contato@tknodes.com.br"),
                "Apache Licence",
                "https://www.apache.org/licenses/LICENSE-2.0",
                ArrayList()
        )
    }
}

/*
private fun ApiConfig.getBasePackageName() = this::class.java.`package`.name

@Bean
fun mainApi(): Docket {
    return newDocket(apiConfig)
            .groupName("Main")
            .useDefaultResponseMessages(false)
            .also { addGlobalOperationParameters(it, apiConfig) }
            .also { addGlobalResponseMessages(it) }

            .select()
            .apis(RequestHandlerSelectors
                    .basePackage(apiConfig.getBasePackageName()))
            .build()
}*/
/*
@Bean
fun monitoringApi(): Docket {
    return newDocket(apiConfig)
            .groupName("Monitoring")
            .useDefaultResponseMessages(true)
            .also { addGlobalOperationParameters(it, apiConfig) }
            .also { addGlobalResponseMessages(it) }

            .select()
            .apis(Predicates.not(RequestHandlerSelectors.basePackage(apiConfig.getBasePackageName())))
            .build()
}

companion object {
    private fun newDocket(apiConfig: ApiConfig): Docket =
            Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(
                            ApiInfoBuilder()
                                    .title(apiConfig.title)
                                    .build()
                    )

    private fun addGlobalOperationParameters(docket: Docket, apiConfig: ApiConfig): Docket =
            docket.globalOperationParameters(
                    listOf(
                            globalHeaderParameter(apiConfig.headerTraceId, "traceId (UUID)")
                    )
            )

    private fun globalHeaderParameter(name: String, description: String): Parameter =
            ParameterBuilder()
                    .name(name)
                    .description(description)
                    .parameterType("header")
                    .modelRef(ModelRef("string"))
                    .build()

    private fun addGlobalResponseMessages(docket: Docket): Docket {
        val httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value()
        val httpStatusMessage = HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase

        for (method in Defaults().defaultResponseMessages().keys) {
            docket.globalResponseMessage(
                    method,
                    listOf(
                            ResponseMessageBuilder()
                                    .code(httpStatusCode)
                                    .message(httpStatusMessage)
                                    .build()
                    )
            )
        }

        return docket
    }*/
