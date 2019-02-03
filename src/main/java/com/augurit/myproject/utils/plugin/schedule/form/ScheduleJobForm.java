package com.augurit.myproject.utils.plugin.schedule.form;

import java.util.Date;

public class ScheduleJobForm {
	// 运行状态
	public static final String STATUS_RUNNING = "1";
	// 非运行状态
	public static final String STATUS_NOT_RUNNING = "0";
	// 并发
	public static final String CONCURRENT_IS = "1";
	// 不并发
	public static final String CONCURRENT_NOT = "0";

	private Long id;
	// id
	private Long jobId;
	// 创建时间
	private Date createTime;
	// 更新时间
	private Date updateTime;
	// 名字
	private String jobName;
	// 分组
	private String jobGroup;
	// 状态
	private String jobStatus;
	// 表达式
	private String cronExpression;
	// 描述
	private String description;
	// 执行类路径
	private String beanClass;
	// 是否并发
	private String isCouncurrent;
	// spring中注册bean的id
	private String springId;
	// 执行类中指定方法
	private String methodName;
	// 备注
	private String remark;
	
	private String methodParams;
	
	private String params;

	public Long getJobId() {
		return this.jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroup() {
		return this.jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	public String getJobStatus() {
		return this.jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public String getCronExpression() {
		return this.cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getBeanClass() {
		return this.beanClass;
	}

	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}

	public String getIsCouncurrent() {
		return isCouncurrent;
	}

	public void setIsCouncurrent(String isCouncurrent) {
		this.isCouncurrent = isCouncurrent;
	}

	public String getSpringId() {
		return this.springId;
	}

	public void setSpringId(String springId) {
		this.springId = springId;
	}
	public String getMethodName() {
		return this.methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMethodParams() {
		return methodParams;
	}

	public void setMethodParams(String methodParams) {
		this.methodParams = methodParams;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}