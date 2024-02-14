package com.example.BlogApp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequenza_libreria")
    @SequenceGenerator(name="sequenza_libreria",initialValue = 1,allocationSize = 1)
    private int id;
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    @Column(name="tempo_di_lettura")
    private int tempoDiLettura;
    @ManyToOne
    @JoinColumn(name="persona_fk")
    private Persona persona;
}
