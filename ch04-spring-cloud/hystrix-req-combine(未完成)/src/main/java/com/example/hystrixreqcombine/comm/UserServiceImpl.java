package com.example.hystrixreqcombine.comm;

import com.example.hystrixreqcombine.comm.UserInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl {


    public List<UserInfo> findAll(List<Integer> ids) {

        List<UserInfo> users = new ArrayList<>();
        for (Integer id : ids) {
            users.add(new UserInfo(id));
        }
        return users;
    }
}
