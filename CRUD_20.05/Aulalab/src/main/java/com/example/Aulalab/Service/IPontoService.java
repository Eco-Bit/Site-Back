package com.example.Aulalab.Service;

import com.example.Aulalab.Model.Ponto;
import com.example.Aulalab.Model.PontoDTO;

import java.util.Optional;
import java.util.List;

public interface IPontoService {
    public Optional<Ponto> cadastrarPonto(PontoDTO ponto);
    public Optional<Ponto> consultarPorIdPonto(String id);
    public List<Ponto> consultarTodosPontos();
    public PontoDTO atualizaPonto(String id, PontoDTO ponto);
    public void excluirPonto(String id);
}
