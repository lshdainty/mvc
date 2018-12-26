package com.hello.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloAction implements Action{
	public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");	//인코딩을 먼저하고 한글 parameter을 받아야 한글이 안깨진다.
		
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String helloId = "test";
		String helloPw = "1234";
		String result="";
		
		if(helloId.equals(id)) {
			if(helloPw.equals(passwd)) {
				result = "1";	//로그인 성공
			}
			else {
				result = "0";//비밀번호 틀림
			}
		}else {
			result = "-1";
		}
		
		return "{ \"result\":\"ok\", \"message\":" + result + "}";
	}
}
