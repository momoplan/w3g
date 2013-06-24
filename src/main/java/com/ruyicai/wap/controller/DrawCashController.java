package com.ruyicai.wap.controller;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.SelectAllUtil;
import com.ruyicai.wap.util.UserInfoUtil;
import com.ruyicai.wap.util.validate.ValidateUserInfoUtil;
import com.ruyicai.wap.vo.Account;
import com.ruyicai.wap.vo.Cash;
import com.ruyicai.wap.vo.DNABand;
import com.ruyicai.wap.vo.UserInfo;

@Controller
@RequestMapping("/drawCash")
public class DrawCashController {
	private static final Logger logger = Logger.getLogger(DrawCashController.class);
	@Autowired
	SelectAllUtil selectAllUtil;
	@Autowired
	UserInfoUtil userInfoUtil;
	@Autowired
	ValidateUserInfoUtil validateUserInfoUtil;
	/**
	 * 查询是否绑定DNA
	 * @param request
	 * @return
	 */
	@RequestMapping("/findDNAtoCash.do")
	public ModelAndView findDNAtoCash(HttpServletRequest request){
		logger.info("DrawCashController/findDNAtoCash查询是否绑定DNA");
		ModelAndView modelAndView = new ModelAndView();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo.getUserNo();
		String userName = userInfo.getUserName();
		//查询可提现金额
		Account account = selectAllUtil.selectUserAccount(userNo);
		String drawAccount = account.getDrawBalance();
		//查询是否DNA绑定
		DNABand dnaBand = selectAllUtil.selectUserDNABinding(userNo);
		modelAndView.addObject("drawAccount", drawAccount);
		if(dnaBand!=null){
			modelAndView.addObject("dnaBand", dnaBand);
			modelAndView.setViewName("drawCashDNA");
			logger.info("DrawCashController/findDNAtoCash查询是否绑定DNA:已绑定");
		}else{
			UserInfo user = selectAllUtil.selectUserInfoByUserName(userName);
			String name = user.getName();
			if(!"".equals(name)&&name!=null&&!"null".equals(name)){
				modelAndView.addObject("drawCashName", name);
			}
			modelAndView.setViewName("drawCashIndex");
			logger.info("DrawCashController/findDNAtoCash查询是否绑定DNA:未绑定");

		}
		return modelAndView;
	}
	/**
	 * 提现验证参数并到详细页面
	 * @param amount
	 * @param bankName
	 * @param realName
	 * @param bankNumber
	 * @param drawBalance
	 * @param banding
	 * @param passWord
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/drawCashDetail.do",method=RequestMethod.POST)
	public ModelAndView drawCashDetail(
			@RequestParam(value="amount" ,defaultValue="") String amount,
			@RequestParam(value="bankName" ,defaultValue="") String bankName,
			@RequestParam(value="name" ,defaultValue="") String name,
			@RequestParam(value="bankNumber" ,defaultValue="") String bankNumber,
			@RequestParam(value="drawAccount" ,defaultValue="") String drawAccount,
			@RequestParam(value="banding" ,defaultValue="") String banding,
			@RequestParam(value="passWord" ,defaultValue="") String passWord,
			HttpServletRequest request){
		logger.info("DrawCashController/drawCashDetail用户提现参数amount:"+amount+",bankName:"+bankName
				+",name:"+name+",drawAccount:"+drawAccount+",banding:"+banding+",passWord:"+passWord);
		ModelAndView modelAndView = new ModelAndView();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userName = userInfo.getUserName();
		String validateResult = validateUserInfoUtil.validateDrawCash(amount, drawAccount, bankName, name, bankNumber, userName, passWord);
		if(!"".equals(validateResult)&&validateResult!=null){
			logger.info("DrawCashController/drawCashDetail用户提现参数验证失败返回validateResult:"+validateResult);
			if("DNA".equals(banding)){
				modelAndView = findDNAtoCash(request);
				modelAndView.addObject("messageError", validateResult);
				return modelAndView;
			}else{
				modelAndView = findDNAtoCash(request);
				modelAndView.addObject("messageError", validateResult);
				modelAndView.addObject("amount", amount);
				modelAndView.addObject("name", name);
				modelAndView.addObject("bankNumber", bankNumber);
				modelAndView.addObject("drawAccount", drawAccount);
				modelAndView.addObject("banding", banding);
				modelAndView.addObject("passWord", passWord);
				modelAndView.setViewName("drawCashIndex");
				return modelAndView;
			}
		}
		logger.info("DrawCashController/drawCashDetail用户提现参数验证通过");
		modelAndView.addObject("amount", amount);
		modelAndView.addObject("bankName", userInfoUtil.getBankName(bankName));
		modelAndView.addObject("name", name);
		modelAndView.addObject("bankNumber", bankNumber);
		modelAndView.addObject("drawAccount", drawAccount);
		modelAndView.addObject("banding", banding);
		modelAndView.addObject("passWord", passWord);
		modelAndView.setViewName("drawCashDetail");
		return modelAndView;
	}
	

	/**
	 * 用户提现
	 * @param amount
	 * @param bankName
	 * @param name
	 * @param bankNumber
	 * @param drawAccount
	 * @param bankAddress
	 * @param request
	 * @return
	 * @throws NumberFormatException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/drawCash.do",method=RequestMethod.POST)
	public ModelAndView drawCash(
			@RequestParam(value="amount" ,defaultValue="") String amount,
			@RequestParam(value="bankName" ,defaultValue="") String bankName,
			@RequestParam(value="name" ,defaultValue="") String name,
			@RequestParam(value="bankNumber" ,defaultValue="") String bankNumber,
			@RequestParam(value="drawAccount" ,defaultValue="") String drawAccount,
			HttpServletRequest request) throws NumberFormatException, UnsupportedEncodingException{
		ModelAndView modelAndView = new ModelAndView();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo.getUserNo();
		logger.info("DrawCashController/drawCash提现参数amount："+amount+",bankName:"+bankName+",name:"+name+",bankNumber:"
				+bankNumber+",drawAccount:"+drawAccount+",userNo:"+userNo);
		logger.info("DrawCashController/drawCash金额转换成long前："+amount);
		logger.info("DrawCashController/drawCash金额转换后："+(long)(Float.parseFloat(amount) * 100));
		String errorCode = userInfoUtil.drawCash(userNo, (long)(Float.parseFloat(amount) * 100), bankName, bankNumber, name, bankName); 
		logger.info("DrawCashController/drawCash提现返回errorCode:"+errorCode);
		if("0".equals(errorCode)){
			modelAndView.addObject("messageError", "提现请求成功，请等待审核！");
			modelAndView.setViewName("drawCashResult");
			logger.info("DrawCashController/drawCash提现成功");
		}else if("400005".equals(errorCode)){
			modelAndView.addObject("messageError", "提现失败，提现金额不足！");
			modelAndView.setViewName("drawCashFail");
			logger.info("DrawCashController/drawCash提现失败，提现金额不足");
		}else if("400012".equals(errorCode)){
			modelAndView.addObject("messageError", "提现人姓名与用户信息中填写的姓名不一致！");
			modelAndView.setViewName("drawCashFail");
			logger.info("DrawCashController/drawCash提现失败，提现金额不足");
		}else{
			modelAndView.addObject("messageError", "提现失败，请稍后再试！");
			modelAndView.setViewName("drawCashFail");
			logger.info("DrawCashController/drawCash提现失败");
		}
		return modelAndView;
	}

	/**
	 * 撤销提现
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/cancelDrawCash.do")
	public ModelAndView cancelDrawCash(
			@RequestParam(value="id" ,defaultValue="") String id,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		logger.info("DrawCashController/cancelDrawCash取消提现参数：id:"+id);
		String errorCode = userInfoUtil.cancelTcashDetail(id);
		if("0".equals(errorCode)){
			modelAndView.addObject("messageError", "撤销提现成功！");
			logger.info("DrawCashController/cancelDrawCash撤销提现成功");
		}else{
			modelAndView.addObject("messageError", "撤销提现失败！");
			logger.info("DrawCashController/cancelDrawCash撤销提现失败errorCode:"+errorCode);
		}
		modelAndView.setViewName("drawCashResult");	
		return modelAndView;
	}
	/**
	 * 查询提现记录
	 * @param pageIndex
	 * @param maxResult
	 * @param model
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/drawCashHistory.do")
	public ModelAndView drawCashHistory(
			@RequestParam(value="startLine",defaultValue="0") String startLine,
			@RequestParam(value="endLine",defaultValue="10") String endLine,
			@RequestParam(value="nowPage",defaultValue="1") String nowPage,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		int maxLine =10;
		logger.info("DrawCashController/drawCashHistory提现查询参数startLine:"+startLine+",endLine:"+endLine+",nowPage:"+nowPage);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo.getUserNo();
		Map<String, Object> map =selectAllUtil.selectCashByUsernoAndPage(userNo, (Integer.parseInt(nowPage)-1)+"", maxLine+"");
		List<Cash> drawCashList = new ArrayList<Cash>();
		if(map!=null){
			drawCashList = (List<Cash>) map.get("drawCashList");
			if(drawCashList!=null){
				String totalPage =  (String) map.get("totalPage");
				modelAndView.addObject("totalPage",Integer.parseInt(totalPage));
			}
			modelAndView.addObject("drawCashList",drawCashList);
			modelAndView.addObject("nowPage",Integer.parseInt(nowPage) );
			modelAndView.addObject("maxLine",maxLine );
		}
		modelAndView.setViewName("drawCashHistory");
		return modelAndView;
	}
}
