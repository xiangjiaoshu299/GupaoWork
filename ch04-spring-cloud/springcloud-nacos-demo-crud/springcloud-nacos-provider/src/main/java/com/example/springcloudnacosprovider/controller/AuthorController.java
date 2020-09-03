package com.example.springcloudnacosprovider.controller;

import org.example.entity.Author;
import com.example.springcloudnacosprovider.entity.AuthorExample;
import com.example.springcloudnacosprovider.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorMapper authorMapper;

    //服务提供者自己测试，是否能正常使用mybatis
    @GetMapping("testAuthors")
    public List<Author> authors() {
        AuthorExample ex = new AuthorExample();
        List<Author> authors = authorMapper.selectByExample(ex);
        return authors;
    }
}
