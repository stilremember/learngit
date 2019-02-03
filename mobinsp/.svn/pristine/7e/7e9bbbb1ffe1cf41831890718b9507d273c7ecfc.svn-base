package com.augurit.myproject.sjcj.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "NW_CHECK_RECORD")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NwCheckRecord implements java.io.Serializable {
	
	// 属性
	@Id
    @Column(name = "ID")
    @SequenceGenerator(name="SEQ_NW_CHECK_RECORD", sequenceName="SEQ_NW_CHECK_RECORD", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_NW_CHECK_RECORD")
	private Long id;
	
	private String checkPersonId;
	
	private String checkPerson;
	
	private String checkState;
	
	private Date checkTime;
	
	private String checkDesription;
	
	private String reportType;

	private Long reportId;  //外键
	
	private String usId;

	private String orgName;

	private String orgId;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getCheckPersonId() {
		return this.checkPersonId;
	}

	public void setCheckPersonId(String checkPersonId) {
		this.checkPersonId = checkPersonId;
	}
	public String getCheckPerson() {
		return this.checkPerson;
	}

	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}
	public String getCheckState() {
		return this.checkState;
	}

	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}
	public Date getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public String getCheckDesription() {
		return this.checkDesription;
	}

	public void setCheckDesription(String checkDesription) {
		this.checkDesription = checkDesription;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public String getUsId() {
		return this.usId;
	}

	public void setUsId(String usId) {
		this.usId = usId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}