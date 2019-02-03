package com.augurit.myproject.utils.plugin.schedule.controller;


import com.augurit.ads.fw.common.controller.BaseController;
import com.augurit.myproject.utils.Exception.AppException;
import com.augurit.myproject.utils.Exception.Result;
import com.augurit.myproject.utils.plugin.schedule.form.ScheduleJobForm;
import com.augurit.myproject.utils.plugin.schedule.service.IScheduleJobService;
import org.apache.commons.lang3.StringUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/job")
@RestController
public class ScheduleJobController extends BaseController<ScheduleJobForm> {

    @Autowired
    private IScheduleJobService scheduleJobService;

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    @ResponseBody
     public Result findAll(){
        Object object =scheduleJobService.getAll();
        return new Result(200,object);
    }



    @RequestMapping(value = "/updateJob",method = RequestMethod.POST)
    @ResponseBody
    public Result updateJob(ScheduleJobForm form){
        if(form.getJobId()!=null){
            scheduleJobService.update(form);
            return new Result(200,"success");
        }else{
            return new Result(500,"参数错误!");
        }
    }

    @RequestMapping(value = "/deleteJob/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteJob(@PathVariable("id") Long id){
        try {
            scheduleJobService.delete(id);
            return new Result(200,"success");
        } catch (Exception e) {
            return new Result(500,"参数错误!");
        }
    }

    @RequestMapping(value = "/startJob/{id}",method = RequestMethod.POST)
    @ResponseBody
    public Result startJob(@PathVariable("id") Long id){
        Result res = new Result();
        ScheduleJobForm job = scheduleJobService.get(id);
        if("start".equals(job.getJobStatus())){
            res.setMessage("任务已经启动!");
        }else{
            try {
                scheduleJobService.changeJobStatus(job,"start");
                res.setCode(200);
                res.setMessage("success");
            } catch (SchedulerException e) {
                e.printStackTrace();
                res.setCode(500);
                res.setMessage("启动失败!定时容器异常!");
            }
        }
        return res;
    }


}
