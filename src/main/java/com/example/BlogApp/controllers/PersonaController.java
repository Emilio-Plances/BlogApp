package com.example.BlogApp.controllers;

import com.example.BlogApp.exceptions.NotFoundException;
import com.example.BlogApp.requests.PersonaRequest;
import com.example.BlogApp.responses.DefaultResponse;
import com.example.BlogApp.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/persone")
public class PersonaController  {
    @Autowired
    private PersonaService personaService;
    @GetMapping
    public ResponseEntity<DefaultResponse> getAll(Pageable pageable){
        try{
            return DefaultResponse.successNoMessage(personaService.getAll(pageable), HttpStatus.OK);
        }catch(Exception e){
            return DefaultResponse.errorNoMessage(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getById(@PathVariable int id){
        try {
            return DefaultResponse.successCustomMessage("Persona trovata!",personaService.findById(id),HttpStatus.OK);
        } catch (NotFoundException e) {
            return DefaultResponse.errorCustomMessage("Persona non trovata!",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return DefaultResponse.errorNoMessage(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping
    public ResponseEntity<DefaultResponse> add(@RequestBody PersonaRequest p){
        try{
            return DefaultResponse.successCustomMessage("Persona creata!",personaService.add(p),HttpStatus.CREATED);
        }catch(Exception e){
            return DefaultResponse.errorNoMessage(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> update(@PathVariable int id,@RequestBody PersonaRequest p){
        try{
            return DefaultResponse.successCustomMessage("Persona aggiornata!",personaService.update(id,p),HttpStatus.OK);
        }catch (NotFoundException e) {
            return DefaultResponse.errorCustomMessage("Persona non trovata!",HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return DefaultResponse.errorNoMessage(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> delete(@PathVariable int id){
        try{
            String message="Persona con id="+id+" eliminata";
            personaService.delete(id);
            return DefaultResponse.voidSuccess(message,HttpStatus.OK);
        }catch (NotFoundException e) {
            return DefaultResponse.errorCustomMessage("Persona non trovata!",HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return DefaultResponse.errorNoMessage(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
