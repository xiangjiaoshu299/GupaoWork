package com.gupaoedu.crud.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Properties;

/**
 * @Author: ghf
 */

/**
 * 分表插件。注意：这里写完了还要去 主配置文件中注册
 */
@Intercepts({
        //拦截Executor类中，名字为query，参数为xx的方法
        @Signature(type = Executor.class, method = "query", args = {
                MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class
        })
})
public class SplitTableInterrupt implements Interceptor {

    Log log = LogFactory.getLog(SplitTableInterrupt.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("执行分表逻辑");

        Object[] invocateArgs = invocation.getArgs();

        MappedStatement mappedStatement = (MappedStatement) invocateArgs[0];
        Object queryArgs = invocateArgs[1];
        RowBounds rowBounds = (RowBounds) invocateArgs[2];

        //得到sql语句
        BoundSql boundSql = mappedStatement.getBoundSql(invocateArgs);
        String sql = boundSql.getSql();

        //------------自定义逻辑

        sql = diySql(sql, queryArgs);

        //--------------end

        //自定义sqlSource
        SqlSource sqlSource = new StaticSqlSource(mappedStatement.getConfiguration(), sql, boundSql.getParameterMappings());
        //修改原来的sqlSource（修改mappedStatement对象的sqlSource）
        Field sqlSourceField = MappedStatement.class.getDeclaredField("sqlSource");
        sqlSourceField.setAccessible(true);
        sqlSourceField.set(mappedStatement, sqlSource);

        //继续执行原来的方法
        return invocation.proceed();
    }

    private String diySql(String sql, Object queryArgs) {
        log.info("查询的sql: " + sql);

        String fromStr = "from t_fee";
        int hasTFee = sql.indexOf(fromStr);
        int nextWorldIndex = hasTFee + 10;
        if(nextWorldIndex > sql.length() - 1){
            return sql;
        }
        String nextWord = sql.substring(nextWorldIndex,nextWorldIndex + 1);

        String changedSql = sql;
        if(hasTFee != -1 && StringUtils.isEmpty(nextWord.trim())){

            if(queryArgs == null){
                return sql;
            }

            Class clazz = queryArgs.getClass();
            if(clazz != LocalDateTime.class){
                return sql;
            }

            LocalDateTime time = (LocalDateTime) queryArgs;
            int year = time.getYear();
            int month = time.getMonthValue();
            String monthStr = String.format("%02d", month);
            String suffix = year + monthStr;
            changedSql = sql.replace(fromStr, fromStr + "_" + suffix);

            log.info("分表后的sql: " + changedSql);
        }

        return changedSql;
    }

    @Override
    public Object plugin(Object target) {

        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
