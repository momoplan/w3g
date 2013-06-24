package com.ruyicai.wap.vo;

import java.text.ParseException;

import com.ruyicai.wap.util.validate.ValidateUserInfoUtil;

public class UserInfo {
	private String userNo;
	private String mobileId;
	private String state;
	private String passWord;
	private String name;
	private String certId;
	private String phone;
	private String email;
	private String address;
	private String qq;
	private String msn;
	private String regTime;
	private String modTime;
	private String nickName;
	private String channel;
	private String userName;
	
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getMobileId() {
		return mobileId;
	}
	public void setMobileId(String mobileId) {
		if(" ".equals(mobileId)||"null".equals(mobileId)) mobileId = "";
		this.mobileId = mobileId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCertId() {
		return certId;
	}
	public void setCertId(String certId) {
		certId = getCertId(certId);
		this.certId = certId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getMsn() {
		return msn;
	}
	public void setMsn(String msn) {
		this.msn = msn;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public String getModTime() {
		return modTime;
	}
	public void setModTime(String modTime) {
		this.modTime = modTime;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCertId(String certId){
		try {
			if("111111111111111".equals(certId)){
				return "";
			}
		} catch (NumberFormatException e) {
			return "";
		}
		return certId;
	}
}
