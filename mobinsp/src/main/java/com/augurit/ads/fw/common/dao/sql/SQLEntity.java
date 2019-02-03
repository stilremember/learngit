package com.augurit.ads.fw.common.dao.sql;

public class SQLEntity implements SQLTransformer {
    private String alias;
    private Class className;

    public SQLEntity(Class className) {
        this.className = className;
    }

    public SQLEntity(String alias, Class className) {
        this.alias = alias;
        this.className = className;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Class getClassName() {
        return this.className;
    }

    public void setClassName(Class className) {
        this.className = className;
    }
}
