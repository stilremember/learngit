package com.augurit.myproject.utils.arcgis.form;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.augurit.myproject.sjcj.web.form.NwCjDataForm;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class DataFormToFeatureConvertor {
	
	//转成图层实体准备保存
	public static FeatureForm convertCorrVoToForm(NwCjDataForm form) {
		if(form != null) {
			FeatureForm featureForm = new FeatureForm();
			featureForm.setId(form.getId());
			featureForm.setObjectid(form.getObjectId()!=null?form.getObjectId():null);
			featureForm.setMark_id(form.getId());
			featureForm.setMark_person_id(form.getMarkPersonId());
			featureForm.setMark_person(form.getMarkPerson());
			featureForm.setMark_time(form.getMarkTime()!=null? form.getMarkTime().getTime() :null);
			featureForm.setUpdate_time(form.getUpdateTime()!=null? form.getUpdateTime().getTime() :null);
			featureForm.setUser_id(form.getUserId());
			featureForm.setLayer_name(form.getCollectType());
			featureForm.setCoox(form.getCoox());
			featureForm.setCooy(form.getCooy());
			featureForm.setParent_org_name(form.getParentOrgName());
			featureForm.setParent_org_id(form.getParentOrgId());
			featureForm.setReport_type(form.getReportType());
			featureForm.setState(form.getState());
			featureForm.setYj_attr_one(form.getYjAttrOne());
			featureForm.setYj_attr_two(form.getYjAttrTwo());
			featureForm.setYj_attr_three(form.getYjAttrThree());
			featureForm.setYj_attr_four(form.getYjAttrFour());
			featureForm.setYj_attr_five(form.getYjAttrFive());
			featureForm.setYj_comb(form.getYjComb());

			featureForm.setCurrent_area(form.getCurrentArea());
			featureForm.setCurrent_town(form.getCurrentTown());
			featureForm.setCurrent_village(form.getCurrentVillage());
			featureForm.setDescription(form.getDescription());
			featureForm.setCmb_number(form.getCmbNumber());
			featureForm.setCmb_name(form.getCmbName());
			featureForm.setCmb_des_water(form.getCmbDesWater());
			featureForm.setCmb_treat_proce(form.getCmbTreatProce());
			featureForm.setCmb_run_time(form.getCmbRunTime()!=null?form.getCmbRunTime().getTime():null);
			featureForm.setCmb_standard(form.getCmbStandard());
			featureForm.setCmb_street_people(form.getCmbStreetPeople());
			featureForm.setCmb_street_people_phone(form.getCmbStreetPeoplePhone());
			featureForm.setCmb_vill_people(form.getCmbVillPeople());
			featureForm.setCmb_vill_people_phone(form.getCmbVillPeoplePhone());
			featureForm.setCmb_ma_unit(form.getCmbMaUnit());
			featureForm.setCmb_ma_unit_people(form.getCmbMaUnitPeople());
			featureForm.setCmb_ma_unit_phone(form.getCmbMaUnitPhone());
			featureForm.setCmb_cov_area(form.getCmbCovArea());
			featureForm.setCmb_is_dyn(form.getCmbIsDyn());
			featureForm.setCmb_is_dyn(form.getCmbIsDyn());
			featureForm.setRoad(form.getRoad());
			featureForm.setAddr(form.getAddr());
			featureForm.setX(form.getX());
			featureForm.setY(form.getY());

			featureForm.setParent_org_id(form.getParentOrgId());
			featureForm.setParent_org_name(form.getParentOrgName());
			featureForm.setCheck_state(form.getCheckState());
			featureForm.setCheck_person(form.getCheckPerson());
			featureForm.setCheck_person_id(form.getCheckPersonId());
			featureForm.setCheck_time(form.getCheckTime()!=null? form.getCheckTime(): null);
			featureForm.setCheck_desription(form.getCheckDesription());
			featureForm.setUser_id(form.getUserId());
			featureForm.setCheck_type(form.getCheckType());

			featureForm.setTeam_org_id(form.getTeamOrgId());
			featureForm.setTeam_org_name(form.getTeamOrgName());
			featureForm.setCollect_type(form.getCollectType());
			return featureForm;
		}else
			return null;
	}
	//图层实体储存转换
	public static Map<String, Object> convertFeatureVoToCorrForm(FeatureForm featureForm) {
		if(featureForm != null) {
			Map<String, Object> form = new HashMap();
			form.put("objectid",featureForm.getObjectid());
			form.put("check_state",featureForm.getCheck_state());
			form.put("check_person",featureForm.getCheck_person());
			form.put("check_person_id",featureForm.getCheck_person_id());
			form.put("check_desription",featureForm.getCheck_desription());
			form.put("check_time",featureForm.getCheck_time()!=null? new Date(featureForm.getCheck_time()): null);
			return form;
		}else{
			return null;
		}
	}
	//转成图层保存json字符串
	public static String convertFeatureToJson(FeatureForm form) {
		JSONArray jsonArray = new JSONArray();
		if(form!=null){
			Map<String, Object> feature = new HashMap<String, Object>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("x", form.getX());
			map.put("y", form.getY());
			feature.put("attributes", form);
			feature.put("geometry", map);
			jsonArray.add(feature);
			return jsonArray.toString();
		}else{
			return null;
		}
	}
	/*********TODO******************移动端修改功能（不能修改审核信息）**********************TODO************/
	/**
	 * 校核(返回可以修改的数据)
	 * @param nwForm
	 * @return feature
	 * */
	public static String convertFeatureToMap(NwCjDataForm nwForm){
		FeatureForm form = convertCorrVoToForm(nwForm);
		JSONArray jsonArray = new JSONArray();
		if(form!=null){
			Map<String, Object> feature = new HashMap<String, Object>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("x", form.getX());
			map.put("y", form.getY());
			JSONObject jsonForm =  JSONObject.fromObject(form);
			if(jsonForm.containsKey("check_person_id"))
				jsonForm.remove("check_person_id");
			if(jsonForm.containsKey("check_person"))
				jsonForm.remove("check_person");
			if(jsonForm.containsKey("check_state"))
				jsonForm.remove("check_state");
			if(jsonForm.containsKey("check_time"))
				jsonForm.remove("check_time");
			if(jsonForm.containsKey("check_type"))
				jsonForm.remove("check_type");
			if(jsonForm.containsKey("check_desription"))
				jsonForm.remove("check_desription");
			feature.put("attributes", jsonForm);
			feature.put("geometry", map);
			jsonArray.add(feature);
			return jsonArray.toString();
		}else{
			return null;
		}
	}
	//转成图层保存json字符串
	public static String convertUpdateFeatureToJson(FeatureUpdateForm form) {
		JSONArray jsonArray = new JSONArray();
		if(form!=null){
			Map<String, Object> feature = new HashMap<String, Object>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("x", form.getX());
			map.put("y", form.getY());
			feature.put("attributes", form);
			feature.put("geometry", map);
			jsonArray.add(feature);
			return jsonArray.toString();
		}else{
			return null;
		}
	}
	
	/**
	 * 存到arcgis服务时间戳是UTC+0的，转为UTC+8(北京时间)
	 * @params time 本地时间戳(arcgis服务上显示为零时区时间)
	 * @return UTC+8时区 (arcgis服务上显示为北京时间)
	 * */
	public static Long UnicVoUtc(Long time){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
		int dstOffset =  cal.get(Calendar.DST_OFFSET);
		cal.add(Calendar.MILLISECOND, +(zoneOffset + dstOffset));
		return cal.getTimeInMillis();
	}
	
	public static FeatureUpdateForm convertUpdateVoToFeature(NwCjDataForm form) {
		if(form!=null){
			FeatureUpdateForm featureUpdateForm = new FeatureUpdateForm();
			featureUpdateForm.setId(form.getId());
			featureUpdateForm.setObjectid(form.getObjectId()!=null?form.getObjectId():null);
			featureUpdateForm.setCheck_state(form.getCheckState());
			featureUpdateForm.setCheck_time(new Date().getTime());
			featureUpdateForm.setCheck_person_id(form.getCheckPersonId());
			featureUpdateForm.setCheck_person(form.getCheckPerson());
			featureUpdateForm.setCheck_desription(form.getDescription());
			return featureUpdateForm;
		}
		return null;
	}
}
