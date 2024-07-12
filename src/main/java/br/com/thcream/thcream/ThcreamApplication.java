package br.com.thcream.thcream;

import br.com.thcream.thcream.Principal.Principal;
import br.com.thcream.thcream.Service.ConsumerApi;
import br.com.thcream.thcream.Service.ConverterDados;
import br.com.thcream.thcream.model.DadosEpisodio;
import br.com.thcream.thcream.model.DadosSerie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ThcreamApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ThcreamApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		String busca = "Gilmore Girls";
//		String chave = "bd4251ba";
//
//		// Template de URL base
//		String urlTemplate = "http://www.omdbapi.com/?t=%s&apikey=%s";
//
//
//		ConsumerApi consumerApi = new ConsumerApi();
//
//
//		String enderecoSerie = String.format(urlTemplate, busca.replace(" ", "+"), chave);
//
//		// Obtendo os dados da API
//		var dadosSerie = consumerApi.obterDados(enderecoSerie);
//		ConverterDados converterDados = new ConverterDados();
//		DadosSerie serie = converterDados.obterDados(dadosSerie, DadosSerie.class);
//		System.out.println(serie);
//
//
//		String enderecoEpisodio = String.format("http://www.omdbapi.com/?t=%s&Season=1&episode=2&apikey=%s", busca.replace(" ", "+"), chave);
//		var dadosEpisodio = consumerApi.obterDados(enderecoEpisodio);
//		DadosEpisodio episodio = converterDados.obterDados(dadosEpisodio, DadosEpisodio.class);
//		System.out.println(episodio);
//
//
//		List<Object> listaTemporadas = new ArrayList<>();
//
//		// Montando as URLs para todas as temporadas
//		for (int i = 1; i <= serie.totalTemporadas(); i++) {
//			String enderecoTemporada = String.format("http://www.omdbapi.com/?t=%s&Season=%d&apikey=%s", busca.replace(" ", "+"), i, chave);
//			var dadosTemporada = consumerApi.obterDados(enderecoTemporada);
//
//			listaTemporadas.add(dadosTemporada);
//			System.out.println(dadosTemporada);
		Principal principal = new Principal();
		principal.exibeMenu();

		}


//		System.out.println("Lista de dados das temporadas: " + listaTemporadas);

	}



