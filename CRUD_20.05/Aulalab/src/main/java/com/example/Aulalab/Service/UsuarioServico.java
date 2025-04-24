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
    public void excluir(String id) {

    }
    public Usuario dtoParaUsuario (UsuarioDTO user){
        return new Usuario(user.nome(), user.email(), user.telefone(), user.senha(), user.cep(), user.endereco());
    }

    @Override
    public Usuario validarCadastro(String email, String senha) {
        Usuario usuario = usuarioRepositorio.findByemail(email);
        if (usuario != null && usuario.getSenha().equals(senha)) {

            return usuario;
        } else {

            logger.info("Tentativa de login falhou para o usuário: {}", email);
            return null;
        }
    }

    @Override
    public UsuarioDTO atualizaUser(String id, UsuarioDTO user) {
        Optional<Usuario> userOptional = usuarioRepositorio.findById(id);
        if (userOptional.isPresent()) {
            Usuario usuarioExiste = userOptional.get();
            usuarioExiste.setNome(user.nome());
            usuarioExiste.setemail(user.email());
            usuarioExiste.setCep(user.cep());
            usuarioExiste.setEndereco(user.endereco());
            usuarioExiste.setTelefone(user.telefone());

            Usuario userAtualizado = usuarioRepositorio.save(usuarioExiste);
            return new UsuarioDTO(
                    userAtualizado.getId(),
                    userAtualizado.getNome(),
                    userAtualizado.getemail(),
                    "", // senha não é retornada
                    userAtualizado.getCep(),
                    userAtualizado.getEndereco(),
                    userAtualizado.getTelefone()
            );
        } else {
            throw new IllegalArgumentException("Usuário não encontrado para o ID fornecido: " + id);
        }
    }

    public void atualizarSenha(String id, String senhaAtual, String novaSenha) {
        Optional<Usuario> userOptional = usuarioRepositorio.findById(id);
        if (userOptional.isPresent()) {
            Usuario usuario = userOptional.get();
            if (!usuario.getSenha().equals(senhaAtual)) {
                throw new IllegalArgumentException("Senha atual incorreta.");
            }

            usuario.setSenha(novaSenha); // Idealmente com hash
            usuarioRepositorio.save(usuario);
        } else {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }
    }



    @Override
    public Optional<Usuario>consultaPorIdUser(String id) {
        return usuarioRepositorio.findById(id);
    }

}
