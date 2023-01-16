package com.developer.heart.leitorjdbcspringbatch.reader;

import com.developer.heart.leitorjdbcspringbatch.dto.Cliente;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcCursorReaderConfig {

    @Bean
    public JdbcCursorItemReader jdbcCursorItemReader(@Qualifier("appDataSource") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Cliente>()
                .name("jdbcCursorItemReader")
                .dataSource(dataSource)
                .sql("""
                        select * from cliente
                        """)
                .rowMapper(new BeanPropertyRowMapper<>(Cliente.class))
                .build();
    }

}
