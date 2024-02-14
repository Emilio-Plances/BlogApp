package com.example.BlogApp.repositories;

import com.example.BlogApp.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona,Integer>, PagingAndSortingRepository<Persona, Integer> {
}
