package com.gemini.domain;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author 章源辰
 * @time: 2016/11/2 16:8
 * @describion:
 */
public class Role {

    @ApiModelProperty(value = "角色id", hidden = true)
    private int roleId;

    @ApiModelProperty(value = "名称", required = true)
    private String roleName;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
