package com.augurit.myproject.utils.Exception;


import net.sf.json.JSONObject;

public class AppException extends Exception{
	private static final long serialVersionUID = 1L;

	private int code;
	private String message;

	
	public AppException(){
		super();
	}

	public AppException(String message){
		this.message=message;
	}

	public AppException(int code,String message){
		this.code=code;
		this.message=message;
	}
	public AppException(String message,Throwable cause){
		super(message,cause);
		this.message=message;
	}

	public String toString(){
		JSONObject json = new JSONObject();
		if(getCode()!=0){
			json.put("code",getCode());
		}else{
			json.put("code",500);
		}
		json.put("message",getMessage());
		return json.toString();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
