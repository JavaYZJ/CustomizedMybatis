/**
 * Copyright (C), 2018, 杨智杰
 * FileName: User
 * Author:   猪猪
 * Date:     2018/12/27 21:40
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package cn.yzj.mybatis.po;

import java.util.Date;

/**
 * 〈功能简述〉<br> 
 * 〈〉
 *
 * @author 猪猪
 * @create 2018/12/27
 * @since 1.0.0
 */
public class User {

    private int  id;
    private String username;
    private Date birthday;
    private String sex;
    private String address;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
