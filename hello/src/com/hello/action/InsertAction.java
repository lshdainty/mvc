package com.hello.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

import com.hello.model.DBBean;

public class InsertAction implements Action{
	public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		JSONObject jsonObject = new JSONObject();
		String job = request.getParameter("job");
		String resultString = "NG";
		String generatedId = null;
		
		if(job != null) {
			DBBean db = DBBean.getInstance();
			int result = db.insert(job);
			if(result != 0) {
				generatedId = String.valueOf(result);
				resultString = "OK";
			}else {
				resultString = "NG";
			}
			jsonObject.put("result", resultString);
			jsonObject.put("id", generatedId);
		}
		return jsonObject.toString();
	}
}