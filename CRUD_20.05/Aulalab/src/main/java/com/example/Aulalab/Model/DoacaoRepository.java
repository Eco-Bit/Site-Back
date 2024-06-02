package com.example.Aulalab.Model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface DoacaoRepository extends MongoRepository<Doacao, String> {
    List<Doacao> findByUserId(String userId);
}