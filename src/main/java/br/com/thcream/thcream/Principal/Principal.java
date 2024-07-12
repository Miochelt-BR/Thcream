package br.com.thcream.thcream.Principal;

import br.com.thcream.thcream.Service.ConsumerApi;
import br.com.thcream.thcream.Service.ConverterDados;
import br.com.thcream.thcream.model.DadosEpisodio;
import br.com.thcream.thcream.model.DadosSerie;
import br.com.thcream.thcream.model.DadosTemporada;
import br.com.thcream.thcream.model.Episodio;
;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;


import static org.springframework.boot.web.server.Ssl.ClientAuth.map;

public class Principal {
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String CHAVE = "&apikey=bd4251ba";
    private ConsumerApi consumo = new ConsumerApi();
    private ConverterDados converter = new ConverterDados();
    private Scanner entradas = new Scanner(System.in);

    public void exibeMenu() {
        System.out.println("Digite o nome da série:");
        var nomeSeries = entradas.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSeries.replace(" ", "+") + CHAVE);
        System.out.println("JSON Retornado: " + json); // Debug

        DadosSerie serie = converter.obterDados(json, DadosSerie.class);
        System.out.println(serie);

        List<DadosTemporada> listaTemporadas = new ArrayList<>();
        if (serie != null && serie.totalTemporadas() != null) {
            // Montando as URLs para todas as temporadas
            for (int i = 1; i <= Integer.parseInt(String.valueOf(serie.totalTemporadas())); i++) {
                String enderecoTemporada = String.format("http://www.omdbapi.com/?t=%s&Season=%d&apikey=%s",
                        nomeSeries.replace(" ", "+"), i, "bd4251ba");
                var dadosTemporadaJson = consumo.obterDados(enderecoTemporada);
                DadosTemporada dadosTemporada = converter.obterDados(dadosTemporadaJson, DadosTemporada.class);
                listaTemporadas.add(dadosTemporada);
            }

            // Consolidando todos os episódios em uma única lista
            List<DadosEpisodio> todosEpisodios = listaTemporadas.stream()
                    .flatMap(temporada -> temporada.episodios().stream())
                    .collect(Collectors.toList());

            // Imprimindo todos os títulos dos episódios
            todosEpisodios.forEach(episodio -> System.out.println(episodio.titulo()));

            // Usando Stream para listar os títulos dos episódios, limitando a 3 resultados que contenham "Gilmore"
            todosEpisodios.stream()
                    .map(DadosEpisodio::titulo)
                    .filter(titulo -> titulo.contains("Gilmore"))
                    .limit(3)
                    .forEach(System.out::println);
        } else {
            System.out.println("Não foi possível obter os dados da série.");
        }

        // Transformando listaTemporadas em uma lista de Episodio
        List<Episodio> todosEpisodiosLista = listaTemporadas.stream()
                .flatMap(temporada -> temporada.episodios().stream())
                .map(d -> new Episodio(d.numeroEpisodio(), d.titulo(), d.numeroEpisodio(), d.avaliacao(),d))
                .collect(Collectors.toList());
    }
}