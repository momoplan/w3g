<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	 <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span><a href="${pageContext.request.contextPath }/chargeCenter">账户充值</a></span></p>
    </div>
    <h1><a>支付宝快捷充值</a></h1>
    <div class="wap-pro user-addmoney">
    	<img src="${pageContext.request.contextPath }/images/user-ZFB.png" width="120" height="30" />
        <p class="noone">即时入账，无手续费</p>
   	    <p>充值金额：${amount }元</p>
   	    <c:if test="${not empty supportTopPayChannelList }">
			<c:forEach items="${supportTopPayChannelList }" var="supportTopPayChannel" varStatus="supportTopPayChannelIndex">
				<c:if test="${supportTopPayChannelIndex.index ==0}"><p>①${supportTopPayChannel.name }</p></c:if>
				<c:if test="${supportTopPayChannelIndex.index ==1}"><p>②${supportTopPayChannel.name }</p></c:if>			
				<c:forEach items="${supportTopPayChannel.supportSecPayChannelList }" var="supportSecPayChannel" varStatus="supportSecPayChannelIndex">
					<form action="${pageContext.request.contextPath }/chargeCenter/chargeAlipay_KJ.do" method="POST">
						<input type="hidden" name="amount" value="${amount }">
						<input type="hidden" name="checkbox" value="${checkbox }">
						<input type="hidden" name="cashierCode" value="${supportSecPayChannel.cashierCode }">
						<input type="hidden" name="name" value="${supportSecPayChannel.name }">
						<c:choose>
							<c:when test="${supportSecPayChannelIndex.index+1 == fn:length(supportTopPayChannel.supportSecPayChannelList) }">
								<p><input type="submit" value="${supportSecPayChannel.name }银行  " class="user-czk-btn"/></p>
							</c:when>
							<c:when test="${supportTopPayChannelIndex.index ==0}">
								<p><input type="submit" value="${supportSecPayChannel.name }信用卡" class="user-czk-btn"/></p>
							</c:when>
							<c:when test="${supportTopPayChannelIndex.index ==1}">
								<p><input type="submit" value="${supportSecPayChannel.name }储蓄卡" class="user-czk-btn"/></p>
							</c:when>
						</c:choose>
					</form>
				</c:forEach>
			</c:forEach>
		</c:if>
    </div>
   