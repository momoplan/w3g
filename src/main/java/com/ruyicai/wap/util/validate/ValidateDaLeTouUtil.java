package com.ruyicai.wap.util.validate;

import java.util.ArrayList;
import java.util.List;

public class ValidateDaLeTouUtil {

	/**
	 * 验证大乐透自选
	 * @param rballs
	 * @param bballs
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateBySelf(List<String> rballs,List<String> bballs,String beiShu,String addNumber){
		if(rballs==null) return "前区号码选择5-18个号码！";
		if(rballs.size()<5||rballs.size()>18) return "前区号码选择5-18个号码！";
		if(bballs==null) return "后区号码至少选择2个号码！";
		if(bballs.size()<2) return "后区号码至少选择2个号码！";
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
	 * 验证大乐透随机自选
	 * @param ballNumber
	 * @param ballType
	 * @return
	 */
	public static String validateByAutoSelf(String ballNumber,String ballType){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if("R".equals(ballType)&&Integer.parseInt(ballNumber)<5) return "至少选择5个号码！";
		if("R".equals(ballType)&&Integer.parseInt(ballNumber)>18) return "最多选择18个号码！";
		if("B".equals(ballType)&&(Integer.parseInt(ballNumber)<2||Integer.parseInt(ballNumber)>12)) return "后区选择2-12个号码！";
		return "";
	}
	/**
	 * 验证大乐透机选
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
	 * 验证大乐透胆拖投注
	 * @param dballs
	 * @param tballs
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByDanTuo(List<String> rdballs,List<String> rtballs,List<String> bdballs,List<String> btballs,String beiShu,String addNumber){
		if(rdballs!=null){
			if(rdballs.size()>4) return "前区胆码选择0-4个！";
		}
		if(rtballs==null) return "前区拖码选择1-28个！";
		if(rtballs.size()<1||rtballs.size()>28) return "前区拖码选择1-28个！";
		if(rdballs!=null){
			if(rdballs.size()+rtballs.size()<6) return "前区胆+拖≥6！";
		}else{
			if(rtballs.size()<6) return "前区胆+拖≥6！";
		}
		List<String> rlist = new ArrayList<String>();
		if(rdballs!=null)rlist.addAll(rdballs);
		rlist.addAll(rtballs);
		if(ValidateLotteryUtil.validateIsRepeat(rlist)) return "前区胆码和拖码不能重复！";
//		if(bdballs!=null){
//			if(bdballs.size()!=1) return "后区胆码选择1个！";
//		}
		if(bdballs==null) return "后区胆码选择1个！";
		if(bdballs.size()!=1) return "后区胆码选择1个！";

		if(btballs==null) return "后区区拖码选择2-12个！";
		if(btballs.size()<2||btballs.size()>12) return "后区拖码选择2-12个！";
//		if(bdballs!=null){
		if(bdballs.size()+btballs.size()<3) return "后区胆+拖≥3！";
//		}else{
//			if(btballs.size()<2) return "后区胆+拖≥3！";
//		}
		List<String> blist = new ArrayList<String>();
		if(bdballs!=null)blist.addAll(bdballs);
		blist.addAll(btballs);
		if(ValidateLotteryUtil.validateIsRepeat(blist)) return "后区胆码和拖码不能重复！";
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
	 * 验证生肖乐
	 * @param balls
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByShengXiaoLe(List<String> balls,String beiShu,String addNumber){
		if(balls==null) return "选择2-12个号码！";
		if(balls.size()<2||balls.size()>12) return "选择2-12个号码！";
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
	 * 验证随机生肖乐
	 * @param ballNumber
	 * @return
	 */
	public static String validateByAutoShengXiaoLe(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<2||Integer.parseInt(ballNumber)>12) return "选择2-12个号码！";
		return "";
	}
}
