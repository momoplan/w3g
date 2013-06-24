<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	 <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span><a href="${pageContext.request.contextPath }/chargeCenter">账户充值</a></span></p>
    </div>
    <h1><a>支付宝快捷充值</a></h1>
     <div class="wap-pro user-addmoney">
    	<img src="${pageContext.request.contextPath }/images/user-ZFB.png" width="120" height="30" />
        <p class="noone">即时入账，无手续费</p>
        <p class="prompt-pro">${messageError }</p>
        <form action="${pageContext.request.contextPath }/chargeCenter/chargeAlipayChannel.do" method="post">
        	<p>充值金额(整数)：<input name="amount" type="text" value="${amount }"/>元</p>
        	<p><input type="submit" value="充值" class="user-check-btn" /></p>
        </form>
    </div>
   