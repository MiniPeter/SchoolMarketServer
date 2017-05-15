package com.peter.bean;

public class Notice {

    private int id;//N001
    private int authorId;
    private String title;
    private String content;
    private long createTime;
    
    
	@Override
	public String toString() {
		return "Notice [id=" + id + ", authorId=" + authorId + ", title=" + title + ", content=" + content
				+ ", createTime=" + createTime + "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
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
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
}
