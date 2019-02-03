package com.augurit.myproject.utils.plugin.kettle;

import com.augurit.myproject.utils.plugin.kettle.entity.DataSource;
import com.augurit.myproject.utils.plugin.kettle.execution.TramsFormThread;
import org.junit.Test;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.logging.ChannelLogTable;
import org.pentaho.di.core.logging.StepLogTable;
import org.pentaho.di.core.logging.TransLogTable;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.core.variables.VariableSpace;
import org.pentaho.di.core.variables.Variables;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

import java.util.List;

public class TransForm {
    private DataSource dataSource;

    public static void runTask(String path,DataSource dataSource){
        System.out.println("初始化Trans----------");
        Trans trans = null;
        try {
            KettleEnvironment.init();
            EnvUtil.environmentInit();
            TransMeta transMeta = new TransMeta(path);
            if(dataSource!=null){
                System.out.println(dataSource.getDataBanesMeta().testConnection());
                transMeta.addDatabase(dataSource.getDataBanesMeta());
            }
            List<DatabaseMeta> list =transMeta.getDatabases();
            VariableSpace space = new Variables();
            //space.setVariable("ps_kettle_log", "localhost");
            space.initializeVariablesFrom(null);
            StepLogTable stepLogTable = StepLogTable.getDefault(space, transMeta);
            stepLogTable.setTableName("ps_kettle_log");
            stepLogTable.setConnectionName("localhost");

            //得到本地日志表
            space.setVariable("ps_kettle_log_trans", "local`host");
            TransLogTable transLogTable = TransLogTable.getDefault(space, transMeta, transMeta.getSelectedSteps()); //所有的步骤
            transLogTable.setConnectionName("localhost");
            transLogTable.setTableName("ps_kettle_log_trans");
            transMeta.setTransLogTable(transLogTable);

            transMeta.setStepLogTable(stepLogTable);
            trans = new Trans(transMeta);

            TramsFormThread runThread = new TramsFormThread(path);
            Thread th = new Thread(runThread);
            th.start(); //执行
            //trans.execute(null);
            //trans.waitUntilFinished();//等待执行结束
            //return transMeta.getLogChannelId();
        } catch (Exception e) {
            e.printStackTrace();
            //return null;
        }
    }

    public static void main(String[] args) {
        String path = "C:\\Users\\LCH\\Desktop\\同步sbss.ktr";
        DataSource dataSource = new DataSource(null,"localhost","Oracle","Native","localhost","orcl","1521","awater","awater");
        runTask(path,dataSource);
    }
    @Test
    public void test(){
        String path = "/Users/hows/Downloads/d/javaApp/kettle/data-integration/samples/transformations/Add a sequence - Basic example.ktr";
        DataSource dataSource = new DataSource(null,"localhost","Oracle","Native","localhost","orcl","1521","awater","awater");
        runTask(path,dataSource);
    }

    public class RunQueue implements Runnable{
        public Trans transs;
        @Override
        public void run() {
            try {
                transs.execute(null);
                transs.waitUntilFinished();//等待执行结束
            } catch (KettleException e) {
                e.printStackTrace();
            }
        }
    };

    public DataSource getDataSource() {
        return dataSource;
    }
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
