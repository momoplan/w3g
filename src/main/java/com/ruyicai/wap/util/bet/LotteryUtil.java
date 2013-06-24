package com.ruyicai.wap.util.bet;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.Constants;
import com.ruyicai.wap.util.SelectAllUtil;
import com.ruyicai.wap.vo.BetRequest;
import com.ruyicai.wap.vo.CaseLotRequest;
import com.ruyicai.wap.vo.OrderRequest;
import com.ruyicai.wap.vo.SubscribeRequest;
import com.ruyicai.wap.vo.WinInfo;
@Component
public class LotteryUtil {
	private static Logger logger = Logger.getLogger(SelectAllUtil.class);
	@Value(value = "${lottery}")
	String lottery;
	@Autowired
	SelectAllUtil selectAllUtil;
	@Autowired
	CommonUtil commonUtil;

	/**
	 * 通过彩种编号lotNo获得彩种名称lotName
	 * 
	 * @param lotNo
	 * @return彩种名称
	 */
	public String getLotNameByLotNo(String lotNo) {
		String lotName = "";
		if (Constants.LOTNO_11YDJ_SD11X5.equals(lotNo)) {
			lotName = Constants.LOTNAME_11YDJ_SD11X5;
		} else if (Constants.LOTNO_22X5.equals(lotNo)) {
			lotName = Constants.LOTNAME_22X5;
		} else if (Constants.LOTNO_DLC_JX11X5.equals(lotNo)) {
			lotName = Constants.LOTNAME_DLC_JX11X5;
		} else if (Constants.LOTNO_DLT.equals(lotNo)) {
			lotName = Constants.LOTNAME_DLT;
		} else if (Constants.LOTNO_FC3D.equals(lotNo)) {
			lotName = Constants.LOTNAME_FC3D;
		} else if (Constants.LOTNO_GD11X5.equals(lotNo)) {
			lotName = Constants.LOTNAME_GD11X5;
		} else if (Constants.LOTNO_GDKL10F.equals(lotNo)) {
			lotName = Constants.LOTNAME_GDKL10F;
		} else if (Constants.LOTNO_JC_LQ_DXF.equals(lotNo)) {
			lotName = Constants.LOTNAME_JC_LQ_DXF;
		} else if (Constants.LOTNO_JC_LQ_RFSF.equals(lotNo)) {
			lotName = Constants.LOTNAME_JC_LQ_RFSF;
		} else if (Constants.LOTNO_JC_LQ_SF.equals(lotNo)) {
			lotName = Constants.LOTNAME_JC_LQ_SF;
		} else if (Constants.LOTNO_JC_LQ_SFC.equals(lotNo)) {
			lotName = Constants.LOTNAME_JC_LQ_SFC;
		} else if (Constants.LOTNO_JC_ZQ_BCSPF.equals(lotNo)) {
			lotName = Constants.LOTNAME_JC_ZQ_BCSPF;
		} else if (Constants.LOTNO_JC_ZQ_BF.equals(lotNo)) {
			lotName = Constants.LOTNAME_JC_ZQ_BF;
		} else if (Constants.LOTNO_JC_ZQ_SPF.equals(lotNo)) {
			lotName = Constants.LOTNAME_JC_ZQ_SPF;
		} else if (Constants.LOTNO_JC_ZQ_RQSPF.equals(lotNo)) {
			lotName = Constants.LOTNAME_JC_ZQ_RQSPF;
		}else if (Constants.LOTNO_JC_ZQ_ZJQ.equals(lotNo)) {
			lotName = Constants.LOTNAME_JC_ZQ_ZJQ;
		} else if (Constants.LOTNO_PL3.equals(lotNo)) {
			lotName = Constants.LOTNAME_PL3;
		} else if (Constants.LOTNO_PL5.equals(lotNo)) {
			lotName = Constants.LOTNAME_PL5;
		} else if (Constants.LOTNO_QLC.equals(lotNo)) {
			lotName = Constants.LOTNAME_QLC;
		} else if (Constants.LOTNO_QXC.equals(lotNo)) {
			lotName = Constants.LOTNAME_QXC;
		} else if (Constants.LOTNO_SSC.equals(lotNo)) {
			lotName = Constants.LOTNAME_SSC;
		} else if (Constants.LOTNO_SSQ.equals(lotNo)) {
			lotName = Constants.LOTNAME_SSQ;
		} else if (Constants.LOTNO_ZC_4C_JQC.equals(lotNo)) {
			lotName = Constants.LOTNAME_ZC_4C_JQC;
		} else if (Constants.LOTNO_ZC_6C_BQC.equals(lotNo)) {
			lotName = Constants.LOTNAME_ZC_6C_BQC;
		} else if (Constants.LOTNO_ZC_R9C.equals(lotNo)) {
			lotName = Constants.LOTNAME_ZC_R9C;
		} else if (Constants.LOTNO_ZC_SFC_14C.equals(lotNo)) {
			lotName = Constants.LOTNAME_ZC_SFC_14C;
		} else if (Constants.LOTNO_JC_LQ_HHGG.equals(lotNo)) {
			lotName = Constants.LOTNAME_JC_LQ_HHGG;
		} else if (Constants.LOTNO_JC_ZQ_HHGG.equals(lotNo)) {
			lotName = Constants.LOTNAME_JC_ZQ_HHGG;
		} else if (Constants.LOTNO_BD_BQC.equals(lotNo)) {
			lotName = Constants.LOTNAME_BD_BQC;
		} else if (Constants.LOTNO_BD_DCBF.equals(lotNo)) {
			lotName = Constants.LOTNAME_BD_DCBF;
		} else if (Constants.LOTNO_BD_SPF.equals(lotNo)) {
			lotName = Constants.LOTNAME_BD_SPF;
		} else if (Constants.LOTNO_BD_SXPDS.equals(lotNo)) {
			lotName = Constants.LOTNAME_BD_SXPDS;
		} else if (Constants.LOTNO_BD_ZJQ.equals(lotNo)) {
			lotName = Constants.LOTNAME_BD_ZJQ;
		} else if (Constants.LOTNO_NMK3.equals(lotNo)) {
			lotName = Constants.LOTNAME_NMK3;
		}
		return lotName;
	}
	/**
	 * 将字符串list的注码转换成字符串
	 * 
	 * @param betCodeList
	 * @return
	 */
	public static String getStringFromStringList(List<String> betCodeList) {
		String result = "";
		if (betCodeList == null || betCodeList.size() == 0) {
			return "";
		} else {
			for (int i = 0; i < betCodeList.size(); i++) {
				result += betCodeList.get(i);
			}
			return result;
		}

	}
	/**
	 * 将数字list的注码转换成字符串
	 * 
	 * @param betCodeList
	 * @return
	 */
	public static String getStringFromIntegerList(List<Integer> betCodeList) {
		String result = "";
		if (betCodeList == null || betCodeList.size() == 0) {
			return "";
		} else {
			for (int i = 0; i < betCodeList.size(); i++) {
				result += betCodeList.get(i);
			}
			return result;
		}

	}
	/**
	 * 将list的注码转换成带","的字符串
	 * 
	 * @param betCodeList
	 * @return
	 */
	public static String getDouHaoStringFromStringList(List<String> betCodeList) {
		String result = "";
		if (betCodeList == null || betCodeList.size() == 0) {
			return "";
		} else {
			for (int i = 0; i < betCodeList.size(); i++) {
				result += betCodeList.get(i) + ",";
			}
			if (result.charAt(result.length() - 1) == ',') {
				result = result.substring(0, result.length() - 1);
			}
			return result;
		}

	}
	/**
	 * 将list的注码转换成带","的字符串
	 * 
	 * @param betCodeList
	 * @return
	 */
	public static String getDouHaoStringFromIntegerList(List<Integer> betCodeList) {
		String result = "";
		if (betCodeList == null || betCodeList.size() == 0) {
			return "";
		} else {
			for (int i = 0; i < betCodeList.size(); i++) {
				result += betCodeList.get(i) + ",";
			}
			if (result.charAt(result.length() - 1) == ',') {
				result = result.substring(0, result.length() - 1);
			}
			return result;
		}

	}

	/**
	 * 将list的注码转换成带" "的字符串
	 * 
	 * @param betCodeList
	 * @return
	 */
	public static String getKongGeStringFromStringList(List<String> betCodeList) {
		String result = "";
		if (betCodeList == null || betCodeList.size() == 0) {
			return "";
		} else {
			for (int i = 0; i < betCodeList.size(); i++) {
				result += betCodeList.get(i) + " ";
			}

			if (result.charAt(result.length() - 1) == ' ') {
				result = result.substring(0, result.length() - 1);
			}
			return result;
		}

	}
	/**
	 * 将list的注码转换成带" "的字符串
	 * 
	 * @param betCodeList
	 * @return
	 */
	public static String getKongGeStringFromIntegerList(List<Integer> betCodeList) {
		String result = "";
		if (betCodeList == null || betCodeList.size() == 0) {
			return "";
		} else {
			for (int i = 0; i < betCodeList.size(); i++) {
				result += betCodeList.get(i) + " ";
			}

			if (result.charAt(result.length() - 1) == ' ') {
				result = result.substring(0, result.length() - 1);
			}
			return result;
		}

	}
	/**
	 * 将带0的注码转换成list
	 * 
	 * @param betCode
	 * @return
	 */
	public static List<String> getStringListFromZeroString(String betCode) {
		List<String> betCodeList = new ArrayList<String>();
		int betCodeLenght = betCode.length();
		int betCodeListSize = betCodeLenght / 2;
		int n = 0;
		for (int i = 0; i < betCodeListSize; i++) {
			String s = betCode.substring(n, n + 2);
			n = n + 2;
			betCodeList.add(s);
		}
		return betCodeList;
	}
	/**
	 * 将带0的注码转换成list
	 * 
	 * @param betCode
	 * @return
	 */
	public static List<Integer> getIntegerListFromZeroString(String betCode) {
		List<Integer> betCodeList = new ArrayList<Integer>();
		int betCodeLenght = betCode.length();
		int betCodeListSize = betCodeLenght / 2;
		int n = 0;
		for (int i = 0; i < betCodeListSize; i++) {
			String s = betCode.substring(n, n + 2);
			n = n + 2;
			betCodeList.add(Integer.parseInt(s));
		}
		return betCodeList;
	}
	/**
	 * 将不带0的注码转换成list
	 * 
	 * @param betCode
	 * @return
	 */
	public static List<String> getStringListFromString(String betCode) {
		List<String> betCodeList = new ArrayList<String>();
		int betCodeLenght = betCode.length();
		int n = 0;
		for (int i = 0; i < betCodeLenght; i++) {
			String s = betCode.substring(n, n + 1);
			n = n + 1;
			betCodeList.add(s);
		}
		return betCodeList;
	}
	/**
	 * 将不带0的注码转换成list
	 * 
	 * @param betCode
	 * @return
	 */
	public static List<Integer> getIntegerListFromString(String betCode) {
		List<Integer> betCodeList = new ArrayList<Integer>();
		int betCodeLenght = betCode.length();
		int n = 0;
		for (int i = 0; i < betCodeLenght; i++) {
			String s = betCode.substring(n, n + 1);
			n = n + 1;
			betCodeList.add(Integer.parseInt(s));
		}
		return betCodeList;
	}

	/**
	 * 将注码中的"0"去掉
	 * 
	 * @param str
	 * @return
	 */
	public static String removeBetCodeZero(String str) {
		StringBuffer stringBuffer = new StringBuffer();
		int j = 1;
		for (int i = 0; i < str.length() / 2; i++) {
			stringBuffer.append(str.substring(j, j + 1));
			j += 2;
		}
		return stringBuffer.toString();
	}
	/**
	 * 注码中0开头的去0
	 * @param str
	 * @return
	 */
	public static List<String> removeBetCodeZero2(String str) {
		List<String> list = new ArrayList<String>();
		int j = 0;
		for (int i = 0; i < str.length() / 2; i++) {
			String s = str.substring(j, j + 2);
			if(s.startsWith("0")) s = s.substring(1);
			list.add(s);
			j += 2;
		}
		return list;
	}
	public static void main(String[] args) {
		System.out.println(removeBetCodeZero2("0405060708091011121314151617"));
	}
	/**
	 * 给注码加"0"
	 * 
	 * @param betcode
	 * @return
	 */
	public static String addBetCodeZero(String betcode) {
		StringBuffer stringBuffer = new StringBuffer();
		betcode = betcode.trim();
		int n = 0;
		for (int i = 0; i < betcode.length(); i++) {
			String str = betcode.substring(n, n + 1);
			stringBuffer.append("0" + str);
			n++;
		}
		return stringBuffer.toString();
	}
	/**
	 * 将注码中小于10的前面加0
	 * @param betCodeList
	 * @return
	 */
	public static String addZeroForBetCodeList(List<String> betCodeList){
		String betCodes = "";
		for (String betCode : betCodeList) {
			if(betCode.length()==1) betCodes+="0"+betCode;
			else betCodes+=betCode;
		}
		return betCodes;
	}
	/**
	 * 将注码中小于10的前面加0
	 * @param betCodeList
	 * @return
	 */
	public static String addZeroForBetCodeIntegerList(List<Integer> betCodeList){
		String betCodes = "";
		for (int betCode : betCodeList) {
			if(betCode<10) betCodes+="0"+betCode;
			else betCodes+=betCode;
		}
		return betCodes;
	}
	/**
	 * 将不带0的StringList注码转换成IntegerList
	 * @param betCodeList
	 * @return
	 */
	public static List<Integer> getIntegerListForStringList(List<String> betCodeList){
		List<Integer> integers = new ArrayList<Integer>();
		for (String betCode : betCodeList) {
			integers.add(Integer.parseInt(betCode));
		}
		return integers;
	}
	/**
	 * 将不带0 的IntegerList注码转换成StringList
	 * @param betCodeList
	 * @return
	 */
	public static List<String> getStringListForIntegerList(List<Integer> betCodeList){
		List<String> strings = new ArrayList<String>();
		for (int betCode : betCodeList) {
			strings.add(betCode+"");
		}
		return strings;
	}
	/**
	 * 普通投注，追号
	 * 
	 * @param jsonObject
	 * @return
	 */
	public String getToBetOrder(JSONObject jsonObject) {
		String url = lottery + "bet/tobetOrder";
		String parameter = "body=" + jsonObject;
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("LotteryUtil/getToBetOrder普通投注，追号返回：" + result);
		return result;
	}

	/**
	 * 普通投注，追号
	 * 
	 * @param userNo
	 * @param lotNo
	 * @param playType
	 * @param batchCode
	 * @param betCode
	 * @param beiShu
	 * @param oneAmount
	 * @param amount
	 * @param addNumber
	 * @param prizeend中奖是否停止追号0
	 *            :不停止,1:停止
	 * @param channel
	 * @return
	 */
	public String toBetOrder(String userNo, String lotNo,String playType,
			String batchCode, String betCode, String beiShu, String oneAmount,
			String amount, String addNumber, String prizeend, String channel) {
		logger.info("LotteryUtil/toBetOrder普通投注，追号参数userNo:" + userNo
				+ ",lotNo:" + lotNo + ",playType:" + playType +",batchCode:" + batchCode + ",betCode:"
				+ betCode + ",beiShu:" + beiShu + ",oneAmount:" + oneAmount
				+ ",amount:" + amount + ",addNumber:" + addNumber
				+ ",prizeend:" + prizeend + ",channel:" + channel);
		String result = "";
		String errorCode = "";
		lotNo = getJCBetLotNo(lotNo);
		String memo = getMemo(lotNo, playType);
		String drawway = "";// 投注方式0-单式，1-复式，2-胆拖，3-单式上传）
		drawway = getDrawWayAndLotsType(lotNo, betCode).get("drawWay");
		if (oneAmount == null || "".equals(oneAmount))
			oneAmount = "2";
		if (addNumber == null || "".equals(addNumber))
			addNumber = "1";
		if (beiShu == null || "".equals(beiShu))
			beiShu = "1";
		int oneAmountInt = Integer.parseInt(oneAmount) * 100;
		long amountLong = Long.parseLong(amount) * 100;
		int beiShuInt = Integer.parseInt(beiShu);
		int addNumberInt = Integer.parseInt(addNumber);
		if (batchCode == null || "".equals(batchCode)) {
			batchCode = selectAllUtil.selectLotteryBatchCode(lotNo);
		}
		List<BetRequest> betRequests = new ArrayList<BetRequest>();
		List<SubscribeRequest> subscribeRequests = new ArrayList<SubscribeRequest>();

		if (addNumberInt > 1) {
			// 得到追期的期号
			String afterIssue = selectAllUtil.getAfterIssue(lotNo, batchCode,
					(addNumberInt - 1) + "");
			JSONObject afterIssueObject = JSONObject.fromObject(afterIssue);
			String afterIssueErrorCode = afterIssueObject
					.getString("errorCode");
			if ("500".equals(afterIssueErrorCode)) {
				return "服务器忙,请稍后再试";
			}
			JSONArray afterIssueArray = afterIssueObject.getJSONArray("value");
			if (!"0".equals(afterIssueErrorCode)
					|| afterIssueArray.size() != addNumberInt) {
				return "服务器忙,请稍后再试";
			}
			String batchcode = "";
			for (int i = 0; i < afterIssueArray.size(); i++) {
				SubscribeRequest subscribeRequest = new SubscribeRequest();
				JSONObject issueObject = afterIssueArray.getJSONObject(i);
				JSONObject idObject = issueObject.getJSONObject("id");
				batchcode = idObject.getString("batchcode");
				subscribeRequest.setAmt(new BigDecimal(amountLong/addNumberInt));
				subscribeRequest.setLotmulti(new BigDecimal(beiShuInt));
				subscribeRequest.setBatchcode(batchcode);
				subscribeRequests.add(subscribeRequest);
			}
		}
		betRequests = getBetRequests(lotNo, betCode, amountLong, beiShuInt, addNumberInt);
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setLotno(lotNo);
		orderRequest.setBatchcode(batchCode);
		orderRequest.setUserno(userNo);
		orderRequest.setBuyuserno(userNo);
		orderRequest.setChannel(channel);
		orderRequest.setSubchannel("00092493");
		orderRequest.setPaytype(new BigDecimal(1));
		orderRequest.setMemo(memo);
		orderRequest.setBetRequests(betRequests);
		orderRequest.setPrizeend(new BigDecimal(prizeend));
		orderRequest.setOneamount(new BigDecimal(oneAmountInt));
		if (addNumberInt > 1) {
			orderRequest.setAccountnomoneysms(new BigDecimal(1));
			orderRequest.setBettype("0");
			orderRequest.setDrawway(new BigDecimal(drawway));
			orderRequest.setSubscribeRequests(subscribeRequests);
			orderRequest.setLotmulti(new BigDecimal(beiShu));
			orderRequest.setAmt(new BigDecimal(amountLong));
		} else {
			orderRequest.setAmt(new BigDecimal(amountLong));
			orderRequest.setSubscribeRequests(null);
			orderRequest.setLotmulti(new BigDecimal(beiShu));
			orderRequest.setBettype("2");// zhuihao(0), taocan(1),
											// touzhu(2),hemai(3),zengsong(4),zengsong_nosms(5);
		}
		logger.info("LotteryUtil/toBetOrder普通投注，追号参数:"
				+ JSONObject.fromObject(orderRequest));
		result = getToBetOrder(JSONObject.fromObject(orderRequest));
		JSONObject resultObject = JSONObject.fromObject(result);
		errorCode = resultObject.getString("errorCode");
		String messageError = "";
		if ("0".equals(errorCode)) {
			messageError = "您的投注申请已受理！";
		} else if ("500".equals(errorCode)) {
			messageError = "服务器忙，请稍后再试！";
		} else if("20100706".equals(errorCode)){
			messageError = "投注失败，您的投注已过期！";
		}else {
			messageError = "您的投注失败，请稍后再试！";
		}
		return messageError;
	}
	public String toJcBetOrder(String userNo, String lotNo,String betCode, String beiShu, String oneAmount,
			String amount, String channel) {
		logger.info("LotteryUtil/toJcBetOrder竞彩普通投注参数userNo:" + userNo
				+ ",lotNo:" + lotNo + ",betCode:"
				+ betCode + ",beiShu:" + beiShu + ",oneAmount:" + oneAmount
				+ ",amount:" + amount + ",channel:" + channel);
		String result = "";
		String errorCode = "";
		lotNo = getJCBetLotNo(lotNo);
		String memo = getMemo(lotNo, "");
		if (oneAmount == null || "".equals(oneAmount))
			oneAmount = "2";
		if (beiShu == null || "".equals(beiShu))
			beiShu = "1";
		int oneAmountInt = Integer.parseInt(oneAmount) * 100;
		long amountLong = Long.parseLong(amount) * 100;
		List<BetRequest> betRequests = new ArrayList<BetRequest>();
		betRequests = getJcBetRequests(betCode,beiShu);
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setLotno(lotNo);
		orderRequest.setUserno(userNo);
		orderRequest.setBuyuserno(userNo);
		orderRequest.setChannel(channel);
		orderRequest.setSubchannel("00092493");
		orderRequest.setPaytype(new BigDecimal(1));
		orderRequest.setMemo(memo);
		orderRequest.setSubscribeRequests(null);
		orderRequest.setBetRequests(betRequests);
		orderRequest.setOneamount(new BigDecimal(oneAmountInt));
		orderRequest.setAmt(new BigDecimal(amountLong));
		orderRequest.setSubscribeRequests(null);
		orderRequest.setLotmulti(new BigDecimal(beiShu));
		orderRequest.setBettype("2");// zhuihao(0), taocan(1),
											// touzhu(2),hemai(3),zengsong(4),zengsong_nosms(5);
		logger.info("LotteryUtil/toJcBetOrder竞彩普通投注:"
				+ JSONObject.fromObject(orderRequest));
		result = getToBetOrder(JSONObject.fromObject(orderRequest));
		JSONObject resultObject = JSONObject.fromObject(result);
		errorCode = resultObject.getString("errorCode");
		String messageError = "";
		if ("0".equals(errorCode)) {
			messageError = "您的投注申请已受理！";
		} else if ("500".equals(errorCode)) {
			messageError = "服务器忙，请稍后再试！";
		} else if("20100706".equals(errorCode)){
			messageError = "投注失败，您的投注已过期！";
		}else {
			messageError = "您的投注失败，请稍后再试！";
		}
		return messageError;
	}
	/**
	 * 普通赠送
	 * 
	 * @param jsonObject
	 * @return
	 */
	public String getToPresent(JSONObject jsonObject) {
		String url = lottery + "present/savepresent";
		String parameter = "body=" + jsonObject;
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("LotteryUtil/getToPresent普通赠送返回：" + result);
		return result;

	}

	/**
	 * 普通赠送
	 * @param userNo
	 * @param reciverMobile
	 * @param lotNo
	 * @param playType
	 * @param batchCode
	 * @param betCode
	 * @param beiShu
	 * @param oneAmount
	 * @param amount
	 * @param addNumber
	 * @param blessing
	 * @param channel
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String toPresent(String userNo, String reciverMobile,
			String lotNo,String playType, String batchCode, String betCode, String beiShu,
			String oneAmount, String amount, String addNumber, String blessing,
			String channel) throws UnsupportedEncodingException {
		logger.info("LotteryUtil/toPresent普通增送userNo:" + userNo
				+ ",reciverMobile:" + reciverMobile + ",lotNo:" + lotNo+ ",playType:" + playType
				+ ",batchCode:" + batchCode + ",betCode:" + betCode
				+ ",beiShu:" + beiShu + ",oneAmount:" + oneAmount + ",amount:"
				+ amount + ",addNumber:" + addNumber + ",blessing:" + blessing
				+ ",channel:" + channel);
		String result = "";
		String memo = getMemo(lotNo, playType);
		if (oneAmount == null || "".equals(oneAmount))
			oneAmount = "2";
		if (addNumber == null || "".equals(addNumber))
			addNumber = "1";
		if (beiShu == null || "".equals(beiShu))
			beiShu = "1";
		int oneAmountInt = Integer.parseInt(oneAmount) * 100;
		int amountInt = Integer.parseInt(amount) * 100;
		int beiShuInt = Integer.parseInt(beiShu);
		int addNumberInt = Integer.parseInt(addNumber);
		if (batchCode == null || "".equals(batchCode)) {
			batchCode = selectAllUtil.selectLotteryBatchCode(lotNo);
		}
		List<BetRequest> betRequests = getBetRequests(lotNo, betCode, amountInt, beiShuInt, addNumberInt);
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setLotno(lotNo);
		orderRequest.setBatchcode(batchCode);
		orderRequest.setUserno(userNo);
		orderRequest.setBuyuserno(userNo);
		orderRequest.setChannel(channel);
		orderRequest.setSubchannel("00092493");
		orderRequest.setPaytype(new BigDecimal(1));
		orderRequest.setMemo(memo);
		orderRequest.setBetRequests(betRequests);
		orderRequest.setOneamount(new BigDecimal(oneAmountInt));
		orderRequest.setAmt(new BigDecimal(amountInt));
		orderRequest.setReciverMobile(reciverMobile);
		orderRequest.setSubscribeRequests(null);
		orderRequest.setLotmulti(new BigDecimal(beiShu));
		orderRequest.setBettype("4");// zhuihao(0), taocan(1),
										// touzhu(2),hemai(3),zengsong(4),zengsong_nosms(5);
		orderRequest.setBlessing(URLEncoder.encode(blessing, "UTF-8"));
		logger.info("LotteryUtil/toPresent普通赠送参数:"
				+ JSONObject.fromObject(orderRequest));
		result = getToPresent(JSONObject.fromObject(orderRequest));
		JSONObject resultObject = JSONObject.fromObject(result);
		String errorCode = resultObject.getString("errorCode");
		return errorCode;
	}
	/**
	 * 普通合买
	 * 
	 * @param body
	 * @return
	 */
	public String getToCaseLotOrder(String body) {
		String url = lottery + "caselot/caselotOrder";
		String parameter = "body=" + body;
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("LotteryUtil/getToCaseLotOrder普通合买返回：" + result);
		return result;

	}
	/**
	 * 普通合买
	 * @param userNo
	 * @param clrJson
	 * @param lotNo
	 * @param playType
	 * @param batchCode
	 * @param betCode
	 * @param beiShu
	 * @param oneAmount
	 * @param amount
	 * @param addNumber
	 * @param channel
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String toCaseLotOrder(String userNo, JSONObject clrJson,
			String lotNo, String playType,String batchCode, String betCode, String beiShu,
			String oneAmount, String amount, String addNumber,
			String channel) throws UnsupportedEncodingException{
		logger.info("LotteryUtil/toCaseLotOrder普通合买参数userNo:" + userNo
				+ ",clrJson:" + clrJson.toString() + ",lotNo:" + lotNo + ",playType:" + playType
				+ ",batchCode:" + batchCode + ",betCode:" + betCode
				+ ",beiShu:" + beiShu + ",oneAmount:" + oneAmount + ",amount:"
				+ amount + ",addNumber:" + addNumber 
				+ ",channel:" + channel);
		String result = "";
		String memo = getMemo(lotNo, playType);
		if (oneAmount == null || "".equals(oneAmount))
			oneAmount = "2";
		if (addNumber == null || "".equals(addNumber))
			addNumber = "1";
		if (beiShu == null || "".equals(beiShu))
			beiShu = "1";
		int oneAmountInt = Integer.parseInt(oneAmount) * 100;
		int amountInt = Integer.parseInt(amount) * 100;
		int beiShuInt = Integer.parseInt(beiShu);
		int addNumberInt = Integer.parseInt(addNumber);
		if (batchCode == null || "".equals(batchCode)) {
			batchCode = selectAllUtil.selectLotteryBatchCode(lotNo);
		}
		
		BigDecimal lotsType = new BigDecimal(getDrawWayAndLotsType(lotNo, betCode).get("lotsType"));
		List<BetRequest> betRequests = getBetRequests(lotNo, betCode, amountInt, beiShuInt, addNumberInt);
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setLotno(lotNo);
		orderRequest.setBatchcode(batchCode);
		orderRequest.setAmt(new BigDecimal(amountInt));
		orderRequest.setUserno(userNo);
		orderRequest.setLotmulti(new BigDecimal(beiShu));
		orderRequest.setBuyuserno(userNo);
		orderRequest.setChannel(channel);
		orderRequest.setSubchannel("00092493");
		orderRequest.setPaytype(new BigDecimal(1));
		orderRequest.setMemo(memo);
		orderRequest.setLotsType(lotsType);//	投注类型 ，合买使用
		orderRequest.setBetRequests(betRequests);
		orderRequest.setSubscribeRequests(null);
		orderRequest.setOneamount(new BigDecimal(oneAmountInt));
		orderRequest.setBettype("3");//zhuihao(0), taocan(1), touzhu(2),hemai(3),zengsong(4),zengsong_nosms(5);
		CaseLotRequest clr = new CaseLotRequest();
		clr.setTotalAmt(orderRequest.getAmt().longValue());//合买总金额取投注总金额
		clr.setStarter(userNo);
		clr.setBuyAmt(Integer.parseInt(clrJson.getString("buyAmt"))*100);
		clr.setSafeAmt(Integer.parseInt(clrJson.getString("safeAmt"))*100);
		clr.setMinAmt(Integer.parseInt(clrJson.getString("minAmt"))*100);
		clr.setCommisionRatio(Integer.parseInt(clrJson.getString("commisionRatio")));
		clr.setVisibility(Integer.parseInt(clrJson.getString("visibility")));
		clr.setTitle("如意彩合买");
		String desc =clrJson.getString("desc");
		clr.setDesc(desc);
		orderRequest.setCaseLotRequest(clr);
		logger.info("LotteryUtil/toCaseLotOrder普通合买参数:"
				+ JSONObject.fromObject(orderRequest));
		String body = URLEncoder.encode(JSONObject.fromObject(orderRequest).toString(),"UTF-8");
		result = getToCaseLotOrder(body);
		JSONObject resultObject = JSONObject.fromObject(result);
		String errorCode = resultObject.getString("errorCode");
		if("0".equals(errorCode)){
			return "恭喜，您的合买投注方案已提交成功！";
		}else if("500011".equals(errorCode)){
			return "抱歉，您的合买投注方案失败，方案已过期！";
		}else{
			return "抱歉，您的合买投注方案失败，请稍后再试！";
		}
	}
	/**
	 * 竞彩合买
	 * @param userNo
	 * @param clrJson
	 * @param lotNo
	 * @param playType
	 * @param betCode
	 * @param beiShu
	 * @param oneAmount
	 * @param amount
	 * @param channel
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String toJcCaseLotOrder(String userNo, JSONObject clrJson,
			String lotNo, String playType,String betCode, String beiShu,
			String oneAmount, String amount,String channel) throws UnsupportedEncodingException{
		logger.info("LotteryUtil/toJcCaseLotOrder竞彩普通合买参数userNo:" + userNo
				+ ",clrJson:" + clrJson.toString() + ",lotNo:" + lotNo + ",playType:" + playType
				 + ",betCode:" + betCode
				+ ",beiShu:" + beiShu + ",oneAmount:" + oneAmount + ",amount:"
				+ amount + ",channel:" + channel);
		String result = "";
		String memo = getMemo(lotNo, playType);
		if (oneAmount == null || "".equals(oneAmount))
			oneAmount = "2";
		if (beiShu == null || "".equals(beiShu))
			beiShu = "1";
		int oneAmountInt = Integer.parseInt(oneAmount) * 100;
		long amountLong = Long.parseLong(amount) * 100;
		
		BigDecimal lotsType = new BigDecimal(getDrawWayAndLotsType(lotNo, betCode).get("lotsType"));
		List<BetRequest> betRequests = getJcBetRequests(betCode,beiShu);
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setLotno(lotNo);
		orderRequest.setAmt(new BigDecimal(amountLong));
		orderRequest.setUserno(userNo);
		orderRequest.setLotmulti(new BigDecimal(beiShu));
		orderRequest.setBuyuserno(userNo);
		orderRequest.setChannel(channel);
		orderRequest.setSubchannel("00092493");
		orderRequest.setPaytype(new BigDecimal(1));
		orderRequest.setMemo(memo);
		orderRequest.setLotsType(lotsType);//	投注类型 ，合买使用
		orderRequest.setBetRequests(betRequests);
		orderRequest.setSubscribeRequests(null);
		orderRequest.setOneamount(new BigDecimal(oneAmountInt));
		orderRequest.setBettype("3");//zhuihao(0), taocan(1), touzhu(2),hemai(3),zengsong(4),zengsong_nosms(5);
		CaseLotRequest clr = new CaseLotRequest();
		clr.setTotalAmt(orderRequest.getAmt().longValue());//合买总金额取投注总金额
		clr.setStarter(userNo);
		clr.setBuyAmt(Long.parseLong(clrJson.getString("buyAmt"))*100);
		clr.setSafeAmt(Long.parseLong(clrJson.getString("safeAmt"))*100);
		clr.setMinAmt(Long.parseLong(clrJson.getString("minAmt"))*100);
		clr.setCommisionRatio(Integer.parseInt(clrJson.getString("commisionRatio")));
		clr.setVisibility(Integer.parseInt(clrJson.getString("visibility")));
		clr.setTitle("如意彩合买");
		String desc =clrJson.getString("desc");
		clr.setDesc(desc);
		orderRequest.setCaseLotRequest(clr);
		logger.info("LotteryUtil/toJcCaseLotOrder竞彩普通合买参数:"
				+ JSONObject.fromObject(orderRequest));
		String body = URLEncoder.encode(JSONObject.fromObject(orderRequest).toString(),"UTF-8");
		result = getToCaseLotOrder(body);
		JSONObject resultObject = JSONObject.fromObject(result);
		String errorCode = resultObject.getString("errorCode");
		if("0".equals(errorCode)){
			return "恭喜，您的合买投注方案已提交成功！";
		}else if("500011".equals(errorCode)){
			return "抱歉，您的合买投注方案失败，方案已过期！";
		}else{
			return "抱歉，您的合买投注方案失败，请稍后再试！";
		}
	}
	public String betCaselot(String userNo,long buyAmount,long buySafeAmount,String caseLotId,String channel){
		String url = lottery + "caselot/betCaselot";
		String parameter = "userno=" + userNo+ "&amt="+buyAmount+ "&safeAmt="+buySafeAmount
				+"&caseId="+caseLotId+"&channel="+ channel;
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("LotteryUtil/betCaselot参与合买返回：" + result);
		return result;
	}
	public String toBetCaseLot(String userNo,long buyAmount,long buySafeAmount,String caseLotId,String channel){
		String result = betCaselot(userNo, buyAmount, buySafeAmount, caseLotId, channel);
		JSONObject resultJsonObject = JSONObject.fromObject(result);
		String errorCode = resultJsonObject.getString("errorCode");
		if("0099".equals(errorCode)){
			return "参与合买失败";
		}else if("0".equals(errorCode)){
			return "参与合买成功";
		}else if("500001".equals(errorCode)){
			return "合买方案不存在";
		}else if("500011".equals(errorCode)){
			return "合买已过截止时间";
		}else if("500012".equals(errorCode)){
			return "该合买订单不存在";
		}else if("20100706".equals(errorCode)){
			return "该期已经过期";
		}else{
			return "参与合买失败";
		}
	}
	public String cancelCaseLot(String userNo, String caseLotId){
		String url = lottery + "caselot/cancelCaselot";
		String parameter = "userno=" + userNo+"&caseId="+caseLotId;
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("LotteryUtil/cancelCaseLot合买撤单返回：" + result);
		return result;
	}
	public String toCancelCaseLot(String userNo, String caseLotId){
		String result = cancelCaseLot(userNo, caseLotId);
		JSONObject resultJsonObject = JSONObject.fromObject(result);
		String errorCode = resultJsonObject.getString("errorCode");
		if ("500007".equals(errorCode)) {
			return "进度已达到50%或者大于50%，超出可撤销范围";
		}else if("500011".equals(errorCode)) {
			return "合买已过截止时间";
		}else if("500012".equals(errorCode)) {
			return "该合买订单不存在";
		}else if("0".equals(errorCode)) {
			return "合买撤单成功";
		}else{
			return "合买撤单失败";
		}
	}
	public String cancelCaseLotBuy(String userNo, String caseLotId){
		String url = lottery + "caselot/cancelCaselotbuy";
		String parameter = "userno=" + userNo+"&caseId="+caseLotId;
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("LotteryUtil/cancelCaseLotBuy合买撤资返回：" + result);
		return result;
	}
	public String toCancelCaseLotBuy(String userNo, String caseLotId){
		String result = cancelCaseLotBuy(userNo, caseLotId);
		JSONObject resultJsonObject = JSONObject.fromObject(result);
		String errorCode = resultJsonObject.getString("errorCode");
		if ("500010".equals(errorCode)) {
			return "合买进度超出可撤资范围,撤资失败";
		}else if("500011".equals(errorCode)) {
			return "合买已过截止时间";
		}else if("500012".equals(errorCode)) {
			return "该合买订单不存在";
		}else if("0".equals(errorCode)) {
			return "合买撤资成功";
		}else{
			return "合买撤资失败";
		}
	}
	/**
	 * 得到betRequests
	 * @param lotNo
	 * @param betCode
	 * @param amountInt
	 * @param beiShuInt
	 * @param addNumberInt
	 * @return
	 */
	public static List<BetRequest> getBetRequests(String lotNo,String betCode,long amountLong,int beiShuInt,int addNumberInt){
		List<BetRequest> betRequests = new ArrayList<BetRequest>();

		// 排除以^分隔的注码：2012050102030405^06060708091000^050102030405^
		if (Constants.LOTNO_FC3D.equals(lotNo) && betCode.startsWith("20")) {
			BetRequest betRequest = new BetRequest();
			betRequest.setBetcode(betCode);
			betRequest.setAmt(new BigDecimal(amountLong / beiShuInt
					/ addNumberInt));
			betRequests.add(betRequest);
		} else if (betCode.indexOf("^") > -1) {
			String[] codes = betCode.split("\\^");
			for (String code : codes) {
				BetRequest betRequest = new BetRequest();
				betRequest.setBetcode(code + "^");
				betRequest.setAmt(new BigDecimal(amountLong / beiShuInt
						/ addNumberInt / codes.length));
				betRequests.add(betRequest);

			}
		} else if (betCode.indexOf(";") > -1) {
			String[] codes = betCode.split("\\;");
			for (String code : codes) {
				BetRequest betRequest = new BetRequest();
				betRequest.setBetcode(code);
				betRequest.setAmt(new BigDecimal(amountLong / beiShuInt
						/ addNumberInt / codes.length));
				betRequests.add(betRequest);

			}
		} else {
			BetRequest betRequest = new BetRequest();
			betRequest.setBetcode(betCode);
			betRequest.setAmt(new BigDecimal(amountLong / beiShuInt
					/ addNumberInt));
			betRequests.add(betRequest);

		}
		return betRequests;
	}
	/**
	 * 得到竞彩BetRequests
	 * @param betCode
	 * @return
	 */
	public static List<BetRequest> getJcBetRequests(String betCode,String beiShu){
		List<BetRequest> betRequests = new ArrayList<BetRequest>();
		int beiShuInt = Integer.parseInt(beiShu);
		if(betCode.indexOf(";")!=-1){
			String[] betcodes = betCode.split("\\;");
			for(int i=0;i<betcodes.length;i++){
				String str[] = betcodes[i].split("\\_");
				BetRequest betRequest = new BetRequest();
				betRequest.setBetcode(str[0]);
				
				betRequest.setAmt(new BigDecimal(Long.parseLong(str[1])*100/beiShuInt));
				betRequests.add(betRequest);
			}
		}else{
			BetRequest betRequest = new BetRequest();
			String str[] = betCode.split("\\_");
			betRequest.setBetcode(str[0]);
			
			betRequest.setAmt(new BigDecimal(Long.parseLong(str[1])*100/beiShuInt));
			betRequests.add(betRequest);
		}
		return betRequests;
	}
	/**
	 * 得到页面显示投注剩余时间和开奖信息
	 * @param modelAndView
	 */
	public void getModelAndView(ModelAndView modelAndView,String lotNo){
		List<WinInfo> winInfos = selectAllUtil.selectTwininfoByLotno(lotNo, "5");
//		String headLine = selectAllUtil.selectLotteryTermAndEndtime(lotNo);
		String batchCode = selectAllUtil.selectLotteryBatchCode(lotNo);
		Map<String, String> map = selectAllUtil.selectEndTime(lotNo, batchCode);
		String betEndTime = "";
		String heMaiEndTime = "";
		if(map!=null){
			betEndTime = map.get("betEndTime");
			heMaiEndTime = map.get("heMaiEndTime");
		}
		String remainingTime= selectAllUtil.selectLotteryRemainingTime(lotNo, batchCode);

//		String endTime = selectAllUtil.selectLotteryStopTime(lotNo);

		modelAndView.addObject("beiShu", "1");
		modelAndView.addObject("addNumber", "1");
		modelAndView.addObject("winInfos", winInfos);
//		modelAndView.addObject("headLine", headLine);
		modelAndView.addObject("betEndTime", betEndTime);
		modelAndView.addObject("heMaiEndTime", heMaiEndTime);
		modelAndView.addObject("remainingTime", remainingTime);
		modelAndView.addObject("batchCode", batchCode);
	}
	/**
	 * 得到memo
	 * @param lotNo
	 * @return
	 */
	public String getMemo(String lotNo,String playType){
		return getLotNameByLotNo(lotNo)+playType;
	}
	/**
	 * 得到投注DrawWayAndLotsType
	 * @param lotNo
	 * @param betCode
	 * @return
	 */
	public static Map<String, String> getDrawWayAndLotsType(String lotNo,String betCode){
		Map<String, String> drawWayAndLotsType = new HashMap<String, String>();
		String drawWay = "";// 投注方式0-单式，1-复式，2-胆拖，3-单式上传），追号时使用
		String lotsType = "";// 订单类型0-单式上传，1-复式，2-胆拖，3-多方案 合买
		String drawWay_DS = "0";
		String drawWay_FS = "1";
		String drawWay_DT = "2";
//		String drawWay_DSSC = "3";
		String lotsType_DSSC = "0";
		String lotsType_FS = "1";
		String lotsType_DT = "2";
//		String lotsType_DFA = "3";
		
		if(Constants.LOTNO_QLC.equals(lotNo)){//七乐彩
			String str = betCode.substring(0, 2);
			if("00".equals(str)){//单式
				drawWay = drawWay_DS;
				lotsType = lotsType_DSSC;
			}
			if("10".equals(str)){//复式
				drawWay = drawWay_FS;
				lotsType = lotsType_FS;
			}
			if("20".equals(str)){//胆拖
				drawWay = drawWay_DT;
				lotsType = lotsType_DT;
			}
		}else if(Constants.LOTNO_FC3D.equals(lotNo)){//福彩3D
			String str = betCode.substring(0, 2);
			if ("00".equals(str)) {//单选单式
				drawWay = drawWay_DS;
				lotsType = lotsType_DSSC;
			}
			if ("01".equals(str)) {//组三单式
				drawWay = drawWay_DS;
				lotsType = lotsType_DSSC;
			}
			if ("02".equals(str)) {//组六单式
				drawWay = drawWay_DS;
				lotsType = lotsType_DSSC;
			}
			if ("20".equals(str)) {//单选按位包号，可实现直选复式
				drawWay = drawWay_FS;
				lotsType = lotsType_FS;
			}
			if ("31".equals(str)) {//组三复式
				drawWay = drawWay_FS;
				lotsType = lotsType_FS;
			}
			if ("32".equals(str)) {//组六复式
				drawWay = drawWay_FS;
				lotsType = lotsType_FS;
			}
			if ("10".equals(str)) {//单选包点
				drawWay = drawWay_DS;
				lotsType = lotsType_DSSC;
			}
			if ("11".equals(str)) {//组三包点
				drawWay = drawWay_DS;
				lotsType = lotsType_DSSC;
			}
			if ("12".equals(str)) {//组六包点
				drawWay = drawWay_DS;
				lotsType = lotsType_DSSC;
			}
			if ("54".equals(str)) {//胆拖
				drawWay = drawWay_DT;
				lotsType = lotsType_DT;
			}
			if ("34".equals(str)) {//单选单复式
				drawWay = drawWay_FS;
				lotsType = lotsType_FS;
			}
		}else if(Constants.LOTNO_SSQ.equals(lotNo)){//双色球
			String str = betCode.substring(0, 2);
			if ("00".equals(str)) {//单式：红球单式，篮球单式
				drawWay = drawWay_DS;
				lotsType = lotsType_DSSC;
			}
			if ("10".equals(str)) {//红复式：红球复式，篮球单式
				drawWay = drawWay_FS;
				lotsType = lotsType_FS;
			}
			if ("20".equals(str)) {//蓝复式：红球单式，蓝球复式
				drawWay = drawWay_FS;
				lotsType = lotsType_FS;
			}
			if ("30".equals(str)) {//双复式：红球复式，蓝球复式
				drawWay = drawWay_FS;
				lotsType = lotsType_FS;
			}
			if ("40".equals(str)) {//红胆蓝单式：红球胆，蓝球单式
				drawWay = drawWay_DT;
				lotsType = lotsType_DT;
			}
			if ("50".equals(str)) {//红胆蓝复式：红球胆，蓝球复式
				drawWay = drawWay_DT;
				lotsType = lotsType_DT;
			}
		}else if(Constants.LOTNO_DLT.equals(lotNo)){//大乐透
			if (betCode.indexOf("-") != -1) {
				if (betCode.indexOf("$") != -1) {//胆拖
					drawWay = drawWay_DT;
					lotsType = lotsType_DT;
				} else {
					String str[] = betCode.split("\\-");
					if (str[0].trim().length() == 14
							&& str[1].trim().length() == 5) {//单式
						drawWay = drawWay_DS;
						lotsType = lotsType_DSSC;
					} else {//复式
						drawWay = drawWay_FS;
						lotsType = lotsType_FS;
					}
				}
			} else {//生肖乐
				if (betCode.trim().length() == 5) {//单式
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				} else {//复式
					drawWay = drawWay_FS;
					lotsType = lotsType_FS;
				}
			}
			
		}else if(Constants.LOTNO_PL3.equals(lotNo)){//排列三
			String str[] = betCode.split("\\|");
			if ("1".equals(str[0])) {//直选
				if (str[1].length() == 5) {//单式
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				} else {//复式
					drawWay = drawWay_FS;
					lotsType = lotsType_FS;
				}
			}
			if ("6".equals(str[0])) {//组选
				drawWay = drawWay_DS;
				lotsType = lotsType_DSSC;
			}
			if ("S1".equals(str[0])) {//直选和值
				if (str[1].indexOf(",") != -1) {//复式
					drawWay = drawWay_FS;
					lotsType = lotsType_FS;
				} else {//单式
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				}
			}
			if ("S9".equals(str[0])) {//组选和值
				if (str[1].indexOf(",") != -1) {//复式
					drawWay = drawWay_FS;
					lotsType = lotsType_FS;
				} else {//单式
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				}
			}
			if ("S3".equals(str[0])) {//组三和值
				if (str[1].indexOf(",") != -1) {//复式
					drawWay = drawWay_FS;
					lotsType = lotsType_FS;
				} else {//单式
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				}
			}
			if ("S6".equals(str[0])) {//组六和值
				if (str[1].indexOf(",") != -1) {//复式
					drawWay = drawWay_FS;
					lotsType = lotsType_FS;
				} else {//单式
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				}
			}
			if ("F3".equals(str[0])) {//组三包号
				drawWay = drawWay_FS;
				lotsType = lotsType_FS;
			}
			if ("F6".equals(str[0])) {//组六包号
				drawWay = drawWay_FS;
				lotsType = lotsType_FS;
			}
			if ("Z3".equals(str[0])) {//组三复式
				drawWay = drawWay_FS;
				lotsType = lotsType_FS;
			}
			if ("Z6".equals(str[0])) {//组六复式
				drawWay = drawWay_FS;
				lotsType = lotsType_FS;
			}
		}else if(Constants.LOTNO_ZC_SFC_14C.equals(lotNo)){//足彩胜负彩14场
			if (betCode.indexOf(",") == -1) {
				drawWay = drawWay_DS;
				lotsType = lotsType_DSSC;
			} else {
				String str[] = betCode.split("\\,");
				for (int i = 0; i < str.length; i++) {
					if (str[i].trim().length() > 1) {
						drawWay = drawWay_FS;
						lotsType = lotsType_FS;
						break;
					}
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				}
			}
		}else if(Constants.LOTNO_ZC_R9C.equals(lotNo)){//足彩任九场
			if (betCode.indexOf("$") != -1) {
				drawWay = drawWay_DT;
				lotsType = lotsType_DT;
			} else {
				if (betCode.indexOf(",") == -1) {
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				} else {
					String str[] = betCode.split("\\,");
					for (int i = 0; i < str.length; i++) {
						if (str[i].trim().length() > 1) {
							drawWay = drawWay_FS;
							lotsType = lotsType_FS;
							break;
						}
						drawWay = drawWay_DS;
						lotsType = lotsType_DSSC;
					}
				}
			}
		}else if(Constants.LOTNO_ZC_4C_JQC.equals(lotNo)){//足彩四场进球彩
			if (betCode.indexOf(",") == -1) {
				drawWay = drawWay_DS;
				lotsType = lotsType_DSSC;
			} else {
				String str[] = betCode.split("\\,");
				for (int i = 0; i < str.length; i++) {
					if (str[i].trim().length() > 1) {
						drawWay = drawWay_FS;
						lotsType = lotsType_FS;
						break;
					}
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				}
			}
		}else if(Constants.LOTNO_ZC_6C_BQC.equals(lotNo)){//足彩6场半全场
			if (betCode.indexOf(",") == -1) {
				drawWay = drawWay_DS;
				lotsType = lotsType_DSSC;
			} else {
				String str[] = betCode.split("\\,");
				for (int i = 0; i < str.length; i++) {
					if (str[i].trim().length() > 1) {
						drawWay = drawWay_FS;
						lotsType = lotsType_FS;
						break;
					}
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				}
			}
		}else if(Constants.LOTNO_SSC.equals(lotNo)){//时时彩
			String str[] = betCode.split("\\|");
			String code = str[1];
			if (betCode.indexOf(";") != -1) {
				String str1[] = str[1].split("\\;");
				code = str1[0];
			}

			if ("5D".equals(str[0])) {//五星
				if (code.trim().length() == 9) {//单式
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				} else {//复式
					drawWay = drawWay_FS;
					lotsType = lotsType_FS;
				}

			}
			if ("3D".equals(str[0])) {//三星
				if (code.trim().length() == 9) {//单式
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				} else {//复式
					drawWay = drawWay_FS;
					lotsType = lotsType_FS;
				}
			}
			if ("2D".equals(str[0])) {//二星
				if (code.trim().length() == 9) {//单式
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				} else {//复式
					drawWay = drawWay_FS;
					lotsType = lotsType_FS;
				}
			}
			if ("1D".equals(str[0])) {//一星
				if (code.trim().length() == 9) {//单式
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				} else {//复式
					drawWay = drawWay_FS;
					lotsType = lotsType_FS;
				}
			}
			if ("5F".equals(str[0])) {//五星复选
				drawWay = drawWay_FS;
				lotsType = lotsType_FS;
			}
			if ("5T".equals(str[0])) {//五星通选
				if (code.trim().length() == 9) {//单式
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				} else {//复式
					drawWay = drawWay_FS;
					lotsType = lotsType_FS;
				}
			}
			if ("3F".equals(str[0])) {//三星复选
				drawWay = drawWay_FS;
				lotsType = lotsType_FS;
			}
			if ("2F".equals(str[0])) {//二星复选
				drawWay = drawWay_FS;
				lotsType = lotsType_FS;
			}
			if ("S2".equals(str[0])) {//二星组选和值
				if (code.trim().length() == 1) {//单式
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				} else {//复式
					drawWay = drawWay_FS;
					lotsType = lotsType_FS;
				}
			}
			if ("DD".equals(str[0])) {//大小单双
				drawWay = drawWay_DS;
				lotsType = lotsType_DSSC;
			}
			if ("Z2".equals(str[0])) {
				if (code.trim().length() == 3) {//单式
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				} else {//复式
					drawWay = drawWay_FS;
					lotsType = lotsType_FS;
				}
			}
			if ("F2".equals(str[0])) {//二星组选复式
				drawWay = drawWay_FS;
				lotsType = lotsType_FS;
			}
			if ("3".equals(str[0])) {//组三单式
				drawWay = drawWay_DS;
				lotsType = lotsType_DSSC;
			}
			if ("6".equals(str[0])) {//组六单式
				drawWay = drawWay_DS;
				lotsType = lotsType_DSSC;
			}
			if ("Z3F".equals(str[0])) {//组三复式
				drawWay = drawWay_FS;
				lotsType = lotsType_FS;
			}
			if ("Z6F".equals(str[0])) {//组六复式
				drawWay = drawWay_FS;
				lotsType = lotsType_FS;
			}
		}else if(Constants.LOTNO_QXC.equals(lotNo)){//七星彩
			if (betCode.indexOf(",") == -1) {//单式
				drawWay = drawWay_DS;
				lotsType = lotsType_DSSC;
			} else {
				String str[] = betCode.split("\\,");
				for (int i = 0; i < str.length; i++) {
					if (str[i].trim().length() > 1) {
						drawWay = drawWay_FS;
						lotsType = lotsType_FS;
						break;
					}
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				}
			}
		}else if(Constants.LOTNO_DLC_JX11X5.equals(lotNo)){//江西11选5//多乐彩
			String str[] = betCode.split("\\|");
			if ("R1".equals(str[0])) {//任选一
				if (str[1].trim().length() == 2) {//单式
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				} else {//复式
					drawWay = drawWay_FS;
					lotsType = lotsType_FS;
				}
			}
			if ("R2".equals(str[0])) {//任选二
				if (str[1].indexOf("$") != -1) {//胆拖
					drawWay = drawWay_DT;
					lotsType = lotsType_DT;
				} else {
					if (str[1].trim().length() == 5) {//单式
						drawWay = drawWay_DS;
						lotsType = lotsType_DSSC;
					} else {//复式
						drawWay = drawWay_FS;
						lotsType = lotsType_FS;
					}
				}
			}
			if ("R3".equals(str[0])) {//任选三
				if (str[1].indexOf("$") != -1) {//胆拖
					drawWay = drawWay_DT;
					lotsType = lotsType_DT;
				} else {
					if (str[1].trim().length() == 8) {//单式
						drawWay = drawWay_DS;
						lotsType = lotsType_DSSC;
					} else {//复式
						drawWay = drawWay_FS;
						lotsType = lotsType_FS;
					}
				}
			}
			if ("R4".equals(str[0])) {//任选四
				if (str[1].indexOf("$") != -1) {//胆拖
					drawWay = drawWay_DT;
					lotsType = lotsType_DT;
				} else {
					if (str[1].trim().length() == 11) {//单式
						drawWay = drawWay_DS;
						lotsType = lotsType_DSSC;
					} else {//复式
						drawWay = drawWay_FS;
						lotsType = lotsType_FS;
					}
				}
			}
			if ("R5".equals(str[0])) {//任选五
				if (str[1].indexOf("$") != -1) {//胆拖
					drawWay = drawWay_DT;
					lotsType = lotsType_DT;
				} else {
					if (str[1].trim().length() == 14) {//单式
						drawWay = drawWay_DS;
						lotsType = lotsType_DSSC;
					} else {//复式
						drawWay = drawWay_FS;
						lotsType = lotsType_FS;
					}
				}
			}
			if ("R6".equals(str[0])) {//任选六
				if (str[1].indexOf("$") != -1) {//胆拖
					drawWay = drawWay_DT;
					lotsType = lotsType_DT;
				} else {
					if (str[1].trim().length() == 17) {//单式
						drawWay = drawWay_DS;
						lotsType = lotsType_DSSC;
					} else {//复式
						drawWay = drawWay_FS;
						lotsType = lotsType_FS;
					}
				}
			}
			if ("R7".equals(str[0])) {//任选七
				if (str[1].indexOf("$") != -1) {//胆拖
					drawWay = drawWay_DT;
					lotsType = lotsType_DT;
				} else {
					if (str[1].trim().length() == 20) {//单式
						drawWay = drawWay_DS;
						lotsType = lotsType_DSSC;
					} else {//复式
						drawWay = drawWay_FS;
						lotsType = lotsType_FS;
					}
				}
			}
			if ("R8".equals(str[0])) {//任选八
				if (str[1].indexOf("$") != -1) {//胆拖
					drawWay = drawWay_DT;
					lotsType = lotsType_DT;
				} else {
					if (str[1].trim().length() == 23) {//单式
						drawWay = drawWay_DS;
						lotsType = lotsType_DSSC;
					} else {//复式
						drawWay = drawWay_FS;
						lotsType = lotsType_FS;
					}
				}
			}
			if ("Q2".equals(str[0])) {//选前二直选
				if (str[1].trim().length() == 5) {//单式
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				} else {//复式
					drawWay = drawWay_FS;
					lotsType = lotsType_FS;
				}
			}
			if ("Q3".equals(str[0])) {//选前三直选
				if (str[1].trim().length() == 8) {//单式
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				} else {//复式
					drawWay = drawWay_FS;
					lotsType = lotsType_FS;
				}
			}
			if ("Z2".equals(str[0])) {//选前二组选
				if (str[1].indexOf("$") != -1) {//胆拖
					drawWay = drawWay_DT;
					lotsType = lotsType_DT;
				} else {
					if (str[1].trim().length() == 5) {//单式
						drawWay = drawWay_DS;
						lotsType = lotsType_DSSC;
					} else {//复式
						drawWay = drawWay_FS;
						lotsType = lotsType_FS;
					}
				}
			}
			if ("Z3".equals(str[0])) {//选前三组选
				if (str[1].indexOf("$") != -1) {//胆拖
					drawWay = drawWay_DT;
					lotsType = lotsType_DT;
				} else {
					if (str[1].length() == 8) {//单式
						drawWay = drawWay_DS;
						lotsType = lotsType_DSSC;
					} else {//复式
						drawWay = drawWay_FS;
						lotsType = lotsType_FS;
					}
				}
			}
		}else if(Constants.LOTNO_PL5.equals(lotNo)){//排列五
			if (betCode.indexOf(",") == -1) {
				drawWay = drawWay_DS;
				lotsType = lotsType_DSSC;
			} else {
				String str[] = betCode.split("\\,");
				for (int i = 0; i < str.length; i++) {
					if (str[i].trim().length() > 1) {
						drawWay = drawWay_FS;
						lotsType = lotsType_FS;
						break;
					}
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				}
			}
		}else if(Constants.LOTNO_11YDJ_SD11X5.equals(lotNo)){//十一运夺金//山东11选5
			String[] marks = betCode.split("\\@");
			String mark = marks[1].substring(0, 1);
			if (mark.equals("*")) {//复式
				drawWay = drawWay_FS;
				lotsType = lotsType_FS;
			} else if (!mark.equals("*") && betCode.contains("*")) {//胆拖
				drawWay = drawWay_DT;
				lotsType = lotsType_DT;
			} else if (!mark.equals("*")) {//单式
				drawWay = drawWay_DS;
				lotsType = lotsType_DSSC;
			}
		}else if(Constants.LOTNO_22X5.equals(lotNo)){//22选5
			String str[] = betCode.split("\\@");
			if("0".equals(str[0])){//单式
				drawWay = drawWay_DS;
				lotsType = lotsType_DSSC;
			}
			if("1".equals(str[0])){//复式
				drawWay = drawWay_FS;
				lotsType = lotsType_FS;
			}
			if("2".equals(str[0])){//胆拖
				drawWay = drawWay_DT;
				lotsType = lotsType_DT;
			}
		}else if(Constants.LOTNO_GD11X5.equals(lotNo)){//广东11选5
			String str[] = betCode.split("\\|");
			if("S".equals(str[0])){//单式
				drawWay = drawWay_DS;
				lotsType = lotsType_DSSC;
			}
			if("M".equals(str[0])||"P".equals(str[0])){//复式
				drawWay = drawWay_FS;
				lotsType = lotsType_FS;
			}
			if("D".equals(str[0])){//胆拖
				drawWay = drawWay_DT;
				lotsType = lotsType_DT;
			}
		}else if(Constants.LOTNO_GDKL10F.equals(lotNo)){//广东快乐十分
			String str[] = betCode.split("\\|");
			if("S".equals(str[0])){//单式
				drawWay = drawWay_DS;
				lotsType = lotsType_DSSC;
			}
			if("M".equals(str[0])||"P".equals(str[0])){//复式
				drawWay = drawWay_FS;
				lotsType = lotsType_FS;
			}
			if("D".equals(str[0])){//胆拖
				drawWay = drawWay_DT;
				lotsType = lotsType_DT;
			}
		}else if(Constants.LOTNO_JC_LQ_SF.equals(lotNo)){//竞彩篮球胜负
			String[] betcodes = betCode.split("\\^");
			for (int i = 0; i < betcodes.length - 1; i++) {
				String zhuma[] = betcodes[i].split("\\|");
				if (zhuma[3].length() > 1) {
					drawWay = drawWay_FS;
					lotsType = lotsType_FS;
					break;
				} else {
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				}
			}
		}else if(Constants.LOTNO_JC_LQ_RFSF.equals(lotNo)){//竞彩蓝球让分胜负
			String[] betcodes = betCode.split("\\^");
			for (int i = 0; i < betcodes.length - 1; i++) {
				String zhuma[] = betcodes[i].split("\\|");
				if (zhuma[3].length() > 1) {
					drawWay = drawWay_FS;
					lotsType = lotsType_FS;
					break;
				} else {
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				}
			}
		}else if(Constants.LOTNO_JC_LQ_SFC.equals(lotNo)){//竞彩篮球胜分差
			String[] betcodes = betCode.split("\\^");
			for (int i = 0; i < betcodes.length - 1; i++) {
				String zhuma[] = betcodes[i].split("\\|");
				if (zhuma[3].length() > 1) {
					drawWay = drawWay_FS;
					lotsType = lotsType_FS;
					break;
				} else {
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				}
			}
		}else if(Constants.LOTNO_JC_LQ_DXF.equals(lotNo)){//竞彩篮球大小分
			String[] betcodes = betCode.split("\\^");
			for (int i = 0; i < betcodes.length - 1; i++) {
				String zhuma[] = betcodes[i].split("\\|");
				if (zhuma[3].length() > 1) {
					drawWay = drawWay_FS;
					lotsType = lotsType_FS;
					break;
				} else {
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				}
			}
		}else if(Constants.LOTNO_JC_ZQ_SPF.equals(lotNo)){//竞彩足球胜平负
			String[] betcodes = betCode.split("\\^");
			for (int i = 0; i < betcodes.length - 1; i++) {
				String zhuma[] = betcodes[i].split("\\|");
				if (zhuma[3].length() > 1) {
					drawWay = drawWay_FS;
					lotsType = lotsType_FS;
					break;
				} else {
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				}
			}
		}else if(Constants.LOTNO_JC_ZQ_RQSPF.equals(lotNo)){//竞彩足球让球胜平负
			String[] betcodes = betCode.split("\\^");
			for (int i = 0; i < betcodes.length - 1; i++) {
				String zhuma[] = betcodes[i].split("\\|");
				if (zhuma[3].length() > 1) {
					drawWay = drawWay_FS;
					lotsType = lotsType_FS;
					break;
				} else {
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				}
			}
		}else if(Constants.LOTNO_JC_ZQ_BF.equals(lotNo)){//竞彩足球比分
			String[] betcodes = betCode.split("\\^");
			for (int i = 0; i < betcodes.length - 1; i++) {
				String zhuma[] = betcodes[i].split("\\|");
				if (zhuma[3].length() > 1) {
					drawWay = drawWay_FS;
					lotsType = lotsType_FS;
					break;
				} else {
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				}
			}
		}else if(Constants.LOTNO_JC_ZQ_ZJQ.equals(lotNo)){//竞彩足球总进球
			String[] betcodes = betCode.split("\\^");
			for (int i = 0; i < betcodes.length - 1; i++) {
				String zhuma[] = betcodes[i].split("\\|");
				if (zhuma[3].length() > 1) {
					drawWay = drawWay_FS;
					lotsType = lotsType_FS;
					break;
				} else {
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				}
			}
		}else if(Constants.LOTNO_JC_ZQ_BCSPF.equals(lotNo)){//竞彩足球半场胜平负
			String[] betcodes = betCode.split("\\^");
			for (int i = 0; i < betcodes.length - 1; i++) {
				String zhuma[] = betcodes[i].split("\\|");
				if (zhuma[3].length() > 1) {
					drawWay = drawWay_FS;
					lotsType = lotsType_FS;
					break;
				} else {
					drawWay = drawWay_DS;
					lotsType = lotsType_DSSC;
				}
			}
		}
		drawWayAndLotsType.put("drawWay", drawWay);
		drawWayAndLotsType.put("lotsType", lotsType);
		return drawWayAndLotsType;
	}
	/**
	 * 得到竞彩投注的lotNo
	 * @param lotNo
	 * @return
	 */
	public static String getJCBetLotNo(String lotNo){
		if("BSK001".equals(lotNo)) return Constants.LOTNO_JC_LQ_SF;//竞彩篮球胜负
		if("BSK002".equals(lotNo)) return Constants.LOTNO_JC_LQ_RFSF;//竞彩篮球让分胜负
		if("BSK003".equals(lotNo)) return Constants.LOTNO_JC_LQ_SFC;//竞彩篮球胜分差
		if("BSK004".equals(lotNo)) return Constants.LOTNO_JC_LQ_DXF;//竞彩篮球大小分
		if("FT001".equals(lotNo)) return Constants.LOTNO_JC_ZQ_SPF;//竞彩足球胜平负
		if("FT002".equals(lotNo)) return Constants.LOTNO_JC_ZQ_BF;//竞彩足球比分
		if("FT003".equals(lotNo)) return Constants.LOTNO_JC_ZQ_ZJQ;//竞彩足球总进球
		if("FT004".equals(lotNo)) return Constants.LOTNO_JC_ZQ_BCSPF;//竞彩足球半场胜平负
		return lotNo;
	}
}
