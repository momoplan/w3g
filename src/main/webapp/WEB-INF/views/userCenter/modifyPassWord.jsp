<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span>修改密码</span></p>
    </div>
    <div class="wap-pro" style="padding-top:10px">
    	<p class="prompt-pro">${messageError }</p>
    	<form action="${pageContext.request.contextPath }/userInfo/resetPassWord.do" method="post">
    		<p>原密码：&nbsp;&nbsp;<input name="oldPassWord" type="password" value="${oldPassWord }"/></p>
        	<p>新密码：&nbsp;&nbsp;<input name="newPassWord" type="password" /></p>
        	<p>确认密码：<input name="reNewPassWord" type="password" /></p>
        	<p><input type="submit" value="修&nbsp改" class="user-check-btn" /></p>
    	</form>
    </div>