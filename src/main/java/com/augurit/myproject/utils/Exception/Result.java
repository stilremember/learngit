package com.augurit.myproject.utils.Exception;

import net.sf.json.JSONObject;

public class Result {
    private int code;
    private Object data;
    private String message;

    public Result(){ }
    public Result(int code,Object data){
        this.code=code;
        this.data = data;
    }
    public Result(int code,String message){
        this.code=code;
        this.message = message;
    }
    public Result(int code,Object data,String message){
        this.code=code;
        this.data = data;
        this.message = message;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("code",code);
        json.put("data",data);
        json.put("message",message);
        return json.toString();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
