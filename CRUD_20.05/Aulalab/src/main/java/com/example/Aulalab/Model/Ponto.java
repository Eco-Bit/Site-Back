package com.example.Aulalab.Model;
/*
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

 */

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "Ponto")
public class Ponto {

    @Id
    private String id;
    private String nomePonto;
    private String endererecoPonto;
    private String numeroPonto;
    //private List <String> materiasPonto;
    private String materiasPonto;

    public String getAbertoSabado() {
        return abertoSabado;
    }

    public void setAbertoSabado(String abertoSabado) {
        this.abertoSabado = abertoSabado;
    }

    private String abertoSabado;

    public Ponto(){

    }

    public Ponto(String nomePonto, String endererecoPonto, String numeroPonto, String materiasPonto, String abertoSabado) {
        this.nomePonto = nomePonto;
        this.endererecoPonto = endererecoPonto;
        this.numeroPonto = numeroPonto;
        this.materiasPonto = materiasPonto;
        this.abertoSabado = abertoSabado;
    }

    public String getMateriasPonto() {
        return materiasPonto;
    }

    public void setMateriasPonto(String materiasPonto) {
        if(materiasPonto.isEmpty()){
            throw new IllegalArgumentException("Campo obrigatorio");
        } else {
            this.materiasPonto = materiasPonto;
        }
    }

    public String getNumeroPonto() {
        return numeroPonto;
    }

    public void setNumeroPonto(String numeroPonto) {
        if(numeroPonto.isBlank()){
            throw new IllegalArgumentException("Campo obrigatorio");
        }else{
            this.numeroPonto = numeroPonto;
        }
    }

    public String getEndererecoPonto() {
        return endererecoPonto;
    }

    public void setEndererecoPonto(String endererecoPonto) {
        if(endererecoPonto.isBlank()){
            throw  new IllegalArgumentException("Campo Obrigatorio");
        }
        else {
            this.endererecoPonto = endererecoPonto;
        }
    }

    public String getNomePonto() {
        return nomePonto;
    }

    public void setNomePonto(String nomePonto) {
        if(nomePonto.isBlank()){
            throw new IllegalArgumentException("Campo Obrigatorio");
        }
        else{
            this.nomePonto = nomePonto;
        }
    }
    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

}

