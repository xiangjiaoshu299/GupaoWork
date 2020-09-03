package com.example.springcloudnacosconsumer;

import org.apache.dubbo.config.annotation.Reference;
import org.example.AuthorService;
import org.example.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    @Reference
    private AuthorService authorService;
    //测试：http://localhost:8082/authors
    @GetMapping("authors")
    public List<Author> authors() {
        List<Author> authors = authorService.list();
        return authors;
    }
    //测试：postman请求，http://localhost:8082/authors param设置个name:ghf
    @PostMapping("author")
    public String addAuthor(String name){
        Author author = new Author();
        author.setAuthorName(name);
        int c = authorService.add(author);
        if(c > 0){
            return "success";
        }else{
            return "fail";
        }
    }
    //测试：http://localhost:8082/author/1002?authorName=Saomic
    @PutMapping("author/{id}")
    public String updateAuthor(@PathVariable int id, Author author){
        int c = authorService.update(id, author);
        if(c > 0){
            return "success";
        }else{
            return "fail";
        }
    }

    @DeleteMapping("author/{id}")
    public String delteAuthor(@PathVariable int id){
        int c = authorService.delete(id);
        if(c > 0){
            return "success";
        }else{
            return "fail";
        }
    }
}
