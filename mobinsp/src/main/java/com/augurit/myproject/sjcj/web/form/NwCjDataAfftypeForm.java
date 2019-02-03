package com.augurit.myproject.sjcj.web.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NwCjDataAfftypeForm {
	// 属性
	private Long id;
	private Long markId;
	private Long createTime;
	private String name;
	private String type;
	private String remark;
	private String fieldName;
	private List<NwCjDataAfftypeAttachmentForm> attachment = new ArrayList<>(); //附件

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getMarkId() {
		return this.markId;
	}

	public void setMarkId(Long markId) {
		this.markId = markId;
	}
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	

	public List<NwCjDataAfftypeAttachmentForm> getAttachment() {
		return attachment;
	}

	public void setAttachment(List<NwCjDataAfftypeAttachmentForm> attachment) {
		this.attachment = attachment;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	
	
}