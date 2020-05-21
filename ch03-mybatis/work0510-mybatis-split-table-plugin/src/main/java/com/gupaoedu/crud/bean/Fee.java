package com.gupaoedu.crud.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Author: ghf
 */
@Data
public class Fee {

    private int id;
    private Double feeAmount;
    private Date feeDate;
}
