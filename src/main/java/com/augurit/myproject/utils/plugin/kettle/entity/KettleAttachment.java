package com.augurit.myproject.utils.plugin.kettle.entity;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "PS_KETTLE_ATTACHMENT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KettleAttachment {


    // 属性
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name="SEQ_PS_KETTLE_ATTACHMENT", sequenceName = "SEQ_PS_KETTLE_ATTACHMENT",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PS_KETTLE_ATTACHMENT")
    private Long id;
    private Long fileId;
    private String version;
    private String content;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
