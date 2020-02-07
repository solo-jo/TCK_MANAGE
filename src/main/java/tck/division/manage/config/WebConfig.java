package tck.division.manage.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	private final String uploadImagesPath;

	public WebConfig(@Value("${file.upload-dir}") String uploadImagesPath) {
		this.uploadImagesPath = uploadImagesPath;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/static/img//**")
		.addResourceLocations("file:///" + uploadImagesPath + "/")
		.setCachePeriod(3600)
		.resourceChain(true)
		.addResolver(new PathResourceResolver());
		
		//registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		
	}
	
	
}
