package com.ruyicai.wap.controller;


import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.SelectAllUtil;
import com.ruyicai.wap.vo.Account;
import com.ruyicai.wap.vo.Order;
import com.ruyicai.wap.vo.Present;
import com.ruyicai.wap.vo.UserInfo;

@Controller
@RequestMapping("/select")
public class SelectAllController {
	Logger logger = Logger.getLogger(SelectAllController.class);
	@Autowired
	SelectAllUtil selectAllUtil;
	/**
	 * 查询账户详情
	 * @param request
	 * @return
	 */
	@RequestMapping("/userAccount.do")
	public ModelAndView userAccount(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo.getUserNo();
		Account account = selectAllUtil.selectUserAccount(userNo);
		modelAndView.addObject("account", account);
		modelAndView.setViewName("userAccount");
		return modelAndView;
	}
	/**
	 * 查询用户账户明细
	 * @param beginTime
	 * @param endTime
	 * @param pageIndex
	 * @param maxResult
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/userAccountDetail.do")
	public ModelAndView userAccountDetail(
			@RequestParam(value="beginTime",defaultValue="")String beginTime,
			@RequestParam(value="endTime",defaultValue="")String endTime,
			@RequestParam(value="startLine",defaultValue="0")String startLine,
			@RequestParam(value="endLine",defaultValue="10")String endLine,
			@RequestParam(value="nowPage",defaultValue="0")String nowPage,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		logger.info("SelectAllController/userAccountDetail查询用户账户明细beginTime:"+beginTime
				+",endTime"+endTime+",startLine:"+startLine+",endLine:"+endLine);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo.getUserNo();
		int maxLine =10;
		if("".equals(beginTime.trim())||beginTime==null){
			beginTime = "20100101";
		}
		if("".equals(endTime.trim())||endTime==null){
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			endTime = dateFormat.format(date);
		}
		Pattern pattern = Pattern.compile("[0-9]{8}");
		Matcher matcher = pattern.matcher(beginTime);
		Matcher matcher2 = pattern.matcher(endTime);
		modelAndView.addObject("beginTime", beginTime);
		modelAndView.addObject("endTime", endTime);
		if (!matcher.matches()||!matcher2.matches()) {
			modelAndView.addObject("messageError", "日期格式不正确！");
			modelAndView.setViewName("userAccountIndex");
			return modelAndView;
		} 
		Map<String, Object> map = selectAllUtil.selectTaccountDetail(userNo, beginTime, endTime, (Integer.parseInt(nowPage)-1)+"" , maxLine+"");
		if(map!=null&&map.size()>0){
			List<Account> accounts = (List<Account>) map.get("accounts");
			String totalPage = (String) map.get("totalPage");
			modelAndView.addObject("totalPage", Integer.parseInt(totalPage));
			modelAndView.addObject("nowPage", Integer.parseInt(nowPage));
			modelAndView.addObject("maxLine", maxLine);
			modelAndView.addObject("accounts", accounts);
		}
		modelAndView.setViewName("userAccountDetail");
		return modelAndView;
	}
	/**
	 * 用户投注查询
	 * @param beginTime
	 * @param endTime
	 * @param startLine
	 * @param endLine
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/userBetting.do")
	public ModelAndView userBetting(
			@RequestParam(value="beginTime",defaultValue="20100101") String beginTime,
			@RequestParam(value="endTime",defaultValue="") String endTime,
			@RequestParam(value="startLine",defaultValue="0") String startLine,
			@RequestParam(value="endLine",defaultValue="10") String endLine,
			@RequestParam(value="nowPage",defaultValue="1") String nowPage,
			@RequestParam(value="lotNo",defaultValue="") String lotNo,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		int maxLine = 10;
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo.getUserNo();
		logger.info("SelectAllController/userBetting查询用户投注信息beginTime:"+beginTime
				+",endTime:"+endTime+",startLine:"+startLine+",endLine:"+endLine+",userNo:"+userNo+",lotNo:"+lotNo);
		if("".equals(beginTime.trim())||beginTime==null){
			beginTime = "20100101";
		}
		if("".equals(endTime.trim())||endTime==null){
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			endTime = dateFormat.format(date);
		}
		Pattern pattern = Pattern.compile("[0-9]{8}");
		Matcher matcher = pattern.matcher(beginTime);
		Matcher matcher2 = pattern.matcher(endTime);
		modelAndView.addObject("beginTime", beginTime);
		modelAndView.addObject("endTime", endTime);
		if (!matcher.matches()||!matcher2.matches()) {
			modelAndView.addObject("messageError", "日期格式不正确！");
			modelAndView.setViewName("userBetting");
			return modelAndView;
		}
		Map<String, Object> userBettingMap = selectAllUtil.selectUserBetting(userNo, beginTime, endTime, startLine, maxLine+"", lotNo);
		List<Order> orders= null;
		String totalPage = "";
		String currentPageNo = "";
		if(userBettingMap!=null&&userBettingMap.size()>0){
			orders = (List<Order>) userBettingMap.get("orders");
			totalPage = (String) userBettingMap.get("totalPage");
			currentPageNo = (String) userBettingMap.get("currentPageNo");
		}
		logger.info("SelectAllController/userBetting查询用户投注信息totalPage："+totalPage+",currentPageNo:"+currentPageNo+",nowPage:"+nowPage);
		modelAndView.addObject("orders", orders);
		modelAndView.addObject("maxLine", maxLine);
		modelAndView.addObject("totalPage", Integer.parseInt(totalPage));
		modelAndView.addObject("nowPage", Integer.parseInt(nowPage));
		modelAndView.setViewName("userBetting");
		return modelAndView;
	}
	/**
	 * 用户投注查询详情
	 * @param lotName
	 * @param batchCode
	 * @param orderId
	 * @param createTime
	 * @param beiShu
	 * @param zhuShu
	 * @param amount
	 * @param lotNo
	 * @param betCode
	 * @param prizeState
	 * @param orderPrize
	 * @param winBaseCode
	 * @param request
	 * @return
	 */
	@RequestMapping("/userBettingDetail.do")
	public ModelAndView userBettingDetail(
			@RequestParam(value="lotName",defaultValue="") String lotName,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="orderId",defaultValue="") String orderId,
			@RequestParam(value="createTime",defaultValue="") String createTime,
			@RequestParam(value="beiShu",defaultValue="") String beiShu,
			@RequestParam(value="zhuShu",defaultValue="") String zhuShu,
			@RequestParam(value="amount",defaultValue="") String amount,
			@RequestParam(value="lotNo",defaultValue="") String lotNo,
			@RequestParam(value="betCode",defaultValue="") String betCode,
			@RequestParam(value="prizeState",defaultValue="") String prizeState,
			@RequestParam(value="orderPrize",defaultValue="") String orderPrize,
			@RequestParam(value="winBaseCode",defaultValue="") String winBaseCode,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		logger.info("SelectAllController/userBettingDetail查询用户投注信息详情");
		modelAndView.addObject("lotName", lotName);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("orderId", orderId);
		modelAndView.addObject("createTime", createTime);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("zhuShu", zhuShu);
		modelAndView.addObject("amount", amount);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("prizeState", prizeState);
		modelAndView.addObject("orderPrize", orderPrize);
		modelAndView.addObject("winBaseCode", winBaseCode);
		modelAndView.setViewName("userBettingDetail");
		return modelAndView;
	}
	/**
	 * 查询用户中奖信息列表
	 * @param beginTime
	 * @param endTime
	 * @param startLine
	 * @param endLine
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/userWinning.do")
	public ModelAndView userWinning(
			@RequestParam(value="beginTime",defaultValue="20100101") String beginTime,
			@RequestParam(value="endTime",defaultValue="") String endTime,
			@RequestParam(value="startLine",defaultValue="0") String startLine,
			@RequestParam(value="endLine",defaultValue="10") String endLine,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		int maxLine = 10;
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo.getUserNo();
		logger.info("SelectAllController/userWinning查询用户中奖信息列表beginTime:"+beginTime
				+",endTime:"+endTime+",startLine"+startLine+",endLine:"+endLine);

		if("".equals(beginTime.trim())||beginTime==null){
			beginTime = "20100101";
		}
		if("".equals(endTime.trim())||endTime==null){
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			endTime = dateFormat.format(date);
		} 
		Map<String, Object> userWinningMap = selectAllUtil.selectUserWinning(userNo, beginTime, endTime, startLine, maxLine+"");
		if(userWinningMap!=null&&userWinningMap.size()>0){
			List<Order> orders= (List<Order>) userWinningMap.get("orders");
			String totalPage = (String) userWinningMap.get("totalPage");
			String currentPageNo = (String) userWinningMap.get("currentPageNo");
			modelAndView.addObject("orders", orders);
			modelAndView.addObject("totalPage", Integer.parseInt(totalPage));
			modelAndView.addObject("nowPage", Integer.parseInt(currentPageNo));
			modelAndView.addObject("maxLine", maxLine);

		}
		modelAndView.setViewName("userWinning");
		return modelAndView;
	}
	/**
	 * 查询用户中奖信息详情
	 * @param lotName
	 * @param batchCode
	 * @param orderId
	 * @param createTime
	 * @param encashTime
	 * @param beiShu
	 * @param zhuShu
	 * @param amount
	 * @param lotNo
	 * @param betCode
	 * @param orderPrize
	 * @param winBaseCode
	 * @param request
	 * @return
	 */
	@RequestMapping("/userWinningDetail.do")
	public ModelAndView userWinningDetail(
			@RequestParam(value="lotName",defaultValue="") String lotName,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="orderId",defaultValue="") String orderId,
			@RequestParam(value="createTime",defaultValue="") String createTime,
			@RequestParam(value="encashTime",defaultValue="") String encashTime,
			@RequestParam(value="beiShu",defaultValue="") String beiShu,
			@RequestParam(value="zhuShu",defaultValue="") String zhuShu,
			@RequestParam(value="amount",defaultValue="") String amount,
			@RequestParam(value="lotNo",defaultValue="") String lotNo,
			@RequestParam(value="betCode",defaultValue="") String betCode,
			@RequestParam(value="orderPrize",defaultValue="") String orderPrize,
			@RequestParam(value="winBaseCode",defaultValue="") String winBaseCode,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		logger.info("SelectAllController/userWinningDetail查询用户中奖信息详情");
		modelAndView.addObject("lotName", lotName);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("orderId", orderId);
		modelAndView.addObject("createTime", createTime);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("zhuShu", zhuShu);
		modelAndView.addObject("amount", amount);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("encashTime", encashTime);
		modelAndView.addObject("orderPrize", orderPrize);
		modelAndView.addObject("winBaseCode", winBaseCode);
		modelAndView.setViewName("userWinningDetail");
		return modelAndView;
	}
	/**
	 * 查询用户追号信息列表
	 * @param beginTime
	 * @param endTime
	 * @param startLine
	 * @param endLine
	 * @param lotNo
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/userAddNumber.do")
	public ModelAndView userAddNumber(
			@RequestParam(value="beginTime",defaultValue="20100101") String beginTime,
			@RequestParam(value="endTime",defaultValue="") String endTime,
			@RequestParam(value="startLine",defaultValue="0") String startLine,
			@RequestParam(value="endLine",defaultValue="10") String endLine,
			@RequestParam(value="lotNo",defaultValue="") String lotNo,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo.getUserNo();
		logger.info("SelectAllController/userAddNumber查询用户追号信息列表userNo:"+userNo);
		int maxLine = 10;
		if("".equals(beginTime.trim())||beginTime==null){
			beginTime = "20100101";
		}
		if("".equals(endTime.trim())||endTime==null){
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			endTime = dateFormat.format(date);
		}
		Pattern pattern = Pattern.compile("[0-9]{8}");
		Matcher matcher = pattern.matcher(beginTime);
		Matcher matcher2 = pattern.matcher(endTime);
		modelAndView.addObject("beginTime", beginTime);
		modelAndView.addObject("endTime", endTime);
		if (!matcher.matches()||!matcher2.matches()) {
			modelAndView.addObject("messageError", "日期格式不正确！");
			modelAndView.setViewName("userAddNumber");
			return modelAndView;
		}
		Map<String, Object> addNumberMap = selectAllUtil.selectAddNumber(userNo, lotNo, beginTime, endTime, startLine, maxLine+"");
		if(addNumberMap!=null&&addNumberMap.size()>0){
			List<Order> orders = (List<Order>) addNumberMap.get("orders");
			String totalPage = (String) addNumberMap.get("totalPage");
			String currentPageNo = (String) addNumberMap.get("currentPageNo");
			modelAndView.addObject("orders", orders);
			modelAndView.addObject("totalPage", Integer.parseInt(totalPage));
			modelAndView.addObject("nowPage", Integer.parseInt(currentPageNo));
			modelAndView.addObject("maxLine", maxLine);
		}
		modelAndView.addObject("endLine", Integer.parseInt(endLine));
		modelAndView.setViewName("userAddNumber");
		return modelAndView;

	}
	/**
	 * 查询用户追号信息详情
	 * @param nowNum
	 * @param state
	 * @param flowNo
	 * @param beginBatch
	 * @param startLine
	 * @param endLine
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/userAddNumberDetail.do")
	public ModelAndView userAddNumberDetail(
			@RequestParam(value="nowNum",defaultValue="") String nowNum,
			@RequestParam(value="state",defaultValue="") String state,
			@RequestParam(value="flowNo",defaultValue="") String flowNo,
			@RequestParam(value="prizeend",defaultValue="") String prizeend,
			@RequestParam(value="beginBatch",defaultValue="") String beginBatch,
			@RequestParam(value="startLine",defaultValue="0") String startLine,
			@RequestParam(value="endLine",defaultValue="10") String endLine,
			HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView modelAndView = new ModelAndView();
		logger.info("SelectAllController/userAddNumberDetail查询用户追号信息详情");
		Order order = selectAllUtil.selectAddNumberDetail(flowNo, "0", "153");
		if(order!=null){
			order.setState(state);
			order.setPrizeend(prizeend);
		}
		modelAndView.addObject("order", order);
		modelAndView.addObject("startLine", startLine);
		modelAndView.addObject("endLine", endLine);
		modelAndView.setViewName("userAddNumberDetail");
		return modelAndView;
		
	}
	/**
	 * 用户停止追号
	 * @param flowNo
	 * @param request
	 * @return
	 */
	@RequestMapping("/userGiveUpAddNumberDetail.do")
	public ModelAndView userGiveUpAddNumberDetail(
			@RequestParam(value="flowNo",defaultValue="") String flowNo,
			HttpServletRequest request){
		logger.info("SelectAllController/userGiveUpAddNumberDetail用户停止追号flowNo:"+flowNo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("flowNo", flowNo);
		modelAndView.setViewName("userGiveUpAddNumber");
		return modelAndView;
	}
	/**
	 * 用户停止追号
	 * @param flowNo
	 * @param request
	 * @return
	 */
	@RequestMapping("/userGiveUpAddNumber.do")
	public ModelAndView userGiveUpAddNumber(
			@RequestParam(value="flowNo",defaultValue="") String flowNo,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		logger.info("SelectAllController/userGiveUpAddNumber用户停止追号flowNo:"+flowNo);
		String errorCode = selectAllUtil.giveUpSubscribe(flowNo);
		if("0".equals(errorCode)){
			modelAndView.addObject("messageError", "停止追号成功！");
		}else{
			modelAndView.addObject("messageError", "停止追号失败！");
		}
		modelAndView.setViewName("userGiveUpAddNumberResult");
		return modelAndView;
	}
	/**
	 * 赠送查询列表
	 * @param startLine
	 * @param endLine
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/selectSenderPresent.do")
	public ModelAndView selectSenderPresent(
			@RequestParam(value="startLine",defaultValue="0") String startLine,
			@RequestParam(value="endLine",defaultValue="10") String endLine,
			@RequestParam(value="presentType",defaultValue="sender") String presentType,
			HttpServletRequest request){
		logger.info("SelectAllController/selectSenderPresent赠送查询列表参数startLine:"+startLine+",endLine:"+endLine);
		ModelAndView modelAndView = new ModelAndView();
		int maxLine = 10;
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo.getUserNo();
		Map<String, Object> map = selectAllUtil.selectSenderPresentDetails(userNo, startLine, maxLine+"");
		if(map!=null&&map.size()>0){
			List<Present> presents = (List<Present>) map.get("presents");
			String totalPage = (String) map.get("totalPage");
			String currentPageNo = (String) map.get("currentPageNo");
			modelAndView.addObject("maxLine",maxLine);
			modelAndView.addObject("nowPage",Integer.parseInt(currentPageNo));
			modelAndView.addObject("totalPage",Integer.parseInt(totalPage));
			modelAndView.addObject("presents",presents);
		}
		modelAndView.addObject("presentType",presentType);
		modelAndView.setViewName("senderPresent");		
		return modelAndView;
		
	}
	/**
	 * 赠送查询详情
	 * @param mobile
	 * @param amt
	 * @param batchCode
	 * @param lotName
	 * @param zhuShu
	 * @param beiShu
	 * @param createTime
	 * @param betCode
	 * @param winCode
	 * @param orderPrize
	 * @param presentType
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectSenderPresentDetail.do")
	public ModelAndView selectSenderPresentDetail(
			@RequestParam(value="mobile",defaultValue="") String mobile,
			@RequestParam(value="amt",defaultValue="") String amt,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="lotName",defaultValue="") String lotName,
			@RequestParam(value="zhuShu",defaultValue="") String zhuShu,
			@RequestParam(value="beiShu",defaultValue="") String beiShu,
			@RequestParam(value="createTime",defaultValue="") String createTime,
			@RequestParam(value="betCode",defaultValue="") String betCode,
			@RequestParam(value="winCode",defaultValue="") String winCode,
			@RequestParam(value="orderPrize",defaultValue="") String orderPrize,
			@RequestParam(value="prizeState",defaultValue="") String prizeState,
			@RequestParam(value="presentType",defaultValue="sender") String presentType,
			HttpServletRequest request){
		logger.info("SelectAllController/selectSenderPresent赠送查询详情参数mobile:"+mobile+",amt:"+amt);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("mobile",mobile);
		modelAndView.addObject("amt",amt);
		modelAndView.addObject("batchCode",batchCode);
		modelAndView.addObject("lotName",lotName);
		modelAndView.addObject("zhuShu",zhuShu);
		modelAndView.addObject("beiShu",beiShu);
		modelAndView.addObject("createTime",createTime);	
		modelAndView.addObject("betCode",betCode);
		modelAndView.addObject("winCode",winCode);
		modelAndView.addObject("orderPrize",orderPrize);
		modelAndView.addObject("prizeState",prizeState);
		modelAndView.addObject("presentType",presentType);
		modelAndView.setViewName("senderPresentDetail");		
		return modelAndView;
		
	}
	/**
	 * 被赠送查询列表
	 * @param startLine
	 * @param endLine
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/selectReciverPresent.do")
	public ModelAndView selectReciverPresent(
			@RequestParam(value="startLine",defaultValue="0") String startLine,
			@RequestParam(value="endLine",defaultValue="10") String endLine,
			@RequestParam(value="presentType",defaultValue="reciver") String presentType,
			HttpServletRequest request){
		logger.info("SelectAllController/selectReciverPresent被赠送查询参数列表startLine:"+startLine+",endLine:"+endLine);
		ModelAndView modelAndView = new ModelAndView();
		int maxLine=10;
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo.getUserNo();
		Map<String, Object> map = selectAllUtil.selectReciverPresentDetails(userNo, startLine, maxLine+"");
		if(map!=null&&map.size()>0){
			List<Present> presents = (List<Present>) map.get("presents");
			String totalPage = (String) map.get("totalPage");
			String currentPageNo = (String) map.get("currentPageNo");
			modelAndView.addObject("maxLine",maxLine);
			modelAndView.addObject("nowPage",Integer.parseInt(currentPageNo));
			modelAndView.addObject("totalPage",Integer.parseInt(totalPage));
			modelAndView.addObject("presents",presents);
		}
		modelAndView.addObject("presentType",presentType);
		modelAndView.setViewName("reciverPresent");		
		return modelAndView;
		
	}
	/**
	 * 被赠送查询详情
	 * @param userName
	 * @param amt
	 * @param batchCode
	 * @param lotName
	 * @param zhuShu
	 * @param beiShu
	 * @param createTime
	 * @param betCode
	 * @param winCode
	 * @param orderPrize
	 * @param presentType
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectReciverPresentDetails.do")
	public ModelAndView selectReciverPresentDetails(
			@RequestParam(value="userName",defaultValue="") String userName,
			@RequestParam(value="amt",defaultValue="") String amt,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="lotName",defaultValue="") String lotName,
			@RequestParam(value="zhuShu",defaultValue="") String zhuShu,
			@RequestParam(value="beiShu",defaultValue="") String beiShu,
			@RequestParam(value="createTime",defaultValue="") String createTime,
			@RequestParam(value="betCode",defaultValue="") String betCode,
			@RequestParam(value="winCode",defaultValue="") String winCode,
			@RequestParam(value="orderPrize",defaultValue="") String orderPrize,
			@RequestParam(value="prizeState",defaultValue="") String prizeState,
			@RequestParam(value="presentType",defaultValue="reciver") String presentType,
			HttpServletRequest request){
		logger.info("SelectAllController/selectReciverPresentDetails被赠送查询详情参数userName:"+userName+",amt:"+amt);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userName",userName);
		modelAndView.addObject("amt",amt);
		modelAndView.addObject("batchCode",batchCode);
		modelAndView.addObject("lotName",lotName);
		modelAndView.addObject("zhuShu",zhuShu);
		modelAndView.addObject("beiShu",beiShu);
		modelAndView.addObject("createTime",createTime);	
		modelAndView.addObject("betCode",betCode);
		modelAndView.addObject("winCode",winCode);
		modelAndView.addObject("orderPrize",orderPrize);
		modelAndView.addObject("prizeState",prizeState);
		modelAndView.addObject("presentType",presentType);
		modelAndView.setViewName("reciverPresentDetail");		
		return modelAndView;
		
	}
}
