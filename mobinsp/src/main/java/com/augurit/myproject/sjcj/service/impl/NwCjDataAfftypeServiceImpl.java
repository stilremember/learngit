package com.augurit.myproject.sjcj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.augurit.myproject.sjcj.convert.NwCjDataAfftypeConvertor;
import com.augurit.myproject.sjcj.dao.NwCjDataAfftypeDao;
import com.augurit.myproject.sjcj.entity.NwCjDataAfftype;
import com.augurit.myproject.sjcj.service.INwCjDataAfftypeService;
import com.augurit.myproject.sjcj.web.form.NwCjDataAfftypeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.augurit.ads.fw.utils.HqlUtils;
import com.augurit.ads.fw.utils.PageUtils;

@Service
@Transactional
public class NwCjDataAfftypeServiceImpl implements INwCjDataAfftypeService {

	@Autowired
	private NwCjDataAfftypeDao nwCjDataAfftypeDao;
	
	/**
	 * 根据外键得到附属设施
	 * */
	public List<NwCjDataAfftypeForm> getAffList(Long markId){
		List<NwCjDataAfftypeForm> list= new ArrayList<>();
		if(markId!=null){
			List<NwCjDataAfftype> listAff = nwCjDataAfftypeDao.findBy("markId", markId);
			if(listAff!=null&& listAff.size()>0)
				return NwCjDataAfftypeConvertor.convertVoListToFormList(listAff);
			else
				return list;
		}else{
			return list;
		}
	}
	/**
	 * 根据主键获取Form对象
	 */
	@Transactional(readOnly = true)
	public NwCjDataAfftypeForm get(Long id) {
		NwCjDataAfftype entity = nwCjDataAfftypeDao.get(id);
		return NwCjDataAfftypeConvertor.convertVoToForm(entity);
	}
	
	/**
	 * 删除Form对象列表
	 */
	public void delete(Long... ids) {
		nwCjDataAfftypeDao.delete(ids);
	}

	/**
	 * 保存新增或修改的Form对象列表
	 */
	public void save(NwCjDataAfftypeForm... forms) {
		if(forms != null)
			for(NwCjDataAfftypeForm form : forms)
				this.save(form);
	}
	
	/**
	 * 保存新增或修改的Form对象
	 */
	public void save(NwCjDataAfftypeForm form){
		
		if(form != null){
			NwCjDataAfftype entity = null;
			
			//准备VO对象
			if(form != null && form.getId() != null){
				entity = nwCjDataAfftypeDao.get(form.getId());
			}else{
				entity = new NwCjDataAfftype();
			}
			
			//属性值转换
			NwCjDataAfftypeConvertor.convertFormToVo(form, entity);
			
			//保存
			nwCjDataAfftypeDao.save(entity);
			
			//回填ID属性值
			form.setId(entity.getId());
		}
	}

	/**
	 * 根据Form对象的查询条件作分页查询
	 */
	@Transactional(readOnly = true)
	public Page<NwCjDataAfftypeForm> search(Page<NwCjDataAfftypeForm> page, NwCjDataAfftypeForm form) {
		// 查询语句及参数
		StringBuffer hql = new StringBuffer("from NwCjDataAfftype ps where 1=1");
		Map<String,Object> values = new HashMap<String,Object>();
		
		// 查询条件
		if(form != null){
			
		}
		
		//排序
		hql.append(HqlUtils.buildOrderBy(page, "ps"));
		
		// 执行分页查询操作
		Page pg = nwCjDataAfftypeDao.findPage(page, hql.toString(), values);
		
		// 转换为Form对象列表并赋值到原分页对象中
		List<NwCjDataAfftypeForm> list = NwCjDataAfftypeConvertor.convertVoListToFormList(pg.getResult());
		PageUtils.copy(pg, list, page);
		return page;
	}
	
	/**
	 * 根据过滤条件列表作分页查询
	 */
	@Transactional(readOnly = true)
	public Page<NwCjDataAfftypeForm> search(Page<NwCjDataAfftypeForm> page, List<PropertyFilter> filters) {
		// 按过滤条件分页查找对象
		Page<NwCjDataAfftype> pg = nwCjDataAfftypeDao.findPage(page, filters);
		
		// 转换为Form对象列表并赋值到原分页对象中
		List<NwCjDataAfftypeForm> list = NwCjDataAfftypeConvertor.convertVoListToFormList(pg.getResult());
		PageUtils.copy(pg, list, page);
		return page;
	}
}