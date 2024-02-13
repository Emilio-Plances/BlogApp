package com.example.BlogApp.services;

import com.example.BlogApp.exceptions.NotFoundException;
import com.example.BlogApp.models.Persona;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Getter
public class PersonaService {
    private List<Persona> listaPersone=new ArrayList<>();

    public Persona add(Persona p){
        p.setAvatar("https://ui-avatars.com/api/?name="+p.getNome()+"+"+p.getCognome());
        listaPersone.add(p);
        return p;
    }
    public Persona findById(int id) throws NotFoundException{
        Optional<Persona> p=listaPersone.stream().filter(el->el.getId()==id).findFirst();
        if(p.isPresent()) return p.get();
        throw new NotFoundException("Persona non trovata!");
    }
    public Persona update(int id, Persona persona)throws NotFoundException {
        Persona p=findById(id);
        p.setNome(persona.getNome());
        p.setCognome(persona.getCognome());
        p.setEmail(persona.getEmail());
        p.setAvatar(persona.getAvatar());
        p.setDataNascita(persona.getDataNascita());
        return p;
    }
    public void delete(int id)throws NotFoundException{
        listaPersone.remove(findById(id));
    }
}
