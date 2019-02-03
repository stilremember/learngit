package com.augurit.myproject.utils.plugin.schedule;

import com.augurit.myproject.utils.plugin.schedule.form.ScheduleJobForm;
import com.augurit.myproject.utils.plugin.schedule.service.impl.JobTaskService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class TaskTest {
	public final static Logger log = Logger.getLogger(TaskUtils.class);
	@Autowired
	private JobTaskService jobTaskService;
	
	public static void main(String[] args){
		for(int i=0;i<10;i++){
			log.info("run........"+i);
			System.out.println("run........"+i);
		}
	}
	
	public void a(String a){
		System.out.println("这里是执行有参方法，参数为："+a.toString());
	}
	public void a(){
		System.out.println("这里是执行无参方法");
	}
	
	@Test
	public void run(){
		ScheduleJobForm form = new ScheduleJobForm();
		form.setJobGroup("etl");
		form.setJobName("test01");
		form.setCreateTime(new Date());
		form.setBeanClass("com.augurit.awater_sw.utils.schedule.TaskTest");
		//JobTaskService jobService = new JobTaskService();
		try {
			jobTaskService.addJob(form);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	
}
