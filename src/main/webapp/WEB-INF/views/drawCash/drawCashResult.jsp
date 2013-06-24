<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  	<div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span>提现</span></p>
    </div>
    <div class="wap-pro">
    	<p class="tishi-blank">${messageError }</p>
        <p><a href="${pageContext.request.contextPath }/drawCash/drawCashHistory.do" class="tz-chakan-btn-1">查看提现记录</a></p>
       
    </div>
