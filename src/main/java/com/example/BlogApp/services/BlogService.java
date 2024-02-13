package com.example.BlogApp.services;

import com.example.BlogApp.exceptions.NotFoundException;
import com.example.BlogApp.models.Blog;
import com.example.BlogApp.models.Persona;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Getter
public class BlogService {
    @Autowired
    private PersonaService personaService;
    private List<Blog> listaBlog=new ArrayList<>();

    public Blog add(Blog b){
        listaBlog.add(b);
        return b;
    }
    public Blog findById(int id) throws NotFoundException {
        Optional<Blog> p=listaBlog.stream().filter(el->el.getId()==id).findFirst();
        if(p.isPresent()) return p.get();
        throw new NotFoundException("Blog non trovato!");
    }
    public Blog update(int id, Blog blog)throws NotFoundException {
        Blog b=findById(id);

        Persona p=personaService.findById(blog.getPersona().getId());
        b.setPersona(p);

        b.setCategoria(blog.getCategoria());
        b.setContenuto(blog.getContenuto());
        b.setCover(blog.getCover());
        b.setTempoDiLettura(blog.getTempoDiLettura());
        b.setTitolo(blog.getTitolo());

        return b;
    }
    public void delete(int id)throws NotFoundException{
        listaBlog.remove(findById(id));
    }
}
