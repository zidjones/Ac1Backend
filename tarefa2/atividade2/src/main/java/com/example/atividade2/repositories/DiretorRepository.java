package com.example.atividade2.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.atividade2.models.Diretor;

public interface DiretorRepository extends JpaRepository<Diretor, Long> {
    List<Diretor> findByNomeStartingWith(String nome);

    @Query("SELECT d FROM Diretor d LEFT JOIN FETCH d.filmes WHERE d.id = :id")
    Optional<Diretor> findDiretorByIdComFilmes(Long id);
}