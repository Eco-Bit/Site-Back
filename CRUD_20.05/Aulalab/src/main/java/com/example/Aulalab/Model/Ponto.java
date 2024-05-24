package com.example.Aulalab.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;

@Entity
public class Ponto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nomePonto;
    private String endererecoPonto;
    private String numeroPonto;
    //private List <String> materiasPonto;
    private String materiasPonto;

    public Ponto(){

    }

    public Ponto(String nomePonto, String endererecoPonto, String numeroPonto, String materiasPonto) {
        this.nomePonto = nomePonto;
        this.endererecoPonto = endererecoPonto;
        this.numeroPonto = numeroPonto;
        this.materiasPonto = materiasPonto;
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
    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

}

