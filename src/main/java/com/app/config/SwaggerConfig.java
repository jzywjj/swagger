package com.app.config;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Autowired
	private Environment environment;
	
	@Bean
	public Docket docket() {
		Profiles isEnabled = Profiles.of("dev","test");
		boolean isAccept = environment.acceptsProfiles(isEnabled);
		
		Parameter tokenParameter = new ParameterBuilder().name("token")
		.description("用户令牌")
		.parameterType("header")
		.modelRef(new ModelRef("String"))
		.required(false).build();
		
		return new Docket(DocumentationType.SWAGGER_2)
				.globalOperationParameters(Arrays.asList(new Parameter[] {tokenParameter}))
				.groupName("userAdmin")
				.ignoredParameterTypes(HttpServlet.class,HttpServletRequest.class,HttpSession.class)
				.enable(isAccept)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.app.controller"))
				.paths(PathSelectors.ant("/api/**"))
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		Contact contact = new Contact("zhiyong.jin@aliyun.com", null, "zhiyong.jin@aliyun.com");
		return new ApiInfo("wechat api", // 标题
				"博物馆Api doc", // 描述
				"v1.0", // 版本
				"http://terms.service.url/", contact, // 联系人信息
				"Apach 2.0 许可", // 许可
				"许可链接", // 许可连接
				new ArrayList<>()); // 扩展
	}
}
