package com.jmpaniego.movies.repositories;

import com.jmpaniego.movies.models.Genere;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenereRepository extends CrudRepository<Genere, Long> {
  Optional<Genere> findByName(String name);
}
