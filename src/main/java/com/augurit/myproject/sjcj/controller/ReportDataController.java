package com.augurit.myproject.sjcj.controller;

import com.augurit.ads.fw.common.controller.BaseController;
import com.augurit.myproject.sjcj.service.INwCjDataService;
import com.augurit.myproject.sjcj.web.form.NwCjDataForm;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/reportData")
@Controller
public class ReportDataController extends BaseController<NwCjDataForm> {

    @Autowired
    private INwCjDataService nwCjDataService;

    /**
     *
     * @api {post} /reportData/getDataList 获取列表信息
     * @apiDescription 获取列表信息
     * @apiParam {Number} [pageNo] 页数
     * @apiParam {Number} [pageSize] 每页的条数
     * @apiGroup 查询类
     * @apiErrorExample {json} 错误返回值:
     *       {
     *          "code": 10003,
     *         "msg": "ParametersError [Method]:get_tests参数错误!",
     *         "error": {
     *              "id": "",
     *              "page": "",
     *              "perpage": ""
     *          },
     *         "status": "fail"
     *       }
     * @apiSuccessExample {json} 正确返回值:
     *   {
     *     "code": 0,
     *     "msg": "OK ",
     *     "data": [
     *        {
     *            "id": "622051004185471233",
     *            "testCode": "000050",
     *        }
     *    ],
     *    "status": "ok",
     *    "count": "14"
     *    }
     *
     * 查看列表
     * */
    @RequestMapping(value = "/getDataList",method = {RequestMethod.POST})
    @ResponseBody
    public String getDataList(NwCjDataForm form){
        Map map = new HashMap();
        List<Map> listMap= new ArrayList<>();
        String startTime=request.getParameter("startTime");
        String endTime=request.getParameter("endTime");
        JSONObject json = new JSONObject();
        page =  nwCjDataService.searchPc(page,form,map);
        if(page.getResult()!=null)
            json.put("rows", page.getResult());
            json.put("total", page.getTotalItems());
        return json.toString();
    }





}
