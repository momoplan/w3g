package com.ruyicai.wap.util.validate;

import java.util.List;

public class ValidateSevenStarUtil {

	/**
	 * 验证七星彩自选
	 * @param ball1s
	 * @param ball2s
	 * @param ball3s
	 * @param ball4s
	 * @param ball5s
	 * @param ball6s
	 * @param ball7s
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateBySelf(List<String> ball1s,List<String> ball2s,List<String> ball3s,List<String> ball4s,List<String> ball5s,List<String> ball6s,List<String> ball7s,String beiShu,String addNumber){
		if(ball1s==null) return "第一位至少选择1个号码！";
		if(ball1s.size()<1) return "第一位至少选择1个号码！";
		if(ball2s==null) return "第二位至少选择1个号码！";
		if(ball2s.size()<1) return "第二位至少选择1个号码！";
		if(ball3s==null) return "第三位至少选择1个号码！";
		if(ball3s.size()<1) return "第三位至少选择1个号码！";
		if(ball4s==null) return "第四位至少选择1个号码！";
		if(ball4s.size()<1) return "第四位至少选择1个号码！";
		if(ball5s==null) return "第五位至少选择1个号码！";
		if(ball5s.size()<1) return "第五位至少选择1个号码！";
		if(ball6s==null) return "第六位至少选择1个号码！";
		if(ball6s.size()<1) return "第六位至少选择1个号码！";
		if(ball7s==null) return "第七位至少选择1个号码！";
		if(ball7s.size()<1) return "第七位至少选择1个号码！";
		String validatebeiShuResult = ValidateLotteryUtil.validateBeiShu(beiShu, 1, 10000);
		if(validatebeiShuResult!=null&&!"".equals(validatebeiShuResult)){
			return validatebeiShuResult;
		}
		String validateAddNumberResult = ValidateLotteryUtil.validateAddNumber(addNumber, 1, 99);
		if(validateAddNumberResult!=null&&!"".equals(validateAddNumberResult)){
			return validateAddNumberResult;
		}
		return "";
	}

	/**
	 * 验证七星彩随机自选
	 * @param ballNumber
	 * @return
	 */
	public static String validateByAutoSelf(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<1) return "至少选择1个号码！";
		if(Integer.parseInt(ballNumber)>10) return "最多选择10个号码！";
		return "";
	}
	/**
	 * 验证七星彩机选
	 * @param zhuShu
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByAuto(String zhuShu,String beiShu,String addNumber,String betCodeView){
		if(ValidateLotteryUtil.validateIsNull(betCodeView)) return "请先机选注码！";
		String validateZhuShuResult = ValidateLotteryUtil.validateZhuShu(zhuShu, 1, 99);
		if(validateZhuShuResult!=null&&!"".equals(validateZhuShuResult)){
			return validateZhuShuResult;
		}
		String validateBeiShuResult = ValidateLotteryUtil.validateBeiShu(beiShu, 1, 10000);
		if(validateBeiShuResult!=null&&!"".equals(validateBeiShuResult)){
			return validateBeiShuResult;
		}
		String validateAddNumberResult = ValidateLotteryUtil.validateAddNumber(addNumber, 1, 99);
		if(validateAddNumberResult!=null&&!"".equals(validateAddNumberResult)){
			return validateAddNumberResult;
		}
		return "";
	}
}
