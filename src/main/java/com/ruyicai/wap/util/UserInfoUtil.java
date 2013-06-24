package com.ruyicai.wap.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ruyicai.wap.vo.LastestPayChannel;
import com.ruyicai.wap.vo.Score;
import com.ruyicai.wap.vo.SupportSecPayChannel;
import com.ruyicai.wap.vo.SupportTopPayChannel;
import com.ruyicai.wap.vo.UserInfo;
@Component
public class UserInfoUtil {
	private Logger logger = Logger.getLogger(UserInfoUtil.class);
	@Value("${lottery}")
	String lottery;
	@Value("${msgcenter}")
	String msgcenter;
	@Value("${scorecenter}")
	String scorecenter;
	@Value("${chargeCenter}")
	String chargeCenter;
	@Autowired
	SelectAllUtil selectAllUtil;
	@Autowired
	CommonUtil commonUtil;
	/**
	 * 用户登录
	 * @param userName
	 * @param passWord
	 * @return
	 */
	public Map<String , Object> toLogin(String userName,String passWord){
		Map<String , Object> map = new HashMap<String, Object>();
		UserInfo userInfo = new UserInfo();
		String result = selectAllUtil.getUserInfoByUserName(userName);
		JSONObject resultObject = JSONObject.fromObject(result);
		String errorCode = resultObject.getString("errorCode");
		if("0".equals(errorCode)){
			JSONObject jsonObjectValue = resultObject.getJSONObject("value");
			String userNo = jsonObjectValue.getString("userno");
			String mobileId = jsonObjectValue.getString("mobileid");
			String state = jsonObjectValue.getString("state");
			String userPassWord = jsonObjectValue.getString("password");
			String name = jsonObjectValue.getString("name");
			String certId = jsonObjectValue.getString("certid");
			String phone = jsonObjectValue.getString("phone");
			String email = jsonObjectValue.getString("email");
			String address = jsonObjectValue.getString("address");
			String qq = jsonObjectValue.getString("qq");
			String msn = jsonObjectValue.getString("msn");
			String regTime = jsonObjectValue.getString("regtime");
			String modTime = jsonObjectValue.getString("modtime");
			String nickName = jsonObjectValue.getString("nickname");
			String channel = jsonObjectValue.getString("channel");
			userInfo.setUserNo(userNo);
			userInfo.setMobileId(mobileId);
			userInfo.setState(state);
			userInfo.setPassWord(userPassWord);
			userInfo.setName(name);
			userInfo.setCertId(certId);
			userInfo.setPhone(phone);
			userInfo.setEmail(email);
			userInfo.setAddress(address);
			userInfo.setQq(qq);
			userInfo.setMsn(msn);
			userInfo.setRegTime(regTime);
			userInfo.setModTime(modTime);
			userInfo.setNickName(nickName);
			userInfo.setChannel(channel);
			userInfo.setUserName(userName);
			if("0".equals(state)){
				map.put("result", "用户未注册！");
				map.put("userInfo", userInfo);
			}else if("2".equals(state)){
				map.put("result", "用户暂停！");
				map.put("userInfo", userInfo);
			}else if("1".equals(state)&&!CommonUtil.encoderByMd5(passWord).equals(userPassWord)){
				map.put("result", "用户名或密码错误！");
				map.put("userInfo", userInfo);
			}else if("1".equals(state)&&CommonUtil.encoderByMd5(passWord).equals(userPassWord)){
				map.put("result", "");
				map.put("userInfo", userInfo);
			}else{
				map.put("result", "用户登录失败！");
				map.put("userInfo", userInfo);
			}
		}else if("100002".equals(errorCode)){
			map.put("result", "用户未注册！");
			map.put("userInfo", userInfo);
		}else{
			map.put("result", "用户登录失败！");
			map.put("userInfo", userInfo);
		}
		return map;
	}
	/**
	 * 用户注册
	 * @param userName
	 * @param passWord
	 * @param channel
	 * @return
	 */
	public String register(String userName,String passWord,String channel){
		String url = lottery +"tuserinfoes/register";
		String parameter = "userName="+userName+"&password="+passWord+"&channel="+channel;
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("UserInfoUtil/register用注册返回：" + result);
		JSONObject resultObject = JSONObject.fromObject(result);
		String errorCode = resultObject.getString("errorCode");
		return errorCode;
	}
	/**
	 * 修改密码
	 * @param userNo
	 * @param passWord
	 * @return errorCode
	 */
	public String resetPassWord(String userNo,String passWord){
		String url = lottery +"tuserinfoes/resetPassword";
		String parameter = "userno="+userNo+"&password="+passWord;
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("UserInfoUtil/resetPassWord用户修改密码返回：" + result);
		JSONObject resultObject = JSONObject.fromObject(result);
		String errorCode = resultObject.getString("errorCode");
		return errorCode;

	}
	/**
	 * 用户提现
	 * @param userNo
	 * @param balance
	 * @param bankId
	 * @param bankAccount
	 * @param name
	 * @param bankName
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String drawCash(String userNo, long balance,
			String bankId, String bankAccount, String name, String bankName) throws UnsupportedEncodingException{
		bankId = URLEncoder.encode(bankId,"UTF-8");
		bankName = URLEncoder.encode(bankName,"UTF-8");
		name = URLEncoder.encode(name,"UTF-8");
		String url = lottery + "taccountdetails/drawCash";
		String parameter = "userno=" + userNo + "&balance=" + balance
				+ "&bankId=" + bankId + "&bankAccount=" + bankAccount
				+ "&name=" + name + "&BANKNAME=" + bankName;

		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("UserInfoUtil/drawCash用户提现返回：" + result);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		return errorCode;
	}
	/**
	 * 撤销提现
	 * @param cashdetailId
	 * @return
	 */
	public String cancelTcashDetail(String cashdetailId) {
		String url = lottery + "taccountdetails/cancelTcashDetail";
		String parameter = "cashdetailId=" + cashdetailId;
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("UserInfoUtil/cancelTcashDetail用户撤销提现返回：" + result);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		return errorCode;
	}
	/**
	 * 修改用户信息
	 * @param userno
	 * @param nickname
	 * @param name
	 * @param certid
	 * @return
	 */
	public String perfectUserinfo(String userNo ,String nickName,String name,String certId){
		String url = lottery + "tuserinfoes/modify";
		String parameter = "userno="+userNo;
		if(!"".equals(nickName)&&nickName!=null){
			parameter +="&nickname="+nickName;
		}
		if(!"".equals(name)&&name!=null){
			parameter+="&name=" + name;
		}
		if(!"".equals(certId)&&certId!=null){
			parameter+="&certid=" + certId;
		}
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("UserInfoUtil/modifyUserinfo修改用户信息返回:" + result);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		return errorCode;
	}
	
	/**
	 * 手机绑定/解绑
	 * @param userno
	 * @param mobileid
	 * @return
	 */
	public String bindMobile(String userNo ,String mobileId){
		String url = lottery + "tuserinfoes/modify";
		String parameter = "userno="+userNo+"&mobileid="+mobileId;
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("UserInfoUtil/bindMobile绑定/解绑手机号返回:" + result);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		return errorCode;
	}
	/**
	 * 发送短信
	 * @param mobileids
	 * @param text
	 * @return
	 * @throws JSONException
	 * @throws UnsupportedEncodingException
	 */
	public boolean sendSms(String mobileids, String text) throws JSONException, UnsupportedEncodingException{
		String url = msgcenter + "sms/send";
		String parameter = "mobileIds=" + mobileids +"&text="+URLEncoder.encode(text, "UTF-8");
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("UserInfoUtil/sendSms发送短信返回内容："+result);
		JSONObject jsonObject = JSONObject.fromObject(result);
	    String errorCode = jsonObject.getString("errorCode");
	    if("0".equals(errorCode)){
	    	return true;
	    }
		return false;
	}
	/**
	 * 用户留言
	 * @param body("userno", userno;"content", content;"detail", detail)
	 * @return
	 */
	public String saveMsg(JSONObject body){
		String url = msgcenter + "msg/saveMsg";
		String parameter = "body=" + body;
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("UserInfoUtil/saveMsg用户留言返回result:"+result);
		JSONObject resultObject = JSONObject.fromObject(result);
		String errorCode = resultObject.getString("errorCode");
		return errorCode;
	}
	public String getBankName(String bankName){
		if("china_gongshang".equals(bankName)){
			return "中国工商银行";
		}
		if("china_nongye".equals(bankName)){
			return "中国农业银行";
		}
		if("china_jianshe".equals(bankName)){
			return "中国建设银行";
		}
		if("china_minsheng".equals(bankName)){
			return "中国民生银行";
		}
		if("zhaoshang".equals(bankName)){
			return "招商银行";
		}
		if("china_youzhengchuxu".equals(bankName)){
			return "中国邮政储蓄银行";
		}
		if("jiaotong".equals(bankName)){
			return "交通银行";
		}
		if("huaxia".equals(bankName)){
			return "华夏银行";
		}
		if("xingye".equals(bankName)){
			return "兴业银行";
		}
		if("zhongxin".equals(bankName)){
			return "中信银行";
		}
		if("china_guangda".equals(bankName)){
			return "中国光大银行";
		}
		if("guangdong_fazhan".equals(bankName)){
			return "广东发展银行";
		}
		if("shanghaipudong_fazhan".equals(bankName)){
			return "上海浦东发展银行";
		}
		if("shenzhen_fazhan".equals(bankName)){
			return "深圳发展银行";
		}
		if("hangzhou".equals(bankName)){
			return "杭州银行";
		}
		return bankName;
		
	}
	/**
	 * 增加积分
	 * @param userNo
	 * @return
	 */
	public String addTuserinfoScore(String userNo,int scoreType){
		String url = scorecenter + "addTuserinfoScore";
		String parameter = "userno=" + userNo+"&scoreType=" + scoreType;
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("UserInfoUtil/addTuserinfoScore增加积分返回result:"+result);
		return result;
	}
	/**
	 * 增加积分
	 * @param userNo
	 * @param scoreType
	 * @return
	 */
	public String addUserScore(String userNo,int scoreType){
		String result = addTuserinfoScore(userNo, scoreType);
		JSONObject resultObject = JSONObject.fromObject(result);
		String errorCode = resultObject.getString("errorCode");
		if("0".equals(errorCode)){
			boolean value = resultObject.getBoolean("value");
			if(!value){
				return "已经增加过！";
			}else{
				return "";
			}
		}else if("500".equals(errorCode)){
			return "服务器忙，请稍后再试！";
		}else{
			return "添加积分失败！";
		}
	}

	/**
	 * 兑换积分
	 * @param userNo
	 * @param score
	 * @return result
	 */
	public String transScore2Money(String userNo,String score){
		String url = scorecenter + "transScore2Money";
		String parameter = "userno=" + userNo+"&score=" + score;
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("UserInfoUtil/transScore兑换积分返回result:"+result);
		return result;
	}
	/**
	 * 兑换积分
	 * @param userNo
	 * @param transScore
	 * @return
	 */
	public Score transScore(String userNo,String transScore){
		Score score = new Score();
		String result = transScore2Money(userNo, transScore);
		JSONObject resultObject = JSONObject.fromObject(result);
		String errorCode = resultObject.getString("errorCode");
		if("0".equals(errorCode)){
			JSONObject jsonObjectValue = resultObject.getJSONObject("value");
			String money = jsonObjectValue.getString("money");
			JSONObject jsonObjectTuserinfoScore = jsonObjectValue.getJSONObject("tuserinfoScore");
			String newScore = jsonObjectTuserinfoScore.getString("score");
			score.setScore(newScore);
			score.setMoney(commonUtil.getBalanceFormat(money, 2));
		}
		return score;
	}
	/**
	 * 充值卡充值
	 * @param jsonString{userno,bankid,accesstype,paytype,totalAmount,
	 * amt,channel,subchannel,card_no,cardno,card_pwd,ladderpresentflag是否参加充值送彩金活动 0:不参加1：参加}
	 * @return errorCode
	 */
	public String nineteenpayCharge(JSONObject jsonString){
		String url = chargeCenter +"charge!nineteenpayCharge";
		String parameter = "jsonString="+jsonString;
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("UserInfoUtil/nineteenpayCharge充值卡充值返回result:"+result);
		JSONObject resultObject = JSONObject.fromObject(result);
		String errorCode = resultObject.getString("error_code");
		return errorCode;

	}

	/**
	 * 支付宝快捷支付银行列表
	 * @param jsonObject
	 * @return
	 */
	public String getPayChannels(JSONObject jsonObject){
		String url = chargeCenter +"alipaywapchannelcharge!getPayChannels";
		String parameter = "jsonString="+jsonObject.toString();
		String result = commonUtil.setUrlByPOST(url, parameter);
		logger.info("UserInfoUtil/getPayChannels返回result："+result);
		return result; 
	}

	/**
	 * 支付宝快捷支付银行列表
	 * @param jsonObject
	 * @return
	 */
	public Map<String, Object> selectPayChannelList(JSONObject jsonObject){
		logger.info("ChargeWapUtil/selectPayChannelList支付宝快捷支付银行列表参数jsonObject："+jsonObject);
		String result = getPayChannels(jsonObject);
		JSONObject resultObject = JSONObject.fromObject(result);
		String errorCode = resultObject.getString("error_code");
		Map<String, Object> map = new HashMap<String, Object>();
		if("0".equals(errorCode)){
			String value = resultObject.getString("value");
			JSONObject jsonObjectValue = JSONObject.fromObject(value);
			JSONObject payChannleResult = jsonObjectValue.getJSONObject("payChannleResult");
			if(payChannleResult.containsKey("lastestPayChannel")){
				JSONObject lastestPayChannelObject = payChannleResult.getJSONObject("lastestPayChannel");
				String name = lastestPayChannelObject.getString("name");
				String cashierCode = lastestPayChannelObject.getString("cashierCode");
				LastestPayChannel lastestPayChannel = new LastestPayChannel();
				lastestPayChannel.setName(name);
				lastestPayChannel.setCashierCode(cashierCode);
				map.put("lastestPayChannel",lastestPayChannel);
			}
			JSONObject supportedPayChannelList = payChannleResult.getJSONObject("supportedPayChannelList");
			JSONArray supportTopPayChannelArray = supportedPayChannelList.getJSONArray("supportTopPayChannel");
			List<SupportTopPayChannel> supportTopPayChannelList = new ArrayList<SupportTopPayChannel>();
			SupportTopPayChannel supportTopPayChannel = null;
			for(int i=0;i<supportTopPayChannelArray.size();i++){
				supportTopPayChannel = new SupportTopPayChannel();
				JSONObject supportTopPayChannelObject = supportTopPayChannelArray.getJSONObject(i);
				String name = supportTopPayChannelObject.getString("name");
				supportTopPayChannel.setName(name);
				String supportSecPayChannelListString = supportTopPayChannelObject.getString("supportSecPayChannelList");
				JSONObject supportSecPayChannelListObject = JSONObject.fromObject(supportSecPayChannelListString);
				
				String supportSecPayChannelString = supportSecPayChannelListObject.getString("supportSecPayChannel");
				JSONArray supportSecPayChannelArray = JSONArray.fromObject(supportSecPayChannelString);
				SupportSecPayChannel supportSecPayChannel = null;
				List<SupportSecPayChannel> supportSecPayChannelList = new ArrayList<SupportSecPayChannel>();
				for(int m=0;m<supportSecPayChannelArray.size();m++){
					JSONObject supportSecPayChannelObject = supportSecPayChannelArray.getJSONObject(m);
					name =  supportSecPayChannelObject.getString("name");
					String cashierCode = supportSecPayChannelObject.getString("cashierCode");
					supportSecPayChannel = new SupportSecPayChannel();
					supportSecPayChannel.setName(name);
					supportSecPayChannel.setCashierCode(cashierCode);
					supportSecPayChannelList.add(supportSecPayChannel);
				}
				supportTopPayChannel.setSupportSecPayChannelList(supportSecPayChannelList);
				supportTopPayChannelList.add(supportTopPayChannel);
			}
			map.put("supportTopPayChannelList",supportTopPayChannelList);

		}else{
			map.put("supportTopPayChannelList",null);
		}
		return map; 
	}
	/**
	 * 支付宝wap充值
	 * @param jsonString
	 * @return
	 */
	public Map<String, String> chargeAlipay_Wap(JSONObject jsonString){
		logger.info("UserInfoUtil/chargeAlipay_Wap支付宝wap充值jsonString："+jsonString.toString());
		String url = chargeCenter +"charge!zfbWapCharge";
		String parameter = "jsonString="+jsonString;
		String result  = commonUtil.setUrlByPOST(url, parameter);
		logger.info("UserInfoUtil/chargeAlipay_Wap返回result："+result);
		JSONObject resultObject = JSONObject.fromObject(result);
		String errorCode = resultObject.getString("error_code");
		String requrl = resultObject.getString("requrl");
		Map<String, String> map = new HashMap<String, String>();
		map.put("errorCode", errorCode);
		map.put("requrl", requrl);
		return map;
	}
	/**
	 * 联动优势充值
	 * @param jsonString
	 * @return
	 */
	public Map<String, String> chargeUnify_Wap(JSONObject jsonString){
		logger.info("UserInfoUtil/chargeUnify_Wap联动优势充值jsonString："+jsonString.toString());
		String url = chargeCenter +"umpayCharge!chargeUnify";
		String parameter = "jsonString="+jsonString;
		String result  = commonUtil.setUrlByPOST(url, parameter);
		logger.info("UserInfoUtil/chargeUnify_Wap返回result："+result);
		JSONObject resultObject = JSONObject.fromObject(result);
		String errorCode = resultObject.getString("error_code");
		String requrl = resultObject.getString("pay_url");
		Map<String, String> map = new HashMap<String, String>();
		map.put("errorCode", errorCode);
		map.put("requrl", requrl);
		return map;
	}
	/**
	 * 联动优势充值
	 * @param jsonString
	 * @return
	 */
	public Map<String, String> chargeUnify_MobileFare(JSONObject jsonString){
		logger.info("UserInfoUtil/chargeUnify_MobileFare联动优势手机话费充值jsonString："+jsonString.toString());
		String url = chargeCenter +"umpayCharge!chargeUnify2";
		String parameter = "jsonString="+jsonString;
		String result  = commonUtil.setUrlByPOST(url, parameter);
		logger.info("UserInfoUtil/chargeUnify_MobileFare返回result："+result);
		JSONObject resultObject = JSONObject.fromObject(result);
		String errorCode = resultObject.getString("error_code");
		String requrl = resultObject.getString("pay_url");
		Map<String, String> map = new HashMap<String, String>();
		map.put("errorCode", errorCode);
		map.put("requrl", requrl);
		return map;
	}
	/**
	 * 支付宝快捷支付
	 * @param jsonString
	 * @return
	 */
	public Map<String, String> chargeAlipay_KJ(JSONObject jsonString){
		logger.info("UserInfoUtil/chargeAlipay_KJ支付宝快捷支付参数jsonString："+jsonString.toString());
		String url = chargeCenter +"alipaywapchannelcharge!charge";
		String parameter = "jsonString="+jsonString;
		String result  = commonUtil.setUrlByPOST(url, parameter);
		logger.info("UserInfoUtil/chargeAlipay_KJ返回result："+result);
		JSONObject resultObject = JSONObject.fromObject(result);
		String errorCode = resultObject.getString("error_code");
		String requrl = resultObject.getString("value");
		Map<String, String> map = new HashMap<String, String>();
		map.put("errorCode", errorCode);
		map.put("requrl", requrl);
		return map;

	}
	/**
	 * DNA充值
	 * @param jsonString
	 * @return
	 */
	public Map<String, String> chargeUnionPay(JSONObject jsonString){
		logger.info("UserInfoUtil/chargeUnionPay DNA充值参数jsonString："+jsonString.toString());
		String url = chargeCenter +"dnacharge!dnaBankCharge";
		String parameter = "jsonString="+jsonString;
		String result  = commonUtil.setUrlByPOST(url, parameter);
		logger.info("UserInfoUtil/chargeUnionPay DNA充值返回result："+result);
		JSONObject resultObject = JSONObject.fromObject(result);
		String errorCode = resultObject.getString("error_code");
		String remark = resultObject.containsKey("remark")? resultObject.getString("remark"):"";
		Map<String, String> map = new HashMap<String, String>();
		map.put("errorCode", errorCode);
		map.put("remark", remark);
		return map;

	}
}
