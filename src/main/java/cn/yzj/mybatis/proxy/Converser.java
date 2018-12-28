package cn.yzj.mybatis.proxy;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;


public class Converser {
    /**
     * 这个方法，是将结果集中的每一条数据封装到一个JavaBean中，多条数据就对应多个JavaBean，再将多个JavaBean放到一个List集合中
     * @param set
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> converList(ResultSet set, Class clazz){
        List<T> beans = new ArrayList<T>();
        //1.遍历结果集
        try {
            //根据结果集元数据，获取结果集中的每一列的列名
            ResultSetMetaData metaData = set.getMetaData();
            int columnCount = metaData.getColumnCount();//获取总列数
            while (set.next()){
                //每次遍历，遍历出一条数据，每条数据就对应一个JavaBean对象
                T o = (T) clazz.newInstance();
                //获取每一列数据，根据列名获取
                //for循环遍历出每一列
                for(int i=1;i<=columnCount;i++){
                    String columnName = metaData.getColumnName(i);
                    Object value = set.getObject(columnName);//获取该列的值
                    //将该列的值存放到JavaBean中
                    //也就是调用JavaBean的set方法,使用内省机制
                    PropertyDescriptor descriptor = new PropertyDescriptor(columnName,clazz);
                    Method writeMethod = descriptor.getWriteMethod();//获取该属性的set方法
                    //调用set方法
                    writeMethod.invoke(o,value);
                }
                //经过这个for循环，我的JavaBean就设置好了
                //把JavaBean添加进list集合
                beans.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return beans;
    }
}
