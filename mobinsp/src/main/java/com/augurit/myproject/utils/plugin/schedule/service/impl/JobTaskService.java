package com.augurit.myproject.utils.plugin.schedule.service.impl;

import com.augurit.myproject.utils.plugin.schedule.QuartzJobFactory;
import com.augurit.myproject.utils.plugin.schedule.QuartzJobFactoryDisallowConcurrentExecution;
import com.augurit.myproject.utils.plugin.schedule.form.ScheduleJobForm;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * 计划任务管理
 * @author HOWS
 * */
@Service("jobTaskService")
public class JobTaskService{
	public final Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	
	/**###################################################
	//保存数据库
	public void addTask(ScheduleJobForm job){
		job.setCreateTime(new Date());
		scheduleJobService.save(job);
	}
	
	public ScheduleJobForm getTaskById(Long id){
		return scheduleJobService.get(id);
	}*/
	public void changeStatus(ScheduleJobForm job, String status) throws SchedulerException{
		if(job == null){
			return ;
		}
		if("stop".equals(status)){
			deleteJob(job);
			job.setJobStatus(ScheduleJobForm.STATUS_NOT_RUNNING);
		}else if ("start".equals(status)) {
			job.setJobStatus(ScheduleJobForm.STATUS_RUNNING);
			addJob(job);
		}
		//scheduleJobService.save(job);
	}
	
	/**
	 * 更改任务 cron表达式
	 * @throws SchedulerException 
	 * 
	 * @throws SchedulerException
	 */
	public void updateCron(ScheduleJobForm jobForm, String cron) throws SchedulerException{
		if(jobForm == null){
			return;
		}
		jobForm.setCronExpression(cron);
		if(ScheduleJobForm.STATUS_RUNNING.equals(jobForm.getJobStatus())){
			updateJobCron(jobForm);
		}
	}
	
	/**###################  操作定时job(spring容器中) #################***/
	/**
	 * 添加任务(或者更新定时配置)
	 * @throws SchedulerException 
	 * */
	public void addJob(ScheduleJobForm form) throws SchedulerException{
		if(form==null || ScheduleJobForm.STATUS_RUNNING.equals(form.getJobStatus())){
			return ;
		}
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		log.debug(scheduler+"......新增");
		TriggerKey triggerKey = TriggerKey.triggerKey(form.getJobName(), form.getJobGroup());
		CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		if(cronTrigger == null){
			Class clazz = ScheduleJobForm.CONCURRENT_IS.equals(form.getIsCouncurrent())?
									QuartzJobFactory.class : QuartzJobFactoryDisallowConcurrentExecution.class;
			JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(form.getJobName(), form.getJobGroup()).build();
			jobDetail.getJobDataMap().put("scheduleJob", form);
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(form.getCronExpression());
			cronTrigger = TriggerBuilder.newTrigger().
								withIdentity(form.getJobName(),form.getJobGroup()).
										withSchedule(scheduleBuilder).build();
			scheduler.scheduleJob(jobDetail, cronTrigger);
		}else{
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(form.getCronExpression());
			cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
			scheduler.rescheduleJob(triggerKey, cronTrigger);
		}
	}
	
	/**
	 * 获取所有计划中的任务列表
	 * @throws SchedulerException 
	 * */
	public List<ScheduleJobForm> getAllJob() throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys= scheduler.getJobKeys(matcher);
		List<ScheduleJobForm> jobList = new ArrayList<ScheduleJobForm>();
		for (JobKey key : jobKeys) {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(key);
			for (Trigger trigger : triggers) {
				ScheduleJobForm job = new ScheduleJobForm();
				job.setJobName(key.getName());
				job.setJobGroup(key.getGroup());
				job.setDescription("触发器:"+trigger.getDescription());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setJobStatus(triggerState.name());
				if(trigger instanceof CronTrigger){
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
				}
				jobList.add(job);
			}
		}
		return jobList;
	}
	/**
	 * 获取所有正在运行的job
	 * */
	public List<ScheduleJobForm> getRunningJob(){
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			List<JobExecutionContext> excutionJobs = scheduler.getCurrentlyExecutingJobs();
			List<ScheduleJobForm> jobList = new ArrayList<ScheduleJobForm>(excutionJobs.size());
			for (JobExecutionContext jobs : excutionJobs) {
				ScheduleJobForm job = new ScheduleJobForm();
				JobDetail jobDetail = jobs.getJobDetail();
				Trigger trigger = jobs.getTrigger();
				JobKey jobKey = jobDetail.getKey();
				job.setJobName(jobKey.getName());
				job.setJobGroup(jobKey.getGroup());
				job.setDescription("触发器:"+trigger.getKey());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setJobStatus(triggerState.name());
				if(trigger instanceof CronTrigger){
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
				}
				jobList.add(job);
			}
			return jobList;
		} catch (SchedulerException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 暂停一个job
	 * @throws SchedulerException 
	 * */
	public void pasueJob(ScheduleJobForm scheduleJob) throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.pauseJob(jobKey);
	}
	
	
	/**
	 * 删除一个job
	 * */
	public void deleteJob(ScheduleJobForm job)throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
		scheduler.deleteJob(jobKey);
	}
	/**
	 * 恢复一个job
	 * @throws SchedulerException 
	 * */
	public void resumeJob(ScheduleJobForm job) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(job.getJobName(), job.getJobGroup());
		scheduler.resumeJob(jobKey);
	}
	/**
	 * 立即执行job
	 * @param scheduleJobForm
	 * @throws SchedulerException 
	 * */
	public void runAJobNow(ScheduleJobForm scheduleJobForm) throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJobForm.getJobName(), scheduleJobForm.getJobGroup());
		scheduler.triggerJob(jobKey);
		
	}
	/**
	 * 更新job时间表达式
	 * @throws SchedulerException 
	 * */
	public void updateJobCron(ScheduleJobForm scheduleJob) throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(),scheduleJob.getJobGroup());
		CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
		cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
		scheduler.rescheduleJob(triggerKey, cronTrigger);
	}
	
	
}
