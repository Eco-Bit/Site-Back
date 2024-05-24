package com.example.Aulalab.Service;

import com.example.Aulalab.Model.UsuarioDTO;
import com.example.Aulalab.Model.Usuario;
import com.example.Aulalab.Model.UsuarioRepositorio;
import com.example.Aulalab.Model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServico implements iUsuarioServico {
    Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Override
    public Optional<Usuario> cadastrar(UsuarioDTO user) {
        logger.info("Serviço de cadastro iniciado");
        Usuario usuario = dtoParaUsuario(user);
        return Optional.ofNullable(usuarioRepositorio.save(usuario));
    }

    @Override
    public List<Usuario> consultaCatalogo() {

        return usuarioRepositorio.findAll();
    }


    @Override
    public void excluir(Long id) {

    }
    public Usuario dtoParaUsuario (UsuarioDTO user){
        return new Usuario(user.nome(), user.email(), user.telefone(), user.senha(), user.cep(), user.endereco());
    }

    @Override
    public Usuario validarCadastro(String email, String senha) {
        Usuario usuario = usuarioRepositorio.findByemail(email);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            // Successful login
            return usuario;
        } else {
            // Failed login attempt
            logger.info("Login attempt failed for user: {}", email);
            return null;
        }
    }

    @Override
    public UsuarioDTO atualizaUser(Long id, UsuarioDTO user) {
        Optional<Usuario> userOptional = usuarioRepositorio.findById(id);
        if (userOptional.isPresent()) {
            Usuario usuarioExiste = userOptional.get();
            // Atualiza os campos da doação existente com base nos dados fornecidos
            usuarioExiste.setNome(user.nome());
            usuarioExiste.setemail(user.email());
            usuarioExiste.setSenha(user.senha());
            // Salva a doação atualizada no banco de dados
            Usuario UserAtualizada = usuarioRepositorio.save(usuarioExiste);
            // Retorna os dados da doação atualizada
            return new UsuarioDTO(
                    UserAtualizada.getId(),
                    UserAtualizada.getNome(),
                    UserAtualizada.getemail(),
                    UserAtualizada.getSenha(),
                    UserAtualizada.getCep(),
                    UserAtualizada.getEndereco(),
                    UserAtualizada.getTelefone()
            );
        } else {
            // Se a doação não for encontrada, lança uma exceção
            throw new IllegalArgumentException("Usuario não encontrada para o ID fornecido: " + id);
        }
    }

    @Override
    public Optional<Usuario>consultaPorIdUser(long id) {
        return usuarioRepositorio.findById(id);
    }

}
