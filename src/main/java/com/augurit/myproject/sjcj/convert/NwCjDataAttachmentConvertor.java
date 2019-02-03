package com.augurit.myproject.sjcj.convert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.augurit.myproject.sjcj.entity.NwCjDataAttachment;
import com.augurit.myproject.sjcj.web.form.NwCjDataAttachmentForm;

public class NwCjDataAttachmentConvertor {
	public static NwCjDataAttachmentForm convertVoToForm(NwCjDataAttachment entity) {
		if(entity != null) {
			NwCjDataAttachmentForm form = new NwCjDataAttachmentForm();
			form.setId(entity.getId());
			form.setMarkId(entity.getMarkId());
			form.setAttName(entity.getAttName());
			form.setAttTime(entity.getAttTime()!=null ? entity.getAttTime().getTime():null);
			form.setMime(entity.getMime());
			form.setAttPath(entity.getAttPath());
			form.setThumPath(entity.getThumPath());
			return form;
		}else
			return null;
	}
	
	public static void convertFormToVo(NwCjDataAttachmentForm form, NwCjDataAttachment entity) {
		if(entity != null && form != null) {
			entity.setId(form.getId());
			entity.setMarkId(form.getMarkId());
			if(form.getAttName() != null && form.getAttName().trim().length() > 0)
				entity.setAttName(form.getAttName().trim());
			entity.setAttTime(form.getAttTime()!=null ? new Date(form.getAttTime()):null);
			if(form.getMime() != null && form.getMime().trim().length() > 0)
				entity.setMime(form.getMime().trim());
			if(form.getAttPath() != null && form.getAttPath().trim().length() > 0)
				entity.setAttPath(form.getAttPath().trim());
			if(form.getThumPath() != null && form.getThumPath().trim().length() > 0)
				entity.setThumPath(form.getThumPath().trim());
		}
	}
	
	public static List<NwCjDataAttachmentForm> convertVoListToFormList(List<NwCjDataAttachment> nwCjDataAttachmentList) {
		if(nwCjDataAttachmentList != null && nwCjDataAttachmentList.size() > 0) {
			List<NwCjDataAttachmentForm> nwCjDataAttachmentFormList = new ArrayList();
			for(int i=0; i<nwCjDataAttachmentList.size(); i++) {
				nwCjDataAttachmentFormList.add(convertVoToForm(nwCjDataAttachmentList.get(i)));
			}
			getListDesc(nwCjDataAttachmentFormList);
			return nwCjDataAttachmentFormList;
		}
		return null;
	}
	
	public static List<Map> convertVoListToMapList(List<NwCjDataAttachment> nwCjDataAttachmentList) {
		if(nwCjDataAttachmentList != null && nwCjDataAttachmentList.size() > 0) {
			List<Map> mapList = new ArrayList();
			for(int i=0; i<nwCjDataAttachmentList.size(); i++) {
				NwCjDataAttachment entity = nwCjDataAttachmentList.get(i);
				Map map = new HashMap();

				map.put("id", entity.getId() == null ? "" : entity.getId().toString());
				map.put("markId", entity.getMarkId() == null ? "" : entity.getMarkId().toString());
				map.put("attName", entity.getAttName());
				map.put("attTime", entity.getAttTime().getTime());
				map.put("mime", entity.getMime());
				map.put("attPath", entity.getAttPath());
				map.put("thumPath", entity.getThumPath());
				
				mapList.add(map);
			}
			return mapList;
		}
		return null;
	}
	public static List<Map> convertVoListFormToMapList(List<NwCjDataAttachmentForm> nwCjDataAttachmentList) {
		if(nwCjDataAttachmentList != null && nwCjDataAttachmentList.size() > 0) {
			List<Map> mapList = new ArrayList();
			for(int i=0; i<nwCjDataAttachmentList.size(); i++) {
				NwCjDataAttachmentForm entity = nwCjDataAttachmentList.get(i);
				Map map = new HashMap();
				
				map.put("id", entity.getId() == null ? "" : entity.getId().toString());
				map.put("markId", entity.getMarkId() == null ? "" : entity.getMarkId().toString());
				map.put("attName", entity.getAttName());
				map.put("attTime", entity.getAttTime());
				map.put("mime", entity.getMime());
				map.put("attPath", entity.getAttPath());
				map.put("thumPath", entity.getThumPath());
				
				mapList.add(map);
			}
			return mapList;
		}
		return null;
	}
	
	public static List<NwCjDataAttachment> convertFormListToVoList(List<NwCjDataAttachmentForm> nwCjDataAttachmentFormList) {
		if(nwCjDataAttachmentFormList != null && nwCjDataAttachmentFormList.size() > 0) {
			List<NwCjDataAttachment> nwCjDataAttachmentList = new ArrayList();
			for(int i=0; i<nwCjDataAttachmentFormList.size(); i++) {
				NwCjDataAttachment nwCjDataAttachment = new NwCjDataAttachment();
				convertFormToVo(nwCjDataAttachmentFormList.get(i), nwCjDataAttachment);
				nwCjDataAttachmentList.add(nwCjDataAttachment);
			}
			return nwCjDataAttachmentList;
		}
		return null;
	}
	
	public static NwCjDataAttachmentForm convertMapVoForm(Map<String, Object> map) {
		NwCjDataAttachmentForm form = new NwCjDataAttachmentForm();
		if(map.containsKey("id"))
			form.setId(Long.parseLong(map.get("id").toString()));
		if(map.containsKey("markId"))
			form.setMarkId(Long.parseLong(map.get("markId").toString()));
		if(map.containsKey("attName"))
			form.setAttName(map.get("attName").toString());
		if(map.containsKey("attTime"))
			form.setAttTime(Long.parseLong(map.get("attTime").toString()));
		if(map.containsKey("mime"))
			form.setMime(map.get("mime").toString());
		if(map.containsKey("attPath"))
			form.setAttPath(map.get("attPath").toString());
		if(map.containsKey("thumPath"))
			form.setThumPath(map.get("thumPath").toString());
		return form;
	}
	
	public static void getListDesc(List<NwCjDataAttachmentForm> list){
		Collections.sort(list, ageComparatorAttTime);
	}
	
	/**
 	 * List<Map<String,Object>>集合时间排序
 	 * @parama attTime(Long)
 	 * */
 	 public static Comparator ageComparatorAttTime = new Comparator() {  
		@Override
		public int compare(Object o1, Object o2) {
			NwCjDataAttachmentForm m1 = (NwCjDataAttachmentForm) o1;
			NwCjDataAttachmentForm m2 = (NwCjDataAttachmentForm) o2;
			return ( m1.getAttTime() > m2.getAttTime() ? -1 :  
          	  ( m1.getAttTime() == m2.getAttTime() ? 0 : 1));  
		}  
      };  
}