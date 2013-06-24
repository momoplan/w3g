<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span>完善信息</span></p>
    </div>
    <div class="wap-pro" style="padding-top:10px">
    	<form action="${pageContext.request.contextPath }/userInfo/perfectUserInfo.do" method="post">
    		<p class="prompt-pro">${messageError }</p>
    		<c:choose>
    			<c:when test="${not empty nickNameView }">
    				<p>用户昵称：${nickNameView }</p>
    				<input name="nickNameView" type="hidden" value="${nickNameView }" />
    			</c:when>
    			<c:otherwise>
    				<p>用户昵称：<input name="nickName" type="text" value="${nickName }"/></p>
    			</c:otherwise>
    		</c:choose>
    		<c:choose>
    			<c:when test="${not empty nameView }">
    				<p>真实姓名：${nameView }</p>
    				<input name="nameView" type="hidden" value="${nameView }"/>
    			</c:when>
    			<c:otherwise>
    				<p>真实姓名：<input name="name" type="text" value="${name }"/></p>
    			</c:otherwise>
    		</c:choose>
    		<c:choose>
    			<c:when test="${not empty certIdView }">
    				<p>身份证号：${certIdView }</p>
    				<input name="certIdView" type="hidden" value="${certIdView }"/>
    			</c:when>
    			<c:otherwise>
    				<p>身份证号：<input name="certId" type="text" value="${certId }"/></p>
    			</c:otherwise>
    		</c:choose>
        	<input name="token" value="${token }" type="hidden"/>
        	<p><input type="submit" value="确&nbsp认" class="user-check-btn queren-btn-left" /><input name="submitType" type="submit" value="重&nbsp;填" class="user-check-btn queren-btn-right" /></p>
    	</form>
    </div>