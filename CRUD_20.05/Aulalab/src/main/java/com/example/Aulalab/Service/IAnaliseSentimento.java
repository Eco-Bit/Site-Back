package com.example.Aulalab.Service;

import com.example.Aulalab.Model.AnaliseSentimento;

import java.util.Optional;

public interface IAnaliseSentimento {
    public Optional<AnaliseSentimento> saveAnalise(AnaliseSentimento analiseSentimento);

}
