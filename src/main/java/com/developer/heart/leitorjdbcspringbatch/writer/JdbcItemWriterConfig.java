package com.developer.heart.leitorjdbcspringbatch.writer;

import com.developer.heart.leitorjdbcspringbatch.dto.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class JdbcItemWriterConfig {


    @Bean
    public ItemWriter<?> itemWriter() {
        return list -> list.forEach(System.out::println);
    }

}
