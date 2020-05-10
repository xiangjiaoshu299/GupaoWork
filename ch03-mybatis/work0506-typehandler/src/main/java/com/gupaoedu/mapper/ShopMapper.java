package com.gupaoedu.mapper;


import com.gupaoedu.domain.Shop;

import java.util.List;

public interface ShopMapper {


    public List<Shop> selectList();


    public int insertOne(Shop shop);


}
