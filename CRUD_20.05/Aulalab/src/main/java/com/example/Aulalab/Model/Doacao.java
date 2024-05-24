package com.example.Aulalab.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import java.util.List;

@Entity
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String titulo; // Equivalent to "nome"
    private String descricao;
    private int quantidade;
    private String categoria;
    private String condicao;
    private String disponibilidade;

    @ElementCollection
    @CollectionTable(name = "doacao_images", joinColumns = @JoinColumn(name = "doacao_id"))
    @Column(name = "image_url")
    private List<String> imageUrls;

    public Doacao() {
    }

    public Doacao(String titulo, String descricao, int quantidade, String categoria, String condicao, String disponibilidade, List<String> imageUrls) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.condicao = condicao;
        this.disponibilidade = disponibilidade;
        this.imageUrls = imageUrls;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}