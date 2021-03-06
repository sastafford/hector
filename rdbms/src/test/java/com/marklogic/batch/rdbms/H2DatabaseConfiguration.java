package com.marklogic.batch.rdbms;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class H2DatabaseConfiguration {

    @Qualifier("customerDatabase")
    @Bean
    public DataSource dataSource() {
        // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase dataSource = builder
            .setType(EmbeddedDatabaseType.H2)
            .addScript("db/sampledata.sql")
            .build();
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(
            @Qualifier("customerDatabase")DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
