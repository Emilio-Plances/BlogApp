package com.example.BlogApp.models;

import lombok.Data;

import java.util.Random;

@Data
public class Blog {
    private int id=new Random().nextInt(1,Integer.MAX_VALUE);
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempoDiLettura;
    private Persona persona;
}
