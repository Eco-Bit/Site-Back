package com.example.Aulalab.Model;

import java.util.List;

public record doacaoDTO (
        long id,
        String titulo, // Equivalent to "nome"
        String descricao,
        int quantidade,
        String categoria,
        String condicao,
        String disponibilidade,
        List<String> imageUrls){
}
