package br.com.thcream.thcream;

import br.com.thcream.thcream.Service.ConsumerApi;
import br.com.thcream.thcream.Service.ConverterDados;
import br.com.thcream.thcream.model.DadosSerie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThcreamApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ThcreamApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Definindo os parâmetros de busca e a chave da API
		String busca = "Gilmore Girls";
		String chave = "bd4251ba";

		// Criando a instância de ConsumerApi
		ConsumerApi consumerApi = new ConsumerApi();

		// Montando a URL de busca
		String endereco = "http://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=" + chave;

		// Obtendo os dados da API
		var dados = consumerApi.obterDados(endereco);
//		var json = consumerApi.obterDados("https://coffee.alexflipnote.dev/random.json");
//		System.out.println(json);
		ConverterDados converterDados = new ConverterDados();
		DadosSerie dadosSerie = converterDados.obterDados(dados, DadosSerie.class);
		System.out.println(dadosSerie);

	}

	}
