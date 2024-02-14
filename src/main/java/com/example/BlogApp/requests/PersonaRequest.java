package com.example.BlogApp.requests;

import lombok.Data;

import java.time.LocalDate;
@Data
public class PersonaRequest {
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataNascita;
}
