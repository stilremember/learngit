package com.augurit.myproject.utils.plugin.schedule;

import com.augurit.myproject.utils.plugin.schedule.core.spring.SpringUtils;
import com.augurit.myproject.utils.plugin.schedule.form.ScheduleJobForm;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;

public class TaskUtils {
	public final static Logger log = Logger.getLogger(TaskUtils.class);
	
	/**
	 * 通过反射调用实体类中的方法
	 * */
	@SuppressWarnings("unchecked")
	public static void invokMethod(ScheduleJobForm form){
		Object object =null;
		Class clazz = null;
		if(StringUtils.isNotBlank(form.getSpringId())){
			object = SpringUtils.getBean(form.getBeanClass());
		}else if(StringUtils.isNotBlank(form.getBeanClass())){
			try {
				clazz = Class.forName(form.getBeanClass());
				object = clazz.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(object ==null){
			log.error("任务名称 = ["+form.getJobName()+"]-----未启动成功，请检查是否配置正确!!!");
			return;
		}
		clazz=object.getClass();
		Method method = null;
		try {
			if(StringUtils.isNotBlank(form.getMethodParams()) && form.getMethodParams().equals("1")){
				method=clazz.getDeclaredMethod(form.getMethodName(),String.class);
			}else{
				method=clazz.getDeclaredMethod(form.getMethodName());
			}
		} catch (NoSuchMethodException e) {
			log.error("任务名称 = [" + form.getJobName() + "]---------------未启动成功，方法名设置错误！！！");
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		if(method !=null){
			try {
				if(StringUtils.isNotBlank(form.getMethodParams()) && form.getMethodParams().equals("1")){
					method.invoke(object,new String[]{form.getParams()});
				}else{
					method.invoke(object);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("任务名称 = ["+ form.getJobName() +"]-------启动成功");
	}
	
	
}
