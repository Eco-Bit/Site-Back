package com.example.Aulalab.Service;

import com.example.Aulalab.Model.AnaliseSentimentoRepositorio;
import com.example.Aulalab.Model.AnaliseSentimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnaliseSentimentoService {

    @Autowired
    AnaliseSentimentoRepositorio repositorio;

    public AnaliseSentimento saveAnalise(AnaliseSentimento analise) {
        return repositorio.save(analise);
    }
}
