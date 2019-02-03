package com.augurit.myproject.sjcj.convert;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.augurit.myproject.sjcj.entity.NwCjDataAfftypeAttachment;
import com.augurit.myproject.sjcj.web.form.NwCjDataAfftypeAttachmentForm;

public class NwCjDataAfftypeAttachmentConvertor {
	public static NwCjDataAfftypeAttachmentForm convertVoToForm(NwCjDataAfftypeAttachment entity) {
		if(entity != null) {
			NwCjDataAfftypeAttachmentForm form = new NwCjDataAfftypeAttachmentForm();
			form.setId(entity.getId());
			form.setAffId(entity.getAffId());
			form.setAttName(entity.getAttName());
			form.setAttTime(entity.getAttTime().getTime());
			form.setMime(entity.getMime());
			form.setAttPath(entity.getAttPath());
			form.setThumPath(entity.getThumPath());
			return form;
		}else
			return null;
	}
	
	public static void convertFormToVo(NwCjDataAfftypeAttachmentForm form, NwCjDataAfftypeAttachment entity) {
		if(entity != null && form != null) {
			entity.setId(form.getId());
			entity.setAffId(form.getAffId());
			if(form.getAttName() != null && form.getAttName().trim().length() > 0)
				entity.setAttName(form.getAttName().trim());
			if(form.getAttTime()!=null)
				entity.setAttTime(new Date(form.getAttTime()));
			if(form.getMime() != null && form.getMime().trim().length() > 0)
				entity.setMime(form.getMime().trim());
			if(form.getAttPath() != null && form.getAttPath().trim().length() > 0)
				entity.setAttPath(form.getAttPath().trim());
			if(form.getThumPath() != null && form.getThumPath().trim().length() > 0)
				entity.setThumPath(form.getThumPath().trim());
		}
	}
	
	public static List<NwCjDataAfftypeAttachmentForm> convertVoListToFormList(List<NwCjDataAfftypeAttachment> nwCjDataAfftypeAttachmentList) {
		if(nwCjDataAfftypeAttachmentList != null && nwCjDataAfftypeAttachmentList.size() > 0) {
			List<NwCjDataAfftypeAttachmentForm> nwCjDataAfftypeAttachmentFormList = new ArrayList();
			for(int i=0; i<nwCjDataAfftypeAttachmentList.size(); i++) {
				nwCjDataAfftypeAttachmentFormList.add(convertVoToForm(nwCjDataAfftypeAttachmentList.get(i)));
			}
			return nwCjDataAfftypeAttachmentFormList;
		}
		return null;
	}
	
	public static List<Map> convertVoListToMapList(List<NwCjDataAfftypeAttachment> nwCjDataAfftypeAttachmentList) {
		if(nwCjDataAfftypeAttachmentList != null && nwCjDataAfftypeAttachmentList.size() > 0) {
			List<Map> mapList = new ArrayList();
			for(int i=0; i<nwCjDataAfftypeAttachmentList.size(); i++) {
				NwCjDataAfftypeAttachment entity = nwCjDataAfftypeAttachmentList.get(i);
				Map map = new HashMap();

				map.put("id", entity.getId() == null ? "" : entity.getId().toString());
				map.put("affId", entity.getAffId() == null ? "" : entity.getAffId().toString());
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
	
	public static List<NwCjDataAfftypeAttachment> convertFormListToVoList(List<NwCjDataAfftypeAttachmentForm> nwCjDataAfftypeAttachmentFormList) {
		if(nwCjDataAfftypeAttachmentFormList != null && nwCjDataAfftypeAttachmentFormList.size() > 0) {
			List<NwCjDataAfftypeAttachment> nwCjDataAfftypeAttachmentList = new ArrayList();
			for(int i=0; i<nwCjDataAfftypeAttachmentFormList.size(); i++) {
				NwCjDataAfftypeAttachment nwCjDataAfftypeAttachment = new NwCjDataAfftypeAttachment();
				convertFormToVo(nwCjDataAfftypeAttachmentFormList.get(i), nwCjDataAfftypeAttachment);
				nwCjDataAfftypeAttachmentList.add(nwCjDataAfftypeAttachment);
			}
			return nwCjDataAfftypeAttachmentList;
		}
		return null;
	}

	public static NwCjDataAfftypeAttachmentForm convertMapVoForm(Map<String, Object> map) {
		NwCjDataAfftypeAttachmentForm form = new NwCjDataAfftypeAttachmentForm();
		if(map.containsKey("id"))
			form.setId(Long.parseLong(map.get("id").toString()));
		if(map.containsKey("affId"))
			form.setAffId(Long.parseLong(map.get("affId").toString()));
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
}