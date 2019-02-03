package com.augurit.myproject.utils.plugin.kettle.utils;

import com.augurit.myproject.utils.plugin.kettle.entity.DataSource;
import com.augurit.myproject.utils.plugin.kettle.execution.TramsFormThread;
import com.augurit.myproject.utils.plugin.kettle.execution.TransThread;
import org.junit.Test;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleMissingPluginsException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.logging.StepLogTable;
import org.pentaho.di.core.logging.TransLogTable;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.core.variables.VariableSpace;
import org.pentaho.di.core.variables.Variables;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

import java.util.List;

public class TestKettle {

    public TestKettle(){
        try {
            KettleEnvironment.init();
            EnvUtil.environmentInit();
        } catch (KettleException e) {
            e.printStackTrace();
        }
    }

    public void runs(String path,DataSource dataSource) throws KettleException {
        Trans trans = null;
        TransMeta transMeta = new TransMeta(path);
        if(dataSource!=null){
            System.out.println(dataSource.getDataBanesMeta().testConnection());
            transMeta.addDatabase(dataSource.getDataBanesMeta());
        }
        trans = new Trans(transMeta);
        trans.execute(null);
        trans.waitUntilFinished();//等待执行结束
    }
    public void runsss(String path,DataSource dataSource) throws KettleException {
        Trans trans = null;
        TransMeta transMeta = new TransMeta(path);
        if(dataSource!=null){
            System.out.println(dataSource.getDataBanesMeta().testConnection());
            transMeta.addDatabase(dataSource.getDataBanesMeta());
        }
        trans = new Trans(transMeta);
        trans.execute(null);
        trans.waitUntilFinished();//等待执行结束
    }

    @Test
    public void test(){
        String path = "C:\\Users\\LCH\\Desktop\\同步sbss.ktr";
        DataSource dataSource = new DataSource(null,"localhost","Oracle","Native","localhost","orcl","1521","awater","awater");

        try {
            //TransThread runThread = new TransThread(path);
            //runThread.start(); //执行
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
