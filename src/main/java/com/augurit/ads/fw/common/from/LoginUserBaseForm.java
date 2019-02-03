//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.augurit.ads.fw.common.from;

public abstract class LoginUserBaseForm {
    private Long userId;
    private String loginName;
    private String userName;
    private Long defaultPosId;
    private String defaultPosName;
    private Long defaultOrgId;
    private String defaultOrgName;
    private String clientIp;

    public LoginUserBaseForm() {
    }

    public Long getDefaultPosId() {
        return this.defaultPosId;
    }

    public void setDefaultPosId(Long defaultPosId) {
        this.defaultPosId = defaultPosId;
    }

    public String getDefaultPosName() {
        return this.defaultPosName;
    }

    public void setDefaultPosName(String defaultPosName) {
        this.defaultPosName = defaultPosName;
    }

    public Long getDefaultOrgId() {
        return this.defaultOrgId;
    }

    public void setDefaultOrgId(Long defaultOrgId) {
        this.defaultOrgId = defaultOrgId;
    }

    public String getDefaultOrgName() {
        return this.defaultOrgName;
    }

    public void setDefaultOrgName(String defaultOrgName) {
        this.defaultOrgName = defaultOrgName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getClientIp() {
        return this.clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }
}
