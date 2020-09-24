package com.ghf.tx.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void add(){
        int count = memberService.txAddMember();
        log.info("插入成功的记录数：" + count);

        /**
         * 测试结果：
         * 插入成功的记录数：2
         *
         * 放开txAddMember()函数里面除0的非法操作的一行代码：
         * （1）java.lang.ArithmeticException: / by zero
         * （2）数据库未插入新的记录
         */
    }
}
