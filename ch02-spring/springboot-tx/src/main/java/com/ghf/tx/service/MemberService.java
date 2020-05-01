package com.ghf.tx.service;

import com.ghf.tx.dao.MemberMapper;
import com.ghf.tx.model.Member;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    public void txAddMember(){
        Member me = new Member();
        me.setName("ghf");
        me.setAge(18);
        me.setAddr("宝安");
        memberMapper.insert(me);

//        int a = 5 / 0;

        //第二次添加
        Member me2 = new Member();
        me2.setName("ggg");
        me2.setAge(18);
        me2.setAddr("赣州");
        memberMapper.insert(me2);
    }
}
