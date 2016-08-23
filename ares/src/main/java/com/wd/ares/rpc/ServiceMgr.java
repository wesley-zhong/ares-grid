package com.wd.ares.rpc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.wd.ares.db.service.RpcService;


@Component
public class ServiceMgr {

   @Inject
   private List<RpcService> jRpcServices;
		
	@PostConstruct
	public void Init(){
		for(RpcService  service : jRpcServices){
			String serviceName = service.getClass().getSimpleName();
			jServiceMaps.put(serviceName, service);
		}
	}
	
  
  public RpcService  GetService(String serviceName){
	  return jServiceMaps.get(serviceName);
  }
	
    private   Map<String,RpcService>  jServiceMaps = new HashMap<>();
}
