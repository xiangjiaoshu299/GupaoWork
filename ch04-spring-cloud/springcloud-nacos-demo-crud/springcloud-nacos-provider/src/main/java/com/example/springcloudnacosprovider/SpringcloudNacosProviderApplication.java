package com.example.springcloudnacosprovider;

import com.example.springcloudnacosprovider.mapper.AuthorMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;



@MapperScan("com.example.springcloudnacosprovider.mapper")
@SpringBootApplication
public class SpringcloudNacosProviderApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ac = SpringApplication.run(SpringcloudNacosProviderApplication.class, args);
        //这里发现，可以正常注入 AuthorMapper的bean
        //AuthorMapper authorMapper = ac.getBean(AuthorMapper.class);
        //System.out.println("authorMapper: " + authorMapper);
    }

}
