package io.github.budincsevity.OpenSubtitlesAPI

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/api/.*"))
                .build()
                .pathMapping("/")

    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("OpenSubtitles REST Search Service")
                .description("REST Search wrapper for OpenSubtitles XMLRPC API")
                .contact(Contact("Norbert Budincsevity", "http://github.com/Budincsevity", "nbudincsevity@gmail.com"))
                .version("1.0")
                .build()
    }
}
