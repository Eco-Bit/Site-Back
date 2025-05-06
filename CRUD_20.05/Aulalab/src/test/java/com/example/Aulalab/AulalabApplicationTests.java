package com.example.Aulalab;


import com.example.Aulalab.Model.Usuario;
import com.example.Aulalab.Model.UsuarioDTO;
import com.example.Aulalab.Model.UsuarioRepositorio;
import com.example.Aulalab.Service.UsuarioServico;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AulalabApplicationTests {

    @Autowired
    private UsuarioServico usuarioServico;

    @Autowired
    private UsuarioRepositorio usuariorepositorio;

    @Test
    void ct01_cadastro_preenchido_usuario_validado(){
        UsuarioDTO usuario = new UsuarioDTO(
                null,
                "Usuario 1",
                "teste@usuario.com",
                "11999998888",
                "teste123",
                "12345-678",
                "Rua rua ruela, 01"
        );
        //Salvo
        Optional<Usuario> salvo = usuarioServico.cadastrar(usuario);

        //Verificar se foi Salvo
        assertTrue(salvo.isPresent());
        Usuario usuarioRetornado = salvo.get();

        //Verificar se foi salvo do bd
        assertEquals("Usuario 1", usuarioRetornado.getNome());
        assertEquals("teste@usuario.com", usuarioRetornado.getemail());
    }

    @Test
    void ct02_cadastro_usuario_email_duplicado() {
        // Primeiro cadastro
        UsuarioDTO primeiro = new UsuarioDTO(
                null,
                "João Silva",
                "joao@email.com",
                "11988887777",
                "senha123",
                "12345-678",
                "Rua A, 100"
        );
        usuarioServico.cadastrar(primeiro);

        // Segundo cadastro com o mesmo email
        UsuarioDTO duplicado = new UsuarioDTO(
                null,
                "Carlos Souza",
                "joao@email.com", // mesmo e-mail
                "11999998888",
                "senha456",
                "98765-432",
                "Rua B, 200"
        );

        // Tenta cadastrar e espera uma exceção ou retorno vazio
        Exception exception = assertThrows(Exception.class, () -> {
            usuarioServico.cadastrar(duplicado);
        });

        assertEquals("Email já cadastrado.", exception.getMessage());
    }


    @Test
    void ct03_cadastrar_usuario_faltando_campos() {
        UsuarioDTO invalido = new UsuarioDTO(
                null,
                "Julio",
                null,
                "11999998888",
                "alo", // senha ausente
                "12345-678",
                "Rua C, 300"
        );

        Exception exception = assertThrows(Exception.class, () -> {
            usuarioServico.cadastrar(invalido);
        });

        String mensagem = exception.getMessage();
        assertTrue(mensagem.contains("email"));
        System.out.println("ct03 passou: campos obrigatórios validados com erro -> " + mensagem);
    }

}
