package com.wd.ares.bean.request;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import com.wd.ares.bean.Purchase;

@Data
public class WdPurchaseReqeust {
	private List<Purchase> reqValues = new ArrayList<Purchase>();

}
