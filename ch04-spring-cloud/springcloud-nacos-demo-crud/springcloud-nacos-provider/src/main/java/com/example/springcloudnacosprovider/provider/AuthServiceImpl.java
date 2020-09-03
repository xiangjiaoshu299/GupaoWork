package com.example.springcloudnacosprovider.provider;

import com.example.springcloudnacosprovider.entity.AuthorExample;
import com.example.springcloudnacosprovider.mapper.AuthorMapper;
import org.apache.dubbo.config.annotation.Service;
import org.example.AuthorService;
import org.example.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(protocol = "dubbo")
public class AuthServiceImpl implements AuthorService {

    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public int add(Author author) {
        return authorMapper.insert(author);
    }

    @Override
    public int delete(int id) {
        return authorMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(int id, Author author) {
        author.setAuthorId(id);
        return authorMapper.updateByPrimaryKey(author);
    }

    @Override
    public List<Author> list() {
        AuthorExample ex = new AuthorExample();
        return authorMapper.selectByExample(ex);
    }

}
