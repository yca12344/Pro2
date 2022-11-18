package com.example.springdb.mapper;

import com.example.springdb.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> findAll();

    @Insert("insert into user(username,password) values (#{username},#{password})")
    public int save(User user);

    @Delete("delete from user where id=#{id}")
    public int delete(int id);

    @Select("select * from user where id = #{id}")
    public User get(int id);

    @Update("update user set username=#{username},password=#{password} where id=#{id}")
    public int update(User user);

    @Select("select * from user where id = #{id}")
    public User selectUserById(int id);

    @Select("select * from user where username=#{username}")
    public User selectUserByName(String username);

    @Insert("insert into user(username,password)values(#{username},#{password})")
    public int insertUser(User user);
}
