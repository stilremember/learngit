//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.augurit.ads.fw.common.extmodel.base;

import java.util.List;

public class BaseTreeNode<T> extends ExtElement implements ITreeNode<T> {
    protected boolean leaf;
    protected String qtip;
    protected List<T> children;

    public BaseTreeNode() {
    }

    public static boolean isRootNode(String id) {
        return id != null && id.equals("-1");
    }

    public static boolean isRootNode(Long id) {
        return isRootNode(id != null ? id.toString() : null);
    }

    public boolean isLeaf() {
        return this.leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public String getQtip() {
        return this.qtip;
    }

    public void setQtip(String qtip) {
        this.qtip = qtip;
    }

    public List<T> getChildren() {
        return this.children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }
}
