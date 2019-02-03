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
@Table(name = "NW_CJ_DATA_AFFTYPE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NwCjDataAfftype implements java.io.Serializable {
	
	// 属性
	@Id
    @Column(name = "ID")
    @SequenceGenerator(name="SEQ_NW_CJ_DATA_AFFTYPE", sequenceName="SEQ_NW_CJ_DATA_AFFTYPE", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_NW_CJ_DATA_AFFTYPE")
	private Long id;
	
	private Long markId;
	
	private String name;
	
	private String type;
	
	private String remark;
	
	private Date createTime;

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}