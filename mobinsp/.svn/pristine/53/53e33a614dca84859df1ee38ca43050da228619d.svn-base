package com.augurit.myproject.sjcj.service.impl;

import com.augurit.ads.fw.utils.HqlUtils;
import com.augurit.ads.fw.utils.PageUtils;
import com.augurit.myproject.sjcj.convert.NwCheckRecordConvertor;
import com.augurit.myproject.sjcj.dao.NwCheckRecordDao;
import com.augurit.myproject.sjcj.entity.NwCheckRecord;
import com.augurit.myproject.sjcj.service.INwCheckRecordService;
import com.augurit.myproject.sjcj.web.form.NwCheckRecordForm;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class NwCheckRecordServiceImpl implements INwCheckRecordService {

	@Autowired
	private NwCheckRecordDao nwCheckRecordDao;
	
	/**********************TODO 移动端代码**********************/
	@Override
	public String toCheckRecord(String reportType, Long reportId) {
		JSONObject json = new JSONObject();
		JSONArray jsonArr=null;
		if(reportId!=null){
			NwCheckRecordForm form = new NwCheckRecordForm();
			form.setReportType(reportType);
			form.setReportId(reportId);
			List<NwCheckRecordForm> listForm =this.searchForm(form);
			if(listForm!=null&&listForm.size()>0)
				jsonArr = JSONArray.fromObject(listForm);
			if(jsonArr!=null&& jsonArr.size()>0){
				for(Object js : jsonArr){
					if(js!=null){
						JSONObject j = (JSONObject) js;
						if(j.containsKey("checkTime")){
//							System.out.println(j.get("checkTime"));
							Map<String, Object> m = (Map<String, Object>) j.get("checkTime");
							if(m.containsKey("time"))
								j.put("checkTime",m.get("time"));
						}
					}
				}
				json.put("data", jsonArr);
			}
			json.put("code", 200);
		}else{
			json.put("code", 500);
			json.put("message", "请求参数错误!");
		}
		return json.toString();
	}
	/**
	 * 根据主键获取Form对象
	 */
	@Transactional(readOnly = true)
	public NwCheckRecordForm get(Long id) {
		NwCheckRecord entity = nwCheckRecordDao.get(id);
		return NwCheckRecordConvertor.convertVoToForm(entity);
	}
	
	/**
	 * 删除Form对象列表
	 */
	public void delete(Long... ids) {
		nwCheckRecordDao.delete(ids);
	}

	/**
	 * 保存新增或修改的Form对象列表
	 */
	public void save(NwCheckRecordForm... forms) {
		if(forms != null)
			for(NwCheckRecordForm form : forms)
				this.save(form);
	}
	
	/**
	 * 保存新增或修改的Form对象
	 */
	public void save(NwCheckRecordForm form){
		
		if(form != null){
			NwCheckRecord entity = null;
			
			//准备VO对象
			if(form != null && form.getId() != null){
				entity = nwCheckRecordDao.get(form.getId());
			}else{
				entity = new NwCheckRecord();
			}
			
			//属性值转换
			NwCheckRecordConvertor.convertFormToVo(form, entity);
			
			//保存
			nwCheckRecordDao.save(entity);
			
			//回填ID属性值
			form.setId(entity.getId());
		}
	}

	/**
	 * 根据Form对象的查询条件作分页查询
	 */
	@Transactional(readOnly = true)
	public Page<NwCheckRecordForm> search(Page<NwCheckRecordForm> page, NwCheckRecordForm form) {
		// 查询语句及参数
		StringBuffer hql = new StringBuffer("from NwCheckRecord ps where 1=1");
		Map<String,Object> values = new HashMap<String,Object>();
		
		// 查询条件
		if(form != null){
			
		}
		
		//排序
		hql.append(HqlUtils.buildOrderBy(page, "ps"));
		
		// 执行分页查询操作
		Page pg = nwCheckRecordDao.findPage(page, hql.toString(), values);
		
		// 转换为Form对象列表并赋值到原分页对象中
		List<NwCheckRecordForm> list = NwCheckRecordConvertor.convertVoListToFormList(pg.getResult());
		PageUtils.copy(pg, list, page);
		return page;
	}
	
	/**
	 * 根据过滤条件列表作分页查询
	 */
	@Transactional(readOnly = true)
	public Page<NwCheckRecordForm> search(Page<NwCheckRecordForm> page, List<PropertyFilter> filters) {
		// 按过滤条件分页查找对象
		Page<NwCheckRecord> pg = nwCheckRecordDao.findPage(page, filters);
		
		// 转换为Form对象列表并赋值到原分页对象中
		List<NwCheckRecordForm> list = NwCheckRecordConvertor.convertVoListToFormList(pg.getResult());
		PageUtils.copy(pg, list, page);
		return page;
	}
	/**
	 * 倒序查询审核记录
	 * */
	public List<NwCheckRecordForm> searchForm(NwCheckRecordForm form){
		List<NwCheckRecord> list = new ArrayList<>();
		// 查询语句及参数
		StringBuffer hql = new StringBuffer("from NwCheckRecord ps where 1=1");
		Map<String,Object> values = new HashMap<String,Object>();
		// 查询条件
		if(form != null){
			if(StringUtils.isNotBlank(form.getReportType())){
				hql.append(" and ps.reportType=:reportType");
				values.put("reportType", form.getReportType());
			}
			if(form.getReportId()!=null){
				hql.append(" and ps.reportId=:reportId");
				values.put("reportId", form.getReportId());
			}
		}
		hql.append(" order by ps.checkTime desc");
		list = nwCheckRecordDao.find(hql.toString(), values);
		if(list.size()>0)
			return NwCheckRecordConvertor.convertVoListToFormList(list);
		else
			return null;
	}
}