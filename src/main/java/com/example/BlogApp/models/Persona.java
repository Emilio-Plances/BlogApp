package com.example.BlogApp.models;

import lombok.Data;

import java.time.LocalDate;
import java.util.Random;

@Data
public class Persona {
    private int id=new Random().nextInt(1,Integer.MAX_VALUE);
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataNascita;
    private String avatar;
}