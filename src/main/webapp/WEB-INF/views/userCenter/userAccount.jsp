<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  	<div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span>账户余额</span></p>
    </div>
    <div class="wap-pro">
    	<p class="user-centermoney">总余额：<span>${ account.balance}</span> 元</p>
        <p class="user-centermoney">冻结金额:：<span>${account.freezeBalance }</span> 元</p>
        <p class="user-centermoney">可投注金额：<span>${account.betBalance }</span> 元</p>
        <p class="user-centermoney">可提现金额：<span>${account.drawBalance }</span> 元</p>
    </div>
