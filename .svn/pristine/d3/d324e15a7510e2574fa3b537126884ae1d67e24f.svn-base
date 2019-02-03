package com.augurit.ads.fw.common.dao;

import com.augurit.ads.fw.common.dao.sql.SQLEntity;
import com.augurit.ads.fw.common.dao.sql.SQLScalar;
import com.augurit.ads.fw.common.dao.sql.SQLTransformer;
import com.augurit.ads.fw.utils.lang.CollectionUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PageRequest;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.utils.AssertUtils;

public class BaseDao<T, ID extends Serializable> extends HibernateDao<T, ID> {
    public BaseDao() {
    }

    public T find(ID id) {
        AssertUtils.notNull(id, "id不能为空");
        return (T)this.getSession().get(this.entityClass, id);
    }

    public void save(List<T> list) {
        AssertUtils.notEmpty(list, "entities不能为空");
        Iterator var3 = list.iterator();

        while(var3.hasNext()) {
            T entity = (T)var3.next();
            this.save(entity);
        }

    }

    public void delete(List<T> list) {
        AssertUtils.notEmpty(list, "entities不能为空");
        Iterator var3 = list.iterator();

        while(var3.hasNext()) {
            T entity = (T)var3.next();
            this.delete(entity);
        }

    }

    public void delete(ID... ids) {
        AssertUtils.notNull(ids, "ids不能为空");
        Serializable[] var5 = ids;
        int var4 = ids.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            ID id = (ID)var5[var3];
            this.delete(id);
        }

    }

    public Page<T> findPage(PageRequest pageRequest, String hql, List values) {
        return this.findPage(pageRequest, hql, CollectionUtils.toObjectArray(values));
    }

    public <X> List<X> find(String hql, List values) {
        return this.createQuery(hql, CollectionUtils.toObjectArray(values)).list();
    }

    public Page<T> findPageInScrollMode(PageRequest pageRequest, String hql, Map<String, ?> values) {
        AssertUtils.notNull(pageRequest, "pageRequest不能为空");
        Page<T> page = new Page(pageRequest);
        Query query = this.createQuery(hql, values);
        ScrollableResults scrollableResults = query.scroll(ScrollMode.SCROLL_SENSITIVE);
        scrollableResults.last();
        page.setTotalItems((long)(scrollableResults.getRowNumber() + 1));
        this.setPageParameterToQuery(query, pageRequest);
        List result = query.list();
        page.setResult(result);
        return page;
    }

    public Page findPageBySql(PageRequest pageRequest, String sql, List<SQLTransformer> transformers, List values) {
        return this.findPageBySql(pageRequest, sql, transformers, CollectionUtils.toObjectArray(values));
    }

    public Page findPageBySql(PageRequest pageRequest, String sql, List<SQLTransformer> transformers, Object... values) {
        AssertUtils.notNull(pageRequest, "pageRequest不能为空");
        AssertUtils.hasText(sql, "SQL不能为空");
        Page page = new Page(pageRequest);
        if (pageRequest.isCountTotal()) {
            long totalItems = this.countSqlResult(sql, values);
            page.setTotalItems(totalItems);
        }

        SQLQuery query = this.createSQLQuery(sql, values);
        if (transformers != null && transformers.size() > 0) {
            Iterator var8 = transformers.iterator();

            label71:
            while(true) {
                while(true) {
                    while(true) {
                        if (!var8.hasNext()) {
                            break label71;
                        }

                        SQLTransformer t = (SQLTransformer)var8.next();
                        if (t instanceof SQLEntity) {
                            SQLEntity e = (SQLEntity)t;
                            if (e.getAlias() != null && e.getAlias().trim().length() > 0 && e.getClassName() != null) {
                                query.addEntity(e.getAlias(), e.getClassName());
                            } else if ((e.getAlias() == null || e.getAlias().trim().length() == 0) && e.getClassName() != null) {
                                query.addEntity(e.getClassName());
                            } else if (e.getAlias() != null && e.getAlias().trim().length() > 0 && e.getClassName() == null) {
                                query.addEntity(e.getAlias());
                            } else {
                                AssertUtils.isTrue(false, "SQLEntity不正确");
                            }
                        } else if (t instanceof SQLScalar) {
                            SQLScalar s = (SQLScalar)t;
                            if (s.getColumn() != null && s.getColumn().trim().length() > 0 && s.getType() != null) {
                                query.addScalar(s.getColumn(), s.getType());
                            } else if (s.getColumn() != null && s.getColumn().trim().length() > 0 && s.getType() == null) {
                                query.addScalar(s.getColumn());
                            } else {
                                AssertUtils.isTrue(false, "SQLScalar不正确");
                            }
                        }
                    }
                }
            }
        }

        page.setResult(query.setFirstResult(page.getOffset()).setMaxResults(page.getPageSize()).list());
        return page;
    }

    public Page findPageBySql(PageRequest pageRequest, String sql, List<SQLTransformer> transformers, Map<String, Object> values) {
        AssertUtils.notNull(pageRequest, "pageRequest不能为空");
        AssertUtils.hasText(sql, "SQL不能为空");
        Page page = new Page(pageRequest);
        if (pageRequest.isCountTotal()) {
            long totalItems = this.countSqlResult(sql, values);
            page.setTotalItems(totalItems);
        }

        SQLQuery query = this.createSQLQuery(sql, values);
        if (transformers != null && transformers.size() > 0) {
            Iterator var8 = transformers.iterator();

            label71:
            while(true) {
                while(true) {
                    while(true) {
                        if (!var8.hasNext()) {
                            break label71;
                        }

                        SQLTransformer t = (SQLTransformer)var8.next();
                        if (t instanceof SQLEntity) {
                            SQLEntity e = (SQLEntity)t;
                            if (e.getAlias() != null && e.getAlias().trim().length() > 0 && e.getClassName() != null) {
                                query.addEntity(e.getAlias(), e.getClassName());
                            } else if ((e.getAlias() == null || e.getAlias().trim().length() == 0) && e.getClassName() != null) {
                                query.addEntity(e.getClassName());
                            } else if (e.getAlias() != null && e.getAlias().trim().length() > 0 && e.getClassName() == null) {
                                query.addEntity(e.getAlias());
                            } else {
                                AssertUtils.isTrue(false, "SQLEntity不正确");
                            }
                        } else if (t instanceof SQLScalar) {
                            SQLScalar s = (SQLScalar)t;
                            if (s.getColumn() != null && s.getColumn().trim().length() > 0 && s.getType() != null) {
                                query.addScalar(s.getColumn(), s.getType());
                            } else if (s.getColumn() != null && s.getColumn().trim().length() > 0 && s.getType() == null) {
                                query.addScalar(s.getColumn());
                            } else {
                                AssertUtils.isTrue(false, "SQLScalar不正确");
                            }
                        }
                    }
                }
            }
        }

        page.setResult(query.setFirstResult(page.getOffset()).setMaxResults(page.getPageSize()).list());
        return page;
    }

    public long countSqlResult(String sql, Object... values) {
        AssertUtils.hasText(sql, "SQL不能为空");
        String countSql = this.prepareCountSql(sql);

        try {
            BigDecimal count = (BigDecimal)this.createSQLQuery(countSql, values).uniqueResult();
            return count.longValue();
        } catch (Exception var5) {
            throw new RuntimeException("sql can't be auto count, sql is:" + countSql, var5);
        }
    }

    public long countSqlResult(String sql, Map<String, Object> values) {
        AssertUtils.hasText(sql, "SQL不能为空");
        String countSql = this.prepareCountSql(sql);

        try {
            BigDecimal count = (BigDecimal)this.createSQLQuery(countSql, values).uniqueResult();
            return count.longValue();
        } catch (Exception var5) {
            throw new RuntimeException("sql can't be auto count, sql is:" + countSql, var5);
        }
    }

    public SQLQuery createSQLQuery(String sql, Object... values) {
        AssertUtils.hasText(sql, "SQL不能为空");
        SQLQuery query = this.getSession().createSQLQuery(sql);
        if (values != null) {
            for(int i = 0; i < values.length; ++i) {
                query.setParameter(i, values[i]);
            }
        }

        return query;
    }

    public SQLQuery createSQLQuery(String sql, Map<String, Object> values) {
        AssertUtils.hasText(sql, "SQL不能为空");
        SQLQuery query = this.getSession().createSQLQuery(sql);
        if (values != null) {
            Iterator it = values.entrySet().iterator();

            while(it.hasNext()) {
                Entry<String, Object> parameter = (Entry)it.next();
                query.setParameter((String)parameter.getKey(), parameter.getValue());
            }
        }

        return query;
    }

    public void clear() {
        this.getSession().clear();
    }

    public T get(ID id) {
        AssertUtils.notNull(id, "id不能为空");
        T entity = (T)this.getSession().get(this.entityClass, id);
        return entity;
    }

    public T load(ID id) {
        AssertUtils.notNull(id, "id不能为空");
        T entity = (T)this.getSession().load(this.entityClass, id);
        return entity;
    }
    /**
     * 按HQL查询对象列表.（只返回第一个结果）
     *
     * @param hql
     * @return
     */
    public T findFirst(final String hql) {
        List<T> result = this.createQuery(hql).list();
        if (result.size() > 0)
            return (T) result.get(0);
        else
            return null;
    }
}