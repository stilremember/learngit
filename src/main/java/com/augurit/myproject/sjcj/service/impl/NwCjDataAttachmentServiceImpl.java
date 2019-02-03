package com.augurit.myproject.sjcj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.augurit.myproject.sjcj.convert.NwCjDataAttachmentConvertor;
import com.augurit.myproject.sjcj.dao.NwCjDataAttachmentDao;
import com.augurit.myproject.sjcj.entity.NwCjDataAttachment;
import com.augurit.myproject.sjcj.service.INwCjDataAttachmentService;
import com.augurit.myproject.sjcj.web.form.NwCjDataAttachmentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.augurit.ads.fw.utils.HqlUtils;
import com.augurit.ads.fw.utils.PageUtils;

@Service
@Transactional
public class NwCjDataAttachmentServiceImpl implements INwCjDataAttachmentService {

	@Autowired
	private NwCjDataAttachmentDao nwCjDataAttachmentDao;
	
	/**
	 * 根据外键得到最新一条附件
	 * */
	@Override
	public NwCjDataAttachmentForm getMarkIdForOne(String markId) {
		try {
			Long markids = Long.parseLong(markId);
			String hql = "from NwCjDataAttachment ps where 1=1 and ps.markId="+markids+" order by ps.attTime asc";
			return NwCjDataAttachmentConvertor.convertVoToForm(nwCjDataAttachmentDao.findFirst(hql));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 查询得到附件
	 * */
	public List<NwCjDataAttachmentForm> getCmbList(Long markId){
		List<NwCjDataAttachmentForm> list= new ArrayList<>();
		if(markId!=null){
			List<NwCjDataAttachment> listAtt = nwCjDataAttachmentDao.findBy("markId", markId);
			if(listAtt.size()>0)
				return NwCjDataAttachmentConvertor.convertVoListToFormList(listAtt);
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
	public NwCjDataAttachmentForm get(Long id) {
		nwCjDataAttachmentDao.getSessionFactory().evict(NwCjDataAttachment.class,id);
		NwCjDataAttachment entity = nwCjDataAttachmentDao.get(id);
		return NwCjDataAttachmentConvertor.convertVoToForm(entity);
	}
	
	/**
	 * 删除Form对象列表
	 */
	public void delete(Long... ids) {
		nwCjDataAttachmentDao.delete(ids);
	}

	/**
	 * 保存新增或修改的Form对象列表
	 */
	public void save(NwCjDataAttachmentForm... forms) {
		if(forms != null)
			for(NwCjDataAttachmentForm form : forms)
				this.save(form);
	}
	
	/**
	 * 保存新增或修改的Form对象
	 */
	public void save(NwCjDataAttachmentForm form){
		
		if(form != null){
			NwCjDataAttachment entity = null;
			
			//准备VO对象
			if(form != null && form.getId() != null){
				entity = nwCjDataAttachmentDao.get(form.getId());
			}else{
				entity = new NwCjDataAttachment();
			}
			
			//属性值转换
			NwCjDataAttachmentConvertor.convertFormToVo(form, entity);
			
			//保存
			nwCjDataAttachmentDao.save(entity);
			
			//回填ID属性值
			form.setId(entity.getId());
		}
	}

	/**
	 * 根据Form对象的查询条件作分页查询
	 */
	@Transactional(readOnly = true)
	public Page<NwCjDataAttachmentForm> search(Page<NwCjDataAttachmentForm> page, NwCjDataAttachmentForm form) {
		// 查询语句及参数
		StringBuffer hql = new StringBuffer("from NwCjDataAttachment ps where 1=1");
		Map<String,Object> values = new HashMap<String,Object>();
		
		// 查询条件
		if(form != null){
			
		}
		
		//排序
		hql.append(HqlUtils.buildOrderBy(page, "ps"));
		
		// 执行分页查询操作
		Page pg = nwCjDataAttachmentDao.findPage(page, hql.toString(), values);
		
		// 转换为Form对象列表并赋值到原分页对象中
		List<NwCjDataAttachmentForm> list = NwCjDataAttachmentConvertor.convertVoListToFormList(pg.getResult());
		PageUtils.copy(pg, list, page);
		return page;
	}
	
	/**
	 * 根据过滤条件列表作分页查询
	 */
	@Transactional(readOnly = true)
	public Page<NwCjDataAttachmentForm> search(Page<NwCjDataAttachmentForm> page, List<PropertyFilter> filters) {
		// 按过滤条件分页查找对象
		Page<NwCjDataAttachment> pg = nwCjDataAttachmentDao.findPage(page, filters);
		
		// 转换为Form对象列表并赋值到原分页对象中
		List<NwCjDataAttachmentForm> list = NwCjDataAttachmentConvertor.convertVoListToFormList(pg.getResult());
		PageUtils.copy(pg, list, page);
		return page;
	}
	
}