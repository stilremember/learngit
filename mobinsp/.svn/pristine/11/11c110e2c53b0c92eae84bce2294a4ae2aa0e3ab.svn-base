package org.springside.modules.orm;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springside.modules.utils.AssertUtils;

import java.util.List;

public class PageRequest {
    protected int pageNo = 1;
    protected int pageSize = 10;
    protected String orderBy = null;
    protected String orderDir = null;
    protected boolean countTotal = true;

    public PageRequest() {
    }

    public PageRequest(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
        if (pageNo < 1) {
            this.pageNo = 1;
        }

    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        if (pageSize < 1) {
            this.pageSize = 1;
        }

    }

    public String getOrderBy() {
        return this.orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderDir() {
        return this.orderDir;
    }

    public void setOrderDir(String orderDir) {
        String lowcaseOrderDir = StringUtils.lowerCase(orderDir);
        String[] orderDirs = StringUtils.split(lowcaseOrderDir, ',');
        String[] var7 = orderDirs;
        int var6 = orderDirs.length;

        for(int var5 = 0; var5 < var6; ++var5) {
            String orderDirStr = var7[var5];
            if (!StringUtils.equals("desc", orderDirStr) && !StringUtils.equals("asc", orderDirStr)) {
                throw new IllegalArgumentException("排序方向" + orderDirStr + "不是合法值");
            }
        }

        this.orderDir = lowcaseOrderDir;
    }

    @JsonIgnore
    public List<Sort> getSort() {
        if (!this.isOrderBySetted()) {
            return null;
        } else {
            String[] orderBys = StringUtils.split(this.orderBy, ',');
            String[] orderDirs = StringUtils.split(this.orderDir, ',');
            AssertUtils.isTrue(orderBys.length == orderDirs.length, "分页多重排序参数中,排序字段与排序方向的个数不相等");
            List<Sort> orders = (List)Lists.newArrayList();

            for(int i = 0; i < orderBys.length; ++i) {
                orders.add(new PageRequest.Sort(orderBys[i], orderDirs[i]));
            }

            return orders;
        }
    }

    public boolean isOrderBySetted() {
        return StringUtils.isNotBlank(this.orderBy) && StringUtils.isNotBlank(this.orderDir);
    }

    public boolean isCountTotal() {
        return this.countTotal;
    }

    public void setCountTotal(boolean countTotal) {
        this.countTotal = countTotal;
    }

    public int getOffset() {
        return (this.pageNo - 1) * this.pageSize;
    }

    public static class Sort {
        public static final String ASC = "asc";
        public static final String DESC = "desc";
        private final String property;
        private final String dir;

        public Sort(String property, String dir) {
            this.property = property;
            this.dir = dir;
        }

        public String getProperty() {
            return this.property;
        }

        public String getDir() {
            return this.dir;
        }
    }
}
