package com.ruyicai.wap.controller.lottery;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.Constants;
import com.ruyicai.wap.util.bet.LotteryFootballUtil;
import com.ruyicai.wap.util.bet.LotteryUtil;
import com.ruyicai.wap.util.validate.ValidateFootballUtil;
import com.ruyicai.wap.util.validate.ValidateLotteryUtil;
@Controller
@RequestMapping("/football")
public class LotteryFootballController {
	@Autowired
	LotteryFootballUtil lotteryFootballUtil;
	@Autowired
	LotteryFootballIndexController lotteryFootballIndexController;
	@Autowired
	CommonUtil commonUtil;
	@Autowired
	LotteryUtil lotteryUtil;
	@RequestMapping("/byShengFuCai")
	public ModelAndView byShengFuCai(
			@RequestParam(value="lotNo",defaultValue="T01003") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="beiShu",defaultValue="") String beiShu,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		List<String> zhuMaList = new ArrayList<String>();
		String error  ="";
		String betCode = "";
		for(int i=1;i<15;i++){
			String code = "";
			String[] zhuMa= request.getParameterValues("zhuMa"+i);
			if(zhuMa==null){
				error+=i+",";
				code += "";
			}else{
				for (String string : zhuMa) {
					code += string;
				}
			}
			zhuMaList.add(code);
			betCode += code+",";
		}
		if(error!=null&&!"".equals(error)){
			if(error.endsWith(",")) 
				error = error.substring(0, error.length()-1);
			modelAndView = lotteryFootballIndexController.byFootball(lotNo, batchCode, "");
			String s = (String) modelAndView.getModel().get("messageError");
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("zhuMaList", zhuMaList);
			if(s==null||"".equals(s)){
				modelAndView.addObject("messageError", "比赛:"+error+"场请选择赛果！");
			}else{
				modelAndView.addObject("messageError", s);

			}
			modelAndView.setViewName(getViewName(lotNo));
			return modelAndView;
		}
		String validateResult = ValidateFootballUtil.validateByShengFuCai(beiShu);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView = lotteryFootballIndexController.byFootball(lotNo, batchCode, "");
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("zhuMaList", zhuMaList);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName(getViewName(lotNo));
			return modelAndView;
		}
		int zhuShu = 1;
		for(int m = 0;m<zhuMaList.size();m++){
			zhuShu *= zhuMaList.get(m).length();
		}
		long amount = 2*zhuShu*Integer.parseInt(beiShu);
		String validateAmountResult = ValidateLotteryUtil.validateMaxAmount(2, Integer.parseInt(beiShu), zhuShu);
		if(validateAmountResult!=null&&!"".equals(validateAmountResult)){
			modelAndView = lotteryFootballIndexController.byFootball(lotNo, batchCode, "");
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("zhuMaList", zhuMaList);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName(getViewName(lotNo));
			return modelAndView;

		}
		String playType = "";
		if(zhuShu==1){
			playType = "单式";
		}else{
			playType = "复式";
		}
		String amountView = commonUtil.getBalanceFormat(amount*100+"", 2);
		String lotName = lotteryUtil.getLotNameByLotNo(lotNo);
		if(betCode.endsWith(",")) 
			betCode = betCode.substring(0, betCode.length()-1);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("oneAmount", 2);
		modelAndView.addObject("zhuShu", zhuShu);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("amountView", amountView);
		modelAndView.addObject("amount", amount);
		modelAndView.addObject("lotName", lotName);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("betCodeView", betCode);
		modelAndView.addObject("playType", playType);
		commonUtil.setToken(request, modelAndView);
		modelAndView.setViewName("zuCaiBetDetail");
		return modelAndView;
	}
	@RequestMapping("/byRenXuan9Chang")
	public ModelAndView byRenXuan9Chang(
			@RequestParam(value="lotNo",defaultValue="T01004") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="beiShu",defaultValue="") String beiShu,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		List<String> zhuMaList = new ArrayList<String>();
		int kong  =0;
		String betCodeStr = "";
		int dan=0;
		String danError="";
		for(int i=1;i<15;i++){
			String code = "";
			String[] zhuMa= request.getParameterValues("zhuMa"+i);
			if(zhuMa==null){
				kong++;
				code += "#";
			}else{
				for (String string : zhuMa) {
					if(string.indexOf("4")>-1){
						dan++;
					}
					code += string;
				}
				if("4".equals(code)){
					danError+=i+",";
				}
			}
			zhuMaList.add(code);
			betCodeStr += code+",";
		}
		if(danError!=null&&!"".equals(danError)){
			if(danError.endsWith(",")) 
				danError = danError.substring(0, danError.length()-1);
			modelAndView = lotteryFootballIndexController.byFootball(lotNo, batchCode, "");
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("zhuMaList", zhuMaList);
			modelAndView.addObject("messageError", "比赛"+danError+"未选择赛果,不能设胆！");
			modelAndView.setViewName(getViewName(lotNo));
			return modelAndView;
		}
		String messageError = "";
		if(14-kong<9) messageError="至少选择9场比赛！";
		if(14-kong==9&&dan>0) messageError="胆拖玩法需要选择比赛大于9场！";
		if(14-kong>9&&dan>8) messageError="最多可选8场比赛设胆！";
		if(messageError!=null&&!"".equals(messageError)){
			modelAndView = lotteryFootballIndexController.byFootball(lotNo, batchCode, "");
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("zhuMaList", zhuMaList);
			modelAndView.addObject("messageError", messageError);
			modelAndView.setViewName(getViewName(lotNo));
			return modelAndView;
		}
		String validateResult = ValidateFootballUtil.validateByShengFuCai(beiShu);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView = lotteryFootballIndexController.byFootball(lotNo, batchCode, "");
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("zhuMaList", zhuMaList);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName(getViewName(lotNo));
			return modelAndView;
		}
		int zhuShu = 1;
		String betCodeView = "";
		String betCode = "";
		if(betCodeStr.indexOf("4")>-1){
			String danCode = "";
			String tuoCode = "";
			for(int i=1;i<15;i++){
				String[] zhuMa= request.getParameterValues("zhuMa"+i);
				String code = "";
				if(zhuMa==null){
					danCode+="#,";
					tuoCode+="#,";
				}else{
					for (String string : zhuMa) {
						code+=string;
					}
					if(code.indexOf("4")>-1){
						danCode+=code.replace("4", "")+",";
						tuoCode+="#,";
					}else{
						danCode+="#,";
						tuoCode+=code+",";
					}
				}
				
			}
			if(danCode.endsWith(",")) 
				danCode = danCode.substring(0, danCode.length()-1);
			if(tuoCode.endsWith(",")) 
				tuoCode = tuoCode.substring(0, tuoCode.length()-1);
			betCode = danCode+"$"+tuoCode;
			betCodeView = "胆码:"+danCode+"<br/>拖码:"+tuoCode;
		}else{
			if(betCodeStr.endsWith(",")) 
				betCode = betCodeStr.substring(0, betCodeStr.length()-1);
			betCodeView = betCode;
		}
		zhuShu = Integer.parseInt(lotteryFootballUtil.getRenXuan9ZhuShu(betCode));
		long amount = 2*zhuShu*Integer.parseInt(beiShu);
		String validateAmountResult = ValidateLotteryUtil.validateMaxAmount(2, Integer.parseInt(beiShu), zhuShu);
		if(validateAmountResult!=null&&!"".equals(validateAmountResult)){
			modelAndView = lotteryFootballIndexController.byFootball(lotNo, batchCode, "");
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("zhuMaList", zhuMaList);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName(getViewName(lotNo));
			return modelAndView;

		}
		String playType = "";
		if(zhuShu==1){
			playType = "单式";
		}else if(betCodeStr.indexOf("4")>-1){
			playType = "胆拖";
		}else{
			playType = "复式";
		}
		String amountView = commonUtil.getBalanceFormat(amount*100+"", 2);
		String lotName = lotteryUtil.getLotNameByLotNo(lotNo);
		
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("oneAmount", 2);
		modelAndView.addObject("zhuShu", zhuShu);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("amountView", amountView);
		modelAndView.addObject("amount", amount);
		modelAndView.addObject("lotName", lotName);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("betCodeView", betCodeView);
		modelAndView.addObject("playType", playType);
		commonUtil.setToken(request, modelAndView);
		modelAndView.setViewName("zuCaiBetDetail");
		return modelAndView;
	}
	@RequestMapping("/by6ChangBanQuanChang")
	public ModelAndView by6ChangBanQuanChang(
			@RequestParam(value="lotNo",defaultValue="T01006") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="beiShu",defaultValue="") String beiShu,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		List<String> zhuMaBList = new ArrayList<String>();
		List<String> zhuMaQList = new ArrayList<String>();
		String errorB  ="";
		String errorQ  ="";
		String betCode = "";
		for(int i=1;i<7;i++){
			String codeB = "";
			String codeQ = "";
			String[] zhuMaB= request.getParameterValues("zhuMaB"+i);
			String[] zhuMaQ= request.getParameterValues("zhuMaQ"+i);
			if(zhuMaB==null){
				errorB+=i+",";
				codeB += "";
			}else{
				for (String string : zhuMaB) {
					codeB += string;
				}
			}
			zhuMaBList.add(codeB);
			if(zhuMaQ==null){
				errorQ+=i+",";
				codeQ += "";
			}else{
				for (String string : zhuMaQ) {
					codeQ += string;
				}
			}
			zhuMaQList.add(codeQ);
			betCode += codeB+","+codeQ+",";
		}
		if(errorB!=null&&!"".equals(errorB)){
			if(errorB.endsWith(",")) 
				errorB = errorB.substring(0, errorB.length()-1);
			modelAndView = lotteryFootballIndexController.byFootball(lotNo, batchCode, "");
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("zhuMaBList", zhuMaBList);
			modelAndView.addObject("zhuMaQList", zhuMaQList);
			modelAndView.addObject("messageError", "比赛:"+errorB+"场请选择赛果！");
			modelAndView.setViewName(getViewName(lotNo));
			return modelAndView;
		}
		if(errorQ!=null&&!"".equals(errorQ)){
			if(errorQ.endsWith(",")) 
				errorQ = errorQ.substring(0, errorQ.length()-1);
			modelAndView = lotteryFootballIndexController.byFootball(lotNo, batchCode, "");
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("zhuMaBList", zhuMaBList);
			modelAndView.addObject("zhuMaQList", zhuMaQList);
			modelAndView.addObject("messageError", "比赛:"+errorQ+"场请选择赛果！");
			modelAndView.setViewName(getViewName(lotNo));
			return modelAndView;
		}
		String validateResult = ValidateFootballUtil.validateByShengFuCai(beiShu);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView = lotteryFootballIndexController.byFootball(lotNo, batchCode, "");
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("zhuMaBList", zhuMaBList);
			modelAndView.addObject("zhuMaQList", zhuMaQList);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName(getViewName(lotNo));
			return modelAndView;
		}
		int zhuShu = 1;
		List<String> zhuMaList = new ArrayList<String>();
		zhuMaList.addAll(zhuMaBList);
		zhuMaList.addAll(zhuMaQList);
		for(int m = 0;m<zhuMaList.size();m++){
			zhuShu *= zhuMaList.get(m).length();
		}
		long amount = 2*zhuShu*Integer.parseInt(beiShu);
		String validateAmountResult = ValidateLotteryUtil.validateMaxAmount(2, Integer.parseInt(beiShu), zhuShu);
		if(validateAmountResult!=null&&!"".equals(validateAmountResult)){
			modelAndView = lotteryFootballIndexController.byFootball(lotNo, batchCode, "");
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("zhuMaBList", zhuMaBList);
			modelAndView.addObject("zhuMaQList", zhuMaQList);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName(getViewName(lotNo));
			return modelAndView;

		}
		String playType = "";
		if(zhuShu==1){
			playType = "单式";
		}else{
			playType = "复式";
		}
		String amountView = commonUtil.getBalanceFormat(amount*100+"", 2);
		String lotName = lotteryUtil.getLotNameByLotNo(lotNo);
		if(betCode.endsWith(",")) 
			betCode = betCode.substring(0, betCode.length()-1);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("oneAmount", 2);
		modelAndView.addObject("zhuShu", zhuShu);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("amountView", amountView);
		modelAndView.addObject("amount", amount);
		modelAndView.addObject("lotName", lotName);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("betCodeView", betCode);
		modelAndView.addObject("playType", playType);
		commonUtil.setToken(request, modelAndView);
		modelAndView.setViewName("zuCaiBetDetail");
		return modelAndView;
	}
	@RequestMapping("/by4ChangJinQiu")
	public ModelAndView by4ChangJinQiu(
			@RequestParam(value="lotNo",defaultValue="T01005") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="beiShu",defaultValue="") String beiShu,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		List<String> zhuMaZList = new ArrayList<String>();
		List<String> zhuMaKList = new ArrayList<String>();
		String errorZ  ="";
		String errorK  ="";
		String betCode = "";
		for(int i=1;i<5;i++){
			String codeZ = "";
			String codeK = "";
			String[] zhuMaZ= request.getParameterValues("zhuMaZ"+i);
			String[] zhuMaK= request.getParameterValues("zhuMaK"+i);
			if(zhuMaZ==null){
				errorZ+=i+",";
				codeZ += "";
			}else{
				for (String string : zhuMaZ) {
					codeZ += string;
				}
			}
			zhuMaZList.add(codeZ);
			if(zhuMaK==null){
				errorK+=i+",";
				codeK += "";
			}else{
				for (String string : zhuMaK) {
					codeK += string;
				}
			}
			zhuMaKList.add(codeK);
			betCode += codeZ+","+codeK+",";
		}
		if(errorZ!=null&&!"".equals(errorZ)){
			if(errorZ.endsWith(",")) 
				errorZ = errorZ.substring(0, errorZ.length()-1);
			modelAndView = lotteryFootballIndexController.byFootball(lotNo, batchCode, "");
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("zhuMaZList", zhuMaZList);
			modelAndView.addObject("zhuMaKList", zhuMaKList);
			modelAndView.addObject("messageError", "比赛:"+errorZ+"场请选择赛果！");
			modelAndView.setViewName(getViewName(lotNo));
			return modelAndView;
		}
		if(errorK!=null&&!"".equals(errorK)){
			if(errorK.endsWith(",")) 
				errorK = errorK.substring(0, errorK.length()-1);
			modelAndView = lotteryFootballIndexController.byFootball(lotNo, batchCode, "");
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("zhuMaZList", zhuMaZList);
			modelAndView.addObject("zhuMaKList", zhuMaKList);
			modelAndView.addObject("messageError", "比赛:"+errorK+"场请选择赛果！");
			modelAndView.setViewName(getViewName(lotNo));
			return modelAndView;
		}
		String validateResult = ValidateFootballUtil.validateByShengFuCai(beiShu);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView = lotteryFootballIndexController.byFootball(lotNo, batchCode, "");
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("zhuMaZList", zhuMaZList);
			modelAndView.addObject("zhuMaKList", zhuMaKList);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName(getViewName(lotNo));
			return modelAndView;
		}
		int zhuShu = 1;
		List<String> zhuMaList = new ArrayList<String>();
		zhuMaList.addAll(zhuMaZList);
		zhuMaList.addAll(zhuMaKList);
		for(int m = 0;m<zhuMaList.size();m++){
			zhuShu *= zhuMaList.get(m).length();
		}
		long amount = 2*zhuShu*Integer.parseInt(beiShu);
		String validateAmountResult = ValidateLotteryUtil.validateMaxAmount(2, Integer.parseInt(beiShu), zhuShu);
		if(validateAmountResult!=null&&!"".equals(validateAmountResult)){
			modelAndView = lotteryFootballIndexController.byFootball(lotNo, batchCode, "");
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("zhuMaZList", zhuMaZList);
			modelAndView.addObject("zhuMaKList", zhuMaKList);
			modelAndView.addObject("messageError", validateAmountResult);
			modelAndView.setViewName(getViewName(lotNo));
			return modelAndView;

		}
		String playType = "";
		if(zhuShu==1){
			playType = "单式";
		}else{
			playType = "复式";
		}
		String amountView = commonUtil.getBalanceFormat(amount*100+"", 2);
		String lotName = lotteryUtil.getLotNameByLotNo(lotNo);
		if(betCode.endsWith(",")) 
			betCode = betCode.substring(0, betCode.length()-1);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("oneAmount", 2);
		modelAndView.addObject("zhuShu", zhuShu);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("amountView", amountView);
		modelAndView.addObject("amount", amount);
		modelAndView.addObject("lotName", lotName);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("betCodeView", betCode);
		modelAndView.addObject("playType", playType);
		commonUtil.setToken(request, modelAndView);
		modelAndView.setViewName("zuCaiBetDetail");
		return modelAndView;
	}
	public String getViewName(String lotNo){
		if(Constants.LOTNO_ZC_SFC_14C.equals(lotNo)) return "football/byShengFuCai";
		if(Constants.LOTNO_ZC_R9C.equals(lotNo)) return "football/byRenXuan9Chang";
		if(Constants.LOTNO_ZC_4C_JQC.equals(lotNo)) return "football/by4ChangJinQiu";
		if(Constants.LOTNO_ZC_6C_BQC.equals(lotNo)) return "football/by6ChangBanQuanChang";
		return "";
	}
}
