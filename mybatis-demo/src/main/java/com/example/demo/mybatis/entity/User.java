package com.example.demo.mybatis.entity;


import com.example.demo.mybatis.annotation.NotBlank;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/10/11.
 */
public class User implements Serializable{

    private static final long serialVersionUID = -1369148652554782331L;

    private Integer id;
    @NotBlank(message = "用户名不能为null")
    private String username;
    private String password;
    @NotBlank
    private Integer age;
    @NotBlank
    private String phone;
    private List<Role> roleList;
    private Org org;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                '}';
    }
}
