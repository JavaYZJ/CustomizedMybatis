/**
 * Copyright (C), 2018, 杨智杰
 * FileName: XMLConfigBuilder
 * Author:   猪猪
 * Date:     2018/12/27 23:14
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.yzj.mybatis.io;

import cn.yzj.mybatis.po.Configuration;
import cn.yzj.mybatis.po.Mapper;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈功能简述〉<br> 
 * 〈读取mybatis主配置文件〉
 *
 * @author 猪猪
 * @create 2018/12/27
 * @since 1.0.0
 */
public class XMLConfigBuilder {
    /**
     * 读取主配置文件
     * @param inputStream
     * @return
     */
    public static Configuration loadXmlConfiguration(InputStream inputStream){
        //使用dom4j和xpath解析主配置文件
        SAXReader reader = new SAXReader();
        Configuration config = new Configuration();
        try {
            //将主配置文件二进制流传入，得到document对象
            Document document = reader.read(inputStream);
            //获取property节点集合（用于生成连接对象的四大参数）
            List<Element> list = document.selectNodes("//property");
            for (Element element : list) {
                String name = element.attributeValue("name");
                String value = element.attributeValue("value");
                if (name.equals("username")) config.setUsername(value);
                if (name.equals("driver")) config.setDriver(value);
                if (name.equals("password")) config.setPassword(value);
                if (name.equals("url")) config.setUrl(value);
            }
            //读取主配置文件下的所有mapper映射文件
            List<Element> listMapper = document.selectNodes("//mapper");
            for (Element element : listMapper) {
                //遍历每个mapper标签，获取resource属性（mapper映射文件的全限类名）
                String resource = element.attributeValue("resource");
                //使用dom4j和xpath解析mapper映射文件
                Map<String, Mapper> loadMapper = loadMapper(resource);
                config.setMappers(loadMapper);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return config;
    }

    /**
     * 使用dom4j和xpath解析mapper映射文件
     * @param resource
     * @return
     */
    private static Map<String, Mapper> loadMapper(String resource) {
        Map<String,Mapper> mappers = new HashMap<String, Mapper>();
        //利用类加载器将配置文件转换为二进制流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(inputStream);
            //获取根节点对象
            Element rootElement = document.getRootElement();
            //获取根节点的namespace属性值
            String namespace = rootElement.attributeValue("namespace");
            //获取根节点下所有select节点
            List<Element> selectNodes = rootElement.selectNodes("//select");
            //遍历，获取每个select节点的id、resultType以及sql语句等值
            for (Element selectNode : selectNodes) {
                String id = selectNode.attributeValue("id");
                String resultType = selectNode.attributeValue("resultType");
                String sql = selectNode.getTextTrim();
                //用一个mapper对象存储以上的值
                Mapper mapper = new Mapper();
                mapper.setId(id);
                mapper.setResultType(resultType);
                mapper.setSql(sql);
                //一个mapper映射文件可以有多个select（statment），不同映射文件里，select的id可以相同
                //所以将namespace+"."+id作为唯一标志，用于区分，并存入map集合里
                mappers.put(namespace+"."+id,mapper);
            }
            return mappers;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    @Test
    public void test(){

        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        Configuration config = loadXmlConfiguration(is);
        Connection connection = null;
        try {
            connection = config.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(connection);
    }
}
