package com.jmpaniego.movies.repositories;

import com.jmpaniego.movies.models.Genere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenereRepository extends JpaRepository<Genere, Long> {
  Optional<Genere> findByName(String name);
}
