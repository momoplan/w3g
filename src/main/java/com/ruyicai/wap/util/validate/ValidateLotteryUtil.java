package com.ruyicai.wap.util.validate;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateLotteryUtil {

	/**
	 * 验证赠送
	 * @param reciverMobiles
	 * @param blessing
	 * @return
	 */
	public static String validatePresent(String reciverMobiles,String blessing){
		if(validateIsNull(reciverMobiles)){
			return "手机号不能为空！";
		}
		String regex = "^((13|14|15|18)\\d{9}\\,)*(13|14|15|18)\\d{9}$";
		String regex2 = "^(13|14|15|18)\\d{9}$";
		if(reciverMobiles.indexOf(",")==-1){
			Pattern pattern = Pattern.compile(regex2);
			Matcher matcher = pattern.matcher(reciverMobiles);
			if(!matcher.matches()){
				return "手机号不正确!";
			}
		}else{
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(reciverMobiles);
			if(!matcher.matches()){
				return "手机号不正确!";
			}
		}
		if(blessing.length()<=20&&blessing.length()>0){
			String r = "^([a-zA-Z0-9\u4E00-\u9FA5]|[,]|[，]|[“]|[”]|[.]|[。]|[？]|[?]|[！]|[!]|[:]|[：])+$";
			Pattern pattern = Pattern.compile(r);
			Matcher match = pattern.matcher(blessing);
			if(!match.matches()){
				return "赠言不能带特殊符号!";
			}
		}
		if(blessing.length()>20){
			return "赠言太长,请重新填写！";
		}
		return "";
		
	}
	public static void main(String[] args) {
		String r = "^([a-zA-Z0-9\u4E00-\u9FA5]|[,]|[，]|[“]|[.]|[。]|[？]|[?]|[！]|[!])+$";
		Pattern pattern = Pattern.compile(r);
		Matcher match = pattern.matcher("123,.!");
		System.out.println(match.matches());
	}
	public static String validateCaseLot(String amount,String buyAmt,String safeAmt,String minAmt,String desc){
		if(validateIsNull(buyAmt)){
			return "认购金额不能为空！";
		}
		if(validateIsNull(safeAmt)){
			return "保底金额不能为空！";
		}
		if(validateIsNull(minAmt)){
			return "最低认购金额不能为空！";
		}
		if(!validateIsInteger(buyAmt)){
			return "认购金额必须为整数！";
		}
		if(!validateIsInteger(safeAmt)){
			return "保底金额必须为整数！";
		}
		if(!validateIsInteger(minAmt)){
			return "最低认购金额必须为整数！";
		}
		int buy = Integer.parseInt(buyAmt);
		int safe = Integer.parseInt(safeAmt);
		int min = Integer.parseInt(minAmt);
		int totalAmount = Integer.parseInt(amount);
		if(6>totalAmount){
			return "方案总金额不能小于6元！";
		}
		if(buy>totalAmount){
			return "认购金额不能大于方案总金额！";
		}
		if(safe>totalAmount){
			return "保底金额不能大于方案总金额！";
		}
		if(buy+safe>totalAmount){
			return "认购+保底不能大于方案总金额！";
		}
		if(buy<1&&safe<1){
			return "至少认购或保底1元！";
		}
		if(min>totalAmount){
			return "最低认购金额不能大于方案总金额！";
		}
		if(min<1){
			return "最低认购金额不能小于1元！";
		}
		if(validateIsSpecialCharacter(desc)&&desc.length()>0){
			return "方案描述不能包含特殊字符！";
		}
		if(desc.getBytes().length>50){
			return "方案描述字符过长！";
		}
		return "";
	}
	public static String validateBetCaseLot(String amount,String buyAmt,String safeAmt,String minAmt,String buyAmount,String buySafeAmount){
		if(validateIsNull(buyAmount)){
			return "认购金额不能为空！";
		}
		if(validateIsNull(buySafeAmount)){
			return "保底金额不能为空！";
		}
		if(!validateIsInteger(buyAmount)){
			return "认购金额必须为整数！";
		}
		if(!validateIsInteger(buySafeAmount)){
			return "保底金额必须为整数！";
		}
		double buy = Double.parseDouble(buyAmt);
		double safe = Double.parseDouble(safeAmt);
		double min = Double.parseDouble(minAmt);
		double totalAmount = Double.parseDouble(amount);
		int buyA = Integer.parseInt(buyAmount);
		int buySafe = Integer.parseInt(buySafeAmount);
		//验证认购金额和保底金额是否同时小于1
		if(buyA<1&&buySafe<1){
			return "请输入认购金额！";
		}
		//认购金额不能大于可认购金额
		if(buyA>totalAmount-buy){
			return "认购金额不能大于可认购金额！";
		}
		//可认购金额大于最低认购金额，认购金额不能小于最低认购金额
		if(min<totalAmount-buy&&buyA<min&&buyA>0){
			return "认购金额不能小于最低认购金额！";
		}
		//保底金额不能大于可保底金额
		if(buySafe>(totalAmount-buy-safe>0 ? totalAmount-buy-safe : 0 )){
			return "保底金额不能大于可保底金额！";
		}
		return "";
	}
	/**
	 * 验证是否为空
	 * @param paramter
	 * @return 空返回true
	 */
	public static boolean validateIsNull(String paramter){
		if("".equals(paramter.trim())||paramter==null){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 验证是否为整数
	 * @param paramter
	 * @return
	 */
	public static boolean validateIsInteger(String paramter){
		Pattern pattern = Pattern.compile("^([1-9]{1}[0-9]*|0)");
		Matcher matcher = pattern.matcher(paramter);
		return matcher.matches();
	}
	/**
	 * 验证是否包含特殊字符(包含返回true)
	 * @param paramter
	 * @return
	 */
	public static boolean validateIsSpecialCharacter(String paramter){
		String regex="[^%$&]{1,}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(paramter);
		System.out.println(matcher.matches());
		return !matcher.matches();
	}
	/**
	 * 验证倍数
	 * @param beiShu
	 * @param min 最小允许倍数
	 * @param max 最大允许倍数 
	 * @return
	 */
	public static String validateBeiShu(String beiShu,int min,int max){
		if(validateIsNull(beiShu)) return "倍数不能为空！";
		if(!validateIsInteger(beiShu)) return "倍数格式不正确！";
		if(Integer.parseInt(beiShu)<min) return "倍数不能小于"+min+"倍！";
		if(Integer.parseInt(beiShu)>max) return "倍数不能大于"+max+"倍！";
		return "";
	}
	/**
	 * 验证期数
	 * @param addNumber
	 * @param min 最小允许期数
	 * @param max 最大允许期数
	 * @return
	 */
	public static String validateAddNumber(String addNumber,int min,int max){
		if(validateIsNull(addNumber)) return "期数不能为空！";
		if(!validateIsInteger(addNumber)) return "期数格式不正确！";
		if(Integer.parseInt(addNumber)<min) return "期数不能小于"+min+"期！";
		if(Integer.parseInt(addNumber)>max) return "期数不能大于"+max+"期！";
		return "";
	}
	/**
	 * 验证注数
	 * @param zhuShu
	 * @param min 最小允许注数
	 * @param max最大允许注数
	 * @return
	 */
	public static String validateZhuShu(String zhuShu,int min,int max){
		if(validateIsNull(zhuShu)) return "注数不能为空！";
		if(!validateIsInteger(zhuShu)) return "注数格式不正确！";
		if(Integer.parseInt(zhuShu)<min) return "注数不能小于"+min+"注！";
		if(Integer.parseInt(zhuShu)>max) return "注数不能大于"+max+"注！";
		return "";
	}
	/**
	 * 验证个数
	 * @param geShu
	 * @param min 最小允许个数
	 * @param max 最大允许个数
	 * @return
	 */
	public static String validateGeShu(String geShu,int min,int max){
		if(validateIsNull(geShu)) return "个数不能为空！";
		if(!validateIsInteger(geShu)) return "个数格式不正确！";
		if(Integer.parseInt(geShu)<1) return "个数不能小于"+min+"个！";
		if(Integer.parseInt(geShu)>max) return "个数不能大于"+max+"个！";
		return "";
	}
	/**
	 * 验证单倍允许最多金额
	 * @param oneAmount
	 * @param beiShu
	 * @param zhuShu
	 * @return
	 */
	public static String validateMaxAmount(int oneAmount,int beiShu,int zhuShu){
		if(oneAmount*zhuShu>20000) return "单倍金额不能超过2万";
		return "";
	}
	/**
	 * 验证广东快乐10分单倍允许最大金额
	 * @param oneAmount
	 * @param beiShu
	 * @param zhuShu
	 * @return
	 */
	public static String validateGDKL10MaxAmount(int oneAmount,int beiShu,int zhuShu){
		if(oneAmount*zhuShu>18000) return "单倍金额不能超过18000";
		return "";
	}
	/**
	 * 验证注码是否重复
	 * @param betCodeList
	 * @return
	 */
	public static boolean validateIsRepeat(List<String> betCodeList) {
		for (int i = 0; i < betCodeList.size(); i++) {
			for (int j = betCodeList.size() - 1; j > i; j--) {
				if (betCodeList.get(i).equals(betCodeList.get(j))) {
					return true;
				}
			}
		}
		return false;
	}
}
