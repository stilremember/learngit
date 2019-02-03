package org.springside.modules.orm.hibernate;

import org.springside.modules.utils.AssertUtils;
import org.springside.modules.utils.ReflectionUtils;
import org.hibernate.*;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SimpleHibernateDao<T, ID extends Serializable> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected SessionFactory sessionFactory;
    protected Class<T> entityClass;

    public SimpleHibernateDao() {
        this.entityClass = ReflectionUtils.getSuperClassGenricType(this.getClass());
    }

    public SimpleHibernateDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public void save(T entity) {
        AssertUtils.notNull(entity, "entity不能为空");
        this.getSession().saveOrUpdate(entity);
        this.logger.debug("save entity: {}", entity);
    }

    public void delete(T entity) {
        AssertUtils.notNull(entity, "entity不能为空");
        this.getSession().delete(entity);
        this.logger.debug("delete entity: {}", entity);
    }

    public void delete(ID id) {
        AssertUtils.notNull(id, "id不能为空");
        this.delete(this.get(id));
        this.logger.debug("delete entity {},id is {}", this.entityClass.getSimpleName(), id);
    }

    public T get(ID id) {
        AssertUtils.notNull(id, "id不能为空");
        return (T)this.getSession().get(this.entityClass, id);
    }

    public List<T> get(Collection<ID> ids) {
        return this.find(Restrictions.in(this.getIdName(), ids));
    }

    public List<T> getAll() {
        return this.find();
    }

    public List<T> getAll(String orderByProperty, boolean isAsc) {
        Criteria c = this.createCriteria();
        if (isAsc) {
            c.addOrder(Order.asc(orderByProperty));
        } else {
            c.addOrder(Order.desc(orderByProperty));
        }

        return c.list();
    }

    public List<T> findBy(String propertyName, Object value) {
        AssertUtils.hasText(propertyName, "propertyName不能为空");
        Criterion criterion = Restrictions.eq(propertyName, value);
        return this.find(criterion);
    }

    public T findUniqueBy(String propertyName, Object value) {
        AssertUtils.hasText(propertyName, "propertyName不能为空");
        Criterion criterion = Restrictions.eq(propertyName, value);
        return (T)this.createCriteria(criterion).uniqueResult();
    }

    public <X> List<X> find(String hql, Object... values) {
        return this.createQuery(hql, values).list();
    }

    public <X> List<X> find(String hql, Map<String, ?> values) {
        return this.createQuery(hql, values).list();
    }

    public <X> X findUnique(String hql, Object... values) {
        return (X)this.createQuery(hql, values).uniqueResult();
    }

    public <X> X findUnique(String hql, Map<String, ?> values) {
        return (X)this.createQuery(hql, values).uniqueResult();
    }

    public int batchExecute(String hql, Object... values) {
        return this.createQuery(hql, values).executeUpdate();
    }

    public int batchExecute(String hql, Map<String, ?> values) {
        return this.createQuery(hql, values).executeUpdate();
    }

    public Query createQuery(String queryString, Object... values) {
        AssertUtils.hasText(queryString, "queryString不能为空");
        Query query = this.getSession().createQuery(queryString);
        if (values != null) {
            for(int i = 0; i < values.length; ++i) {
                query.setParameter(i, values[i]);
            }
        }

        return query;
    }

    public Query createQuery(String queryString, Map<String, ?> values) {
        AssertUtils.hasText(queryString, "queryString不能为空");
        Query query = this.getSession().createQuery(queryString);
        if (values != null) {
            query.setProperties(values);
        }

        return query;
    }

    public List<T> find(Criterion... criterions) {
        return this.createCriteria(criterions).list();
    }

    public T findUnique(Criterion... criterions) {
        return (T)this.createCriteria(criterions).uniqueResult();
    }

    public Criteria createCriteria(Criterion... criterions) {
        Criteria criteria = this.getSession().createCriteria(this.entityClass);
        Criterion[] var6 = criterions;
        int var5 = criterions.length;

        for(int var4 = 0; var4 < var5; ++var4) {
            Criterion c = var6[var4];
            criteria.add(c);
        }

        return criteria;
    }

    public void initProxyObject(Object proxy) {
        Hibernate.initialize(proxy);
    }

    public void flush() {
        this.getSession().flush();
    }

    public Query distinct(Query query) {
        query.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return query;
    }

    public Criteria distinct(Criteria criteria) {
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria;
    }

    public String getIdName() {
        ClassMetadata meta = this.getSessionFactory().getClassMetadata(this.entityClass);
        return meta.getIdentifierPropertyName();
    }

    public boolean isPropertyUnique(String propertyName, Object newValue, Object oldValue) {
        if (newValue != null && !newValue.equals(oldValue)) {
            Object object = this.findUniqueBy(propertyName, newValue);
            return object == null;
        } else {
            return true;
        }
    }
}
