package org.springside.modules.orm;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

public class Page<T> extends PageRequest implements Iterable<T> {
    protected List<T> result = null;
    protected long totalItems = 0L;

    public Page() {
    }

    public Page(PageRequest request) {
        this.pageNo = request.pageNo;
        this.pageSize = request.pageSize;
        this.countTotal = request.countTotal;
        this.orderBy = request.orderBy;
        this.orderDir = request.orderDir;
    }

    public List<T> getResult() {
        return this.result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public long getTotalItems() {
        return this.totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public Iterator<T> iterator() {
        return this.result.iterator();
    }

    public int getTotalPages() {
        return (int)Math.ceil((double)this.totalItems / (double)this.getPageSize());
    }

    public boolean hasNextPage() {
        return this.getPageNo() + 1 <= this.getTotalPages();
    }

    public boolean isLastPage() {
        return !this.hasNextPage();
    }

    public int getNextPage() {
        return this.hasNextPage() ? this.getPageNo() + 1 : this.getPageNo();
    }

    public boolean hasPrePage() {
        return this.getPageNo() > 1;
    }

    public boolean isFirstPage() {
        return !this.hasPrePage();
    }

    public int getPrePage() {
        return this.hasPrePage() ? this.getPageNo() - 1 : this.getPageNo();
    }

    public List<Integer> getSlider(int count) {
        int halfSize = count / 2;
        int totalPage = this.getTotalPages();
        int startPageNo = Math.max(this.getPageNo() - halfSize, 1);
        int endPageNo = Math.min(startPageNo + count - 1, totalPage);
        if (endPageNo - startPageNo < count) {
            startPageNo = Math.max(endPageNo - count, 1);
        }

        List<Integer> result = (List)Lists.newArrayList();

        for(int i = startPageNo; i <= endPageNo; ++i) {
            result.add(i);
        }

        return result;
    }
}
