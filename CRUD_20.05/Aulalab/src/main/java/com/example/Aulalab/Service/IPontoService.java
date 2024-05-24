package com.example.Aulalab.Service;

import com.example.Aulalab.Model.Ponto;
import com.example.Aulalab.Model.PontoDTO;

import java.util.Optional;
import java.util.List;

public interface IPontoService {
    public Optional<Ponto> cadastrarPonto(PontoDTO ponto);
    public Optional<PontoDTO> consultarPorIdPonto(Long id);
    public List<Ponto> consultarTodosPontos();
    public PontoDTO atualizaPonto(Long id, PontoDTO ponto);
    public void excluirPonto(Long id);
}
