package com.ruyicai.wap.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.SelectAllUtil;
import com.ruyicai.wap.util.UserInfoUtil;
import com.ruyicai.wap.util.validate.ValidateUserInfoUtil;
import com.ruyicai.wap.vo.DNABand;
import com.ruyicai.wap.vo.LastestPayChannel;
import com.ruyicai.wap.vo.SupportTopPayChannel;
import com.ruyicai.wap.vo.UserInfo;

@Controller
@RequestMapping("/chargeCenter")
public class ChargeCenterController {
	Logger logger = Logger.getLogger(ChargeCenterController.class);
	@Value("${subchannel}")
	String subchannel;
	@Value("${channel}")
	String channel;
	@Autowired
	SelectAllUtil selectAllUtil;
	@Autowired
	UserInfoUtil userInfoUtil;
	/**
	 * 手机卡充值
	 * @param cardType
	 * @param cardNo
	 * @param cardPassWord
	 * @param amount
	 * @param totalAmount
	 * @param checkBox
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/nineteenPayCardCharge.do",method=RequestMethod.POST)
	public ModelAndView nineteenPayCardCharge(
			@RequestParam(value="cardType",defaultValue="")String cardType,
			@RequestParam(value="cardNo",defaultValue="")String cardNo,
			@RequestParam(value="cardPassWord",defaultValue="")String cardPassWord,
			@RequestParam(value="amount",defaultValue="")String amount,
			@RequestParam(value="totalAmount",defaultValue="")String totalAmount,
			@RequestParam(value="ladderPresentFlag",defaultValue="1")String ladderPresentFlag,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		logger.info("ChargeCenterController/nineteenPayCardCharge手机卡充值参数cardType:"+cardType+",cardNo:"+cardNo
				+",cardPassWord:"+cardPassWord+",amount:"+amount+",totalAmount:"+totalAmount+",ladderPresentFlag:"+ladderPresentFlag);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo.getUserNo();
		String validateResult = ValidateUserInfoUtil.validateNineteenPayCardCharge(cardNo, cardPassWord, amount, totalAmount);
		if(!"".equals(validateResult)&&validateResult!=null){
			modelAndView.addObject("cardType", cardType);
			modelAndView.addObject("cardNo", cardNo);
			modelAndView.addObject("cardPassWord", cardPassWord);
			modelAndView.addObject("amount", amount);
			modelAndView.addObject("totalAmount", totalAmount);
			modelAndView.addObject("ladderPresentFlag", ladderPresentFlag);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("chargeFail");
			logger.info("ChargeCenterController/nineteenPayCardCharge手机卡充值参数验证失败返回validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("ChargeCenterController/nineteenPayCardCharge手机卡充值参数验证通过！");
		JSONObject jsonString = new JSONObject();
		jsonString.put("card_no", cardNo);
		jsonString.put("cardno", cardNo);
		jsonString.put("card_pwd", cardPassWord);
		jsonString.put("totalAmount", Integer.parseInt(totalAmount)*100);
		jsonString.put("userno", userNo);
		jsonString.put("bankid", "gyj001");
		jsonString.put("accesstype", "W");
		jsonString.put("ladderpresentflag", ladderPresentFlag);
		jsonString.put("paytype", getCardType(cardType));
		jsonString.put("amt", Integer.parseInt(amount)*100);
		jsonString.put("channel", channel);
		jsonString.put("subchannel",subchannel);
		String errorCode = userInfoUtil.nineteenpayCharge(jsonString);
		if("0".equals(errorCode)){
			modelAndView.addObject("messageError", "充值请求已受理,请等待5分钟之后进入帐户查询余额！");
			modelAndView.setViewName("chargeResult");
			logger.info("ChargeCenterController/nineteenPayCardCharge手机卡充值成功");
			return modelAndView;

		}if("104".equals(errorCode)){
			modelAndView.addObject("messageError", "充值失败，卡号或密码错误！");
			modelAndView.setViewName("chargeFail");
			logger.info("ChargeCenterController/nineteenPayCardCharge手机卡充值失败返回errorCode:"+errorCode);
			return modelAndView;

		}else{
			modelAndView.addObject("messageError", "充值失败，请稍后再试！");
			modelAndView.setViewName("chargeFail");
			logger.info("ChargeCenterController/nineteenPayCardCharge手机卡充值失败返回errorCode:"+errorCode);
			return modelAndView;

		}		
	}
	/**
	 * 支付宝充值
	 * @param cardType
	 * @param amount
	 * @param ladderPresentFlag
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/chargeAlipay_Wap.do",method=RequestMethod.POST)
	public ModelAndView chargeAlipay_Wap(
			@RequestParam(value="cardType",defaultValue="")String cardType,
			@RequestParam(value="amount",defaultValue="")String amount,
			@RequestParam(value="ladderPresentFlag",defaultValue="1")String ladderPresentFlag,
			HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		logger.info("ChargeCenterController/chargeAlipay_Wap支付宝充值参数cardType:"+cardType
				+",amount:"+amount+",ladderPresentFlag:"+ladderPresentFlag);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo.getUserNo();
		String validateResult = ValidateUserInfoUtil.validateChargeAlipay(amount);
		if(!"".equals(validateResult)&&validateResult!=null){
			modelAndView.addObject("cardType", cardType);
			modelAndView.addObject("amount", amount);
			modelAndView.addObject("ladderPresentFlag", ladderPresentFlag);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("chargeAlipay_Wap");
			logger.info("ChargeCenterController/chargeAlipay_Wap支付宝充值参数验证失败返回validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("ChargeCenterController/chargeAlipay_Wap支付宝充值参数验证通过！");
		JSONObject jsonString = new JSONObject();
		jsonString.put("userno", userNo);
		jsonString.put("bankid", "zfb001");
		jsonString.put("accesstype", "W");
		jsonString.put("ladderpresentflag", ladderPresentFlag);
		jsonString.put("paytype", "0300");
		jsonString.put("amt", Integer.parseInt(amount)*100);
		jsonString.put("channel", channel);
		jsonString.put("subchannel",subchannel);
		jsonString.put("callbackurl", "http://3g.ruyicai.com");
		Map<String, String> map = userInfoUtil.chargeAlipay_Wap(jsonString);
		String errorCode = map.get("errorCode");
		if("0".equals(errorCode)){
			String requrl = map.get("requrl");
			try {
				logger.info("ChargeCenterController/chargeAlipay_Wap支付宝充值跳转页面成功");
				response.sendRedirect(requrl);
			} catch (IOException e) {
				logger.error("ChargeCenterController/chargeAlipay_Wap支付宝充值跳转页面失败");
				modelAndView.addObject("messageError", "服务器忙，请稍后再试！");
				modelAndView.setViewName("chargeFail");
				return modelAndView;
			}
			return null;
		}else{
			modelAndView.addObject("messageError", "充值失败，请稍后再试！");
			modelAndView.setViewName("chargeFail");
			logger.info("ChargeCenterController/chargeAlipay_Wap支付宝充值失败返回errorCode:"+errorCode);
			return modelAndView;

		}		
	}
	/**
	 * 支付宝快捷充值银行列表
	 * @param amount
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/chargeAlipayChannel.do",method=RequestMethod.POST)
	public ModelAndView chargeAlipayChannel(
			@RequestParam(value="amount",defaultValue="") String amount,
			HttpServletRequest request){
		logger.info("ChargeCenterController/chargeAlipayChannel支付宝快捷充值银行列表参数amount:"+amount);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateUserInfoUtil.validateChargeAlipay(amount);
		if(!"".equals(validateResult)&&validateResult!=null){
			modelAndView.addObject("amount", amount);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("chargeAlipay_KJ");
			logger.info("ChargeCenterController/chargeAlipayChannel支付宝快捷充值银行列表验证失败返回validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("ChargeCenterController/chargeAlipayChannel支付宝快捷充值银行列表参数验证通过！");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userno", "");
		Map<String, Object> map = userInfoUtil.selectPayChannelList(jsonObject);
		LastestPayChannel lastestPayChannel = new LastestPayChannel();
		List<SupportTopPayChannel> supportTopPayChannelList = new ArrayList<SupportTopPayChannel>();
		if(map.containsKey("lastestPayChannel"))
			lastestPayChannel = (LastestPayChannel) map.get("lastestPayChannel");
		if(map.containsKey("supportTopPayChannelList"))
			supportTopPayChannelList = (List<SupportTopPayChannel>) map.get("supportTopPayChannelList");
		modelAndView.addObject("lastestPayChannel", lastestPayChannel);
		modelAndView.addObject("supportTopPayChannelList", supportTopPayChannelList);
		modelAndView.addObject("amount", amount);
		modelAndView.setViewName("chargeAlipayChannel");
		return modelAndView;
	}
	/**
	 * 支付宝快捷支付
	 * @param cashierCode
	 * @param amount
	 * @param name
	 * @param ladderPresentFlag
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/chargeAlipay_KJ.do",method=RequestMethod.POST)
	public ModelAndView chargeAlipay_KJ(
			@RequestParam(value="cashierCode",defaultValue="")String cashierCode,
			@RequestParam(value="amount",defaultValue="")String amount,
			@RequestParam(value="name",defaultValue="")String name,
			@RequestParam(value="ladderPresentFlag",defaultValue="1")String ladderPresentFlag,
			HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		logger.info("ChargeCenterController/chargeAlipay_KJ支付宝充值参数cashierCode:"+cashierCode
				+",amount:"+amount+",ladderPresentFlag:"+ladderPresentFlag+",name:"+name);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo.getUserNo();
		JSONObject jsonString = new JSONObject();
		jsonString.put("userno", userNo);
		jsonString.put("bankid", "zfb001");
		jsonString.put("accesstype", "W");
		jsonString.put("bankaccount ", "6");
		jsonString.put("cashiercode", cashierCode);
		jsonString.put("ladderpresentflag", ladderPresentFlag);
		jsonString.put("paytype", "0300");
		jsonString.put("amt", Integer.parseInt(amount)*100);
		jsonString.put("channel", channel);
		jsonString.put("subchannel",subchannel);
		jsonString.put("callbackurl", "http://3g.ruyicai.com");
		Map<String, String> map = userInfoUtil.chargeAlipay_KJ(jsonString);
		String errorCode = map.get("errorCode");
		if("0".equals(errorCode)){
			String requrl = map.get("requrl");
			try {
				logger.info("ChargeCenterController/chargeAlipay_KJ支付宝快捷支付充值跳转页面成功");
				response.sendRedirect(requrl);
			} catch (IOException e) {
				logger.error("ChargeCenterController/chargeAlipay_KJ支付宝充值快捷支付跳转页面失败");
				modelAndView.addObject("messageError", "充值失败，请稍后再试！");
				modelAndView.setViewName("chargeFail");
				return modelAndView;
			}
			return null;
		}else{
			modelAndView.addObject("messageError", "充值失败，请稍后再试！");
			modelAndView.setViewName("chargeFail");
			logger.info("ChargeCenterController/chargeAlipay_KJ支付宝快捷支付充值失败返回errorCode:"+errorCode);
			return modelAndView;

		}		
	}
	/**
	 * 查询是否绑定DNA
	 * @param request
	 * @return
	 */
	@RequestMapping("/findDNAtoCharge.do")
	public ModelAndView findDNAtoCharge(HttpServletRequest request){
		logger.info("ChargeCenterController/findDNAtoCharge查询是否绑定DNA");
		ModelAndView modelAndView = new ModelAndView();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo.getUserNo();
		String userName = userInfo.getUserName();
		//查询是否DNA绑定
		DNABand dnaBand = selectAllUtil.selectUserDNABinding(userNo);
		if(dnaBand!=null){
			modelAndView.addObject("dnaBand", dnaBand);
			modelAndView.setViewName("chargeUnionPay_DNA");
			logger.info("ChargeCenterController/findDNAtoCharge查询是否绑定DNA:已绑定");
		}else{
			UserInfo user = selectAllUtil.selectUserInfoByUserName(userName);
			String name = user.getName();
			if(!"".equals(name)&&name!=null&&!"null".equals(name)){
				modelAndView.addObject("chargeName", name);
			}
			modelAndView.setViewName("chargeUnionPay");
			logger.info("ChargeCenterController/findDNAtoCharge查询是否绑定DNA:未绑定");

		}
		return modelAndView;
	}
	/**
	 * DNA充值
	 * @param mobileId
	 * @param cardNo
	 * @param amount
	 * @param ladderPresentFlag
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/chargeUnionPay_DNA.do",method=RequestMethod.POST)
	public ModelAndView chargeUnionPay_DNA(
			@RequestParam(value="mobileId",defaultValue="")String mobileId,
			@RequestParam(value="cardNo",defaultValue="")String cardNo,
			@RequestParam(value="amount",defaultValue="")String amount,
			@RequestParam(value="bankName",defaultValue="")String bankName,
			@RequestParam(value="ladderPresentFlag",defaultValue="1")String ladderPresentFlag,
			HttpServletRequest request){
		logger.info("ChargeCenterController/chargeUnionPay_DNA DNA充值参数mobileId:"+mobileId+",cardNo:"+cardNo+",amount:"+amount+",ladderPresentFlag:"+ladderPresentFlag);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateUserInfoUtil.validateDnaCharge(mobileId,amount);
		if(!"".equals(validateResult)&&validateResult!=null){
			modelAndView = findDNAtoCharge(request);
			modelAndView.addObject("amount", amount);
			modelAndView.addObject("mobileId", mobileId);
			modelAndView.addObject("ladderPresentFlag", ladderPresentFlag);
			modelAndView.addObject("messageError", validateResult);
			logger.info("ChargeCenterController/chargeUnionPay_DNA DNA充值验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("ChargeCenterController/chargeUnionPay_DNA DNA充值验证通过");
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo.getUserNo();
		JSONObject jsonString = new JSONObject();
		jsonString.put("userno", userNo);
		jsonString.put("bankid", "dna001");
		jsonString.put("accesstype", "W");
		jsonString.put("ladderpresentflag", ladderPresentFlag);
		jsonString.put("paytype", "0100");
		jsonString.put("cardtype", "0100");
		jsonString.put("amt", Integer.parseInt(amount)*100);
		jsonString.put("channel", channel);
		jsonString.put("subchannel",subchannel);
		jsonString.put("cardno", cardNo);
		jsonString.put("expand", ",,,," + mobileId + ",true,"+userInfoUtil.getBankName(bankName));
		Map<String, String> map = userInfoUtil.chargeUnionPay(jsonString);
		String errorCode = map.get("errorCode");
		if("T439".equals(errorCode)||"00A3".equals(errorCode)||"0000".equals(errorCode)){
			modelAndView.addObject("messageError", "已提交请求，请等待来电！");
			modelAndView.setViewName("chargeResult");
		}else if("500".equals(errorCode)){
			modelAndView.addObject("messageError", "服务器忙，请稍后再试！");
			modelAndView.setViewName("chargeFail");
		}else{
			String remark = map.get("remark");
			modelAndView.addObject("messageError", remark);
			modelAndView.setViewName("chargeFail");
		}
		return modelAndView;
		
	}
	/**
	 * DNA充值
	 * @param mobileId
	 * @param cardNo
	 * @param bankType
	 * @param name
	 * @param documentNumber
	 * @param documentAddress
	 * @param accountAddress1
	 * @param accountAddress2
	 * @param amount
	 * @param ladderPresentFlag
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/chargeUnionPay.do",method=RequestMethod.POST)
	public ModelAndView chargeUnionPay(
			@RequestParam(value="mobileId",defaultValue="")String mobileId,
			@RequestParam(value="cardNo",defaultValue="")String cardNo,
			@RequestParam(value="bankType",defaultValue="")String bankType,
			@RequestParam(value="name",defaultValue="")String name,
			@RequestParam(value="documentNumber",defaultValue="")String documentNumber,
			@RequestParam(value="documentAddress",defaultValue="")String documentAddress,
			@RequestParam(value="accountAddress1",defaultValue="")String accountAddress1,
			@RequestParam(value="accountAddress2",defaultValue="")String accountAddress2,
			@RequestParam(value="amount",defaultValue="")String amount,
			@RequestParam(value="ladderPresentFlag",defaultValue="1")String ladderPresentFlag,
			HttpServletRequest request){
		logger.info("ChargeCenterController/chargeUnionPay DNA首次充值参数mobileId:"+mobileId+",cardNo:"+cardNo+",amount:"+amount+",ladderPresentFlag:"+ladderPresentFlag
				+",bankType:"+bankType+",name:"+name+",documentNumber:"+documentNumber+",documentAddress:"+documentAddress
				+",accountAddress1:"+accountAddress1+",accountAddress2:"+accountAddress2);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateUserInfoUtil.validateDnaFirstCharge(mobileId, cardNo, name, amount, bankType, documentNumber, documentAddress, accountAddress1, accountAddress2);
		if(!"".equals(validateResult)&&validateResult!=null){
			modelAndView = findDNAtoCharge(request);
			modelAndView.addObject("amount", amount);
			modelAndView.addObject("mobileId", mobileId);
			modelAndView.addObject("cardNo", cardNo);
			modelAndView.addObject("bankType", bankType);
			modelAndView.addObject("name", name);
			modelAndView.addObject("documentNumber", documentNumber);
			modelAndView.addObject("documentAddress", documentAddress);
			modelAndView.addObject("accountAddress1", accountAddress1);
			modelAndView.addObject("accountAddress2", accountAddress2);
			modelAndView.addObject("ladderPresentFlag", ladderPresentFlag);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("chargeUnionPay");
			logger.info("ChargeCenterController/chargeUnionPay DNA首次充值验证失败返回validateResult:"+validateResult);
			return modelAndView;

		}
		logger.info("ChargeCenterController/chargeUnionPay DNA首次充值验证通过");
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo.getUserNo();
		JSONObject jsonString = new JSONObject();
		jsonString.put("userno", userNo);
		jsonString.put("bankid", "dna001");
		jsonString.put("accesstype", "W");
		jsonString.put("ladderpresentflag", ladderPresentFlag);
		jsonString.put("paytype", "0100");
		jsonString.put("cardtype", "dna001");
		jsonString.put("amt", Integer.parseInt(amount)*100);
		jsonString.put("channel", channel);
		jsonString.put("subchannel",subchannel);
		jsonString.put("cardno", cardNo);
		jsonString.put("expand", name + "," + documentNumber + ","
				+ accountAddress1 + accountAddress2 + "," + documentAddress
				+ "," + mobileId + ",false,"+userInfoUtil.getBankName(bankType));
		Map<String, String> map = userInfoUtil.chargeUnionPay(jsonString);
		String errorCode = map.get("errorCode");
		String remark = map.get("remark");
		if("T439".equals(errorCode)||"00A3".equals(errorCode)||"0000".equals(errorCode)){
			modelAndView.addObject("messageError", "已提交请求，请等待来电！");
			modelAndView.setViewName("chargeResult");
		}else{
			modelAndView.addObject("messageError", remark);
			modelAndView.setViewName("chargeFail");
		}
		return modelAndView;
		
	}
	/**
	 * 得到cardType
	 * @param cardType
	 * @return
	 */
	public static String getCardType(String cardType){
		if("YD".equals(cardType)) return "0203";
		else if("LT".equals(cardType)) return "0206";
		else if("DX".equals(cardType)) return "0221";
		else return "";
	}
	/**
	 * 联动优势充值
	 * @param amount
	 * @param ladderPresentFlag
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/chargeUnify_Wap.do",method=RequestMethod.POST)
	public ModelAndView chargeUnify_Wap(
			@RequestParam(value="amount",defaultValue="")String amount,
			@RequestParam(value="ladderPresentFlag",defaultValue="1")String ladderPresentFlag,
			HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		logger.info("ChargeCenterController/chargeUnify_Wap联动优势充值参数amount:"+amount+",ladderPresentFlag:"+ladderPresentFlag);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo.getUserNo();
		String validateResult = ValidateUserInfoUtil.validateChargeUnify(amount);
		if(!"".equals(validateResult)&&validateResult!=null){
			modelAndView.addObject("amount", amount);
			modelAndView.addObject("ladderPresentFlag", ladderPresentFlag);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("chargeUnify_Wap");
			logger.info("ChargeCenterController/chargeUnify_Wap联动优势充值参数验证失败返回validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("ChargeCenterController/chargeUnify_Wap联动优势充值参数验证通过！");
		JSONObject jsonString = new JSONObject();
		jsonString.put("userno", userNo);
		jsonString.put("bankid", "ump001");
		jsonString.put("accesstype", "B");
		jsonString.put("ladderpresentflag", ladderPresentFlag);
		jsonString.put("paytype", " ");
		jsonString.put("amt", Integer.parseInt(amount)*100);
		jsonString.put("channel", channel);
		jsonString.put("subchannel",subchannel);
		jsonString.put("bankaccount", "1");
		jsonString.put("continuebettype", "");
		jsonString.put("orderid", "");
		jsonString.put("mediatype", "MOBILE");
		jsonString.put("mediaid", "");
		jsonString.put("userip", "");
		jsonString.put("plat", "wap");
		jsonString.put("method", "get");
		jsonString.put("returl", "http://3g.ruyicai.com");
		Map<String, String> map = userInfoUtil.chargeUnify_Wap(jsonString);
		String errorCode = map.get("errorCode");
		if("0".equals(errorCode)){
			String requrl = map.get("requrl");
			try {
				logger.info("ChargeCenterController/chargeUnify_Wap联动优势充值跳转页面成功");
				response.sendRedirect(requrl);
			} catch (IOException e) {
				logger.error("ChargeCenterController/chargeUnify_Wap联动优势充值跳转页面失败");
				modelAndView.addObject("messageError", "服务器忙，请稍后再试！");
				modelAndView.setViewName("chargeFail");
				return modelAndView;
			}
			return null;
		}else{
			modelAndView.addObject("messageError", "充值失败，请稍后再试！");
			modelAndView.setViewName("chargeFail");
			logger.info("ChargeCenterController/chargeUmpay_Wap支付宝充值失败返回errorCode:"+errorCode);
			return modelAndView;

		}		
	}
	/**
	 * 联动优势话费充值
	 * @param amount
	 * @param ladderPresentFlag
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/chargeUnify_MobileFare.do",method=RequestMethod.POST)
	public ModelAndView chargeUnify_MobileFare(
			@RequestParam(value="submitType",defaultValue="")String submitType,
			@RequestParam(value="mobile",defaultValue="")String mobile,
			@RequestParam(value="ladderPresentFlag",defaultValue="1")String ladderPresentFlag,
			HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		logger.info("ChargeCenterController/chargeUnify_MobileFare联动优势话费充值参数submitType:"+submitType+",ladderPresentFlag:"+ladderPresentFlag);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo.getUserNo();
		String amount = "";
		if("3元话费充2元彩金".equals(submitType))
			amount = "3";
		if("30元话费充20元彩金".equals(submitType))
			amount = "30";
		String validateResult = ValidateUserInfoUtil.validateChargeUnify_MobileFare(amount,mobile);
		if(!"".equals(validateResult)&&validateResult!=null){
			modelAndView.addObject("mobile", mobile);
			modelAndView.addObject("ladderPresentFlag", ladderPresentFlag);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("chargeUnify_MobileFare");
			logger.info("ChargeCenterController/chargeUnify_MobileFare联动优势话费充值参数验证失败返回validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("ChargeCenterController/chargeUnify_MobileFare联动优势话费充值参数验证通过！");
		JSONObject jsonString = new JSONObject();
		String goodsid = "";
		if("3".equals(amount)) goodsid = "030";
		if("30".equals(amount)) goodsid = "300";
		jsonString.put("userno", userNo);
		jsonString.put("bankid", "ump002");
		jsonString.put("accesstype", "W");
		jsonString.put("ladderpresentflag", ladderPresentFlag);
		jsonString.put("paytype", " ");
		jsonString.put("amt", Integer.parseInt(amount)*100);
		jsonString.put("channel", channel);
		jsonString.put("subchannel",subchannel);
		jsonString.put("bankaccount", "0");
		jsonString.put("continuebettype", "");
		jsonString.put("orderid", "");
		jsonString.put("mobileid", mobile);
		jsonString.put("plat", "wap");
		jsonString.put("goodsid", goodsid);
		jsonString.put("returl", "http://3g.ruyicai.com");
		jsonString.put("method", "get");
		Map<String, String> map = userInfoUtil.chargeUnify_MobileFare(jsonString);
		String errorCode = map.get("errorCode");
		if("0".equals(errorCode)){
			String requrl = map.get("requrl");
			try {
				logger.info("ChargeCenterController/chargeUnify_MobileFare联动优势充值跳转页面成功");
				response.sendRedirect(requrl);
			} catch (IOException e) {
				logger.error("ChargeCenterController/chargeUnify_Wap联动优势充值跳转页面失败");
				modelAndView.addObject("messageError", "服务器忙，请稍后再试！");
				modelAndView.setViewName("chargeFail");
				return modelAndView;
			}
			return null;
		}else{
			modelAndView.addObject("messageError", "充值失败，请稍后再试！");
			modelAndView.setViewName("chargeFail");
			logger.info("ChargeCenterController/chargeUnify_MobileFare支付宝充值失败返回errorCode:"+errorCode);
			return modelAndView;

		}		
	}
}
