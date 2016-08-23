package com.wd.ares.db.service;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.wd.ares.bean.request.WdRequsetBeanList;
import com.wd.ares.db.utils.WdBeanUtils;

@Component
public class PurchaseService implements RpcService {
	
	@Inject
	 private DataSource dataSource;
	
	public String  purchaseStorageRefund(WdRequsetBeanList requestBean) throws SQLException, IOException {	
		Connection con = dataSource.getConnection();	
		String insertValues = WdBeanUtils.concertBeanListToInsertValue(requestBean.getWdReqValues());
		System.out.println(" values = " + insertValues);
		CallableStatement cstmt = con.prepareCall("{call dbo.wdProc_k3wsi_allprocess(?,?,?)}");
		cstmt.setString(1, "201");
		cstmt.setString(2, insertValues);
		cstmt.registerOutParameter(3, java.sql.Types.CHAR);
		cstmt.execute();
		String ret = cstmt.getNString(3);
		System.out.println("ret : " + ret);
		return ret;
	}
}
