<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <div class="wap-pro" style="padding-top:10px">
    	<p class="prompt-pro">${messageError }</p>
    	<form action="${pageContext.request.contextPath }/userInfo/mobileBanding.do" method="post">
    		<p>手机号：${mobileId }</p>
        	<p>验证码：<input name="checkCode" type="text" value="${checkCode }"/></p>
        	<input type="hidden" name="mobileId" value="${mobileId }"/>
        	<p><input type="submit" value="绑&nbsp定" class="user-check-btn" /></p>
    	</form>
    </div>
