package com.ruyicai.wap.util.validate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ValidateGuangDongHappyTenMinutesUtil {
	/**
	 * 验证广东快乐十分任选自选
	 * @param balls
	 * @param ballType
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByOptionSelf(List<String> balls,String ballType,String beiShu,String addNumber){
		if("S1".equals(ballType)&&balls==null) return "至少选择1个号码！";
		if("S1".equals(ballType)&&balls.size()<1) return "至少选择1个号码！";
		if("H1".equals(ballType)&&balls==null) return "至少选择1个号码！";
		if("H1".equals(ballType)&&balls.size()<1) return "至少选择1个号码！";
		if("R2".equals(ballType)&&balls==null) return "至少选择2个号码！";
		if("R2".equals(ballType)&&balls.size()<2) return "至少选择2个号码！";
		if("R3".equals(ballType)&&balls==null) return "至少选择3个号码！";
		if("R3".equals(ballType)&&balls.size()<3) return "至少选择3个号码！";
		if("R4".equals(ballType)&&balls==null) return "至少选择4个号码！";
		if("R4".equals(ballType)&&balls.size()<4) return "至少选择4个号码！";
		if("R5".equals(ballType)&&balls==null) return "至少选择5个号码！";
		if("R5".equals(ballType)&&balls.size()<5) return "至少选择5个号码！";
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
	 * 验证广东快乐十分随机任选
	 * @param ballNumber
	 * @param ballType
	 * @return
	 */
	public static String validateByAutoOptionSelf(String ballNumber,String ballType){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if("S1".equals(ballType)&&(Integer.parseInt(ballNumber)<1||Integer.parseInt(ballNumber)>18)) return "选择1-18个号码！";
		if("H1".equals(ballType)&&(Integer.parseInt(ballNumber)<1||Integer.parseInt(ballNumber)>2)) return "选择1-2个号码！";
		if("R2".equals(ballType)&&(Integer.parseInt(ballNumber)<2||Integer.parseInt(ballNumber)>20)) return "选择2-20个号码！";
		if("R3".equals(ballType)&&(Integer.parseInt(ballNumber)<3||Integer.parseInt(ballNumber)>20)) return "选择3-20个号码！";
		if("R4".equals(ballType)&&(Integer.parseInt(ballNumber)<4||Integer.parseInt(ballNumber)>20)) return "选择4-20个号码！";
		if("R5".equals(ballType)&&(Integer.parseInt(ballNumber)<5||Integer.parseInt(ballNumber)>20)) return "选择5-20个号码！";
		return "";
	}
	/**
	 * 验证广东快乐十分机选
	 * @param zhuShu
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByOptionAuto(String zhuShu,String beiShu,String addNumber,String betCodeView){
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
	 * 验证广东快乐十分胆拖
	 * @param dballs
	 * @param tballs
	 * @param ballType
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByDanTuo(List<String> dballs,List<String> tballs,String ballType,String beiShu,String addNumber){
		if("R2".equals(ballType)){
			if(dballs==null) return "胆码选择1个号码！";
			if(dballs.size()!=1) return "胆码选择1个号码！";
			if(tballs==null) return "拖码不能为空！";
			if(dballs.size()+tballs.size()<3||dballs.size()+tballs.size()>11) return "胆码+拖码选择3-11个号码！";
			List<String> list = new ArrayList<String>();
			list.addAll(dballs);
			list.addAll(tballs);
			if(ValidateLotteryUtil.validateIsRepeat(list)) return "胆码和拖码不能重复！";
		} 
		if("R3".equals(ballType)){
			if(dballs==null) return "胆码选择1-2个号码！";
			if(dballs.size()<1||dballs.size()>2) return "胆码选择1-2个号码！";
			if(tballs==null) return "拖码不能为空！";
			if(dballs.size()+tballs.size()<4||dballs.size()+tballs.size()>11) return "胆码+拖码选择4-11个号码！";
			List<String> list = new ArrayList<String>();
			list.addAll(dballs);
			list.addAll(tballs);
			if(ValidateLotteryUtil.validateIsRepeat(list)) return "胆码和拖码不能重复！";
		} 
		if("R4".equals(ballType)){
			if(dballs==null) return "胆码选择1-3个号码！";
			if(dballs.size()<1||dballs.size()>3) return "胆码选择1-3个号码！";
			if(tballs==null) return "拖码不能为空！";
			if(dballs.size()+tballs.size()<5||dballs.size()+tballs.size()>11) return "胆码+拖码选择5-11个号码！";
			List<String> list = new ArrayList<String>();
			list.addAll(dballs);
			list.addAll(tballs);
			if(ValidateLotteryUtil.validateIsRepeat(list)) return "胆码和拖码不能重复！";
		} 
		if("R5".equals(ballType)){
			if(dballs==null) return "胆码选择1-4个号码！";
			if(dballs.size()<1||dballs.size()>4) return "胆码选择1-4个号码！";
			if(tballs==null) return "拖码不能为空！";
			if(dballs.size()+tballs.size()<6||dballs.size()+tballs.size()>11) return "胆码+拖码选择6-11个号码！";
			List<String> list = new ArrayList<String>();
			list.addAll(dballs);
			list.addAll(tballs);
			if(ValidateLotteryUtil.validateIsRepeat(list)) return "胆码和拖码不能重复！";
		} 
		if("Z2".equals(ballType)){
			if(dballs==null) return "胆码选择1个号码！";
			if(dballs.size()!=1) return "胆码选择1个号码！";
			if(tballs==null) return "拖码不能为空！";
			if(dballs.size()+tballs.size()<3||dballs.size()+tballs.size()>11) return "胆码+拖码选择3-11个号码！";
			List<String> list = new ArrayList<String>();
			list.addAll(dballs);
			list.addAll(tballs);
			if(ValidateLotteryUtil.validateIsRepeat(list)) return "胆码和拖码不能重复！";
		} 
		if("Z3".equals(ballType)){
			if(dballs==null) return "胆码选择1-2个号码！";
			if(dballs.size()<1||dballs.size()>2) return "胆码选择1-2个号码！";
			if(tballs==null) return "拖码不能为空！";
			if(dballs.size()+tballs.size()<4||dballs.size()+tballs.size()>11) return "胆码+拖码选择4-11个号码！";
			List<String> list = new ArrayList<String>();
			list.addAll(dballs);
			list.addAll(tballs);
			if(ValidateLotteryUtil.validateIsRepeat(list)) return "胆码和拖码不能重复！";
		} 
		if("Q2".equals(ballType)){
			if(dballs==null) return "胆码选择1个号码！";
			if(dballs.size()!=1) return "胆码选择1个号码！";
			if(tballs==null) return "拖码不能为空！";
			if(dballs.size()+tballs.size()<3||dballs.size()+tballs.size()>11) return "胆码+拖码选择3-11个号码！";
			List<String> list = new ArrayList<String>();
			list.addAll(dballs);
			list.addAll(tballs);
			if(ValidateLotteryUtil.validateIsRepeat(list)) return "胆码和拖码不能重复！";
		} 
		if("Q3".equals(ballType)){
			if(dballs==null) return "胆码选择1-2个号码！";
			if(dballs.size()<1||dballs.size()>2) return "胆码选择1-2个号码！";
			if(tballs==null) return "拖码不能为空！";
			if(dballs.size()+tballs.size()<4||dballs.size()+tballs.size()>11) return "胆码+拖码选择4-11个号码！";
			List<String> list = new ArrayList<String>();
			list.addAll(dballs);
			list.addAll(tballs);
			if(ValidateLotteryUtil.validateIsRepeat(list)) return "胆码和拖码不能重复！";
		} 
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
	 * 验证广东快乐十分选二连组
	 * @param balls
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateBySelectTwoLinkGroupSelf(List<String> balls,String beiShu,String addNumber){
		if(balls==null) return "选择2-20个号码！";
		if(balls.size()<2||balls.size()>20) return "选择2-20个号码！";
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
	 * 验证广东快乐十分随机选二连组
	 * @param ballNumber
	 * @return
	 */
	public static String validateByAutoSelectTwoLinkGroupSelf(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<2||Integer.parseInt(ballNumber)>20) return "选择2-20个号码！";
		return "";
	}
	/**
	 * 验证广东快乐十分前三组选
	 * @param balls
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByForwardThreeGroupSelect(List<String> balls,String beiShu,String addNumber){
		if(balls==null) return "选择3-20个号码！";
		if(balls.size()<3||balls.size()>20) return "选择3-20个号码！";
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
	 * 验证广东快乐十分随机前三组选
	 * @param ballNumber
	 * @return
	 */
	public static String validateByAutoForwardThreeGroupSelect(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<3||Integer.parseInt(ballNumber)>20) return "选择3-20个号码！";
		return "";
	}
	/**
	 * 验证广东快乐十分选二连直
	 * @param ball1s
	 * @param ball2s
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateBySelectTwoLinkDirectSelf(List<String> ball1s,List<String> ball2s,String beiShu,String addNumber){
		if(ball1s==null) return "第一位选择1-20个号码！";
		if(ball1s.size()<1||ball1s.size()>20) return "第一位选择1-20个号码！";
		if(ball2s==null) return "第二位选择1-20个号码！";
		if(ball2s.size()<1||ball2s.size()>20) return "第二位选择1-20个号码！";
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
	 * 验证广东快乐十分随机选二连直
	 * @param ballNumber
	 * @return
	 */
	public static String validateByAutoSelectTwoLinkDirectSelf(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<1||Integer.parseInt(ballNumber)>20) return "选择1-20个号码！";
		return "";
	}
	/**
	 * 验证广东快乐十分前三直选
	 * @param ball1s
	 * @param ball2s
	 * @param ball3s
	 * @param beiShu
	 * @param addNumber
	 * @return
	 */
	public static String validateByForwardThreeDirectSelect(List<String> ball1s,List<String> ball2s,List<String> ball3s,String beiShu,String addNumber){
		if(ball1s==null) return "第一位选择1-20个号码！";
		if(ball1s.size()<1||ball1s.size()>20) return "第一位选择1-20个号码！";
		if(ball2s==null) return "第二位选择1-20个号码！";
		if(ball2s.size()<1||ball2s.size()>20) return "第二位选择1-20个号码！";
		if(ball3s==null) return "第三位选择1-20个号码！";
		if(ball3s.size()<1||ball3s.size()>20) return "第三位选择1-20个号码！";
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
	 * 验证广东快乐十分随机前三直选
	 * @param ballNumber
	 * @return
	 */
	public static String validateByAutoForwardThreeDirectSelect(String ballNumber){
		if(ValidateLotteryUtil.validateIsNull(ballNumber)) return "号码个数不能为空！";
		if(!ValidateLotteryUtil.validateIsInteger(ballNumber)) return "号码个数不正确！";
		if(Integer.parseInt(ballNumber)<1||Integer.parseInt(ballNumber)>20) return "选择1-20个号码！";
		return "";
	}
}
