package com.example.Aulalab.Service;

import com.example.Aulalab.Model.Usuario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.Aulalab.Model.Doacao;
import com.example.Aulalab.Model.doacaoDTO;
import com.example.Aulalab.Model.DoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class DoacaoService implements IDoacaoService {
    Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    DoacaoRepository doacaoRepository;

    @Override
    public Optional<Doacao> cadastrarDoa(doacaoDTO doacaoDto) {
        Doacao doacao = new Doacao();
        doacao.setId(doacaoDto.id());
        doacao.setTitulo(doacaoDto.titulo());
        doacao.setDescricao(doacaoDto.descricao());
        doacao.setQuantidade(doacaoDto.quantidade());
        doacao.setCategoria(doacaoDto.categoria());
        doacao.setCondicao(doacaoDto.condicao());
        doacao.setDisponibilidade(doacaoDto.disponibilidade());
        doacao.setimagensBase64(doacaoDto.imagensBase64());
        doacao.setUserId(doacaoDto.userId());
        doacao.setTelefone(doacaoDto.telefone());
        Doacao savedDoacao = doacaoRepository.save(doacao);
        return Optional.of(savedDoacao);
    }


    @Override
    public Optional<Doacao> consultarPorId(String id) {
        return doacaoRepository.findById(id);
    }

    @Override
    public List<Doacao> consultarIdUser(String userId) {
        return doacaoRepository.findByUserId(userId);
    }

    @Override
    public List<Doacao> consultarDoacao() {
        return doacaoRepository.findAll();
    }


    public doacaoDTO atualizaDoa(String id, doacaoDTO doacaoDTO) {
        Optional<Doacao> doacaoOptional = doacaoRepository.findById(id);
        if (doacaoOptional.isPresent()) {
            Doacao doacaoExistente = doacaoOptional.get();
            doacaoExistente.setTitulo(doacaoDTO.titulo());
            doacaoExistente.setDescricao(doacaoDTO.descricao());
            doacaoExistente.setQuantidade(doacaoDTO.quantidade());
            doacaoExistente.setCategoria(doacaoDTO.categoria());
            doacaoExistente.setCondicao(doacaoDTO.condicao());
            doacaoExistente.setDisponibilidade(doacaoDTO.disponibilidade());
            doacaoExistente.setimagensBase64(doacaoDTO.imagensBase64());
            doacaoExistente.setUserId(doacaoDTO.userId());
            doacaoExistente.setTelefone(doacaoDTO.telefone());

            Doacao doacaoAtualizada = doacaoRepository.save(doacaoExistente);

            return new doacaoDTO(
                    doacaoAtualizada.getId(),
                    doacaoAtualizada.getTitulo(),
                    doacaoAtualizada.getDescricao(),
                    doacaoAtualizada.getQuantidade(),
                    doacaoAtualizada.getCategoria(),
                    doacaoAtualizada.getCondicao(),
                    doacaoAtualizada.getDisponibilidade(),
                    doacaoAtualizada.getimagensBase64(),
                    doacaoAtualizada.getUserId(),
                    doacaoAtualizada.getTelefone()


            );
        } else {
            throw new IllegalArgumentException("Doação não encontrada para o ID fornecido: " + id);
        }
    }


    @Override
    public void excluir(String id) {
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
                d.imagensBase64(),
                d.userId(),
                d.telefone()
        );
    }
}