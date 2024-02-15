package com.example.BlogApp.controllers;

import com.example.BlogApp.exceptions.BadRequestException;
import com.example.BlogApp.exceptions.NotFoundException;
import com.example.BlogApp.requests.BlogRequest;
import com.example.BlogApp.responses.DefaultResponse;
import com.example.BlogApp.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @GetMapping
    public ResponseEntity<DefaultResponse> getAll(Pageable pageable){
        return DefaultResponse.successNoMessage(blogService.getAll(pageable), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getById(@PathVariable int id) throws NotFoundException {
        return DefaultResponse.successCustomMessage("Blog trovato!",blogService.findById(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<DefaultResponse> add(@RequestBody @Validated BlogRequest b, BindingResult bR) throws NotFoundException {
        if(bR.hasErrors()) throw new BadRequestException(bR.getAllErrors().stream().map(ObjectError::getObjectName).toList().toString());
        return DefaultResponse.successCustomMessage("Blog creata!",blogService.add(b),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> update(@PathVariable int id,@RequestBody @Validated BlogRequest b,BindingResult bR) throws NotFoundException {
        if(bR.hasErrors()) throw new BadRequestException(bR.getAllErrors().stream().map(ObjectError::getObjectName).toList().toString());
        return DefaultResponse.successCustomMessage("Blog aggiornato!",blogService.update(id,b),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> delete(@PathVariable int id) throws NotFoundException {
        String message="Blog con id="+id+" eliminato";
        blogService.delete(id);
        return DefaultResponse.voidSuccess(message,HttpStatus.OK);
    }
}