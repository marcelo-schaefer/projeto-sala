package com.prova.sala;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends MongoRepository<Sala, String> {

	List<Sala> findAllByTipo(SalaTipo tipo);

	List<Sala> findAllByIdIn(List<String> id);

}
