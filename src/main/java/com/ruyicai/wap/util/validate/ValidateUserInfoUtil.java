package com.ruyicai.wap.util.validate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.SelectAllUtil;
import com.ruyicai.wap.vo.UserInfo;

/**
 * 用户信息验证类
 */
@Component
public class ValidateUserInfoUtil {
	private Logger logger = Logger.getLogger(ValidateUserInfoUtil.class);
	@Autowired
	SelectAllUtil selectAllUtil;
	public String validateLogin(HttpServletRequest request,
			String userName, String passWord) {
		if (validateIsNull(userName)) {
			return "用户名不能为空！";
		}
		if (validateIsNull(passWord)) {
			return "密码不能为空！";
		}
//		if (validateIsNull(checkCode)) {
//			return "验证码不能为空！";
//		}
//		String RANDOMCODEKEY = (String) request.getSession().getAttribute(
//				RandomValidateCodeController.RANDOMCODEKEY);
//		if (!checkCode.equals(RANDOMCODEKEY)) {
//			return "验证码不正确！";
//		}
		return "";
	}

	/**
	 * 验证注册信息
	 * 
	 * @param userName
	 * @param passWord
	 * @param rePassWord
	 * @param checkBox
	 * @return
	 */
	public String validateRegister(String userName, String passWord,
			String rePassWord, String checkBox) {
		if (validateIsNull(userName))
			return "用户名不能为空！";
		if (validateIsNull(passWord))
			return "密码不能为空！";
		if (validateIsNull(rePassWord))
			return "重复密码不能为空！";
		if (!passWord.equals(rePassWord))
			return "两次密码输入不一致,请重新输入！";
		if (!"1".equals(checkBox))
			return "请同意用户协议！";
		if (!validateUserName(userName))
			return "用户名格式不正确！";
		if (passWord.length() < 4 || passWord.length() > 16)
			return "密码格式不正确！";
		return "";
	}

	/**
	 * 验证注册信息
	 * 
	 * @param userName
	 * @param passWord
	 * @param rePassWord
	 * @param checkBox
	 * @return
	 */
	public String validateResetPassWord(String oldPassWord,
			String newPassWord, String reNewPassWord, String userName) {
		if (validateIsNull(oldPassWord))
			return "原密码不能为空！";
		if (validateIsNull(newPassWord))
			return "新密码不能为空！";
		if (validateIsNull(reNewPassWord))
			return "重复新密码不能为空！";
		if (!newPassWord.equals(reNewPassWord))
			return "两次密码输入不一致,请重新输入！";
		if (newPassWord.length() < 4 || newPassWord.length() > 16)
			return "密码格式不正确！";
		UserInfo userInfo = selectAllUtil.selectUserInfoByUserName(userName);
		String passWord = userInfo.getPassWord();
		if (!passWord.equals(CommonUtil.encoderByMd5(oldPassWord)))
			return "原密码不正确！";
		return "";
	}

	/**
	 * 验证找回密码
	 * 
	 * @param userName
	 * @param mobileId
	 * @return
	 */
	public String validateRetrievePassWord(String userName,
			String mobileId) {
		if (validateIsNull(userName))
			return "用户名不能为空！";
		if (validateIsNull(mobileId))
			return "手机号不能为空！";
		UserInfo userInfo = selectAllUtil.selectUserInfoByUserName(userName);
		if (userInfo == null)
			return "用户名不存在！";
		if (!"1".equals(userInfo.getState()))
			return "用户名不正确";
		String userMobileId = userInfo.getMobileId();
		if (!mobileId.equals(userMobileId))
			return "手机号不正确！";
		return "";
	}

	/**
	 * 验证用户提现参数
	 * 
	 * @param amount提现金额
	 * @param drawAmount可提现金额
	 * @param bankName银行名称
	 * @param name真实姓名
	 * @param bankNumber银行卡号
	 * @param bankAddress开卡地址
	 * @param userName
	 * @return
	 */
	public String validateDrawCash(String amount, String drawAccount,
			String bankName, String name, String bankNumber,
			String userName, String passWord) {
		// 验证是否有可提现金额
		if ("0".equals(drawAccount) || "0.00".equals(drawAccount)) {
			return "您的可提现金额不足！";
		}
		// 验证是否为空
		if (validateIsNull(name)) {
			return "持卡人名不能为空！";
		}
		if (validateIsNull(amount)) {
			return "提现金额不能为空！";
		}
//		if (validateIsNull(bankAddress)) {
//			return "开卡地址不能为空！";
//		}
		if (validateIsNull(bankNumber)) {
			return "银行卡号不能为空！";
		}
		if (validateIsNull(bankName) || "00".equals(bankName)) {
			return "请选择开卡银行！";
		}
		// 判断提现金额是否正确
		Pattern pattern = Pattern.compile("^(([1-9]\\d*)|0)(\\.\\d{1,2})?$");
		Matcher matcher = pattern.matcher(amount);
		if (!matcher.matches()) {
			return "提现金额格式不正确！";
		}
		// 判断提现金额是否比可提现金额大
		if (Double.valueOf(drawAccount).doubleValue() < Double.valueOf(amount)
				.doubleValue()) {
			return "提现金额不能大于可提现金额！";
		}
		if (Double.valueOf(drawAccount) >= 10
				&& Double.valueOf(amount).doubleValue() < 10) {
			return "可提现金额大于10元至少提现10元！";
		}
		// 判断可提现金额是否小于10
		if (Double.valueOf(drawAccount) < 10
				&& Double.valueOf(drawAccount).doubleValue() != Double.valueOf(
						amount).doubleValue()) {
			return "可提现金额小于10元,必须1次提清！";
		}
		// 验证银行卡号是否正确
		Pattern bnpattern = Pattern.compile("^([0-9]{16,21})");
		Matcher bnmatcher = bnpattern.matcher(bankNumber);
		if (!bnmatcher.matches()) {
			return "银行卡号不正确！";
		}
		UserInfo userInfo = new UserInfo();
		userInfo = selectAllUtil.selectUserInfoByUserName(userName);
		String userPassWord = userInfo.getPassWord();
		if (!CommonUtil.encoderByMd5(passWord).equals(userPassWord)) {
			return "密码不正确！";
		}
		return "";
	}

	/**
	 * 完善用户信息验证
	 * 
	 * @param name
	 * @param nickName
	 * @param certId
	 * @return
	 */
	public String validatePerfectUserInfo(String name, String nickName,
			String certId) {
		if (validateIsNull(nickName)) {
			return "昵称不能为空！";
		}
		if (validateIsNull(name)) {
			return "真是姓名不能为空！";
		}
		if (validateIsNull(certId)) {
			return "身份证号不能为空！";
		}
		if (!validateNickNameFormat(nickName)) {
			return "昵称格式不正确！";
		}
		int nickNameLength = chineseLength(nickName);
		if (nickNameLength < 4 || nickNameLength > 16) {
			return "昵称长度不正确！";
		}
		if (!validateNameFormat(name)) {
			return "真实姓名格式不正确！";
		}
		try {
			if ("111111111111111".equals(certId)||!validateCertId(certId)) {
				return "身份证信息不正确！";
			}
		} catch (NumberFormatException e) {
			logger.error("验证身份证信息出错！");
			e.printStackTrace();
			return "身份证信息出错！";
		} catch (ParseException e) {
			logger.error("验证身份证信息出错！");
			e.printStackTrace();
			return "身份证信息出错！";
		}
		return "";
	}
	/**
	 * 完善用户信息验证昵称
	 * 
	 * @param name
	 * @param nickName
	 * @param certId
	 * @return
	 */
	public String validatePerfectUserInfoByNickName(String nickName) {
		if (validateIsNull(nickName)) {
			return "昵称不能为空！";
		}
		if (!validateNickNameFormat(nickName)) {
			return "昵称格式不正确！";
		}
		int nickNameLength = chineseLength(nickName);
		if (nickNameLength < 4 || nickNameLength > 16) {
			return "昵称长度不正确！";
		}
		return "";
	}
	/**
	 * 完善用户信息验证真实姓名
	 * @param name
	 * @return
	 */
	public String validatePerfectUserInfoByName(String name){
		if (validateIsNull(name)) {
			return "真是姓名不能为空！";
		}
		if (!validateNameFormat(name)) {
			return "真实姓名格式不正确！";
		}
		return "";
	}
	/**
	 * 完善用户信息验证
	 * 
	 * @param name
	 * @param nickName
	 * @param certId
	 * @return
	 */
	public String validatePerfectUserInfoByCertId(String certId) {

		
		if (validateIsNull(certId)) {
			return "身份证号不能为空！";
		}
		
		try {
			if ("111111111111111".equals(certId)||!validateCertId(certId)) {
				return "身份证信息不正确！";
			}
		} catch (NumberFormatException e) {
			logger.error("验证身份证信息出错！");
			e.printStackTrace();
			return "身份证信息出错！";
		} catch (ParseException e) {
			logger.error("验证身份证信息出错！");
			e.printStackTrace();
			return "身份证信息出错！";
		}
		return "";
	}
	/**
	 * 验证是否为空
	 * 
	 * @param paramter
	 * @return
	 */
	public static boolean validateIsNull(String paramter) {
		if ("".equals(paramter.trim()) || paramter == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 验证昵称包含字母、数字、汉字和下划线且不能以下划线开头和结尾
	 * ^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4E00-\u9FA5]+$
	 * @param nickName
	 * @return
	 */
	public boolean validateNickNameFormat(String nickName) {
		String regex = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4E00-\u9FA5]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher match = pattern.matcher(nickName);
		return match.matches();
	}
	/**
	 * 验证真实姓名
	 * 
	 * @param name
	 * @return
	 */
	public boolean validateNameFormat(String name) {
		String regex = "^[\u4e00-\u9fa5]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher match = pattern.matcher(name);
		boolean b = match.matches();
		if (b) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 验证身份证号
	 * 
	 * @param certId
	 * @return
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
	public static boolean validateCertId(String certId)
			throws NumberFormatException, ParseException {
		String errorInfo = "";// 记录错误信息
		String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4",
				"3", "2" };
		String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
				"9", "10", "5", "8", "4", "2" };
		// String[] Checker = {"1","9","8","7","6","5","4","3","2","1","1"};
		String Ai = "";

		// ================ 号码的长度 15位或18位 ================
		if (certId.length() != 15 && certId.length() != 18) {
			errorInfo = "号码长度应该为15位或18位。";
			System.out.println(errorInfo);
			return false;
		}
		// =======================(end)========================

		// ================ 数字 除最后以为都为数字 ================
		if (certId.length() == 18) {
			Ai = certId.substring(0, 17);
		} else if (certId.length() == 15) {
			Ai = certId.substring(0, 6) + "19" + certId.substring(6, 15);
		}
		if (isNumeric(Ai) == false) {
			errorInfo = "15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
			System.out.println(errorInfo);
			return false;
		}
		// =======================(end)========================

		// ================ 出生年月是否有效 ================
		String strYear = Ai.substring(6, 10);// 年份
		String strMonth = Ai.substring(10, 12);// 月份
		String strDay = Ai.substring(12, 14);// 月份

		if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
			errorInfo = "生日无效。";
			System.out.println(errorInfo);
			return false;
		}

		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
				|| (gc.getTime().getTime() - s.parse(
						strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
			errorInfo = "生日不在有效范围。";
			System.out.println(errorInfo);
			return false;
		}
		if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
			errorInfo = "月份无效";
			System.out.println(errorInfo);
			return false;
		}
		if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
			errorInfo = "日期无效";
			System.out.println(errorInfo);
			return false;
		}
		// =====================(end)=====================

		// ================ 地区码时候有效 ================
		Map<String, String> h = getAreaCode();
		if (h.get(Ai.substring(0, 2)) == null) {
			errorInfo = "地区编码错误。";
			System.out.println(errorInfo);
			return false;
		}
		// ==============================================

		// ================ 判断最后一位的值 ================
		int TotalmulAiWi = 0;
		for (int i = 0; i < 17; i++) {
			TotalmulAiWi = TotalmulAiWi
					+ Integer.parseInt(String.valueOf(Ai.charAt(i)))
					* Integer.parseInt(Wi[i]);
		}
		int modValue = TotalmulAiWi % 11;
		String strVerifyCode = ValCodeArr[modValue];
		Ai = Ai + strVerifyCode;

		if (certId.length() == 18) {
			if (Ai.equals(certId) == false) {
				errorInfo = "身份证无效，最后一位字母错误";
				System.out.println(errorInfo);
				return false;
			}
		} else {
			System.out.println("所在地区:" + h.get(Ai.substring(0, 2).toString()));
			System.out.println("新身份证号:" + Ai);
			return true;
		}
		// =====================(end)=====================
		System.out.println("所在地区:" + h.get(Ai.substring(0, 2).toString()));
		return true;
	}

	/**
	 * ======================================================================
	 * 功能：设置地区编码
	 * 
	 * @return hashMap 对象
	 */
	private static Map<String, String> getAreaCode() {
		Map<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("11", "北京");
		hashMap.put("12", "天津");
		hashMap.put("13", "河北");
		hashMap.put("14", "山西");
		hashMap.put("15", "内蒙古");
		hashMap.put("21", "辽宁");
		hashMap.put("22", "吉林");
		hashMap.put("23", "黑龙江");
		hashMap.put("31", "上海");
		hashMap.put("32", "江苏");
		hashMap.put("33", "浙江");
		hashMap.put("34", "安徽");
		hashMap.put("35", "福建");
		hashMap.put("36", "江西");
		hashMap.put("37", "山东");
		hashMap.put("41", "河南");
		hashMap.put("42", "湖北");
		hashMap.put("43", "湖南");
		hashMap.put("44", "广东");
		hashMap.put("45", "广西");
		hashMap.put("46", "海南");
		hashMap.put("50", "重庆");
		hashMap.put("51", "四川");
		hashMap.put("52", "贵州");
		hashMap.put("53", "云南");
		hashMap.put("54", "西藏");
		hashMap.put("61", "陕西");
		hashMap.put("62", "甘肃");
		hashMap.put("63", "青海");
		hashMap.put("64", "宁夏");
		hashMap.put("65", "新疆");
		hashMap.put("71", "台湾");
		hashMap.put("81", "香港");
		hashMap.put("82", "澳门");
		hashMap.put("91", "国外");
		return hashMap;
	}

	/**
	 * ======================================================================
	 * 功能：判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	private static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
		/*
		 * 判断一个字符时候为数字 if(Character.isDigit(str.charAt(0))) { return true; }
		 * else { return false; }
		 */
	}

	/**
	 * ======================================================================
	 * 功能：判断字符串是否为日期格式
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDate(String strDate) {
		Pattern pattern = Pattern
				.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
		Matcher m = pattern.matcher(strDate);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取字符串的长度，如果有中文，则每个中文字符计为2位
	 * 
	 * @param value
	 *            指定的字符串
	 * @return 字符串的长度
	 */
	public int chineseLength(String value) {
		int valueLength = 0;
		String chinese = "[\\u4E00-\\u9FA5]+";
		/* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
		for (int i = 0; i < value.length(); i++) {
			/* 获取一个字符 */
			String temp = value.substring(i, i + 1);
			/* 判断是否为中文字符 */
			if (temp.matches(chinese)) {
				/* 中文字符长度为2 */
				valueLength += 2;
			} else {
				/* 其他字符长度为1 */
				valueLength += 1;
			}
		}
		return valueLength;
	}

	/**
	 * 验证手机号
	 * 
	 * @param mobileId
	 * @return
	 */
	public static boolean validateMobileId(String mobileId) {
		Pattern p = Pattern.compile("^(13|14|15|18)\\d{9}$");
		Matcher m = p.matcher(mobileId);
		return m.matches();
	}

	/**
	 * 验证兑换积分
	 * 
	 * @param tradScore
	 * @param score
	 * @param handsel
	 * @return
	 */
	public String validateScore(String tradScore, String score,
			String handsel) {
		int s = 500;
		String regex = "^[1-9][0-9]*$";
		if ("".equals(tradScore) || tradScore == null) {
			return "兑换积分不能为空";
		} else if (!tradScore.matches(regex)) {
			return "兑换积分输入不正确";
		} else if (Integer.parseInt(tradScore) / s < 1
				&& Integer.parseInt(tradScore) % s != 0) {
			return "兑换积分必须是" + s + "的整倍数";
		} else if (Integer.parseInt(tradScore) > Integer.parseInt(score)) {
			return "兑换积分不能大于总积分";
		} else if (Integer.parseInt(score) < s) {
			return "对不起，您的积分不足";
		}
		if ("".equals(handsel) || handsel == null) {
			return "兑换彩金不能为空";
		} else if (!handsel.matches(regex)) {
			return "兑换彩金输入不正确";
		} else if (Integer.parseInt(handsel) * s != Integer.parseInt(tradScore)) {
			return "兑换积分和彩金输入不匹配";
		}
		return "";
	}

	/**
	 * 验证用户名是否正确 4-16个字符，可以使用字母、数字、下划线
	 * 
	 * @param userName
	 * @return
	 */
	public boolean validateUserName(String userName) {
		String re = "^[a-zA-Z0-9_]{4,16}$";
		Pattern pattern = Pattern.compile(re);
		Matcher matcher = pattern.matcher(userName);
		return matcher.matches();
	}

	/**
	 * 验证绑定手机号参数
	 * 
	 * @param checkCode
	 * @param request
	 * @return
	 */
	public String validateMobileBanding(String checkCode,
			HttpServletRequest request) {
		if (validateIsNull(checkCode))
			return "验证码不能为空！";
		String randCode = (String) request.getSession()
				.getAttribute("randCode");
		if (!checkCode.equals(randCode))
			return "验证码不正确！";
		return "";
	}

	/**
	 * 验证留言参数
	 * 
	 * @param content
	 * @param detail
	 * @return
	 */
	public String validateMsg(String content, String detail) {
		if (validateIsNull(content))
			return "留言内容不能为空！";
		if (validateIsNull(detail))
			return "联系方式不能为空！";
		if (validateIsSpecialCharacter(content))
			return "留言内容不能包含特殊字符！";
		if (content.length() > 500)
			return "留言内容过长！";
		if (!validateEmail(detail) && !validateMobileId(detail)
				&& !validateQQ(detail) && !validateTelephone(detail))
			return "联系方式格式不正确！";
		return "";

	}

	/**
	 * 验证邮箱
	 * 
	 * @param email
	 * @return
	 */
	public boolean validateEmail(String email) {
		String re = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
		Pattern pattern = Pattern.compile(re);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * 验证QQ
	 * 
	 * @param qq
	 * @return
	 */
	public boolean validateQQ(String qq) {
		String re = "^[1-9]\\d{4,9}$";
		Pattern pattern = Pattern.compile(re);
		Matcher matcher = pattern.matcher(qq);
		return matcher.matches();
	}

	/**
	 * 验证电话号码
	 * 
	 * @param telephone
	 * @return
	 */
	public boolean validateTelephone(String telephone) {
		String re = "^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$";
		Pattern pattern = Pattern.compile(re);
		Matcher matcher = pattern.matcher(telephone);
		return matcher.matches();
	}

	/**
	 * 验证是否包含特殊字符
	 * 
	 * @param paramter
	 * @return
	 */
	public boolean validateIsSpecialCharacter(String paramter) {
		String regex = "[^%$&]{1,}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(paramter);
		return !matcher.matches();
	}
	/**
	 * 验证兑换积分参数
	 * 
	 * @param tradScore
	 * @param score
	 * @param handsel
	 * @return
	 */
	public String validateTransScore(String tradScore,
			String totalScore, String handsel) {
		int s = 500;
		if (validateIsNull(tradScore))
			return "兑换积分不能为空！";
		if (validateIsNull(handsel))
			return "兑换彩金不能为空！";
		String regex = "^[1-9][0-9]*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(tradScore);
		if (!matcher.matches())
			return "兑换积分不正确！";
		matcher = pattern.matcher(handsel);
		if (!matcher.matches())
			return "兑换金额不正确！";
		if (Integer.parseInt(tradScore) / s < 1
				&& Integer.parseInt(tradScore) % s != 0)
			return "兑换积分必须是" + s + "的整倍数";
		if (Integer.parseInt(tradScore) > Integer.parseInt(totalScore))
			return "兑换积分不能大于总积分";
		if (Integer.parseInt(totalScore) < s)
			return "对不起，您的积分不足";
		if (Integer.parseInt(handsel) * s != Integer.parseInt(tradScore))
			return "兑换积分和彩金输入不匹配";
		return "";
	}

	/**
	 * 验证充值卡充值
	 * 
	 * @param cardNo
	 * @param cardPassWord
	 * @param amount
	 * @param totalAmount
	 * @return
	 */
	public static String validateNineteenPayCardCharge(String cardNo,
			String cardPassWord, String amount, String totalAmount) {
		if (validateIsNull(cardNo))
			return "卡号不能为空！";
		if (validateIsNull(cardPassWord))
			return "卡密不能为空！";
		if (validateIsNull(amount))
			return "充值金额不能为空！";
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(amount);
		if (!isNum.matches())
			return "充值金额不正确！";
		int ai = Integer.parseInt(amount);
		if (ai < 1)
			return "充值金额不能小于1";
		if (ai > Integer.parseInt(totalAmount))
			return "充值金额不能大于充值卡面值！";

		return "";
	}

	/**
	 * 验证支付宝充值
	 * 
	 * @param amount
	 * @return
	 */
	public static String validateChargeAlipay(String amount) {
		if (validateIsNull(amount))
			return "充值金额不能为空！";
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(amount);
		if (!isNum.matches())
			return "充值金额不正确！";
		int ai = Integer.parseInt(amount);
		if (ai < 1)
			return "充值金额不能小于1";
		return "";
	}
	/**
	 * 验证联动优势充值
	 * 
	 * @param amount
	 * @return
	 */
	public static String validateChargeUnify(String amount) {
		if (validateIsNull(amount))
			return "充值金额不能为空！";
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(amount);
		if (!isNum.matches())
			return "充值金额不正确！";
		int ai = Integer.parseInt(amount);
		if (ai < 1)
			return "充值金额不能小于1";
		return "";
	}
	/**
	 * 验证联动优势话费充值
	 * 
	 * @param amount
	 * @return
	 */
	public static String validateChargeUnify_MobileFare(String amount,String mobile) {
		if (validateIsNull(amount))
			return "充值金额不能为空！";
		if(validateIsNull(mobile))
			return "手机号不能为空！";
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(amount);
		if (!isNum.matches())
			return "充值金额不正确！";
		int ai = Integer.parseInt(amount);
		if (ai != 3&&ai!=30)
			return "充值金额不正确！";
		if(!ValidateUserInfoUtil.validateMobileId(mobile))
			return "请填写正确的手机号码！";
		return "";
	}
	/**
	 * 验证DNA充值参数
	 * 
	 * @param mobileId
	 * @param amount
	 * @return
	 */
	public static String validateDnaCharge(String mobileId, String amount) {
		if (validateIsNull(mobileId))
			return "手机号不能为空！";
		if (!validateMobileId(mobileId))
			return "手机号不正确！";
		if (validateIsNull(amount))
			return "充值金额不能为空！";
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(amount);
		if (!isNum.matches())
			return "充值金额不正确！";
		int ai = Integer.parseInt(amount);
		if (ai < 1)
			return "充值金额不能小于1";
		return "";
	}

	/**
	 * 验证DNA首次充值
	 * @param mobileId
	 * @param cardNo
	 * @param name
	 * @param amount
	 * @param bankType
	 * @param documentNumber
	 * @param documentAddress
	 * @param accountAddress1
	 * @param accountAddress2
	 * @return
	 */
	public static String validateDnaFirstCharge(String mobileId, String cardNo,
			String name, String amount, String bankType, String documentNumber,
			String documentAddress, String accountAddress1,
			String accountAddress2) {
		if (validateIsNull(amount))
			return "充值金额不能为空！";
		if ("00".equals(bankType))
			return "请选择开卡银行！";
		if (validateIsNull(cardNo))
			return "银行卡号不能为空！";
		if (validateIsNull(name))
			return "开卡人姓名不能为空！";
		if (validateIsNull(documentNumber))
			return "开卡人身份证号不能为空！";
		if (validateIsNull(documentAddress))
			return "身份证户籍所在地不能为空！";
		if (validateIsNull(accountAddress1))
			return "银行卡开户省份不能为空！";
		if (validateIsNull(accountAddress2))
			return "银行卡开户城市不能为空！";
		if (validateIsNull(mobileId))
			return "电话号码不能为空！";
		if (!validateBankCard(cardNo))
			return "银行卡号不正确！";
		try {
			if(!validateCertId(documentNumber))
				return "身份证号不正确！";
		} catch (NumberFormatException e) {
			return "身份证号不正确！";
		} catch (ParseException e) {
			return "身份证号不正确！";
		}
		if (!validateMobileId(mobileId))
			return "电话号码不正确！";
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(amount);
		if (!isNum.matches())
			return "充值金额不正确！";
		int ai = Integer.parseInt(amount);
		if (ai < 1)
			return "充值金额不能小于1";
		return "";
	}

	/**
	 * 校验银行卡卡号
	 * 
	 * @param cardId
	 * @return
	 */
	public static boolean validateBankCard(String cardId) {
		char bit = getBankCardCheckCode(cardId
				.substring(0, cardId.length() - 1));
		if (bit == 'N') {
			return false;
		}
		return cardId.charAt(cardId.length() - 1) == bit;
	}

	/**
	 * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
	 * 
	 * @param nonCheckCodeCardId
	 * @return
	 */
	public static char getBankCardCheckCode(String nonCheckCodeCardId) {
		if (nonCheckCodeCardId == null
				|| nonCheckCodeCardId.trim().length() == 0
				|| !nonCheckCodeCardId.matches("\\d+")) {
			// 如果传的不是数据返回N
			return 'N';
		}
		char[] chs = nonCheckCodeCardId.trim().toCharArray();
		int luhmSum = 0;
		for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
			int k = chs[i] - '0';
			if (j % 2 == 0) {
				k *= 2;
				k = k / 10 + k % 10;
			}
			luhmSum += k;
		}
		return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
	}
	public static void main(String[] args) {
//		String regex = "[^%$&]{1,}";
//		Pattern pattern = Pattern.compile(regex);
//		Matcher matcher = pattern.matcher("10086");
//		System.out.println(matcher.matches());
		String regex = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4E00-\u9FA5]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher match = pattern.matcher("abcdef_汉子123");
		System.out.println(match.matches());
	}
}
