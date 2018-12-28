/**
 * Copyright (C), 2018, 杨智杰
 * FileName: Resources
 * Author:   猪猪
 * Date:     2018/12/27 22:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.yzj.mybatis.io;

import java.io.InputStream;

/**
 * 〈功能简述〉<br> 
 * 〈利用类加载器将配置文件转换为二进制流〉
 *
 * @author 猪猪
 * @create 2018/12/27
 * @since 1.0.0
 */
public class Resources {
    public static InputStream getResourceAsStream(String xmlPath) {
        //利用类加载器将配置文件转换为二进制流
        InputStream is = Resources.class.getClassLoader().getResourceAsStream(xmlPath);
        return is;
    }
}
