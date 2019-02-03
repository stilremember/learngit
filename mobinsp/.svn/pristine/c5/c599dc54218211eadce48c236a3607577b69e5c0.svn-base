package com.augurit.myproject.utils.plugin.kettle;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.logging.JobEntryLogTable;
import org.pentaho.di.core.variables.VariableSpace;
import org.pentaho.di.core.variables.Variables;
import org.pentaho.di.job.JobMeta;

public class JobForm {

    public static void runTransfer(String[] params, String jobPath) throws Exception {
        KettleEnvironment.init();
        JobMeta jobMeta = new JobMeta(jobPath,null);

        DatabaseMeta ci =
                new DatabaseMeta("本地","Oracle","Native","localhost","orcl","1521","awater_kettle","awater_kettle");

        jobMeta.addDatabase(ci);
        VariableSpace space = new Variables();
        space.setVariable("kettle_log", "本地oracle");
        space.initializeVariablesFrom(null);
        /*JobLogTable jobLogTable = JobLogTable.getDefault(space, jobMeta); //所有的步骤
        jobLogTable.setConnectionName("本地");
        jobMeta.setJobLogTable(jobLogTable);*/
        JobEntryLogTable jobEntryLogTable = JobEntryLogTable.getDefault(space,jobMeta);

        org.pentaho.di.job.Job job = new org.pentaho.di.job.Job(null,jobMeta);
        job.setVariable("id", params[0]);
        job.setVariable("dt", params[1]);
        job.start();
        job.waitUntilFinished();
        if (job.getErrors() > 0) {
            throw new Exception("There are errors during job exception!(执行job发生异常)");
        }


    }
}
