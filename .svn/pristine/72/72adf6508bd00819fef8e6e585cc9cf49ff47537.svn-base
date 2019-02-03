//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.augurit.ads.fw.utils;

import org.springside.modules.orm.PageRequest;

import javax.servlet.http.HttpServletRequest;

public class ExtUtils {
    public ExtUtils() {
    }

    public static void initPageFromExtGridParam(HttpServletRequest request, PageRequest page) {
        String start = request.getParameter("start");
        String limit = request.getParameter("limit");
        String sort = request.getParameter("sort");
        String dir = request.getParameter("dir");
        if (start != null && start.trim().length() > 0 && limit != null && limit.trim().length() > 0) {
            int startNo = Integer.valueOf(start).intValue();
            int limitNo = Integer.valueOf(limit).intValue();
            if (startNo >= 0 && limitNo > 0) {
                page.setPageSize(Integer.valueOf(limit).intValue());
                page.setPageNo(startNo / limitNo + 1);
            }
        }

        if (sort != null && sort.trim().length() > 0) {
            page.setOrderBy(sort);
            if (dir != null && (dir.equalsIgnoreCase("desc") || dir.equalsIgnoreCase("asc"))) {
                page.setOrderDir(dir.toLowerCase());
            }
        }

    }

    public static String removeEmptyChildrenFromTreeNode(String json) {
        return json != null && json.length() > 0 ? json.replace("\"children\":[],", "") : json;
    }

    public static boolean isXmlHttpRequest(HttpServletRequest request) {
        boolean result = false;
        String isXmlHttpRequest = request.getHeader("x-requested-with");
        if (isXmlHttpRequest != null && isXmlHttpRequest.equals("XMLHttpRequest")) {
            result = true;
        }

        return result;
    }

    public static void main(String[] args) {
    }
}
