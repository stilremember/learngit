package com.augurit.ads.fw.utils.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class CollectionUtils extends org.apache.commons.collections.CollectionUtils {
    public CollectionUtils() {
    }

    public static Object[] toObjectArray(List list) {
        Object[] objs = (Object[])null;
        if (list != null && list.size() > 0) {
            objs = list.toArray(new Object[list.size()]);
        }

        return objs;
    }

    public static Integer[] toIntegerArray(List<Integer> list) {
        Integer[] objs = (Integer[])null;
        if (list != null && list.size() > 0) {
            objs = (Integer[])list.toArray(new Integer[list.size()]);
        }

        return objs;
    }

    public static Long[] toLongArray(List<Long> list) {
        Long[] objs = (Long[])null;
        if (list != null && list.size() > 0) {
            objs = (Long[])list.toArray(new Long[list.size()]);
        }

        return objs;
    }

    public static Long[] toLongArray(Set<Long> set) {
        return set != null && set.size() > 0 ? (Long[])set.toArray(new Long[set.size()]) : null;
    }

    public static String[] toStringArray(List<String> list) {
        String[] objs = (String[])null;
        if (list != null && list.size() > 0) {
            objs = (String[])list.toArray(new String[list.size()]);
        }

        return objs;
    }

    public static String[] toStringArray(Set<String> set) {
        String[] objs = (String[])null;
        if (set != null && set.size() > 0) {
            objs = (String[])set.toArray(new String[set.size()]);
        }

        return objs;
    }

    public static List<String> toStringList(Set<String> set) {
        List<String> list = null;
        if (set != null && set.size() > 0) {
            list = new ArrayList();
            list.addAll(set);
        }

        return list;
    }

    public static List<Long> toLongList(Set<Long> set) {
        List<Long> list = null;
        if (set != null && set.size() > 0) {
            list = new ArrayList();
            list.addAll(set);
        }

        return list;
    }

    public static Set<String> toStringSet(List<String> strList) {
        Set<String> bufferSet = new HashSet();
        bufferSet.addAll(strList);
        return bufferSet;
    }

    public static Set<Long> toLongSet(List<Long> longList) {
        Set<Long> bufferSet = new HashSet();
        bufferSet.addAll(longList);
        return bufferSet;
    }

    public static <T> List<T> toList(Map<String, T> map) {
        if (map != null && map.size() > 0) {
            List<T> result = new ArrayList();
            Iterator it = map.entrySet().iterator();

            while(it.hasNext()) {
                T obj = (T)((Entry)it.next()).getValue();
                if (obj != null) {
                    result.add(obj);
                }
            }

            return result;
        } else {
            return null;
        }
    }

    public static String[] toStringArrayFromObjs(List<Object> list) {
        String[] objs = (String[])null;
        if (list != null && list.size() > 0) {
            objs = new String[list.size()];
            int i = 0;

            for(int size = list.size(); i < size; ++i) {
                objs[i] = list.get(i).toString();
            }
        }

        return objs;
    }

    public static List<Long> toLongList(Long[] array) {
        List<Long> ids = new ArrayList();
        if (array != null && array.length > 0) {
            for(int i = 0; i < array.length; ++i) {
                ids.add(array[i]);
            }
        }

        return ids;
    }

    public static boolean isNotEmpty(Collection collection) {
        return collection != null && collection.size() > 0;
    }
}
