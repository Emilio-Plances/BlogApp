package com.example.BlogApp.controllers;

import com.example.BlogApp.exceptions.NotFoundException;
import com.example.BlogApp.models.Persona;
import com.example.BlogApp.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/persone")
public class PersonaController  {
    @Autowired
    private PersonaService personaService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Persona> getAll(){
        return personaService.getListaPersone();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> getById(@PathVariable int id){
        try {
            Persona p = personaService.findById(id);
            return new ResponseEntity<Persona>(p, HttpStatus.OK);
        }
        catch (NotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<Persona> save(@RequestBody Persona persona){
        Persona p=personaService.add(persona);
        return new ResponseEntity<>(p,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Persona> putUpdate(@PathVariable int id, @RequestBody Persona persona){
        try {
            Persona p=personaService.update(id,persona);
            return new ResponseEntity<>(p,HttpStatus.OK);
        }
        catch (NotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Persona> delete(@PathVariable int id){
        try {
            personaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (NotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
