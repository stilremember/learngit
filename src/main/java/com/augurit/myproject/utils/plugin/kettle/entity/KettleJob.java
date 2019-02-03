package com.augurit.myproject.utils.plugin.kettle.entity;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "PS_KETTLE_JOB")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KettleJob implements java.io.Serializable{
    private static final long serialVersionUID = -546885098407989821L;

    // 属性
    @Id
    @Column(name = "ID")
    @SequenceGenerator(name="SEQ_PS_KETTLE_JOB", sequenceName = "SEQ_PS_KETTLE_JOB",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PS_KETTLE_JOB")
    private Long id;
    private String name;
    private String fileName;
    private String filePath;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
