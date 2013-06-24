package com.ruyicai.wap.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import sun.misc.BASE64Encoder;
@Component
public class CommonUtil {
	private static Logger logger = Logger.getLogger(CommonUtil.class);

	/**
	 * 将金额(单位:分)转换成金额(单位:元)保留小数点
	 * @param amount金额(单位:分)
	 * @param num保留小数点位数
	 * @return
	 */
	public String getBalanceFormat(String amount,int num){
		BigDecimal result=new BigDecimal("0");
		try{
			BigDecimal bai = new BigDecimal("100");
			BigDecimal amt = new BigDecimal(amount);
			result = amt.divide(bai, num, BigDecimal.ROUND_HALF_UP); 
		}catch (Exception e) {
			return "0.00";
		}
		return result.toString();

	}
	/**
	 * 时间格式化
	 * @param time时间
	 * @param formatType格式化类型
	 * @return
	 */
	public String getDataFormat(long time,String formatType){
//		logger.info("CommonUtil/getDataFormat时间格式化参数time:"+time+",formatType："+formatType);

		String dateTime = "";
		SimpleDateFormat dateFormat =null;
		if("".equals(formatType)){
			dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}else{
			try{
				dateFormat = new SimpleDateFormat(formatType);
			}catch (Exception e) {
				logger.error("CommonUtil/getDataFormat时间格式化类型错误："+formatType);
				dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return dateFormat.format(time);
			}
		} 
		dateTime = dateFormat.format(time);
//		logger.info("CommonUtil/getDataFormat时间格式化："+dateTime);
		return dateTime;
	}
	/**
	 * 生成随机验证码
	 * @return
	 */
	public static String getRandomValidateCode(){
		String randomValidateCode = "";
		String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";//随机产生的字符串
		int stringNum = 4;//随机产生字符数量
		Random random = new Random();
		for(int i=0;i<stringNum;i++){
			int randomNum = random.nextInt(randString.length());
			randomValidateCode+=randString.charAt(randomNum);
		}
		return randomValidateCode;
	}
	/**
	 * 设置重复提交
	 * @param request
	 * @param modelAndView
	 */
	public void setToken(HttpServletRequest request, ModelAndView modelAndView) {
		Random rdm = new Random();
		int transctionId = rdm.nextInt();
		modelAndView.addObject("token", transctionId + "");
		request.getSession().setAttribute(transctionId + "", "false");
	}
	/**
	 * post请求
	 * @param url
	 * @param parameter
	 * @return
	 */
	public String setUrlByPOST(String url, String parameter) {
		String retStr;
		try {
			URL reqUrl = new URL(url);
			HttpURLConnection reqConn = (HttpURLConnection) reqUrl
					.openConnection();
			reqConn.setDoOutput(true);
			reqConn.setDoInput(true);
			reqConn.setConnectTimeout(300 * 1000);
			reqConn.setReadTimeout(300 * 1000);
			reqConn.setRequestMethod("POST");
			PrintWriter out = new PrintWriter(reqConn.getOutputStream());
			out.print(parameter);
			out.flush();
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					reqConn.getInputStream(), "UTF-8"));
			retStr = in.readLine();
			logger.info("URL转换POST方式:" + url + "?" + parameter);
			return retStr;
		} catch (IOException e) {
			logger.error("URL转换POST方式异常" + url + "?" + parameter);
			e.printStackTrace();
			return e.getMessage();
		}
	}
	/**
	 * get请求
	 * @param url
	 * @return
	 */
	public String setURLByGET(String url) {
		HttpClient httpclient = new HttpClient();
		String response = "";
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		try {
			int code = httpclient.executeMethod(getMethod);
			if (code == HttpStatus.SC_OK) {
				InputStream resStream = getMethod.getResponseBodyAsStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(resStream,"UTF-8"));
				StringBuffer resBuffer = new StringBuffer();
				String resTemp = "";
				while((resTemp = br.readLine()) != null){
					resBuffer.append(resTemp);
				}
				response = resBuffer.toString(); 
			} else {
				logger.error("不是200ok返回=code="+code+"url="+url);
			}
		} catch (Exception e) {
			logger.error("URL转换GET方式异常!"+url, e);
		} finally {
			getMethod.releaseConnection();
		}
		logger.info("URL转换GET方式:" + url);
		return response;
	}
	/**
	 * 密码MD5加密
	 * @param str
	 * @return
	 */
	public static String encoderByMd5(String str){
        //确定计算方法
        //加密后的字符串
		String newstr = "";
		try {
			MessageDigest md5=MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return newstr;
    }
	/**
	 * 生成随机字符串。
	 * @return
	 */
	public static String getUuid() {
		String code = "";
		UUID uuid = UUID.randomUUID();
		code = uuid.toString().replaceAll("-", "");
		return code;
	}
	/**
	 * 得到页面展现的用户昵称或用户名或手机号
	 * @param nickName
	 * @param userName
	 * @param mobileId
	 * @return
	 */
	public String getViewName(String nickName,String userName,String mobileId){
		if("".equals(nickName)||nickName ==null||"null".equals(nickName)){
			nickName = userName;
			Pattern p = Pattern.compile("^(13|14|15|18)\\d{9}$");
			Matcher m = p.matcher(nickName);
			if(m.matches()&&nickName.length()==11){
				nickName = nickName.substring(0, 3) + "****" + nickName.substring(7, 11);
			}
			return nickName;
		}else{
			return nickName;
		}
	}
	/**
	 * 按key排序Map
	 * 
	 * @param map
	 * @param reverse
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map sortMapByKey(Map map, final boolean reverse) {
		List list = new LinkedList(map.entrySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				if (reverse) {
					return -((Comparable) ((Map.Entry) (o1)).getKey())
							.compareTo(((Map.Entry) (o2)).getKey());
				}
				return ((Comparable) ((Map.Entry) (o1)).getKey())
						.compareTo(((Map.Entry) (o2)).getKey());
			}
		});

		Map result = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

}
