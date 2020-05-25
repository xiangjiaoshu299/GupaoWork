package com.ghf.mybatisdiy.frame;

/**
 * @Author: ghf
 */
public class GPSqlSessioin {

    private GpConfiguration configuration;
    private GpExecutor executor;

    public GPSqlSessioin(GpConfiguration configuration, GpExecutor gpExecutor) {
        this.configuration = configuration;
        this.executor = gpExecutor;
    }

    public <T> T getMapper(Class<T> clazz) {
        return configuration.getMapper(clazz, this);//把1个sqlSession对象传过去，因为configuration创建的代理对象中，有1个sqlSession对象
    }

    public <T> T selectOne(String methodFullName, Object parameter) {
        String sql = GpConfiguration.sqlMapping.getString(methodFullName);
        if (sql != null && sql != "") {
            return executor.query(sql, parameter);
        }

        return null;
    }
}
