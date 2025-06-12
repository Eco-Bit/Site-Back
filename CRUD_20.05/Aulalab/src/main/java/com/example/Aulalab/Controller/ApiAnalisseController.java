package com.example.Aulalab.Controller;


import com.example.Aulalab.Model.AnaliseSentimento;
import com.example.Aulalab.Model.AnaliseSentimentoDTO;
import com.example.Aulalab.Service.AnaliseSentimentoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/")

public class ApiAnalisseController {

    Logger logger = LogManager.getLogger(this.getClass().getName());
    @Autowired
    AnaliseSentimentoService analiseSentimentoService;

    @PostMapping("/saveAnalise")
    public ResponseEntity<Object> saveAnalise(@RequestBody AnaliseSentimentoDTO analiseSentimentoDTO) {
        if (analiseSentimentoDTO.text() == null || analiseSentimentoDTO.text().trim().length() < 3) {
            return ResponseEntity.badRequest().body("Texto inválido ou muito curto");
        }

        AnaliseSentimento analise = new AnaliseSentimento();
        analise.setText(analiseSentimentoDTO.text());
        analise.setSentiment(analiseSentimentoDTO.sentiment());
        analise.setConfidence(analiseSentimentoDTO.confidence());


        AnaliseSentimento saveAnalise = analiseSentimentoService.saveAnalise(analise);
        return ResponseEntity.ok(saveAnalise);
    }
}
