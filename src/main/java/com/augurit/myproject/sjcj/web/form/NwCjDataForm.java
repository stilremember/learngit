package com.augurit.myproject.sjcj.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


public class NwCjDataForm {
	// 属性
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
	private String currentArea;//所在区
	private String currentTown;//所在镇
	private String currentVillage;//所在行政村
	private String description; //上报说明
	private String yjAttrOne; //特征
	private String yjAttrTwo; //雨污类别
	private String yjAttrThree; //井盖材质
	private String yjAttrFour;  //维管单位
	private String yjAttrFive;	//已挂牌编号
	private String yjComb;	//所属设施点
	private String cmbNumber; //设施编号
	private String cmbName; //设施名称
	private Double cmbDesWater; //设计处理水量
	private String cmbTreatProce; // 处理工艺
	private Date cmbRunTime; //投入运行时间
	private String cmbStandard; //设计出水标准
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
	private Long checkTime;
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

	private List<NwCjDataAfftypeForm> attachComb=new ArrayList<>();  //附属设施
	private List<NwCjDataAttachmentForm> attachment=new ArrayList<>();   //附件
	
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
		return updateTime;
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
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCmbNumber() {
		return cmbNumber;
	}

	public void setCmbNumber(String cmbNumber) {
		this.cmbNumber = cmbNumber;
	}

	public String getCmbName() {
		return cmbName;
	}

	public void setCmbName(String cmbName) {
		this.cmbName = cmbName;
	}

	public Double getCmbDesWater() {
		return cmbDesWater;
	}

	public void setCmbDesWater(Double cmbDesWater) {
		this.cmbDesWater = cmbDesWater;
	}

	public String getCmbTreatProce() {
		return cmbTreatProce;
	}

	public void setCmbTreatProce(String cmbTreatProce) {
		this.cmbTreatProce = cmbTreatProce;
	}

	public Date getCmbRunTime() {
		return cmbRunTime;
	}

	public void setCmbRunTime(Date cmbRunTime) {
		this.cmbRunTime = cmbRunTime;
	}

	public String getCmbStandard() {
		return cmbStandard;
	}

	public void setCmbStandard(String cmbStandard) {
		this.cmbStandard = cmbStandard;
	}

	public String getCmbStreetPeople() {
		return cmbStreetPeople;
	}

	public void setCmbStreetPeople(String cmbStreetPeople) {
		this.cmbStreetPeople = cmbStreetPeople;
	}

	public String getCmbStreetPeoplePhone() {
		return cmbStreetPeoplePhone;
	}

	public void setCmbStreetPeoplePhone(String cmbStreetPeoplePhone) {
		this.cmbStreetPeoplePhone = cmbStreetPeoplePhone;
	}

	public String getCmbVillPeople() {
		return cmbVillPeople;
	}

	public void setCmbVillPeople(String cmbVillPeople) {
		this.cmbVillPeople = cmbVillPeople;
	}

	public String getCmbVillPeoplePhone() {
		return cmbVillPeoplePhone;
	}

	public void setCmbVillPeoplePhone(String cmbVillPeoplePhone) {
		this.cmbVillPeoplePhone = cmbVillPeoplePhone;
	}

	public String getCmbMaUnit() {
		return cmbMaUnit;
	}

	public void setCmbMaUnit(String cmbMaUnit) {
		this.cmbMaUnit = cmbMaUnit;
	}

	public String getCmbMaUnitPeople() {
		return cmbMaUnitPeople;
	}

	public void setCmbMaUnitPeople(String cmbMaUnitPeople) {
		this.cmbMaUnitPeople = cmbMaUnitPeople;
	}

	public String getCmbMaUnitPhone() {
		return cmbMaUnitPhone;
	}

	public void setCmbMaUnitPhone(String cmbMaUnitPhone) {
		this.cmbMaUnitPhone = cmbMaUnitPhone;
	}

	public String getCmbCovArea() {
		return cmbCovArea;
	}

	public void setCmbCovArea(String cmbCovArea) {
		this.cmbCovArea = cmbCovArea;
	}

	public String getCmbIsDyn() {
		return cmbIsDyn;
	}

	public void setCmbIsDyn(String cmbIsDyn) {
		this.cmbIsDyn = cmbIsDyn;
	}

	public List<NwCjDataAfftypeForm> getAttachComb() {
		return attachComb;
	}

	public void setAttachComb(List<NwCjDataAfftypeForm> attachComb) {
		this.attachComb = attachComb;
	}

	public List<NwCjDataAttachmentForm> getAttachment() {
		return attachment;
	}

	public void setAttachment(List<NwCjDataAttachmentForm> attachment) {
		this.attachment = attachment;
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

	public Long getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Long checkTime) {
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