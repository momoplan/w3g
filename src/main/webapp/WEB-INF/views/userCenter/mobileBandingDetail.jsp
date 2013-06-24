<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <div class="wap-pro" style="padding-top:10px">
    	<p class="prompt-pro">${messageError }</p>
    	<form action="${pageContext.request.contextPath }/userInfo/cancelMobileBanding.do" method="post">
    		<p>用户名：${userName }</p>
        	<p>手机号：${mobileId }</p>
        	<input type="hidden" name="mobileId" value="${mobileId }"/>
        	<p><input type="submit" value="解&nbsp绑" class="user-check-btn" /></p>
    	</form>
    </div>
