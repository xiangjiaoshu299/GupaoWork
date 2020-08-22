package com.example.hystrixreqcombine.comm;

import lombok.Data;

@Data
public class UserInfo{

    int id;
    String name;

    public UserInfo(Integer id) {
        this.id = id;
        this.name = "张" + id + "丰";
    }
}