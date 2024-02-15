package com.example.BlogApp.controllers;

import com.cloudinary.Cloudinary;
import com.example.BlogApp.entities.Persona;
import com.example.BlogApp.exceptions.BadRequestException;
import com.example.BlogApp.exceptions.NotFoundException;
import com.example.BlogApp.requests.PersonaRequest;
import com.example.BlogApp.responses.DefaultResponse;
import com.example.BlogApp.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;


@RestController
@RequestMapping("api/persone")
public class PersonaController  {
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private JavaMailSenderImpl JMS;
    @Autowired
    private PersonaService personaService;
    @GetMapping
    public ResponseEntity<DefaultResponse> getAll(Pageable pageable){
        return DefaultResponse.successNoMessage(personaService.getAll(pageable), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getById(@PathVariable int id) throws NotFoundException {
        return DefaultResponse.successCustomMessage("Persona trovata!",personaService.findById(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<DefaultResponse> add(@RequestBody @Validated PersonaRequest p, BindingResult bR){
        if(bR.hasErrors()) throw new BadRequestException(bR.getAllErrors().stream().map(ObjectError::getObjectName).toList().toString());
        sendEmail(p.getEmail());
        return DefaultResponse.successCustomMessage("Persona creata!",personaService.add(p),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> update(@PathVariable int id,@RequestBody @Validated PersonaRequest p,BindingResult bR) throws NotFoundException {
        if(bR.hasErrors()) throw new BadRequestException(bR.getAllErrors().stream().map(ObjectError::getObjectName).toList().toString());
        return DefaultResponse.successCustomMessage("Persona aggiornata!",personaService.update(id,p),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> delete(@PathVariable int id) throws NotFoundException {
        String message="Persona con id="+id+" eliminata";
        personaService.delete(id);
        return DefaultResponse.voidSuccess(message,HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<DefaultResponse> uploadLogo(@PathVariable int id,@RequestParam("upload") MultipartFile file) throws IOException, NotFoundException {
        String url=(String) cloudinary.uploader().upload(file.getBytes(),new HashMap()).get("url");
        Persona p=personaService.uploadAvatar(id,url);
        return DefaultResponse.successCustomMessage("Immagine caricata",p,HttpStatus.OK);
    }
    public void sendEmail(String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Ciao!");
        message.setText("So che te l'ho già detto ma, ciao!");
        JMS.send(message);
    }
}
