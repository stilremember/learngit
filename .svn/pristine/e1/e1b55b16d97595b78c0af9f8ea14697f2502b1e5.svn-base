package com.augurit.myproject.utils.arcgis.http;

import com.augurit.myproject.utils.common.ThirdUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 同步上报图层功能类
 * @author hows
 * */
public final class httpArcgisClient {
	private static  Logger logger = Logger.getLogger(httpArcgisClient.class.getName());
	private static String url = new ThirdUtils().getRequest();
	private static int TIME_OUT=30000;//30s
	private static BasicHttpParams httpParams = null;
	static{
		httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, TIME_OUT); //连接超时
		HttpConnectionParams.setSoTimeout(httpParams, TIME_OUT); //响应超时
	}
	public static String addFeature(String features){
		String request_url = url+"/addFeatures";
		HttpClient httpClient = new DefaultHttpClient(httpParams);
		HttpPost post = new HttpPost(request_url);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("f", "json"));
		params.add(new BasicNameValuePair("features",features ));
		try {
			HttpEntity entity = new UrlEncodedFormEntity(params,"UTF-8");
			post.setHeader(new BasicHeader("ContentType", "text/plain;charset=utf-8"));
			post.setEntity(entity);
			HttpResponse response = httpClient.execute(post);
			if(response.getStatusLine().getStatusCode() == 200){
				String result = EntityUtils.toString(response.getEntity());
				JSONObject json =  JSONObject.fromObject(result);
				System.out.println("nw上报："+result);
				if(json.containsKey("addResults")){
					JSONArray jsonResult = json.getJSONArray("addResults");
					JSONObject js = jsonResult.getJSONObject(0);
					if(js.containsKey("success") ){
						return js.toString();
					}
					logger.info(result);
				}
			}
			return "300";
		} catch (Exception e) {
			System.out.println("addFeature请求连接或超时异常!");
			return "500";
		}
	}
	public static String updateFeature(String features){
		String request_url = url+"/updateFeatures";
		HttpClient httpClient = new DefaultHttpClient(httpParams);
		HttpPost post = new HttpPost(request_url);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("f", "json"));
		params.add(new BasicNameValuePair("features",features));
		try {
			HttpEntity entity = new UrlEncodedFormEntity(params,"UTF-8");
			post.setHeader(new BasicHeader("ContentType", "text/plain;charset=utf-8"));
			post.setEntity(entity);
			HttpResponse response = httpClient.execute(post);
			if(response.getStatusLine().getStatusCode() == 200){
				String result = EntityUtils.toString(response.getEntity());
				JSONObject json =  JSONObject.fromObject(result);
				//System.out.println("nw更新："+result);
				if(json.containsKey("updateResults")){
					JSONArray jsonResult = json.getJSONArray("updateResults");
					JSONObject js = jsonResult.getJSONObject(0);
					if(js.containsKey("success")){
						return js.toString();
					}
					logger.info(result);
				}
			}
		} catch (Exception e) {
			System.out.println("updateFeature请求连接或超时异常!");
			return "500";
		}
		return "300";
	}
	public static Boolean deleteFeature(String ids){
		String request_url = url+"/deleteFeatures";
		HttpClient httpClient = new DefaultHttpClient(httpParams);
		HttpPost post = new HttpPost(request_url);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("f", "json"));
		params.add(new BasicNameValuePair("objectIds",ids));
		try {
			HttpEntity entity = new UrlEncodedFormEntity(params,"UTF-8");
			post.setHeader(new BasicHeader("ContentType", "text/plain;charset=utf-8"));
			post.setEntity(entity);
			HttpResponse response = httpClient.execute(post);
			if(response.getStatusLine().getStatusCode() == 200){
				String result = EntityUtils.toString(response.getEntity());
				JSONObject json =  JSONObject.fromObject(result);
				System.out.println(json.toString());
				if(json.containsKey("deleteResults")){
					JSONArray jsonResult = json.getJSONArray("deleteResults");
					JSONObject js = jsonResult.getJSONObject(0);
					if(js.containsKey("success")){
						System.out.println("nw删除:"+js.toString());
						return true;
					}
					logger.info(result);
				}
				if("{}".equals(json.toString())){//说明该数据未在图层中
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			System.out.println("deleteFeature请求连接或超时异常!");
			return false;
		}
	}
	
}
