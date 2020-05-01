package com.ghf.tx;


import com.ghf.tx.service.MemberService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@MapperScan("com.ghf.tx.dao")
@SpringBootApplication
public class TxApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(TxApplication.class, args);
    }
}
