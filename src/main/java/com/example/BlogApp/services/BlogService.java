package com.example.BlogApp.services;

import com.example.BlogApp.exceptions.NotFoundException;
import com.example.BlogApp.entities.Blog;
import com.example.BlogApp.requests.BlogRequest;
import com.example.BlogApp.entities.Persona;
import com.example.BlogApp.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
    @Autowired
    private PersonaService personaService;
    @Autowired
    private BlogRepository blogRepository;
    public Blog add(BlogRequest blog) throws NotFoundException {
        Blog b= new Blog();
        Persona p=personaService.findById(blog.getPersonaId());
        b.setPersona(p);
        return blogRepository.save(copy(blog,b));
    }
    public Blog findById(int id) throws NotFoundException {
        return blogRepository.findById(id).orElseThrow(()->new NotFoundException("Nessun blog trovato con id="+id));
    }
    public Page<Blog> getAll(Pageable pageable){
        return blogRepository.findAll(pageable);
    }
    public Blog update(int id, BlogRequest blog)throws NotFoundException {
        Blog b=findById(id);
        Persona p=personaService.findById(blog.getPersonaId());
        b.setPersona(p);
        return blogRepository.save(copy(blog,b));
    }
    public void delete(int id)throws NotFoundException{
        Blog b=findById(id);
        blogRepository.deleteById(id);
    }
    private Blog copy(BlogRequest blog, Blog b){
        b.setTitolo(blog.getTitolo());
        b.setCategoria(blog.getCategoria());
        b.setContenuto(blog.getContenuto());
        b.setTempoDiLettura(blog.getTempoDiLettura());
        b.setCover(blog.getCover());
        return b;
    }
}
