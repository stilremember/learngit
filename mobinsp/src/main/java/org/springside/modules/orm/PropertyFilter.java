package org.springside.modules.orm;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.mapper.ObjectMapper;
import org.springside.modules.utils.AssertUtils;
import org.springside.modules.utils.web.ServletUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.Map.Entry;

public class PropertyFilter {
    public static final String OR_SEPARATOR = "_OR_";
    private PropertyFilter.MatchType matchType = null;
    private Object matchValue = null;
    private Class<?> propertyClass = null;
    private String[] propertyNames = null;

    public PropertyFilter() {
    }

    public PropertyFilter(String filterName, String value) {
        String firstPart = StringUtils.substringBefore(filterName, "_");
        String matchTypeCode = StringUtils.substring(firstPart, 0, firstPart.length() - 1);
        String propertyTypeCode = StringUtils.substring(firstPart, firstPart.length() - 1, firstPart.length());

        try {
            this.matchType = (PropertyFilter.MatchType)Enum.valueOf(PropertyFilter.MatchType.class, matchTypeCode);
        } catch (RuntimeException var8) {
            throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性比较类型.", var8);
        }

        try {
            this.propertyClass = ((PropertyFilter.PropertyType)Enum.valueOf(PropertyFilter.PropertyType.class, propertyTypeCode)).getValue();
        } catch (RuntimeException var7) {
            throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性值类型.", var7);
        }

        String propertyNameStr = StringUtils.substringAfter(filterName, "_");
        AssertUtils.isTrue(StringUtils.isNotBlank(propertyNameStr), "filter名称" + filterName + "没有按规则编写,无法得到属性名称.");
        this.propertyNames = StringUtils.splitByWholeSeparator(propertyNameStr, "_OR_");
        this.matchValue = ObjectMapper.convertToObject(value, this.propertyClass);
    }

    public static List<PropertyFilter> buildFromHttpRequest(HttpServletRequest request) {
        return buildFromHttpRequest(request, "filter");
    }

    public static List<PropertyFilter> buildFromHttpRequest(HttpServletRequest request, String filterPrefix) {
        List<PropertyFilter> filterList = new ArrayList();
        Map<String, Object> filterParamMap = ServletUtils.getParametersStartingWith(request, filterPrefix + "_");
        Iterator var5 = filterParamMap.entrySet().iterator();

        while(var5.hasNext()) {
            Entry<String, Object> entry = (Entry)var5.next();
            String filterName = (String)entry.getKey();
            String value = (String)entry.getValue();
            if (StringUtils.isNotBlank(value)) {
                PropertyFilter filter = new PropertyFilter(filterName, value);
                filterList.add(filter);
            }
        }

        return filterList;
    }

    public Class<?> getPropertyClass() {
        return this.propertyClass;
    }

    public PropertyFilter.MatchType getMatchType() {
        return this.matchType;
    }

    public Object getMatchValue() {
        return this.matchValue;
    }

    public String[] getPropertyNames() {
        return this.propertyNames;
    }

    public String getPropertyName() {
        AssertUtils.isTrue(this.propertyNames.length == 1, "There are not only one property in this filter.");
        return this.propertyNames[0];
    }

    public boolean hasMultiProperties() {
        return this.propertyNames.length > 1;
    }

    public static enum MatchType {
        EQ,
        LIKE,
        LT,
        GT,
        LE,
        GE;

        private MatchType() {
        }
    }

    public static enum PropertyType {
        S(String.class),
        I(Integer.class),
        L(Long.class),
        N(Double.class),
        D(Date.class),
        B(Boolean.class);

        private Class<?> clazz;

        private PropertyType(Class<?> clazz) {
            this.clazz = clazz;
        }

        public Class<?> getValue() {
            return this.clazz;
        }
    }
}
