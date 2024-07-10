package br.com.thcream.thcream.Service;

public interface IconverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
