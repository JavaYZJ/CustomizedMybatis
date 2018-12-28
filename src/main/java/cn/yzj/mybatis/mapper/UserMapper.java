package cn.yzj.mybatis.mapper;

import cn.yzj.mybatis.po.User;
import cn.yzj.mybatis.utils.E3Result;

import java.util.List;

public interface UserMapper {

     List<User> getUserList();

     User getUserById(int id);

     E3Result deleteUserById(int id);

     E3Result updateUserById(User user);

}
