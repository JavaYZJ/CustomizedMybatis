/**
 * Copyright (C), 2018, 杨智杰
 * FileName: SqlSessionFactoryBuilder
 * Author:   猪猪
 * Date:     2018/12/27 22:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.yzj.mybatis.session;

import cn.yzj.mybatis.session.impl.DefaultSqlSessionFactory;

import java.io.InputStream; /**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author 猪猪
 * @create 2018/12/27
 * @since 1.0.0
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream is) {
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory();
        sqlSessionFactory.setIs(is);
        return sqlSessionFactory;
    }
}
