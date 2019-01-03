package bambooforest.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bambooforest.model.PostDBBean;

public class IndexAction implements Action{
	public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{	
		//DB에서 게시글 목록을 읽어와 request.setAttribute 할 예정
		
		PostDBBean db = PostDBBean.getInstance();
		request.setAttribute("postList", db.getPostList());
		
		return "/list.jsp";
	}
}