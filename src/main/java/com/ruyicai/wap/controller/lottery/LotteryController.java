package com.ruyicai.wap.controller.lottery;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.SelectAllUtil;
import com.ruyicai.wap.util.bet.LotteryUtil;
import com.ruyicai.wap.util.validate.ValidateLotteryUtil;
import com.ruyicai.wap.vo.Account;
import com.ruyicai.wap.vo.UserInfo;

@Controller
@RequestMapping("/lottery")
public class LotteryController {
	Logger logger = Logger.getLogger(LotteryController.class);
	@Value(value="${channel}")
	String channel;
	@Autowired
	SelectAllUtil selectAllUtil;
	@Autowired
	LotteryUtil lotteryUtil;
	@Autowired
	CommonUtil commonUtil;
	
	/**
	 * 普通订单投注，追号,赠送，合买
	 * @param betCode
	 * @param beiShu
	 * @param zhuShu
	 * @param addNumber
	 * @param amount
	 * @param amountView
	 * @param batchCode
	 * @param lotNo
	 * @param playType
	 * @param oneAmount
	 * @param prizeend中奖是否停止追号0
	 *            :不停止,1:停止
	 * @param buyWays购买方式：投注(buy)，赠送(present)，合买(caseLot)
	 * @param presentType 是否开启免费赠送0:开启；1:不开启(默认)。开启需要修改页面传值
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("/bet.do")
	public ModelAndView bet(			
			@RequestParam(value="betCode",defaultValue="") String betCode,
			@RequestParam(value="betCodeView",defaultValue="") String betCodeView,
			@RequestParam(value="beiShu",defaultValue="1") String beiShu,
			@RequestParam(value="zhuShu",defaultValue="1") String zhuShu,
			@RequestParam(value="addNumber",defaultValue="1") String addNumber,
			@RequestParam(value="amount",defaultValue="") String amount,
			@RequestParam(value="amountView",defaultValue="") String amountView,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="lotNo",defaultValue="") String lotNo,
			@RequestParam(value="playType",defaultValue="") String playType,
			@RequestParam(value="oneAmount",defaultValue="2") String oneAmount,
			@RequestParam(value="prizeend",defaultValue="0") String prizeend,
			@RequestParam(value="buyWays",defaultValue="buy") String buyWays,
			@RequestParam(value="presentType",defaultValue="1") String presentType,
			@RequestParam(value="token",defaultValue="") String token,
			HttpServletRequest request){
		logger.info("LotteryController/bet投注参数betCode:"+betCode+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",amount:"+amount+",lotNo:"+lotNo+",playType:"+playType+",batchCode:"+batchCode+",oneAmount:"+oneAmount+",prizeend:"+prizeend+",buyWays:"+buyWays+",token:"+token);		

		ModelAndView modelAndView = new ModelAndView();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String isExecute = (String) request.getSession().getAttribute(token);
		if(userInfo==null){
			modelAndView.addObject("messageError", "您还没有登录，请先登录！");
			modelAndView.setViewName("login");
		}
		String userNo = userInfo.getUserNo();
		Account account = selectAllUtil.selectUserAccount(userNo);
		String betBalance = account.getBetBalance();
		if("false".equals(isExecute)){
			request.getSession().setAttribute(token, "true");
			if("buy".equals(buyWays)){
				double amountd = Double.parseDouble(amount);
				if(amountd>Double.parseDouble(betBalance)){
					modelAndView.addObject("messageError", "您的余额不足，请先充值！");
					modelAndView.setViewName("chargeCenter");
					return modelAndView;
				}
				String betResult = "";
				if(lotNo.startsWith("J"))
					betResult = lotteryUtil.toJcBetOrder(userNo, lotNo, betCode, beiShu, oneAmount, amount, channel);
				else
					betResult = lotteryUtil.toBetOrder(userNo, lotNo, playType, batchCode, betCode, beiShu, oneAmount, amount, addNumber, prizeend, channel);
				account = selectAllUtil.selectUserAccount(userNo);
				betBalance = account.getBetBalance();
				modelAndView.addObject("messageError", betResult);
				modelAndView.addObject("betBalance", betBalance);
				modelAndView.setViewName("betResult");

			}else if("present".equals(buyWays)){
				double amountd = Double.parseDouble(amount);
				if(amountd>Double.parseDouble(betBalance)&&"1".equals(presentType)){
					modelAndView.addObject("messageError", "您的余额不足，请先充值！");
					modelAndView.setViewName("chargeCenter");
					return modelAndView;
				}
				modelAndView.addObject("betCode", betCode);
				modelAndView.addObject("betCodeView", betCodeView);
				modelAndView.addObject("beiShu", beiShu);
				modelAndView.addObject("zhuShu", zhuShu);
				modelAndView.addObject("addNumber", addNumber);
				modelAndView.addObject("amount", amount);
				modelAndView.addObject("amountView", amountView);
				modelAndView.addObject("batchCode", batchCode);
				modelAndView.addObject("playType", playType);
				modelAndView.addObject("lotNo", lotNo);
				modelAndView.addObject("lotName", lotteryUtil.getLotNameByLotNo(lotNo));
				modelAndView.addObject("oneAmount", oneAmount);
				commonUtil.setToken(request, modelAndView);
				modelAndView.setViewName("present");
			}else if("caseLot".equals(buyWays)){
				modelAndView.addObject("betCode", betCode);
				modelAndView.addObject("betCodeView", betCodeView);
				modelAndView.addObject("beiShu", beiShu);
				modelAndView.addObject("zhuShu", zhuShu);
				modelAndView.addObject("addNumber", addNumber);
				modelAndView.addObject("amount", amount);
				modelAndView.addObject("amountView", amountView);
				modelAndView.addObject("batchCode", batchCode);
				modelAndView.addObject("playType", playType);
				modelAndView.addObject("lotNo", lotNo);
				modelAndView.addObject("lotName", lotteryUtil.getLotNameByLotNo(lotNo));
				modelAndView.addObject("oneAmount", oneAmount);
				commonUtil.setToken(request, modelAndView);
				modelAndView.setViewName("caseLot");
			}
		}else{
			modelAndView.addObject("messageError", "请勿重复提交！");
			modelAndView.setViewName("betResult");
		}
		return modelAndView;
	}
	/**
	 * 普通赠送
	 * @param betCode
	 * @param beiShu
	 * @param addNumber
	 * @param amount
	 * @param batchCode
	 * @param lotNo
	 * @param oneAmount
	 * @param reciverMobiles
	 * @param blessing赠言
	 * @param token
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/present.do")
	public ModelAndView present(			
			@RequestParam(value="betCode",defaultValue="") String betCode,
			@RequestParam(value="betCodeView",defaultValue="") String betCodeView,
			@RequestParam(value="beiShu",defaultValue="1") String beiShu,
			@RequestParam(value="addNumber",defaultValue="1") String addNumber,
			@RequestParam(value="amount",defaultValue="") String amount,
			@RequestParam(value="amountView",defaultValue="") String amountView,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="lotNo",defaultValue="") String lotNo,
			@RequestParam(value="playType",defaultValue="") String playType,
			@RequestParam(value="oneAmount",defaultValue="2") String oneAmount,
			@RequestParam(value="reciverMobiles",defaultValue="") String reciverMobiles,
			@RequestParam(value="blessing",defaultValue="") String blessing,
			@RequestParam(value="token",defaultValue="") String token,
			HttpServletRequest request) throws UnsupportedEncodingException{
		logger.info("LotteryController/present投注参数betCode:"+betCode+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",amount:"+amount+",lotNo:"+lotNo+",playType:"+playType+",batchCode:"+batchCode+",oneAmount:"+oneAmount+",reciverMobiles:"+reciverMobiles+",blessing:"+blessing+",token:"+token);		

		ModelAndView modelAndView = new ModelAndView();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String isExecute = (String) request.getSession().getAttribute(token);
		if(userInfo==null){
			modelAndView.addObject("messageError", "您还没有登录，请先登录！");
			modelAndView.setViewName("login");
		}
		String userNo = userInfo.getUserNo();
		Account account = selectAllUtil.selectUserAccount(userNo);
		String betBalance = account.getBetBalance();
		reciverMobiles = reciverMobiles.trim();
		String validateResult = ValidateLotteryUtil.validatePresent(reciverMobiles, blessing);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("betCode", betCode);
			modelAndView.addObject("betCodeView", betCodeView);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("amount", amount);
			modelAndView.addObject("amountView", amountView);
			modelAndView.addObject("batchCode", batchCode);
			modelAndView.addObject("playType", playType);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("lotName", lotteryUtil.getLotNameByLotNo(lotNo));
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("reciverMobiles", reciverMobiles);
			modelAndView.addObject("blessing", blessing);
			modelAndView.addObject("messageError", validateResult);
			commonUtil.setToken(request, modelAndView);
			modelAndView.setViewName("present");
			return modelAndView;
		}

		if("false".equals(isExecute)){
			request.getSession().setAttribute(token, "true");
			String[] mobiles = reciverMobiles.split(",");
			String messageError = "";
			String successMessageError ="";
			String failMessageError = "";
			String reasonMessage = "";
			for (String reciverMobile : mobiles) {
				String errorCode = lotteryUtil.toPresent(userNo, reciverMobile, lotNo, playType, batchCode, betCode, beiShu, oneAmount, amount, addNumber, blessing, channel);
				if("0".equals(errorCode)){
					successMessageError+=reciverMobile+"<br/>";
				}else if("810007".equals(errorCode)){
					reasonMessage = "由于你已使用银联语音充值，为保障你您银行卡资金的安全，此账户不能做赠送!";
				}else if("20100710".equals(errorCode)){
					failMessageError+=reciverMobile+":余额不足！<br/>";
				}else{
					failMessageError+=reciverMobile+"<br/>";
				}
			}
			if(reasonMessage!=null&&!"".equals(reasonMessage)){
				messageError = reasonMessage;
			}else if(successMessageError==null||"".equals(successMessageError)){
				messageError = "赠送失败的手机号:"+failMessageError;
			}else if(failMessageError==null||"".equals(failMessageError)){
				messageError = "赠送成功的手机号:"+successMessageError;
			}else{
				messageError = "赠送成功的手机号:"+successMessageError+"赠送失败的手机号:"+failMessageError;
			}
//			messageError = successMessageError+failMessageError+reasonMessage;
			account = selectAllUtil.selectUserAccount(userNo);
			betBalance = account.getBetBalance();
			modelAndView.addObject("betBalance", betBalance);
			modelAndView.addObject("messageError", messageError);
			modelAndView.setViewName("betResult");
		}else{
			modelAndView.addObject("messageError", "请勿重复提交！");
			modelAndView.setViewName("betResult");
		}
		return modelAndView;
	}
	
	/**
	 * 普通合买投注
	 * @param betCode
	 * @param beiShu
	 * @param addNumber
	 * @param amount
	 * @param batchCode
	 * @param lotNo
	 * @param oneAmount
	 * @param buyAmt认购金额
	 * @param safeAmt保底金额
	 * @param minAmt最低认购金额
	 * @param commisionRatio发起人提成
	 * @param visibility公开标识0:对所有人公开;1:保密;2:对所有人截至后公开;3:对跟单者立即公开;4:对跟单者截止后公开
	 * @param desc方案描述
	 * @param token
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/caseLot.do")
	public ModelAndView caseLot(			
			@RequestParam(value="betCode",defaultValue="") String betCode,
			@RequestParam(value="betCodeView",defaultValue="") String betCodeView,
			@RequestParam(value="beiShu",defaultValue="1") String beiShu,
			@RequestParam(value="addNumber",defaultValue="1") String addNumber,
			@RequestParam(value="amount",defaultValue="") String amount,
			@RequestParam(value="amountView",defaultValue="") String amountView,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="lotNo",defaultValue="") String lotNo,
			@RequestParam(value="playType",defaultValue="") String playType,
			@RequestParam(value="oneAmount",defaultValue="2") String oneAmount,
			@RequestParam(value="buyAmt",defaultValue="") String buyAmt,
			@RequestParam(value="safeAmt",defaultValue="") String safeAmt,
			@RequestParam(value="minAmt",defaultValue="") String minAmt,
			@RequestParam(value="commisionRatio",defaultValue="") String commisionRatio,
			@RequestParam(value="visibility",defaultValue="") String visibility,
			@RequestParam(value="desc",defaultValue="") String desc,
			@RequestParam(value="token",defaultValue="") String token,
			HttpServletRequest request) throws UnsupportedEncodingException{
		logger.info("LotteryController/bet投注参数betCode:"+betCode+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",amount:"+amount+",lotNo:"+lotNo+",playType:"+playType+",batchCode:"+batchCode+",oneAmount:"+oneAmount+",buyAmt:"+buyAmt
				+",safeAmt:"+safeAmt+",minAmt:"+minAmt+",commisionRatio:"+commisionRatio+",visibility:"+visibility+",desc:"+desc+",token:"+token);		

		ModelAndView modelAndView = new ModelAndView();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String isExecute = (String) request.getSession().getAttribute(token);
		if(userInfo==null){
			modelAndView.addObject("messageError", "您还没有登录，请先登录！");
			modelAndView.setViewName("login");
		}
		String validateResult = ValidateLotteryUtil.validateCaseLot(amount, buyAmt, safeAmt, minAmt, desc);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("betCode", betCode);
			modelAndView.addObject("betCodeView", betCodeView);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("amount", amount);
			modelAndView.addObject("batchCode", batchCode);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("lotName", lotteryUtil.getLotNameByLotNo(lotNo));
			modelAndView.addObject("oneAmount", oneAmount);
			modelAndView.addObject("buyAmt", buyAmt);
			modelAndView.addObject("safeAmt", safeAmt);
			modelAndView.addObject("minAmt", minAmt);
			modelAndView.addObject("commisionRatio", commisionRatio);
			modelAndView.addObject("visibility", visibility);
			modelAndView.addObject("desc", desc);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("betResult");
			return modelAndView;
		}
		String userNo = userInfo.getUserNo();
		Account account = selectAllUtil.selectUserAccount(userNo);
		String betBalance = account.getBetBalance();
		if(Double.parseDouble(buyAmt)+Double.parseDouble(safeAmt)>Double.parseDouble(betBalance)){
			modelAndView.addObject("messageError", "您的余额不足，请先充值！");
			modelAndView.setViewName("caseLot");
		}
		if("false".equals(isExecute)){
			request.getSession().setAttribute(token, "true");
			JSONObject clrJson = new JSONObject();
			clrJson.put("buyAmt", buyAmt);
			clrJson.put("safeAmt", safeAmt);
			clrJson.put("minAmt", minAmt);
			clrJson.put("commisionRatio", commisionRatio);
			clrJson.put("visibility", visibility);
			clrJson.put("desc", desc);
			String betResult = "";
			if(lotNo.startsWith("J"))
				betResult = lotteryUtil.toJcCaseLotOrder(userNo, clrJson, lotNo, playType, betCode, beiShu, oneAmount, amount, channel);
			else
				betResult = lotteryUtil.toCaseLotOrder(userNo, clrJson, lotNo, playType, batchCode, betCode, beiShu, oneAmount, amount, addNumber, channel);
			modelAndView.addObject("messageError", betResult);
			modelAndView.setViewName("betResult");
		}else{
			modelAndView.addObject("messageError", "请勿重复提交！");
			modelAndView.setViewName("betResult");
		}
		return modelAndView;
	}
}
