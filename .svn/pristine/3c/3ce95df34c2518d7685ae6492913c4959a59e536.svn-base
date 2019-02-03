package com.augurit.myproject.utils.plugin.schedule.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PS_SCHEDULEJOB")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ScheduleJob implements java.io.Serializable {
	private static final long serialVersionUID = -546885098407989821L;

	// 属性
	@Id
    @Column(name = "ID")
	@SequenceGenerator(name="SEQ_PS_SCHEDULEJOB", sequenceName = "SEQ_PS_SCHEDULEJOB",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PS_SCHEDULEJOB")
	private Long id;

	private Long jobId;
	
	private Date createTime;
	
	private Date updateTime;
	
	private String jobName;
	
	private String jobGroup;
	
	private String jobStatus;
	
	private String cronExpression;
	
	private String description;
	
	private String beanClass;
	
	private String isCouncurrent;
	
	private String springId;
	
	private String methodName;
	
	private String remark;
	
	private String methodParams;
	
	private String params;

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBeanClass() {
		return beanClass;
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
		return springId;
	}

	public void setSpringId(String springId) {
		this.springId = springId;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
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