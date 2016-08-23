package com.wd.ares.bean;

import lombok.Data;

@Data
public class WdBaseResponse {
	private int responseCode;
	private String msg;
	private byte[] response;
}
