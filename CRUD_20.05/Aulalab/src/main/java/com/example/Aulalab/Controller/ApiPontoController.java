package com.example.Aulalab.Controller;

import com.example.Aulalab.Model.Usuario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Aulalab.Model.Ponto;
import com.example.Aulalab.Model.PontoDTO;
import com.example.Aulalab.Service.IPontoService;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class ApiPontoController {
    Logger logger = LogManager.getLogger(this.getClass().getName());

    @Autowired
    IPontoService pontoService;

    @PutMapping("/savePonto")
    public ResponseEntity<Object> savePonto(@RequestBody PontoDTO pont) {
        try {
            Optional<Ponto> ponto = pontoService.cadastrarPonto(pont);
            return ResponseEntity.status(HttpStatus.CREATED).body(ponto.get());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/getAllPontos")
    public ResponseEntity<Object>consultarTodosPontos() {
        return ResponseEntity.status(HttpStatus.OK).body(pontoService.consultarTodosPontos());
    }

    @PutMapping("/atualizaPonto/{id}")
    public ResponseEntity<Object> atualizaPonto(@PathVariable("id") String pontoID, @RequestBody PontoDTO pontoAtualizado){
        logger.info("Ponto Atualizado");
        try{
            PontoDTO p = pontoService.atualizaPonto(pontoID, pontoAtualizado);
            return ResponseEntity.ok(p);

        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deletePonto/{id}")
    public void excuirPonto(@PathVariable("id") String id){pontoService.excluirPonto(id);}

    @GetMapping("/getPontoId/{id}")
            public ResponseEntity<Object> pontoById(@PathVariable("id") String id){
            Optional<Ponto> ponto = pontoService.consultarPorIdPonto(id);
            if (ponto.isPresent()){
                return ResponseEntity.ok(ponto.get());
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ponto nao encontrado");
            }

    }

}
