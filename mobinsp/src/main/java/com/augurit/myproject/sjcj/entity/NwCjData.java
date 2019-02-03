package com.augurit.myproject.sjcj.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "NW_CJ_DATA")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NwCjData implements java.io.Serializable {
	
	// 属性
	@Id
    @Column(name = "ID")
    @SequenceGenerator(name="SEQ_NW_CJ_DATA", sequenceName="SEQ_NW_CJ_DATA", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_NW_CJ_DATA")
	private Long id;
	
	private String markPersonId;
	
	private String markPerson;
	
	private Long userId;
	
	private Date markTime;
	
	private Double x;
	
	private Double y;
	
	private Double coox;
	
	private Double cooy;
	
	private String parentOrgName;
	
	private String parentOrgId;
	
	private Date updateTime;
	
	private String reportType;
	
	private String state;
	
	private String addr;
	
	private String road;
	
	private String currentArea;
	
	private String currentTown;
	
	private String currentVillage;
	
	private String description;
	
	private String yjAttrOne;
	
	private String yjAttrTwo;
	
	private String yjAttrThree;
	
	private String yjAttrFour;
	
	private String yjAttrFive;
	
	private String yjComb;
	
	private String cmbNumber;
	
	private String cmbName;
	
	private Double cmbDesWater;
	
	private String cmbTreatProce;
	
	private Date cmbRunTime;
	
	private String cmbStandard;
	
	private String cmbStreetPeople;
	
	private String cmbStreetPeoplePhone;
	
	private String cmbVillPeople;
	
	private String cmbVillPeoplePhone;
	
	private String cmbMaUnit;
	
	private String cmbMaUnitPeople;
	
	private String cmbMaUnitPhone;
	
	private String cmbCovArea;
	
	private String cmbIsDyn;
	
	private String remark;
	private String collectType;
	
	private Long objectId;
	private String isAddFeature;
	private String checkState;
	private String checkPersonId;
	private String checkPerson;
	private Date checkTime;
	private String checkDesription;
	
	private String directOrgId;
	private String directOrgName;
	private Long yjCombId;
	private String checkType;
	
	private String teamOrgId;
	private String teamOrgName;

	private String teamCheck;
	private String parentCheck;
	private String topCheck;
	private String checkOrgName;

	private String burgOrgName;
	private String burgOrgId;

	private String modifyPersonPc;
	private String modifyPersonId;

	private Long cjid;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getMarkPersonId() {
		return this.markPersonId;
	}

	public void setMarkPersonId(String markPersonId) {
		this.markPersonId = markPersonId;
	}
	public String getMarkPerson() {
		return this.markPerson;
	}

	public void setMarkPerson(String markPerson) {
		this.markPerson = markPerson;
	}
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getMarkTime() {
		return this.markTime;
	}

	public void setMarkTime(Date markTime) {
		this.markTime = markTime;
	}
	public Double getX() {
		return this.x;
	}

	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return this.y;
	}

	public void setY(Double y) {
		this.y = y;
	}
	public Double getCoox() {
		return this.coox;
	}

	public void setCoox(Double coox) {
		this.coox = coox;
	}
	public Double getCooy() {
		return this.cooy;
	}

	public void setCooy(Double cooy) {
		this.cooy = cooy;
	}
	public String getParentOrgName() {
		return this.parentOrgName;
	}

	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}
	public String getParentOrgId() {
		return this.parentOrgId;
	}

	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getReportType() {
		return this.reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getRoad() {
		return this.road;
	}

	public void setRoad(String road) {
		this.road = road;
	}
	public String getCurrentArea() {
		return this.currentArea;
	}

	public void setCurrentArea(String currentArea) {
		this.currentArea = currentArea;
	}
	public String getCurrentTown() {
		return this.currentTown;
	}

	public void setCurrentTown(String currentTown) {
		this.currentTown = currentTown;
	}
	public String getCurrentVillage() {
		return this.currentVillage;
	}

	public void setCurrentVillage(String currentVillage) {
		this.currentVillage = currentVillage;
	}
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getYjAttrOne() {
		return this.yjAttrOne;
	}

	public void setYjAttrOne(String yjAttrOne) {
		this.yjAttrOne = yjAttrOne;
	}
	public String getYjAttrTwo() {
		return this.yjAttrTwo;
	}

	public void setYjAttrTwo(String yjAttrTwo) {
		this.yjAttrTwo = yjAttrTwo;
	}
	public String getYjAttrThree() {
		return this.yjAttrThree;
	}

	public void setYjAttrThree(String yjAttrThree) {
		this.yjAttrThree = yjAttrThree;
	}
	public String getYjAttrFour() {
		return this.yjAttrFour;
	}

	public void setYjAttrFour(String yjAttrFour) {
		this.yjAttrFour = yjAttrFour;
	}
	public String getYjAttrFive() {
		return this.yjAttrFive;
	}

	public void setYjAttrFive(String yjAttrFive) {
		this.yjAttrFive = yjAttrFive;
	}
	public String getYjComb() {
		return this.yjComb;
	}

	public void setYjComb(String yjComb) {
		this.yjComb = yjComb;
	}
	public String getCmbNumber() {
		return this.cmbNumber;
	}

	public void setCmbNumber(String cmbNumber) {
		this.cmbNumber = cmbNumber;
	}
	public String getCmbName() {
		return this.cmbName;
	}

	public void setCmbName(String cmbName) {
		this.cmbName = cmbName;
	}
	public Double getCmbDesWater() {
		return this.cmbDesWater;
	}

	public void setCmbDesWater(Double cmbDesWater) {
		this.cmbDesWater = cmbDesWater;
	}
	public String getCmbTreatProce() {
		return this.cmbTreatProce;
	}

	public void setCmbTreatProce(String cmbTreatProce) {
		this.cmbTreatProce = cmbTreatProce;
	}
	public Date getCmbRunTime() {
		return this.cmbRunTime;
	}

	public void setCmbRunTime(Date cmbRunTime) {
		this.cmbRunTime = cmbRunTime;
	}
	public String getCmbStandard() {
		return this.cmbStandard;
	}

	public void setCmbStandard(String cmbStandard) {
		this.cmbStandard = cmbStandard;
	}
	public String getCmbStreetPeople() {
		return this.cmbStreetPeople;
	}

	public void setCmbStreetPeople(String cmbStreetPeople) {
		this.cmbStreetPeople = cmbStreetPeople;
	}
	public String getCmbStreetPeoplePhone() {
		return this.cmbStreetPeoplePhone;
	}

	public void setCmbStreetPeoplePhone(String cmbStreetPeoplePhone) {
		this.cmbStreetPeoplePhone = cmbStreetPeoplePhone;
	}
	public String getCmbVillPeople() {
		return this.cmbVillPeople;
	}

	public void setCmbVillPeople(String cmbVillPeople) {
		this.cmbVillPeople = cmbVillPeople;
	}
	public String getCmbVillPeoplePhone() {
		return this.cmbVillPeoplePhone;
	}

	public void setCmbVillPeoplePhone(String cmbVillPeoplePhone) {
		this.cmbVillPeoplePhone = cmbVillPeoplePhone;
	}
	public String getCmbMaUnit() {
		return this.cmbMaUnit;
	}

	public void setCmbMaUnit(String cmbMaUnit) {
		this.cmbMaUnit = cmbMaUnit;
	}
	public String getCmbMaUnitPeople() {
		return this.cmbMaUnitPeople;
	}

	public void setCmbMaUnitPeople(String cmbMaUnitPeople) {
		this.cmbMaUnitPeople = cmbMaUnitPeople;
	}
	public String getCmbMaUnitPhone() {
		return this.cmbMaUnitPhone;
	}

	public void setCmbMaUnitPhone(String cmbMaUnitPhone) {
		this.cmbMaUnitPhone = cmbMaUnitPhone;
	}
	public String getCmbCovArea() {
		return this.cmbCovArea;
	}

	public void setCmbCovArea(String cmbCovArea) {
		this.cmbCovArea = cmbCovArea;
	}
	public String getCmbIsDyn() {
		return this.cmbIsDyn;
	}

	public void setCmbIsDyn(String cmbIsDyn) {
		this.cmbIsDyn = cmbIsDyn;
	}
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCollectType() {
		return collectType;
	}

	public void setCollectType(String collectType) {
		this.collectType = collectType;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public String getIsAddFeature() {
		return isAddFeature;
	}

	public void setIsAddFeature(String isAddFeature) {
		this.isAddFeature = isAddFeature;
	}

	public String getCheckState() {
		return checkState;
	}

	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}

	public String getCheckPersonId() {
		return checkPersonId;
	}

	public void setCheckPersonId(String checkPersonId) {
		this.checkPersonId = checkPersonId;
	}

	public String getCheckPerson() {
		return checkPerson;
	}

	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getCheckDesription() {
		return checkDesription;
	}

	public void setCheckDesription(String checkDesription) {
		this.checkDesription = checkDesription;
	}

	public String getDirectOrgId() {
		return directOrgId;
	}

	public void setDirectOrgId(String directOrgId) {
		this.directOrgId = directOrgId;
	}

	public String getDirectOrgName() {
		return directOrgName;
	}

	public void setDirectOrgName(String directOrgName) {
		this.directOrgName = directOrgName;
	}

	public Long getYjCombId() {
		return yjCombId;
	}

	public void setYjCombId(Long yjCombId) {
		this.yjCombId = yjCombId;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getTeamOrgId() {
		return teamOrgId;
	}

	public void setTeamOrgId(String teamOrgId) {
		this.teamOrgId = teamOrgId;
	}

	public String getTeamOrgName() {
		return teamOrgName;
	}

	public void setTeamOrgName(String teamOrgName) {
		this.teamOrgName = teamOrgName;
	}

	public String getTeamCheck() {
		return teamCheck;
	}

	public void setTeamCheck(String teamCheck) {
		this.teamCheck = teamCheck;
	}

	public String getParentCheck() {
		return parentCheck;
	}

	public void setParentCheck(String parentCheck) {
		this.parentCheck = parentCheck;
	}

	public String getTopCheck() {
		return topCheck;
	}

	public void setTopCheck(String topCheck) {
		this.topCheck = topCheck;
	}

	public String getCheckOrgName() {
		return checkOrgName;
	}

	public void setCheckOrgName(String checkOrgName) {
		this.checkOrgName = checkOrgName;
	}

	public String getModifyPersonPc() {
		return modifyPersonPc;
	}

	public void setModifyPersonPc(String modifyPersonPc) {
		this.modifyPersonPc = modifyPersonPc;
	}

	public String getModifyPersonId() {
		return modifyPersonId;
	}

	public void setModifyPersonId(String modifyPersonId) {
		this.modifyPersonId = modifyPersonId;
	}

	public String getBurgOrgName() {
		return burgOrgName;
	}

	public void setBurgOrgName(String burgOrgName) {
		this.burgOrgName = burgOrgName;
	}

	public String getBurgOrgId() {
		return burgOrgId;
	}

	public void setBurgOrgId(String burgOrgId) {
		this.burgOrgId = burgOrgId;
	}

	public Long getCjid() {
		return cjid;
	}

	public void setCjid(Long cjid) {
		this.cjid = cjid;
	}
}