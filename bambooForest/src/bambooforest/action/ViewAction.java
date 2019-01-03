package bambooforest.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bambooforest.model.PostDBBean;

public class ViewAction implements Action{
	public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String pid = request.getParameter("postid");
		if(pid != null) {
			int postid = Integer.parseInt(pid);
			PostDBBean db = PostDBBean.getInstance();
			request.setAttribute("post", db.getPost(postid));
			request.setAttribute("replyList", db.getReplyList(postid));
		}
		return "/view.jsp";
	}
}
