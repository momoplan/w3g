package com.ruyicai.wap.util.validate;

import java.util.List;

public class ValidateShiShiCaiUtil {
	/**
	 * 验证时时彩1星自选
	 * @param gballs
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByOneStarSelf(List<String> gballs,String beiShu,String addNumber){
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
	 * 验证时时彩1星随机自选
	 * @param ballNumber
	 * @return
	 */
	public static String validateByOneStarAutoSelf(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<1) return "至少选择1个号码！";
		if(Integer.parseInt(ballNumber)>10) return "最多选择10个号码！";
		return "";
	}
	/**
	 * 验证时时彩1星机选
	 * @param zhuShu
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByOneStarAuto(String zhuShu,String beiShu,String addNumber,String betCodeView){
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
	 * 验证时时彩2星自选
	 * @param gballs
	 * @param sballs
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByTwoStarDirect(List<String> gballs,List<String> sballs,String beiShu,String addNumber){
		if(gballs==null) return "个位至少选择1个号码！";
		if(gballs.size()<1) return "个位至少选择1个号码！";
		if(sballs==null) return "十位至少选择1个号码！";
		if(sballs.size()<1) return "十位至少选择1个号码！";
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
	 * 验证时时彩2星随机自选
	 * @param ballNumber
	 * @return
	 */
	public static String validateByTwoStarAutoDirect(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<1) return "至少选择1个号码！";
		if(Integer.parseInt(ballNumber)>10) return "最多选择10个号码！";
		return "";
	}
	/**
	 * 验证时时彩2星机选
	 * @param zhuShu
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByTwoStarAuto(String zhuShu,String beiShu,String addNumber,String betCodeView){
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
	 * 验证时时彩2星组选
	 * @param balls
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByTwoStarGroup(List<String> balls,String beiShu,String addNumber){
		if(balls==null) return "至少选择2个号码！";
		if(balls.size()<2) return "至少选择2个号码！";
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
	 * 验证时时彩2星随机组选
	 * @param ballNumber
	 * @return
	 */
	public static String validateByTwoStarAutoGroup(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<2) return "至少选择2个号码！";
		if(Integer.parseInt(ballNumber)>10) return "最多选择10个号码！";
		return "";
	}
	/**
	 * 验证时时彩2星和值
	 * @param balls
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByTwoStarHeZhi(List<String> balls,String beiShu,String addNumber){
		if(balls.size()<1) return "个位至少选择1个号码！";
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
	 * 验证时时彩2星随机和值
	 * @param ballNumber
	 * @return
	 */
	public static String validateByTwoStarAutoHeZhi(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<1) return "至少选择1个号码！";
		if(Integer.parseInt(ballNumber)>19) return "最多选择19个号码！";
		return "";
	}
	/**
	 * 验证时时彩3星自选
	 * @param gballs
	 * @param sballs
	 * @param bballs
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByThreeStarDirect(List<String> gballs,List<String> sballs,List<String> bballs,String beiShu,String addNumber){
		if(gballs==null) return "个位至少选择1个号码！";
		if(gballs.size()<1) return "个位至少选择1个号码！";
		if(sballs==null) return "十位至少选择1个号码！";
		if(sballs.size()<1) return "十位至少选择1个号码！";
		if(bballs==null) return "百位至少选择1个号码！";
		if(bballs.size()<1) return "百位至少选择1个号码！";
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
	 * 验证时时彩3星随机自选
	 * @param ballNumber
	 * @return
	 */
	public static String validateByThreeStarAutoDirect(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<1||Integer.parseInt(ballNumber)>10) return "选择1-10个号码！";
		return "";
	}
	/**
	 * 验证时时彩3星机选
	 * @param zhuShu
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByThreeStarAuto(String zhuShu,String beiShu,String addNumber,String betCodeView){
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
	 * 验证时时彩3星组三
	 * @param balls
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByThreeStarGroup3(List<String> balls,String beiShu,String addNumber){
		if(balls==null) return "至少选择2个号码！";
		if(balls.size()<2) return "至少选择2个号码！";
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
	 * 验证时时彩3星随机组三
	 * @param ballNumber
	 * @return
	 */
	public static String validateByThreeStarAutoGroup3(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<2) return "至少选择2个号码！";
		if(Integer.parseInt(ballNumber)>10) return "最多选择10个号码！";
		return "";
	}
	/**
	 * 验证时时彩3星组6
	 * @param balls
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByThreeStarGroup6(List<String> balls,String beiShu,String addNumber){
		if(balls==null) return "至少选择3个号码！";
		if(balls.size()<3) return "至少选择3个号码！";
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
	 * 验证时时彩3星随机组六
	 * @param ballNumber
	 * @return
	 */
	public static String validateByThreeStarAutoGroup6(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<3) return "至少选择3个号码！";
		if(Integer.parseInt(ballNumber)>10) return "最多选择10个号码！";
		return "";
	}
	/**
	 * 验证时时彩五星自选
	 * @param wballs
	 * @param qballs
	 * @param bballs
	 * @param sballs
	 * @param gballs
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByFiveStarDirect(List<String> wballs,List<String> qballs,List<String> bballs,List<String> sballs,List<String> gballs,String beiShu,String addNumber){
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
	 * 验证时时彩五星随机自选
	 * @param ballNumber
	 * @return
	 */
	public static String validateByFiveStarAutoDirect(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<1) return "至少选择1个号码！";
		if(Integer.parseInt(ballNumber)>10) return "最多选择10个号码！";
		return "";
	}
	/**
	 * 验证时时彩五星机选
	 * @param zhuShu
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByFiveStarAuto(String zhuShu,String beiShu,String addNumber,String betCodeView){
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
	 * 验证时时彩五星通选
	 * @param wballs
	 * @param qballs
	 * @param bballs
	 * @param sballs
	 * @param gballs
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByFiveStarTongSelf(List<String> wballs,List<String> qballs,List<String> bballs,List<String> sballs,List<String> gballs,String beiShu,String addNumber){
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
	 * 验证时时彩五星通选随机
	 * @param ballNumber
	 * @return
	 */
	public static String validateByFiveStarTongAutoSelf(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<1) return "至少选择1个号码！";
		if(Integer.parseInt(ballNumber)>10) return "最多选择10个号码！";
		return "";
	}
	/**
	 * 验证时时彩五星通选机选
	 * @param zhuShu
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByFiveStarTongAuto(String zhuShu,String beiShu,String addNumber){
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
	public static String validateByDaXiaoDanShuangSelf(String gball,String sball,String beiShu,String addNumber){
		if(gball==null||"".equals(gball)) return "个位选择1个号码！";
		if(gball.length()!=1) return "个位选择1个号码！";
		if(sball==null||"".equals(sball)) return "十位选择1个号码！";
		if(sball.length()!=1) return "十位选择1个号码！";
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
