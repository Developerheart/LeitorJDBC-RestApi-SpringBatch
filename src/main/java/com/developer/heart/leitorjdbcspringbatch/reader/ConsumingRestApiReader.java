package com.developer.heart.leitorjdbcspringbatch.reader;

import com.developer.heart.leitorjdbcspringbatch.dto.Pessoa;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class ConsumingRestApiReader implements ItemReader<Pessoa> {

    private final String apiUrl;

    private final RestTemplate restTemplate;

    private int nextStudentIndex;

    private List<Pessoa> pessoaData;

    public ConsumingRestApiReader(String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
        nextStudentIndex = 0;
    }

    @Override
    public Pessoa read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (studentDataIsNotInitialized()) {
            pessoaData = fetchStudentDataFromAPI();
        }

        Pessoa nextStudent = null;

        if (nextStudentIndex < pessoaData.size()) {
            nextStudent = pessoaData.get(nextStudentIndex);
            nextStudentIndex++;
        } else {
            nextStudentIndex = 0;
            pessoaData = null;
        }

        return nextStudent;
    }

    private boolean studentDataIsNotInitialized() {
        return this.pessoaData == null;
    }

    private List<Pessoa> fetchStudentDataFromAPI() {
        ResponseEntity<Pessoa[]> response = restTemplate.getForEntity(apiUrl, Pessoa[].class);
        Pessoa[] pessoaData = response.getBody();
        assert pessoaData != null;
        return Arrays.asList(pessoaData);
    }
}
