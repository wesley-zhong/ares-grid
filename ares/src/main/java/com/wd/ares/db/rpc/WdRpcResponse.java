package com.wd.ares.db.rpc;

import lombok.Data;

@Data
public class WdRpcResponse {
	private int responseCode;
	private String msg;
}
