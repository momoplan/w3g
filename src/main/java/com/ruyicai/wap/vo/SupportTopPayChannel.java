package com.ruyicai.wap.vo;

import java.util.List;

public class SupportTopPayChannel {
    private String                     name;
    
    private String                     cashierCode;

    private List<SupportSecPayChannel> supportSecPayChannelList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCashierCode() {
		return cashierCode;
	}

	public void setCashierCode(String cashierCode) {
		this.cashierCode = cashierCode;
	}

	public List<SupportSecPayChannel> getSupportSecPayChannelList() {
		return supportSecPayChannelList;
	}

	public void setSupportSecPayChannelList(
			List<SupportSecPayChannel> supportSecPayChannelList) {
		this.supportSecPayChannelList = supportSecPayChannelList;
	}

}
