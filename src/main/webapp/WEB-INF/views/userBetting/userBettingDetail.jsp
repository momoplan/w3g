<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
     <div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span><a href="${pageContext.request.contextPath }/select/userBetting.do">投注查询</a></span></p>
    </div>
    
    <div class="user-moneychange" style="background:none">
    	<p>彩种：${lotName }</p>
    	<c:if test="${not empty batchCode && 'null' != batchCode }">
    		<p>期号：<span>${batchCode }</span></p>
    	</c:if>
        <p>订单号：${orderId }</p>
        <p>投注时间：${createTime }</p>
        <p>倍数：${beiShu }</p>
        <p>注数：${zhuShu }</p>
        <p>金额：<span>${amount }元</span></p>
        <c:choose>
        		<c:when test="${'已中奖' eq prizeState }">
        			<p>中奖金额：<span>${orderPrize }元</span></p>
        		</c:when>
        		<c:otherwise>
        			<p>${prizeState }</p>
        		</c:otherwise>
        	</c:choose>
        <div class="xuxian-line"></div>
        <p>方案内容：<p/>
        <div class="user-moneychange hemai-pro" style="padding-top:0;">
        <c:choose>
        	<c:when test="${fn:startsWith(lotName, '竞彩')}">
        		${betCode==null||"" eq betCode?'暂不支持！': betCode}
        	</c:when>
        	<c:otherwise>
        		<p class="playball">${betCode==null||"" eq betCode?'暂不支持！': betCode}</p>
        	</c:otherwise>
        </c:choose>
        </div>
        <c:choose>
        	<c:when test="${not empty winBaseCode && ' ' != winBaseCode}">
        		<p>开奖号码：${winBaseCode }</p>
        	</c:when>
        	<c:when test="${fn:startsWith(lotName, '竞彩')}">
        	</c:when>
        	<c:otherwise>
        		<p>开奖号码：${prizeState }</p>
        	</c:otherwise>
        </c:choose>
    </div>
