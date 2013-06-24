<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span>完善信息</span></p>
    </div>
    <div class="wap-pro" style="padding-top:10px">
    	<p class="prompt-pro">${messageError }</p>
        <p>用户昵称：${nickName }</p>
        <p>真实姓名：${name }</p>
        <p>身份证号：${certId }</p>
    </div>