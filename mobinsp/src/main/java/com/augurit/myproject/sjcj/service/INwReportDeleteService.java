package com.augurit.myproject.sjcj.service;

import java.util.List;

import com.augurit.ads.fw.common.service.ICrudService;
import com.augurit.myproject.sjcj.web.form.NwReportDeleteForm;

public interface INwReportDeleteService extends ICrudService<NwReportDeleteForm, Long> {

	List<NwReportDeleteForm> getDeleteStatus(String isDelete);
}