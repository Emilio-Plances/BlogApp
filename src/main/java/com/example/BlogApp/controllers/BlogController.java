package com.example.BlogApp.controllers;

import com.example.BlogApp.exceptions.NotFoundException;
import com.example.BlogApp.requests.BlogRequest;
import com.example.BlogApp.responses.DefaultResponse;
import com.example.BlogApp.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @GetMapping
    public ResponseEntity<DefaultResponse> getAll(Pageable pageable){
        try{
            return DefaultResponse.successNoMessage(blogService.getAll(pageable), HttpStatus.OK);
        }catch(Exception e){
            return DefaultResponse.errorNoMessage(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getById(@PathVariable int id){
        try {
            return DefaultResponse.successCustomMessage("Blog trovato!",blogService.findById(id),HttpStatus.OK);
        } catch (NotFoundException e) {
            return DefaultResponse.errorCustomMessage("Blog non trovato!",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return DefaultResponse.errorNoMessage(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping
    public ResponseEntity<DefaultResponse> add(@RequestBody BlogRequest b){
        try{
            return DefaultResponse.successCustomMessage("Blog creata!",blogService.add(b),HttpStatus.CREATED);
        }catch (NotFoundException e) {
            return DefaultResponse.errorCustomMessage("Questo utente non esiste!",HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return DefaultResponse.errorNoMessage(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> update(@PathVariable int id,@RequestBody BlogRequest b){
        try{
            return DefaultResponse.successCustomMessage("Blog aggiornato!",blogService.update(id,b),HttpStatus.OK);
        }catch (NotFoundException e) {
            return DefaultResponse.errorCustomMessage(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return DefaultResponse.errorNoMessage(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> delete(@PathVariable int id){
        try{
            String message="Blog con id="+id+" eliminato";
            blogService.delete(id);
            return DefaultResponse.voidSuccess(message,HttpStatus.OK);
        }catch (NotFoundException e) {
            return DefaultResponse.errorCustomMessage("Blog non trovato!",HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return DefaultResponse.errorNoMessage(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}