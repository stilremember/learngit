//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.augurit.ads.fw.common.from;

public class ResultForm<T> {
    private boolean success;
    private T result;
    private String message;
    private String procInstId;

    public ResultForm(boolean success) {
        this.success = success;
    }

    public ResultForm(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResultForm(boolean success, T result) {
        this.success = success;
        this.result = result;
    }

    public void failure(String message) {
        this.success = false;
        this.message = message;
    }

    public T getResult() {
        return this.result;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProcInstId() {
        return this.procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }
}
