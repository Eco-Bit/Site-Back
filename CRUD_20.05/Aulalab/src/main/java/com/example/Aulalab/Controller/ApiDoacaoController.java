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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class ApiDoacaoController {
    Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    IDoacaoService doacaoService;

    @PostMapping("/saveDoa")
    public ResponseEntity<Object> cadastrar(@RequestBody doacaoDTO doaDTO) {
        try {
            Optional<Doacao> doacao = doacaoService.cadastrarDoa(doaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(doacao.get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

   @GetMapping("/getAllDoa")
    public ResponseEntity<Object> todoEstoque(){
       return ResponseEntity.status(HttpStatus.OK).body(doacaoService.consultarDoacao());
   }

   @DeleteMapping("/deleteDoa/{id}")
    public  void excluir(@PathVariable("id")String id){
       doacaoService.excluir(id);
   }

   @GetMapping("/getDoaId/{id}")
   public Optional<Doacao> doaById(@PathVariable("id")String id){
        return doacaoService.consultarPorId(id);
   }

    @GetMapping("/getDoaUser/{id}")
    public ResponseEntity<List<Doacao>> doaUser(@PathVariable("id") String userId) {
        List<Doacao> doacoes = doacaoService.consultarIdUser(userId);
        if (doacoes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(doacoes);
    }

    @PutMapping("/updateDoa/{id}")
    public ResponseEntity<Object> atualizaDoa(@PathVariable("id") String doaId, @RequestBody doacaoDTO doaAtua) {
        logger.info("saihisahsaoihsaoish");
        try {
            doacaoDTO doa = doacaoService.atualizaDoa(doaId, doaAtua);
            return ResponseEntity.ok(doa);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
