package com.augurit.myproject.sjcj.convert;

import com.augurit.myproject.sjcj.entity.NwCjData;
import com.augurit.myproject.sjcj.web.form.NwCjDataCombForm;
import com.augurit.myproject.sjcj.web.form.NwCjDataForm;
import com.augurit.myproject.sjcj.web.form.NwCjDataYjForm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NwCjDataConvertor {
	public static NwCjDataForm convertVoToForm(NwCjData entity) {
		if(entity != null) {
			NwCjDataForm form = new NwCjDataForm();
			form.setId(entity.getId());
			form.setMarkPersonId(entity.getMarkPersonId());
			form.setMarkPerson(entity.getMarkPerson());
			form.setUserId(entity.getUserId());
			form.setMarkTime(entity.getMarkTime());
			form.setX(entity.getX());
			form.setY(entity.getY());
			form.setCoox(entity.getCoox());
			form.setCooy(entity.getCooy());
			form.setParentOrgName(entity.getParentOrgName());
			form.setParentOrgId(entity.getParentOrgId());
			form.setUpdateTime(entity.getUpdateTime());
			form.setReportType(entity.getReportType());
			form.setState(entity.getState());
			form.setAddr(entity.getAddr());
			form.setRoad(entity.getRoad());
			form.setCurrentArea(entity.getCurrentArea());
			form.setCurrentTown(entity.getCurrentTown());
			form.setCurrentVillage(entity.getCurrentVillage());
			form.setDescription(entity.getDescription());
			form.setYjAttrOne(entity.getYjAttrOne());
			form.setYjAttrTwo(entity.getYjAttrTwo());
			form.setYjAttrThree(entity.getYjAttrThree());
			form.setYjAttrFour(entity.getYjAttrFour());
			form.setYjAttrFive(entity.getYjAttrFive());
			form.setYjComb(entity.getYjComb());
			form.setCmbNumber(entity.getCmbNumber());
			form.setCmbName(entity.getCmbName());
			form.setCmbDesWater(entity.getCmbDesWater());
			form.setCmbTreatProce(entity.getCmbTreatProce());
			form.setCmbRunTime(entity.getCmbRunTime());
			form.setCmbStandard(entity.getCmbStandard());
			form.setCmbStreetPeople(entity.getCmbStreetPeople());
			form.setCmbStreetPeoplePhone(entity.getCmbStreetPeoplePhone());
			form.setCmbVillPeople(entity.getCmbVillPeople());
			form.setCmbVillPeoplePhone(entity.getCmbVillPeoplePhone());
			form.setCmbMaUnit(entity.getCmbMaUnit());
			form.setCmbMaUnitPeople(entity.getCmbMaUnitPeople());
			form.setCmbMaUnitPhone(entity.getCmbMaUnitPhone());
			form.setCmbCovArea(entity.getCmbCovArea());
			form.setCmbIsDyn(entity.getCmbIsDyn());
			form.setRemark(entity.getRemark());
			form.setCollectType(entity.getCollectType());
			form.setObjectId(entity.getObjectId());
			form.setIsAddFeature(entity.getIsAddFeature());
			form.setCheckState(entity.getCheckState());
			form.setCheckPersonId(entity.getCheckPersonId());
			form.setCheckPerson(entity.getCheckPerson());
			form.setCheckTime(entity.getCheckTime()!=null?entity.getCheckTime().getTime():null);
			form.setCheckDesription(entity.getCheckDesription());
			
			form.setDirectOrgId(entity.getDirectOrgId());
			form.setDirectOrgName(entity.getDirectOrgName());
			form.setYjCombId(entity.getYjCombId());
			form.setCheckType(entity.getCheckType());
			
			form.setTeamOrgId(entity.getTeamOrgId());
			form.setTeamOrgName(entity.getTeamOrgName());

			form.setTeamCheck(entity.getTeamCheck());
			form.setParentCheck(entity.getParentCheck());
			form.setTopCheck(entity.getTopCheck());
			form.setCheckOrgName(entity.getCheckOrgName());
			form.setModifyPersonPc(entity.getModifyPersonPc());
			form.setModifyPersonId(entity.getModifyPersonId());
			form.setCjid(entity.getCjid());
			return form;
		}else
			return null;
	}
	
	public static void convertFormToVo(NwCjDataForm form, NwCjData entity) {
		if(entity != null && form != null) {
			entity.setId(form.getId());
			if(form.getMarkPersonId() != null && form.getMarkPersonId().trim().length() > 0)
				entity.setMarkPersonId(form.getMarkPersonId().trim());
			if(form.getMarkPerson() != null && form.getMarkPerson().trim().length() > 0)
				entity.setMarkPerson(form.getMarkPerson().trim());
			if(form.getUserId()!=null)
				entity.setUserId(form.getUserId());
			if(form.getMarkTime()!=null)
				entity.setMarkTime(form.getMarkTime());
			if(form.getX()!=null)
				entity.setX(form.getX());
			if(form.getY()!=null)
				entity.setY(form.getY());
			if(form.getCoox()!=null)
				entity.setCoox(form.getCoox());
			if(form.getCooy()!=null)
				entity.setCooy(form.getCooy());
			if(form.getParentOrgName() != null && form.getParentOrgName().trim().length() > 0)
				entity.setParentOrgName(form.getParentOrgName().trim());
			if(form.getParentOrgId() != null && form.getParentOrgId().trim().length() > 0)
				entity.setParentOrgId(form.getParentOrgId().trim());
			if(form.getUpdateTime()!=null)
				entity.setUpdateTime(form.getUpdateTime());
			if(form.getReportType() != null && form.getReportType().trim().length() > 0)
				entity.setReportType(form.getReportType().trim());
			if(form.getState() != null && form.getState().trim().length() > 0)
				entity.setState(form.getState());
			if(form.getAddr() != null && form.getAddr().trim().length() > 0)
				entity.setAddr(form.getAddr().trim());
			if(form.getRoad() != null && form.getRoad().trim().length() > 0)
				entity.setRoad(form.getRoad().trim());
			if(form.getCurrentArea() != null && form.getCurrentArea().trim().length() > 0)
				entity.setCurrentArea(form.getCurrentArea().trim());
			if(form.getCurrentTown() != null && form.getCurrentTown().trim().length() > 0)
				entity.setCurrentTown(form.getCurrentTown().trim());
			if(form.getCurrentVillage() != null && form.getCurrentVillage().trim().length() > 0)
				entity.setCurrentVillage(form.getCurrentVillage().trim());
			if(form.getDescription() != null && form.getDescription().trim().length() > 0)
				entity.setDescription(form.getDescription().trim());
			if(form.getYjAttrOne() != null && form.getYjAttrOne().trim().length() > 0)
				entity.setYjAttrOne(form.getYjAttrOne().trim());
			if(form.getYjAttrTwo() != null && form.getYjAttrTwo().trim().length() > 0)
				entity.setYjAttrTwo(form.getYjAttrTwo().trim());
			if(form.getYjAttrThree() != null && form.getYjAttrThree().trim().length() > 0)
				entity.setYjAttrThree(form.getYjAttrThree().trim());
			if(form.getYjAttrFour() != null && form.getYjAttrFour().trim().length() > 0)
				entity.setYjAttrFour(form.getYjAttrFour().trim());
			if(form.getYjAttrFive() != null && form.getYjAttrFive().trim().length() > 0)
				entity.setYjAttrFive(form.getYjAttrFive().trim());
			//if(form.getYjComb() != null && form.getYjComb().trim().length() > 0)
			entity.setYjComb(form.getYjComb());
			if(form.getCmbNumber() != null && form.getCmbNumber().trim().length() > 0)
				entity.setCmbNumber(form.getCmbNumber().trim());
			if(form.getCmbName() != null && form.getCmbName().trim().length() > 0)
				entity.setCmbName(form.getCmbName().trim());
			if(form.getCmbDesWater() != null)
				entity.setCmbDesWater(form.getCmbDesWater());
			if(form.getCmbTreatProce() != null && form.getCmbTreatProce().trim().length() > 0)
				entity.setCmbTreatProce(form.getCmbTreatProce().trim());
			if(form.getCmbRunTime()!=null)
				entity.setCmbRunTime(form.getCmbRunTime());
			if(form.getCmbStandard() != null && form.getCmbStandard().trim().length() > 0)
				entity.setCmbStandard(form.getCmbStandard().trim());
			if(form.getCmbStreetPeople() != null && form.getCmbStreetPeople().trim().length() > 0)
				entity.setCmbStreetPeople(form.getCmbStreetPeople().trim());
			if(form.getCmbStreetPeoplePhone() != null && form.getCmbStreetPeoplePhone().trim().length() > 0)
				entity.setCmbStreetPeoplePhone(form.getCmbStreetPeoplePhone().trim());
			if(form.getCmbVillPeople() != null && form.getCmbVillPeople().trim().length() > 0)
				entity.setCmbVillPeople(form.getCmbVillPeople().trim());
			if(form.getCmbVillPeoplePhone() != null && form.getCmbVillPeoplePhone().trim().length() > 0)
				entity.setCmbVillPeoplePhone(form.getCmbVillPeoplePhone().trim());
			if(form.getCmbMaUnit() != null && form.getCmbMaUnit().trim().length() > 0)
				entity.setCmbMaUnit(form.getCmbMaUnit().trim());
			if(form.getCmbMaUnitPeople() != null && form.getCmbMaUnitPeople().trim().length() > 0)
				entity.setCmbMaUnitPeople(form.getCmbMaUnitPeople().trim());
			if(form.getCmbMaUnitPhone() != null && form.getCmbMaUnitPhone().trim().length() > 0)
				entity.setCmbMaUnitPhone(form.getCmbMaUnitPhone().trim());
			if(form.getCmbCovArea() != null && form.getCmbCovArea().trim().length() > 0)
				entity.setCmbCovArea(form.getCmbCovArea().trim());
			if(form.getCmbIsDyn() != null && form.getCmbIsDyn().trim().length() > 0)
				entity.setCmbIsDyn(form.getCmbIsDyn());
			if(form.getRemark() != null && form.getRemark().trim().length() > 0)
				entity.setRemark(form.getRemark().trim());
			if(form.getCollectType() != null && form.getCollectType().trim().length() > 0)
				entity.setCollectType(form.getCollectType().trim());
			
			if(form.getObjectId() != null )
				entity.setObjectId(form.getObjectId());
			if(form.getIsAddFeature() != null && form.getIsAddFeature().trim().length() > 0)
				entity.setIsAddFeature(form.getIsAddFeature().trim());
			if(form.getCheckState() != null && form.getCheckState().trim().length() > 0)
				entity.setCheckState(form.getCheckState().trim());
			if(form.getCheckPerson() != null && form.getCheckPerson().trim().length() > 0)
				entity.setCheckPerson(form.getCheckPerson().trim());
			if(form.getCheckPersonId() != null && form.getCheckPersonId().trim().length() > 0)
				entity.setCheckPersonId(form.getCheckPersonId().trim());
			if(form.getCheckTime()!=null)
				entity.setCheckTime(new Date(form.getCheckTime()));
			if(form.getCheckDesription() != null && form.getCheckDesription().trim().length() > 0)
				entity.setCheckDesription(form.getCheckDesription().trim());
			if(form.getDirectOrgId() != null && form.getDirectOrgId().trim().length() > 0)
				entity.setDirectOrgId(form.getDirectOrgId().trim());
			if(form.getDirectOrgName() != null && form.getDirectOrgName().trim().length() > 0)
				entity.setDirectOrgName(form.getDirectOrgName().trim());
			//if(form.getYjCombId()!=null)
			entity.setYjCombId(form.getYjCombId());
			if(form.getCheckType()!=null && form.getCheckType().trim().length() > 0)
				entity.setCheckType(form.getCheckType());
			if(form.getTeamOrgId()!=null && form.getTeamOrgId().trim().length() > 0)
				entity.setTeamOrgId(form.getTeamOrgId());
			if(form.getTeamOrgName()!=null && form.getTeamOrgName().trim().length() > 0)
				entity.setTeamOrgName(form.getTeamOrgName());

			if(form.getTeamCheck()!=null && form.getTeamCheck().trim().length() > 0)
				entity.setTeamCheck(form.getTeamCheck());
			if(form.getParentCheck()!=null && form.getParentCheck().trim().length() > 0)
				entity.setParentCheck(form.getParentCheck());
			if(form.getTopCheck()!=null && form.getTopCheck().trim().length() > 0)
				entity.setTopCheck(form.getTopCheck());
			if(form.getCheckOrgName()!=null && form.getCheckOrgName().trim().length() > 0)
				entity.setCheckOrgName(form.getCheckOrgName());

			if(form.getModifyPersonPc()!=null && form.getModifyPersonPc().trim().length() > 0)
				entity.setModifyPersonPc(form.getModifyPersonPc());
			if(form.getModifyPersonId()!=null && form.getModifyPersonId().trim().length() > 0)
				entity.setModifyPersonId(form.getModifyPersonId());
			if(form.getCjid()!=null)
				entity.setCjid(form.getCjid());

		}
	}
	
	public static List<NwCjDataForm> convertVoListToFormList(List<NwCjData> nwCjDataList) {
		if(nwCjDataList != null && nwCjDataList.size() > 0) {
			List<NwCjDataForm> nwCjDataFormList = new ArrayList();
			for(int i=0; i<nwCjDataList.size(); i++) {
				nwCjDataFormList.add(convertVoToForm(nwCjDataList.get(i)));
			}
			return nwCjDataFormList;
		}
		return null;
	}
	/**
	 * 列表转换需要(少量信息就行)
	 * */
	public static List<Map> convertFormVoListToMapLists(List<NwCjDataForm> nwCjDataList) {
		if(nwCjDataList != null && nwCjDataList.size() > 0) {
			List<Map> mapList = new ArrayList();
			for(int i=0; i<nwCjDataList.size(); i++) {
				NwCjDataForm form = nwCjDataList.get(i);
				Map map = new HashMap();
				
				map.put("id", form.getId() == null ? "" : form.getId().toString());
				map.put("markPersonId", form.getMarkPersonId());
				map.put("markPerson", form.getMarkPerson());
				map.put("userId", form.getUserId() == null ? "" : form.getUserId().toString());
				map.put("markTime", form.getMarkTime()!=null?form.getMarkTime().getTime():null);
				map.put("x", form.getX() == null ? "" : form.getX().toString());
				map.put("y", form.getY() == null ? "" : form.getY().toString());
				map.put("coox", form.getCoox() == null ? "" : form.getCoox().toString());
				map.put("cooy", form.getCooy() == null ? "" : form.getCooy().toString());
				map.put("parentOrgName", form.getParentOrgName());
				map.put("parentOrgId", form.getParentOrgId());
				map.put("updateTime", form.getUpdateTime()!=null? form.getUpdateTime().getTime():null);
				map.put("reportType", form.getReportType());
				map.put("state", form.getState() == null ? "" : form.getState().toString());
				map.put("addr", form.getAddr());
				map.put("road", form.getRoad());
				map.put("collectType", form.getCollectType());
				map.put("objectId", form.getObjectId());
				map.put("isAddFeature", form.getIsAddFeature());
				map.put("checkState", form.getCheckState());
				map.put("checkPersonId", form.getCheckPersonId());
				map.put("checkPerson", form.getCheckPerson());
				map.put("checkTime", form.getCheckTime()!=null? form.getCheckTime() :null);
				map.put("directOrgId", form.getDirectOrgId());
				map.put("directOrgName", form.getDirectOrgName());
				map.put("yjCombId", form.getYjCombId());
				map.put("checkType", form.getCheckType());

				map.put("modifyPersonPc", form.getModifyPersonPc());
				mapList.add(map);
			}
			return mapList;
		}
		return null;
	}
	public static List<Map> convertFormVoListToMapList(List<NwCjDataForm> nwCjDataList) {
		if(nwCjDataList != null && nwCjDataList.size() > 0) {
			List<Map> mapList = new ArrayList();
			for(int i=0; i<nwCjDataList.size(); i++) {
				NwCjDataForm form = nwCjDataList.get(i);
				Map map = new HashMap();

				map.put("id", form.getId() == null ? "" : form.getId().toString());
				map.put("markPersonId", form.getMarkPersonId());
				map.put("markPerson", form.getMarkPerson());
				map.put("userId", form.getUserId() == null ? "" : form.getUserId().toString());
				map.put("markTime", form.getMarkTime()!=null?form.getMarkTime().getTime():null);
				map.put("x", form.getX() == null ? "" : form.getX().toString());
				map.put("y", form.getY() == null ? "" : form.getY().toString());
				map.put("coox", form.getCoox() == null ? "" : form.getCoox().toString());
				map.put("cooy", form.getCooy() == null ? "" : form.getCooy().toString());
				map.put("parentOrgName", form.getParentOrgName());
				map.put("parentOrgId", form.getParentOrgId());
				map.put("updateTime", form.getUpdateTime()!=null? form.getUpdateTime().getTime():null);
				map.put("reportType", form.getReportType());
				map.put("state", form.getState() == null ? "" : form.getState().toString());
				map.put("addr", form.getAddr());
				map.put("road", form.getRoad());
				map.put("currentArea", form.getCurrentArea());
				map.put("currentTown", form.getCurrentTown());
				map.put("currentVillage", form.getCurrentVillage());
				map.put("description", form.getDescription());
				map.put("yjAttrOne", form.getYjAttrOne());
				map.put("yjAttrTwo", form.getYjAttrTwo());
				map.put("yjAttrThree", form.getYjAttrThree());
				map.put("yjAttrFour", form.getYjAttrFour());
				map.put("yjAttrFive", form.getYjAttrFive());
				map.put("yjComb", form.getYjComb());
				map.put("cmbNumber", form.getCmbNumber());
				map.put("cmbName", form.getCmbName());
				map.put("cmbDesWater", form.getCmbDesWater() == null ? "" : form.getCmbDesWater().toString());
				map.put("cmbTreatProce", form.getCmbTreatProce());
				map.put("cmbRunTime", form.getCmbRunTime()!=null?form.getCmbRunTime().getTime():null);
				map.put("cmbStandard", form.getCmbStandard());
				map.put("cmbStreetPeople", form.getCmbStreetPeople());
				map.put("cmbStreetPeoplePhone", form.getCmbStreetPeoplePhone());
				map.put("cmbVillPeople", form.getCmbVillPeople());
				map.put("cmbVillPeoplePhone", form.getCmbVillPeoplePhone());
				map.put("cmbMaUnit", form.getCmbMaUnit());
				map.put("cmbMaUnitPeople", form.getCmbMaUnitPeople());
				map.put("cmbMaUnitPhone", form.getCmbMaUnitPhone());
				map.put("cmbCovArea", form.getCmbCovArea());
				map.put("cmbIsDyn", form.getCmbIsDyn() == null ? "" : form.getCmbIsDyn().toString());
				map.put("remark", form.getRemark());
				map.put("collectType", form.getCollectType());
				
				map.put("objectId", form.getObjectId());
				map.put("isAddFeature", form.getIsAddFeature());
				map.put("checkState", form.getCheckState());
				map.put("checkPersonId", form.getCheckPersonId());
				map.put("checkPerson", form.getCheckPerson());
				map.put("checkTime", form.getCheckTime()!=null? form.getCheckTime() :null);
				map.put("checkDesription", form.getCheckDesription());
				map.put("directOrgId", form.getDirectOrgId());
				map.put("directOrgName", form.getDirectOrgName());
				map.put("yjCombId", form.getYjCombId());
				map.put("checkType", form.getCheckType());
				map.put("teamOrgId", form.getTeamOrgId());
				map.put("teamOrgName", form.getTeamOrgName());

				map.put("teamCheck", form.getTeamCheck());
				map.put("parentCheck", form.getParentCheck());
				map.put("topCheck", form.getTopCheck());
				map.put("checkOrgName", form.getCheckOrgName());
				map.put("modifyPersonPc", form.getModifyPersonPc());
				map.put("modifyPersonId", form.getModifyPersonId());
				map.put("cjid", form.getCjid());
				mapList.add(map);
			}
			return mapList;
		}
		return null;
	}
	public static List<Map> convertVoListToMapList(List<NwCjData> nwCjDataList) {
		if(nwCjDataList != null && nwCjDataList.size() > 0) {
			List<Map> mapList = new ArrayList();
			for(int i=0; i<nwCjDataList.size(); i++) {
				NwCjData entity = nwCjDataList.get(i);
				Map map = new HashMap();

				map.put("id", entity.getId() == null ? "" : entity.getId().toString());
				map.put("markPersonId", entity.getMarkPersonId());
				map.put("markPerson", entity.getMarkPerson());
				map.put("userId", entity.getUserId() == null ? "" : entity.getUserId().toString());
				map.put("markTime", entity.getMarkTime()!=null?entity.getMarkTime().getTime():null);
				map.put("x", entity.getX() == null ? "" : entity.getX().toString());
				map.put("y", entity.getY() == null ? "" : entity.getY().toString());
				map.put("coox", entity.getCoox() == null ? "" : entity.getCoox().toString());
				map.put("cooy", entity.getCooy() == null ? "" : entity.getCooy().toString());
				map.put("parentOrgName", entity.getParentOrgName());
				map.put("parentOrgId", entity.getParentOrgId());
				map.put("updateTime", entity.getUpdateTime()!=null? entity.getUpdateTime().getTime():null);
				map.put("reportType", entity.getReportType());
				map.put("state", entity.getState() == null ? "" : entity.getState().toString());
				map.put("addr", entity.getAddr());
				map.put("road", entity.getRoad());
				map.put("currentArea", entity.getCurrentArea());
				map.put("currentTown", entity.getCurrentTown());
				map.put("currentVillage", entity.getCurrentVillage());
				map.put("description", entity.getDescription());
				map.put("yjAttrOne", entity.getYjAttrOne());
				map.put("yjAttrTwo", entity.getYjAttrTwo());
				map.put("yjAttrThree", entity.getYjAttrThree());
				map.put("yjAttrFour", entity.getYjAttrFour());
				map.put("yjAttrFive", entity.getYjAttrFive());
				map.put("yjComb", entity.getYjComb());
				map.put("cmbNumber", entity.getCmbNumber());
				map.put("cmbName", entity.getCmbName());
				map.put("cmbDesWater", entity.getCmbDesWater() == null ? "" : entity.getCmbDesWater().toString());
				map.put("cmbTreatProce", entity.getCmbTreatProce());
				map.put("cmbRunTime", entity.getCmbRunTime()!=null?entity.getCmbRunTime().getTime():null);
				map.put("cmbStandard", entity.getCmbStandard());
				map.put("cmbStreetPeople", entity.getCmbStreetPeople());
				map.put("cmbStreetPeoplePhone", entity.getCmbStreetPeoplePhone());
				map.put("cmbVillPeople", entity.getCmbVillPeople());
				map.put("cmbVillPeoplePhone", entity.getCmbVillPeoplePhone());
				map.put("cmbMaUnit", entity.getCmbMaUnit());
				map.put("cmbMaUnitPeople", entity.getCmbMaUnitPeople());
				map.put("cmbMaUnitPhone", entity.getCmbMaUnitPhone());
				map.put("cmbCovArea", entity.getCmbCovArea());
				map.put("cmbIsDyn", entity.getCmbIsDyn() == null ? "" : entity.getCmbIsDyn().toString());
				map.put("remark", entity.getRemark());
				map.put("collectType", entity.getCollectType());
				
				map.put("objectId", entity.getObjectId());
				map.put("isAddFeature", entity.getIsAddFeature());
				map.put("checkState", entity.getCheckState());
				map.put("checkPersonId", entity.getCheckPersonId());
				map.put("checkPerson", entity.getCheckPerson());
				map.put("checkTime", entity.getCheckTime()!=null?entity.getCheckTime().getTime():null);
				map.put("checkDesription", entity.getCheckDesription());
				map.put("directOrgId", entity.getDirectOrgId());
				map.put("directOrgName", entity.getDirectOrgName());
				map.put("yjCombId", entity.getYjCombId());
				map.put("checkType", entity.getCheckType());
				map.put("teamOrgId", entity.getTeamOrgId());
				map.put("teamOrgName", entity.getTeamOrgName());

				map.put("teamCheck", entity.getTeamCheck());
				map.put("parentCheck", entity.getParentCheck());
				map.put("topCheck", entity.getTopCheck());
				map.put("checkOrgName", entity.getCheckOrgName());
				map.put("modifyPersonPc", entity.getModifyPersonPc());
				map.put("modifyPersonId", entity.getModifyPersonId());
				map.put("cjid", entity.getCjid());
				mapList.add(map);
			}
			return mapList;
		}
		return null;
	}
	public static Map convertVoToMap(NwCjDataForm form) {
				Map map = new HashMap();
				
				map.put("id", form.getId() == null ? "" : form.getId().toString());
				map.put("markPersonId", form.getMarkPersonId());
				map.put("markPerson", form.getMarkPerson());
				map.put("userId", form.getUserId() == null ? "" : form.getUserId().toString());
				map.put("markTime", form.getMarkTime()!=null?form.getMarkTime().getTime():null);
				map.put("x", form.getX() == null ? "" : form.getX().toString());
				map.put("y", form.getY() == null ? "" : form.getY().toString());
				map.put("coox", form.getCoox() == null ? "" : form.getCoox().toString());
				map.put("cooy", form.getCooy() == null ? "" : form.getCooy().toString());
				map.put("parentOrgName", form.getParentOrgName());
				map.put("parentOrgId", form.getParentOrgId());
				map.put("updateTime", form.getUpdateTime()!=null?form.getUpdateTime().getTime():null);
				map.put("reportType", form.getReportType());
				map.put("state", form.getState() == null ? "" : form.getState().toString());
				map.put("addr", form.getAddr());
				map.put("road", form.getRoad());
				map.put("currentArea", form.getCurrentArea());
				map.put("currentTown", form.getCurrentTown());
				map.put("currentVillage", form.getCurrentVillage());
				map.put("description", form.getDescription());
				map.put("yjAttrOne", form.getYjAttrOne());
				map.put("yjAttrTwo", form.getYjAttrTwo());
				map.put("yjAttrThree", form.getYjAttrThree());
				map.put("yjAttrFour", form.getYjAttrFour());
				map.put("yjAttrFive", form.getYjAttrFive());
				map.put("yjComb", form.getYjComb());
				map.put("cmbNumber", form.getCmbNumber());
				map.put("cmbName", form.getCmbName());
				map.put("cmbDesWater", form.getCmbDesWater() == null ? "" : form.getCmbDesWater().toString());
				map.put("cmbTreatProce", form.getCmbTreatProce());
				map.put("cmbRunTime", form.getCmbRunTime()!=null? form.getCmbRunTime().getTime():null);
				map.put("cmbStandard", form.getCmbStandard());
				map.put("cmbStreetPeople", form.getCmbStreetPeople());
				map.put("cmbStreetPeoplePhone", form.getCmbStreetPeoplePhone());
				map.put("cmbVillPeople", form.getCmbVillPeople());
				map.put("cmbVillPeoplePhone", form.getCmbVillPeoplePhone());
				map.put("cmbMaUnit", form.getCmbMaUnit());
				map.put("cmbMaUnitPeople", form.getCmbMaUnitPeople());
				map.put("cmbMaUnitPhone", form.getCmbMaUnitPhone());
				map.put("cmbCovArea", form.getCmbCovArea());
				map.put("cmbIsDyn", form.getCmbIsDyn() == null ? "" : form.getCmbIsDyn().toString());
				map.put("remark", form.getRemark());
				map.put("collectType", form.getCollectType());
				
				map.put("objectId", form.getObjectId());
				map.put("isAddFeature", form.getIsAddFeature());
				map.put("checkState", form.getCheckState());
				map.put("checkPersonId", form.getCheckPersonId());
				map.put("checkPerson", form.getCheckPerson());
				map.put("checkTime", form.getCheckTime()!=null? form.getCheckTime() :null);
				map.put("checkDesription", form.getCheckDesription());
				
				map.put("directOrgId", form.getDirectOrgId());
				map.put("directOrgName", form.getDirectOrgName());
				map.put("yjCombId", form.getYjCombId());
				map.put("checkType", form.getCheckType());
				map.put("teamOrgId", form.getTeamOrgId());
				map.put("teamOrgName", form.getTeamOrgName());

				map.put("teamCheck", form.getTeamCheck());
				map.put("parentCheck", form.getParentCheck());
				map.put("topCheck", form.getTopCheck());
				map.put("checkOrgName", form.getCheckOrgName());
				map.put("modifyPersonPc", form.getModifyPersonPc());
				map.put("modifyPersonId", form.getModifyPersonId());
				map.put("cjid", form.getCjid());
			return map;
	}
	
	public static List<NwCjData> convertFormListToVoList(List<NwCjDataForm> nwCjDataFormList) {
		if(nwCjDataFormList != null && nwCjDataFormList.size() > 0) {
			List<NwCjData> nwCjDataList = new ArrayList();
			for(int i=0; i<nwCjDataFormList.size(); i++) {
				NwCjData nwCjData = new NwCjData();
				convertFormToVo(nwCjDataFormList.get(i), nwCjData);
				nwCjDataList.add(nwCjData);
			}
			return nwCjDataList;
		}
		return null;
	}
	/**
	 * 窨井
	 * */
	public static NwCjDataForm convertYjVoToForm(NwCjDataYjForm dataForm) {
		NwCjDataForm form = new NwCjDataForm();
		form.setId(dataForm.getId());
		form.setMarkPerson(dataForm.getMarkPerson());
		form.setMarkPersonId(dataForm.getMarkPersonId());
		form.setUserId(dataForm.getUserId());
		form.setMarkTime(dataForm.getMarkTime());
		form.setX(dataForm.getX());
		form.setY(dataForm.getY());
		form.setCoox(dataForm.getCoox());
		form.setCooy(dataForm.getCooy());
		form.setParentOrgName(dataForm.getParentOrgName());
		form.setParentOrgId(dataForm.getParentOrgId());
		form.setUpdateTime(dataForm.getUpdateTime());
		form.setReportType(dataForm.getReportType());
		form.setState(dataForm.getState());
		form.setAddr(dataForm.getAddr());
		form.setRoad(dataForm.getRoad());
		form.setCurrentArea(dataForm.getCurrentArea());
		form.setCurrentArea(dataForm.getCurrentArea());
		form.setCurrentTown(dataForm.getCurrentTown());
		form.setCurrentVillage(dataForm.getCurrentVillage());
		form.setDescription(dataForm.getDescription());
		form.setYjAttrOne(dataForm.getYjAttrOne());
		form.setYjAttrTwo(dataForm.getYjAttrTwo());
		form.setYjAttrThree(dataForm.getYjAttrThree());
		form.setYjAttrFour(dataForm.getYjAttrFour());
		form.setYjAttrFive(dataForm.getYjAttrFive());
		form.setYjAttrTwo(dataForm.getYjAttrTwo());
		form.setYjComb(dataForm.getYjComb());
		form.setRemark(dataForm.getRemark());
		form.setCollectType(dataForm.getCollectType());
		form.setCmbMaUnit(dataForm.getCmbMaUnit());
		
		form.setObjectId(dataForm.getObjectId());
		form.setIsAddFeature(dataForm.getIsAddFeature());
		form.setCheckState(dataForm.getCheckState());
		form.setCheckPersonId(dataForm.getCheckPersonId());
		form.setCheckPerson(dataForm.getCheckPerson());
		form.setCheckTime(dataForm.getCheckTime()!=null?dataForm.getCheckTime():null);
		form.setCheckDesription(dataForm.getCheckDesription());
		form.setYjCombId(dataForm.getYjCombId());
		form.setCheckType(dataForm.getCheckType());
		return form;
	}
	/**
	 * 设施点
	 * */
	public static NwCjDataForm convertCmbVoToForm(NwCjDataCombForm dataForm) {
		NwCjDataForm form = new NwCjDataForm();
		form.setId(dataForm.getId());
		form.setMarkPerson(dataForm.getMarkPerson());
		form.setMarkPersonId(dataForm.getMarkPersonId());
		form.setUserId(dataForm.getUserId());
		form.setMarkTime(dataForm.getMarkTime());
		form.setX(dataForm.getX());
		form.setY(dataForm.getY());
		form.setCoox(dataForm.getCoox());
		form.setCooy(dataForm.getCooy());
		form.setParentOrgName(dataForm.getParentOrgName());
		form.setParentOrgId(dataForm.getParentOrgId());
		form.setUpdateTime(dataForm.getUpdateTime());
		form.setReportType(dataForm.getReportType());
		form.setState(dataForm.getState());
		form.setAddr(dataForm.getAddr());
		form.setRoad(dataForm.getRoad());
		form.setCurrentArea(dataForm.getCurrentArea());
		form.setCurrentArea(dataForm.getCurrentArea());
		form.setCurrentTown(dataForm.getCurrentTown());
		form.setCurrentVillage(dataForm.getCurrentVillage());
		form.setDescription(dataForm.getDescription());
		form.setCmbNumber(dataForm.getCmbNumber());
		form.setCmbName(dataForm.getCmbName());
		form.setCmbDesWater(dataForm.getCmbDesWater());
		form.setCmbTreatProce(dataForm.getCmbTreatProce());
		form.setCmbRunTime(dataForm.getCmbRunTime());
		form.setCmbStandard(dataForm.getCmbStandard());
		form.setCmbStreetPeople(dataForm.getCmbStreetPeople());
		form.setCmbStreetPeoplePhone(dataForm.getCmbStreetPeoplePhone());
		form.setCmbVillPeople(dataForm.getCmbVillPeople());
		form.setCmbVillPeoplePhone(dataForm.getCmbVillPeoplePhone());
		form.setCmbMaUnit(dataForm.getCmbMaUnit());
		form.setCmbMaUnitPeople(dataForm.getCmbMaUnitPeople());
		form.setCmbMaUnitPhone(dataForm.getCmbMaUnitPhone());
		form.setCmbCovArea(dataForm.getCmbCovArea());
		form.setCmbIsDyn(dataForm.getCmbIsDyn());
		form.setRemark(dataForm.getRemark());
		form.setCollectType(dataForm.getCollectType());
		
		form.setObjectId(dataForm.getObjectId());
		form.setIsAddFeature(dataForm.getIsAddFeature());
		form.setCheckState(dataForm.getCheckState());
		form.setCheckPersonId(dataForm.getCheckPersonId());
		form.setCheckPerson(dataForm.getCheckPerson());
		form.setCheckTime(dataForm.getCheckTime()!=null?dataForm.getCheckTime():null);
		form.setCheckDesription(dataForm.getCheckDesription());
		form.setYjCombId(dataForm.getYjCombId());
		form.setCheckType(dataForm.getCheckType());
		return form;
	}
}