package com.wd.ares.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WdControllerTest {
	
	@RequestMapping(value = "/wd",method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody	
	public String getUser(HttpServletRequest request) throws SQLException,
			IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(
				request.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = in.readLine()) != null) {
			sb.append(line);
		}
		System.out.println("========================================");
		System.out.println(sb.toString());
		System.out.println("========================================");
		return sb.toString();
	}
}
