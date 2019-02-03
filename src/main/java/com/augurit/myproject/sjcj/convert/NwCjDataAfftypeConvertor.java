package com.augurit.myproject.sjcj.convert;

import com.augurit.myproject.sjcj.entity.NwCjDataAfftype;
import com.augurit.myproject.sjcj.web.form.NwCjDataAfftypeAttachmentForm;
import com.augurit.myproject.sjcj.web.form.NwCjDataAfftypeForm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NwCjDataAfftypeConvertor {
	public static NwCjDataAfftypeForm convertVoToForm(NwCjDataAfftype entity) {
		if(entity != null) {
			NwCjDataAfftypeForm form = new NwCjDataAfftypeForm();
			form.setId(entity.getId());
			form.setMarkId(entity.getMarkId());
			form.setName(entity.getName());
			form.setType(entity.getType());
			form.setRemark(entity.getRemark());
			form.setCreateTime(entity.getCreateTime().getTime());
			return form;
		}else
			return null;
	}
	
	public static void convertFormToVo(NwCjDataAfftypeForm form, NwCjDataAfftype entity) {
		if(entity != null && form != null) {
			entity.setId(form.getId());
			if(form.getMarkId()!=null)
				entity.setMarkId(form.getMarkId());
			if(form.getName() != null && form.getName().trim().length() > 0)
				entity.setName(form.getName().trim());
			if(form.getType() != null && form.getType().trim().length() > 0)
				entity.setType(form.getType().trim());
			if(form.getRemark() != null && form.getRemark().trim().length() > 0)
				entity.setRemark(form.getRemark().trim());
			if(form.getCreateTime()!=null)
				entity.setCreateTime(new Date(form.getCreateTime()));
		}
	}
	
	public static List<NwCjDataAfftypeForm> convertVoListToFormList(List<NwCjDataAfftype> nwCjDataAfftypeList) {
		if(nwCjDataAfftypeList != null && nwCjDataAfftypeList.size() > 0) {
			List<NwCjDataAfftypeForm> nwCjDataAfftypeFormList = new ArrayList();
			for(int i=0; i<nwCjDataAfftypeList.size(); i++) {
				nwCjDataAfftypeFormList.add(convertVoToForm(nwCjDataAfftypeList.get(i)));
			}
			return nwCjDataAfftypeFormList;
		}
		return null;
	}
	
	public static List<Map> convertVoListToMapList(List<NwCjDataAfftype> nwCjDataAfftypeList) {
		if(nwCjDataAfftypeList != null && nwCjDataAfftypeList.size() > 0) {
			List<Map> mapList = new ArrayList();
			for(int i=0; i<nwCjDataAfftypeList.size(); i++) {
				NwCjDataAfftype entity = nwCjDataAfftypeList.get(i);
				Map map = new HashMap();

				map.put("id", entity.getId() == null ? "" : entity.getId().toString());
				map.put("markId", entity.getMarkId() == null ? "" : entity.getMarkId().toString());
				map.put("name", entity.getName());
				map.put("type", entity.getType());
				map.put("remark", entity.getRemark());
				map.put("createTime", entity.getCreateTime());
				mapList.add(map);
			}
			return mapList;
		}
		return null;
	}
	
	public static List<NwCjDataAfftype> convertFormListToVoList(List<NwCjDataAfftypeForm> nwCjDataAfftypeFormList) {
		if(nwCjDataAfftypeFormList != null && nwCjDataAfftypeFormList.size() > 0) {
			List<NwCjDataAfftype> nwCjDataAfftypeList = new ArrayList();
			for(int i=0; i<nwCjDataAfftypeFormList.size(); i++) {
				NwCjDataAfftype nwCjDataAfftype = new NwCjDataAfftype();
				convertFormToVo(nwCjDataAfftypeFormList.get(i), nwCjDataAfftype);
				nwCjDataAfftypeList.add(nwCjDataAfftype);
			}
			return nwCjDataAfftypeList;
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