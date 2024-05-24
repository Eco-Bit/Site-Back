package com.example.Aulalab.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoacaoRepository extends JpaRepository <Doacao, Long> {

    //Doacao findById(int id);
}
