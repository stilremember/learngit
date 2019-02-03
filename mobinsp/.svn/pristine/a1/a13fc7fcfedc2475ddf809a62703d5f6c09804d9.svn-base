package com.augurit.myproject.utils.arcgis.timer;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import com.augurit.myproject.sjcj.service.INwReportDeleteService;
import com.augurit.myproject.sjcj.web.form.NwReportDeleteForm;
import com.augurit.myproject.utils.arcgis.http.httpArcgisClient;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 移动端修改操作图层类
 * @author hows
 * */
public class SynchronousDeleteData {
	 //队列大小  
    private static final int QUEUE_LENGTH_DELETE = 10000*10;  
    //基于内存的阻塞队列  
    private static BlockingQueue<NwReportDeleteForm> queue = new LinkedBlockingQueue<NwReportDeleteForm>(QUEUE_LENGTH_DELETE);
    //创建保存计划任务执行器  
    private static ScheduledExecutorService deleteEsecute = null;

	public static INwReportDeleteService nwReportDeleteService=null;
    private static Boolean flag=false;
    
    @Autowired
	public SynchronousDeleteData(INwReportDeleteService nwReportDeleteService){
    	SynchronousDeleteData.nwReportDeleteService = nwReportDeleteService;
	}
	/**
	 * 执行队列
	 * */
	private final static Runnable RunDeleteQueue = new Runnable() {
		@Override
		public void run() {
			try {
				//如果没有数据，则会阻塞线程
				NwReportDeleteForm takse =  queue.take();
				Boolean flags =  httpArcgisClient.deleteFeature(takse.getObjectId());
				if(!flags){
					//删除失败需要定时器定时循环
					takse.setIsDelete("2");
					nwReportDeleteService.save(takse);
				}else{
					takse.setDeleteTime(new Date());
					takse.setIsDelete("1");//删除成功
					takse.setObjectId(null);
					nwReportDeleteService.save(takse);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();//线程中断
				flag=false;
			}
		}
	};
	/**
	 * 执行队列(移动端修改队列)
	 * */
	public static void start(){
		if(deleteEsecute==null){
			deleteEsecute = Executors.newScheduledThreadPool(3);
			synchronized(deleteEsecute){
				flag=true;
				deleteEsecute.scheduleWithFixedDelay(RunDeleteQueue, 0, 1, TimeUnit.MICROSECONDS);
				System.out.println("启动农污删除队列成功!");
			}
		}
    }
	/**
	 * 停止定时器
	 * */
	public static void stop(){
		if(deleteEsecute!=null){
			synchronized(deleteEsecute){
				if(deleteEsecute!=null && !deleteEsecute.isShutdown()){
					deleteEsecute.shutdown();
					try {
						Boolean fa = deleteEsecute.awaitTermination(5, TimeUnit.SECONDS);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}finally{
						deleteEsecute=null;
					}
					System.out.println("关闭农污删除队列成功!");
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
	public static Boolean addFeature(NwReportDeleteForm form){
		if(form!=null){
			return queue.offer(form);
		}else{
			return false;
		}
	}
	/**
	 * 查看队列的详细信息（当前队列长度剩余长度等）
	 * */
	public static Map getQueueView(){
		Map map = new HashMap();
		map.put("currentLength", queue.size());   //当前队列长度 
		map.put("remaingLength", queue.remainingCapacity()); //剩余队列长度
		map.put("total", QUEUE_LENGTH_DELETE);  //总长度
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
	
	public static int getFeature(){
		return queue.size();
	}
	
	/**
	 * 定时删除
	 * */
	public static void deleteFeature(){
		synchronized(SynchronousUpdateData.class){
			List<NwReportDeleteForm> list =  nwReportDeleteService.getDeleteStatus("2");
			int i=0;
			if(list!=null && list.size()>0){  
				for(NwReportDeleteForm form : list){
					if(SynchronousDeleteData.addFeature(form)){
						i++;
					}
				}
				System.out.println("开始执行定时删除。。。。"+new Date().toString()+"删除"+i+"条数据!");
			}
		}
	}
	/**
	 * 删除（移动端删除操作）
	 **/
	public static Boolean deleteFeature(String objectId){
		synchronized(SynchronousDeleteData.class){
			if(StringUtils.isNotBlank(objectId)){
				Boolean result = httpArcgisClient.deleteFeature(objectId);
				return result;
			}else{
				return false;
			}
		}
	}
	
}
