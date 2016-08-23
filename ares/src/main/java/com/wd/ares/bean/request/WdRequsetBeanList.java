package com.wd.ares.bean.request;

import java.util.ArrayList;
import java.util.List;

import com.wd.ares.bean.WdRequest;

import lombok.Data;

@Data
public class WdRequsetBeanList {
	private List<WdRequest> wdReqValues = new ArrayList<WdRequest>();
}
