package com.augurit.myproject.sjcj.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


import com.augurit.myproject.sjcj.convert.NwReportDeleteConvertor;
import com.augurit.myproject.sjcj.dao.NwReportDeleteDao;
import com.augurit.myproject.sjcj.entity.NwReportDelete;
import com.augurit.myproject.sjcj.service.INwReportDeleteService;
import com.augurit.myproject.sjcj.web.form.NwReportDeleteForm;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.augurit.ads.fw.utils.PageUtils;
import com.augurit.ads.fw.utils.HqlUtils;

import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.Page;

@Service
@Transactional
public class NwReportDeleteServiceImpl implements INwReportDeleteService {

	@Autowired
	private NwReportDeleteDao nwReportDeleteDao;
	
	
	/**
	 *查询是否删除失败数据(一次查询少量以免连接时间过长)
	 * */
	@Override
	public List<NwReportDeleteForm> getDeleteStatus(String isDelete) {
		Page<NwReportDelete> page = new Page();
		page.setPageSize(500);
		List<NwReportDelete> list = new ArrayList<>();
		if(StringUtils.isNotBlank(isDelete)){
			StringBuffer hql = new StringBuffer("from ReportDelete ps where 1=1");
			Map values = new HashMap<>();
			if("2".equals(isDelete)){
				hql.append(" and ps.isDelete=:isDelete");
				values.put("isDelete", isDelete);
			}
			hql.append(HqlUtils.buildOrderBy(page, "ps"));
			page = nwReportDeleteDao.findPage(page, hql.toString(), values);
			if(page.getResult()!=null&& page.getResult().size()>0){
				list =page.getResult();
			}
		}
		return NwReportDeleteConvertor.convertVoListToFormList(list);
	}
	
	/**
	 * 根据主键获取Form对象
	 */
	@Transactional(readOnly = true)
	public NwReportDeleteForm get(Long id) {
		NwReportDelete entity = nwReportDeleteDao.get(id);
		return NwReportDeleteConvertor.convertVoToForm(entity);
	}
	
	/**
	 * 删除Form对象列表
	 */
	public void delete(Long... ids) {
		nwReportDeleteDao.delete(ids);
	}

	/**
	 * 保存新增或修改的Form对象列表
	 */
	public void save(NwReportDeleteForm... forms) {
		if(forms != null)
			for(NwReportDeleteForm form : forms)
				this.save(form);
	}
	
	/**
	 * 保存新增或修改的Form对象
	 */
	public void save(NwReportDeleteForm form){
		
		if(form != null){
			NwReportDelete entity = null;
			
			//准备VO对象
			if(form != null && form.getId() != null){
				entity = nwReportDeleteDao.get(form.getId());
			}else{
				entity = new NwReportDelete();
			}
			
			//属性值转换
			NwReportDeleteConvertor.convertFormToVo(form, entity);
			
			//保存
			nwReportDeleteDao.save(entity);
			
			//回填ID属性值
			form.setId(entity.getId());
		}
	}

	/**
	 * 根据Form对象的查询条件作分页查询
	 */
	@Transactional(readOnly = true)
	public Page<NwReportDeleteForm> search(Page<NwReportDeleteForm> page, NwReportDeleteForm form) {
		// 查询语句及参数
		StringBuffer hql = new StringBuffer("from NwReportDelete ps where 1=1");
		Map<String,Object> values = new HashMap<String,Object>();
		
		// 查询条件
		if(form != null){
			
		}
		
		//排序
		hql.append(HqlUtils.buildOrderBy(page, "ps"));
		
		// 执行分页查询操作
		Page pg = nwReportDeleteDao.findPage(page, hql.toString(), values);
		
		// 转换为Form对象列表并赋值到原分页对象中
		List<NwReportDeleteForm> list = NwReportDeleteConvertor.convertVoListToFormList(pg.getResult());
		PageUtils.copy(pg, list, page);
		return page;
	}
	
	/**
	 * 根据过滤条件列表作分页查询
	 */
	@Transactional(readOnly = true)
	public Page<NwReportDeleteForm> search(Page<NwReportDeleteForm> page, List<PropertyFilter> filters) {
		// 按过滤条件分页查找对象
		Page<NwReportDelete> pg = nwReportDeleteDao.findPage(page, filters);
		
		// 转换为Form对象列表并赋值到原分页对象中
		List<NwReportDeleteForm> list = NwReportDeleteConvertor.convertVoListToFormList(pg.getResult());
		PageUtils.copy(pg, list, page);
		return page;
	}
}