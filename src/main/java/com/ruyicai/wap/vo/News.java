package com.ruyicai.wap.vo;

public class News {
	private String id;
	private String vol_typeid_fk;
	private String lotno;
	private String vol_title;
	private String vol_content;
	private String updatetime;
	private String createtime;
	private String state;
	private String author;


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLotno() {
		return lotno;
	}
	public void setLotno(String lotno) {
		this.lotno = lotno;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getVol_typeid_fk() {
		return vol_typeid_fk;
	}
	public void setVol_typeid_fk(String vol_typeid_fk) {
		this.vol_typeid_fk = vol_typeid_fk;
	}
	public String getVol_title() {
		return vol_title;
	}
	public void setVol_title(String vol_title) {
		this.vol_title = vol_title;
	}
	public String getVol_content() {
		return vol_content;
	}
	public void setVol_content(String vol_content) {
		this.vol_content = vol_content.replaceAll("\\r\\n|\\n|\\r", "<br/>");
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
