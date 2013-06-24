package com.ruyicai.wap.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.SelectAllUtil;
import com.ruyicai.wap.util.bet.LotteryUtil;
import com.ruyicai.wap.util.validate.ValidateLotteryUtil;
import com.ruyicai.wap.vo.CaseLot;
import com.ruyicai.wap.vo.UserInfo;

@Controller
@RequestMapping("/caseLotCenter")
public class CaseLotCenterController {
	Logger logger = Logger.getLogger(CaseLotCenterController.class);
	@Autowired
	SelectAllUtil selectAllUtil;
	@Autowired
	LotteryUtil lotteryUtil;
	@Value(value="${channel}")
	String channel;
	/**
	 * 查询合买大厅列表
	 * @param startLine
	 * @param endLine
	 * @param maxLine
	 * @param orderDir
	 * @param orderBy
	 * @param lotNo
	 * @param batchCode
	 * @param search
	 * @param type
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/selectCaseLot")
	public ModelAndView selectCaseLotCenter(
			@RequestParam(value="startLine",defaultValue="0") String startLine,
			@RequestParam(value="endLine",defaultValue="10") String endLine,
			@RequestParam(value="maxLine",defaultValue="10") String maxLine,
			@RequestParam(value="orderDir",defaultValue="DESC") String orderDir,
			@RequestParam(value="orderBy",defaultValue="progress") String orderBy,
			@RequestParam(value="lotNo",defaultValue="") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="search",defaultValue="") String search,
			@RequestParam(value="type",defaultValue="") String type,
			HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView modelAndView = new ModelAndView();
		logger.info("CaseLotCenterController/selectCaseLotCenter查询合买大厅列表参数startLine:"+startLine
				+",endLine:"+endLine+",maxLine:"+maxLine+",orderDir:"+orderDir+",orderBy:"+orderBy
				+",lotNo:"+lotNo+",batchCode:"+batchCode+",search:"+search+",type:"+type);
		Map<String, String> parameterMap = new HashMap<String, String>();
		if(!"10".equals(endLine)){
			search = search==null||"".equals(search)?"":new String(search.getBytes("ISO-8859-1"), "UTF-8");
		}
		parameterMap.put("startLine", startLine);
		parameterMap.put("endLine", maxLine);
		parameterMap.put("orderDir", orderDir);
		parameterMap.put("orderBy", orderBy);
		parameterMap.put("lotNo", lotNo);
		parameterMap.put("batchCode", batchCode);
		parameterMap.put("search", search);
		parameterMap.put("sortState", "0");//0置顶+普通1置顶
		Map<String, Object> resultMap = selectAllUtil.selectCaseLots(parameterMap);
		List<CaseLot> caseLotList= new ArrayList<CaseLot>();
		caseLotList = (List<CaseLot>) resultMap.get("caseLotList");
		String totalPage = (String) resultMap.get("totalPage");
		String currentPageNo = (String) resultMap.get("currentPageNo");
		System.out.println("search==="+search);		
		modelAndView.addObject("startLine", startLine);
		modelAndView.addObject("endLine", endLine);
		modelAndView.addObject("maxLine", maxLine);
		modelAndView.addObject("totalPage", totalPage);
		modelAndView.addObject("nowPage", currentPageNo);
		modelAndView.addObject("orderDir", orderDir);
		modelAndView.addObject("orderBy", orderBy);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("search", search );
		modelAndView.addObject("type", type);
		modelAndView.addObject("caseLotList", caseLotList);
		modelAndView.setViewName("caseLotCenter");
		return modelAndView;
	}
	/**
	 * 查看合买订单详情
	 * @param caseLotId
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectCaseLotDetail")
	public ModelAndView selectCaseLotDetail(
			@RequestParam(value="caseLotId",defaultValue="") String caseLotId,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		logger.info("CaseLotCenterController/selectCaseLotDetail查询合买订单详情参数caseLotId："+caseLotId);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo==null?"":userInfo.getUserNo();
		CaseLot caseLot = selectAllUtil.selectCaseLotDetail(caseLotId, userNo);
		modelAndView.addObject("caseLot", caseLot);
		Map<String, Object> buysMap = selectAllUtil.selectCaseLotBuysSimplify(caseLotId, "0", "30", "buyTime", "ASC");
		if(buysMap!=null){
			String totalResult = (String) buysMap.get("totalResult");
			modelAndView.addObject("totalResult", totalResult);
		}
		modelAndView.setViewName("caseLotDetail");
		return modelAndView;
	}
	/**
	 * 查询参与人员列表
	 * @param caseLotId
	 * @param startLine
	 * @param endLine
	 * @param orderBy
	 * @param orderDir
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/selectCaseLotBuysSimplify")
	public ModelAndView selectCaseLotBuysSimplify(
			@RequestParam(value="caseLotId",defaultValue="") String caseLotId,
			@RequestParam(value="startLine",defaultValue="0") String startLine,
			@RequestParam(value="endLine",defaultValue="30") String endLine,
			@RequestParam(value="orderBy",defaultValue="buyTime") String orderBy,
			@RequestParam(value="orderDir",defaultValue="ASC") String orderDir,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		logger.info("CaseLotCenterController/selectCaseLotBuysSimplify查询合买订单详情参数caseLotId："+caseLotId
				+",startLine:"+startLine+",orderBy:"+orderBy);
		int maxLine = 30;
		Map<String, Object> buysMap = selectAllUtil.selectCaseLotBuysSimplify(caseLotId, startLine, maxLine+"", orderBy, orderDir);
		if(buysMap!=null){
			List<CaseLot> caseLots = (List<CaseLot>) buysMap.get("caseLots");
			String totalPage = (String) buysMap.get("totalPage");
			String currentPageNo = (String) buysMap.get("currentPageNo");
			String totalResult = (String) buysMap.get("totalResult");
			modelAndView.addObject("caseLots", caseLots);
			modelAndView.addObject("totalPage", totalPage);
			modelAndView.addObject("nowPage", currentPageNo);
			modelAndView.addObject("totalResult", totalResult);
			modelAndView.addObject("maxLine", maxLine);
		}
		modelAndView.addObject("caseLotId", caseLotId);
		modelAndView.setViewName("caseLotBuysSimplify");
		return modelAndView;
	}
	/**
	 * 查询合买查询列表
	 * @param startLine
	 * @param endLine
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/selectCaseLotBuys.do")
	public ModelAndView selectCaseLotBuys(
			@RequestParam(value="startLine",defaultValue="0") String startLine,
			@RequestParam(value="endLine",defaultValue="10") String endLine,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		logger.info("CaseLotCenterController/selectCaseLotBuys查询合买查询列表参数startLine:"+startLine);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo==null?"":userInfo.getUserNo();
		int maxLine = 10;
		Map<String, Object> caseLotBuysMap = selectAllUtil.selectCaseLotBuys(userNo, startLine, maxLine+"");
		if(caseLotBuysMap!=null){
			List<CaseLot> caseLots = (List<CaseLot>) caseLotBuysMap.get("caseLotList");
			String totalPage = (String) caseLotBuysMap.get("totalPage");
			String currentPageNo = (String) caseLotBuysMap.get("currentPageNo");
			modelAndView.addObject("caseLots", caseLots);
			modelAndView.addObject("totalPage", Integer.parseInt(totalPage) );
			modelAndView.addObject("nowPage", Integer.parseInt(currentPageNo));
			modelAndView.addObject("maxLine", maxLine);
		}
		modelAndView.setViewName("caseLotBuys");
		return modelAndView;
	}
	/**
	 * 查看合买订单详情
	 * @param caseLotId
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectCaseLotBuysDetail.do")
	public ModelAndView selectCaseLotBuysDetail(
			@RequestParam(value="caseLotId",defaultValue="") String caseLotId,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		logger.info("CaseLotCenterController/selectCaseLotBuysDetail查询合买订单详情参数caseLotId："+caseLotId);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo==null?"":userInfo.getUserNo();
		CaseLot caseLot = selectAllUtil.selectCaseLotDetail(caseLotId, userNo);
		modelAndView.addObject("caseLot", caseLot);
		Map<String, Object> buysMap = selectAllUtil.selectCaseLotBuysSimplify(caseLotId, "0", "30", "buyTime", "ASC");
		if(buysMap!=null){
			String totalResult = (String) buysMap.get("totalResult");
			modelAndView.addObject("totalResult", totalResult);
		}
		modelAndView.setViewName("caseLotBuysDetail");
		return modelAndView;
	}
	/**
	 * 用户参与合买
	 * @param totalAmt
	 * @param buyAmt
	 * @param minAmt
	 * @param safeAmt
	 * @param buyAmount
	 * @param buySafeAmount
	 * @param caseLotId
	 * @param request
	 * @return
	 */
	@RequestMapping("/betCaseLot.do")
	public ModelAndView betCaseLot(
			@RequestParam(value="totalAmt",defaultValue="") String totalAmt,
			@RequestParam(value="buyAmt",defaultValue="") String buyAmt,
			@RequestParam(value="minAmt",defaultValue="") String minAmt,
			@RequestParam(value="safeAmt",defaultValue="") String safeAmt,
			@RequestParam(value="buyAmount",defaultValue="") String buyAmount,
			@RequestParam(value="buySafeAmount",defaultValue="") String buySafeAmount,
			@RequestParam(value="caseLotId",defaultValue="") String caseLotId,
			HttpServletRequest request){
		logger.info("CaseLotCenterController/betCaseLot参与合买参数totalAmt："+totalAmt
				+",buyAmt:"+",minAmt:"+minAmt+",safeAmt:"+safeAmt+",buyAmount:"+buyAmount
				+",buySafeAmount:"+buySafeAmount+",caseLotId:"+caseLotId);
		ModelAndView modelAndView = new ModelAndView();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo==null?"":userInfo.getUserNo();
		String validateResult = ValidateLotteryUtil.validateBetCaseLot(totalAmt, buyAmt, safeAmt, minAmt, buyAmount, buySafeAmount);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView = selectCaseLotDetail(caseLotId, request);
			modelAndView.addObject("buyAmount", buyAmount);
			modelAndView.addObject("buySafeAmount", buySafeAmount);
			modelAndView.addObject("messageError", validateResult);
			logger.info("CaseLotCenterController/betCaseLot参与合买参数验证失败返回validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("CaseLotCenterController/betCaseLot参与合买参数验证通过");
		String messageError  = lotteryUtil.toBetCaseLot(userNo, Long.parseLong(buyAmount)*100, Long.parseLong(buySafeAmount)*100, caseLotId, channel);
		modelAndView.addObject("messageError", messageError);
		modelAndView.setViewName("betResult");
		return modelAndView;
	}
	/**
	 * 合买撤单
	 * @param caseLotId
	 * @param request
	 * @return
	 */
	@RequestMapping("/cancelCaseLot.do")
	public ModelAndView cancelCaseLot(
			@RequestParam(value="caseLotId",defaultValue="") String caseLotId,
			HttpServletRequest request){
		logger.info("CaseLotCenterController/cancelCaseLot合买撤单参数caseLotId:"+caseLotId);
		ModelAndView modelAndView = new ModelAndView();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo==null?"":userInfo.getUserNo();
		String messageError  = lotteryUtil.toCancelCaseLot(userNo, caseLotId);
		modelAndView.addObject("messageError", messageError);
		modelAndView.setViewName("betResult");
		return modelAndView;
	}
	/**
	 * 合买撤资
	 * @param caseLotId
	 * @param request
	 * @return
	 */
	@RequestMapping("/cancelCaseLotBuy.do")
	public ModelAndView cancelCaseLotBuy(
			@RequestParam(value="caseLotId",defaultValue="") String caseLotId,
			HttpServletRequest request){
		logger.info("CaseLotCenterController/cancelCaseLotBuy合买撤资参数caseLotId:"+caseLotId);
		ModelAndView modelAndView = new ModelAndView();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo==null?"":userInfo.getUserNo();
		String messageError  = lotteryUtil.toCancelCaseLotBuy(userNo, caseLotId);
		modelAndView.addObject("messageError", messageError);
		modelAndView.setViewName("betResult");
		return modelAndView;
	}
}
