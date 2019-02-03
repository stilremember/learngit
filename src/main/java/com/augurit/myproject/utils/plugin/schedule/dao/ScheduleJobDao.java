package com.augurit.myproject.utils.plugin.schedule.dao;

import com.augurit.ads.fw.common.dao.BaseDao;
import com.augurit.myproject.utils.plugin.schedule.entity.ScheduleJob;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class ScheduleJobDao extends BaseDao<ScheduleJob, Long> {
}
