<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span><a href="${pageContext.request.contextPath }/chargeCenter">账户充值</a></span></p>
    </div>
    <div class="wap-pro">
    	<p class="tishi-blank">${messageError }</p>
        <p><a href="${pageContext.request.contextPath }/lotteryCenter" class="tz-chakan-btn-1">立即购彩</a></p>
    </div>   