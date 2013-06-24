<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span>手机绑定</span></p>
    </div>
    <div class="wap-pro" style="padding-top:10px">
    	<p class="prompt-pro">${messageError }</p>
    	<form action="${pageContext.request.contextPath }/userInfo/mobileBandingMsg.do" method="post">
    		<p>用户名：${userName }</p>
        	<p>手机号：<input name="mobileId" type="text" value="${mobileId }"/></p>
        	<p><input type="submit" value="绑&nbsp定" class="user-check-btn" /></p>
    	</form>
    </div>
