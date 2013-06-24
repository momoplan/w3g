package com.ruyicai.wap.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 投注和追号传到lottery中的参数
 * 
 * @author Administrator
 * 
 */
public class OrderRequest {
	private String batchcode;// 期号
	private String lotno;// 彩种
	private BigDecimal amt;// 总金额
	private String bettype;// zhuihao(0), taocan(1),
							// touzhu(2),hemai(3),zengsong(4),zengsong_nosms(5);
	private String userno;// 被赠送人
	private BigDecimal lotmulti;// 倍数
	private String buyuserno;// 赠送人
	private String channel;// 用户的渠道号
	private String subchannel;// 默认为00094293
	private BigDecimal paytype;// 0-为付款；1-已付款
	private BigDecimal oneamount;
	private BigDecimal drawway;//投注方式，（0-单式，1-复式，2-胆拖，3-单式上传），追号时使用
	private BigDecimal lotsType;//订单类型 0-单式上传，1-复式，2-胆拖，3-多方案
	private String memo;// 玩法
	private List<BetRequest> betRequests;// 投注实体类的集合
	private CaseLotRequest caseLotRequest;
	private BigDecimal prizeend = BigDecimal.ZERO;//中奖是否停止追号0：不停止,1：停止
	private BigDecimal accountnomoneysms = BigDecimal.ZERO;
	private List<SubscribeRequest> subscribeRequests;// 追号实体集合
	private String Blessing;
	/** 赠送彩票接受人手机号 */
	private String reciverMobile;
	public String getReciverMobile() {
		return reciverMobile;
	}

	public void setReciverMobile(String reciverMobile) {
		this.reciverMobile = reciverMobile;
	}

	public String getBlessing() {
		return Blessing;
	}

	public void setBlessing(String blessing) {
		Blessing = blessing;
	}

	public BigDecimal getDrawway() {
		return drawway;
	}

	public void setDrawway(BigDecimal drawway) {
		this.drawway = drawway;
	}

	public BigDecimal getLotsType() {
		return lotsType;
	}

	public void setLotsType(BigDecimal lotsType) {
		this.lotsType = lotsType;
	}

	public String getBatchcode() {
		return batchcode;
	}

	public void setBatchcode(String batchcode) {
		this.batchcode = batchcode;
	}

	public String getLotno() {
		return lotno;
	}

	public void setLotno(String lotno) {
		this.lotno = lotno;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public String getBettype() {
		return bettype;
	}

	public void setBettype(String bettype) {
		this.bettype = bettype;
	}

	public String getUserno() {
		return userno;
	}

	public void setUserno(String userno) {
		this.userno = userno;
	}

	public BigDecimal getLotmulti() {
		return lotmulti;
	}

	public void setLotmulti(BigDecimal lotmulti) {
		this.lotmulti = lotmulti;
	}

	public String getBuyuserno() {
		return buyuserno;
	}

	public void setBuyuserno(String buyuserno) {
		this.buyuserno = buyuserno;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getSubchannel() {
		return subchannel;
	}

	public void setSubchannel(String subchannel) {
		this.subchannel = subchannel;
	}

	public BigDecimal getPaytype() {
		return paytype;
	}

	public void setPaytype(BigDecimal paytype) {
		this.paytype = paytype;
	}


	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<BetRequest> getBetRequests() {
		return betRequests;
	}

	public void setBetRequests(List<BetRequest> betRequests) {
		this.betRequests = betRequests;
	}

	public BigDecimal getPrizeend() {
		return prizeend;
	}

	public void setPrizeend(BigDecimal prizeend) {
		this.prizeend = prizeend;
	}

	public BigDecimal getAccountnomoneysms() {
		return accountnomoneysms;
	}

	public void setAccountnomoneysms(BigDecimal accountnomoneysms) {
		this.accountnomoneysms = accountnomoneysms;
	}

	public List<SubscribeRequest> getSubscribeRequests() {
		return subscribeRequests;
	}

	public void setSubscribeRequests(List<SubscribeRequest> subscribeRequests) {
		this.subscribeRequests = subscribeRequests;
	}

	public BigDecimal getOneamount() {
		return oneamount;
	}

	public void setOneamount(BigDecimal oneamount) {
		this.oneamount = oneamount;
	}

	public CaseLotRequest getCaseLotRequest() {
		return caseLotRequest;
	}

	public void setCaseLotRequest(CaseLotRequest caseLotRequest) {
		this.caseLotRequest = caseLotRequest;
	}

}
