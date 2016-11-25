package com.gemini.domain;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zychen
 * @time: 2016/11/2 16:8
 * @describion:
 */
public class User {

    @ApiModelProperty(value = "用户id", hidden = true)
    private int userId;

    @ApiModelProperty(value = "名称", required = true)
    private String name;

    @ApiModelProperty(value = "性别", readOnly = true)
    private String sex;

    private String address;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
