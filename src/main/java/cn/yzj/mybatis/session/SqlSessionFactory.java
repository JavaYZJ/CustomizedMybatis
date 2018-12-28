/**
 * Copyright (C), 2018, 杨智杰
 * FileName: SqlSessionFactory
 * Author:   猪猪
 * Date:     2018/12/27 22:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.yzj.mybatis.session;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author 猪猪
 * @create 2018/12/27
 * @since 1.0.0
 */
public interface SqlSessionFactory {
     SqlSession openSession();
}
