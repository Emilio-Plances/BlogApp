package com.example.BlogApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Entity
@Data
@Table(name="persone")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sequenza_persone")
    @SequenceGenerator(name="sequenza_persone",initialValue = 1,allocationSize = 1)
    private int id;
    private String nome;
    private String cognome;
    private String email;
    @Column(name = "data_nascita")
    private LocalDate dataNascita;
    private String avatar;
    @JsonIgnore
    @OneToMany(mappedBy = "persona",cascade = CascadeType.REMOVE)
    private List<Blog> listaBlog;
}