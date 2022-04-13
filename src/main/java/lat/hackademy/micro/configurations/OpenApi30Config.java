package lat.hackademy.micro.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@SecurityScheme(
	    name = "bearerAuth",
	    type = SecuritySchemeType.HTTP,
	    bearerFormat = "JWT",
	    scheme = "bearer"
	)
public class OpenApi30Config {

	private final String moduleName;
	private final String apiVersion;

	public OpenApi30Config(@Value("${module-name}") String moduleName, @Value("${api-version}") String apiVersion) {
		this.moduleName = moduleName;
		this.apiVersion = apiVersion;
	}

	// Se agregan las definiciones para swagger
	@Bean
	public OpenAPI customOpenAPI(@Value("${application.title}") String appTitle,
			@Value("${application.description}") String appDesciption,
			@Value("${application.version}") String appVersion) {
		
	    final String securitySchemeName = "bearerAuth";
	    final String apiTitle = String.format("%s API", StringUtils.capitalize(moduleName));

		return new OpenAPI()
				.info(new Info().title(appTitle).version(appVersion).description(appDesciption)
				.termsOfService("http://swagger.io/terms/")
				.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
}
