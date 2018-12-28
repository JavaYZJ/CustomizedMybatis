/**
 * Copyright (C), 2018, 杨智杰
 * FileName: Test1
 * Author:   猪猪
 * Date:     2018/12/27 22:08
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.yzj.mybatis;

import cn.yzj.mybatis.io.Resources;
import cn.yzj.mybatis.mapper.UserMapper;
import cn.yzj.mybatis.po.User;
import cn.yzj.mybatis.session.SqlSession;
import cn.yzj.mybatis.session.SqlSessionFactory;
import cn.yzj.mybatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * 〈功能简述〉<br> 
 * 〈mybatis框架使用测试〉
 *
 * @author 猪猪
 * @create 2018/12/27
 * @since 1.0.0
 */
public class Test1 {

    public static void main(String[] args) {
        //读取mybatis主配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //通过构建者设计模式创建工厂
        SqlSessionFactory factory = builder.build(is);
        //通过工厂模式创建sqlSession
        SqlSession sqlSession = factory.openSession();
        //通过动态代理获取对应mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        List<User> userList = mapper.getUserList();
        //遍历打印结果集
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
