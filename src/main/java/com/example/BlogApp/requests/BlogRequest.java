package com.example.BlogApp.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BlogRequest {
    @NotNull(message = "Non deve essere null")
    @NotEmpty(message = "Non deve essere vuoto")
    private String categoria;

    @NotNull(message = "Non deve essere null")
    @NotEmpty(message = "Non deve essere vuoto")
    private String titolo;

    @NotNull(message = "Non deve essere null")
    @NotEmpty(message = "Non deve essere vuoto")
    private String cover;

    @NotNull(message = "Non deve essere null")
    @NotEmpty(message = "Non deve essere vuoto")
    private String contenuto;

    @NotNull(message = "Non deve essere null")
    private Integer tempoDiLettura;

    @NotNull(message = "Non deve essere null")
    private int personaId;
}
