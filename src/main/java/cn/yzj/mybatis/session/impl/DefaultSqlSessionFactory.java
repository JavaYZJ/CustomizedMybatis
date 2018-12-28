/**
 * Copyright (C), 2018, 杨智杰
 * FileName: DefaultSqlSessionFactory
 * Author:   猪猪
 * Date:     2018/12/27 23:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.yzj.mybatis.session.impl;

import cn.yzj.mybatis.io.XMLConfigBuilder;
import cn.yzj.mybatis.po.Configuration;
import cn.yzj.mybatis.session.SqlSession;
import cn.yzj.mybatis.session.SqlSessionFactory;

import java.io.InputStream;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author 猪猪
 * @create 2018/12/27
 * @since 1.0.0
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private InputStream is;
    public void setIs(InputStream is) {
        this.is = is;
    }
    public SqlSession openSession() {
        DefaultSqlSession sqlSession = new DefaultSqlSession();
        Configuration config = XMLConfigBuilder.loadXmlConfiguration(is);
        sqlSession.setConfiguration(config);
        return sqlSession;
    }
}
