package com.ruyicai.wap.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ruyicai.wap.util.bet.LotteryUtil;
import com.ruyicai.wap.vo.Account;
import com.ruyicai.wap.vo.CaseLot;
import com.ruyicai.wap.vo.Cash;
import com.ruyicai.wap.vo.DNABand;
import com.ruyicai.wap.vo.JingCaiSaiGuo;
import com.ruyicai.wap.vo.MsgRequest;
import com.ruyicai.wap.vo.Order;
import com.ruyicai.wap.vo.Present;
import com.ruyicai.wap.vo.Ranking;
import com.ruyicai.wap.vo.Score;
import com.ruyicai.wap.vo.UserInfo;
import com.ruyicai.wap.vo.WinInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Component
public class SelectAllUtil {
	private Logger logger = Logger.getLogger(SelectAllUtil.class);
	@Value("${lottery}")
	String lottery;
	@Value("${msgcenter}")
	String msgcenter;
	@Value("${ranking}")
	String ranking;
	@Value("${scorecenter}")
	String scorecenter;
	@Autowired
	CommonUtil commonUtil;
	@Autowired
	LotteryUtil lotteryUtil;
	@Autowired
	BetCodeAnalysisUtil betCodeAnalysisUtil;
	@Autowired
	OrderInfoAnalysisUtil orderInfoAnalysisUtil;
	@Autowired
	JingCaiOrderInfoAnalysisUtil jingCaiOrderInfoAnalysisUtil;

	/**
	 * 通过用户名查询用户信息
	 * 
	 * @param userName
	 * @return
	 */
	public String getUserInfoByUserName(String userName) {
		String url="";
		try{
			url = lottery + "tuserinfoes?json&find=ByUserName&userName="
					+ URLEncoder.encode(userName, "UTF-8");

			String result = commonUtil.setURLByGET(url);
			logger.info("SelectAllUtil/getUserInfoByUserName调用Lottery返回的result："
					+ result);
			return result;
		}catch (Exception e) {
			logger.info("SelectAllUtil/getUserInfoByUserName调用Lottery异常e："
					+ e.getMessage());
			return "";
		}
		
	}

	/**
	 * 查询用户信息
	 * 
	 * @param userName
	 * @return
	 */
	public UserInfo selectUserInfoByUserName(String userName) {
		UserInfo userInfo = new UserInfo();

		try{
		String result = getUserInfoByUserName(userName);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		if ("0".equals(errorCode)) {
			JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
			String userNo = jsonObjectValue.getString("userno");
			String mobileId = jsonObjectValue.getString("mobileid");
			String state = jsonObjectValue.getString("state");
			String passWord = jsonObjectValue.getString("password");
			String name = jsonObjectValue.getString("name");
			String certId = jsonObjectValue.getString("certid");
			String phone = jsonObjectValue.getString("phone");
			String email = jsonObjectValue.getString("email");
			String address = jsonObjectValue.getString("address");
			String qq = jsonObjectValue.getString("qq");
			String msn = jsonObjectValue.getString("msn");
			String regTime = jsonObjectValue.getString("regtime");
			String modTime = jsonObjectValue.getString("modtime");
			String nickName = jsonObjectValue.getString("nickname");
			String channel = jsonObjectValue.getString("channel");
			userName = jsonObjectValue.getString("userName");
			userInfo.setUserNo(userNo);
			userInfo.setMobileId(mobileId);
			userInfo.setState(state);
			userInfo.setPassWord(passWord);
			userInfo.setName(name);
			userInfo.setCertId(certId);
			userInfo.setPhone(phone);
			userInfo.setEmail(email);
			userInfo.setAddress(address);
			userInfo.setQq(qq);
			userInfo.setMsn(msn);
			userInfo.setRegTime(regTime);
			userInfo.setModTime(modTime);
			userInfo.setNickName(nickName);
			userInfo.setChannel(channel);
			userInfo.setUserName(userName);
		}
		return userInfo;
		}catch (Exception e) {
			return userInfo;
		}
	}

	/**
	 * 通过用户编号查询用户信息
	 * 
	 * @param userName
	 * @return
	 */
	public String getUserInfoByUserNo(String userNo) {
		String url = lottery + "tuserinfoes?json&find=ByUserno&userno="
				+ userNo;
		String result = commonUtil.setURLByGET(url);
		logger.info("SelectAllUtil/getUserInfoByUserNo调用Lottery返回的result："
				+ result);
		return result;
	}

	/**
	 * 查询用户信息
	 * 
	 * @param userName
	 * @return
	 */
	public UserInfo selectUserInfoByUserNo(String userNo) {
		UserInfo userInfo = new UserInfo();
		String result = getUserInfoByUserNo(userNo);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		if ("0".equals(errorCode)) {
			JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
			userNo = jsonObjectValue.getString("userno");
			String mobileId = jsonObjectValue.getString("mobileid");
			String state = jsonObjectValue.getString("state");
			String passWord = jsonObjectValue.getString("password");
			String name = jsonObjectValue.getString("name");
			String certId = jsonObjectValue.getString("certid");
			String phone = jsonObjectValue.getString("phone");
			String email = jsonObjectValue.getString("email");
			String address = jsonObjectValue.getString("address");
			String qq = jsonObjectValue.getString("qq");
			String msn = jsonObjectValue.getString("msn");
			String regTime = jsonObjectValue.getString("regtime");
			String modTime = jsonObjectValue.getString("modtime");
			String nickName = jsonObjectValue.getString("nickname");
			String channel = jsonObjectValue.getString("channel");
			userInfo.setUserNo(userNo);
			userInfo.setMobileId(mobileId);
			userInfo.setState(state);
			userInfo.setPassWord(passWord);
			userInfo.setName(name);
			userInfo.setCertId(certId);
			userInfo.setPhone(phone);
			userInfo.setEmail(email);
			userInfo.setAddress(address);
			userInfo.setQq(qq);
			userInfo.setMsn(msn);
			userInfo.setRegTime(regTime);
			userInfo.setModTime(modTime);
			userInfo.setNickName(nickName);
			userInfo.setChannel(channel);
		}
		return userInfo;
	}

	/**
	 * 查询用户账户信息
	 * 
	 * @param userno
	 * @return
	 */
	public String getUserAccount(String userno) {
		String url = lottery + "select/getAccount";
		String parameter = "userno=" + userno;
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("SelectAllUtil/getUserAccount调用Lottery返回的result：" + result);
		return result;
	}

	/**
	 * 查询用户账户信息
	 * 
	 * @param userno
	 * @return
	 */
	public Account selectUserAccount(String userNo) {
		String result = getUserAccount(userNo);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		String balance = "", freezeBalance = "", drawBalance = "", betBalance = "";
		Account account = new Account();
		if ("0".equals(errorCode) || errorCode == "0") {
			JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
			long balanceLong = jsonObjectValue.getLong("balance");// 总金额
			long freezeBalanceLong = jsonObjectValue.getLong("freezebalance");// 冻结金额
			long drawBalanceLong = jsonObjectValue.getLong("drawbalance");// 可提现金额

			BigDecimal balancebd = new BigDecimal(Long.toString(balanceLong));
			BigDecimal freezeBalancebd = new BigDecimal(freezeBalanceLong);
			betBalance = balancebd.subtract(freezeBalancebd).toString();
			long betBalanceLong = Long.parseLong(betBalance);
			drawBalanceLong = betBalanceLong > drawBalanceLong ? drawBalanceLong
					: betBalanceLong;

			balance = commonUtil
					.getBalanceFormat(Long.toString(balanceLong), 2);
			freezeBalance = commonUtil.getBalanceFormat(
					Long.toString(freezeBalanceLong), 2);
			drawBalance = commonUtil.getBalanceFormat(
					Long.toString(drawBalanceLong), 2);
			betBalance = commonUtil.getBalanceFormat(betBalance, 2);

			logger.info("SelectAllUtil/selectUserAccount处理之后账户金额balance:"
					+ balance + ",冻结金额freezeBalance:" + freezeBalance
					+ ",可提现金额drawBalance:" + drawBalance + ",可投注金额betBalance:"
					+ betBalance);

			account.setBalance(balance);
			account.setFreezeBalance(freezeBalance);
			account.setDrawBalance(drawBalance);
			account.setBetBalance(betBalance);
		}
		return account;
	}

	/**
	 * 查询DNA绑定信息
	 * 
	 * @param userno
	 * @return
	 */
	public String getUserDNABinding(String userno) {
		String url = lottery + "taccounts/getDNABinding";
		String parameter = "userno=" + userno;
		// 调用接口发送到投注后台
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("SelectAllUtil/getDNABinding查询是否DNA绑定返回：" + result);
		return result;
	}

	/**
	 * 查询DNA绑定信息
	 * 
	 * @param
	 * @return
	 */
	public DNABand selectUserDNABinding(String userno) {
		DNABand dnaBand = null;
		String result = getUserDNABinding(userno);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		if ("0".equals(errorCode)
				&& !"null".equals(jsonObject.getString("value"))&&jsonObject.getString("value")!=null) {
			JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
			String state = jsonObjectValue.getString("state");
			// 状态为1说明已经绑定
			if ("1".equals(state)) {
				String name = jsonObjectValue.getString("name");
				String bankCardNo = jsonObjectValue.getString("bankcardno");
				String bankName = jsonObjectValue.getString("bankname");
				String certId = jsonObjectValue.getString("certid");
				String mobileId = jsonObjectValue.getString("mobileid");

				logger.info("SelectAllUtil/selectUserDNABinding提现查询DNA绑定已绑定：name:"
						+ name
						+ ",bankNumber:"
						+ bankCardNo
						+ ",bankName:"
						+ bankName
						+ ",certId:"
						+ certId
						+ ",mobileId:"
						+ mobileId);
				if ("null".equals(bankName) || bankName == null
						|| " ".equals(bankName) || "".equals(bankName.trim())) {
					bankName = "";
				}
				dnaBand = new DNABand();
				dnaBand.setName(name);
				dnaBand.setBankCardNo(bankCardNo);
				dnaBand.setBankName(bankName);
				dnaBand.setCertId(certId);
				dnaBand.setMobileId(mobileId);
				return dnaBand;
			}
			logger.info("SelectAllUtil/selectUserDNABinding提现查询DNA绑定未绑定");
		}
		return dnaBand;
	}

	/**
	 * 提现记录查询
	 * 
	 * @param userno
	 * @param pageIndex
	 * @param maxResult
	 * @return
	 */
	public String getCashByUsernoAndPage(String userno,
			String pageIndex, String maxResult) {
		String url = lottery + "taccounts/queryCashByUsernoAndPage";
		String parameter = "userno=" + userno + "&pageIndex=" + pageIndex
				+ "&maxResult=" + maxResult;
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("SelectAllUtil/getCashByUsernoAndPage提现记录查询返回：" + result);
		return result;
	}

	/**
	 * 提现记录查询
	 * 
	 * @param userno
	 * @param pageIndex
	 * @param maxResult
	 * @return Map<String, Object>
	 */
	public Map<String, Object> selectCashByUsernoAndPage(String userno,
			String pageIndex, String maxResult) {
		List<Cash> drawCashList = new ArrayList<Cash>();
		Map<String, Object> map = new HashMap<String, Object>();
		Cash cash = null;
		String result = getCashByUsernoAndPage(userno, pageIndex, maxResult);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");

		if ("0".equals(errorCode)) {
			JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
			String totalPage = jsonObjectValue.getString("totalPage");
			String currentPageNo = jsonObjectValue.getString("currentPageNo");

			JSONArray jsonArray = jsonObjectValue.getJSONArray("list");
			for (int i = 0; i < jsonArray.size(); i++) {
				cash = new Cash();
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				String amt = commonUtil.getBalanceFormat(
						jsonObject2.getString("amt"), 2);
				cash.setPlatTime(commonUtil.getDataFormat(
						jsonObject2.getLong("plattime"), ""));
				cash.setAmt(amt);
				cash.setState(jsonObject2.getString("state"));
				cash.setRejectReason(jsonObject2.getString("rejectreason"));
				cash.setId(jsonObject2.getString("id"));
				drawCashList.add(cash);
			}
			map.put("drawCashList", drawCashList);
			map.put("totalPage", totalPage);
			map.put("currentPageNo", currentPageNo);
		}
		return map;
	}

	/**
	 * 查询用户留言
	 * 
	 * @param condition
	 *            (("EQS_userno", userno))
	 * @param startLine
	 * @param endLine
	 * @return
	 */
	public String getMsgs(JSONObject condition, String startLine,
			String endLine) {
		String url = msgcenter + "msg/selectMsgs";
		String parameter = "condition=" + condition + "&startLine=" + startLine
				+ "&endLine=" + endLine;
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("SelectAllUtil/getMsgs查询用户留言返回：" + result);
		return result;
	}

	/**
	 * 查询用户留言
	 * 
	 * @param condition
	 *            (("EQS_userno", userno))
	 * @param startLine
	 * @param endLine
	 * @return
	 */
	public Map<String, Object> selectMsgs(JSONObject condition,
			String startLine, String endLine) {
		Map<String, Object> map = new HashMap<String, Object>();
		String result = getMsgs(condition, startLine, endLine);
		JSONObject resultObject = JSONObject.fromObject(result);
		String errorCode = resultObject.getString("errorCode");
		if ("0".equals(errorCode)) {
			JSONObject jsonObjectValue = resultObject.getJSONObject("value");
			JSONArray jsonArray = jsonObjectValue.getJSONArray("list");
			String totalPage = jsonObjectValue.getString("totalPage");
			String currentPageNo = jsonObjectValue.getString("currentPageNo");
			List<MsgRequest> msgRequests = new ArrayList<MsgRequest>();
			MsgRequest msgRequest = null;
			for (int i = 0; i < jsonArray.size() && i < 20; i++) {
				msgRequest = new MsgRequest();
				JSONObject object = jsonArray.getJSONObject(i);
				String content = object.get("content") == null ? "" : object
						.getString("content");
				String reply = object.get("reply") == null ? "" : object
						.getString("reply");
				if ("null".equals(reply)) {
					reply = "";
				}
				msgRequest.setContent(content);
				msgRequest.setReply(reply);
				msgRequests.add(msgRequest);
			}
			map.put("totalPage", totalPage);
			map.put("currentPageNo", currentPageNo);
			map.put("msgRequests", msgRequests);
		} else {
			map.put("totalPage", "");
			map.put("currentPageNo", "");
			map.put("msgRequests", null);

		}
		return map;
	}

	/**
	 * 账户明细查询
	 * 
	 * @param userno
	 * @param beginTime
	 * @param endTime
	 * @param pageIndex
	 * @param maxResult
	 * @return
	 */
	public String getTaccountDetail(String userNo, String beginTime,
			String endTime, String pageIndex, String maxResult) {
		String url = lottery + "taccountdetails?get=Taccountdetail&json";
		String parameter = "&userno=" + userNo + "&beginTime=" + beginTime
				+ "&endTime=" + endTime + "&pageIndex=" + pageIndex
				+ "&maxResult=" + maxResult;

		String result = commonUtil.setURLByGET(url + parameter);
		logger.info("SelectAllUtil/getTaccountDetail提现记录查询返回：" + result);
		return result;
	}

	/**
	 * 账户明细查询
	 * 
	 * @param userno
	 * @param beginTime
	 * @param endTime
	 * @param pageIndex
	 * @param maxResult
	 * @return
	 */
	public Map<String, Object> selectTaccountDetail(String userNo,
			String beginTime, String endTime, String pageIndex, String maxResult) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Account> accounts = new ArrayList<Account>();
		Account account = null;
		String result = getTaccountDetail(userNo, beginTime, endTime,
				pageIndex, maxResult);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		if ("0".equals(errorCode)) {
			JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
			JSONArray jsonArray = jsonObjectValue.getJSONArray("list");
			String totalPage = jsonObjectValue.getString("totalPage");
			String currentPageNo = jsonObjectValue.getString("currentPageNo");
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				int blsign = jsonObject2.getInt("blsign");
				String platTime = commonUtil.getDataFormat(
						jsonObject2.getLong("plattime"), "");
				String memo = jsonObject2.getString("memo");
				String amt = commonUtil.getBalanceFormat(
						jsonObject2.getString("amt"), 2);

				if (!"".equals(memo) && memo != null) {
					if (memo.indexOf("gyj001") != -1) {
						memo = memo.substring(6);
					}
				}
				String amtView = "";
				if (blsign == -1)
					amtView = "-" + amt;
				else
					amtView = amt;
				account = new Account();
				account.setAmt(amtView);
				account.setMemo(memo);
				account.setPlatTime(platTime);
				accounts.add(account);
			}
			map.put("accounts", accounts);
			map.put("totalPage", totalPage);
			map.put("currentPageNo", currentPageNo);
		}
		return map;

	}

	/**
	 * 查询投注订单信息
	 * 
	 * @param userno
	 * @param beginTime
	 * @param endTime
	 * @param startLine
	 * @param endLine
	 * @return
	 */
	public String getTorders(String userno, String beginTime,
			String endTime, String startLine, String endLine) {
		String url = lottery + "select/getTorders2";
		String parameter = "userno=" + userno + "&beginTime=" + beginTime
				+ "&endTime=" + endTime + "&startLine=" + startLine
				+ "&endLine=" + endLine + "&isprize=1";
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("SelectAllUtil/getTorders查询订单信息返回：" + result);
		return result;
	}

	/**
	 * 查询用户中奖信息
	 * 
	 * @param userno
	 * @param beginTime
	 * @param endTime
	 * @param startLine
	 * @param endLine
	 * @return
	 */
	public Map<String, Object> selectUserWinning(String userno,
			String beginTime, String endTime, String startLine, String endLine) {
		Map<String, Object> userWinningMap = null;
		List<Order> orders = new ArrayList<Order>();
		Order order = null;
		logger.info("SelectAllUtil/selectUserWinning查询用户中奖信息开始...");
		String result = getTorders(userno, beginTime, endTime, startLine,
				endLine);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
		if ("0".equals(errorCode) && jsonObjectValue != null) {
			userWinningMap = new HashMap<String, Object>();
			String totalPage = jsonObjectValue.getString("totalPage");
			String currentPageNo = jsonObjectValue.getString("currentPageNo");
			JSONArray jsonArray = jsonObjectValue.getJSONArray("list");
			WinInfo winInfo = null;
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				JSONObject jsonObjectOrder = jsonObject2
						.getJSONObject("torder");
				String orderInfo = jsonObjectOrder.getString("orderinfo");
				String betCode = jsonObjectOrder.getString("betcode");
				String lotNo = jsonObjectOrder.getString("lotno");
				String lotName = lotteryUtil.getLotNameByLotNo(lotNo);
				String batchCode = jsonObjectOrder.getString("batchcode");
				String orderPrize = commonUtil.getBalanceFormat(
						jsonObjectOrder.getString("orderprize"), 2);
				String prizeInfo = jsonObjectOrder.getString("prizeinfo");
				String encashTime = "";
				if(jsonObjectOrder.getString("createtime")!=null&&!"null".equals(jsonObjectOrder.getString("createtime"))&&!"".equals(jsonObjectOrder.getString("createtime"))){
					encashTime = commonUtil.getDataFormat(
							jsonObjectOrder.getLong("encashtime"), "");
				}
				String createTime = commonUtil.getDataFormat(
						jsonObjectOrder.getLong("createtime"), "");
				String amount = commonUtil.getBalanceFormat(
						jsonObjectOrder.getString("amt"), 2);
				String beiShu = jsonObjectOrder.getString("lotmulti");
				String zhuShu = jsonObjectOrder.getString("betnum");
				String orderId = jsonObjectOrder.getString("id");
				String winBaseCode = jsonObjectOrder.getString("winbasecode");
				String betCodeView = "";
				if(lotNo.indexOf("J")>-1&&lotNo.startsWith("J")){
					betCodeView = jingCaiOrderInfoAnalysisUtil.orderInfoAnalysis(lotNo, orderId, orderInfo);
				}else{
					if(!"".equals(orderInfo)&&orderInfo!=null){
						betCodeView = orderInfoAnalysisUtil.orderInfoAnalysis(lotNo, orderInfo);
					}else{
						betCodeView = betCodeAnalysisUtil.betCodeAnalysis(lotNo, betCode);
					}
				}
				winInfo = selectTwininfo(lotNo, batchCode);
				winBaseCode = winInfo.getWinCode();
				order = new Order();
				order.setOrderId(orderId);
				order.setLotName(lotName);
				order.setOrderPrize(orderPrize);
				order.setAmount(amount);
				order.setPrizeInfo(prizeInfo);
				order.setEncashTime(encashTime);
				order.setCreateTime(createTime);
				order.setBetCode(betCode);
				order.setBeiShu(beiShu);
				order.setZhuShu(zhuShu);
				order.setBatchCode(batchCode);
				order.setWinBaseCode(winBaseCode);
				order.setBetCode(betCodeView);
				orders.add(order);
			}
			userWinningMap.put("totalPage", totalPage);
			userWinningMap.put("currentPageNo", currentPageNo);
			userWinningMap.put("orders", orders);
		}
		return userWinningMap;
	}

	/**
	 * 查询用户投注信息
	 * 
	 * @param userno
	 * @param beginTime
	 * @param endTime
	 * @param startLine
	 * @param endLine
	 * @param lotno
	 * @return
	 */
	public String getUseraction(String userNo, String beginTime,
			String endTime, String startLine, String endLine, String lotNo) {
		String url = lottery + "select/getUseraction";
		String parameter = "userno=" + userNo + "&beginTime=" + beginTime
				+ "&endTime=" + endTime + "&startLine=" + startLine
				+ "&endLine=" + endLine + "&state=1" + "&lotno=" + lotNo+"&bettype=2";
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("SelectAllUtil/getUseraction查询用户投注信息返回：" + result);
		return result;
	}

	/**
	 * 投注查询
	 * 
	 * @param userno
	 * @param beginTime
	 * @param endTime
	 * @param startLine
	 * @param endLine
	 * @param lotno
	 * @return
	 */
	public Map<String, Object> selectUserBetting(String userNo,
			String beginTime, String endTime, String startLine, String endLine,
			String lotNo) {
		Map<String, Object> userBettingMap = null;
		List<Order> orders = new ArrayList<Order>();
		Order order = null;
		String result = getUseraction(userNo, beginTime, endTime, startLine,
				endLine, lotNo);
		logger.info("SelectAllUtil/selectUserBetting查询用户投注列表");
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		if ("0".equals(errorCode)) {
			userBettingMap = new HashMap<String, Object>();
			JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
			String totalPage = jsonObjectValue.getString("totalPage");
			String currentPageNo = jsonObjectValue.getString("currentPageNo");
			JSONArray jsonArray = jsonObjectValue.getJSONArray("list");
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObjectOrder = jsonArray.getJSONObject(i)
						.getJSONObject("torder");
//				String batchCode = jsonObjectOrder.getString("batchcode");
				lotNo = jsonObjectOrder.getString("lotno");
				String lotName = lotteryUtil.getLotNameByLotNo(lotNo);
				String amount = commonUtil.getBalanceFormat(
						jsonObjectOrder.getString("amt"), 2);
				String createTime = commonUtil.getDataFormat(
						Long.parseLong(jsonArray.getJSONObject(i).getString("modifytime")), "");
				String orderInfo = jsonObjectOrder.getString("orderinfo");
				String betCode = jsonObjectOrder.getString("betcode");
				String betCodeView = "";
				String orderId = jsonObjectOrder.getString("id");
				if(lotNo.indexOf("J")>-1&&lotNo.startsWith("J")){
					betCodeView = jingCaiOrderInfoAnalysisUtil.orderInfoAnalysis(lotNo, orderId, orderInfo);
				}else{
					if(!"".equals(orderInfo)&&orderInfo!=null&&!"null".equals(orderInfo)){
						betCodeView = orderInfoAnalysisUtil.orderInfoAnalysis(lotNo, orderInfo);
					}else if(!"".equals(betCode)&&betCode!=null&&!"null".equals(betCode)){
						betCodeView = betCodeAnalysisUtil.betCodeAnalysis(lotNo, betCode);
					}
				}
				String beiShu = jsonObjectOrder.getString("lotmulti");
				String zhuShu = jsonObjectOrder.getString("betnum");
				String winBaseCode = "";
				order = new Order();
				if(lotNo.indexOf("J")>-1){
					order.setBatchCode("");
				}else{
					order.setBatchCode(jsonObjectOrder.getString("batchcode"));
					WinInfo winInfo = selectTwininfo(lotNo, jsonObjectOrder.getString("batchcode"));
					winBaseCode = winInfo.getWinCode();
				}
				String prizeState = jsonObjectOrder.getString("prizestate");
				String orderPrize = jsonObjectOrder.getString("orderprizeamt");//税后奖金
				System.out.println("========"+orderPrize);
				if("0".equals(prizeState)){
					prizeState = "未开奖";
				}else if("1".equals(prizeState)){
					prizeState = "等待开奖";
				}else if("2".equals(prizeState)){
					prizeState = "等待开奖";
				}else if("3".equals(prizeState)){
					prizeState = "未中奖";
				}else if("4".equals(prizeState)){
					if(!"null".equals(orderPrize)&&!"".equals(orderPrize)&&orderPrize!=null&&!"0".equals(orderPrize)){
						prizeState = "已中奖";
						orderPrize = commonUtil.getBalanceFormat(orderPrize, 2);
					}else{
						prizeState = "正在算奖";
					}
				}else if("5".equals(prizeState)){
					if(!"null".equals(orderPrize)&&!"".equals(orderPrize)&&orderPrize!=null&&!"0".equals(orderPrize)){
						prizeState = "已中奖";
						orderPrize = commonUtil.getBalanceFormat(orderPrize, 2);
					}else{
						prizeState = "正在算奖";
					}
				}
//				if("0".equals(prizeState)){
//					prizeState = "未开奖";
//				}else if("1".equals(prizeState)){
//					prizeState = "等待开奖";
//				}else if("2".equals(prizeState)){
//					prizeState = "等待开奖";
//				}else if("3".equals(prizeState)){
//					prizeState = "未中奖";
//				}else if("4".equals(prizeState)){
//					prizeState = "已中奖";
//				}else if("5".equals(prizeState)){
//					prizeState = "已中奖";
//
//				}
				order.setBeiShu(beiShu);
				order.setZhuShu(zhuShu);
				order.setPrizeState(prizeState);
				order.setOrderPrize(orderPrize);
				order.setWinBaseCode(winBaseCode);
				order.setBeiShu(beiShu);
				order.setOrderId(orderId);
				order.setBetCode(betCodeView);
				order.setAmount(amount);
				order.setLotNo(lotNo);
				order.setLotName(lotName);
				order.setCreateTime(createTime);
				orders.add(order);
			}
			userBettingMap.put("orders", orders);
			userBettingMap.put("totalPage", totalPage);
			userBettingMap.put("currentPageNo", currentPageNo);
			logger.info("SelectAllUtil/selectUserBetting查询用户投注列表成功");
		}
		return userBettingMap;
	}

	/**
	 * 查询开奖信息
	 * 
	 * @param lotno
	 * @param issuenum
	 * @return
	 */
	public String getTwininfoBylotno(String lotno, String issuenum) {
		try{
		String url = lottery + "select/getTwininfoBylotno";
		String parameter = "";
		if (issuenum == null && "".equals(issuenum)) {
			parameter = "lotno=" + lotno;// 传送参数
		} else {
			parameter = "lotno=" + lotno + "&issuenum=" + issuenum;// 传送参数
		}
		String result = commonUtil.setUrlByPOST(url, parameter);
//		logger.info("SelectAllUtil/getTwininfoBylotno查询开奖信息返回：" + result);
		return result;
		}catch (Exception e) {
			logger.error("SelectAllUtil/getTwininfoBylotno查询开奖信息异常e：" + e.getMessage());
			return "";
		}
	}

	/**
	 * 查询开奖信息
	 * 
	 * @param lotno
	 * @param issuenum
	 * @return
	 */
	public List<WinInfo> selectTwininfoByLotno(String lotNo,
			String issuenum) {
		List<WinInfo> wincodeList = new ArrayList<WinInfo>();
		try{
		String result = getTwininfoBylotno(lotNo, issuenum);
		JSONObject resultObject = JSONObject.fromObject(result);
		String errorCode = resultObject.getString("errorCode");
		if ("0".equals(errorCode)) {
			JSONArray valueArray = resultObject.getJSONArray("value");
			if (valueArray != null) {
				for (int i = 0; i < valueArray.size(); i++) {
					JSONObject jsonObject = valueArray.getJSONObject(i);
					WinInfo winInfo = new WinInfo();
					String batchCode = "";// 期号
					String openTime = "";// 开奖时间
					String winBaseCode = "";// 基础号码
					String winSpecialCode = "";// 开奖特殊号码
					String winCode = "";// 页面显示开奖号码
					if (jsonObject != null) {
						JSONObject jsonObjectId = jsonObject
								.getJSONObject("id");
						if (jsonObjectId != null) {
							batchCode = jsonObjectId.getString("batchcode");
							winInfo.setBatchCode(batchCode);
						}
						openTime = jsonObject.getString("opentime")==null ||"".equals(jsonObject.getString("opentime"))||"null".equals(jsonObject.getString("opentime"))? "" : commonUtil.getDataFormat(
								jsonObject.getLong("opentime"), "yyyy-MM-dd");
						winInfo.setOpenTime(openTime);
						winBaseCode = jsonObject.getString("winbasecode");
						winSpecialCode = jsonObject.getString("winspecialcode");
						winCode = getWinCode(lotNo, winBaseCode, winSpecialCode);
						winInfo.setWinCode(winCode);
						winInfo.setLotNo(lotNo);
					}
					wincodeList.add(winInfo);
				}
			}

		}
		return wincodeList;
		}catch (Exception e) {
			return wincodeList;
		}
	}

	public String getWinCode(String lotNo, String winBaseCode,
			String winSpecialCode) {
		String wincode = "";
		if (Constants.LOTNO_SSQ.equals(lotNo)) {// 双色球
			wincode = LotteryUtil.getDouHaoStringFromStringList(LotteryUtil
					.getStringListFromZeroString(winBaseCode))
					+ "|"
					+ winSpecialCode;
		}
		if (Constants.LOTNO_FC3D.equals(lotNo)) {// 福彩3D
			wincode = LotteryUtil.getDouHaoStringFromStringList(LotteryUtil
					.getStringListFromString(LotteryUtil
							.removeBetCodeZero(winBaseCode)));
		}
		if (Constants.LOTNO_QLC.equals(lotNo)) {// 七乐彩
			wincode = LotteryUtil.getDouHaoStringFromStringList(LotteryUtil
					.getStringListFromZeroString(winBaseCode))
					+ "|"
					+ winSpecialCode;
		}
		if (Constants.LOTNO_DLT.equals(lotNo)) {// 大乐透
			winBaseCode = winBaseCode.replaceAll(" ", "");
			wincode = LotteryUtil.getDouHaoStringFromStringList(LotteryUtil
					.getStringListFromZeroString(winBaseCode.substring(0, 10)))
					+ "|"
					+ LotteryUtil.getDouHaoStringFromStringList(LotteryUtil
							.getStringListFromZeroString(winBaseCode
									.substring(11)));
		}
		if (Constants.LOTNO_PL3.equals(lotNo)) {// 排列三
			wincode = LotteryUtil.getDouHaoStringFromStringList(LotteryUtil
					.getStringListFromString(winBaseCode));
		}
		if (Constants.LOTNO_ZC_SFC_14C.equals(lotNo)) {// 胜负彩
			wincode = winBaseCode;
		}
		if (Constants.LOTNO_ZC_R9C.equals(lotNo)) {// 任九场
			wincode = winBaseCode;
		}
		if (Constants.LOTNO_ZC_4C_JQC.equals(lotNo)) {// 进球彩
			wincode = winBaseCode;
		}
		if (Constants.LOTNO_ZC_6C_BQC.equals(lotNo)) {// 六场半
			wincode = winBaseCode;
		}
		if (Constants.LOTNO_SSC.equals(lotNo)) {// 时时彩
			wincode = LotteryUtil.getDouHaoStringFromStringList(LotteryUtil
					.getStringListFromString(winBaseCode));
		}
		if (Constants.LOTNO_QXC.equals(lotNo)) {// 七星彩
			wincode = LotteryUtil.getDouHaoStringFromStringList(LotteryUtil
					.getStringListFromString(winBaseCode));
		}
		if (Constants.LOTNO_DLC_JX11X5.equals(lotNo)) {// 江西十一选五(多乐彩)
			wincode = winBaseCode.replaceAll(" ", ",");
		}
		if (Constants.LOTNO_PL5.equals(lotNo)) {// 排列五
			wincode = LotteryUtil.getDouHaoStringFromStringList(LotteryUtil
					.getStringListFromString(winBaseCode));
		}
		if (Constants.LOTNO_11YDJ_SD11X5.equals(lotNo)) {// 十一运夺金
			wincode = winBaseCode.replaceAll(" ", ",");
		}
		if (Constants.LOTNO_22X5.equals(lotNo)) {// 二十二选五
			wincode = winBaseCode.replaceAll(" ", ",");
		}
		if (Constants.LOTNO_GD11X5.equals(lotNo)) {// 广东11选5
			wincode = winBaseCode.replaceAll(" ", ",");
		}
		if (Constants.LOTNO_GDKL10F.equals(lotNo)) {// 广东快乐十分
			wincode = winBaseCode.replaceAll(" ", ",");
		}
		return wincode;

	}

	/**
	 * 查询开奖详细信息包含奖池
	 * 
	 * @param lotNo
	 * @param issue期号
	 * @return
	 */
	public String getTwininfo(String lotNo, String issue) {
		String url = lottery + "select/getTwininfo";
		String parameter = "lotno=" + lotNo + "&issue=" + issue;
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("SelectAllUtil/getTwininfo查询开奖信息返回：" + result);
		return result;
	}

	/**
	 * 查询开奖详细信息包含奖池
	 * 
	 * @param lotNo
	 * @param issue
	 * @return
	 */
	public WinInfo selectTwininfo(String lotNo, String issue) {
		WinInfo winInfo = new WinInfo();
		String result = getTwininfo(lotNo, issue);
		JSONObject winInfoObject = JSONObject.fromObject(result);
		if (winInfoObject == null)
			return winInfo;
		String errorCode = winInfoObject.getString("errorCode");
		if ("0".equals(errorCode)) {
			JSONObject valueObject = winInfoObject.getJSONObject("value");
			JSONObject idObject = valueObject.getJSONObject("id");
			String batchCode = idObject.getString("batchcode");
			String lotName = lotteryUtil.getLotNameByLotNo(lotNo);
			String openTime = "";
			if(valueObject.getString("opentime")!=null&&!"null".equals(valueObject.getString("opentime"))){
				openTime = commonUtil.getDataFormat(valueObject.getLong("opentime"), "");
			}
			String winBaseCode = valueObject.getString("winbasecode");
			String winSpecialCode = valueObject.getString("winspecialcode");
			String info = valueObject.getString("info");
			if (!"null".equals(info) && !"".equals(info) && info != null) {
				info = getInfo(lotNo, info);
			} else {
				info = "";
			}
			String winCode = getWinCode(lotNo, winBaseCode, winSpecialCode);
			winInfo.setBatchCode(batchCode);
			winInfo.setInfo(info);
			winInfo.setLotName(lotName);
			winInfo.setLotNo(lotNo);
			winInfo.setOpenTime(openTime);
			winInfo.setWinCode(winCode);
		}
		return winInfo;
	}

	/**
	 * 追号查询
	 * 
	 * @param userNo
	 * @param lotNo
	 * @param beginTime
	 * @param endTime
	 * @param startLine
	 * @param endLine
	 * @return
	 */
	public String getTsubscribe(String userNo, String lotNo,
			String beginTime, String endTime, String startLine, String endLine) {
		String url = lottery + "select/getTsubscribe";
		String parameter = "userno=" + userNo + "&lotno=" + lotNo
				+ "&beginTime=" + beginTime + "&endTime=" + endTime
				+ "&startLine=" + startLine + "&endLine=" + endLine
				+ "&types=0,2";
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("SelectAllUtil/getTsubscribe查追号列表返回内容：" + result);
		return result;
	}

	/**
	 * 追号查询
	 * 
	 * @param userNo
	 * @param lotNo
	 * @param beginTime
	 * @param endTime
	 * @param startLine
	 * @param endLine
	 * @return
	 */
	public Map<String, Object> selectAddNumber(String userNo,
			String lotNo, String beginTime, String endTime, String startLine,
			String endLine) {
		Map<String, Object> addNumberMap = null;
		List<Order> orders = new ArrayList<Order>();
		Order order = null;
		String result = getTsubscribe(userNo, lotNo, beginTime,
				endTime, startLine, endLine);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		if ("0".equals(errorCode)) {
			addNumberMap = new HashMap<String, Object>();
			JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
			String totalPage = jsonObjectValue.getString("totalPage");
			String currentPageNo = jsonObjectValue.getString("currentPageNo");
			JSONArray jsonArray = jsonObjectValue.getJSONArray("list");
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				String flowNo = jsonObject2.getString("flowno");
				String state = jsonObject2.getString("state");
				String totalAmt = commonUtil.getBalanceFormat(
						jsonObject2.getString("totalamt"), 2);
				String amount = commonUtil.getBalanceFormat(
						jsonObject2.getString("amount"), 2);

				String lotName = lotteryUtil.getLotNameByLotNo(jsonObject2
						.getString("lotno"));
				String prizeend = jsonObject2.getString("prizeend");

				String beginBatch = jsonObject2.getString("beginbatch");
				int batchNum = jsonObject2.getInt("batchnum");
				int lastNum = jsonObject2.getInt("lastnum");
				String totalAmount = commonUtil.getBalanceFormat(
						(jsonObject2.getLong("amount") * batchNum) + "", 2);
				int nowNum = batchNum - lastNum;
				if ("0".equals(state)) {
					if (lastNum > 0) {
						state = "进行中";
					} else {
						state = "已追完";
					}
				}
				if ("2".equals(state)) {
					if (lastNum > 0) {
						state = "已撤销";
					} else {
						state = "已追完";
					}
				}
				if ("3".equals(state)) {
					state = "已追完";
				}
				order = new Order();
				order.setFlowNo(flowNo);
				order.setPrizeend(prizeend);
				order.setNowNum(nowNum + "");
				order.setState(state);
				order.setTotalAmt(totalAmt);
				order.setLotName(lotName);
				order.setBeginBatch(beginBatch);
				order.setBatchNum(batchNum + "");
				order.setAmount(amount);
				order.setTotalAmount(totalAmount);
				orders.add(order);
			}
			addNumberMap.put("totalPage", totalPage);
			addNumberMap.put("currentPageNo", currentPageNo);
			addNumberMap.put("orders", orders);
		}
		return addNumberMap;
	}
	/**
	 * 查追号详情
	 * @param flowNo
	 * @param startLine
	 * @param endLine
	 * @return
	 */
	public String getTorderByScribeno(String flowNo,String startLine,String endLine) {
		String url = lottery + "select/getTorderByScribeno?";
		String parameter = "flowno=" + flowNo+"&startLine="+startLine+"&endLine="+endLine;
		String result = commonUtil.setURLByGET(url + parameter);
		logger.info("SelectAllUtil/getTorderByScribeno查追号详情返回内容：" + result);
		return result;
	}
	/**
	 * 查询追号详情
	 * @param flowNo
	 * @param startLine
	 * @param endLine
	 * @return
	 */
	public Order selectAddNumberDetail(String flowNo,String startLine,String endLine){
		String result = getTorderByScribeno(flowNo, startLine, endLine);
		Order order = null;
		JSONObject jsonObjectResult = JSONObject.fromObject(result);
		String errorCode = jsonObjectResult.getString("errorCode");
		if("0".equals(errorCode)){
			JSONObject jsonObjectValue = jsonObjectResult.getJSONObject("value");
			JSONArray jsonArray = jsonObjectValue.getJSONArray("list");
			long totalAmount = 0;//总金额
			long successAmount = 0;//已追金额
			int batchNum = 0;//总期数
			int nowNum = 0;//已追期数
			int failNum = 0;//撤销期数
			String beginBatch = "";
			String beginTime = "";//开始时间
			String createTime = "";
			String betCodeView = "";
			if(jsonArray!=null&&jsonArray.size()>0){
				JSONObject jsonObject =null;
				JSONObject jsonObjectOrder =null;
				JSONObject jsonObjectUserAction = null;
				for(int i=0;i<jsonArray.size();i++){
					jsonObject = jsonArray.getJSONObject(i);
					jsonObjectOrder = jsonObject.getJSONObject("torder");
					jsonObjectUserAction = jsonObject.getJSONObject("tuseraction");
					String orderstate = jsonObjectOrder.getString("orderstate");
					if(i==0){
						long createtime = jsonObjectOrder.getLong("createtime");
						beginTime = commonUtil.getDataFormat(createtime, "");
						beginBatch = jsonObjectUserAction.getString("batchcode");
					}
					if("3".equals(orderstate)){
						failNum++;
					}
					if("1".equals(orderstate)){
						long createtime = jsonObjectOrder.getLong("createtime");
						createTime= commonUtil.getDataFormat(createtime, "");
						successAmount += Integer.parseInt(jsonObjectUserAction.getString("orderamt"));
						nowNum++;
					}
					totalAmount += Integer.parseInt(jsonObjectUserAction.getString("orderamt"));
					batchNum++;
				}
				String orderInfo = jsonObjectOrder.getString("orderinfo");
				String betCode = jsonObjectOrder.getString("betcode");
				String lotNo = jsonObjectOrder.getString("lotno");
				String lotName = lotteryUtil.getLotNameByLotNo(lotNo);
				if(!"".equals(orderInfo)&&orderInfo!=null){
					betCodeView = orderInfoAnalysisUtil.orderInfoAnalysis(lotNo, orderInfo);
				}else{
					betCodeView = betCodeAnalysisUtil.betCodeAnalysis(lotNo, betCode);
				}
				order = new Order();
				
				order.setLotName(lotName);
				order.setFlowNo(flowNo);
				order.setBatchNum(batchNum+"");
				order.setNowNum(nowNum+"");
				order.setFailNum(failNum+"");
				order.setBeginBatch(beginBatch);
				order.setTotalAmount(commonUtil.getBalanceFormat(totalAmount+"", 2));
				order.setSuccessAmount(commonUtil.getBalanceFormat(successAmount+"", 2));
				order.setCreateTime(createTime);
				order.setBeginTime(beginTime);
				order.setBetCode(betCodeView);
			}
		}
		return order;
	}

	/**
	 * 放弃追号
	 * 
	 * @param tsubscribeNo
	 * @return
	 */
	public String giveUpSubscribe(String tsubscribeNo) {
		String url = lottery + "bet/giveupSubscribe";
		String parameter = "tsubscribeNo=" + tsubscribeNo;
		String result = commonUtil.setUrlByPOST(url, parameter);// 调用
		logger.info("SelectAllUtil/giveUpSubscribe停止追号返回内容：" + result);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		return errorCode;
	}
	/**
	 * 查询被赠送列表
	 * 
	 * @param userno
	 *            被赠送用户userNo
	 * @param condition
	 *            查询条件JSON
	 * @param startLine
	 *            开始记录数
	 * @param endLine总记录数
	 * @param orderBy排序字段
	 * @param orderDir排序类型
	 * @return
	 */
	public String getReciverPresentDetails(String userNo,
			String startLine, String endLine) {
		String url = lottery + "present/selectReciverPresentDetails";
		String parameter = "userno=" + userNo + "&startLine=" + startLine+ "&endLine=" + endLine;
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("SelectAllUtil/getReciverPresentDetails查询被赠送列表 返回：" + result);
		return result;
	}
	/**
	 * 查询被赠送列表
	 * @param userNo
	 * @param startLine
	 * @param endLine
	 * @return Map<String, Object>
	 */
	public Map<String, Object> selectReciverPresentDetails(String userNo,
			String startLine, String endLine){
		Map<String, Object> map = new HashMap<String, Object>();
		String result = getReciverPresentDetails(userNo, startLine, endLine);
		JSONObject resultObject = JSONObject.fromObject(result);
		String errorCode = resultObject.getString("errorCode");
		if("0".equals(errorCode)){
			JSONObject jsonObjectValue = resultObject.getJSONObject("value");
			String totalPage = jsonObjectValue.getString("totalPage");
			String currentPageNo = jsonObjectValue.getString("currentPageNo");
			JSONArray jsonArray = jsonObjectValue.getJSONArray("list");
			Present present = null;
			List<Present> presents = new ArrayList<Present>();
			String lotNo ="";
			String lotName = "";
			String betCode = "";
			String orderInfo = "";
			String betCodeView = "";
			for(int i=0;i<jsonArray.size()&&i<10;i++){
				present = new Present();
				JSONObject object = jsonArray.getJSONObject(i);
				JSONObject presentDetails = object.getJSONObject("presentDetails");
				JSONObject jsonObjectOrder = object.getJSONObject("torder");
				JSONObject userinfo = object.getJSONObject("tuserinfo");
				present.setId(presentDetails.getString("id"));
				present.setUserName(userinfo.getString("userName"));
				present.setAmt(commonUtil.getBalanceFormat(jsonObjectOrder.getString("amt"), 2));
				lotNo = jsonObjectOrder.getString("lotno");
				lotName = lotteryUtil.getLotNameByLotNo(lotNo);
				present.setLotName(lotName);
				betCode = jsonObjectOrder.getString("betcode");
				orderInfo = jsonObjectOrder.getString("orderinfo");
			
				if(orderInfo!=null&&!"".equals(orderInfo)&&!"null".equals(orderInfo)){
					betCodeView = orderInfoAnalysisUtil.orderInfoAnalysis(lotNo, orderInfo);
				}else{
					betCodeView = betCodeAnalysisUtil.betCodeAnalysis(lotNo, betCode);
				}
				String winCode = "";
				if(lotNo.indexOf("J0000")>-1){
					present.setBatchCode("");
				}else{
					present.setBatchCode(jsonObjectOrder.getString("batchcode"));
					WinInfo winInfo = selectTwininfo(lotNo, jsonObjectOrder.getString("batchcode"));
					winCode = winInfo.getWinCode();
				}
				String beiShu = jsonObjectOrder.getString("lotmulti");
				String zhuShu = jsonObjectOrder.getString("betnum");
				String prizeState = jsonObjectOrder.getString("prizestate");
				String orderPrize = jsonObjectOrder.getString("orderprize");//税后奖金
				if("0".equals(prizeState)){
					prizeState = "未开奖";
				}else if("1".equals(prizeState)){
					prizeState = "等待开奖";
				}else if("2".equals(prizeState)){
					prizeState = "等待开奖";
				}else if("3".equals(prizeState)){
					prizeState = "未中奖";
				}else if("4".equals(prizeState)){
					System.out.println("==="+orderPrize);
					if(!"null".equals(orderPrize)&&!"".equals(orderPrize)&&orderPrize!=null&&!"0".equals(orderPrize)){
						prizeState = "已中奖";
						orderPrize = commonUtil.getBalanceFormat(orderPrize, 2);
					}else{
						prizeState = "正在算奖";
					}
				}else if("5".equals(prizeState)){
					if(!"null".equals(orderPrize)&&!"".equals(orderPrize)&&orderPrize!=null&&!"0".equals(orderPrize)){
						prizeState = "已中奖";
						orderPrize = commonUtil.getBalanceFormat(orderPrize, 2);
					}else{
						prizeState = "正在算奖";
					}
				}
				present.setBeiShu(beiShu);
				present.setZhuShu(zhuShu);
				present.setBetCode(betCodeView);
				present.setWinCode(winCode);
				present.setOrderPrize(orderPrize);
				present.setPrizeState(prizeState);

				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				String time = format.format(new Date(Long.parseLong(presentDetails.getString("createTime"))));
				time = time.substring(0,4)+"年"+time.substring(4,6)+"月"+time.substring(6)+"日<br/><br/>";
				present.setCreateTime(time);
				presents.add(present);
			}
			map.put("presents", presents);
			map.put("totalPage", totalPage);
			map.put("currentPageNo", currentPageNo);
		}
		return map;
	}
	/**
	 * 查询赠送列表
	 * 
	 * @param userno
	 *            赠送用户userNo
	 * @param condition
	 *            查询条件JSON
	 * @param startLine
	 *            开始记录数
	 * @param endLine总记录数
	 * @param orderBy排序字段
	 * @param orderDir排序类型
	 * @return
	 */
	public String getSenderPresentDetails(String userNo,
			String startLine, String endLine) {
		String url = lottery + "present/selectSenderPresentDetails";
		String parameter = "userno=" + userNo + "&startLine=" + startLine+"&endLine=" + endLine;
		String str = commonUtil.setUrlByPOST(url, parameter);
		logger.info("查询赠送列表 返回：" + str);
		return str;
	}
	/**
	 * 查询赠送列表
	 * @param userNo
	 * @param startLine
	 * @param endLine
	 * @return Map<String, Object>
	 */
	public Map<String, Object> selectSenderPresentDetails(String userNo,
			String startLine, String endLine){
		Map<String, Object> map = new HashMap<String, Object>();
		String result = getSenderPresentDetails(userNo, startLine, endLine);
		JSONObject resultObject = JSONObject.fromObject(result);
		String errorCode = resultObject.getString("errorCode");
		if("0".equals(errorCode)){
			JSONObject jsonObjectValue = resultObject.getJSONObject("value");
			String totalPage = jsonObjectValue.getString("totalPage");
			String currentPageNo = jsonObjectValue.getString("currentPageNo");
			JSONArray jsonArray = jsonObjectValue.getJSONArray("list");
			Present present = null;
			List<Present> presents = new ArrayList<Present>();
			String lotNo ="";
			String lotName = "";
			String betCode = "";
			String orderInfo = "";
			String betCodeView = "";
			for(int i=0;i<jsonArray.size()&&i<10;i++){
				present = new Present();
				JSONObject object = jsonArray.getJSONObject(i);
				JSONObject jsonObjectOrder = object.getJSONObject("torder");
				JSONObject presentDetails = object.getJSONObject("presentDetails");
				present.setMobile(presentDetails.getString("reciverMobile"));
				present.setAmt(commonUtil.getBalanceFormat(jsonObjectOrder.getString("amt"), 2));
				lotNo = jsonObjectOrder.getString("lotno");
				lotName = lotteryUtil.getLotNameByLotNo(lotNo);
				present.setLotName(lotName);
				betCode = jsonObjectOrder.getString("betcode");
				orderInfo = jsonObjectOrder.getString("orderinfo");
			
				if(orderInfo!=null&&!"".equals(orderInfo)&&!"null".equals(orderInfo)){
					betCodeView = orderInfoAnalysisUtil.orderInfoAnalysis(lotNo, orderInfo);
				}else{
					betCodeView = betCodeAnalysisUtil.betCodeAnalysis(lotNo, betCode);
				}
				String winCode = "";
				if(lotNo.indexOf("J0000")>-1){
					present.setBatchCode("");
				}else{
					present.setBatchCode(jsonObjectOrder.getString("batchcode"));
					WinInfo winInfo = selectTwininfo(lotNo, jsonObjectOrder.getString("batchcode"));
					winCode = winInfo.getWinCode();
				}
				String beiShu = jsonObjectOrder.getString("lotmulti");
				String zhuShu = jsonObjectOrder.getString("betnum");
				String prizeState = jsonObjectOrder.getString("prizestate");
				String orderPrize = jsonObjectOrder.getString("orderprizeamt");//税后奖金
				if("0".equals(prizeState)){
					prizeState = "未开奖";
				}else if("1".equals(prizeState)){
					prizeState = "等待开奖";
				}else if("2".equals(prizeState)){
					prizeState = "等待开奖";
				}else if("3".equals(prizeState)){
					prizeState = "未中奖";
				}else if("4".equals(prizeState)){
					if(!"null".equals(orderPrize)&&!"".equals(orderPrize)&&orderPrize!=null&&!"0".equals(orderPrize)){
						prizeState = "已中奖";
						orderPrize = commonUtil.getBalanceFormat(orderPrize, 2);
					}else{
						prizeState = "正在算奖";
					}
				}else if("5".equals(prizeState)){
					if(!"null".equals(orderPrize)&&!"".equals(orderPrize)&&orderPrize!=null&&!"0".equals(orderPrize)){
						prizeState = "已中奖";
						orderPrize = commonUtil.getBalanceFormat(orderPrize, 2);
					}else{
						prizeState = "正在算奖";
					}
				}

				present.setBeiShu(beiShu);
				present.setZhuShu(zhuShu);
				present.setBetCode(betCodeView);
				present.setWinCode(winCode);
				present.setOrderPrize(orderPrize);
				present.setPrizeState(prizeState);
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				String time = format.format(new Date(Long.parseLong(presentDetails.getString("createTime"))));
				time = time.substring(0,4)+"年"+time.substring(4,6)+"月"+time.substring(6)+"日<br/><br/>";
				present.setCreateTime(time);
				presents.add(present);
			}
			map.put("presents", presents);
			map.put("totalPage", totalPage);
			map.put("currentPageNo", currentPageNo);
		}
		return map;
	}
	/**
	 * 查询期信息
	 * 
	 * @param lotno
	 * @return
	 */
	public String getIssue(String lotno) {
		String url = lottery + "select/getIssue";
		String parameter = "lotno=" + lotno;
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("SelectAllUtil/getIssue()查询期信息返回内容：" + result);
		return result;
	}
	/**
	 * 查询追期期号
	 * 
	 * @param lotno
	 * @param batchcode
	 * @param num
	 * @return
	 */
	public String getAfterIssue(String lotno, String batchcode,
			String num) {
		String url = lottery + "select/getAfterIssue";
		String parameter = "lotno=" + lotno + "&batchcode=" + batchcode
				+ "&num=" + num;
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("SelectAllUtil/getAfterIssue查询追期期号返回：" + result);
		return result;

	}

	/**
	 * 查询当前期号
	 * 
	 * @param lotno
	 * @return
	 */
	public String selectLotteryBatchCode(String lotNo) {
		try{
		String result = getIssue(lotNo);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		if ("500".equals(errorCode)) {
			return "";
		}
		JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
		JSONObject idObject = jsonObjectValue.getJSONObject("id");
		String batchCode = idObject.getString("batchcode");
		return batchCode;
		}catch (Exception e) {
			return "";
		}

	}

//	/**
//	 * 投注页面显示投注时间
//	 * 
//	 * @param lotno
//	 * @return
//	 */
//	public String selectLotteryTermAndEndtime(String lotno) {
//		try{
//		String result = getIssue(lotno);
//		JSONObject jsonObject = JSONObject.fromObject(result);
//		String errorCode = jsonObject.getString("errorCode");
//		if (!"0".equals(errorCode)) {
//			return "";
//		}
//		JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
//		JSONObject idObject = jsonObjectValue.getJSONObject("id");
//		String batchcode = idObject.getString("batchcode");
//		long endtime = jsonObjectValue.getLong("endtime");
//		long nowtime = System.currentTimeMillis();
//		if ("T01007".equals(lotno) || "T01010".equals(lotno)
//				|| "T01012".equals(lotno)) {
//			if (endtime - nowtime > 0) {
//				long minute = (endtime - nowtime) / (60 * 1000);
//				long h = minute / 60;
//				long m = minute % 60;
//				long s = (endtime - nowtime) / 1000 - 60 * minute;
//				if (h == 0)
//					return "距" + batchcode + "期截止" + String.valueOf(m) + "分"
//							+ String.valueOf(s) + "秒";
//				else
//					return "距" + batchcode + "期截止" + String.valueOf(h) + "时"
//							+ String.valueOf(m) + "分" + String.valueOf(s) + "秒";
//			} else {
//				return "距" + batchcode + "期截止0分0秒";
//			}
//		} else {
//			batchcode = batchcode.substring(4);
//			if (endtime - nowtime > 0) {
//				long minute = (endtime - nowtime) / (60 * 1000);
//				long h = minute / 60;
//				long m = minute % 60;
//				return "距" + batchcode + "期截止" + String.valueOf(h) + "时"
//						+ String.valueOf(m) + "分";
//			} else {
//				return "距" + batchcode + "期截止" + "0时" + "0分";
//			}
//		}
//		}catch (Exception e) {
//			return "";
//		}
//	}
//	public String selectLotteryStopTime(String lotNo){
//		try{
//		String result = getIssue(lotNo);
//		JSONObject jsonObject = JSONObject.fromObject(result);
//		String errorCode = jsonObject.getString("errorCode");
//		if (!"0".equals(errorCode)) {
//			return "";
//		}
//		JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
//		long endTime = jsonObjectValue.getLong("endtime");
//		
//		return commonUtil.getDataFormat(endTime, "");
//		}catch (Exception e) {
//			return "";
//		}
//	}
	/**
	 * 根据期号查询期信息
	 * @param lotNo
	 * @param batchCode
	 * @return
	 */
	public String getIssueByLotNoAndBatchCode(String lotNo,String batchCode) {
		String url = lottery + "select/getTlotctrl?lotno=" + lotNo+ "&batchcode=" + batchCode;
		String result = commonUtil.setURLByGET(url);
		logger.info("SelectAllUtil/getIssueByLotNoAndBatchCode查询期信息返回内容：" + result);
		return result;
	}
	public Map<String,String> selectEndTime(String lotNo,String batchCode){
		try{
			Map<String, String> map = new HashMap<String, String>();
		String result = getIssueByLotNoAndBatchCode(lotNo, batchCode);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		if (!"0".equals(errorCode)) {
			return null;
		}
		JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
		long betEndTimeLong = jsonObjectValue.getLong("endbettime");
		String betEndTime = commonUtil.getDataFormat(betEndTimeLong, "");
		long heMaiEndTimeLong = jsonObjectValue.getLong("hemaiendtime");
		String heMaiEndTime = commonUtil.getDataFormat(heMaiEndTimeLong, "");

		map.put("betEndTime", betEndTime);
		map.put("heMaiEndTime", heMaiEndTime);
		return map;
		}catch (Exception e) {
			return null;
		}
	}
	public String selectLotteryRemainingTime(String lotNo,String batchCode){
		try{
			String result = getIssueByLotNoAndBatchCode(lotNo, batchCode);
			JSONObject jsonObject = JSONObject.fromObject(result);
			String errorCode = jsonObject.getString("errorCode");
			if (!"0".equals(errorCode)) {
				return "";
			}
			JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
			long endTime = jsonObjectValue.getLong("endbettime");
			long nowTime = System.currentTimeMillis();
			if (endTime - nowTime > 0){
				long minute = (endTime - nowTime) / (60 * 1000);
//				long h = minute / 60;
				long m = minute % 60;
				long s = (endTime - nowTime) / 1000 - 60 * minute;
				return m+":"+s;
			}else{
				return "0:0";
			}
		}catch (Exception e) {
			return "0:0";
		}
		
	}
	public String getInfo(String lotNo, String info) {
		logger.info("WinSelectAction/getWinInfo查询开奖详细信息根据彩种类型处理lotNo：" + lotNo
				+ "info:" + info);
		String lotWinInfo = "";

		if (lotNo != null && lotNo.trim().equals("F47104")) { // 双色球
			lotWinInfo = getDoubleBallWinInfo(info);
		} else if (lotNo != null && lotNo.trim().equals("F47103")) { // 福彩3D
			lotWinInfo = get3DWinInfo(info);
		} else if (lotNo != null && lotNo.trim().equals("F47102")) { // 七乐彩
			lotWinInfo = getQLCWinInfo(info);
		} else if (lotNo != null && lotNo.trim().equals("T01002")) { // 排列三
			lotWinInfo = getPLSWinInfo(info);
		} else if (lotNo != null && lotNo.trim().equals("T01001")) { // 大乐透
			lotWinInfo = getDLTWinInfo(info);
		} else if (lotNo != null && lotNo.trim().equals("T01011")) { // 排列五
			lotWinInfo = getPLWinInfo(info);
		} else if (lotNo != null && lotNo.trim().equals("T01009")) { // 七星彩
			lotWinInfo = getQXCWinInfo(info);
		} else if (lotNo != null && lotNo.trim().equals("T01013")) { // 22选5
			lotWinInfo = get22Select5WinInfo(info);
		}

		return lotWinInfo;

	}

	public String getDoubleBallWinInfo(String info) {
		// info =
		// "33068233400_33068233400_70506941200,1_2_1000000000;2_75_33750800;3_1106_300000;4_64091_20000;5_1306549_1000;6_9691094_500;7_0_0;8_0_0;9_0_0;10_0_0";
		logger.info("WinSelectAction/getDoubleBallWinInfo查询开奖详细信息：双色球");
		long sellTotalAmount = 0; // 本期销售总金额
		long prizePoolTotalAmount = 0; // 当前奖池总金额
		String lotWinInfo = "<tr><td>奖  项</td><td>中奖注数</td><td>单注奖金(元)</td></tr>";

		if (!"".equals(info) && !info.trim().equals("null")) { // info不为空
			String[] split1 = info.split(",");
			String[] split2 = split1[0].split("_");
			sellTotalAmount = Long.parseLong(split2[0]) / 100; // 本期销售总金额
			prizePoolTotalAmount = Long.parseLong(split2[2]) / 100; // 当前奖池总金额
			String[] split3 = split1[1].split(";");
			for (String string : split3) {
				String[] split4 = string.split("_");
				if (split4[0].equals("1")) { // 一等奖
					lotWinInfo += "<tr><td>一等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";

				} else if (split4[0].equals("2")) { // 二等奖
					lotWinInfo += "<tr><td>二等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";

				} else if (split4[0].equals("3")) { // 三等奖
					lotWinInfo += "<tr><td>三等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";

				} else if (split4[0].equals("4")) { // 四等奖
					lotWinInfo += "<tr><td>四等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";

				} else if (split4[0].equals("5")) { // 五等奖
					lotWinInfo += "<tr><td>五等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";

				} else if (split4[0].equals("6")) { // 六等奖
					lotWinInfo += "<tr><td>六等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";

				}
			}
		}
		String newInfo = "<div class='wap-pro'><p>本期销量：" + getAmtView(sellTotalAmount) + "</p></div><div class='wap-pro'><p>奖池滚存："
				+ getAmtView(prizePoolTotalAmount) + "</p></div>";
		return newInfo + lotWinInfo;
	}

	public String get3DWinInfo(String info) {
		// info =
		// "190519600_190519600_357669718,1_693_100000;2_0_32000;3_1924_16000;4_0_0;5_0_0;6_0_0;7_0_0;8_0_0;9_0_0;10_0_0";
		logger.info("WinSelectAction/get3DWinInfo查询开奖详细信息：福彩3D");

		long sellTotalAmount = 0; // 本期销售总金额
		long prizePoolTotalAmount = 0; // 当前奖池总金额
		String lotWinInfo = "<tr><td>奖  项</td><td>中奖注数</td><td>单注奖金(元)</td></tr>";

		if (!"".equals(info) && !info.trim().equals("null")) { // info不为空
			String[] split1 = info.split(",");
			String[] split2 = split1[0].split("_");
			sellTotalAmount = Long.parseLong(split2[0]) / 100; // 本期销售总金额
			prizePoolTotalAmount = Long.parseLong(split2[2]) / 100; // 当前奖池总金额

			String[] split3 = split1[1].split(";");
			for (String string : split3) {
				String[] split4 = string.split("_");
				if (split4[0].equals("1")) { // 一等奖
					lotWinInfo += "<tr><td>一等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("2")) { // 二等奖
					lotWinInfo += "<tr><td>二等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("3")) { // 三等奖
					lotWinInfo += "<tr><td>三等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				}
			}
		}
		String newInfo = "<div class='wap-pro'><p>本期销量：" + getAmtView(sellTotalAmount) + "</p></div><div class='wap-pro'><p>奖池滚存："
				+ getAmtView(prizePoolTotalAmount) + "</p></div>";
		return newInfo + lotWinInfo;

	}

	public String getQLCWinInfo(String info) {
		// info =
		// "1006935000_1006935000_244471200,1_0_0;2_14_2494600;3_292_239200;4_1105_20000;5_9250_5000;6_21007_1000;7_109593_500;8_0_0;9_0_0;10_0_0";
		logger.info("WinSelectAction/get3DWinInfo查询开奖详细信息：七乐彩");

		long sellTotalAmount = 0; // 本期销售总金额
		long prizePoolTotalAmount = 0; // 当前奖池总金额
		String lotWinInfo = "<tr><td>奖  项</td><td>中奖注数</td><td>单注奖金(元)</td></tr>";

		if (!"".equals(info) && !info.trim().equals("null")) { // info不为空
			String[] split1 = info.split(",");
			String[] split2 = split1[0].split("_");
			sellTotalAmount = Long.parseLong(split2[0]) / 100; // 本期销售总金额
			prizePoolTotalAmount = Long.parseLong(split2[2]) / 100; // 当前奖池总金额

			String[] split3 = split1[1].split(";");
			for (String string : split3) {
				String[] split4 = string.split("_");
				if (split4[0].equals("1")) { // 一等奖
					lotWinInfo += "<tr><td>一等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("2")) { // 二等奖
					lotWinInfo += "<tr><td>二等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("3")) { // 三等奖
					lotWinInfo += "<tr><td>三等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("4")) { // 四等奖
					lotWinInfo += "<tr><td>四等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("5")) { // 五等奖
					lotWinInfo += "<tr><td>五等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("6")) { // 六等奖
					lotWinInfo += "<tr><td>六等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("7")) { // 七等奖
					lotWinInfo += "<tr><td>七等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				}
			}
		}
		String newInfo = "<div class='wap-pro'><p>本期销量：" + getAmtView(sellTotalAmount) + "</p></div><div class='wap-pro'><p>奖池滚存："
				+ getAmtView(prizePoolTotalAmount) + "</p></div>";
		return newInfo + lotWinInfo;

	}

	public String getPLSWinInfo(String info) {
		// info =
		// "2116134200_1021780000_646849000,1_8013_100000;2_6890_32000;3_0_16000";
		logger.info("WinSelectAction/get3DWinInfo查询开奖详细信息：排列三");

		long sellTotalAmount = 0; // 本期销售总金额
		long prizePoolTotalAmount = 0; // 当前奖池总金额
		String lotWinInfo = "<tr><td>奖  项</td><td>中奖注数</td><td>单注奖金(元)</td></tr>";

		if (!"".equals(info) && !info.trim().equals("null")) { // info不为空
			String[] split1 = info.split(",");
			String[] split2 = split1[0].split("_");
			sellTotalAmount = Long.parseLong(split2[0]) / 100; // 本期销售总金额
			prizePoolTotalAmount = Long.parseLong(split2[2]) / 100; // 当前奖池总金额

			String[] split3 = split1[1].split(";");
			for (String string : split3) {
				String[] split4 = string.split("_");
				if (split4[0].equals("1")) { // 一等奖
					lotWinInfo += "<tr><td>一等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("2")) { // 二等奖
					lotWinInfo += "<tr><td>二等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("3")) { // 三等奖
					lotWinInfo += "<tr><td>三等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				}
			}
		}
		String newInfo = "<div class='wap-pro'><p>本期销量：" + getAmtView(sellTotalAmount) + "</p></div><div class='wap-pro'><p>奖池滚存："
				+ getAmtView(prizePoolTotalAmount) + "</p></div>";
		return newInfo + lotWinInfo;

	}

	public String getDLTWinInfo(String info) {
		// info =
		// "8703128900_2455151900_29407441000,1_0_0;2_26_16483000;3_138_806700;4_127_300000;5_4607_60000;6_19362_10000;7_184981_1000;8_2058096_500;"
		// +"11_0_0;12_6_9889800;13_22_484000;14_25_150000;15_1447_30000;16_5670_5000;17_54731_500;18_3373_6000";
		logger.info("WinSelectAction/get3DWinInfo查询开奖详细信息：大乐透");

		long sellTotalAmount = 0; // 本期销售总金额
		long prizePoolTotalAmount = 0; // 当前奖池总金额
		String lotWinInfo = "<tr><td>奖  项</td><td>中奖注数</td><td>单注奖金(元)</td></tr>";

		if (!"".equals(info) && !info.trim().equals("null")) { // info不为空
			String[] split1 = info.split(",");
			String[] split2 = split1[0].split("_");
			sellTotalAmount = Long.parseLong(split2[0]) / 100; // 本期销售总金额
			prizePoolTotalAmount = Long.parseLong(split2[2]) / 100; // 当前奖池总金额

			String[] split3 = split1[1].split(";");
			for (String string : split3) {
				String[] split4 = string.split("_");
				if (split4[0].equals("1")) { // 一等奖
					lotWinInfo += "<tr><td>一等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("2")) { // 二等奖
					lotWinInfo += "<tr><td>二等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("3")) { // 三等奖
					lotWinInfo += "<tr><td>三等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("4")) { // 四等奖
					lotWinInfo += "<tr><td>四等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("5")) { // 五等奖
					lotWinInfo += "<tr><td>五等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("6")) { // 六等奖
					lotWinInfo += "<tr><td>六等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("7")) { // 七等奖
					lotWinInfo += "<tr><td>七等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("8")) { // 八等奖
					lotWinInfo += "<tr><td>八等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("11")) { // 一等奖追加
					lotWinInfo += "<tr><td>一等奖追加</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("12")) { // 二等奖追加
					lotWinInfo += "<tr><td>二等奖追加</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("13")) { // 三等奖追加
					lotWinInfo += "<tr><td>三等奖追加</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("14")) { // 四等奖追加
					lotWinInfo += "<tr><td>四等奖追加</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("15")) { // 五等奖追加
					lotWinInfo += "<tr><td>五等奖追加</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("16")) { // 六等奖追加
					lotWinInfo += "<tr><td>六等奖追加</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("17")) { // 七等奖追加
					lotWinInfo += "<tr><td>七等奖追加</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("18")) { // 十二选二
					lotWinInfo += "<tr><td>十二选二</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				}
			}
		}
		String newInfo = "<div class='wap-pro'><p>本期销量：" + getAmtView(sellTotalAmount) + "</p></div><div class='wap-pro'><p>奖池滚存："
				+ getAmtView(prizePoolTotalAmount) + "</p></div>";
		return newInfo + lotWinInfo;
	}

	public String getPLWinInfo(String info) {
		// info = "856048200_860000000_616898300,1_86_10000000";
		logger.info("WinSelectAction/get3DWinInfo查询开奖详细信息：排列五");

		long sellTotalAmount = 0; // 本期销售总金额
		long prizePoolTotalAmount = 0; // 当前奖池总金额
		String lotWinInfo = "<tr><td>奖  项</td><td>中奖注数</td><td>单注奖金(元)</td></tr>";

		if (!"".equals(info) && !info.trim().equals("null")) { // info不为空
			String[] split1 = info.split(",");
			String[] split2 = split1[0].split("_");
			sellTotalAmount = Long.parseLong(split2[0]) / 100; // 本期销售总金额
			prizePoolTotalAmount = Long.parseLong(split2[2]) / 100; // 当前奖池总金额

			String[] split3 = split1[1].split(";");
			for (String string : split3) {
				String[] split4 = string.split("_");
				if (split4[0].equals("1")) { // 一等奖
					lotWinInfo += "<tr><td>一等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				}
			}
		}
		String newInfo = "<div class='wap-pro'><p>本期销量：" + getAmtView(sellTotalAmount) + "</p></div><div class='wap-pro'><p>奖池滚存："
				+ getAmtView(prizePoolTotalAmount) + "</p></div>";
		return newInfo + lotWinInfo;
	}

	public String getQXCWinInfo(String info) {
		// info =
		// "1660922600_2390167500_500000000,1_5_387797900;2_12_3358000;3_172_180000;4_2584_30000;5_39282_2000;6_447676_500";
		logger.info("WinSelectAction/get3DWinInfo查询开奖详细信息：七星彩");

		long sellTotalAmount = 0; // 本期销售总金额
		long prizePoolTotalAmount = 0; // 当前奖池总金额
		String lotWinInfo = "<tr><td>奖  项</td><td>中奖注数</td><td>单注奖金(元)</td></tr>";

		if (!"".equals(info) && !info.trim().equals("null")) { // info不为空
			String[] split1 = info.split(",");
			String[] split2 = split1[0].split("_");
			sellTotalAmount = Long.parseLong(split2[0]) / 100; // 本期销售总金额
			prizePoolTotalAmount = Long.parseLong(split2[2]) / 100; // 当前奖池总金额

			String[] split3 = split1[1].split(";");
			for (String string : split3) {
				String[] split4 = string.split("_");
				if (split4[0].equals("1")) { // 一等奖
					lotWinInfo += "<tr><td>一等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("2")) { // 二等奖
					lotWinInfo += "<tr><td>二等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("3")) { // 三等奖
					lotWinInfo += "<tr><td>三等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("4")) { // 四等奖
					lotWinInfo += "<tr><td>四等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("5")) { // 五等奖
					lotWinInfo += "<tr><td>五等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("6")) { // 六等奖
					lotWinInfo += "<tr><td>六等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				}
			}
		}
		String newInfo = "<div class='wap-pro'><p>本期销量：" + getAmtView(sellTotalAmount) + "</p></div><div class='wap-pro'><p>奖池滚存："
				+ getAmtView(prizePoolTotalAmount) + "</p></div>";
		return newInfo + lotWinInfo;
	}

	public String get22Select5WinInfo(String info) {
		// info = "184355400_90330100_0,1_43_1054200;2_3536_5000;3_54639_500";
		logger.info("WinSelectAction/get3DWinInfo查询开奖详细信息：22选5");

		long sellTotalAmount = 0; // 本期销售总金额
		long prizePoolTotalAmount = 0; // 当前奖池总金额
		String lotWinInfo = "<tr><td>奖  项</td><td>中奖注数</td><td>单注奖金(元)</td></tr>";

		if (!"".equals(info) && !info.trim().equals("null")) { // info不为空
			String[] split1 = info.split(",");
			String[] split2 = split1[0].split("_");
			sellTotalAmount = Long.parseLong(split2[0]) / 100; // 本期销售总金额
			prizePoolTotalAmount = Long.parseLong(split2[2]) / 100; // 当前奖池总金额

			String[] split3 = split1[1].split(";");
			for (String string : split3) {
				String[] split4 = string.split("_");
				if (split4[0].equals("1")) { // 一等奖
					lotWinInfo += "<tr><td>一等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("2")) { // 二等奖
					lotWinInfo += "<tr><td>二等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				} else if (split4[0].equals("3")) { // 三等奖
					lotWinInfo += "<tr><td>三等奖</td><td>" + split4[1] + "</td><td>"
							+ getAmtView(Long.parseLong(split4[2]) / 100)
							+ "</td></tr>";
				}
			}
		}
		String newInfo = "<div class='wap-pro'><p>本期销量：" + getAmtView(sellTotalAmount) + "</p></div><div class='wap-pro'><p>奖池滚存："
				+ getAmtView(prizePoolTotalAmount) + "</p></div>";
		return newInfo + lotWinInfo;
	}

	/**
	 * 123,456,78
	 * 
	 * @param str
	 * @return
	 */
	public String getAmtView(long str) {
		NumberFormat usFormat = NumberFormat.getIntegerInstance(Locale.US);
		logger.info("str:" + str);
		return usFormat.format(str);
	}

	/**
	 * 中奖历史排行调用后台接口
	 * 
	 * @param time
	 * @param type
	 * @return
	 */
	public String getUserRankingHistory(String time, int type) {
		String url = ranking + "selectUserRankingHistory";
		String parameter = "time=" + time + "&type=" + type;
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("SelectAllUtil/getUserRankingHistory查询用户中奖历史排行返回result："
				+ result);
		return result;
	}

	/**
	 * 中奖历史排行处理后台返回结果，得到中奖历史排行列表
	 * 
	 * @param time
	 * @param type
	 * @return
	 */
	public List<Ranking> selectUserRankingHistory(String time, int type) {
		logger.info("SelectAllUtil/selectUserRankingHistory查询中奖历史排行参数time:"
				+ time + ",type:" + type);
		List<Ranking> userRankingHistoryList = new ArrayList<Ranking>();
		String result = getUserRankingHistory(time, type);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		if ("0".equals(errorCode)) {
			String str = jsonObject.getString("value");
			JSONArray jsonArray = JSONArray.fromObject(str);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject userRankingHistory = jsonArray.getJSONObject(i);
				String userName = userRankingHistory.getString("username");
				String nickName = userRankingHistory.getString("nickname");
				String mobileId = userRankingHistory.getString("mobileId");
				String prizeAmt = userRankingHistory.getString("prizeAmt");
				nickName = getNickName(userName, nickName, mobileId);
				prizeAmt = commonUtil.getBalanceFormat(prizeAmt, 2);
				Ranking ranking = new Ranking();
				ranking.setNickName(nickName);
				ranking.setPrizeAmt(prizeAmt);
				userRankingHistoryList.add(ranking);
			}
		}
		return userRankingHistoryList;
	}

	/**
	 * 中奖历史排行得到页面显示用户名称(昵称，用户名，手机号)
	 * 
	 * @param userName
	 * @param nickName
	 * @param mobileId
	 * @return
	 */
	public String getNickName(String userName, String nickName,
			String mobileId) {
		if (nickName != null && !"".equals(nickName)
				&& !"null".equals(nickName)) {
			return nickName;
		} else if (userName != null && !"".equals(userName)
				&& !"null".equals(userName)) {
			return getHideName(userName);
		} else if (mobileId != null && !"".equals(mobileId)
				&& !"null".equals(mobileId)) {
			return getHideName(mobileId);
		}
		return "";
	}

	/**
	 * 中奖历史排行手机号格式隐藏中间四位
	 * 
	 * @param name
	 * @return
	 */
	public String getHideName(String name) {
		String hideName = "";
		Pattern p = Pattern.compile("^(13|14|15|18)\\d{9}$");
		Matcher m = p.matcher(name);
		if (m.matches() && name.length() == 11) {
			hideName = name.substring(0, 3) + "****" + name.substring(7, 11);
		} else {
			hideName = name;
		}
		return hideName;
	}

	/**
	 * 查看积分
	 * 
	 * @param userNo
	 * @return
	 */
	public String findScoreByUserno(String userNo) {
		String url = scorecenter + "findScoreByUserno";
		String parameter = "userno=" + userNo;
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("SelectAllUtil/findScoreByUserno查询积分返回result：" + result);
		return result;
	}

	/**
	 * 查询用户积分
	 * 
	 * @param userNo
	 * @return
	 */
	public Score selectScore(String userNo) {
		Score score = new Score();
		String result = findScoreByUserno(userNo);
		JSONObject resultObject = JSONObject.fromObject(result);
		String errorCode = resultObject.getString("errorCode");
		if ("0".equals(errorCode)) {
			JSONObject jsonObjectValue = resultObject.getJSONObject("value");
			String userScore = jsonObjectValue.getString("score");
			score.setScore(userScore);
		}
		return score;
	}

	/**
	 * 查询积分明细
	 * 
	 * @param userNo
	 * @param startLine
	 * @param endLine
	 * @return
	 */
	public String findScoreDetailByUserNo(String userNo,
			String startLine, String endLine) {
		String url = scorecenter + "findScoreDetailByUserno";
		String parameter = "userno=" + userNo + "&startLine=" + startLine
				+ "&endLine=" + endLine;
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("SelectAllUtil/findScoreDetailByUserNo查询积分明细返回result："
				+ result);
		return result;
	}

	/**
	 * 查询积分明细
	 * 
	 * @param userNo
	 * @param startLine
	 * @param endLine
	 * @return
	 */
	public Map<String, Object> selectScoreDetail(String userNo,
			String startLine, String endLine) {
		Map<String, Object> map = new HashMap<String, Object>();
		String result = findScoreDetailByUserNo(userNo, startLine, endLine);
		JSONObject resultObject = JSONObject.fromObject(result);
		String errorCode = resultObject.getString("errorCode");
		if ("0".equals(errorCode)) {
			JSONObject jsonObjectValue = resultObject.getJSONObject("value");
			JSONArray jsonArray = jsonObjectValue.getJSONArray("list");
			String totalPage = jsonObjectValue.getString("totalPage");
			// String maxResult = jsonObjectValue.getString("maxResult");
			String currentPageNo = jsonObjectValue.getString("currentPageNo");
			List<Score> scores = new ArrayList<Score>();
			Score score = null;
			for (int i = 0; i < jsonArray.size() && i < 10; i++) {
				score = new Score();
				JSONObject object = jsonArray.getJSONObject(i);
				score.setId((i + 1 + (Integer.parseInt(currentPageNo) - 1) * 10)
						+ "");
				String createTime = object.getString("createTime");
				if (createTime != null && !"".equals(createTime)
						&& !"null".equals(createTime)) {
					createTime = commonUtil.getDataFormat(
							Long.parseLong(createTime), "");
				} else {
					continue;
				}
				score.setCreateTime(createTime);
				score.setScore(object.getString("score"));
				String scoreTypeName = getScoreTypeName(object.getString("scoreType"));
				score.setScoreType(object.getString("scoreType"));
				score.setScoreTypeName(scoreTypeName);
				scores.add(score);
			}
			map.put("scores", scores);
			map.put("totalPage", totalPage);
			map.put("currentPageNo", currentPageNo);
		}
		return map;
	}

	public String getScoreTypeName(String scoreType) {
		if ("1".equals(scoreType)) {
			scoreType = "注册并完善信息";
		} else if ("2".equals(scoreType)) {
			scoreType = "普通投注";
		} else if ("3".equals(scoreType)) {
			scoreType = "发起追号";
		} else if ("4".equals(scoreType)) {
			scoreType = "发起合买";
		} else if ("5".equals(scoreType)) {
			scoreType = "参与合买";
		} else if ("6".equals(scoreType)) {
			scoreType = "用户充值";
		} else if ("7".equals(scoreType)) {
			scoreType = "留言建议";
		} else if ("8".equals(scoreType)) {
			scoreType = "用户登录";
		} else if ("99".equals(scoreType)) {
			scoreType = "赠送积分";
		} else if ("-1".equals(scoreType)) {
			scoreType = "兑换彩金";
		}
		return scoreType;
	}
	/**
	 * 查询合买列表
	 * @param parameterMap
	 * @return
	 */
	public String getCaseLots(Map<String, String> parameterMap){
		String url = lottery + "select/selectCaseLots";
		String startLine = parameterMap.get("startLine")==null ? "0" : parameterMap.get("startLine");
		String endLine = parameterMap.get("endLine")==null ? "10" : parameterMap.get("endLine");
		String orderBy = "";
			try {
				if("totalAmt".equals(parameterMap.get("orderBy"))) //按总金额
					orderBy = "totalAmt";
				if("buyAmt".equals(parameterMap.get("orderBy")))//按认购金额
					orderBy = URLEncoder.encode("buyAmtByStarter+buyAmtByFollower","iso-8859-1");
				if("progress".equals(parameterMap.get("orderBy")))//按进度
					orderBy = URLEncoder.encode("(buyAmtByStarter+buyAmtByFollower+safeAmt)/totalAmt",
							"iso-8859-1");
				if("participantCount".equals(parameterMap.get("orderBy"))) //按人气(参与人数)
					orderBy = "participantCount";
				if("achievement".equals(parameterMap.get("orderBy"))) //按战绩
					orderBy = URLEncoder.encode("effectiveAchievement+ineffectiveAchievement","iso-8859-1");
			} catch (UnsupportedEncodingException e) {
				logger.error("SelectAllUtil/getCaseLots查询合买列表URLEncoder.encode异常");
				e.printStackTrace();
			}
		JSONObject condition = new JSONObject();
		condition.put("EQS_lotno", parameterMap.get("lotNo"));
		condition.put("EQS_batchcode", parameterMap.get("batchCode"));
		String parameter ="state=1&state=2&search="+parameterMap.get("search")
				+"&sortState="+parameterMap.get("sortState")//0置顶+普通 ;1置顶
				+"&startLine="+startLine+"&endLine="+endLine+"&orderBy="+orderBy
				+"&orderDir="+parameterMap.get("orderDir")+"&condition="+condition.toString();
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("SelectAllUtil/getCaseLots查询合买列表返回result："
				+ result);
		return result;

	}
	/**
	 * 查询合买列表
	 * @param parameterMap
	 * @return
	 */
	public Map<String,Object> selectCaseLots(Map<String, String> parameterMap){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		List<CaseLot> caseLotList= new ArrayList<CaseLot>();
		String result = getCaseLots(parameterMap);
		JSONObject resultJsonObject = JSONObject.fromObject(result);
		String errorCode = resultJsonObject.getString("errorCode");
		if("0".equals(errorCode)){
			JSONObject valueJsonObject = resultJsonObject.getJSONObject("value");
			String totalPage = valueJsonObject.getString("totalPage");
			String currentPageNo = valueJsonObject.getString("currentPageNo");
			JSONArray jsonArray = valueJsonObject.getJSONArray("list");
			if(jsonArray!=null&&jsonArray.size()>0){
				for (int i = 0; i < jsonArray.size(); i++) {
					CaseLot caseLot = new CaseLot();
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					String nickName = ""; // 昵称
					String mobileId = ""; // 手机号码
					String userName = "";// 用户名
					String starter  = "";
					if(jsonObject!=null){
						JSONObject starterObject = jsonObject.getJSONObject("starter");
						if(starterObject!=null){
							nickName = starterObject.getString("nickname"); // 昵称
							userName = starterObject.getString("userName"); // 用户名
							mobileId = starterObject.getString("mobileid"); // 手机号
							starter = commonUtil.getViewName(nickName, userName, mobileId);
						}
						JSONObject caseLotObject = jsonObject.getJSONObject("caseLot");
						String id = caseLotObject.getString("id"); // 方案编号
						String lotNo = caseLotObject.getString("lotno"); // 用户编号
						String totalAmt = commonUtil.getBalanceFormat(caseLotObject.getString("totalAmt"), 2)  ; // 方案总金额
						String safeAmt = commonUtil.getBalanceFormat(caseLotObject.getString("safeAmt"),2); // 保底金额
//						String buyAmtByStarter = commonUtil.getBalanceFormat(caseLotObject
//								.getString("buyAmtByStarter"),2); // 发起人认购金额
//						String buyAmtByFollower = commonUtil.getBalanceFormat(caseLotObject
//								.getString("buyAmtByFollower"),2); // 跟随者的认购金额
						String buyAmt = commonUtil.getBalanceFormat(caseLotObject
								.getLong("buyAmtByStarter") + caseLotObject
								.getLong("buyAmtByFollower")+"",2); // 总的认购金额
						JSONObject achievementObject = jsonObject
								.getJSONObject("achievement");//战绩
//						String effectiveAchievement = achievementObject.getString("effectiveAchievement");
//						String ineffectiveAchievement = achievementObject.getString("ineffectiveAchievement");
//						String achievement = getAchievement(effectiveAchievement, ineffectiveAchievement);
						
						JSONObject displayIcon = (achievementObject != null && achievementObject
								.has("displayIcon")) ? (JSONObject) achievementObject
								.get("displayIcon") : new JSONObject();
						String achievement = getAchievementByDisplayIcon(displayIcon);
						BigDecimal caseBuyAfterAmt = (new BigDecimal(buyAmt)).multiply(new BigDecimal(100)).divide(new BigDecimal(totalAmt), 2, BigDecimal.ROUND_HALF_UP);
					    BigDecimal caseBaodiAmt = new BigDecimal(safeAmt).multiply(new BigDecimal(100)).divide(new BigDecimal(totalAmt), 2, BigDecimal.ROUND_HALF_UP);
					    String progress = caseBuyAfterAmt.longValue()+"%+"+caseBaodiAmt.longValue()+"%(保)";
					    String lotName = lotteryUtil.getLotNameByLotNo(lotNo);
					    String sortState = caseLotObject.getString("sortState");//为8或9为置顶的
					    caseLot.setCaseLotId(id);
						caseLot.setTotalAmt(totalAmt+"");
						caseLot.setSafeAmt(safeAmt+"");
						caseLot.setBuyAmt(buyAmt+"");
						caseLot.setLotNo(lotNo);
						caseLot.setLotName(lotName);
						caseLot.setProgress(progress);
						caseLot.setSortState(sortState);
						caseLot.setAchievement(achievement);
						caseLot.setStarter(starter);
						caseLotList.add(caseLot);
					}
				}
				
			}
			resultMap.put("caseLotList", caseLotList);
			resultMap.put("totalPage", totalPage);
			resultMap.put("currentPageNo", currentPageNo);

		}
		return resultMap;
	}
	/**
	 * 查询合买订单详细
	 * @param caseLotId
	 * @param userNo
	 * @return
	 */
	public String getCaseLotDetail(String caseLotId,String userNo){
		String url = lottery + "select/selectCaseLotDetail";
		String parameter = "userno=" + userNo + "&caselotid=" + caseLotId;
		String result = commonUtil.setURLByGET(url+"?"+parameter);
		logger.info("SelectAllUtil/getCaseLotDetail查询合买订单详情返回result："
				+ result);
		return result;
	}
	/**
	 * 查询合买订单详细
	 * @param caseLotId
	 * @param userNo
	 * @return
	 */
	public CaseLot selectCaseLotDetail(String caseLotId,String userNo){
		CaseLot caseLot = new CaseLot();
		String result = getCaseLotDetail(caseLotId, userNo);
		JSONObject resultJsonObject = JSONObject.fromObject(result);
		String errorCode = resultJsonObject.getString("errorCode");
		if("0".equals(errorCode)){
			JSONObject valueJsonObject = resultJsonObject.getJSONObject("value");
			JSONObject starterJsonObject = valueJsonObject.getJSONObject("starter");
			JSONObject caseLotJsonObject = valueJsonObject.getJSONObject("caseLot");
			JSONObject torderJsonObject = valueJsonObject.getJSONObject("torder");
			JSONObject achievementObject = valueJsonObject.getJSONObject("achievement");
			String lotNo = torderJsonObject.getString("lotno");
			String lotName = lotteryUtil.getLotNameByLotNo(lotNo);
			String nickName = starterJsonObject.getString("nickname"); // 昵称
			String userName = starterJsonObject.getString("userName"); // 用户名
			String mobileId = starterJsonObject.getString("mobileid"); // 手机号
			String starterUserNo = starterJsonObject.getString("userno");//发起人userno
			String starter = commonUtil.getViewName(nickName, userName, mobileId);
//			String id = caseLotJsonObject.getString("id"); // 方案编号
			String minAmt = commonUtil.getBalanceFormat(caseLotJsonObject.getString("minAmt"), 2);
			String totalAmt = commonUtil.getBalanceFormat(caseLotJsonObject.getString("totalAmt"), 2)  ; // 方案总金额
			String safeAmt = commonUtil.getBalanceFormat(caseLotJsonObject.getString("safeAmt"),2); // 保底金额
			String buyAmt = commonUtil.getBalanceFormat((caseLotJsonObject
					.getLong("buyAmtByStarter") + caseLotJsonObject
					.getLong("buyAmtByFollower"))+"",2); // 总的认购金额
			String buyAmtByStarter = commonUtil.getBalanceFormat(caseLotJsonObject
					.getString("buyAmtByStarter"), 2);//发起人认购金额
			JSONObject displayIcon = (achievementObject != null && achievementObject
					.has("displayIcon")) ? (JSONObject) achievementObject
					.get("displayIcon") : new JSONObject();
			String achievement = getAchievementByDisplayIcon(displayIcon);
			BigDecimal caseBuyAfterAmt = (new BigDecimal(buyAmt)).multiply(new BigDecimal(100)).divide(new BigDecimal(totalAmt), 2, BigDecimal.ROUND_HALF_UP);
		    BigDecimal caseBaodiAmt = new BigDecimal(safeAmt).multiply(new BigDecimal(100)).divide(new BigDecimal(totalAmt), 2, BigDecimal.ROUND_HALF_UP);
		    String progress = "<span>"+caseBuyAfterAmt.longValue()+"%</span>+<span>"+caseBaodiAmt.longValue()+"%</span><span>(保)</span>";
		    String commisionRatio = caseLotJsonObject.getString("commisionRatio")+"%";//发起人提成
			String description = caseLotJsonObject.getString("description");
			if("".equals(description)||description==null||"null".equals(description)){
				description = "";
			}else{
				//description = description;
			}
			String winCode = "";
			if(lotNo.contains("J0000")){
				caseLot.setBatchCode("");
			}else{
				caseLot.setBatchCode(torderJsonObject.getString("batchcode"));
				WinInfo winInfo = selectTwininfo(lotNo, torderJsonObject.getString("batchcode"));
				winCode = winInfo.getWinCode();
			}
			String batchCode = torderJsonObject.getString("batchcode");
			String betCode = torderJsonObject.getString("betcode");
			String orderInfo = torderJsonObject.getString("orderinfo");
			String betCodeView = "";
			String orderId = torderJsonObject.getString("id");
			if(lotNo.indexOf("J")>-1&&lotNo.startsWith("J")){
				betCodeView = jingCaiOrderInfoAnalysisUtil.orderInfoAnalysis(lotNo, orderId, orderInfo);
			}else{
				if(!"".equals(orderInfo)&&orderInfo!=null){
					betCodeView = orderInfoAnalysisUtil.orderInfoAnalysis(lotNo, orderInfo);
				}else{
					betCodeView = betCodeAnalysisUtil.betCodeAnalysis(lotNo, betCode);
				}
			}
			String visibility = caseLotJsonObject.getString("visibility");
			if("1".equals(visibility)){
				visibility = "保密!<br/>";
			}else if("0".equals(visibility)){
				visibility = "对所有人公开!<br/>";
			}else if("2".equals(visibility)){
				visibility = "对所有人截止后公开!<br/>";
			}else if("3".equals(visibility)){
				visibility = "对跟单者立即公开!<br/>";
			}else if("4".equals(visibility)){
				visibility = "对跟单者截止后公开!<br/>";
			}
			String displayTlots = valueJsonObject.getString("displayTlots");
			System.out.println("displayTlots===="+displayTlots);
			BigDecimal betAmt = new BigDecimal(totalAmt).subtract(new BigDecimal(buyAmt));//可认购金额
			BigDecimal baodiAmt = betAmt.subtract(new BigDecimal(safeAmt));//可保底金额
			if(baodiAmt.intValue()<0){
				baodiAmt=new BigDecimal("0");
			}
			//displayState=1的时候允许撤单撤资
			String orderState = torderJsonObject.getString("orderstate");
			String prizeState = torderJsonObject.getString("prizestate");
			String orderPrize = torderJsonObject.getString("orderprizeamt");//税后奖金
			if("0".equals(prizeState)){
				prizeState = "未开奖";
			}else if("1".equals(prizeState)){
				prizeState = "等待开奖";
			}else if("2".equals(prizeState)){
				prizeState = "等待开奖";
			}else if("3".equals(prizeState)){
				prizeState = "未中奖";
			}else if("4".equals(prizeState)){
				if(!"null".equals(orderPrize)&&!"".equals(orderPrize)&&orderPrize!=null&&!"0".equals(orderPrize)){
					prizeState = "已中奖";
					orderPrize = commonUtil.getBalanceFormat(orderPrize, 2);
				}else{
					prizeState = "正在算奖";
				}
			}else if("5".equals(prizeState)){
				if(!"null".equals(orderPrize)&&!"".equals(orderPrize)&&orderPrize!=null&&!"0".equals(orderPrize)){
					prizeState = "已中奖";
					orderPrize = commonUtil.getBalanceFormat(orderPrize, 2);
				}else{
					prizeState = "正在算奖";
				}
			}
			String beiShu = torderJsonObject.getString("lotmulti");
			String displayState = caseLotJsonObject.getString("displayState");
			String displayStateMemo = caseLotJsonObject.getString("displayStateMemo");
			Map<String, String> map = selectEndTime(lotNo, batchCode);
			String stopTime = "";
			if(map!=null){
				stopTime = map.get("heMaiEndTime");
			}
			String winBigAmt = commonUtil.getBalanceFormat(caseLotJsonObject.getString("winBigAmt"), 2);
			if(userNo!=null&&!"".equals(userNo)){
				Map<String, String> caseLotUserBuyMap = selectCaseLotUserBuy(caseLotId, userNo);
				String userSafeAmt = caseLotUserBuyMap.get("userSafeAmt") ;
				String userTotalAmt = caseLotUserBuyMap.get("userTotalAmt") ;
				String userPrizeAmt = caseLotUserBuyMap.get("userPrizeAmt");
				caseLot.setUserSafeAmt(userSafeAmt);
				caseLot.setUserTotalAmt(userTotalAmt);
				caseLot.setUserPrizeAmt(userPrizeAmt);
				if(starterUserNo.equals(userNo)){
					if("1".equals(displayState)&&caseBuyAfterAmt.longValue()+caseBaodiAmt.longValue()<50&&!"4".equals(orderState)&&!"3".equals(orderState)){
						String starterCancel = "true";
						caseLot.setStarterCancel(starterCancel);
					}
				}else{
					if("1".equals(displayState)&&caseBuyAfterAmt.longValue()+caseBaodiAmt.longValue()<20&&!"4".equals(orderState)&&!"3".equals(orderState)&&Double.parseDouble(userTotalAmt)>0){
						String buyCancel = "true";
						caseLot.setBuyCancel(buyCancel);
					}
				}
			}
			
			
			caseLot.setPrizeState(prizeState);
			caseLot.setWinCode(winCode);
			caseLot.setWinBigAmt(winBigAmt);
			caseLot.setDisplayStateMemo(displayStateMemo);
			caseLot.setStopTime(stopTime);
			caseLot.setBeiShu(beiShu);
			caseLot.setLotNo(lotNo);
			caseLot.setLotName(lotName);
			caseLot.setStarter(starter);
//			caseLot.setBatchCode(batchCode);
			caseLot.setCaseLotId(caseLotId);
			caseLot.setTotalAmt(totalAmt);
			caseLot.setMinAmt(minAmt);
			caseLot.setSafeAmt(safeAmt);
			caseLot.setProgress(progress);
			caseLot.setBuyAmt(buyAmt);
			caseLot.setBuyAmtByStarter(buyAmtByStarter+"");
			caseLot.setCommisionRatio(commisionRatio);
			caseLot.setDescription(description);
			caseLot.setDisplayState(displayState);
			caseLot.setBetCode(betCodeView);
			caseLot.setVisibility(visibility);
			caseLot.setDisplayTlots(displayTlots);
			caseLot.setBetAmt(betAmt+"");
			caseLot.setBaodiAmt(baodiAmt+"");
			caseLot.setAchievement(achievement);

		}
		return caseLot;
	}
	/**
	 * 查询合买用户参与列表只包含参与人信息和参与金额等情况。精简掉caselot,torder,starter等
	 * @param caselotid
	 * @param startLine
	 * @param endLine
	 * @return
	 */
	public String getCaseLotBuysSimplify(String caseLotId,String startLine,String endLine,String orderBy,String orderDir){
		String url = lottery + "select/selectCaseLotBuysSimplify";
		String parameter = "caselotid=" + caseLotId+"&startLine="+startLine+"&endLine="+endLine
					+"&orderBy="+orderBy+"&orderDir="+orderDir;
		String result = commonUtil.setURLByGET(url+"?"+parameter);
		logger.info("SelectAllUtil/getCaseLotBuysSimplify查询合买用户参与列表返回result："
				+ result);
		return result;
	}
	/**
	 * 查询合买用户参与列表
	 * @param caseLotId
	 * @param startLine
	 * @param endLine
	 * @param orderBy
	 * @param orderDir
	 * @return
	 */
	public Map<String, Object> selectCaseLotBuysSimplify(String caseLotId,String startLine,String endLine,String orderBy,String orderDir){
		Map<String, Object> buysMap = null;
		String result = getCaseLotBuysSimplify(caseLotId, startLine, endLine, orderBy, orderDir);
		JSONObject jsonObjectResult = JSONObject.fromObject(result);
		String errorCode = jsonObjectResult.getString("errorCode");
		if("0".equals(errorCode)){
			buysMap = new HashMap<String, Object>();
			List<CaseLot> caseLots = new ArrayList<CaseLot>();
			JSONObject jsonObjectValue = jsonObjectResult.getJSONObject("value");
			String totalPage = jsonObjectValue.getString("totalPage");
			String currentPageNo = jsonObjectValue.getString("currentPageNo");
			String totalResult = jsonObjectValue.getString("totalResult");
			JSONArray jsonArray = jsonObjectValue.getJSONArray("list");
			for(int i=0;i<jsonArray.size();i++){
				CaseLot caseLot = new CaseLot();
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				JSONObject jsonObjectUserInfo = jsonObject.getJSONObject("userinfo");
				JSONObject jsonObjectCaseLotBuy = jsonObject.getJSONObject("caseLotBuy");
				String nickName = jsonObjectUserInfo.getString("nickname");
				String userName = jsonObjectUserInfo.getString("userName");
				String mobileId = jsonObjectUserInfo.getString("mobileid");
				String buyName = commonUtil.getViewName(nickName, userName, mobileId);
				caseLot.setBuyName(buyName);
				String buyTime = commonUtil.getDataFormat(jsonObjectCaseLotBuy.getLong("buyTime"), "");
				caseLot.setBuyTime(buyTime);
				String safeAmt = commonUtil.getBalanceFormat(jsonObjectCaseLotBuy.getString("safeAmt"), 2);
				String buyAmt = commonUtil.getBalanceFormat(jsonObjectCaseLotBuy.getString("num"), 2);
				caseLot.setSafeAmt(safeAmt);
				caseLot.setBuyAmt(buyAmt);
				caseLots.add(caseLot);

			}
			buysMap.put("caseLots", caseLots);
			buysMap.put("totalPage", totalPage);
			buysMap.put("currentPageNo", currentPageNo);
			buysMap.put("totalResult", totalResult);
		}
		return buysMap;
		
	}
	/**
	 * 
	 * 查询用户合买查询列表
	 * @param userNo
	 * @param startLine
	 * @param endLine
	 * @return
	 */
	public String getCaseLotBuys(String userNo,String startLine,String endLine){
		String url = lottery + "select/selectCaseLotBuys";
		String parameter = "userno=" + userNo+"&startLine="+startLine+"&endLine="+endLine;
		String result = commonUtil.setURLByGET(url+"?"+parameter);
		logger.info("SelectAllUtil/getCaseLotBuys查询用户合买查询列表返回result："
				+ result);
		return result;

	}
	/**
	 * 查询用户合买查询列表
	 * @param userNo
	 * @param startLine
	 * @param endLine
	 * @return
	 */
	public Map<String, Object> selectCaseLotBuys(String userNo,String startLine,String endLine){
		Map<String, Object> caseLotBuysMap = null;
		String result = getCaseLotBuys(userNo, startLine, endLine);
		JSONObject jsonObjectResult = JSONObject.fromObject(result);
		String errorCode = jsonObjectResult.getString("errorCode");
		List<CaseLot> caseLotList= new ArrayList<CaseLot>();
		if("0".equals(errorCode)){
			caseLotBuysMap = new HashMap<String, Object>();
			JSONObject jsonObjectValue = jsonObjectResult.getJSONObject("value");
			String totalPage = jsonObjectValue.getString("totalPage");
			String currentPageNo = jsonObjectValue.getString("currentPageNo");
			JSONArray jsonArray = jsonObjectValue.getJSONArray("list");
			if(jsonArray!=null&&jsonArray.size()>0){
				for (int i = 0; i < jsonArray.size(); i++) {
					CaseLot caseLot = new CaseLot();
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					String nickName = ""; // 昵称
					String mobileId = ""; // 手机号码
					String userName = "";// 用户名
					String starter  = "";
					if(jsonObject!=null){
						JSONObject starterObject = jsonObject.getJSONObject("starter");
						if(starterObject!=null){
							nickName = starterObject.getString("nickname"); // 昵称
							userName = starterObject.getString("userName"); // 用户名
							mobileId = starterObject.getString("mobileid"); // 手机号
							starter = commonUtil.getViewName(nickName, userName, mobileId);
						}
						JSONObject caseLotObject = jsonObject.getJSONObject("caseLot");
						JSONObject caseLotBuyObject = jsonObject.getJSONObject("caseLotBuy");
						String id = caseLotObject.getString("id"); // 方案编号
						String lotNo = caseLotObject.getString("lotno"); // 用户编号
						String totalAmt = commonUtil.getBalanceFormat(caseLotObject.getString("totalAmt"), 2)  ; // 方案总金额
//						String safeAmt = commonUtil.getBalanceFormat(caseLotObject.getString("safeAmt"),2); // 保底金额
//						String buyAmt = commonUtil.getBalanceFormat(caseLotObject
//								.getLong("buyAmtByStarter") + caseLotObject
//								.getLong("buyAmtByFollower")+"",2); // 总的认购金额
//						JSONObject achievementObject = jsonObject
//								.getJSONObject("achievement");//战绩
//						
//						JSONObject displayIcon = (achievementObject != null && achievementObject
//								.has("displayIcon")) ? (JSONObject) achievementObject
//								.get("displayIcon") : new JSONObject();
//						String achievement = getAchievementByDisplayIcon(displayIcon);
//						BigDecimal caseBuyAfterAmt = (new BigDecimal(buyAmt)).multiply(new BigDecimal(100)).divide(new BigDecimal(totalAmt), 2, BigDecimal.ROUND_HALF_UP);
//					    BigDecimal caseBaodiAmt = new BigDecimal(safeAmt).multiply(new BigDecimal(100)).divide(new BigDecimal(totalAmt), 2, BigDecimal.ROUND_HALF_UP);
//					    String progress = caseBuyAfterAmt.longValue()+"%+"+caseBaodiAmt.longValue()+"%(保)";
					    String lotName = lotteryUtil.getLotNameByLotNo(lotNo);
						String displayStateMemo = caseLotObject.getString("displayStateMemo");
						String startTime = commonUtil.getDataFormat(caseLotObject.getLong("startTime"), "");
						String buyTime = commonUtil.getDataFormat(caseLotBuyObject.getLong("buyTime"), "");
						String safeAmt = commonUtil.getBalanceFormat(caseLotBuyObject.getString("safeAmt"), 2);
						String buyAmt = commonUtil.getBalanceFormat(caseLotBuyObject.getString("num"), 2);
						caseLot.setBuyTime(buyTime);
						caseLot.setStartTime(startTime);
						caseLot.setDisplayStateMemo(displayStateMemo);
					    caseLot.setCaseLotId(id);
						caseLot.setTotalAmt(totalAmt);
						caseLot.setSafeAmt(safeAmt);
						caseLot.setBuyAmt(buyAmt);
						caseLot.setLotNo(lotNo);
						caseLot.setLotName(lotName);
//						caseLot.setProgress(progress);
//						caseLot.setAchievement(achievement);
						caseLot.setStarter(starter);
						caseLotList.add(caseLot);
					}
				}
			}
			caseLotBuysMap.put("caseLotList", caseLotList);
			caseLotBuysMap.put("totalPage", totalPage);
			caseLotBuysMap.put("currentPageNo", currentPageNo);
		}
		return caseLotBuysMap;
	}
	/**
	 * 查询用户合买的认购金额，中奖金额，佣金金额，保底金额，冻结的保底金额
	 * @param caseLotId
	 * @param userNo
	 * @return
	 */
	public String getCaseLotUserBuy(String caseLotId,String userNo){
		String url = lottery + "select/selectCaseLotUserBuy";
		String parameter = "userno=" + userNo+"&caselotid="+caseLotId;
		String result = commonUtil.setURLByGET(url+"?"+parameter);
		logger.info("SelectAllUtil/getCaseLotUserBuy查询用户合买金额信息返回result："
				+ result);
		return result;
	}
	/**
	 * 查询用户合买的认购金额，中奖金额，佣金金额，保底金额，冻结的保底金额
	 * @param caseLotId
	 * @param userNo
	 * @return
	 */
	public Map<String, String> selectCaseLotUserBuy(String caseLotId,String userNo){
		Map<String, String> caseLotUserBuyMap = null;
		String result = getCaseLotUserBuy(caseLotId, userNo);
		JSONObject jsonObjectResult = JSONObject.fromObject(result);
		String errorCode = jsonObjectResult.getString("errorCode");
		if("0".equals(errorCode)){
			JSONObject jsonObjectValue = jsonObjectResult.getJSONObject("value");
			String userSafeAmt = commonUtil.getBalanceFormat(jsonObjectValue.getString("safeAmt"), 2) ;
			String userTotalAmt = commonUtil.getBalanceFormat(jsonObjectValue.getString("num"), 2) ;
			String userPrizeAmt = commonUtil.getBalanceFormat(jsonObjectValue.getString("prizeAmt"), 2) ;
			caseLotUserBuyMap = new HashMap<String, String>();
			caseLotUserBuyMap.put("userSafeAmt", userSafeAmt);
			caseLotUserBuyMap.put("userTotalAmt", userTotalAmt);
			caseLotUserBuyMap.put("userPrizeAmt", userPrizeAmt);
		}
		return caseLotUserBuyMap;
	}
	/**
	 * 处理战绩
	 * @param displayIcon :{"cup":2,"crown":4,"graydiamond":3,"graycrown":5,"diamond":1,"graycup":1,"graygoldStar":1}
	 * @return
	 */
	public static String getAchievementByDisplayIcon(JSONObject displayIcon){
		System.out.println(displayIcon);
		String achievement = "";
		//有效
		long crown = 0;
		long cup = 0;
		long diamond = 0;
		long goldStar = 0;
		//无效
		long ineffectiveCrown =0;
		long ineffectiveCup = 0;
		long ineffectiveDiamond  =0;
		long ineffectiveGoldStar = 0;
		if(displayIcon!=null){
			String goldStarStr = displayIcon.containsKey("goldStar")!=true ? "" : displayIcon.getString("goldStar");
			String graygoldStarStr = displayIcon.containsKey("graygoldStar")!=true ? "" : displayIcon.getString("graygoldStar");
			String diamondStr = displayIcon.containsKey("diamond")!=true ? "" : displayIcon.getString("diamond");
			String graydiamondStr = displayIcon.containsKey("graydiamond")!=true ? "" : displayIcon.getString("graydiamond");
			String cupStr = displayIcon.containsKey("cup")!=true ? "" : displayIcon.getString("cup");
			String graycupStr = displayIcon.containsKey("graycup")!=true ? "" : displayIcon.getString("graycup");
			String crownStr = displayIcon.containsKey("crown")!=true ? "" : displayIcon.getString("crown");
			String graycrownStr = displayIcon.containsKey("graycrown")!=true ? "" : displayIcon.getString("graycrown");
			System.out.println("goldStarStr:"+goldStarStr);
			if(crownStr!=null&&!"".equals(crownStr)&&!"null".equals(crownStr)){
				crown = Long.parseLong(crownStr);
			}
			if(graycrownStr!=null&&!"".equals(graycrownStr)&&!"null".equals(graycrownStr)){
				ineffectiveCrown = Long.parseLong(graycrownStr);
			}
			if(cupStr!=null&&!"".equals(cupStr)&&!"null".equals(cupStr)){
				cup = Long.parseLong(cupStr);
			}
			if(graycupStr!=null&&!"".equals(graycupStr)&&!"null".equals(graycupStr)){
				ineffectiveCup = Long.parseLong(graycupStr);
			}
			if(diamondStr!=null&&!"".equals(diamondStr)&&!"null".equals(diamondStr)){
				diamond = Long.parseLong(diamondStr);
			}
			if(graydiamondStr!=null&&!"".equals(graydiamondStr)&&!"null".equals(graydiamondStr)){
				ineffectiveDiamond = Long.parseLong(graydiamondStr);
			}
			if(goldStarStr!=null&&!"".equals(goldStarStr)&&!"null".equals(goldStarStr)){
				goldStar = Long.parseLong(goldStarStr);
			}
			if(graygoldStarStr!=null&&!"".equals(graygoldStarStr)&&!"null".equals(graygoldStarStr)){
				ineffectiveGoldStar = Long.parseLong(graygoldStarStr);
			}
			//<span class="xingxing-bg">2</span><span class="zuanshi-bg">1</span><span class="jinbei-bg">4</span><span class="huangguan-bg">1</span>
			if(crown>0){
				achievement +="<span class='huangguan-bg'>"+crown+"</span>";
//				for(int m=0;m<crown;m++){
//					achievement += "<img src='/w/wap/hemaiImages/huangguan_cai.gif'></img>";
//				}
			}
			if(ineffectiveCrown>0){
				achievement +="<span class='huangguan-gray-bg'>"+ineffectiveCrown+"</span>";
//				for(int m=0;m<ineffectiveCrown;m++){
//					achievement += "<img src='/w/wap/hemaiImages/huangguan_hui.gif'></img>";
//				}
			}
			if(cup>0){
				achievement +="<span class='jinbei-bg'>"+cup+"</span>";
//				for(int m=0;m<cup;m++){
//					achievement += "<img src='/w/wap/hemaiImages/jiangbei_cai.gif'></img>";
//				}
			}
			if(ineffectiveCup>0){
				achievement +="<span class='jinbei-gray-bg'>"+ineffectiveCup+"</span>";

//				for(int m=0;m<ineffectiveCup;m++){
//					achievement += "<img src='/w/wap/hemaiImages/jiangbei_hui.gif'></img>";
//				}
			}
			if(diamond>0){
				achievement +="<span class='zuanshi-bg'>"+diamond+"</span>";
//				for(int m=0;m<diamond;m++){
//					achievement += "<img src='/w/wap/hemaiImages/zuanshi_cai.gif'></img>";
//				}
			}
			if(ineffectiveDiamond>0){
				achievement +="<span class='zuanshi-gray-bg'>"+ineffectiveDiamond+"</span>";
//				for(int m=0;m<ineffectiveDiamond;m++){
//					achievement += "<img src='/w/wap/hemaiImages/zuanshi_hui.gif'></img>";
//				}
			}
			if(goldStar>0){
				achievement +="<span class='xingxing-bg'>"+goldStar+"</span>";
//				for(int m=0;m<goldStar;m++){
//					achievement += "<img src='/w/wap/hemaiImages/xing_cai.gif'></img>";
//				}
			}
			if(ineffectiveGoldStar>0){
				achievement +="<span class='xingxing-gray-bg'>"+ineffectiveGoldStar+"</span>";
//				for(int m=0;m<ineffectiveGoldStar;m++){
//					achievement += "<img src='/w/wap/hemaiImages/xing_hui.gif'></img>";
//				}
			}
		}
		return achievement;
	}
	/**
	 * 查询竞彩出票详情
	 * @param orderId
	 * @return
	 */
	public String getJingCaiTlots(String orderId){
		Date nowDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String url = lottery + "select/getTlot";
		String parameter = "orderid=" + orderId + "&beginTime=20100101&endTime=" + dateFormat.format(nowDate)
				+"&startLine=0&endLine=10000";
		String result = commonUtil.setUrlByPOST(url,parameter);
//		logger.info("SelectAllUtil/getJingCaiTlots查询竞彩出票详情返回result："
//				+ result);
		return result;

	}
	/**
	 * 查询竞彩出票详情
	 * @param orderId
	 * @return
	 */
	public JSONObject selectJingCaiTlotsValueObject(String orderId){
		JSONObject tlotsValueObject = null;
		String result = getJingCaiTlots(orderId);
		JSONObject resultJsonObject = JSONObject.fromObject(result);
		String errorCode = resultJsonObject.getString("errorCode");
		if("0".equals(errorCode)){
			tlotsValueObject = resultJsonObject.getJSONObject("value");
		}
		return tlotsValueObject;
	}
	/**
	 * 查询竞彩出票详情(未出票的情况下)
	 * @param orderId
	 * @return
	 */
	public String getJingCaiTlotsByTorder(String orderId){
//		Date nowDate = new Date();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String url = lottery + "select/getTlotsByOrderidWrapper";
		String parameter = "orderid=" + orderId ;
		String result = commonUtil.setUrlByPOST(url,parameter);
//		logger.info("SelectAllUtil/getJingCaiTlotsByTorder查询竞彩出票详情返回result："
//				+ result);
		return result;

	}
	/**
	 * 查询竞彩出票详情(未出票的情况下)
	 * @param orderId
	 * @return
	 */
	public JSONObject selectJingCaiTlotsByTorderValueObject(String orderId){
		JSONObject tlotsValueObject = null;
		String result = getJingCaiTlotsByTorder(orderId);
		JSONObject resultJsonObject = JSONObject.fromObject(result);
		String errorCode = resultJsonObject.getString("errorCode");
		if("0".equals(errorCode)){
			tlotsValueObject = resultJsonObject.getJSONObject("value");
		}
		return tlotsValueObject;
	}
	/**
	 * 查询竞彩赛果
	 * @param lotNo
	 * @param day
	 * @param weekId
	 * @param teamId
	 * @return
	 */
	public String getJingCaiMatches(String lotNo, String day, String weekId, String teamId){
		String url = lottery + "select/getjingcaimatches";
		String parameter = "lotno=" + lotNo + "&day="+day+"&weekid="+weekId+"&teamid="+teamId;
		String result = commonUtil.setUrlByPOST(url,parameter);
		logger.info("SelectAllUtil/getCaseLotDetail查询竞彩赛果详情返回result："+ result);
		return result;
	}
	/**
	 * 查询竞彩赛果
	 * @param lotNo
	 * @param day
	 * @param weekId
	 * @param teamId
	 * @return
	 */
	public JSONObject selectJingCaiMatchesValueObject(String lotNo, String day, String weekId, String teamId){
		JSONObject matchesValueObject =null;
		String result = getJingCaiMatches(lotNo, day, weekId, teamId);
		JSONObject resultJsonObject = JSONObject.fromObject(result);
		String errorCode = resultJsonObject.getString("errorCode");
		if("0".equals(errorCode)){
			matchesValueObject = resultJsonObject.getJSONObject("value");
		}
		return matchesValueObject;
	}
	/**
	 * 查询竞彩赛果(开奖信息)
	 * @param date
	 * @param type0篮球1足彩
	 * @return
	 */
	public String getJingCaiSaiGuo(String date, String type){
		String url = lottery + "select/getjingcairesult";
		String parameter = "date=" + date + "&type="+type;
		String result = commonUtil.setUrlByPOST(url,parameter);
		logger.info("SelectAllUtil/getJingCaiSaiGuo查询竞彩赛果详情返回result："+ result);
		return result;
	}
	/**
	 * 查询竞彩赛果(开奖信息)
	 * @param date
	 * @param type0篮球1足彩
	 * @return
	 */
	public List<JingCaiSaiGuo> selectJcSaiGuo(String date, String type){
		List<JingCaiSaiGuo> jingCaiSaiGuos = new ArrayList<JingCaiSaiGuo>();
		String jcSaiGuo = getJingCaiSaiGuo(date, type);
		JSONObject saiGuoJsonObject = JSONObject.fromObject(jcSaiGuo);
		String errorCode = saiGuoJsonObject.getString("errorCode");
		if("0".equals(errorCode)){
			JSONArray valueJsonArray = saiGuoJsonObject.getJSONArray("value");
			if(valueJsonArray!=null&&!"null".equals(valueJsonArray)){
				for(int i=0;i<valueJsonArray.size();i++){
					JingCaiSaiGuo jingCaiSaiGuo = new JingCaiSaiGuo();
					JSONObject marchesJsonObject = valueJsonArray.getJSONObject(i).getJSONObject("matches");
					JSONObject resultJsonObject = valueJsonArray.getJSONObject(i).getJSONObject("result");
					String weekid =marchesJsonObject.getString("weekid");
					String day = marchesJsonObject.getString("day");
					String teamid = marchesJsonObject.getString("teamid");
					String team = marchesJsonObject.getString("team");
					String league = marchesJsonObject.getString("league");
					long time = marchesJsonObject.getLong("time");
					String result = resultJsonObject.getString("result");
					String letpoint = resultJsonObject.getString("letpoint");
					String basepoint = resultJsonObject.getString("basepoint");
					String firsthalfresult = resultJsonObject.getString("firsthalfresult");
					String dayView = getDayFormat(day);
					String timeView = commonUtil.getDataFormat(time, "HH:mm:ss");
					String week = getWeek(weekid);
					String teamZhu = "";
					String teamKe = "";
					String saiGuo = "";
					String teamStr[] = team.split("\\:");
					String resultStr[] =result.split("\\:");
					System.out.println(team);
					System.out.println(result);
					if(result.indexOf(":")>0){
						if("0".equals(type)){//篮球
							teamZhu = teamStr[0]+"(主)";
							teamKe = teamStr[1]+"(客)";
							if(Integer.parseInt(resultStr[1])>Integer.parseInt(resultStr[0])){
								saiGuo = "主负";
							}else if(Integer.parseInt(resultStr[1])==Integer.parseInt(resultStr[0])){
								saiGuo = "平";
							}else if(Integer.parseInt(resultStr[1])<Integer.parseInt(resultStr[0])){
								saiGuo = "主胜";
							}
							result = resultStr[1]+":"+resultStr[0];
						}else if("1".equals(type)){//足球
							teamZhu = teamStr[0]+"(主)";
							teamKe = teamStr[1]+"(客)";
							if(Integer.parseInt(resultStr[0])>Integer.parseInt(resultStr[1])){
								saiGuo = "主胜";
							}else if(Integer.parseInt(resultStr[0])==Integer.parseInt(resultStr[1])){
								saiGuo = "平";
							}else if(Integer.parseInt(resultStr[0])<Integer.parseInt(resultStr[1])){
								saiGuo = "主负";
							}
						}
						jingCaiSaiGuo.setBasepoint(basepoint);
						jingCaiSaiGuo.setDay(dayView);
						jingCaiSaiGuo.setFirsthalfresult(firsthalfresult);
						jingCaiSaiGuo.setLeague(league);
						jingCaiSaiGuo.setLetpoint(letpoint);
						jingCaiSaiGuo.setResult(result);
						jingCaiSaiGuo.setSaiGuo(saiGuo);
						jingCaiSaiGuo.setTeamid(teamid);
						jingCaiSaiGuo.setTeamKe(teamKe);
						jingCaiSaiGuo.setTeamZhu(teamZhu);
						jingCaiSaiGuo.setTime(timeView);
						jingCaiSaiGuo.setWeek(week);
						jingCaiSaiGuos.add(jingCaiSaiGuo);
					}
					
				}
			}
		}
		return jingCaiSaiGuos;
	}
	/**
	 * 得到日期显示格式
	 * 20130227 to 2013-02-27
	 * @param day
	 * @return
	 */
	public static String getDayFormat(String day){
		return day.substring(0, 4)+"-"+day.substring(4, 6)+"-"+day.substring(6);
	}
	/**
	 * 得到星期
	 * @param weekid
	 * @return
	 */
	public String getWeek(String weekid) {
		String week = "";
		if ("1".equals(weekid)) {
			week = "星期一";
		} else if ("2".equals(weekid)) {
			week = "星期二";
		} else if ("3".equals(weekid)) {
			week = "星期三";
		} else if ("4".equals(weekid)) {
			week = "星期四";
		} else if ("5".equals(weekid)) {
			week = "星期五";
		} else if ("6".equals(weekid)) {
			week = "星期六";
		} else if ("7".equals(weekid)) {
			week = "星期日";
		}
		return week;
	}
}
