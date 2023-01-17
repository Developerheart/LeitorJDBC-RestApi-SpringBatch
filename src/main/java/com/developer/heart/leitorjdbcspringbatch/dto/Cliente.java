package com.developer.heart.leitorjdbcspringbatch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    private String nome;
    private String sobrenome;
    private String idade;
    private String email;

}
