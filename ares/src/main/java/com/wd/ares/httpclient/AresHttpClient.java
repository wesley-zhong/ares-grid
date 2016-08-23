package com.wd.ares.httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wd.ares.bean.WdBaseRequest;
import com.wd.ares.bean.WdRequest;
import com.wd.ares.bean.request.WdRequsetBeanList;
import com.wd.ares.utils.AresFileReader;

public class AresHttpClient {
	
	public static void callRpcMethod(String url, Object obj, String rpcService, String method) throws ClientProtocolException, IOException{
	
		ObjectMapper objectMapper = new ObjectMapper();
		WdBaseRequest request = new WdBaseRequest();
		request.setRpcService(rpcService);
		request.setRpcMethod(method);
		request.setPayLoad(objectMapper.writeValueAsBytes(obj));
		
		
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create(); 
	    //HttpClient  
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build(); 
        HttpPost httpPost = new HttpPost(url); 
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setEntity(new ByteArrayEntity(objectMapper.writeValueAsBytes(request)));
        CloseableHttpResponse response =  closeableHttpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();  
        if (entity != null) {  
            System.out.println("--------------------------------------");  
            System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));  
            System.out.println("--------------------------------------");  
        }
        response.close();
	}
	
	public static void callRpcMethod(String url,byte[] body, String rpcService, String method) throws ClientProtocolException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		WdBaseRequest request = new WdBaseRequest();
		request.setRpcService(rpcService);
		request.setRpcMethod(method);
		request.setPayLoad(body);
	
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create(); 
	    //HttpClient  
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build(); 
        HttpPost httpPost = new HttpPost(url); 
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setEntity(new ByteArrayEntity(objectMapper.writeValueAsBytes(request)));
        CloseableHttpResponse response =  closeableHttpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();  
        if (entity != null) {  
            System.out.println("--------------------------------------");  
            System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));  
            System.out.println("--------------------------------------");  
        }
        response.close();
	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException{
		
		String url = "http://localhost:8080/wd/rpc";
//		List<WdRequest> requestList = new ArrayList<WdRequest>();
//		for(int i = 0 ; i < 3; i ++){
//			WdRequest  request = new WdRequest();
//			request.setFauxPropId("aaaa");
//			requestList.add(request);
//		}
//		WdRequsetBeanList reqestBeanList = new WdRequsetBeanList();
//		reqestBeanList.setWdReqValues(requestList);
		
		if(args.length < 3){
			System.out.println("please input [serviceName] [methodName] [fileName] in the cmd lines");
			return;
		}
		String serviceName = args[0];
		String methodName  = args[1];
		String fileName    = args[2];
		
		byte[] body = AresFileReader.readFileByBytes(fileName);
		callRpcMethod(url, body,serviceName, methodName);
	}
	   

}
