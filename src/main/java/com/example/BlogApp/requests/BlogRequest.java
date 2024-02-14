package com.example.BlogApp.requests;

import lombok.Data;

@Data
public class BlogRequest {
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempoDiLettura;
    private int personaId;
}
