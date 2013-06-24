package com.ruyicai.wap.util.validate;

import java.util.ArrayList;
import java.util.List;

public class ValidateDoubleBallUtil {
	/**
	 * 验证双色球自选
	 * @param rballs
	 * @param bballs
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateBySelf(List<String> rballs,List<String> bballs,String beiShu,String addNumber){
		if(rballs==null)return "至少选择6个红球！";
		if(rballs.size()<6) return "至少选择6个红球！";
		if(bballs==null)return "至少选择1个蓝球！";
		if(bballs.size()<1) return "至少选择1个蓝球！";
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
	 * 验证双色球随机自选
	 * @param ballNumber
	 * @param ballType
	 * @return
	 */
	public static String validateByAutoSelf(String rballNumber,String bballNumber,String ballType){
		if("R".equals(ballType)&&ValidateLotteryUtil.validateIsNull(rballNumber)) return "号码个数不能为空！";
		if("R".equals(ballType)&&!ValidateLotteryUtil.validateIsInteger(rballNumber)) return "号码个数不正确！";
		if("B".equals(ballType)&&ValidateLotteryUtil.validateIsNull(bballNumber)) return "号码个数不能为空！";
		if("B".equals(ballType)&&!ValidateLotteryUtil.validateIsInteger(bballNumber)) return "号码个数不正确！";
		if("R".equals(ballType)&&Integer.parseInt(rballNumber)<6) return "至少选择6个红球！";
		if("R".equals(ballType)&&Integer.parseInt(rballNumber)>16) return "最多选择16个红球！";
		if("B".equals(ballType)&&Integer.parseInt(bballNumber)<1) return "至少选择1个蓝球！";
		if("B".equals(ballType)&&Integer.parseInt(bballNumber)>16) return "最多选择16个蓝球！";
		return "";
	}
	/**
	 * 验证双色球机选
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
	 * 验证胆拖投注
	 * @param rdballs
	 * @param rtballs
	 * @param bballs
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByDanTuo(List<String> rdballs,List<String> rtballs,List<String> bballs,String beiShu,String addNumber){
		if(rdballs==null)return "胆码选择1-5个！";
		if(rdballs.size()>5||rdballs.size()<1) return "胆码选择1-5个！";
		if(rtballs==null)return "拖码选择2-20个！";
		if(rtballs.size()<2||rtballs.size()>20) return "拖码选择2-20个！";
		if(rdballs.size()+rtballs.size()>=25||rdballs.size()+rtballs.size()<7) return "25>胆+拖≥7！";
		if(bballs==null)return "至少选择1个蓝球！";
		if(bballs.size()<1) return "至少选择1个蓝球！";
		List<String> list = new ArrayList<String>();
		list.addAll(rdballs);
		list.addAll(rtballs);
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
