package com.augurit.myproject.utils.plugin.kettle.utils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
/**
 * 任务运行日志
 * */
public final class LogTask {
    private static final Hashtable<String,Object> hashTable = new Hashtable<>();

    public void save(String key,Object object){
        synchronized (hashTable){
            hashTable.put(key,object);
        }
    }
    public Boolean update(String key,Object object){
        synchronized (hashTable){
            if(hashTable.containsKey(key)){
                hashTable.put(key,object);
                return true;
            }else{
                return false;
            }
        }
    }

    public static List<Object> findAll(){
        List<Object> list = new ArrayList<>();
        Iterator<String> it = hashTable.keySet().iterator();
        while(it.hasNext()){
            list.add(hashTable.get(it.next()));
        }
        return list;
    }

    public Object find(String key){
        return hashTable.get(key);
    }

    public static int getSize(){
        return hashTable.size();
    }

    public static Boolean remove(String key){
        hashTable.remove(key);
        return true;
    }
}
