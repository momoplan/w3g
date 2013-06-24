package com.ruyicai.wap.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.Constants;
import com.ruyicai.wap.util.SelectAllUtil;
import com.ruyicai.wap.util.bet.LotteryUtil;
import com.ruyicai.wap.vo.JingCaiSaiGuo;
import com.ruyicai.wap.vo.WinInfo;

@Controller
@RequestMapping("/winInfo")
public class WinInfoController {
	Logger logger = Logger.getLogger(WinInfoController.class);
	@Autowired
	SelectAllUtil selectAllUtil;
	@Autowired
	LotteryUtil lotteryUtil;
	@Autowired
	CommonUtil commonUtil;
	/**
	 * 首页开奖信息
	 * @return
	 */
	@RequestMapping("/selectIndexWinInfo")
	public ModelAndView selectIndexWinInfo(ModelAndView modelAndView){
		try {
			WinInfo ssqWinInfo = selectAllUtil.selectTwininfoByLotno(Constants.LOTNO_SSQ, "1").get(0);
			WinInfo fc3dWinInfo = selectAllUtil.selectTwininfoByLotno(Constants.LOTNO_FC3D, "1").get(0);
			WinInfo dltWinInfo = selectAllUtil.selectTwininfoByLotno(Constants.LOTNO_DLT, "1").get(0);
			modelAndView.addObject("ssqWinInfo", ssqWinInfo);
			modelAndView.addObject("fc3dWinInfo", fc3dWinInfo);
			modelAndView.addObject("dltWinInfo", dltWinInfo);
		} catch (Exception e) {
			logger.error("首页查询开奖信息异常:"+e.getMessage());
		}
		
		return modelAndView;
	}
	/**
	 * 开奖首页信息
	 * @return
	 */
	@RequestMapping("/selectWinInfoCenter")
	public ModelAndView selectWinInfoCenter(){
		ModelAndView modelAndView = new ModelAndView();
		WinInfo ssqWinInfo = selectAllUtil.selectTwininfoByLotno(Constants.LOTNO_SSQ, "1").get(0);
		WinInfo fc3dWinInfo = selectAllUtil.selectTwininfoByLotno(Constants.LOTNO_FC3D, "1").get(0);
		WinInfo qlcWinInfo = selectAllUtil.selectTwininfoByLotno(Constants.LOTNO_QLC, "1").get(0);
		WinInfo dltWinInfo = selectAllUtil.selectTwininfoByLotno(Constants.LOTNO_DLT, "1").get(0);
		WinInfo plsWinInfo = selectAllUtil.selectTwininfoByLotno(Constants.LOTNO_PL3, "1").get(0);
		WinInfo plwWinInfo = selectAllUtil.selectTwininfoByLotno(Constants.LOTNO_PL5, "1").get(0);
		WinInfo qxcWinInfo = selectAllUtil.selectTwininfoByLotno(Constants.LOTNO_QXC, "1").get(0);
		WinInfo s5f22WinInfo = selectAllUtil.selectTwininfoByLotno(Constants.LOTNO_22X5, "1").get(0);
		WinInfo sscWinInfo = selectAllUtil.selectTwininfoByLotno(Constants.LOTNO_SSC, "1").get(0);
		WinInfo jx11x5WinInfo = selectAllUtil.selectTwininfoByLotno(Constants.LOTNO_DLC_JX11X5, "1").get(0);
		WinInfo sd11x5WinInfo = selectAllUtil.selectTwininfoByLotno(Constants.LOTNO_11YDJ_SD11X5, "1").get(0);
		WinInfo gd11x5WinInfo = selectAllUtil.selectTwininfoByLotno(Constants.LOTNO_GD11X5, "1").get(0);
		WinInfo gdkl10fWinInfo = selectAllUtil.selectTwininfoByLotno(Constants.LOTNO_GDKL10F, "1").get(0);
		WinInfo zcsfcWinInfo = selectAllUtil.selectTwininfoByLotno(Constants.LOTNO_ZC_SFC_14C, "1").get(0);
		WinInfo zcrx9WinInfo = selectAllUtil.selectTwininfoByLotno(Constants.LOTNO_ZC_R9C, "1").get(0);
		WinInfo zc6cbWinInfo = selectAllUtil.selectTwininfoByLotno(Constants.LOTNO_ZC_6C_BQC, "1").get(0);
		WinInfo zcjqcWinInfo = selectAllUtil.selectTwininfoByLotno(Constants.LOTNO_ZC_4C_JQC, "1").get(0);
		modelAndView.addObject("ssqWinInfo", ssqWinInfo);
		modelAndView.addObject("fc3dWinInfo", fc3dWinInfo);
		modelAndView.addObject("qlcWinInfo", qlcWinInfo);
		modelAndView.addObject("dltWinInfo", dltWinInfo);
		modelAndView.addObject("plsWinInfo", plsWinInfo);
		modelAndView.addObject("plwWinInfo", plwWinInfo);
		modelAndView.addObject("qxcWinInfo", qxcWinInfo);
		modelAndView.addObject("s5f22WinInfo", s5f22WinInfo);
		modelAndView.addObject("sscWinInfo", sscWinInfo);
		modelAndView.addObject("jx11x5WinInfo", jx11x5WinInfo);
		modelAndView.addObject("sd11x5WinInfo", sd11x5WinInfo);
		modelAndView.addObject("gd11x5WinInfo", gd11x5WinInfo);
		modelAndView.addObject("gdkl10fWinInfo", gdkl10fWinInfo);
		modelAndView.addObject("zcsfcWinInfo", zcsfcWinInfo);
		modelAndView.addObject("zcrx9WinInfo", zcrx9WinInfo);
		modelAndView.addObject("zc6cbWinInfo", zc6cbWinInfo);
		modelAndView.addObject("zcjqcWinInfo", zcjqcWinInfo);
		modelAndView.setViewName("winInfoCenter");
		return modelAndView;
	}
	/**
	 * 查询开奖列表
	 * @param lotNo
	 * @param issueNum
	 * @return
	 */
	@RequestMapping("/selectWinInfoList")
	public ModelAndView selectWinInfoList(
			@RequestParam(value="lotNo",defaultValue="")String lotNo,
			@RequestParam(value="issueNum",defaultValue="10")String issueNum){
		logger.info("WinInfoController/selectWinInfoList查询开奖列表参数lotNo:"+lotNo+",issueNum:"+issueNum);
		ModelAndView modelAndView = new ModelAndView();
		List<WinInfo> winInfos = selectAllUtil.selectTwininfoByLotno(lotNo, issueNum);
		String lotName = lotteryUtil.getLotNameByLotNo(lotNo);
		modelAndView.addObject("winInfos", winInfos);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("lotName", lotName);
		modelAndView.addObject("issueNum", issueNum);
		modelAndView.setViewName("winInfoHistory");
		return modelAndView;
		
	}
	/**
	 * 查询开奖详情
	 * @param lotNo
	 * @param issue
	 * @return
	 */
	@RequestMapping("/selectWinInfo")
	public ModelAndView selectWinInfo(
			@RequestParam(value="lotNo",defaultValue="")String lotNo,
			@RequestParam(value="issue",defaultValue="")String issue){
		logger.info("WinInfoController/selectWinInfo查询开奖详情参数lotNo:"+lotNo+",issue:"+issue);
		ModelAndView modelAndView = new ModelAndView();
		WinInfo winInfo = selectAllUtil.selectTwininfo(lotNo, issue);
		modelAndView.addObject("winInfo", winInfo);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("issue", issue);
		modelAndView.setViewName("winInfoDetail");
		return modelAndView;
		
	}
	@RequestMapping("/selectJcSaiGuo")
	public ModelAndView selectJcSaiGuo(
			@RequestParam(value="date",defaultValue="")String date,
			@RequestParam(value="type",defaultValue="")String type
			){
		ModelAndView modelAndView = new ModelAndView();
		if("".equals(date)||date==null){
			Date date2 = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			date = dateFormat.format(date2);
		}
		List<JingCaiSaiGuo> jingCaiSaiGuos = selectAllUtil.selectJcSaiGuo(date.replaceAll("-", ""), type);
		List<String> queryTimes = queryTime();
		String typeName = "";
		if("0".equals(type)){
			typeName = "竞彩篮球";
		}
		if("1".equals(type)){
			typeName = "竞彩足球";
		}
		modelAndView.addObject("dateTime", date);
		modelAndView.addObject("type", type);
		modelAndView.addObject("jingCaiSaiGuos", jingCaiSaiGuos);
		modelAndView.addObject("queryTimes", queryTimes);
		modelAndView.addObject("typeName", typeName);
		modelAndView.setViewName("jingCaiSaiGuo");
		return modelAndView;
	}
	/**
	 * 用此方法 返回
	 * 
	 * @return [2012-05-14, 2012-05-13, 2012-05-12, 2012-05-11, 2012-05-10,
	 *         2012-05-09]
	 */
	public List<String> queryTime() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i > -6; i--) {
			String a = getAfterDate(i);// 当前时间
			list.add(a);
		}
		return list;
	}

	// 获取现在时间-1
	public String getAfterDate(int i) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, i);
		Date date1 = cal.getTime();
		String date = FormatDate(date1);
		return date;
	}
	// 确定日期字符串格式
	public String FormatDate(Date time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(time);
		return date;
	}
}
