package com.augurit.ads.fw.common.dao.sql;

import org.hibernate.type.Type;

public class SQLScalar implements SQLTransformer {
    private String column;
    private Type type;

    public SQLScalar(String column) {
        this.column = column;
    }

    public SQLScalar(String column, Type type) {
        this.column = column;
        this.type = type;
    }

    public String getColumn() {
        return this.column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
