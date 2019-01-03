package bambooforest.model;

import java.sql.Timestamp;

public class PostBean {
	private int postid;
	private String title;
	private String content;
	private Timestamp created;
	private String memeberid;

	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	
	public String getMemeberid() {
		return memeberid;
	}
	public void setMemeberid(String memeberid) {
		this.memeberid = memeberid;
	}
}
