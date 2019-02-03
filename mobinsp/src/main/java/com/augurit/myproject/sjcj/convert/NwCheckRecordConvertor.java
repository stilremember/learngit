package com.augurit.myproject.sjcj.convert;

import com.augurit.myproject.sjcj.entity.NwCheckRecord;
import com.augurit.myproject.sjcj.web.form.NwCheckRecordForm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NwCheckRecordConvertor {
	public static NwCheckRecordForm convertVoToForm(NwCheckRecord entity) {
		if(entity != null) {
			NwCheckRecordForm form = new NwCheckRecordForm();
			form.setId(entity.getId());
			form.setCheckPersonId(entity.getCheckPersonId());
			form.setCheckPerson(entity.getCheckPerson());
			form.setCheckState(entity.getCheckState());
			form.setCheckTime(entity.getCheckTime());
			form.setCheckDesription(entity.getCheckDesription());
			form.setReportType(entity.getReportType());
			form.setReportId(entity.getReportId());
			form.setUsId(entity.getUsId());
			form.setOrgName(entity.getOrgName());
			form.setOrgId(entity.getOrgId());
			return form;
		}else
			return null;
	}
	
	public static void convertFormToVo(NwCheckRecordForm form, NwCheckRecord entity) {
		if(entity != null && form != null) {
			entity.setId(form.getId());
			if(form.getCheckPersonId() != null && form.getCheckPersonId().trim().length() > 0)
				entity.setCheckPersonId(form.getCheckPersonId().trim());
			if(form.getCheckPerson() != null && form.getCheckPerson().trim().length() > 0)
				entity.setCheckPerson(form.getCheckPerson().trim());
			if(form.getCheckState() != null && form.getCheckState().trim().length() > 0)
				entity.setCheckState(form.getCheckState().trim());
			entity.setCheckTime(form.getCheckTime());
			if(form.getCheckDesription() != null && form.getCheckDesription().trim().length() > 0)
				entity.setCheckDesription(form.getCheckDesription().trim());
			if(form.getReportType() != null && form.getReportType().trim().length() > 0)
				entity.setReportType(form.getReportType().trim());
			if(form.getReportId() != null)
				entity.setReportId(form.getReportId());
			if(form.getUsId() != null && form.getUsId().trim().length() > 0)
				entity.setUsId(form.getUsId().trim());
			if(form.getOrgName() != null && form.getOrgName().trim().length() > 0)
				entity.setOrgName(form.getOrgName().trim());
			if(form.getOrgId() != null && form.getOrgId().trim().length() > 0)
				entity.setOrgId(form.getOrgId().trim());
		}
	}
	
	public static List<NwCheckRecordForm> convertVoListToFormList(List<NwCheckRecord> checkRecordList) {
		if(checkRecordList != null && checkRecordList.size() > 0) {
			List<NwCheckRecordForm> checkRecordFormList = new ArrayList();
			for(int i=0; i<checkRecordList.size(); i++) {
				checkRecordFormList.add(convertVoToForm(checkRecordList.get(i)));
			}
			return checkRecordFormList;
		}
		return null;
	}
	
	public static List<Map> convertVoListToMapList(List<NwCheckRecord> checkRecordList) {
		if(checkRecordList != null && checkRecordList.size() > 0) {
			List<Map> mapList = new ArrayList();
			for(int i=0; i<checkRecordList.size(); i++) {
				NwCheckRecord entity = checkRecordList.get(i);
				Map map = new HashMap();

				map.put("id", entity.getId() == null ? "" : entity.getId().toString());
				map.put("checkPersonId", entity.getCheckPersonId());
				map.put("checkPerson", entity.getCheckPerson());
				map.put("checkState", entity.getCheckState());
				map.put("checkTime", entity.getCheckTime());
				map.put("checkDesription", entity.getCheckDesription());
				map.put("reportType", entity.getReportType());
				map.put("reportId", entity.getReportId());
				map.put("usId", entity.getUsId());
				map.put("orgName",entity.getOrgName());
				map.put("orgId",entity.getOrgId());

				mapList.add(map);
			}
			return mapList;
		}
		return null;
	}
	public static List<Map> convertVoFormListToMapList(List<NwCheckRecordForm> checkRecordList) {
		if(checkRecordList != null && checkRecordList.size() > 0) {
			List<Map> mapList = new ArrayList();
			for(int i=0; i<checkRecordList.size(); i++) {
				NwCheckRecordForm entity = checkRecordList.get(i);
				Map map = new HashMap();

				map.put("id", entity.getId() == null ? "" : entity.getId().toString());
				map.put("checkPersonId", entity.getCheckPersonId());
				map.put("checkPerson", entity.getCheckPerson());
				map.put("checkState", entity.getCheckState());
				map.put("checkTime", entity.getCheckTime()!=null? entity.getCheckTime().getTime():null);
				map.put("checkDesription", entity.getCheckDesription());
				map.put("reportType", entity.getReportType());
				map.put("reportId", entity.getReportId());
				map.put("usId", entity.getUsId());
				map.put("orgName",entity.getOrgName());
				map.put("orgId",entity.getOrgId());

				mapList.add(map);
			}
			return mapList;
		}
		return null;
	}

	public static List<NwCheckRecord> convertFormListToVoList(List<NwCheckRecordForm> checkRecordFormList) {
		if(checkRecordFormList != null && checkRecordFormList.size() > 0) {
			List<NwCheckRecord> checkRecordList = new ArrayList();
			for(int i=0; i<checkRecordFormList.size(); i++) {
				NwCheckRecord checkRecord = new NwCheckRecord();
				convertFormToVo(checkRecordFormList.get(i), checkRecord);
				checkRecordList.add(checkRecord);
			}
			return checkRecordList;
		}
		return null;
	}
}