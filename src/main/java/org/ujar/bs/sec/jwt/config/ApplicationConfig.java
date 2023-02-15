package org.ujar.bs.sec.jwt.config;

import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories({"org.ujar.bs.sec.jwt.repository"})
@EnableJpaAuditing
@EnableTransactionManagement
@EnableConfigurationProperties(JwtTokenProperties.class)
class ApplicationConfig {

  @Bean
  SpringLiquibase liquibase(@Autowired DataSource dataSource) {
    final var liquibase = new SpringLiquibase();
    liquibase.setChangeLog("classpath:liquibase/master.xml");
    liquibase.setDataSource(dataSource);
    return liquibase;
  }

  @Bean
  BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
