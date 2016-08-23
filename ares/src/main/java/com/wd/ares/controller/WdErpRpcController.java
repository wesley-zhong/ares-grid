package com.wd.ares.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wd.ares.bean.WdBaseRequest;
import com.wd.ares.bean.WdBaseResponse;
import com.wd.ares.db.service.RpcService;
import com.wd.ares.rpc.MsgState;
import com.wd.ares.rpc.ServiceMgr;


@Controller
public class WdErpRpcController {
	
	@Inject
	private ServiceMgr  serviceMgr;
	private ObjectMapper objectMapper;
	
	
	@PostConstruct
	public void Init(){
		objectMapper = new ObjectMapper();
	}
	
	
	@RequestMapping(value = "/rpc", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody	
	public  WdBaseResponse  callRpc(@RequestBody WdBaseRequest  request){
		WdBaseResponse  response = new WdBaseResponse();

		RpcService service = serviceMgr.GetService(request.getRpcService());
		if(service == null){
			response.setResponseCode(MsgState.NO_SERVICE);
			return response;
		}
		String   methodName  = request.getRpcMethod();		
		Method method = this.GetMethod(service, methodName);
		if(method == null){
			response.setResponseCode(MsgState.NO_METHOD);
			return response;
		}
		try{
			response.setResponse( CallObjMethod(service, method, request.getPayLoad()));	
			response.setResponseCode(MsgState.OK);
		}catch(Throwable e){
			response.setResponse(e.toString().getBytes());;
		}
		return response;	
	}
	
	private Method GetMethod(Object obj, String methodName)
	{
		Method[] methods = obj.getClass().getMethods();
		for(int i = 0 ; i < methods.length ; ++i){
			if(methods[i].getName().equals(methodName)){
				return methods[i];
			}
		}
		return null;
	}
	
	private  byte[] CallObjMethod(RpcService service, Method method, byte[]  requestData) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, JsonParseException, JsonMappingException, IOException
	{
		 Class<?> methosParamType = method.getParameterTypes()[0];  
		 Object object =  objectMapper.readValue(requestData, methosParamType);
	     Object retObj =  method.invoke(service, object);
	     return objectMapper.writeValueAsBytes(retObj);       
	}

}
