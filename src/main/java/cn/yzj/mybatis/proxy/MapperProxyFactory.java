/**
 * Copyright (C), 2018, 杨智杰
 * FileName: MapperProxyFactory
 * Author:   猪猪
 * Date:     2018/12/28 14:50
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.yzj.mybatis.proxy;

import cn.yzj.mybatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 〈功能简述〉<br> 
 * 〈InvocationHandler接口的实现类〉
 *
 * @author 猪猪
 * @create 2018/12/28
 * @since 1.0.0
 */
public class MapperProxyFactory implements InvocationHandler {

    private SqlSession sqlSession;

    public  MapperProxyFactory(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //如果返回值是一个List集合才执行如下代码
        Class<?> returnType = method.getReturnType();
        if (returnType == List.class) {
            //代理对象调用任何方法，都会在这执行
            //真正执行Sql语句
            //执行Sql语句的工作:sqlSession对象的selectList(),也就是说咱在这要调用那个方法
            //key是"Mapper接口的全限定名"+"."+方法名
            String methodName = method.getName();//方法名
            Class<?> clazz = method.getDeclaringClass();
            String clazzName = clazz.getName();//获取接口的全限定名
            String key = clazzName + "." + methodName;
            List<Object> list = sqlSession.selectList(key);
            return list;
        }else {
            //其他情况日后考虑
            return null;
        }
    }
}
