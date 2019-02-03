//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.augurit.ads.fw.common.extmodel;

import java.util.List;

public class ExtResult<T> {
    private boolean success;
    private String message;
    private boolean autoCount = true;
    private int totalItems = 0;
    private List<T> result;

    public ExtResult() {
    }

    public ExtResult(boolean success) {
        this.success = success;
    }

    public ExtResult(boolean success, boolean autoCount) {
        this.success = success;
        this.autoCount = autoCount;
    }

    public void setResult(List result) {
        this.result = result;
        if (this.autoCount) {
            this.totalItems = result != null && result.size() > 0 ? result.size() : 0;
        }

    }

    public List getResult() {
        return this.result;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotalItems() {
        return this.totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
}
