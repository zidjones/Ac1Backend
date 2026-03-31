package com.example.atividade2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.atividade2.models.Diretor;
import com.example.atividade2.models.Filme;
import com.example.atividade2.repositories.DiretorRepository;
import com.example.atividade2.repositories.FilmeRepository;

@SpringBootApplication
public class Atividade2Application {

    @Bean
    public CommandLineRunner init(
            @Autowired FilmeRepository filmeRepository,
            @Autowired DiretorRepository diretorRepository) {
        return args -> {
            System.out.println("\n*** INSERINDO DIRETORES ***");

            Diretor d1 = diretorRepository.save(new Diretor(null, "Peter Jackson", null));
            Diretor d2 = diretorRepository.save(new Diretor(null, "Woody Allen", null));

            System.out.println("\n*** INSERINDO FILMES ***");

            filmeRepository.save(new Filme(null, "Homem Aranha", 127, d1));
            filmeRepository.save(new Filme(null, "Até o último homem", 102, d1));
            filmeRepository.save(new Filme(null, "Challengers", 188, d2));
            filmeRepository.save(new Filme(null, "Batman", 147, d2));

            System.out.println("\n*** FILMES COM DURAÇÃO > 150 ***");
            filmeRepository.findByDuracaoGreaterThan(120)
                    .forEach(f -> System.out.println(f.getTitulo() + " - " + f.getDuracao() + " min"));

            System.out.println("\n*** FILMES COM DURAÇÃO <= 115 ***");
            filmeRepository.findByDuracaoLessThanEqual(115)
                    .forEach(f -> System.out.println(f.getTitulo() + " - " + f.getDuracao() + " min"));

            System.out.println("\n*** FILMES CUJO TÍTULO COMEÇA COM 'C' ***");
            filmeRepository.findByTituloStartingWith("C")
                    .forEach(f -> System.out.println(f.getTitulo() + " - " + f.getDuracao() + " min"));

            System.out.println("\n*** DIRETORES COM NOME COMEÇANDO COM 'P' ***");
            diretorRepository.findByNomeStartingWith("P")
                    .forEach(d -> System.out.println(d.getNome()));

            System.out.println("\n*** DIRETOR COM FILMES ***");
            Diretor diretor = diretorRepository.findDiretorByIdComFilmes(d1.getId()).orElse(null);

            if (diretor != null) {
                System.out.println("Diretor: " + diretor.getNome());
                diretor.getFilmes().forEach(f -> System.out.println("Filme: " + f.getTitulo() + " - " + f.getDuracao() + " min"));
            } else {
                System.out.println("Não encontrado.");
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Atividade2Application.class, args);
    }

}