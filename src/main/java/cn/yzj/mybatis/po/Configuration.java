/**
 * Copyright (C), 2018, 杨智杰
 * FileName: Configuration
 * Author:   猪猪
 * Date:     2018/12/27 23:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.yzj.mybatis.po;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈功能简述〉<br> 
 * 〈配置对象〉
 *
 * @author 猪猪
 * @create 2018/12/27
 * @since 1.0.0
 */
public class Configuration {

    private String username;
    private String password;
    private String url;
    private String driver;
    //c3p0连接池对象
    private ComboPooledDataSource dataSource = new ComboPooledDataSource();
    //mapper映射文件里面的statment集合
    private Map<String, Mapper> mappers = new HashMap<String, Mapper>();

    /**
     * 获取连接池（c3p0）
     * @return
     */
    public ComboPooledDataSource getDataSources(){
        try {
            //为连接池对象设置属性
            dataSource.setDriverClass(driver);
            dataSource.setJdbcUrl(url);
            dataSource.setUser(username);
            dataSource.setPassword(password);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return dataSource;
    }

    /**
     * 通过连接池获取连接对象
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return getDataSources().getConnection();
    }
    public void setMappers(Map<String, Mapper> mappers) {
        //每次都是合并Map而不是替换
        this.mappers.putAll(mappers);
    }
    public Map<String, Mapper> getMappers() {
        return mappers;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }


}
