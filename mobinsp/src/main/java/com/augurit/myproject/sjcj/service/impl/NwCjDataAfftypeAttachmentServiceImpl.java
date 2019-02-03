package com.augurit.myproject.sjcj.service.impl;

import java.util.*;

import com.augurit.myproject.sjcj.convert.NwCjDataAfftypeAttachmentConvertor;
import com.augurit.myproject.sjcj.dao.NwCjDataAfftypeAttachmentDao;
import com.augurit.myproject.sjcj.entity.NwCjDataAfftypeAttachment;
import com.augurit.myproject.sjcj.service.INwCjDataAfftypeAttachmentService;
import com.augurit.myproject.sjcj.web.form.NwCjDataAfftypeAttachmentForm;
import com.augurit.myproject.utils.common.JsonOfForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.augurit.ads.fw.utils.HqlUtils;
import com.augurit.ads.fw.utils.PageUtils;

@Service
@Transactional
public class NwCjDataAfftypeAttachmentServiceImpl implements INwCjDataAfftypeAttachmentService {

	@Autowired
	private NwCjDataAfftypeAttachmentDao nwCjDataAfftypeAttachmentDao;
	
	/**
	 * 查询得到附件
	 * */
	public List<NwCjDataAfftypeAttachmentForm> getAffAttList(Long affId){
		List<NwCjDataAfftypeAttachmentForm> list= new ArrayList<>();
		if(affId!=null){
			List<NwCjDataAfftypeAttachment> listAffAtt = nwCjDataAfftypeAttachmentDao.findBy("affId", affId);
			if(listAffAtt!=null && listAffAtt.size()>0){
				//升序
				list= NwCjDataAfftypeAttachmentConvertor.convertVoListToFormList(listAffAtt);
				Collections.sort(list, JsonOfForm.ageComparatorAffAttAttTime);
				return list;
			}
			else{
				return list;
			}
		}else{
			return list;
		}
	}
	/**
	 * 根据主键获取Form对象
	 */
	@Transactional(readOnly = true)
	public NwCjDataAfftypeAttachmentForm get(Long id) {
		NwCjDataAfftypeAttachment entity = nwCjDataAfftypeAttachmentDao.get(id);
		return NwCjDataAfftypeAttachmentConvertor.convertVoToForm(entity);
	}
	
	/**
	 * 删除Form对象列表
	 */
	public void delete(Long... ids) {
		nwCjDataAfftypeAttachmentDao.delete(ids);
	}

	/**
	 * 保存新增或修改的Form对象列表
	 */
	public void save(NwCjDataAfftypeAttachmentForm... forms) {
		if(forms != null)
			for(NwCjDataAfftypeAttachmentForm form : forms)
				this.save(form);
	}
	
	/**
	 * 保存新增或修改的Form对象
	 */
	public void save(NwCjDataAfftypeAttachmentForm form){
		
		if(form != null){
			NwCjDataAfftypeAttachment entity = null;
			
			//准备VO对象
			if(form != null && form.getId() != null){
				entity = nwCjDataAfftypeAttachmentDao.get(form.getId());
			}else{
				entity = new NwCjDataAfftypeAttachment();
			}
			
			//属性值转换
			NwCjDataAfftypeAttachmentConvertor.convertFormToVo(form, entity);
			
			//保存
			nwCjDataAfftypeAttachmentDao.save(entity);
			
			//回填ID属性值
			form.setId(entity.getId());
		}
	}

	/**
	 * 根据Form对象的查询条件作分页查询
	 */
	@Transactional(readOnly = true)
	public Page<NwCjDataAfftypeAttachmentForm> search(Page<NwCjDataAfftypeAttachmentForm> page, NwCjDataAfftypeAttachmentForm form) {
		// 查询语句及参数
		StringBuffer hql = new StringBuffer("from NwCjDataAfftypeAttachment ps where 1=1");
		Map<String,Object> values = new HashMap<String,Object>();
		
		// 查询条件
		if(form != null){
			
		}
		
		//排序
		hql.append(HqlUtils.buildOrderBy(page, "ps"));
		
		// 执行分页查询操作
		Page pg = nwCjDataAfftypeAttachmentDao.findPage(page, hql.toString(), values);
		
		// 转换为Form对象列表并赋值到原分页对象中
		List<NwCjDataAfftypeAttachmentForm> list = NwCjDataAfftypeAttachmentConvertor.convertVoListToFormList(pg.getResult());
		PageUtils.copy(pg, list, page);
		return page;
	}
	
	/**
	 * 根据过滤条件列表作分页查询
	 */
	@Transactional(readOnly = true)
	public Page<NwCjDataAfftypeAttachmentForm> search(Page<NwCjDataAfftypeAttachmentForm> page, List<PropertyFilter> filters) {
		// 按过滤条件分页查找对象
		Page<NwCjDataAfftypeAttachment> pg = nwCjDataAfftypeAttachmentDao.findPage(page, filters);
		
		// 转换为Form对象列表并赋值到原分页对象中
		List<NwCjDataAfftypeAttachmentForm> list = NwCjDataAfftypeAttachmentConvertor.convertVoListToFormList(pg.getResult());
		PageUtils.copy(pg, list, page);
		return page;
	}
}