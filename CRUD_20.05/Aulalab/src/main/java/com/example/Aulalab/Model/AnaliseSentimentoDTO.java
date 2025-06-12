package com.example.Aulalab.Model;

public record AnaliseSentimentoDTO(String text, String sentiment, String confidence, String createdAt) {

    private String text;
    private String sentiment;
    private Double confidence;
}
