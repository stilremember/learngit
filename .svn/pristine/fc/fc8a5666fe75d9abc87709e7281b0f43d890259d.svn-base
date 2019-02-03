package com.augurit.myproject.utils.plugin.schedule.convert;


import com.augurit.myproject.utils.plugin.schedule.entity.ScheduleJob;
import com.augurit.myproject.utils.plugin.schedule.form.ScheduleJobForm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleJobConvertor {
	public static ScheduleJobForm convertVoToForm(ScheduleJob entity) {
		if(entity != null) {
			ScheduleJobForm form = new ScheduleJobForm();
			form.setId(entity.getId());
			form.setJobId(entity.getJobId());
			form.setCreateTime(entity.getCreateTime());
			form.setUpdateTime(entity.getUpdateTime());
			form.setJobName(entity.getJobName());
			form.setJobGroup(entity.getJobGroup());
			form.setJobStatus(entity.getJobStatus());
			form.setCronExpression(entity.getCronExpression());
			form.setDescription(entity.getDescription());
			form.setBeanClass(entity.getBeanClass());
			form.setIsCouncurrent(entity.getIsCouncurrent());
			form.setSpringId(entity.getSpringId());
			form.setMethodName(entity.getMethodName());
			form.setRemark(entity.getRemark());
			form.setMethodParams(entity.getMethodParams());
			form.setParams(entity.getParams());
			return form;
		}else
			return null;
	}
	
	public static void convertFormToVo(ScheduleJobForm form, ScheduleJob entity) {
		if(entity != null && form != null) {
			entity.setJobId(form.getJobId());
			entity.setCreateTime(form.getCreateTime());
			entity.setUpdateTime(form.getUpdateTime());
			if(form.getJobName() != null && form.getJobName().trim().length() > 0)
				entity.setJobName(form.getJobName().trim());
			if(form.getJobGroup() != null && form.getJobGroup().trim().length() > 0)
				entity.setJobGroup(form.getJobGroup().trim());
			if(form.getJobStatus() != null && form.getJobStatus().trim().length() > 0)
				entity.setJobStatus(form.getJobStatus().trim());
			if(form.getCronExpression() != null && form.getCronExpression().trim().length() > 0)
				entity.setCronExpression(form.getCronExpression().trim());
			if(form.getDescription() != null && form.getDescription().trim().length() > 0)
				entity.setDescription(form.getDescription().trim());
			if(form.getBeanClass() != null && form.getBeanClass().trim().length() > 0)
				entity.setBeanClass(form.getBeanClass().trim());
			if(form.getIsCouncurrent() != null && form.getIsCouncurrent().trim().length() > 0)
				entity.setIsCouncurrent(form.getIsCouncurrent().trim());
			if(form.getSpringId() != null && form.getSpringId().trim().length() > 0)
				entity.setSpringId(form.getSpringId().trim());
			if(form.getMethodName() != null && form.getMethodName().trim().length() > 0)
				entity.setMethodName(form.getMethodName().trim());
			if(form.getRemark() != null && form.getRemark().trim().length() > 0)
				entity.setRemark(form.getRemark().trim());
			if(form.getMethodParams() != null && form.getMethodParams().trim().length() > 0)
				entity.setMethodParams(form.getMethodParams().trim());
			if(form.getParams() != null && form.getParams().trim().length() > 0)
				entity.setParams(form.getParams().trim());
		}
	}
	
	public static List<ScheduleJobForm> convertVoListToFormList(List<ScheduleJob> scheduleJobList) {
		if(scheduleJobList != null && scheduleJobList.size() > 0) {
			List<ScheduleJobForm> scheduleJobFormList = new ArrayList();
			for(int i=0; i<scheduleJobList.size(); i++) {
				scheduleJobFormList.add(convertVoToForm(scheduleJobList.get(i)));
			}
			return scheduleJobFormList;
		}
		return null;
	}
	
	public static List<Map> convertVoListToMapList(List<ScheduleJob> scheduleJobList) {
		if(scheduleJobList != null && scheduleJobList.size() > 0) {
			List<Map> mapList = new ArrayList();
			for(int i=0; i<scheduleJobList.size(); i++) {
				ScheduleJob entity = scheduleJobList.get(i);
				Map map = new HashMap();

				map.put("jobId", entity.getJobId() == null ? "" : entity.getJobId().toString());
				map.put("createTime", entity.getCreateTime());
				map.put("updateTime", entity.getUpdateTime());
				map.put("jobName", entity.getJobName());
				map.put("jobGroup", entity.getJobGroup());
				map.put("jobStatus", entity.getJobStatus());
				map.put("cronExpression", entity.getCronExpression());
				map.put("description", entity.getDescription());
				map.put("beanClass", entity.getBeanClass());
				map.put("isCouncurrent", entity.getIsCouncurrent());
				map.put("springId", entity.getSpringId());
				map.put("methodName", entity.getMethodName());
				map.put("remark", entity.getRemark());
				map.put("methodParams", entity.getMethodParams());
				map.put("params", entity.getParams());
				
				mapList.add(map);
			}
			return mapList;
		}
		return null;
	}
	
	public static List<ScheduleJob> convertFormListToVoList(List<ScheduleJobForm> scheduleJobFormList) {
		if(scheduleJobFormList != null && scheduleJobFormList.size() > 0) {
			List<ScheduleJob> scheduleJobList = new ArrayList();
			for(int i=0; i<scheduleJobFormList.size(); i++) {
				ScheduleJob scheduleJob = new ScheduleJob();
				convertFormToVo(scheduleJobFormList.get(i), scheduleJob);
				scheduleJobList.add(scheduleJob);
			}
			return scheduleJobList;
		}
		return null;
	}
}