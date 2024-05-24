package com.example.Aulalab.Service;

import com.example.Aulalab.Model.Usuario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.Aulalab.Model.Doacao;
import com.example.Aulalab.Model.doacaoDTO;
import com.example.Aulalab.Model.DoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoacaoService implements IDoacaoService {
    Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    DoacaoRepository doacaoRepository;
    @Override
    public Optional<Doacao> cadastrarDoa(doacaoDTO d) {
        logger.info("Service de cadastro iniciado: ");
        Doacao doacao = dtoParaDoacao(d);
        return Optional.ofNullable(doacaoRepository.save(doacao));

    }

    @Override
    public Optional<Doacao> consultarPorId(long id) {
        return doacaoRepository.findById(id);
    }

    @Override
    public List<Doacao> consultarDoacao() {
        return doacaoRepository.findAll();
    }


    public doacaoDTO atualizaDoa(Long id, doacaoDTO doacaoDTO) {
        Optional<Doacao> doacaoOptional = doacaoRepository.findById(id);
        if (doacaoOptional.isPresent()) {
            Doacao doacaoExistente = doacaoOptional.get();
            doacaoExistente.setTitulo(doacaoDTO.titulo());
            doacaoExistente.setDescricao(doacaoDTO.descricao());
            doacaoExistente.setQuantidade(doacaoDTO.quantidade());
            doacaoExistente.setCategoria(doacaoDTO.categoria());
            doacaoExistente.setCondicao(doacaoDTO.condicao());
            doacaoExistente.setDisponibilidade(doacaoDTO.disponibilidade());
            doacaoExistente.setImageUrls(doacaoDTO.imageUrls());

            Doacao doacaoAtualizada = doacaoRepository.save(doacaoExistente);

            return new doacaoDTO(
                    doacaoAtualizada.getId(),
                    doacaoAtualizada.getTitulo(),
                    doacaoAtualizada.getDescricao(),
                    doacaoAtualizada.getQuantidade(),
                    doacaoAtualizada.getCategoria(),
                    doacaoAtualizada.getCondicao(),
                    doacaoAtualizada.getDisponibilidade(),
                    doacaoAtualizada.getImageUrls()
            );
        } else {
            throw new IllegalArgumentException("Doação não encontrada para o ID fornecido: " + id);
        }
    }


    @Override
    public void excluir(long id) {
         doacaoRepository.deleteById(id);
    }

    public Doacao dtoParaDoacao(doacaoDTO d) {
        return new Doacao(
                d.titulo(),
                d.descricao(),
                d.quantidade(),
                d.categoria(),
                d.condicao(),
                d.disponibilidade(),
                d.imageUrls()
        );
    }
}
