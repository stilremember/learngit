package com.augurit.ads.fw.common.service;

import java.io.Serializable;
import org.springside.modules.orm.Page;

public interface ICrudService<T, ID extends Serializable> {
    T get(ID var1);

    void save(T... var1);

    void delete(ID... var1);

    Page<T> search(Page<T> var1, T var2);
}