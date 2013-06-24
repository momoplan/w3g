package com.ruyicai.wap.util.validate;

import java.util.ArrayList;
import java.util.List;

public class ValidateQiLeCaiUtil {

	/**
	 * 验证七乐彩自选
	 * @param balls
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateBySelf(List<String> balls,String beiShu,String addNumber){
		if(balls==null)return "至少选择7个号码！";
		if(balls.size()<7) return "至少选择7个号码！";
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
	 * 验证七乐彩随机自选
	 * @param ballNumber
	 * @return
	 */
	public static String validateByAutoSelf(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<7) return "至少选择7个号码！";
		if(Integer.parseInt(ballNumber)>30) return "最多选择30个号码！";
		return "";
	}
	/**
	 * 验证七乐彩机选
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

	/**
	 * 验证七乐彩胆拖投注
	 * @param dballs
	 * @param tballs
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByDanTuo(List<String> dballs,List<String> tballs,String beiShu,String addNumber){
		if(dballs==null)return "胆码选择1-6个！";
		if(dballs.size()>6||dballs.size()<1) return "胆码选择1-6个！";
		if(tballs==null)return "拖码选择2-20个！";
		if(tballs.size()<2||tballs.size()>20) return "拖码选择2-20个！";
		if(dballs.size()+tballs.size()<8) return "胆+拖≥8！";
		List<String> list = new ArrayList<String>();
		list.addAll(dballs);
		list.addAll(tballs);
		if(ValidateLotteryUtil.validateIsRepeat(list)) return "胆码和拖码不能重复！";
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
}
