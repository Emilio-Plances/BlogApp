package com.example.BlogApp.services;

import com.example.BlogApp.exceptions.NotFoundException;
import com.example.BlogApp.entities.Persona;
import com.example.BlogApp.repositories.PersonaRepository;
import com.example.BlogApp.requests.PersonaRequest;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Getter
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;
    public Persona add(PersonaRequest persona){
        Persona p=new Persona();
        return personaRepository.save(copy(persona,p));
    }
    public Page<Persona> getAll(Pageable pageable){
        return personaRepository.findAll(pageable);
    }
    public Persona findById(int id) throws NotFoundException{
        return personaRepository.findById(id).orElseThrow(()->new NotFoundException("Nessuna persona trovata con id="+id));
    }
    public Persona update(int id, PersonaRequest persona)throws NotFoundException {
        Persona p=findById(id);
        return personaRepository.save(copy(persona,p));
    }
    public void delete(int id)throws NotFoundException{
        Persona p=findById(id);
        personaRepository.delete(p);
    }
    private Persona copy(PersonaRequest persona,Persona p){
        p.setNome(persona.getNome());
        p.setCognome(persona.getCognome());
        p.setEmail(persona.getEmail());
        p.setDataNascita(persona.getDataNascita());
        p.setAvatar("https://ui-avatars.com/api/?name="+p.getNome()+"+"+p.getCognome());
        return p;
    }
}
