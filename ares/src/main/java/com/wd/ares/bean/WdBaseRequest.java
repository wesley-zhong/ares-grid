package com.wd.ares.bean;

import lombok.Data;

@Data
public class WdBaseRequest {
	private String rpcService;
	private String rpcMethod;
	private byte[] payLoad;
}
