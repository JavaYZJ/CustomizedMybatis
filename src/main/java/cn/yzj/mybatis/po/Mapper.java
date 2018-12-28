/**
 * Copyright (C), 2018, 杨智杰
 * FileName: Mapper
 * Author:   猪猪
 * Date:     2018/12/28 13:36
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.yzj.mybatis.po;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author 猪猪
 * @create 2018/12/28
 * @since 1.0.0
 */
public class Mapper {

    private String id;
    private String resultType;
    private String sql;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
