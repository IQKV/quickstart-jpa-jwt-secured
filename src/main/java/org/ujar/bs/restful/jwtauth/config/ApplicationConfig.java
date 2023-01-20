package org.ujar.bs.rst.jwtauth.config;

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
import org.ujar.boot.starter.logbook.LogbookJsonBodyFilter;
import org.ujar.boot.starter.logbook.LogbookResponseOnStatus;

@Configuration
@LogbookResponseOnStatus
@LogbookJsonBodyFilter
@EnableJpaRepositories({"org.ujar.bs.rst.jwtauth.repository"})
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
