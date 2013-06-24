package com.ruyicai.wap.filter;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.SelectAllUtil;
import com.ruyicai.wap.vo.Account;
import com.ruyicai.wap.vo.UserInfo;

public class MyInteceptor implements HandlerInterceptor {
	Logger logger = Logger.getLogger(MyInteceptor.class);
	@Autowired
	SelectAllUtil selectAllUtil;
	@Autowired
	CommonUtil commonUtil;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		logger.info("MyInteceptor/preHandle拦截器uri："+uri);
		if(uri.indexOf(".do")>-1){
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
			if(userInfo==null){
				request.getRequestDispatcher("/login").forward(request, response); 
				return false;
			}

		}
		if(uri.indexOf("loginOut")==-1&&uri.indexOf("login")==-1&&uri.indexOf(".do")==-1&&uri.indexOf("messageCenter/leaveMessage")==-1&&uri.indexOf("userInfo/registerIndex")==-1){
			String parameters = "";
			Map parameterMap = request.getParameterMap();
			  Set key = parameterMap.keySet();
			  int i=1;
			  String parakey="";
			  String paravalue ="";
			  for(Object aaa: key.toArray()){
		         parakey = aaa.toString();
		         String [] str = ((String[])parameterMap.get(aaa));
		         paravalue = ((String[])parameterMap.get(aaa))[0];
		         String para = "";
		         if(str.length>1){
		        	 for(int m=0;m<str.length;m++){
		        		 if(m==0){
		        			 para+=parakey+"="+str[m];
		        		 }else{
		        			 para+="&"+parakey+"="+str[m];
		        		 }
		        		
		        	 }
		        	 
		         }else{
		        	 para = parakey+"="+paravalue;
		         }
		         if(i==1){
		            parameters += para; 
		         }else{
		            parameters += "&"+para; 
		         }
		         i++;
		       }
			  logger.info("loginOutUrl:"+ uri+(parameters==null||"".equals(parameters) ? "":"?"+parameters));
			  request.getSession().setAttribute("loginOutUrl", uri+(parameters==null||"".equals(parameters) ? "":"?"+parameters));
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String nickName = "";
		String userName = "";
		String mobileId = "";
		String userNo = "";
		String betBalance = "";
		String freezeBalance = "";
		if(userInfo!=null){
			nickName = userInfo.getNickName();
			userName = userInfo.getUserName();
			mobileId = userInfo.getMobileId();
			userNo = userInfo.getUserNo();
			Account account = selectAllUtil.selectUserAccount(userNo);
			betBalance = account.getBetBalance();
			freezeBalance = account.getFreezeBalance();
			nickName = commonUtil.getViewName(nickName, userName, mobileId);
//			if("".equals(nickName)||nickName ==null){
//				nickName = userName;
//				Pattern p = Pattern.compile("^(13|14|15|18)\\d{9}$");
//				Matcher m = p.matcher(nickName);
//				if(m.matches()&&nickName.length()==11){
//					nickName = nickName.substring(0, 3) + "****" + nickName.substring(7, 11);
//				}
//			}
			
		}
		if(modelAndView!=null){
			modelAndView.addObject("viewTopName", nickName);
			modelAndView.addObject("betBalance", betBalance);
			modelAndView.addObject("freezeBalance", freezeBalance);
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
