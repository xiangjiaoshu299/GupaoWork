package com.gupaoedu.domain;

import java.io.Serializable;
import java.util.List;


/**
 * 商户
 */
public class Shop implements Serializable{
    Integer sid; // 商家ID
    String name; // 商家名
    List<Integer> businessRange; // 商户经营业务范围

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getBusinessRange() {
        return businessRange;
    }

    public void setBusinessRange(List<Integer> businessRange) {
        this.businessRange = businessRange;
    }
}
