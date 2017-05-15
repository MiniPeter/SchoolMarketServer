package com.peter.bean;

public class Trade {

    private int id;//��ƷID T001
    private String title;//��Ʒ����
    private int authorId;
    private double originalPrice;//��Ʒԭ�۸�
    private double nowPrice;//������Ʒ�۸�
    private String tagName;//��Ʒ���
    private String imgUrl;//��ƷͼƬ
    private String describe;//����
    private long createTime;//��Ʒ����ʱ��
    private int status;//��Ʒ״̬��0:���ۣ�1:�۳�
    
    @Override
	public String toString() {
		return "Trade [id=" + id + ", title=" + title + ", authorId=" + authorId + ", originalPrice=" + originalPrice
				+ ", nowPrice=" + nowPrice + ", tagName=" + tagName + ", imgUrl=" + imgUrl + ", describe=" + describe
				+ ", createTime=" + createTime + ", status=" + status + "]";
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
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}
	public double getNowPrice() {
		return nowPrice;
	}
	public void setNowPrice(double nowPrice) {
		this.nowPrice = nowPrice;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
