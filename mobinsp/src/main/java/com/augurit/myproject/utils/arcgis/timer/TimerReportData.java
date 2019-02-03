package com.augurit.myproject.utils.arcgis.timer;

import com.augurit.ads.fw.base.SpringCronJob;
import com.augurit.myproject.sjcj.service.INwCjDataService;
import com.augurit.myproject.sjcj.service.INwReportDeleteService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试类
 * */
public class TimerReportData extends SpringCronJob {

	@Autowired
	private INwCjDataService nwCjDataService;@Autowired
	private INwReportDeleteService nwReportDeleteService;  //删除

	
	@Override
	public void run() {
		/*System.out.println("开始定时清理上报数据删除修改操作....................");
		if(SynchronousDeleteData.nwReportDeleteService==null) new SynchronousDeleteData(nwReportDeleteService);
		if(SynchronousUpdateData.nwCjDataService==null ) new SynchronousUpdateData(nwCjDataService);
		SynchronousDeleteData.deleteFeature();
		SynchronousUpdateData.updateFeature();
		SynchronousAddData.addNoSycFeature();*/

	}
}
