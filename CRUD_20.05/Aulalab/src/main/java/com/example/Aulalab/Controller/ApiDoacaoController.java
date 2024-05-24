package com.example.Aulalab.Controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Aulalab.Model.Doacao;
import com.example.Aulalab.Model.doacaoDTO;
import com.example.Aulalab.Service.IDoacaoService;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class ApiDoacaoController {
    Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    IDoacaoService doacaoService;

    @PutMapping("/saveDoa")
    public ResponseEntity<Object> cadastrar(@RequestBody doacaoDTO doa ){
        try{
            Optional<Doacao> doacao = doacaoService.cadastrarDoa(doa);
            return ResponseEntity.status(HttpStatus.CREATED).body(doacao.get());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
   @GetMapping("/getAllDoa")
    public ResponseEntity<Object> todoEstoque(){
       return ResponseEntity.status(HttpStatus.OK).body(doacaoService.consultarDoacao());
   }

   @DeleteMapping("/deleteDoa/{id}")
    public  void excluir(@PathVariable("id")int id){
       doacaoService.excluir(id);
   }

   @GetMapping("/getDoaId/{id}")
   public Optional<Doacao> doaById(@PathVariable("id")long id){
        return doacaoService.consultarPorId(id);
   }

    @PutMapping("/updateDoa/{id}")
    public ResponseEntity<Object> atualizaDoa(@PathVariable("id") Long doaId, @RequestBody doacaoDTO doaAtua) {
        logger.info("saihisahsaoihsaoish");
        try {
            doacaoDTO doa = doacaoService.atualizaDoa(doaId, doaAtua);
            return ResponseEntity.ok(doa);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
