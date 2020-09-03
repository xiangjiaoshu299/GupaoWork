package org.example;

import org.example.entity.Author;

import java.util.List;

public interface AuthorService {

    int add(Author author);
    int delete(int id);
    int update(int id, Author author);
    List<Author> list();
}
