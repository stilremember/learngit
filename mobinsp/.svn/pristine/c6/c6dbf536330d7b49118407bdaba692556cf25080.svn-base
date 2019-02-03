package com.augurit.myproject.utils.plugin.kettle.entity;

import org.pentaho.di.core.database.DatabaseMeta;

/**
 * 数据库连接配置
 * */
public class DataSource   {
    private Long id;
    private String name;  // 储存别名
    private String type;  // 类型（oracle或者mysql）
    private String mode;  // 连接方式
    private String host;  // ip
    private String sid;   // 服务名
    private String port;  // 端口
    private String username; //用户名
    private String password; // 密码

    public DataSource(Long id, String name, String type, String mode, String host, String sid, String port, String username, String password) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.mode = mode;
        this.host = host;
        this.sid = sid;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
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

    //测试链接数据库方法
    public String testConnection(){
        DatabaseMeta ci =new DatabaseMeta(name,type,mode,host,sid,port,username,password);
        return ci.testConnection();// 测试链接
    }
    //连接名、连接数据库、连接方式、地址、登陆方式、端口、用户名、密码
    public DatabaseMeta getDataBanesMeta(){
        DatabaseMeta ci =new DatabaseMeta(name,type,mode,host,sid,port,username,password);
        return ci;
    }
}
