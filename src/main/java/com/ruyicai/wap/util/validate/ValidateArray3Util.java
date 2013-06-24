package com.ruyicai.wap.util.validate;

import java.util.List;


public class ValidateArray3Util {


	/**
	 * 验证排列三直选
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
	 * 验证排列三随机直选
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
	 * 验证排列三机选
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
	 * 验证排列三组选
	 * @param balls
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByGroup(List<String> balls,String beiShu,String addNumber){
		if(balls.size()<2||balls.size()>3) return "选择2-3个号码！";
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
	 * 验证排列三随机组选
	 * @param ballNumber
	 * @return
	 */
	public static String validateByAutoGroup(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<2||Integer.parseInt(ballNumber)>3) return "选择2-3个号码！";
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
	 * 验证排列三随机组三自选
	 * @param ballNumber
	 * @return
	 */
	public static String validateByAutoGroup3Self(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<1||Integer.parseInt(ballNumber)>9) return "选择2-9个号码！";
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
	 * 验证随机组六自选
	 * @param ballNumber
	 * @return
	 */
	public static String validateByAutoGroup6Self(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<3||Integer.parseInt(ballNumber)>9) return "选择3-9个号码！";
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
		if(balls.size()!=1) return "选择1个号码！";
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
	 * 验证随机直选和值
	 * @param ballNumber
	 * @return
	 */
	public static String validateByAutoDirectHeZhi(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)!=1) return "选择1个号码！";
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
		if(balls.size()<1||balls.size()>9) return "选择1-9个号码！";
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
	 * 验证随机组三和值
	 * @param ballNumber
	 * @return
	 */
	public static String validateByAutoGroup3HeZhi(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<1||Integer.parseInt(ballNumber)>9) return "选择1-9个号码！";
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
		if(balls.size()<1||balls.size()>9) return "选择1-9个号码！";
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
	 * 验证随机组六和值
	 * @param ballNumber
	 * @return
	 */
	public static String validateByAutoGroup6HeZhi(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<1||Integer.parseInt(ballNumber)>9) return "选择1-9个号码！";
		return "";
	}
	/**
	 * 验证组三包号
	 * @param balls
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByGroup3BaoHao(List<String> balls,String beiShu,String addNumber){
		if(balls.size()<2||balls.size()>10) return "选择2-10个号码！";
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
	 * 验证随机组三包号
	 * @param ballNumber
	 * @return
	 */
	public static String validateByAutoGroup3BaoHao(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<2||Integer.parseInt(ballNumber)>10) return "选择2-10个号码！";
		return "";
	}
	/**
	 * 验证组六包号
	 * @param balls
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByGroup6BaoHao(List<String> balls,String beiShu,String addNumber){
		if(balls.size()<4||balls.size()>9) return "选择4-9个号码！";
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
	 * 验证随机组六包号
	 * @param ballNumber
	 * @return
	 */
	public static String validateByAutoGroup6BaoHao(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<4||Integer.parseInt(ballNumber)>9) return "选择4-9个号码！";
		return "";
	}
}
