<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><span>用户登录</span></p>
    </div>
    <div class="wap-pro" style="padding-top:8px">
    <p class="prompt-pro">${messageError }</p>
    <form action="${pageContext.request.contextPath }/userInfo/login" method="post">
    	<p>用户名：<input name="userName" type="text" value="${userName }"/></p>
        <p>密&nbsp;&nbsp;&nbsp;&nbsp;码：<input name="passWord" type="password" value="${passWord }"/></p>
        <p class="onload"><input class="onload-btn" name="login" type="submit" value="登录"/></p>
    </form>
    	
    </div>
    <div class="wap-pro-gray" style="border-top:1px solid #d6d6d3; border-bottom:none;">
    	<p><a href="${pageContext.request.contextPath }/retrievePassWord" style="padding:0 20px 0 10px">忘记密码</a><a href="${pageContext.request.contextPath }/userInfo/registerIndex">免费注册</a></p>
    </div>
