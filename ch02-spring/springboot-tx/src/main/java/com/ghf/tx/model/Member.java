package com.ghf.tx.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * t_member
 * @author 
 */
@TableName("t_member")
@Data
public class Member implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer age;

    private String addr;

    private Date updatetime;

    private static final long serialVersionUID = 1L;
}