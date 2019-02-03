package com.augurit.ads.fw.utils;

import org.springside.modules.orm.PageRequest;

public class HqlUtils {
    public static final String RELATIONSHIP_AND = "and";
    public static final String RELATIONSHIP_OR = "or";
    public static final String HQL_SPACE_SEPARATOR = " ";
    public static final String HQL_COMMA_SEPARATOR = ",";
    public static final String HQL_DOT_SEPARATOR = "\\.";

    public HqlUtils() {
    }

    public static String buildOrderBy(PageRequest page) {
        return buildOrderBy(page, (String)null);
    }

    public static String buildOrderBy(PageRequest page, String entityAlias) {
        String hql = "";
        if (entityAlias != null && entityAlias.length() != 0) {
            entityAlias = entityAlias + ".";
        } else {
            entityAlias = "";
        }

        if (page != null && page.getOrderBy() != null && page.getOrderBy().trim().length() > 0) {
            hql = " order by " + entityAlias + page.getOrderBy() + " " + (page.getOrderDir() != null ? page.getOrderDir() : "");
        }

        return hql;
    }

    public static String buildOrCondition(String entityAlias, String property, Long[] values) {
        if (entityAlias != null && property != null && values != null && values.length > 0) {
            String result = " (";

            for(int i = 0; i < values.length; ++i) {
                result = result + entityAlias + "." + property + "=" + values[i];
                if (i < values.length - 1) {
                    result = result + " or ";
                }
            }

            result = result + ")";
            return result;
        } else {
            return "";
        }
    }

    public static String buildOrCondition(String entityAlias, String property, String[] values) {
        if (entityAlias != null && property != null && values != null && values.length > 0) {
            String result = " (";

            for(int i = 0; i < values.length; ++i) {
                result = result + entityAlias + "." + property + "='" + values[i] + "'";
                if (i < values.length - 1) {
                    result = result + " or ";
                }
            }

            result = result + ")";
            return result;
        } else {
            return "";
        }
    }

    public static String buildOrCondition(String entityAlias, String property, int valuesSize) {
        if (entityAlias != null && property != null && valuesSize > 0) {
            String result = " (";

            for(int i = 0; i < valuesSize; ++i) {
                result = result + entityAlias + "." + property + "=?";
                if (i < valuesSize - 1) {
                    result = result + " or ";
                }
            }

            result = result + ")";
            return result;
        } else {
            return "";
        }
    }

    public static String buildLikeCondition(String entityAlias, String property, String[] values, String relationship) {
        if (entityAlias != null && property != null && values != null && values.length > 0) {
            String result = " (";

            for(int i = 0; i < values.length; ++i) {
                result = result + entityAlias + "." + property + " like '%" + values[i] + "%'";
                if (i < values.length - 1) {
                    result = result + " " + relationship + " ";
                }
            }

            result = result + ")";
            return result;
        } else {
            return "";
        }
    }

    public static String buildAndCondition(String entityAlias, String property, Long[] values) {
        if (entityAlias != null && property != null && values != null && values.length > 0) {
            String result = " (";

            for(int i = 0; i < values.length; ++i) {
                result = result + entityAlias + "." + property + "<>" + values[i];
                if (i < values.length - 1) {
                    result = result + " and ";
                }
            }

            result = result + ")";
            return result;
        } else {
            return "";
        }
    }
}
