package com.ruyicai.wap.util.validate;

import org.springframework.stereotype.Component;

@Component
public class ValidateJcUtil {

	/**
	 * 验证选择比赛场数
	 * @param submitType
	 * @param matchsTotal
	 * @return
	 */
	public static String validateMatchsTotal(String submitType,int matchsTotal){
		if("提交投注".equals(submitType)&&matchsTotal<1) return "至少选择1场比赛！";
		if("自由过关".equals(submitType)&&matchsTotal<2) return "至少选择2场比赛！";
		if("多串过关".equals(submitType)&&matchsTotal<3) return "至少选择3场比赛！";
		if(matchsTotal>10) return "最多选择10场比赛！";
		return "";
	}
	/**
	 * 验证倍数
	 * @param beiShu
	 * @return
	 */
	public static String validateBeiShu(String beiShu){
		String validatebeiShuResult = ValidateLotteryUtil.validateBeiShu(beiShu, 1, 100000);
		return validatebeiShuResult;
	}
}
