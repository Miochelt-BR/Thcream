package br.com.thcream.thcream.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverterDados implements IconverteDados {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
