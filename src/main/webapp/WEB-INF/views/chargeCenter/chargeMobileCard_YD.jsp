<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	 <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span><a href="${pageContext.request.contextPath }/chargeCenter">账户充值</a></span></p>
    </div>
    <h1><a>充值卡充值</a></h1>
   <div class="wap-pro-gray czhika"><a class="czhika-cur">移动充值卡</a> | <a href="${pageContext.request.contextPath }/chargeMobileCard?type=LT">联通充值卡</a> | <a href="${pageContext.request.contextPath }/chargeMobileCard?type=DX">电信充值卡</a></div>
    <div class="wap-pro">
      <p><img src="${pageContext.request.contextPath }/images/YD.png" width="120" height="30" /></p>
        <form action="${pageContext.request.contextPath }/chargeCenter/nineteenPayCardCharge.do" method="post">
        	<p>请确认您选择的是: ${param.totalAmount }元充值卡</p>
        	<p>序列号：&nbsp;&nbsp;<input name="cardNo" type="text" value="${cardNo }"/></p>
        	<p>充值密码：<input name="cardPassWord" type="text" value="${cardPassWord }"/></p>
        	<input name="cardType" type="hidden" value="YD"/>
        	<input name="totalAmount" type="hidden" value="${param.totalAmount }"/>
        	<input name="amount" type="hidden" value="${param.totalAmount }"/>
        	<p><input name="" type="submit" value="立即充值" class="tz-chakan-btn" /></p>
        </form>
       
    </div>