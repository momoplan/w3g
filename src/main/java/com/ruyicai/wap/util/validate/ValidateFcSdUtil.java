package com.ruyicai.wap.util.validate;

import java.util.ArrayList;
import java.util.List;

public class ValidateFcSdUtil {


	/**
	 * 验证福彩3D直选
	 * @param bballs
	 * @param sballs
	 * @param gballs
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByDirect(List<String> bballs,List<String> sballs,List<String> gballs,String beiShu,String addNumber){
		if(bballs==null)return "百位至少选择1个号码！";
		if(bballs.size()<1) return "百位至少选择1个号码！";
		if(sballs==null)return "十位至少选择1个号码！";
		if(sballs.size()<1) return "十位至少选择1个号码！";
		if(gballs==null)return "个位至少选择1个号码！";
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
	 * 验证福彩3D随机自选
	 * @param ballNumber
	 * @return
	 */
	public static String validateByAutoDirect(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<1) return "至少选择1个号码！";
		if(Integer.parseInt(ballNumber)>10) return "最多选择10个号码！";
		return "";
	}
	/**
	 * 验证福彩3D随机组三自选
	 * @param ballNumber
	 * @return
	 */
	public static String validateByAutoGroup3Self(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<1) return "至少选择2个号码！";
		if(Integer.parseInt(ballNumber)>9) return "最多选择9个号码！";
		return "";
	}
	/**
	 * 验证福彩3D随机组六自选
	 * @param ballNumber
	 * @return
	 */
	public static String validateByAutoGroup6Self(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<1) return "至少选择3个号码！";
		if(Integer.parseInt(ballNumber)>9) return "最多选择9个号码！";
		return "";
	}
	/**
	 * 验证福彩3D机选
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
	 * 验证组三自选单式
	 * @param ball2s
	 * @param ball1s
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByGroup3SelfSingle(List<String> ball2s,List<String> ball1s,String beiShu,String addNumber){
		if(ball2s.size()==1) return "选择1个号码！";
		if(ball1s.size()==1) return "选择1个号码！";
		ball1s.addAll(ball2s);
		if(ValidateLotteryUtil.validateIsRepeat(ball1s)) return "注码不能重复！";
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
	 * 验证组三自选
	 * @param balls
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByGroup3Self(List<String> balls,String beiShu,String addNumber){
		if(balls==null)return "至少选择2个号码！";
		if(balls.size()<2||balls.size()>9) return "选择2-9个号码！";
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
	 * 验证组六自选
	 * @param balls
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByGroup6Self(List<String> balls,String beiShu,String addNumber){
		if(balls==null)return "至少选择3个号码！";
		if(balls.size()<3||balls.size()>9) return "选择3-9个号码！";
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
	 * 验证直选和值
	 * @param balls
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByDirectHeZhi(List<String> balls,String beiShu,String addNumber){
		if(balls==null)return "选择1个号码！";
		if(!(balls.size()==1)) return "选择1个号码！";
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
	 * 验证组三和值
	 * @param balls
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByGroup3HeZhi(List<String> balls,String beiShu,String addNumber){
		if(balls==null)return "选择1个号码！";
		if(balls.size()==1) return "选择1个号码！";
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
	 * 验证组六和值
	 * @param balls
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByGroup6HeZhi(List<String> balls,String beiShu,String addNumber){
		if(balls==null)return "选择1个号码！";
		if(balls.size()==1) return "选择1个号码！";
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
	 * 验证福彩3D胆拖投注
	 * @param dballs
	 * @param tballs
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByDanTuo(List<String> dballs,List<String> tballs,String beiShu,String addNumber){
		if(dballs==null)return "胆码选择1-2个！";
		if(dballs.size()>2||dballs.size()<1) return "胆码选择1-2个！";
		if(tballs==null)return "拖码选择1-9个！";
		if(tballs.size()<1||tballs.size()>9) return "拖码选择1-9个！";
		if(dballs.size()+tballs.size()<4) return "胆+拖≥4！";
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
