package com.example.Aulalab.Controller;

import com.example.Aulalab.Model.UsuarioRepositorio;
import com.example.Aulalab.Service.UsuarioServico;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Aulalab.Model.Usuario;
import com.example.Aulalab.Model.UsuarioDTO;
import com.example.Aulalab.Service.iUsuarioServico;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class apiUsuarioController {
    Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private iUsuarioServico usuarioServico;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @PostMapping("/save")
    public ResponseEntity<Object> cadastrarCliente(@RequestBody UsuarioDTO user) {
        logger.info(">>>>>> apicontroller cadastrar iniciado ");
        try {
            Optional<Usuario> usuario = usuarioServico.cadastrar(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario.get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UsuarioDTO loginDTO) {
        logger.info(">>>>>> apicontroller LOGIN produto iniciado ");
        try {
            Usuario usuario = usuarioServico.validarCadastro(loginDTO.email(), loginDTO.senha());
            if (usuario != null) {
                // Retorna o ID do usuário diretamente
                return ResponseEntity.ok(Map.of("id", usuario.getId()));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/getall")
    public List<Usuario> consultaCatalogo() {
        return usuarioServico.consultaCatalogo();
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Object> atualizaUser(@PathVariable("id") String userId, @RequestBody UsuarioDTO userAtua) {
        logger.info("Atualizando usuário com ID: {}", userId);
        logger.info("Dados recebidos para atualização: {}", userAtua);
        try {
            UsuarioDTO user = usuarioServico.atualizaUser(userId, userAtua);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            logger.error("Usuário não encontrado para o ID: {}", userId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado para o ID fornecido");
        } catch (Exception e) {
            logger.error("Erro ao atualizar usuário: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @DeleteMapping("/deleteUsuario/{id}")
    public void deleteUsuario(@PathVariable("id") String id) {
        usuarioServico.excluir(id);
    }

    @GetMapping("/getUserId/{id}")
    public ResponseEntity<Object> userById(@PathVariable("id") String id) {
        Optional<Usuario> usuario = usuarioServico.consultaPorIdUser(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    }

    @PutMapping("/updateSenha/{id}")
    public ResponseEntity<Object> atualizarSenha(
            @PathVariable("id") String userId,
            @RequestBody Map<String, String> senhaPayload) {

        String senhaAtual = senhaPayload.get("senhaAtual");
        String novaSenha = senhaPayload.get("novaSenha");

        if (senhaAtual == null || novaSenha == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos obrigatórios: senhaAtual e novaSenha");
        }

        try {
            usuarioServico.atualizarSenha(userId, senhaAtual, novaSenha);
            return ResponseEntity.ok("Senha atualizada com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar senha.");
        }
    }

}
