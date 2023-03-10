package com.developer.heart.leitorjdbcspringbatch.step;

import com.developer.heart.leitorjdbcspringbatch.dto.Cliente;
import com.developer.heart.leitorjdbcspringbatch.dto.Pessoa;
import com.developer.heart.leitorjdbcspringbatch.reader.ConsumingRestApiReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@EnableBatchProcessing
public class JdbcStepConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    //    @Bean
//    public Step jdbcStep(JdbcPagingItemReader<Cliente> jdbcPagingItemReader, ItemWriter<Cliente> itemWriter) {
//        return stepBuilderFactory
//                .get("jdbcStepXD")
//                .<Cliente, Cliente>chunk(1)
//                .reader(jdbcPagingItemReader)
//                .reader(jdbcPagingItemReader)
//                .writer(itemWriter)
//                .faultTolerant()
//                .skip(Exception.class)// dica: criar uma exceção de négocio especifica para cada ocasião
//                .skipLimit(1)
//                .build();
//    }

    // consumindo uma apiRest usando RestTemplate
    @Bean
    public Step jdbcStep(ItemReader<Pessoa> consumingRestApiReader, ItemWriter<Pessoa> itemWriter) {
        return stepBuilderFactory
                .get("jdbcStepXD")
                .<Pessoa, Pessoa>chunk(1)
                .reader(consumingRestApiReader)
                .writer(itemWriter)
                .faultTolerant()
                .skip(Exception.class)// dica: criar uma exceção de négocio especifica para cada ocasião
                .skipLimit(1)
                .build();
    }

}
