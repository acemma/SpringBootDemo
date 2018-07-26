package com.example.demo.mybatis.dao;

import com.example.demo.mybatis.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2017/10/11.
 */
@Mapper
public interface UserMapper {

    @Insert("insert into `t_user`(username,password,age,phone) values(#{username},#{password},#{age},#{phone})")
    int insertByObject(User user);

    @Select("select * from `t_user` where username like '%${username}%'")
    User findByUsername(@Param("username") String username);

    @Select("select * from `t_user`")
    List<User> findAll();

    @Update("update `t_user` set age=#{age} where username=#{username}")
    int update(User user);

    @Select("select * from `t_user`")
    Page<User> findByPage();

    @Select("select * from `t_user` where age<=#{age}")
    Page<User> findByAge(@Param("age") int age);

    @Select("select u.*,r.id as role_id,r.name as role_name,r.code as role_code from t_user u,t_role r,t_user_role ur " +
            "where u.id=#{id} and u.id = ur.user_id and r.id = ur.role_id")
    @ResultMap("userDetailMap")
    User findById(@Param("id") int id);

    @Select("select u.*,r.id as role_id,r.name as role_name,r.code as role_code,o.id as org_id,o.name as org_name,o.code as org_code,o.address as org_address" +
            " from t_user u,t_role r,t_org o,t_user_role ur,t_user_org uo " +
            "where u.id = #{id} and u.id = ur.user_id and r.id = ur.role_id " +
            "and u.id=uo.user_id and o.id = uo.org_id")
    @ResultMap("userResultMap")
    User findOne(@Param("id") int id);
}
