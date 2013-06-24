<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><span>用户注册</span></p>
    </div>
    <div class="wap-pro" style="padding-top:8px">
    <p class="prompt-pro">${messageError }</p>
    	<form action="${pageContext.request.contextPath }/userInfo/register" method="post">
    	<p>用户名：&nbsp;&nbsp;<input name="userName" type="text" value="${userName }"/></p>
        <div class="xianzhi">(4-16个字符，可用字母、数字、  
          下划线)</div>
        <p>登录密码：<input name="passWord" type="password" value="${passWord }"/></p>
		<div class="xianzhi">(6-16个字符，建议使用字母、
          数字组合、混合大小写)</div>
        <p>确认密码：<input name="rePassWord" type="password" value="${rePassWord }"/></p>
        <input name="token" type="hidden" value="${token }"/>
        <p class="lj-zhuce"><input type="submit" value="立即注册"></p>
        <p style=" padding-bottom:10px"><input name="checkBox" type="checkbox" value="1" checked="checked" class="fuxuan" />我已满十八周岁且已阅读并同意<a href="${pageContext.request.contextPath }/userProtocol" class="daigou">《用户服务协议》</a></p>
        </form>
    </div>
