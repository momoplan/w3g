package com.ruyicai.wap.util.validate;

public class ValidateFootballUtil {

	public static String validateByShengFuCai(String beiShu){
		String validatebeiShuResult = ValidateLotteryUtil.validateBeiShu(beiShu, 1, 10000);
		if(validatebeiShuResult!=null&&!"".equals(validatebeiShuResult)){
			return validatebeiShuResult;
		}
		return "";
	}
}
