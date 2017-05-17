package com.peter.bean;

public class Msg {
	private int id;
    private String title;
    private String content;
    private long createTime;
    
	@Override
	public String toString() {
		return "Msg [id=" + id + ", title=" + title + ", content=" + content + ", createTime=" + createTime + "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
