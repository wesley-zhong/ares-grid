package com.wd.ares.db.rpc;

import lombok.Data;

@Data
public class WdRpcRequest {
	private String service;
	private String method;
	byte[] payLoad;
}
