package com.augurit.myproject.sjcj.service.impl;

import com.augurit.ads.fw.common.from.LoginUserBaseForm;
import com.augurit.ads.fw.utils.HqlUtils;
import com.augurit.ads.fw.utils.PageUtils;
import com.augurit.myproject.sjcj.convert.NwCjDataAfftypeConvertor;
import com.augurit.myproject.sjcj.convert.NwCjDataConvertor;
import com.augurit.myproject.sjcj.dao.NwCjDataDao;
import com.augurit.myproject.sjcj.entity.NwCjData;
import com.augurit.myproject.sjcj.service.INwCjDataAfftypeAttachmentService;
import com.augurit.myproject.sjcj.service.INwCjDataAfftypeService;
import com.augurit.myproject.sjcj.service.INwCjDataAttachmentService;
import com.augurit.myproject.sjcj.service.INwCjDataService;
import com.augurit.myproject.sjcj.web.form.NwCjDataAfftypeForm;
import com.augurit.myproject.sjcj.web.form.NwCjDataAttachmentForm;
import com.augurit.myproject.sjcj.web.form.NwCjDataForm;
import com.augurit.myproject.utils.arcgis.form.FeatureForm;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class NwCjDataServiceImpl implements INwCjDataService {

	@Autowired
	private NwCjDataDao nwCjDataDao;
	/*@Autowired
	private IOmUserService omUserService;
	@Autowired
	private IOmOrgService omOrgService;
	@Autowired
	private INwProblemReportService NwProblemReportService;*/
    @Autowired
    private INwCjDataAttachmentService nwCjDataAttachmentService;
    @Autowired
    private INwCjDataAfftypeService nwCjDataAfftypeService;
    @Autowired
    private INwCjDataAfftypeAttachmentService nwCjDataAfftypeAttachmentService;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/************************************移动端使用方法*****************************************/
	/**
     * 移动端保存
     * */
	public void saveAddAll(NwCjDataForm form, List<NwCjDataAttachmentForm> listAttachment, List<NwCjDataAfftypeForm> listaff, List<Map<String,Object>> listMapPhotos){
        this.save(form);
	    for (NwCjDataAttachmentForm nwCjDataAttachmentForm : listAttachment) {  //保存附件
            nwCjDataAttachmentForm.setMarkId(form.getId());
            nwCjDataAttachmentService.save(nwCjDataAttachmentForm);
        }
        if(listaff!=null){   //设施点保存附属设施
            for(NwCjDataAfftypeForm afftypeForm : listaff){  //循环保存附属设施或者图片
                afftypeForm.setId(null);
                afftypeForm.setMarkId(form.getId());
                afftypeForm.setCreateTime(new Date().getTime());
                nwCjDataAfftypeService.save(afftypeForm);
                for(Map<String,Object> map : listMapPhotos){
                    if(afftypeForm.getFieldName()!=null&&map.get("fieldName").equals(afftypeForm.getFieldName())){
                        map.put("affId", afftypeForm.getId());
                        nwCjDataAfftypeAttachmentService.save(NwCjDataAfftypeConvertor.convertMapVoForm(map));  //循环保存附属设施附件
                    }
                }
            }
        }
    }

	/**
	 * 昨天和今天区域统计
	 * */
	@Override
	public Map toPastNowDay(String collectType) {
		Map mapJosn = new HashMap<>();
		Map mapss = new HashMap<>();
		if(!"null".equals(collectType) && StringUtils.isNotBlank(collectType)){
			mapss.put("collectType", collectType);
		}
		List<Map> toDay = new ArrayList<>();
		List<Map> yestDay = new ArrayList<>();
		List<Object> list = new ArrayList<>();
		List<Object> yestDayList = getCountDay(true,mapss);//昨天
		List<Object> toDayList = getCountDay(false,mapss);//今天
		for(Object o:toDayList){
			Map map = new HashMap<>();
			if(o!=null&&((Object[])o).length>0){
				if(!((Object[])o)[0].toString().equals("广州市水务局")){
					map.put("name", ((Object[])o)[0]);
					map.put("checkCount", ((Object[])o)[1]!=null?((Object[])o)[1]:0);
					map.put("addCount", ((Object[])o)[2]!=null?((Object[])o)[2]:0);
					toDay.add(map);
				}
			}
		}
		for(Object o:yestDayList){
			Map map = new HashMap<>();
			if(o!=null&&((Object[])o).length>0){
				if(!((Object[])o)[0].toString().equals("广州市水务局")){
					map.put("name", ((Object[])o)[0]);
					map.put("checkCount", ((Object[])o)[1]!=null?((Object[])o)[1]:0);
					map.put("addCount", ((Object[])o)[2]!=null?((Object[])o)[2]:0);
					yestDay.add(map);
				}
			}
		}
		mapJosn.put("toDay", toDay);
		mapJosn.put("yestDay", yestDay);
		return mapJosn;
	}
	
	/**
	 * 上报统计(移动端使用)
	 * */
	public Map<String,Object> countCollect(NwCjDataForm form,Map<String,Object> map){
		Map<String,Object> mapJson = new HashMap<>();
		StringBuffer sql = new StringBuffer("from Nw_Cj_Data ps where 1=1 and ps.parent_Org_Name not like '%广州市水务局%'");
		Map<String,Object> maps = new HashMap<>();
		if(StringUtils.isNotBlank(form.getParentOrgName())){
			sql.append(" and ps.parent_Org_Name like :parentOrgName");
			maps.put("parentOrgName", "%"+form.getParentOrgName()+"%");
		}
		if(StringUtils.isNotBlank(form.getCollectType())){
			sql.append(" and ps.collect_Type like :collectType");
			maps.put("collectType", "%"+map.get("collectType").toString()+"%");
		}
		if(map.size()>0 && map.containsKey("startTime") && map.containsKey("endTime")){
			sql.append(" and ps.mark_Time between :startTime and :endTime");
			maps.put("startTime", (Date)map.get("startTime"));
			maps.put("endTime", (Date)map.get("endTime"));
		}
		Long doubtAddCount = nwCjDataDao.countSqlResult(sql.toString()+" and ps.check_State ='3' and ps.check_type like '%新增%'", maps);  //新增通过统计
		Long doubtTotal = nwCjDataDao.countSqlResult(sql.toString()+" and ps.check_State ='3'", maps);  //通过统计
		mapJson.put("doubtAddCount", doubtAddCount);
		mapJson.put("doubtCheckCount", doubtTotal-doubtAddCount);
		mapJson.put("doubtTotal", doubtTotal);
		Long passAddCount = nwCjDataDao.countSqlResult(sql.toString()+" and ps.check_State ='2' and ps.check_type like '%新增%'", maps);  //新增疑问统计
		Long passTotal = nwCjDataDao.countSqlResult(sql.toString()+" and ps.check_State ='2'", maps);  //疑问统计
		mapJson.put("passAddCount", passAddCount);
		mapJson.put("passCheckCount", passTotal-passAddCount);
		mapJson.put("passTotal", passTotal);
		Long addCount =  nwCjDataDao.countSqlResult(sql.toString()+" and ps.check_type like '%新增%'", maps);
		Long total = nwCjDataDao.countSqlResult(sql.toString(), maps);
		mapJson.put("addCount", addCount);
		mapJson.put("checkCount", total-addCount);
		mapJson.put("total", total);
		return mapJson;
	}
	/**
	 * 查询设施点是否绑定井
	 * */
	public Boolean getBingYj(Long yjCombId){
		String sql = "from Nw_Cj_Data ps where 1=1 and ps.yj_Comb_Id=:yjCombId";
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("yjCombId", yjCombId);
		Long count = nwCjDataDao.countSqlResult(sql, values);
		return count > 0l ? true :false;
	}
	/**
	 * 查询设施点是否重复
	 * @return true(重复) false(不重复)
	 * */
	public Boolean isComb(String cmbName,Long id){
		StringBuffer sql = new StringBuffer("from Nw_Cj_Data ps where 1=1 ");
		Map<String,Object> values = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(cmbName)){
			sql.append("and ps.cmb_Name=:cmbName ");
			values.put("cmbName", cmbName);
		}
		if(id!=null){
			sql.append("and ps.id<>:id");
			values.put("id", id);
		}
		Long count = nwCjDataDao.countSqlResult(sql.toString(), values);
		return count > 0l ? true :false;
	}
	/**
	 * 农污治理项目信息采集获取未采集设施点列表
	 * @param page	form
	 * @return
	 */
	@Override
	public Page<NwCjDataForm> getSsd(Page<NwCjDataForm> page, NwCjDataForm form){
		// 查询语句及参数
		StringBuffer hql = new StringBuffer("from NwCjData ps where 1=1 ");
		Map<String,Object> values = new HashMap<String,Object>();
		hql.append(" and ps.collectType='设施点' and not exists  ");
		hql.append(" (select 1 from NwProjInfoCollect t where t.sszdId=to_char(ps.id)) ");
		// 查询条件
		if(form != null){
			if(form.getId()!=null){
				hql.append(" or ps.id = :id");
				values.put("id", form.getId());
			}
			if(StringUtils.isNotBlank(form.getCmbNumber())){
				hql.append(" and ps.cmbNumber like :cmbNumber");
				values.put("cmbNumber", "%"+form.getCmbNumber()+"%");
			}
			if(StringUtils.isNotBlank(form.getCmbName())){
				hql.append(" and ps.cmbName like :cmbName");
				values.put("cmbName", "%"+form.getCmbName()+"%");
			}
			if(StringUtils.isNotBlank(form.getDirectOrgId())){
				hql.append(" and ps.directOrgId = :directOrgId");
				values.put("directOrgId", form.getDirectOrgId());
			}
		}
		hql.append(" order by ps.markTime asc");
		//排序
		hql.append(HqlUtils.buildOrderBy(page, "ps"));
		
		// 执行分页查询操作
		Page pg = nwCjDataDao.findPage(page, hql.toString(), values);
		
		// 转换为Form对象列表并赋值到原分页对象中
		List<NwCjDataForm> list = NwCjDataConvertor.convertVoListToFormList(pg.getResult());
		PageUtils.copy(pg, list, page);
		return page;
	}
	/**
	 * 更新
	 * */
	@Override
	public void updateForm(NwCjDataForm form) {
		form.setCheckType(null);
		form.setMarkPerson(null);
		form.setMarkPersonId(null);
		form.setMarkTime(null);
		form.setUserId(null);
		this.save(form);
	}
	
	/**
	 * 根据主键获取Form对象
	 */
	@Transactional(readOnly = true)
	public NwCjDataForm get(Long id) {
		nwCjDataDao.getSessionFactory().evict(NwCjData.class,id);
		NwCjData entity = nwCjDataDao.get(id);
		return NwCjDataConvertor.convertVoToForm(entity);
	}
	
	/**
	 * 删除Form对象列表
	 */
	public void delete(Long... ids) {
		nwCjDataDao.delete(ids);
	}

	/**
	 * 保存新增或修改的Form对象列表
	 */
	public void save(NwCjDataForm... forms) {
		if(forms != null)
			for(NwCjDataForm form : forms)
				this.save(form);
	}
	
	/**
	 * 保存新增或修改的Form对象
	 */
	public void save(NwCjDataForm form){
		
		if(form != null){
			NwCjData entity = null;
			
			//准备VO对象
			if(form != null && form.getId() != null){
				entity = nwCjDataDao.get(form.getId());
			}else{
				entity = new NwCjData();
			}
			
			//属性值转换
			NwCjDataConvertor.convertFormToVo(form, entity);
			
			//保存
			nwCjDataDao.save(entity);
			
			//回填ID属性值
			form.setId(entity.getId());
		}
	}

	/**
	 * 事务公开查询
	 */
	@Transactional(readOnly = true)
	public Page<NwCjDataForm> searchCollectAll(Page<NwCjDataForm> page, NwCjDataForm form,Map<String, Object> map) {
		// 查询语句及参数
		StringBuffer hql = new StringBuffer("from NwCjData ps where 1=1 and ps.parentOrgName not like '%广州市水务局%' ");
		StringBuffer sql = new StringBuffer("from Nw_Cj_Data ps where 1=1 and ps.parent_Org_Name not like '%广州市水务局%' ");
		Map<String,Object> values = new HashMap<String,Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 查询条件
		if(form != null){
			if(form.getObjectId()!=null){
				hql.append(" and to_char(ps.objectId) like :objectId");
				sql.append(" and to_char(ps.object_Id) like :objectId");
				values.put("objectId", "%"+form.getObjectId()+"%");
			}
			if(StringUtils.isNotBlank(form.getParentOrgId())){
				hql.append(" and to_char(ps.parentOrgId) like :parentOrgId");
				sql.append(" and to_char(ps.parent_Org_Id) like :parentOrgId");
				values.put("parentOrgId", form.getParentOrgId());
			}
			if(StringUtils.isNotBlank(form.getParentOrgName())){  //业主单位（区单位）
				hql.append(" and ps.parentOrgName like :parentOrgName");
				sql.append(" and ps.parent_Org_Name like :parentOrgName");
				values.put("parentOrgName", "%"+form.getParentOrgName()+"%");
			}
			if(StringUtils.isNotBlank(form.getCheckType())){  //数据新增或者校核
				hql.append(" and ps.checkType like :checkType");
				sql.append(" and ps.check_Type like :checkType");
				values.put("checkType", "%"+form.getCheckType()+"%");
			}
			if(StringUtils.isNotBlank(form.getYjAttrFive())){  //挂牌编号查询
				hql.append(" and ps.yjAttrFive like :yjAttrFive");
				sql.append(" and ps.yj_Attr_Five like :yjAttrFive");
				values.put("yjAttrFive", "%"+form.getYjAttrFive()+"%");
			}
			if(StringUtils.isNotBlank(form.getCollectType())){   //窨井和设施点
				hql.append(" and ps.collectType like :collectType");
				sql.append(" and ps.collect_type like :collectType");
				values.put("collectType", "%"+form.getCollectType()+"%");
			}
			if(StringUtils.isNotBlank(form.getAddr())){
				hql.append(" and ps.addr like :addr");
				sql.append(" and ps.addr like :addr");
				values.put("addr", "%"+form.getAddr()+"%");
			}
			if(StringUtils.isNotBlank(form.getRoad())){
				hql.append(" and ps.road like :road");
				sql.append(" and ps.road like :road");
				values.put("road", "%"+form.getRoad()+"%");
			}
			if(StringUtils.isNotBlank(form.getCheckState())){   
				hql.append(" and ps.checkState = :checkState");
				sql.append(" and ps.check_State = :checkState");
				values.put("checkState", form.getCheckState());
			}
			if(map!=null){
				if(map.containsKey("startTime")){
					hql.append(" and to_char(ps.markTime,'yyyy-MM-dd hh24:mi:ss') >= :startTime ");
					sql.append(" and to_char(ps.mark_Time,'yyyy-MM-dd hh24:mi:ss') >= :startTime ");
					values.put("startTime", df.format((Date)map.get("startTime")));
				}
				if(map.containsKey("endTime")){
					hql.append(" and to_char(ps.markTime,'yyyy-MM-dd hh24:mi:ss') <= :endTime ");
					sql.append(" and to_char(ps.mark_Time,'yyyy-MM-dd hh24:mi:ss') <= :endTime ");
					values.put("endTime", df.format((Date)map.get("endTime")));
				}
			}
		}
		
		hql.append(" order by ps.markTime desc");
		//排序
		hql.append(HqlUtils.buildOrderBy(page, "ps"));
		// 执行分页查询操作
		Page pg = nwCjDataDao.findPage(page, hql.toString(), values);
		
		Map<String,Object> getCheckNum = values;
		if(getCheckNum.containsKey("checkState")){
			getCheckNum.remove("checkState");
		}
		Long noCheck=nwCjDataDao.countSqlResult(sql.toString()+" and ps.check_type like '%新增%'", getCheckNum);
		map.put("addTotal", noCheck);
		map.put("checkTotal", pg.getTotalItems()-noCheck);
		// 转换为Form对象列表并赋值到原分页对象中
		List<NwCjDataForm> list = NwCjDataConvertor.convertVoListToFormList(pg.getResult());
		PageUtils.copy(pg, list, page);
		return page;
	}
	/**
	 * 事务公开查询
	 */
	@Transactional(readOnly = true)
	public Page<NwCjDataForm> searchAll(Page<NwCjDataForm> page, NwCjDataForm form,Map<String, Object> map) {
		// 查询语句及参数
		StringBuffer hql = new StringBuffer("from NwCjData ps where 1=1");
		StringBuffer sql = new StringBuffer("from Nw_Cj_Data ps where 1=1");
		Map<String,Object> values = new HashMap<String,Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 查询条件
		if(form != null){
			if(form.getObjectId()!=null){
				hql.append(" and to_char(ps.objectId) like :objectId");
				sql.append(" and to_char(ps.object_Id) like :objectId");
				values.put("objectId", "%"+form.getObjectId()+"%");
			}
			if(StringUtils.isNotBlank(form.getParentOrgName())){  //业主单位（区单位）
				hql.append(" and ps.parentOrgName like :parentOrgName");
				sql.append(" and ps.parent_Org_Name like :parentOrgName");
				values.put("parentOrgName", "%"+form.getParentOrgName()+"%");
			}
			if(StringUtils.isNotBlank(form.getCheckType())){  //数据新增或者校核
				hql.append(" and ps.checkType like :checkType");
				sql.append(" and ps.check_Type like :checkType");
				values.put("checkType", "%"+form.getCheckType()+"%");
			}
			if(StringUtils.isNotBlank(form.getYjAttrFive())){  //挂牌编号查询
				hql.append(" and ps.yjAttrFive like :yjAttrFive");
				sql.append(" and ps.yj_Attr_Five like :yjAttrFive");
				values.put("yjAttrFive", "%"+form.getYjAttrFive()+"%");
			}
			if(StringUtils.isNotBlank(form.getCollectType())){   //窨井和设施点
				hql.append(" and ps.collectType like :collectType");
				sql.append(" and ps.collect_type like :collectType");
				values.put("collectType", "%"+form.getCollectType()+"%");
			}
			if(StringUtils.isNotBlank(form.getAddr())){
				hql.append(" and ps.addr like :addr");
				sql.append(" and ps.addr like :addr");
				values.put("addr", "%"+form.getAddr()+"%");
			}
			if(StringUtils.isNotBlank(form.getRoad())){
				hql.append(" and ps.road like :road");
				sql.append(" and ps.road like :road");
				values.put("road", "%"+form.getRoad()+"%");
			}
			if(StringUtils.isNotBlank(form.getCheckState())){   
				hql.append(" and ps.checkState = :checkState");
				sql.append(" and ps.check_State = :checkState");
				values.put("checkState", form.getCheckState());
			}
			if(map!=null){
				if(map.containsKey("startTime")){
					hql.append(" and to_char(ps.markTime,'yyyy-MM-dd hh24:mi:ss') >= :startTime ");
					sql.append(" and to_char(ps.mark_Time,'yyyy-MM-dd hh24:mi:ss') >= :startTime ");
					values.put("startTime", df.format((Date)map.get("startTime")));
				}
				if(map.containsKey("endTime")){
					hql.append(" and to_char(ps.markTime,'yyyy-MM-dd hh24:mi:ss') <= :endTime ");
					sql.append(" and to_char(ps.mark_Time,'yyyy-MM-dd hh24:mi:ss') <= :endTime ");
					values.put("endTime", df.format((Date)map.get("endTime")));
				}
			}
		}
		
		hql.append(" order by ps.markTime desc");
		//排序
		hql.append(HqlUtils.buildOrderBy(page, "ps"));
		// 执行分页查询操作
		Page pg = nwCjDataDao.findPage(page, hql.toString(), values);
		
		Map<String,Object> getCheckNum = values;
		if(getCheckNum.containsKey("checkState")){
			getCheckNum.remove("checkState");
		}
		Long noCheck=nwCjDataDao.countSqlResult(sql.toString()+" and (ps.check_State ='1' or ps.check_State ='' or ps.check_State is null)", getCheckNum);
		map.put("addTotal", noCheck);
		map.put("checkTotal", pg.getTotalItems()-noCheck);
		// 转换为Form对象列表并赋值到原分页对象中
		List<NwCjDataForm> list = NwCjDataConvertor.convertVoListToFormList(pg.getResult());
		PageUtils.copy(pg, list, page);
		return page;
	}
	/**
	 * 根据Form对象的查询条件作分页查询
	 */
	@Transactional(readOnly = true)
	public Page<NwCjDataForm> search(Page<NwCjDataForm> page, NwCjDataForm form,Map<String, Object> map) {
		// 查询语句及参数
		StringBuffer hql = new StringBuffer("from NwCjData ps where 1=1");
		StringBuffer sql = new StringBuffer("from Nw_Cj_Data ps where 1=1");
		Map<String,Object> values = new HashMap<String,Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 查询条件
		if(form != null){
			if(form.getUserId()!=null){
				hql.append(" and ps.userId = :userId");
				sql.append(" and ps.user_id=:userId");
				values.put("userId", form.getUserId());
			}
			if(form.getObjectId()!=null){
				hql.append(" and to_char(ps.objectId) like :objectId");
				sql.append(" and to_char(ps.object_Id) like :objectId");
				values.put("objectId", "%"+form.getObjectId()+"%");
			}
			if(StringUtils.isNotBlank(form.getYjAttrFive())){  //挂牌编号查询
				hql.append(" and ps.yjAttrFive like :yjAttrFive");
				sql.append(" and ps.yj_Attr_Five like :yjAttrFive");
				values.put("yjAttrFive", "%"+form.getYjAttrFive()+"%");
			}
			if(StringUtils.isNotBlank(form.getCollectType())){
				hql.append(" and ps.collectType like :collectType");
				sql.append(" and ps.collect_type like :collectType");
				values.put("collectType", "%"+form.getCollectType()+"%");
			}
			if(StringUtils.isNotBlank(form.getAddr())){
				hql.append(" and ps.addr like :addr");
				sql.append(" and ps.addr like :addr");
				values.put("addr", "%"+form.getAddr()+"%");
			}
			if(StringUtils.isNotBlank(form.getRoad())){
				hql.append(" and ps.road like :road");
				sql.append(" and ps.road like :road");
				values.put("road", "%"+form.getRoad()+"%");
			}
			if(StringUtils.isNotBlank(form.getCheckState())){
				hql.append(" and ps.checkState = :checkState");
				values.put("checkState", form.getCheckState());
			}
			if(map!=null){
				if(map.containsKey("startTime")){
					hql.append(" and to_char(ps.updateTime,'yyyy-MM-dd hh24:mi:ss') >= :startTime ");
					sql.append(" and to_char(ps.update_Time,'yyyy-MM-dd hh24:mi:ss') >= :startTime ");
					values.put("startTime", df.format((Date)map.get("startTime")));
				}
				if(map.containsKey("endTime")){
					hql.append(" and to_char(ps.updateTime,'yyyy-MM-dd hh24:mi:ss') <= :endTime ");
					sql.append(" and to_char(ps.update_Time,'yyyy-MM-dd hh24:mi:ss') <= :endTime ");
					values.put("endTime", df.format((Date)map.get("endTime")));
				}
			}
		}
		
		hql.append(" order by ps.updateTime desc");
		//排序
		hql.append(HqlUtils.buildOrderBy(page, "ps"));
		// 执行分页查询操作
		Page pg = nwCjDataDao.findPage(page, hql.toString(), values);
		
		Map<String,Object> getCheckNum = values;
		if(getCheckNum.containsKey("checkState")){
			getCheckNum.remove("checkState");
		}
		Long noCheck=nwCjDataDao.countSqlResult(sql.toString()+" and (ps.check_State ='1' or ps.check_State ='' or ps.check_State is null)", getCheckNum);
		Long pass=nwCjDataDao.countSqlResult(sql.toString()+" and ps.check_State ='2'", getCheckNum);
		Long succ=nwCjDataDao.countSqlResult(sql.toString()+" and ps.check_State ='3'", getCheckNum);
		map.put("no", noCheck);
		map.put("pass", pass);
		map.put("succ", succ);
		// 转换为Form对象列表并赋值到原分页对象中
		List<NwCjDataForm> list = NwCjDataConvertor.convertVoListToFormList(pg.getResult());
		PageUtils.copy(pg, list, page);
		return page;
	}
	/**
	 * pc端分页
	 */
	@Transactional(readOnly = true)
	public Page<NwCjDataForm> searchPc(Page<NwCjDataForm> page, NwCjDataForm form,Map map) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		// 查询语句及参数
		StringBuffer hql = new StringBuffer("from NwCjData ps where 1=1 and ps.objectId is not null");
		Map<String,Object> values = new HashMap<String,Object>();
		
		// 查询条件
		if(form != null){
			if(form.getUserId()!=null){
				hql.append(" and ps.userId = :userId");
				values.put("userId", form.getUserId());
			}
			if(StringUtils.isNotBlank(form.getMarkPerson())){
				hql.append(" and ps.markPerson like :markPerson");
				values.put("markPerson", "%"+form.getMarkPerson()+"%");
			}
			if(StringUtils.isNotBlank(form.getCheckState())){
				hql.append(" and ps.checkState = :checkState");
				values.put("checkState", form.getCheckState());
			}
			if(StringUtils.isNotBlank(form.getAddr())){
				hql.append(" and ps.addr like :addr");
				values.put("addr", "%"+form.getAddr()+"%");
			}
			if(StringUtils.isNotBlank(form.getRoad())){
				hql.append(" and ps.road like :road");
				values.put("road", "%"+form.getRoad()+"%");
			}
			if(StringUtils.isNotBlank(form.getCmbName())){
				hql.append(" and ps.cmbName like :cmbName");
				values.put("cmbName", "%"+form.getCmbName()+"%");
			}
			if(StringUtils.isNotBlank(form.getReportType())){
				hql.append(" and ps.reportType = :reportType");
				values.put("reportType", form.getReportType());
			}
			if(StringUtils.isNotBlank(form.getCollectType())){
				hql.append(" and ps.collectType like :collectType");
				values.put("collectType", "%"+form.getCollectType()+"%");
			}
			if(StringUtils.isNotBlank(form.getCheckType())){
				hql.append(" and ps.checkType like :checkType");
				values.put("checkType", "%"+form.getCheckType()+"%");
			}
			if(StringUtils.isNotBlank(form.getParentOrgName())){
				hql.append(" and ps.parentOrgName like :parentOrgName");
				values.put("parentOrgName", "%"+form.getParentOrgName()+"%");
			}
			if(StringUtils.isNotBlank(form.getTeamOrgName())){
				hql.append(" and ps.teamOrgName like :teamOrgName");
				values.put("teamOrgName", "%"+form.getTeamOrgName()+"%");
			}
			if(StringUtils.isNotBlank(form.getParentOrgId())){
				hql.append(" and ps.parentOrgId like :parentOrgId");
				values.put("parentOrgId", "%"+form.getParentOrgId()+"%");
			}
			if(StringUtils.isNotBlank(form.getTeamOrgId())){
				hql.append(" and ps.teamOrgId like :teamOrgId");
				values.put("teamOrgId", "%"+form.getTeamOrgId()+"%");
			}
			if(StringUtils.isNotBlank(form.getTeamCheck())){ //镇街
				hql.append(" and ps.teamCheck = :teamCheck");
				values.put("teamCheck", form.getTeamCheck());
			}
			if(StringUtils.isNotBlank(form.getParentCheck())){  //区级
				hql.append(" and ps.parentCheck = :parentCheck");
				values.put("parentCheck", form.getParentCheck());
			}
			if(StringUtils.isNotBlank(form.getTopCheck())){  //市级
				hql.append(" and ps.topCheck = :topCheck");
				values.put("topCheck", form.getTopCheck());
			}
			if(form.getObjectId()!=null){
				hql.append(" and to_char(ps.objectId) like :objectId");
				values.put("objectId", "%"+form.getObjectId()+"%");
			}
		}
		if(map!=null){
			if(map.containsKey("startTime")){
				hql.append(" and to_char(ps.markTime,'yyyy-MM-dd') >= :startTime ");
				values.put("startTime", df.format((Date)map.get("startTime")));
			}
			if(map.containsKey("endTime")){
				hql.append(" and to_char(ps.markTime,'yyyy-MM-dd') <= :endTime ");
				values.put("endTime", df.format((Date)map.get("endTime")));
			}
		}
		if(!StringUtils.isNotBlank(page.getOrderDir())||!StringUtils.isNotBlank(page.getOrderBy())){
			page.setOrderBy("markTime");
			page.setOrderDir("asc");
			//hql.append(" order by ps.markTime asc");
		}
		//排序
		//hql.append(HqlUtils.buildOrderBy(page, "ps"));

		// 执行分页查询操作
		Page pg = nwCjDataDao.findPage(page, hql.toString(), values);
		
		// 转换为Form对象列表并赋值到原分页对象中
		List<NwCjDataForm> list = NwCjDataConvertor.convertVoListToFormList(pg.getResult());
		PageUtils.copy(pg, list, page);
		return page;
	}
	/**
	 * 根据Form对象的查询条件作分页查询
	 */
	@Transactional(readOnly = true)
	public Page<NwCjDataForm> search(Page<NwCjDataForm> page, NwCjDataForm form) {
		// 查询语句及参数
		StringBuffer hql = new StringBuffer("from NwCjData ps where 1=1");
		Map<String,Object> values = new HashMap<String,Object>();
		
		// 查询条件
		if(form != null){
			if(form.getUserId()!=null){
				hql.append(" and ps.userId = :userId");
				values.put("userId", form.getUserId());
			}
			if(StringUtils.isNotBlank(form.getAddr())){
				hql.append(" and ps.addr like :addr");
				values.put("addr", "%"+form.getAddr()+"%");
			}
			if(StringUtils.isNotBlank(form.getRoad())){
				hql.append(" and ps.road like :road");
				values.put("road", "%"+form.getRoad()+"%");
			}
			if(StringUtils.isNotBlank(form.getCmbName())){
				hql.append(" and ps.cmbName like :cmbName");
				values.put("cmbName", "%"+form.getCmbName()+"%");
			}
			if(StringUtils.isNotBlank(form.getReportType())){
				hql.append(" and ps.reportType = :reportType");
				values.put("reportType", form.getReportType());
			}
			if(StringUtils.isNotBlank(form.getCollectType())){
				hql.append(" and ps.collectType like :collectType");
				values.put("collectType", "%"+form.getCollectType()+"%");
			}
			if(StringUtils.isNotBlank(form.getCheckType())){
				hql.append(" and ps.checkType like :checkType");
				values.put("checkType", "%"+form.getCheckType()+"%");
			}
			if(StringUtils.isNotBlank(form.getParentOrgName())){
				hql.append(" and ps.parentOrgName like :parentOrgName");
				values.put("parentOrgName", "%"+form.getParentOrgName()+"%");
			}
			if(StringUtils.isNotBlank(form.getTeamOrgName())){
				hql.append(" and ps.teamOrgName like :teamOrgName");
				values.put("teamOrgName", "%"+form.getTeamOrgName()+"%");
			}
			if(StringUtils.isNotBlank(form.getTeamOrgId())){
				hql.append(" and ps.teamOrgId = :teamOrgId");
				values.put("teamOrgId", form.getTeamOrgId());
			}
			if(form.getObjectId()!=null){
				hql.append(" and to_char(ps.objectId) like :objectId");
				values.put("objectId", "%"+form.getObjectId()+"%");
			}
		}
		
		//排序
		hql.append(HqlUtils.buildOrderBy(page, "ps"));
		
		// 执行分页查询操作
		Page pg = nwCjDataDao.findPage(page, hql.toString(), values);
		
		// 转换为Form对象列表并赋值到原分页对象中
		List<NwCjDataForm> list = NwCjDataConvertor.convertVoListToFormList(pg.getResult());
		PageUtils.copy(pg, list, page);
		return page;
	}
	
	/**
	 * 根据过滤条件列表作分页查询
	 */
	@Transactional(readOnly = true)
	public Page<NwCjDataForm> search(Page<NwCjDataForm> page, List<PropertyFilter> filters) {
		// 按过滤条件分页查找对象
		Page<NwCjData> pg = nwCjDataDao.findPage(page, filters);
		
		// 转换为Form对象列表并赋值到原分页对象中
		List<NwCjDataForm> list = NwCjDataConvertor.convertVoListToFormList(pg.getResult());
		PageUtils.copy(pg, list, page);
		return page;
	}
	/**
	 * 本接口通用方法，判断是市级还是区级还是维管
	 *
	public void getUsersOrg(Map map, LoginUserBaseForm userForm){
		OmUserOrgForm userOrgFrom = omUserService.userNameToNwFrom(userForm.getUser().getLoginName());
		if (userOrgFrom != null) {
			String orgGrade = userForm.getUser().getDefaultOrg()!=null?userForm.getUser().getDefaultOrg().getOrgGrade():null;
			if("22".equals(orgGrade)){ //区级
				map.put("parentOrg",true);
				map.put("parentOrgId",userForm.getUser().getDefaultOrg().getOrgId().toString());
				map.put("parentOrgName",userForm.getUser().getDefaultOrg().getOrgName());
			}else if("23".equals(orgGrade)){
				map.put("teamOrg",true);
				map.put("teamOrgId",userForm.getUser().getDefaultOrg().getOrgId().toString());
				map.put("teamOrgName",userForm.getUser().getDefaultOrg().getOrgName());
			}else{
				List<Long> list = omOrgService.getAllParentIds(userOrgFrom.getOrgId());
				for (Long id : list) {
					OmOrgForm omFrom = omOrgService.getOrgWithParentInfo(id);
					if (omFrom.getOrgGrade() == null)
						continue;
					if (omFrom.getOrgGrade().equals("22")) {// 区级机构
						map.put("parentOrg",true);
						map.put("parentOrgId",omFrom.getOrgId().toString());
						map.put("parentOrgName",omFrom.getOrgName());
					}else if(omFrom.getOrgGrade().equals("23")) {// 镇街
						map.put("teamOrg",true);
						map.put("teamOrgId",omFrom.getOrgId().toString());
						map.put("teamOrgName",omFrom.getOrgName());
					}
					if(list.size()<4 && "1".equals(omFrom.getOrgGrade())){
						if(!map.containsKey("parentOrg")){
							map.put("top",true);
						}
					}
				}
			}
		}
	} */
	/**
	 * 本接口通用方法，得到机构信息
	 *
	public void getUsersFrom(Map map, String loginName){
		OmUserOrgForm userFrom = omUserService.userNameToNwFrom(loginName);
		if (userFrom != null) {
			OmUserForm user = omUserService.getUser(userFrom.getUserId());
			map.put("userName",user.getUserName());
			map.put("userId",user.getUserId());
			map.put("loginName",loginName);
			List<Long> list = omOrgService.getAllParentIds(userFrom.getOrgId());
			for (Long id : list) {
				OmOrgForm omFrom = omOrgService.getOrgWithParentInfo(id);
				if (omFrom.getOrgGrade() == null)
					continue;
				if (omFrom.getOrgGrade().equals("22")) {// 区级机构
					map.put("parentOrgId",omFrom.getOrgId().toString());
					map.put("parentOrgName",omFrom.getOrgName());
				}else if(omFrom.getOrgGrade().equals("23")){ //镇街单位
					map.put("teamOrgId",omFrom.getOrgId().toString());
					map.put("teamOrgName",omFrom.getOrgName());
				}else if(omFrom.getOrgGrade().equals("24")){ //维管单位
					map.put("directOrgId",omFrom.getOrgId().toString());
					map.put("directOrgName",omFrom.getOrgName());
				}else if(omFrom.getOrgGrade().equals("25")){ //市考核单位
					map.put("assessOrgId",omFrom.getOrgId().toString());
					map.put("assessOrgName",omFrom.getOrgName());
					map.put("parentOrgId", "1063");
					map.put("parentOrgName", "广州市水务局");
					map.put("directOrgId",omFrom.getOrgId().toString());
					map.put("directOrgName",omFrom.getOrgName());
				}
				if(list.size()<4 && "1".equals(omFrom.getOrgGrade())){//市级机构
					if(omFrom.getParentOrgId()==null){
						map.put("parentOrgId", omFrom.getOrgId().toString());
						map.put("parentOrgName", omFrom.getOrgName());
						map.put("top", "true");
					}
				}
			}
		}
	}*/
	/**
	 * 查询最新十条数据
	 * */
	public List<Map<String,Object>> getLatestTen(){
		String sql = "select PARENT_ORG_NAME,DIRECT_ORG_NAME,"
				+ "ID,MARK_PERSON,COLLECT_TYPE,MARK_TIME,LAYER_NAME from (select * from AWATER_NW.NW_CJ_DATA ps where ps.MARK_TIME>sysdate-3 order by ps.MARK_TIME desc) where ROWNUM<=10";
		return jdbcTemplate.queryForList(sql);
	}
	/**
	 * 统计最新十条数据（问题上报、数据采集）
	 *
	public String getLatestTenList(){
		JSONObject json = new JSONObject();
		List<Map<String,Object>> list = new ArrayList<>();
		List<Map<String,Object>> listMap = new ArrayList<>();
		try {
			//得到数据采集的最新十条
			List<Map<String,Object>> listcj = getLatestTen();
			//得到问题上报的最新十条
			List<Map<String,Object>> listProblem = NwProblemReportService.getLatestTen();
			if(listcj!=null && listcj.size()>0){
				for(Map mp :listcj){
					if(mp.containsKey("MARK_TIME")){
						Date time = (Date)mp.get("MARK_TIME");
						mp.put("time", time!=null? time.getTime(): null);
						mp.remove("MARK_TIME");
					}
					if(mp.size()>0){
						mp.put("source","cj");
						list.add(mp);
					}
				}
			}
			if(listProblem!=null && listProblem.size()>0){
				for(Map mp :listProblem){
					if(mp.containsKey("SBSJ")){ //时间
						Date time = (Date)mp.get("SBSJ");
						mp.put("time", time!=null? time.getTime(): null);
						mp.put("MARK_PERSON", mp.get("SBR"));
						mp.put("PROBLEM_TYPE", mp.get("WTLX"));
						mp.put("LAYER_NAME", mp.get("SSLX"));
						mp.remove("SBSJ");
						mp.remove("SBR");
						mp.remove("WTLX");
						mp.remove("SSLX");
					}
					if(mp.size()>0){
						mp.put("source","problem");
						list.add(mp);
					}
				}
			}
			if(list.size()>0)
				ParamsToFrom.getListDesc(list);
			for(int i=0;i<(list.size()<8? list.size():8);i++){
				listMap.add(list.get(i));
			}
			json.put("data", listMap);
			json.put("code", 200);
		} catch (RuntimeException e) {
			json.put("code", 500);
			json.put("message", "系统错误");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return json.toString();
	}*/

	/*******************************图层操作事务*****************************************/
	
	/**
	 * 成功保存至图层属性中的数据-保存至数据库
	 * @params List<Map> listMap
	 * */
	public Boolean featureToSaveForm(FeatureForm featureForm){
		NwCjDataForm form = this.get(featureForm.getId());
		form.setIsAddFeature("1"); // 已同步
		form.setObjectId(featureForm.getObjectid());
		this.save(form);
		return true;
	}
	/**
	 * 新增后回填(修改后回填)
	 * 同步失败，标记为2，成功标记为1
	 * */
	@Override
	public Boolean updateFeatureToForm(FeatureForm featureForm, String isAddFeature) {
		NwCjDataForm form = this.get(featureForm.getId());
		form.setIsAddFeature(isAddFeature);
		this.save(form);
		return true;
	}
	/**
	 * 获取区域下未同步数据或者更新失败数据
	 * @param parentOrgId 区域条件
	 * @return Long ids
	 * */
	public List<Long> getNotSyncForm(String parentOrgId,String isAddFeature){
			List<Long> listIds = new ArrayList<>();
			String sql = "select t.id from nw_cj_data t where 1=1 ";
			if("3".equals(isAddFeature)){
				sql+="and t.IS_ADD_FEATURE='3' and t.object_id is not null"; //修改状态
			}else{
				sql+="and t.IS_ADD_FEATURE='2'"; //否则就是同步失败
			}
			if(StringUtils.isNotBlank(parentOrgId)){
				sql+=" and t.parent_org_id ="+parentOrgId;
			}
			sql+=" and rownum <=500";
			List<BigDecimal> bigIds = (List<BigDecimal>)nwCjDataDao.getSession().createSQLQuery(sql).list();
		    for (BigDecimal bigDecimal : bigIds) {
		    	listIds.add(bigDecimal.longValue());
			}
			return listIds;
	}
	
	/**
	 * 上报统计和图表
	 * @param "startTime" 开始时间
	 * @param "endTime" 结束时间
	 * */
	public List<Map<String,Object>> toPartsAndChart(Map<String,Object> map){
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Map<String,Object>> listTotal= new ArrayList<>();
		StringBuffer sql = new StringBuffer("select PARENT_ORG_NAME,sum(total) as total from ("+
				"select S1.PARENT_ORG_NAME , count(*) as total from nw_cj_data s1 where 1=1 and s1.parent_org_name not like '%广州市水务局%'");
				
		if(map.size()>0 && map.containsKey("startTime") && map.containsKey("endTime")){
			String startTime = sim.format(map.get("startTime"));
			String endTime = sim.format(map.get("endTime"));
			sql.append(" and s1.mark_time between to_date('"+startTime+"','YYYY-MM-DD HH24:MI:SS')"
					+ "and to_date('"+endTime+"','YYYY-MM-DD HH24:MI:SS')");
		}
		if(map.containsKey("parentOrgName")){
			sql.append(" and s1.parent_org_name like '%"+map.get("parentOrgName").toString()+"%'");
		}
		if(map.containsKey("collectType")){
			sql.append(" and s1.collect_type like '%"+map.get("collectType").toString()+"%'");
		}
		sql.append(" GROUP BY s1.PARENT_ORG_NAME) GROUP BY PARENT_ORG_NAME  ");
		List<Object> list = nwCjDataDao.getSession().createSQLQuery(sql.toString()).list();
		for(Object obj : list){
			Map<String,Object> maps = new HashMap<>();
			Object[] ob =  (Object[]) obj;
			if(ob[0] !=null && !"广州市水务局".equals(ob[0].toString())){
				maps.put("name", ob[0]);
				maps.put("total", ob[1]);
				listTotal.add(maps);
			}
		}
		return listTotal;
	}
	/**
	 * 统计昨天或者今天的校核和新增
	 * @param 'boolean' yestDay 是否是昨天
	 * */
	private List<Object> getCountDay(Boolean yestDay,Map map){
		String wheres = "";
		if(map!=null){
			if(map.containsKey("collectType")){
				wheres+=" and ps.collect_Type like '%"+map.get("collectType").toString()+"%'";
			}
		}
		String sql ="select PARENT_ORG_NAME,"+
			"wm_concat( case typess when 'add' then total else null end)addCount, "+
			"wm_concat(case typess when 'check' then total else null end) checkCount from ("+
			"select PARENT_ORG_NAME,count(*) as total,'add' as typess  from nw_cj_data ps where 1=1 and ps.check_type like '%新增%'"+wheres+
			" and to_char(ps.mark_Time,'yyyyMMdd')=to_char(sysdate"+(yestDay?"-1":"")+",'yyyyMMdd') GROUP BY PARENT_ORG_NAME union ALL "+
			" select PARENT_ORG_NAME,count(*) as total,'check' as typess  from nw_cj_data ps where 1=1 and ps.check_type like '%校核%'"+wheres+
			" and to_char(ps.mark_Time,'yyyyMMdd')=to_char(sysdate"+(yestDay?"-1":"")+",'yyyyMMdd') GROUP BY PARENT_ORG_NAME)GROUP BY PARENT_ORG_NAME";
		return nwCjDataDao.getSession().createSQLQuery(sql).list();
	}
}