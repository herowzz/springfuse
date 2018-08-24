package com.github.herowzz.springfuse.example.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ModelAttribute;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ApiDocConfig {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.github.herowzz.springfuse.example.controller"))
				.paths(PathSelectors.any())
				.build().ignoredParameterTypes(ModelAttribute.class, Pageable.class)
				.securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				// 页面标题
				.title("SpringFuse API Docs")
				// 创建人
				.contact(new Contact("herowzz", "https://github.com/herowzz/springfuse", "wzzwxh@gmail.com"))
				// 版本号
				.version("1.0")
				// 描述
				.description("API接口描述").build();
	}
	
	private List<ApiKey> securitySchemes() {
		return Arrays.asList(new ApiKey("token", "token", "header"));
	}
	
	private List<SecurityContext> securityContexts() {
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[] {new AuthorizationScope("global", "accessEverything")};
		List<SecurityReference> defaultAuths = Arrays.asList(new SecurityReference("token", authorizationScopes));
        return Arrays.asList(SecurityContext.builder().securityReferences(defaultAuths).build());
    }
	
}
