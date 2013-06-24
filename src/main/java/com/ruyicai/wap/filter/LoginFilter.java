package com.ruyicai.wap.filter;

import java.io.IOException;

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

public class LoginFilter implements Filter {
	Logger logger = Logger.getLogger(WapFilter.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;   
		HttpServletResponse httpResponse = (HttpServletResponse) response;   
		UserInfo userInfo = (UserInfo) httpRequest.getSession().getAttribute("userInfo");
		if(userInfo==null){
			String loginUrl = httpRequest.getHeader("Referer");
			logger.info("LoginFilter/doFilter:loginUrl:"+ loginUrl);
			httpRequest.getSession().setAttribute("loginUrl", loginUrl);
			httpResponse.sendRedirect("/w3g/login");
		}

		chain.doFilter(request, response); 

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
