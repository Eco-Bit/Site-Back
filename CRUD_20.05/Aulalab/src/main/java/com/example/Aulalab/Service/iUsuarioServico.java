package com.example.Aulalab.Service;

import java.util.List;
import java.util.Optional;

import com.example.Aulalab.Model.Usuario;
import com.example.Aulalab.Model.UsuarioDTO;

public interface iUsuarioServico {
    public Optional<Usuario> cadastrar(UsuarioDTO usuario);
    public Optional<Usuario> consultaPorIdUser (long id);
    public List<Usuario> consultaCatalogo();
    public UsuarioDTO atualizaUser(Long id, UsuarioDTO usuario);
    public void excluir(Long id);
    public Usuario validarCadastro(String email, String senha);

}
