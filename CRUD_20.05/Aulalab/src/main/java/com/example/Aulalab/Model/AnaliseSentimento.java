package com.example.Aulalab.Model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection ="AnaliseSentimento")
public class AnaliseSentimento {

    @Id
    private String id;



    private String text;
    private String sentiment;
    private String confidence;
    private String createdAt;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public AnaliseSentimento(String text, String sentiment, String confidence, String createdAt) {
        this.text = text;
        this.sentiment = sentiment;
        this.confidence = confidence;
        this.createdAt = createdAt;
    }

    public AnaliseSentimento(){

    }

}
