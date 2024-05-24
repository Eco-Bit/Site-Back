package com.example.Aulalab;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;


@SpringBootTest
class AulalabApplicationTests {

	@Autowired
	ProdutoRepository repository;

	@Test
	void contextLoads() {
	}

	@Test
	void validaPersistencia(){

		repository.deleteAll();
		Produto produto1 = new Produto("Eletrobomba", "eletronico", 55.32, 12);
		repository.saveAll(Arrays.asList(produto1));
	}

}
