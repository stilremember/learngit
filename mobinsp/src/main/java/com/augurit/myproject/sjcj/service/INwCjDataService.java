package com.augurit.myproject.sjcj.service;

import java.util.List;
import java.util.Map;

import com.augurit.ads.fw.common.from.LoginUserBaseForm;
import com.augurit.myproject.sjcj.web.form.NwCjDataAfftypeForm;
import com.augurit.myproject.sjcj.web.form.NwCjDataAttachmentForm;
import com.augurit.myproject.sjcj.web.form.NwCjDataForm;
import com.augurit.myproject.utils.arcgis.form.FeatureForm;
import org.springside.modules.orm.Page;

import com.augurit.ads.fw.common.service.ICrudService;
import org.springside.modules.orm.PageRequest;

public interface INwCjDataService extends ICrudService<NwCjDataForm, Long> {

	Page<NwCjDataForm> search(Page<NwCjDataForm> page, NwCjDataForm form,
                              Map<String, Object> map);

	void updateForm(NwCjDataForm form);
	
	/*void getUsersFrom(Map map, String loginName);*/

	Boolean featureToSaveForm(FeatureForm takse);

	Boolean updateFeatureToForm(FeatureForm featureForm, String isAddFeature);

	List<Long> getNotSyncForm(String object, String string);

	Boolean getBingYj(Long id);

	Boolean isComb(String string, Long long1);

	List<Map<String, Object>> toPartsAndChart(Map<String, Object> map);

	Map<String, Object> countCollect(NwCjDataForm form, Map<String, Object> map);

	Page<NwCjDataForm> searchAll(Page<NwCjDataForm> page, NwCjDataForm form,
                                 Map<String, Object> map);

	Map toPastNowDay(String layerName);

	Page<NwCjDataForm> searchCollectAll(Page<NwCjDataForm> page,
                                        NwCjDataForm form, Map<String, Object> map);

	Page<NwCjDataForm> searchPc(Page<NwCjDataForm> page, NwCjDataForm form,
								Map map);

	/*String getLatestTenList();*/

    /*void getUsersOrg(Map map, LoginUserBaseForm userForm);*/

	Page<NwCjDataForm> getSsd(Page<NwCjDataForm> page, NwCjDataForm form);

    void saveAddAll(NwCjDataForm form, List<NwCjDataAttachmentForm> listAttachment, List<NwCjDataAfftypeForm> listaff, List<Map<String, Object>> listMapPhotos);
}