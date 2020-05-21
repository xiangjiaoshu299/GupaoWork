package com.gupaoedu;

import com.gupaoedu.domain.Shop;
import com.gupaoedu.mapper.ShopMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * 作业内容:
 * 一个商户，在登记的时候需要注册它的经营范围。比如1手机，2电脑，3相机，4平板，在界面上是一个复选框(checkbox)。
 *
 * 在数据库保存的是用逗号分隔的字符串，例如“1,3,4”，而返回给程序的时候是整形数组List<Integer> {1,3,4}。
 *
 * 实现一个TypeHandler，可以把List<Integer>转换成数据库的varchar。把数据库的vachar转换成List<Integer>。
 *
 * 需要创建一张表、创建POJO、Mapper、xml映射器。
 */
@Slf4j
public class MyBatisTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void prepare() throws IOException {
        log.
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    @Test
    public void testSelectList() throws IOException {
        SqlSession session = sqlSessionFactory.openSession(); // ExecutorType.BATCH
        try {
            ShopMapper mapper = session.getMapper(ShopMapper.class);
            List<Shop> shopList = mapper.selectList();
            for (Shop shop : shopList) {
                List<Integer> authorIdList = shop.getBusinessRange();
                if (authorIdList != null) {
                    System.out.println(authorIdList.size());
                }
            }
            System.out.println("shopList.size(): " + shopList.size());

            for (Shop shop : shopList) {
                List<Integer> rangeList = shop.getBusinessRange();
                if (rangeList == null) {
                    continue;
                }
                System.out.println("rangeList.size()：" + rangeList.size());
            }
        } finally {
            session.close();
        }
    }

    /**
     * 插入
     * @throws IOException
     */
    @Test
    public void testInsert() throws IOException {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            ShopMapper mapper = session.getMapper(ShopMapper.class);
            Shop shop = new Shop();
            shop.setSid(1688);
            shop.setName("测试商家d");
            shop.setBusinessRange(Arrays.asList(1, 2, 3));

            System.out.println(mapper.insertOne(shop));
            session.commit();
        } finally {
            session.close();
        }
    }


}
