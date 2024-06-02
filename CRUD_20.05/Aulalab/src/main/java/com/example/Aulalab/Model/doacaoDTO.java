package com.example.Aulalab.Model;

import java.util.List;

public record doacaoDTO (
        String id,
        String titulo, // Equivalent to "nome"
        String descricao,
        String quantidade,
        String categoria,
        String condicao,
        String disponibilidade,
        List<String> imagensBase64,
        String userId,
        String telefone)
{   }
