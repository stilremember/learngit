package com.augurit.myproject.utils.plugin.schedule;


import com.augurit.myproject.utils.plugin.schedule.service.IScheduleJobService;
import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SchedulerListener implements ServletContextListener {
	public final Logger log = Logger.getLogger(this.getClass());
	

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.
				getWebApplicationContext(sce.getServletContext());
		//开始定时器初始化方法，因为只需初始化 scheduleJobService类，就会自动调用里面的init初始化方法。
		IScheduleJobService scheduleJobService = (IScheduleJobService) webApplicationContext.getBean(IScheduleJobService.class);
		try {
			scheduleJobService.destroy();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("----------------初始化Spring定时器容器操作--------------");
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.
				getWebApplicationContext(sce.getServletContext());
		//开始定时器初始化方法，因为只需初始化 scheduleJobService类，就会自动调用里面的init初始化方法。
		IScheduleJobService scheduleJobService = (IScheduleJobService) webApplicationContext.getBean(IScheduleJobService.class);
		scheduleJobService.init();
		log.info("----------------初始化Spring定时器容器操作-成功--------------");
	}
	
}
