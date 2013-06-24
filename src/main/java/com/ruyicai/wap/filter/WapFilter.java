package com.ruyicai.wap.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ruyicai.wap.vo.UserInfo;

public class WapFilter implements Filter {
	Logger logger = Logger.getLogger(WapFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpRequest.setCharacterEncoding("utf-8");
		String uri = httpRequest.getRequestURI();
		if(uri.indexOf("loginOut")==-1&&("/w3g/".equals(uri)||uri.indexOf("jspx")>0||uri.indexOf("jspa")>0)){
			logger.info("request.getRequestURI():"+httpRequest.getRequestURI());
			String parameters = "";
			Map parameterMap = request.getParameterMap();
			  Set key = parameterMap.keySet();
			  int i=1;
			  String parakey="";
			  String paravalue ="";
			  for(Object aaa: key.toArray()){
		         parakey = aaa.toString();
		         paravalue = ((String[])parameterMap.get(aaa))[0];
		         if(i==1){
		            parameters += parakey+"="+paravalue; 
		         }else{
		            parameters += "&"+parakey+"="+paravalue; 
		         }
		         i++;
		       }
			  logger.info("loginOutUrl:"+ uri.substring(4)+"?"+parameters);
			  httpRequest.getSession().setAttribute("loginOutUrl", uri.substring(4)+"?"+parameters);
		}
		setNickName(httpRequest);
		setContentType(httpRequest, httpResponse);
		chain.doFilter(request, response); 
		
	}

	@Override
	public void destroy() {
		
	}
	public void setNickName(HttpServletRequest request){
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String nickName = "";
		String userName = "";
		if(userInfo!=null){
			nickName = userInfo.getNickName();
			userName = userInfo.getUserName();
			if("".equals(nickName)||nickName ==null){
				nickName = userName;
				Pattern p = Pattern.compile("^(13|14|15|18)\\d{9}$");
				Matcher m = p.matcher(nickName);
				if(m.matches()&&nickName.length()==11){
					nickName = nickName.substring(0, 3) + "****" + nickName.substring(7, 11);
				}
			}
			
		}
		request.setAttribute("nickName", nickName);
	}
	public void setContentType(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType(getContentType(request, response));
	}

	public String getContentType(HttpServletRequest request,
			HttpServletResponse response) {
		String acceptHeader = request.getHeader("accept");
		String type;
		if(acceptHeader==null){
			type = "text/html;charset=UTF-8";
		}else if (acceptHeader.indexOf("application/vnd.wap.xhtml+xml") != -1){
			type = "application/vnd.wap.xhtml+xml;charset=UTF-8";
		}
		else if (acceptHeader.indexOf("application/xhtml+xml") != -1){
			type = "application/xhtml+xml;charset=UTF-8";
		}
		else{
			type = "text/html;charset=UTF-8";
		}
		return type;
	}
}
