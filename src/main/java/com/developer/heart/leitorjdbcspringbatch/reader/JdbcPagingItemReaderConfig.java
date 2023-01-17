package com.developer.heart.leitorjdbcspringbatch.reader;

import com.developer.heart.leitorjdbcspringbatch.dto.Cliente;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class JdbcPagingItemReaderConfig {

    @Bean
    public JdbcPagingItemReader<Cliente> jdbcPagingItemReader(
            @Qualifier("appDataSource") DataSource appDataSource,
            PagingQueryProvider queryProvider) {
        return new JdbcPagingItemReaderBuilder<Cliente>()
                .name("jdbcPagingItemReader")
                .dataSource(appDataSource)
                .queryProvider(queryProvider)
                .pageSize(4)
//                .beanRowMapper(Cliente.class) // feito por uma classe propria do spring
                .rowMapper(rowMapper()) // implementação feita manualmente
                .build();
    }

    private RowMapper<Cliente> rowMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                if (rs.getRow() == 6) {
                    throw new SQLException(String.format("Encerrando a execução - Cliente inválido %s", rs.getString("email")));
                } else return clienteRowMapper(rs);
            }

            private Cliente clienteRowMapper(ResultSet rs) throws SQLException {
                return new Cliente(
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("idade"),
                        rs.getString("email")
                );
            }
        };
    }

    @Bean
    public SqlPagingQueryProviderFactoryBean queryProvider(@Qualifier("appDataSource") DataSource dataSource) {
        SqlPagingQueryProviderFactoryBean bean = new SqlPagingQueryProviderFactoryBean();
        bean.setDataSource(dataSource);
        bean.setSelectClause("SELECT *");
        bean.setFromClause("FROM cliente");
        bean.setSortKey("email");
        return bean;
    }


}
