package com.gupaoedu.type;

import com.sun.deploy.util.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MyTypeHandler extends BaseTypeHandler<List<Integer>> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<Integer> nums, JdbcType jdbcType) throws SQLException {
        System.out.println(111);
        List<String> strList = new ArrayList<>();
        for (Integer num : nums) {
            strList.add(num.toString());
        }
        String str = StringUtils.join(strList, ",");

        preparedStatement.setString(i, str);
    }

    @Override
    public List<Integer> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        System.out.println(222);
        String res = resultSet.getString(s);
        String[] arr = res.split(",");
        List<Integer> list = new ArrayList<>();
        for (String s1 : arr) {
            Integer integer = new Integer(s1);
            list.add(integer);
        }
        return list;
    }

    @Override
    public List<Integer> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        System.out.println(333);
        return null;
    }

    @Override
    public List<Integer> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        System.out.println(444);
        return null;
    }
}