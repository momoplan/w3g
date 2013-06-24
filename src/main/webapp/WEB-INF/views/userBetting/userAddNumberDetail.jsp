<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span><a href="${pageContext.request.contextPath }/select/userAddNumber.do">追号查询</a></span></p>
    </div>
    
   <div class="user-moneychange" style="background:none">
   		<c:if test="${not empty order }">
   			<p>彩种：${order.lotName }</p>
        	<p>订单号：${order.flowNo }</p>
        	<p>发起时间：${order.beginTime }</p>
        	<p>起始期数：<span>${order.beginBatch }</span>期</p>
        	<p>追号期数：<span>${order.batchNum }</span>期</p>
        	<p>已追期期数：<span>${order.nowNum }</span>期</p>
        	<p>已撤销期数：<span>${order.failNum }</span>期</p>
        	<p>总金额：<span>${order.totalAmount }元</span></p>
        	<p>已追金额：<span>${order.successAmount }元</span></p>
        	<p>当前状态：${order.state }</p>
        	<p>中奖后是否停止：${"1" eq order.prizeend ? "是" : "否" }</p>
        	<div class="xuxian-line"></div>
        	<p>方案内容：<br />
        	<p class="playball"><span>${order.betCode }</span></p>
        	<c:if test="${'进行中' eq order.state }">
        		<form action="${pageContext.request.contextPath }/select/userGiveUpAddNumberDetail.do" method="post">
        			 <input type="hidden" name="flowNo" value="${order.flowNo }"/>
        			<p><input type="submit" class="tz-chakan-btn" value="停止追号" /></p>
        		</form>
        	</c:if>
   			
   		</c:if>
    </div>
