package org.springside.modules.orm.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.*;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PageRequest;
import org.springside.modules.orm.PageRequest.Sort;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.PropertyFilter.MatchType;
import org.springside.modules.utils.AssertUtils;
import org.springside.modules.utils.ReflectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HibernateDao<T, ID extends Serializable> extends SimpleHibernateDao<T, ID> {
    public static final String DEFAULT_ALIAS = "ps";

    public HibernateDao() {
    }

    public HibernateDao(Class<T> entityClass) {
        super(entityClass);
    }

    public Page<T> getAll(PageRequest pageRequest) {
        return this.findPage(pageRequest);
    }

    public Page<T> findPage(PageRequest pageRequest, String hql, Object... values) {
        AssertUtils.notNull(pageRequest, "pageRequest不能为空");
        Page<T> page = new Page(pageRequest);
        if (pageRequest.isCountTotal()) {
            long totalCount = this.countHqlResult(hql, values);
            page.setTotalItems(totalCount);
        }

        if (pageRequest.isOrderBySetted()) {
            hql = this.setOrderParameterToHql(hql, pageRequest);
        }

        Query q = this.createQuery(hql, values);
        this.setPageParameterToQuery(q, pageRequest);
        List result = q.list();
        page.setResult(result);
        return page;
    }

    public Page<T> findPage(PageRequest pageRequest, String hql, Map<String, ?> values) {
        AssertUtils.notNull(pageRequest, "page不能为空");
        Page<T> page = new Page(pageRequest);
        if (pageRequest.isCountTotal()) {
            long totalCount = this.countHqlResult(hql, values);
            page.setTotalItems(totalCount);
        }

        if (pageRequest.isOrderBySetted()) {
            hql = this.setOrderParameterToHql(hql, pageRequest);
        }

        Query q = this.createQuery(hql, values);
        this.setPageParameterToQuery(q, pageRequest);
        List result = q.list();
        page.setResult(result);
        return page;
    }

    public Page<T> findPage(PageRequest pageRequest, Criterion... criterions) {
        AssertUtils.notNull(pageRequest, "page不能为空");
        Page<T> page = new Page(pageRequest);
        Criteria c = this.createCriteria(criterions);
        if (pageRequest.isCountTotal()) {
            long totalCount = this.countCriteriaResult(c);
            page.setTotalItems(totalCount);
        }

        this.setPageRequestToCriteria(c, pageRequest);
        List result = c.list();
        page.setResult(result);
        return page;
    }

    protected String setOrderParameterToHql(String hql, PageRequest pageRequest) {
        if (!pageRequest.isOrderBySetted()) {
            return hql;
        } else {
            StringBuilder builder = new StringBuilder(hql);
            builder.append(" order by");
            Iterator var5 = pageRequest.getSort().iterator();

            while(var5.hasNext()) {
                Sort orderBy = (Sort)var5.next();
                builder.append(String.format(" %s.%s %s,", "ps", orderBy.getProperty(), orderBy.getDir()));
            }

            builder.deleteCharAt(builder.length() - 1);
            return builder.toString();
        }
    }

    protected Query setPageParameterToQuery(Query q, PageRequest pageRequest) {
        q.setFirstResult(pageRequest.getOffset());
        q.setMaxResults(pageRequest.getPageSize());
        return q;
    }

    protected Criteria setPageRequestToCriteria(Criteria c, PageRequest pageRequest) {
        AssertUtils.isTrue(pageRequest.getPageSize() > 0, "Page Size must larger than zero");
        c.setFirstResult(pageRequest.getOffset());
        c.setMaxResults(pageRequest.getPageSize());
        if (pageRequest.isOrderBySetted()) {
            Iterator var4 = pageRequest.getSort().iterator();

            while(var4.hasNext()) {
                Sort sort = (Sort)var4.next();
                if ("asc".equals(sort.getDir())) {
                    c.addOrder(Order.asc(sort.getProperty()));
                } else {
                    c.addOrder(Order.desc(sort.getProperty()));
                }
            }
        }

        return c;
    }

    protected long countHqlResult(String hql, Object... values) {
        String countHql = this.prepareCountHql(hql);

        try {
            Long count = (Long)this.findUnique(countHql, values);
            return count.longValue();
        } catch (Exception var5) {
            throw new RuntimeException("hql can't be auto count, hql is:" + countHql, var5);
        }
    }

    protected long countHqlResult(String hql, Map<String, ?> values) {
        String countHql = this.prepareCountHql(hql);

        try {
            Long count = (Long)this.findUnique(countHql, values);
            return count.longValue();
        } catch (Exception var5) {
            throw new RuntimeException("hql can't be auto count, hql is:" + countHql, var5);
        }
    }

    protected String prepareCountHql(String orgHql) {
        String countHql = "select count (*) " + removeSelect(removeOrders(orgHql));
        return countHql;
    }

    protected String prepareCountSql(String orgSql) {
        String countHql = "select count (*) from (" + removeOrders(orgSql) + ")";
        return countHql;
    }

    private static String removeSelect(String hql) {
        int beginPos = hql.toLowerCase().indexOf("from");
        return hql.substring(beginPos);
    }

    private static String removeOrders(String hql) {
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", 2);
        Matcher m = p.matcher(hql);
        StringBuffer sb = new StringBuffer();

        while(m.find()) {
            m.appendReplacement(sb, "");
        }

        m.appendTail(sb);
        return sb.toString();
    }

    protected long countCriteriaResult(Criteria c) {
        CriteriaImpl impl = (CriteriaImpl)c;
        Projection projection = impl.getProjection();
        ResultTransformer transformer = impl.getResultTransformer();
        List orderEntries = null;

        try {
            orderEntries = (List)ReflectionUtils.getFieldValue(impl, "orderEntries");
            ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList());
        } catch (Exception var11) {
            this.logger.error("不可能抛出的异常:{}", var11.getMessage());
        }

        Object totalCountObject = c.setProjection(Projections.rowCount()).uniqueResult();
        long totalCount = totalCountObject != null ? Long.valueOf(totalCountObject.toString()).longValue() : 0L;
        c.setProjection(projection);
        if (projection == null) {
            c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }

        if (transformer != null) {
            c.setResultTransformer(transformer);
        }

        try {
            ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);
        } catch (Exception var10) {
            this.logger.error("不可能抛出的异常:{}", var10.getMessage());
        }

        return totalCount;
    }

    public List<T> findBy(String propertyName, Object value, MatchType matchType) {
        Criterion criterion = this.buildCriterion(propertyName, value, matchType);
        return this.find(new Criterion[]{criterion});
    }

    public List<T> find(List<PropertyFilter> filters) {
        Criterion[] criterions = this.buildCriterionByPropertyFilter(filters);
        return this.find(criterions);
    }

    public Page<T> findPage(PageRequest pageRequest, List<PropertyFilter> filters) {
        Criterion[] criterions = this.buildCriterionByPropertyFilter(filters);
        return this.findPage(pageRequest, criterions);
    }

    protected Criterion buildCriterion(String propertyName, Object propertyValue, MatchType matchType) {
        AssertUtils.hasText(propertyName, "propertyName不能为空");
        Criterion criterion = null;
        switch(matchType.ordinal()) {
            case 1:
                criterion = Restrictions.eq(propertyName, propertyValue);
                break;
            case 2:
                criterion = Restrictions.like(propertyName, (String)propertyValue, MatchMode.ANYWHERE);
                break;
            case 3:
                criterion = Restrictions.lt(propertyName, propertyValue);
                break;
            case 4:
                criterion = Restrictions.gt(propertyName, propertyValue);
                break;
            case 5:
                criterion = Restrictions.le(propertyName, propertyValue);
                break;
            case 6:
                criterion = Restrictions.ge(propertyName, propertyValue);
        }

        return criterion;
    }

    protected Criterion[] buildCriterionByPropertyFilter(List<PropertyFilter> filters) {
        List<Criterion> criterionList = new ArrayList();
        Iterator var4 = filters.iterator();

        while(true) {
            while(var4.hasNext()) {
                PropertyFilter filter = (PropertyFilter)var4.next();
                if (!filter.hasMultiProperties()) {
                    Criterion criterion = this.buildCriterion(filter.getPropertyName(), filter.getMatchValue(), filter.getMatchType());
                    criterionList.add(criterion);
                } else {
                    Disjunction disjunction = Restrictions.disjunction();
                    String[] var9;
                    int var8 = (var9 = filter.getPropertyNames()).length;

                    for(int var7 = 0; var7 < var8; ++var7) {
                        String param = var9[var7];
                        Criterion criterion = this.buildCriterion(param, filter.getMatchValue(), filter.getMatchType());
                        disjunction.add(criterion);
                    }

                    criterionList.add(disjunction);
                }
            }

            return (Criterion[])criterionList.toArray(new Criterion[criterionList.size()]);
        }
    }
}
