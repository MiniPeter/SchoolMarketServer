package com.peter.bean;

public class Order {

	private int id;
	private int tradeId;
	private int authorId;
	private int payId;
	private long createTime;
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", tradeId=" + tradeId + ", authorId=" + authorId + ", payId=" + payId
				+ ", createTime=" + createTime + "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTradeId() {
		return tradeId;
	}
	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public int getPayId() {
		return payId;
	}
	public void setPayId(int payId) {
		this.payId = payId;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
}
