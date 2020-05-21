package com.gupaoedu.crud.dao;

import com.gupaoedu.crud.bean.Fee;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @Author: ghf
 */
public interface FeeMapper {

    @Select("select * from t_fee_202001 where fee_date = #{feeDate}")
    List<Fee> selectList202001(LocalDateTime feeDate);

    @Select("select * from t_fee where fee_date = #{feeDate}")
    List<Fee> selectList(LocalDateTime dateTime);
}
