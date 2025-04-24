package com.example.Aulalab.Service;

import com.example.Aulalab.Model.Ponto;
import com.example.Aulalab.Model.PontoDTO;
import com.example.Aulalab.Model.PontoRepository;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PontoService implements IPontoService {
    Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    PontoRepository pontoRepository;

    @Override
    public Optional<Ponto> cadastrarPonto(PontoDTO p) {
        logger.info("Servico Cadastrar Ponto");
        Ponto ponto = dtoParaPonto(p);
        return Optional.ofNullable(pontoRepository.save(ponto));
    }

    @Override
    public Optional<Ponto> consultarPorIdPonto(String id) {
        return pontoRepository.findById(id);
    }

    @Override
    public List<Ponto> consultarTodosPontos() {
        return pontoRepository.findAll();
    }

    @Override
    public PontoDTO atualizaPonto(String id, PontoDTO ponto) {
        Optional<Ponto> pontoOptional = pontoRepository.findById(id);
        if(pontoOptional.isPresent()) {
            Ponto pontoExiste = pontoOptional.get();

            pontoExiste.setEndererecoPonto(ponto.endererecoPonto());
            pontoExiste.setNumeroPonto(ponto.numeroPonto());
            pontoExiste.setNomePonto(ponto.nomePonto());
            pontoExiste.setMateriasPonto(ponto.materiasPonto());
            pontoExiste.setAbertoSabado(ponto.abertoSabado());
            Ponto pontoAtualizado = pontoRepository.save(pontoExiste);

            return new PontoDTO(
                    pontoAtualizado.getId(),
                    pontoAtualizado.getEndererecoPonto(),
                    pontoAtualizado.getNumeroPonto(),
                    pontoAtualizado.getNomePonto(),
                    pontoAtualizado.getMateriasPonto(),
                    pontoAtualizado.getAbertoSabado()
            );
        }
        else{
            throw new IllegalArgumentException("Ponto nao encontrado");
        }
    }

    @Override
    public void excluirPonto(String id) { pontoRepository.deleteById(id);}


    public Ponto dtoParaPonto(PontoDTO p) {
        return new Ponto(p.nomePonto(), p.endererecoPonto(), p.numeroPonto(),p.materiasPonto(), p.abertoSabado());
    }
}
