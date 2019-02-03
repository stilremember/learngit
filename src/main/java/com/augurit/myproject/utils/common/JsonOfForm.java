package com.augurit.myproject.utils.common;

import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.augurit.myproject.sjcj.web.form.NwCjDataAfftypeAttachmentForm;
import com.augurit.myproject.sjcj.web.form.NwCjDataAttachmentForm;
import net.sf.json.JSONArray;


import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;
import org.apache.commons.lang.StringUtils;

public class JsonOfForm {
	
	public static Object jsonVoForm(String json,Class clazz){
		Object form=null;
		try {
			form=new Object();
			Gson gson = new Gson();
			JsonReader jsonRead = new JsonReader(new StringReader(json));
			jsonRead.setLenient(true);
			form=gson.toJson(json, clazz);
		} catch (JsonParseException e) {
			e.printStackTrace();
			return form;
		}
		return form;
	}
	
	/**
	 * 来自移动端的参数
	 * @param json 要封装的实体对象(时间是时间戳,要先转成Long类型 )
	 * */
	public static Object paramsTofromApp(net.sf.json.JSONObject json,Class clazz){
		try {
			// 实例
			Object obj=clazz.newInstance();
			if(json==null||json.size()<1){
				return obj;
			}
			Set<String> params = json.keySet();
			//得到字段
			Field[] field = clazz.getDeclaredFields();
			for(Field f : field){
				String name = f.getName();
				for(String nams : params){
					if(!json.get(nams).equals("null")&& StringUtils.isNotBlank(json.get(nams).toString())){
						if(name.equals(nams)){
							Object[] paramsValue = new Object[1];
							if(f.getType().equals(Long.class)){
								paramsValue[0]=Long.parseLong(json.get(name).toString());
							}
							if(f.getType().equals(Double.class)){
								paramsValue[0]= Double.parseDouble(json.get(name).toString());
							}
							if(f.getType().equals(Integer.class)){
								paramsValue[0]=Integer.parseInt(json.get(name).toString());		
							}
							if(f.getType().equals(String.class)){
								paramsValue[0]=json.get(name);
							}
							if(f.getType().equals(Date.class)){
								Long time=null;
								if(json.get(name).toString().length()>11)
									time = Long.parseLong((json.get(name).toString()));
								else
									time = Long.parseLong((json.get(name).toString()))*1000;
								paramsValue[0]=new Date(time);
							}
							StringBuffer buffer = new StringBuffer("set");
							buffer.append(name.substring(0,1).toUpperCase());
							buffer.append(name.substring(1));
							//Class[] paramsType = {f.getType()};
							//set方法
							Method method = clazz.getDeclaredMethod(buffer.toString(), f.getType());
							method.invoke(obj, paramsValue);
						}
					}
				}
			}
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 来自移动端的参数
	 * @param jsonArry 要封装的实体对象(时间是时间戳,要先转成Long类型 )
	 * */
	public static Object paramsToListApp(JSONArray jsonArry,Class clazz) throws Exception, IllegalAccessException{
		List<Object> list = new ArrayList<>();
		for(Object objs: jsonArry){
			net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(objs);
			// 实例
			Object obj=clazz.newInstance();
			if(json!=null||json.size()>1){
				Set<String> params = json.keySet();
				//得到字段
				Field[] field = clazz.getDeclaredFields();
				for(Field f : field){
					String name = f.getName();
					for(String nams : params){
						if(!json.get(nams).equals("null")&&json.get(nams)!=null){
							if(name.equals(nams)){
								Object[] paramsValue = new Object[1];
								if(f.getType().equals(Long.class)){
									paramsValue[0]=new Long(json.get(name).toString());
								}
								if(f.getType().equals(Double.class)){
									paramsValue[0]= Double.parseDouble(json.get(name).toString());
								}
								if(f.getType().equals(Integer.class)){
									paramsValue[0]=Integer.parseInt(json.get(name).toString());		
								}
								if(f.getType().equals(String.class)){
									paramsValue[0]=json.get(name);
								}
								if(f.getType().equals(Date.class)){
									Long time=null;
									if(json.get(name).toString().length()>11)
										time = Long.parseLong((json.get(name).toString()));
									else
										time = Long.parseLong((json.get(name).toString()))*1000;
									paramsValue[0]=new Date(time);
								}
								StringBuffer buffer = new StringBuffer("set");
								buffer.append(name.substring(0,1).toUpperCase());
								buffer.append(name.substring(1));
								//Class[] paramsType = {f.getType()};
								//set方法
								Method method = clazz.getDeclaredMethod(buffer.toString(), f.getType());
								method.invoke(obj, paramsValue);
							}
						}
					}
				}
				list.add(obj);
			}
		}
		return list;
	}
	/**
	 * List<NwCjDataAttachmentForm>集合时间倒序
	 * @param markTime(Long)
	 * */
	public static Comparator ageComparatorAttTime = new Comparator() {
		@Override
		public int compare(Object o1, Object o2) {
			NwCjDataAttachmentForm m1 = (NwCjDataAttachmentForm) o1;
			NwCjDataAttachmentForm m2 = (NwCjDataAttachmentForm) o2;
			if(m1.getAttTime() == null) return 1;
			if(m2.getAttTime() == null) return 1;
			return ( m1.getAttTime() < m2.getAttTime() ? -1 :
					(m1.getAttTime() ==(m2.getAttTime()) ? 0 : 1));
		}
	};
	public static Comparator ageComparatorAffAttAttTime = new Comparator() {
		@Override
		public int compare(Object o1, Object o2) {
            NwCjDataAfftypeAttachmentForm m1 = (NwCjDataAfftypeAttachmentForm) o1;
            NwCjDataAfftypeAttachmentForm m2 = (NwCjDataAfftypeAttachmentForm) o2;
			if(m1.getAttTime() == null) return 1;
			if(m2.getAttTime() == null) return 1;
			return ( m1.getAttTime() < m2.getAttTime() ? -1 :
					(m1.getAttTime() ==(m2.getAttTime()) ? 0 : 1));
		}
	};

}
