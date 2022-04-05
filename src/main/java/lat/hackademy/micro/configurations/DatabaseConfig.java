package lat.hackademy.micro.configurations;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@Profile("prod")
public class DatabaseConfig {

	@Value("${spring.datasource.url}")
	private String dbUri;

	private static final Logger log = LoggerFactory.getLogger(DatabaseConfig.class);

	  @Bean
	  public DataSource dataSource() {
	      HikariConfig config = new HikariConfig();
	      String[] dbUrl1 = dbUri.split("@");
	      String dbUrl = "jdbc:postgresql://"+dbUrl1[1];
	      config.setJdbcUrl(dbUrl);
	      config.setUsername(dbUrl1[0].split(":")[1].replace("//", ""));
	      config.setPassword(dbUrl1[0].split(":")[2]);
	      return new HikariDataSource(config);
	  }

}