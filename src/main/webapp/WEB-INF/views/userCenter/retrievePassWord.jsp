<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><span>找回密码</span></p>
    </div>
    <div class="wap-pro" style="padding-top:8px">
    <p class="prompt-pro">${messageError }</p>
    	<form action="${pageContext.request.contextPath }/userInfo/retrievePassWord" method="post">
    	<p>用户名：<input name="userName" type="text" value="${userName }"/></p>
        <p>手机号：<input name="mobileId" type="text" value="${mobileId }"/></p>
        <p class="onload"><input type="submit" class="find-btn" value="立即找回" /></p>
        </form>
        
    </div>
    <div class="wap-pro-gray" style="border-top:1px solid #D6D6D3; border-bottom:none"><p>输入信息后，密码以短信发送到您绑定的手机里，请及时修改密码。如果您尚未绑定手机号或用户名忘记了，请拨打如意彩客服电话400-665-1000，让客服人员协助您找回密码。</p></div>
