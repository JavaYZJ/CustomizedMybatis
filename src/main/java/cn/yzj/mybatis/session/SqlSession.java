/**
 * Copyright (C), 2018, 杨智杰
 * FileName: SqlSession
 * Author:   猪猪
 * Date:     2018/12/27 22:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.yzj.mybatis.session;

import java.util.List;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author 猪猪
 * @create 2018/12/27
 * @since 1.0.0
 */
public interface SqlSession {
      <T> T getMapper(Class<?> mapperClass);
      <T> List<T> selectList(String Key);
      void close();
}
