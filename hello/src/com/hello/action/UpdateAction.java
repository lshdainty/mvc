package com.hello.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

import com.hello.model.DBBean;

public class UpdateAction implements Action{
	public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		JSONObject jsonObject = new JSONObject();
		
		String idString = request.getParameter("id");
		String finish = "NG";
		if(idString != null) {
			int id = Integer.parseInt(idString);
			DBBean db = DBBean.getInstance();
			int result = db.update(id, true);
			if(result == 1) {
				finish = "OK";
			}
		}
		jsonObject.put("result", finish);
		return jsonObject.toString();
	}
}