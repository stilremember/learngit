package com.augurit.myproject.utils.plugin.schedule.service;


import com.augurit.ads.fw.common.service.ICrudService;
import com.augurit.myproject.utils.Exception.AppException;
import com.augurit.myproject.utils.plugin.schedule.form.ScheduleJobForm;
import org.quartz.SchedulerException;

import java.util.List;
import java.util.Map;

public interface IScheduleJobService extends ICrudService<ScheduleJobForm, Long> {
	void init();

	void destroy() throws SchedulerException;
	
	List<ScheduleJobForm> getAll();

	void changeJobStatus(ScheduleJobForm job, String status) throws SchedulerException;

	void updateJobCron(Long jobId, String cron) throws SchedulerException;

	void addTask(ScheduleJobForm form) throws IllegalAccessException, InstantiationException, AppException, NoSuchMethodException;

	List<ScheduleJobForm> getTaskList() throws SchedulerException;

	void toUpdate(Long id);

	void deleteJob(Long[] checkedIds) throws SchedulerException;

	void update(ScheduleJobForm form);

}