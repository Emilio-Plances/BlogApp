package com.example.BlogApp.controllers;

import com.example.BlogApp.exceptions.NotFoundException;
import com.example.BlogApp.models.Blog;
import com.example.BlogApp.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @ResponseStatus(HttpStatus.OK)
    public List<Blog> getAll(){
        return blogService.getListaBlog();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Blog> getById(@PathVariable int id){
        try {
            Blog b = blogService.findById(id);
            return new ResponseEntity<Blog>(b, HttpStatus.OK);
        }
        catch (NotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<Blog> save(@RequestBody Blog blog){
        Blog b=blogService.add(blog);
        return new ResponseEntity<>(b,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Blog> putUpdate(@PathVariable int id, @RequestBody Blog blog){
        try {
            Blog b=blogService.update(id,blog);
            return new ResponseEntity<>(b,HttpStatus.OK);
        }
        catch (NotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Blog> delete(@PathVariable int id){
        try {
            blogService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (NotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
