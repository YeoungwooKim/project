package project.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.classmate.TypeResolver;

import project.project.dto.CommentDto;
import project.project.dto.GameDto;
import project.project.dto.MailDto;
import project.project.dto.MessageDto;
import project.project.dto.RankingDto;
import project.project.dto.RoleDto;
import project.project.dto.UserDto;
import project.project.interceptor.LoggerInterceptor;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class MvcConfiguration implements WebMvcConfigurer { // extends WebMvcConfigurationSupport

	private final TypeResolver typeResolver;

	public MvcConfiguration(final TypeResolver typeResolver) {
		this.typeResolver = typeResolver;
	}

	@Bean
	public HiddenHttpMethodFilter httpMethodFilter() {
		HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
		return hiddenHttpMethodFilter;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		// super.addResourceHandlers(registry);
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggerInterceptor());
	}

	@Bean
	public Docket api() throws Exception {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("project"))
				.paths(PathSelectors.any()).build().additionalModels(typeResolver.resolve(CommentDto.class))
				.additionalModels(typeResolver.resolve(GameDto.class))
				.additionalModels(typeResolver.resolve(MailDto.class))
				.additionalModels(typeResolver.resolve(MessageDto.class))
				.additionalModels(typeResolver.resolve(RankingDto.class))
				.additionalModels(typeResolver.resolve(RoleDto.class))
				.additionalModels(typeResolver.resolve(UserDto.class));
	}
}