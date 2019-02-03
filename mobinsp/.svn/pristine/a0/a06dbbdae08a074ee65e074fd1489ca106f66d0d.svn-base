package com.augurit.myproject.utils.arcgis.timer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.augurit.myproject.sjcj.service.INwCjDataService;
import com.augurit.myproject.sjcj.web.form.NwCjDataForm;
import com.augurit.myproject.utils.arcgis.form.DataFormToFeatureConvertor;
import com.augurit.myproject.utils.arcgis.form.FeatureForm;
import com.augurit.myproject.utils.arcgis.http.httpArcgisClient;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * 上报信息新增图层功能类
 * @author hows
 * */
public class SynchronousAddData {
	 //队列大小  
    private static final int QUEUE_LENGTH = 100000*10;  
    //基于内存的阻塞队列  
    private static BlockingQueue<FeatureForm> queue = new LinkedBlockingQueue<FeatureForm>(QUEUE_LENGTH);
    //创建保存计划任务执行器  
    private static ScheduledExecutorService esecute = null;  
    public static Boolean flag=false;
	
    public static INwCjDataService nwCjDataService=null;
	@Autowired
	public SynchronousAddData(INwCjDataService nwCjDataService){
		SynchronousAddData.nwCjDataService = nwCjDataService;
	}
	
	/**
	 * 执行队列
	 * */
	private final static Runnable RunQueue = new Runnable() {
		@Override
		public void run() {
			try {
				//如果没有数据，则会阻塞线程
				FeatureForm takse =  queue.take();
				String features = DataFormToFeatureConvertor.convertFeatureToJson(takse);
				Boolean flags = false;
				if(StringUtils.isNotBlank(features)){
					String result = httpArcgisClient.addFeature(features);
					if(!"500".equals(result) && !"300".equals(result)){
						JSONObject json = JSONObject.fromObject(result);
						if(json.containsKey("objectId")){
							takse.setObjectid(Long.parseLong(json.getString("objectId")));
							flags = nwCjDataService.featureToSaveForm(takse);
						}
					}
				}
				if(!flags){
					//同步失败（标记isAddFeature）需要手动同步
					Boolean fa = nwCjDataService.updateFeatureToForm(takse,"2");
					if(!fa) addFeature(takse);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();//线程中断
				flag=false;
			}
		}
	};
	/**
	 * 启动
	 * */
	public static void start(){
		if(esecute==null){
			esecute = Executors.newScheduledThreadPool(3);
			synchronized(esecute){
				flag=true;
				esecute.scheduleWithFixedDelay(RunQueue, 0, 1, TimeUnit.MICROSECONDS);
				System.out.println("启动农污上报队列任务成功!");
			}
		}
    }
	/**
	 * 停止定时器
	 * */
	public static void stop(){
		if(esecute!=null){
			synchronized(esecute){
				if(esecute!=null && !esecute.isShutdown()){
					esecute.shutdown();
					try {
						Boolean fa = esecute.awaitTermination(5, TimeUnit.SECONDS);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}finally{
						esecute=null;
					}
					System.out.println("关闭农污上报队列任务成功!");
				}
			}
		}
	}
	/**
	 * 重启定时器
	 * */
	public static void restart(){
		stop();
		start();
	}
	/**
	 * 添加数据到队列中
	 * */
	public static Boolean addFeature(FeatureForm form){
		if(form!=null){
			return queue.offer(form);
		}else{
			return false;
		}
	}
	/**
	 * 添加数据到队列中
	 * */
	public static Boolean addFeatureList(List<FeatureForm> listForm){
		if(listForm!=null && listForm.size()>0){
			try {
				queue.addAll(listForm);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}else{
			return false;
		}
	}
	
	/**
	 * 查看队列的详细信息（当前队列长度剩余长度等）
	 * */
	public static Map getQueueView(){
		Map map = new HashMap();
		map.put("currentLength", queue.size());
		map.put("remaingLength", queue.remainingCapacity());
		map.put("total", QUEUE_LENGTH);
		return map;
	}
	/**
	 * 得到队列中的所有数据
	 * */
	public static Object[] getAll(){
		JSONObject json = new JSONObject();
		Object[] list = queue.toArray();
		return list;
	}
	
	/**
	 * 修改（bs端修改操作）
	 **/
	public static Boolean updateFeature(FeatureForm form){
		synchronized(SynchronousAddData.class){
			//form.setCheck_type("2"); bs端审核默认为2
			String features = DataFormToFeatureConvertor.convertFeatureToJson(form);
			Boolean flag = false;
			if(StringUtils.isNotBlank(features)){
				String result = httpArcgisClient.updateFeature(features);
				if(!"500".equals(result) && !"300".equals(result)){
					JSONObject json = JSONObject.fromObject(result);
					if(json.containsKey("success") && json.get("success").equals(true)){
						flag= true;
					}
				}
			}
			return flag;
		}
	}
	
	/**
	 * 定时执行add图层操作()
	 **/
	public static void addNoSycFeature(){
		synchronized(SynchronousAddData.class){
			List<Long> list =nwCjDataService.getNotSyncForm(null,"2");
			int i=0;
			if(list!=null && list.size()>0){
				for(Long id : list){
					NwCjDataForm form = nwCjDataService.get(id);
					if(form!=null){
						FeatureForm feature = DataFormToFeatureConvertor.convertCorrVoToForm(form);
						Boolean flags =  addFeature(feature);
						if(flags) i++;
					}
				}
				System.out.println("新增了"+i+"条数据!");
			}
		}
	}
}
