package com.augurit.myproject.utils.plugin.kettle.execution;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

public class TransThread extends Thread {
    public String path;

    public TransThread(){
        super();
    }
    @Override
    public void run() {
        try {
            KettleEnvironment.init();
            EnvUtil.environmentInit();
            Trans trans = null;
            TransMeta transMeta = new TransMeta(path);
            trans = new Trans(transMeta);
            trans.execute(null);
            System.out.println(trans.getStatus());
            trans.waitUntilFinished();//等待执行结束
        } catch (KettleException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "/Users/hows/Downloads/d/javaApp/kettle/data-integration/samples/transformations/JSON - read nested fields.ktr";
        TransThread s = new TransThread();
        s.path=path;
        s.start();
    }
    @Test
    public void test() {
        String path = "C:\\Users\\LCH\\Desktop\\同步sbss.ktr";
        TransThread s = new TransThread();
        s.path=path;
        s.start();
    }
}
