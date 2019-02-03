package com.augurit.myproject.utils.plugin.schedule.service.impl;

import com.augurit.ads.fw.utils.HqlUtils;
import com.augurit.ads.fw.utils.PageUtils;
import com.augurit.myproject.utils.Exception.AppException;
import com.augurit.myproject.utils.plugin.schedule.convert.ScheduleJobConvertor;
import com.augurit.myproject.utils.plugin.schedule.core.spring.SpringUtils;
import com.augurit.myproject.utils.plugin.schedule.dao.ScheduleJobDao;
import com.augurit.myproject.utils.plugin.schedule.entity.ScheduleJob;
import com.augurit.myproject.utils.plugin.schedule.form.ScheduleJobForm;
import com.augurit.myproject.utils.plugin.schedule.service.IScheduleJobService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ScheduleJobServiceImpl implements IScheduleJobService {
	public final Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	@Autowired
	private ScheduleJobDao scheduleJobDao;
	@Autowired
	private TransactionTemplate transactionTemplate;
	@Autowired
	private JobTaskService jobTaskService;
	/*@Autowired
	private JdbcTemplate jdbcTemplate;*/
	/**
	 * 初始化定时器容器
	 * */
	public void init(){
		log.info("---------开始定时器初始化方法----------------");
		transactionTemplate.execute(new TransactionCallbackWithoutResult(){
			protected void doInTransactionWithoutResult(TransactionStatus status){
				Scheduler scheduler = schedulerFactoryBean.getScheduler();
				List<ScheduleJobForm> list = getAll();
				if(null != list && list.size()>0){
					try {
						for (ScheduleJobForm scheduleJobForm : list) {
							jobTaskService.addJob(scheduleJobForm);
						}
					} catch (SchedulerException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	/**
	 * 在容器停止时运行
	 * @throws SchedulerException 
	 **/
	 public void destroy() throws SchedulerException{
		 Scheduler scheduler = schedulerFactoryBean.getScheduler();
		 List<ScheduleJobForm> list = this.getAll();
		 for (ScheduleJobForm scheduleJobForm : list) {
			 jobTaskService.deleteJob(scheduleJobForm);
		}
	 }
	 /**
	  * 获取在计划中的任务列表
	  * */
	public List<ScheduleJobForm> getTaskList() throws SchedulerException {
		 return jobTaskService.getAllJob();
	}
	/**
	 * 添加一个计划
	 * */
	@Override
	public void addTask(ScheduleJobForm form) throws IllegalAccessException, InstantiationException, AppException, NoSuchMethodException {
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(form.getCronExpression());
		Object obj = null;
		if(StringUtils.isNotBlank(form.getSpringId())){
			obj = SpringUtils.getBean(form.getSpringId());
		}else{
			Class clazz = SpringUtils.getBean(form.getBeanClass());
			obj = clazz.newInstance();
		}
		if(obj == null){
			throw new AppException("未找到目标类!");
		}else {
			Class clazz = obj.getClass();
			Method method = null;
			method = clazz.getMethod(form.getMethodName(), null);
			if (method == null) {
				throw new AppException("未找到目标方法!");
			}
		}
	}
	/**
	 * 改变一个计划状态，启用或者停止
	 * */
	@Override
	public void changeJobStatus(ScheduleJobForm job, String status) throws SchedulerException {
		jobTaskService.changeStatus(job, status);
	}
	/**
	 * 更新计划的表达式
	 * */
	@Override
	public void updateJobCron(Long id, String cron) throws SchedulerException {
		ScheduleJobForm job = this.get(id);
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
		jobTaskService.updateCron(job, cron);
	}
	/**
	 * 获取表中的全部数据
	 * */
	public List<ScheduleJobForm> getAll(){
		Page<ScheduleJob> page = new Page();
		page = scheduleJobDao.getAll(page);
		if(page.getResult()!=null && page.getResult().size()>0)
			return ScheduleJobConvertor.convertVoListToFormList(page.getResult());
		else
			return null;
	}
	/**
	 * 获取修改作业
	 * */
	@Override
	public void toUpdate(Long id) {
		ScheduleJobForm form = get(id);
	}
	/**
	 * 修改
	 * */
	public void update(ScheduleJobForm form){
		save(form);
	}
	/**
	 * 删除作业（数据库和计划中）
	 * */
	public void deleteJob(Long[] checkedIds) throws SchedulerException {
		for (Long id : checkedIds) {
			ScheduleJobForm job = get(id);
			jobTaskService.deleteJob(job);
		}
	}
	
	/*************************ads业务****************************/
	
	/**
	 * 根据主键获取Form对象
	 */
	@Transactional(readOnly = true)
	public ScheduleJobForm get(Long id) {
		ScheduleJob entity = scheduleJobDao.get(id);
		return ScheduleJobConvertor.convertVoToForm(entity);
	}
	
	/**
	 * 删除Form对象列表
	 */
	public void delete(Long... ids) {
		scheduleJobDao.delete(ids);
	}

	/**
	 * 保存新增或修改的Form对象列表
	 */
	public void save(ScheduleJobForm... forms) {
		if(forms != null)
			for(ScheduleJobForm form : forms)
				this.save(form);
	}
	
	/**
	 * 保存新增或修改的Form对象
	 */
	public void save(ScheduleJobForm form){
		
		if(form != null){
			ScheduleJob entity = null;
			
			//准备VO对象
			if(form != null && form.getJobId() != null){
				entity = scheduleJobDao.get(form.getJobId());
			}else{
				entity = new ScheduleJob();
			}
			
			//属性值转换
			ScheduleJobConvertor.convertFormToVo(form, entity);
			
			//保存
			scheduleJobDao.save(entity);
			
			//回填ID属性值
			form.setJobId(entity.getJobId());
		}
	}

	/**
	 * 根据Form对象的查询条件作分页查询
	 */
	@Transactional(readOnly = true)
	public Page<ScheduleJobForm> search(Page<ScheduleJobForm> page, ScheduleJobForm form) {
		// 查询语句及参数
		StringBuffer hql = new StringBuffer("from ScheduleJob ps where 1=1");
		Map<String,Object> values = new HashMap<String,Object>();
		
		// 查询条件
		if(form != null){
			
		}
		
		//排序
		hql.append(HqlUtils.buildOrderBy(page, "ps"));
		
		// 执行分页查询操作
		Page pg = scheduleJobDao.findPage(page, hql.toString(), values);
		// 转换为Form对象列表并赋值到原分页对象中
		List<ScheduleJobForm> list = ScheduleJobConvertor.convertVoListToFormList(pg.getResult());
		PageUtils.copy(pg, list, page);
		return page;
	}
	
	/**
	 * 根据过滤条件列表作分页查询
	 */
	@Transactional(readOnly = true)
	public Page<ScheduleJobForm> search(Page<ScheduleJobForm> page, List<PropertyFilter> filters) {
		// 按过滤条件分页查找对象
		Page<ScheduleJob> pg = scheduleJobDao.findPage(page, filters);
		
		// 转换为Form对象列表并赋值到原分页对象中
		List<ScheduleJobForm> list = ScheduleJobConvertor.convertVoListToFormList(pg.getResult());
		PageUtils.copy(pg, list, page);
		return page;
	}

}