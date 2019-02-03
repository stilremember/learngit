package com.augurit.myproject.sjcj.service;

import java.util.List;

import com.augurit.ads.fw.common.service.ICrudService;
import com.augurit.myproject.sjcj.web.form.NwCjDataAttachmentForm;

public interface INwCjDataAttachmentService extends ICrudService<NwCjDataAttachmentForm, Long> {

	List<NwCjDataAttachmentForm> getCmbList(Long id);

	NwCjDataAttachmentForm getMarkIdForOne(String string);
}