package com.augurit.myproject.utils.plugin.schedule;

import com.augurit.myproject.utils.plugin.schedule.form.ScheduleJobForm;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class QuartzJobFactory implements Job{
	public final Logger log = Logger.getLogger(this.getClass());

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJobForm scheduleJobForm = (ScheduleJobForm) context.getMergedJobDataMap().get("scheduleJob");
		TaskUtils.invokMethod(scheduleJobForm);
	}
	
	
	
	
}
