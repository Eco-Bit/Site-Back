package com.example.Aulalab.Model;


import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;


@Repository
public interface PontoRepository extends MongoRepository<Ponto, String> {
}
