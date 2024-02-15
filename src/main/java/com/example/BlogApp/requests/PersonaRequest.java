package com.example.BlogApp.requests;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
@Data
public class PersonaRequest {
    @NotNull(message = "Non deve essere null")
    @NotEmpty(message = "Non deve essere vuoto")
    @Size(min = 3,max=15,message = "Deve avere un numero di caratteri tra 3 e 15")
    private String nome;

    @NotNull(message = "Non deve essere null")
    @NotEmpty(message = "Non deve essere vuoto")
    @Size(min = 3,max=15,message = "Deve avere un numero di caratteri tra 3 e 15")
    private String cognome;

    @NotNull(message = "Non deve essere null")
    @NotEmpty(message = "Non deve essere vuoto")
    @Email(message = "Non è una email valida")
    private String email;

    @NotNull(message = "Non deve essere null")
    private LocalDate dataNascita;
}
