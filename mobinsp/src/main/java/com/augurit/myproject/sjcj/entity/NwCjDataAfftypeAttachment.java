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
@Table(name = "NW_CJ_DATA_AFFTYPE_ATTACHMENT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NwCjDataAfftypeAttachment implements java.io.Serializable {
	
	// 属性
	@Id
    @Column(name = "ID")
    @SequenceGenerator(name="SEQ_NW_CJ_AFFTYPE_ATTACHMENT", sequenceName="SEQ_NW_CJ_AFFTYPE_ATTACHMENT", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_NW_CJ_AFFTYPE_ATTACHMENT")
	private Long id;
	
	private Long affId;
	
	private String attName;
	
	private Date attTime;
	
	private String mime;
	
	private String attPath;
	
	private String thumPath;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getAffId() {
		return this.affId;
	}

	public void setAffId(Long affId) {
		this.affId = affId;
	}
	public String getAttName() {
		return this.attName;
	}

	public void setAttName(String attName) {
		this.attName = attName;
	}
	public Date getAttTime() {
		return this.attTime;
	}

	public void setAttTime(Date attTime) {
		this.attTime = attTime;
	}
	public String getMime() {
		return this.mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}
	public String getAttPath() {
		return this.attPath;
	}

	public void setAttPath(String attPath) {
		this.attPath = attPath;
	}
	public String getThumPath() {
		return this.thumPath;
	}

	public void setThumPath(String thumPath) {
		this.thumPath = thumPath;
	}
}