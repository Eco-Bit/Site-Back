package com.example.Aulalab;

import com.example.Aulalab.Model.Usuario;
import com.example.Aulalab.Model.UsuarioRepositorio;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class LoadDatabase {
    Logger logger = LogManager.getLogger(this.getClass());


   /* @Bean
    CommandLineRunner initDatabase(DoacaoRepository repository){

        return  arg ->{
            Doacao doa1 = new Doacao("teste","nome",TRUE,3,"teste");
        repository.saveAll(Arrays.asList(doa1));
        logger.info("item cadastrado");
        };
    } */
    @Bean
    CommandLineRunner initDatabase(UsuarioRepositorio repositorio){
        return args -> {
            Usuario usuario = new Usuario("Thiago","ttt@gmail.com","58926123","3636","09912230","rua libano,38");
            repositorio.saveAll(Arrays.asList(usuario));
            logger.info("Cadastro realizado");
        };
    }

}
