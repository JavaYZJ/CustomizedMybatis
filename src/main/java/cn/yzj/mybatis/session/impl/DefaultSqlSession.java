/**
 * Copyright (C), 2018, 杨智杰
 * FileName: DefaultSqlSession
 * Author:   猪猪
 * Date:     2018/12/27 23:12
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.yzj.mybatis.session.impl;

import cn.yzj.mybatis.po.Configuration;
import cn.yzj.mybatis.po.Mapper;
import cn.yzj.mybatis.proxy.Converser;
import cn.yzj.mybatis.proxy.MapperProxyFactory;
import cn.yzj.mybatis.session.SqlSession;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author 猪猪
 * @create 2018/12/27
 * @since 1.0.0
 */
public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
    /**
     * 使用动态代理技术获取代理对象
     * @param mapperClass
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<?> mapperClass) {
        //newProxyInstance()里面需要传入三个参数:
        // classLoader类加载器,
        // 代理对象实现的接口的字节码对象的数组，
        // InvocationHandler接口的实现类对象
        return (T) Proxy.newProxyInstance(mapperClass.getClassLoader(),new Class[]{mapperClass},new MapperProxyFactory(this));
    }
    /**
     * 执行查询多条数据的Sql语句
     * @param Key
     * @param <T>
     * @return
     */
    public <T> List<T> selectList(String Key){
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try {
            //1.获取连接对象
            connection = configuration.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //2.获取SQl语句
        Map<String, Mapper> mappers = configuration.getMappers();
        Mapper mapper = mappers.get(Key);
        String sql = mapper.getSql();
        String resultType = mapper.getResultType();
        try {
            //3.预编译SQL语句
            pstm = connection.prepareStatement(sql);
            //4.执行Sql语句
            resultSet = pstm.executeQuery();
            //5.将结果集封装到JavaBean里面----->反射技术
            List<T> list = Converser.converList(resultSet, Class.forName(resultType));
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void close() {

    }
}
