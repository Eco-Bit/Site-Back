package com.example.Aulalab.Service;

import com.example.Aulalab.Model.Doacao;
import com.example.Aulalab.Model.doacaoDTO;
import java.util.Optional;
import java.util.List;

public interface IDoacaoService {

    Optional<Doacao> cadastrarDoa(doacaoDTO doacao);
    Optional<Doacao> consultarPorId(String id);
    List<Doacao> consultarDoacao();
    doacaoDTO atualizaDoa(String id, doacaoDTO doacao);
    void excluir(String id);
    List<Doacao> consultarIdUser(String userId);

}
