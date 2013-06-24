 <%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><span>投注结果</span></p>
    </div>
    <div class="wap-pro">
    	<p class="tishi-blank tishi-blank-red">${messageError }</p>
        <p><a href="${pageContext.request.contextPath }/lotteryCenter" class="tz-chakan-btn-1">返回购彩大厅</a></p>
        
    </div>