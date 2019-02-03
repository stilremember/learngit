package com.augurit.myproject.utils.plugin.schedule;

import com.augurit.myproject.utils.plugin.schedule.form.ScheduleJobForm;
import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution
public class QuartzJobFactoryDisallowConcurrentExecution implements Job{
	public final static Logger log = Logger.getLogger(TaskUtils.class);
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJobForm schedule = (ScheduleJobForm) context.getMergedJobDataMap().get("scheduleJob");
		TaskUtils.invokMethod(schedule);
	}
	
	
}
