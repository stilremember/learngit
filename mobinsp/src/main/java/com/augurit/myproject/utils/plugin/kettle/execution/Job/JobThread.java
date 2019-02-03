package com.augurit.myproject.utils.plugin.kettle.execution.Job;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.job.Job;

public class JobThread extends Thread {


    public JobThread() {
    }

    @Override
    public void run() {
        try {
            KettleEnvironment.init();
            EnvUtil.environmentInit();
            Job job =  null;


        } catch (KettleException e) {
            e.printStackTrace();
        }
        super.run();
    }

}
