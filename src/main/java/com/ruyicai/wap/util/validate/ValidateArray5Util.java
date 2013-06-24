package com.ruyicai.wap.util.validate;

import java.util.List;

public class ValidateArray5Util {

	/**
	 * 验证排列五自选参数
	 * @param wballs
	 * @param qballs
	 * @param bballs
	 * @param sballs
	 * @param gballs
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateBySelf(List<String> wballs,List<String> qballs,List<String> bballs,List<String> sballs,List<String> gballs,String beiShu,String addNumber){
		if(wballs==null) return "万位至少选择1个号码！";
		if(wballs.size()<1) return "万位至少选择1个号码！";
		if(qballs==null) return "千位至少选择1个号码！";
		if(qballs.size()<1) return "千位至少选择1个号码！";
		if(bballs==null) return "百位至少选择1个号码！";
		if(bballs.size()<1) return "百位至少选择1个号码！";
		if(sballs==null) return "十位至少选择1个号码！";
		if(sballs.size()<1) return "十位至少选择1个号码！";
		if(gballs==null) return "个位至少选择1个号码！";
		if(gballs.size()<1) return "个位至少选择1个号码！";
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
	 * 验证排列五随机自选
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
	 * 验证排列五机选
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
