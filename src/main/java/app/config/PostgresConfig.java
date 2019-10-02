package app.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostgresConfig {

    @Bean
    public Flyway flyway(DataSource dataSource) {
        FluentConfiguration configuration = Flyway.configure();
        configuration.schemas("demo");
        configuration.cleanDisabled(true);
        configuration.dataSource(dataSource);
        Flyway flyway = new Flyway(configuration);
        flyway.migrate();
        return flyway;
    }

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/demo?currentSchema=demo");
        dataSource.setUsername("user");
        dataSource.setPassword("password");
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnReturn(true);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestWhileIdle(true);
        return dataSource;
    }
}
