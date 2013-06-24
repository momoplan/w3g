<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span>合买查询</span></p>
    </div>
    <h2 class="hemai-title">发起人信息</h2>
    <div class="user-moneychange hemai-pro">
    	<p>发起人：${caseLot.starter }</p>
    	<c:if test="${not empty caseLot.achievement }">
        	<p class="zhanji">战&nbsp;&nbsp;绩:${caseLot.achievement }</p>
        </c:if>
        
        <c:if test="${'true' eq caseLot.starterCancel }">
        	<form action="${pageContext.request.contextPath }/caseLotCenter/cancelCaseLot.do" method="post">
        	<input name="caseLotId" type="hidden" value="${caseLot.caseLotId }"/>
        	<p><input type="submit" class="hemai-btn-left" value="撤单" /></p>
        	</form>
        </c:if>
   </div>
   <h2 class="hemai-title">方案详情</h2>
   <div class="user-moneychange hemai-pro">
    	<p>方案编号：${caseLot.caseLotId }</p>
    	<p>彩种：${caseLot.lotName }</p>
    	<c:choose>
   			<c:when test="${fn:startsWith(caseLot.lotName, '竞彩')}">
   			</c:when>
   			<c:otherwise>
   				<p>认购截止时间：${caseLot.stopTime }</p>
   			</c:otherwise>
   		</c:choose>
       
        <p>倍数：${caseLot.beiShu }</p>
        <c:choose>
   			<c:when test="${fn:startsWith(caseLot.lotName, '竞彩')}">
   			</c:when>
   			<c:otherwise>
   				<p>期号：${caseLot.batchCode }</p>
   			</c:otherwise>
   		</c:choose>
        <p>方案金额：<span>${caseLot.totalAmt }</span>元</p>
        <p>最低认购：${caseLot.minAmt }元</p>
        <p>方案进度：${caseLot.progress }</p>
        <p>认购金额：<span>${caseLot.buyAmt }</span>元</p>
        <p>保底金额：${caseLot.safeAmt }元</p>
        <p>发起人认购：<span>${caseLot.buyAmtByStarter }</span>元</p>
        <p>发起人提成：${caseLot.commisionRatio }</p>
        <p>剩余金额：<span>${caseLot.betAmt }</span>元</p>
        <p>参与人数：${totalResult }人</p>
        <p>方案描述：${caseLot.description }</p>
    </div>
   <h2 class="hemai-title">方案内容</h2>
   <c:choose>
   		<c:when test="${caseLot.displayTlots ==true }">
   			<div class="user-moneychange hemai-pro" style="padding-top:0;">
   				<c:choose>
   					<c:when test="${fn:startsWith(caseLot.lotName, '竞彩')}">
   						${caseLot.betCode }
   					</c:when>
   					<c:otherwise>
   						<p>${caseLot.betCode }</p>
   					</c:otherwise>
   				</c:choose>
   			</div>
   		</c:when>
   		<c:otherwise>
   			<div class="user-moneychange hemai-pro">
   				<p>${caseLot.visibility }</p>
   			</div>
   		</c:otherwise>
   </c:choose>
   <c:if test="${not empty caseLot.userTotalAmt || not empty caseLot.userSafeAmt}">
   		<h2 class="hemai-title">我的认购</h2>
   <div class="user-moneychange hemai-pro">
   		<c:if test="${not empty caseLot.userTotalAmt }">
   			<p>认购金额：${caseLot.userTotalAmt }元</p>
   		</c:if>
   		<c:if test="${not empty caseLot.userSafeAmt }">
   			<p>保底金额：${caseLot.userSafeAmt }元</p>
   		</c:if>
		<c:if test="${'true' eq caseLot.buyCancel }">
			<form action="${pageContext.request.contextPath }/caseLotCenter/cancelCaseLotBuy.do" method="post">
        	<input name="caseLotId" type="hidden" value="${caseLot.caseLotId }"/>
        	<p><input type="submit" class="hemai-btn-left" value="撤资" /></p>
        	</form>
        </c:if>
   </div>
   </c:if>
    
   <c:if test="${not empty caseLot.winCode }">
   		<h2 class="hemai-title">开奖号码</h2>
   		<div class="user-moneychange hemai-pro">
   		  <p>${caseLot.winCode }</p>
   		</div>
   </c:if>
   <c:if test="${'未中奖' eq caseLot.prizeState ||'已中奖' eq caseLot.prizeState }">
   	 <h2 class="hemai-title">中奖金额</h2>
   		<div class="user-moneychange hemai-pro">
        	<p>中奖总额：${caseLot.winBigAmt }元</p>
        	<p>我的奖金：<span>${caseLot.userPrizeAmt }</span>元</p>
   		</div>
   </c:if>
  
   <h2 class="hemai-title">参与人员<a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLotBuysSimplify?caseLotId=${caseLot.caseLotId}">查看详情</a></h2>
	<c:choose>
		<c:when test="${'认购中' eq caseLot.displayStateMemo }">
			<div class="user-moneychange hemai-pro" style="background:#fdfce0">
				<form action="${pageContext.request.contextPath }/caseLotCenter/betCaseLot.do" method="post">
					<p class="prompt-pro">${messageError }</p>
					<p>可认购金额：<span>${caseLot.betAmt }</span>元</p>
        			<p>我要认购：<input name="buyAmount" type="text"  class="rengou" value="${buyAmount }"/>元</p>
        			<p>我要保底：<input name="buySafeAmount" type="text" class="rengou" value="${buySafeAmount==null?'0': buySafeAmount}"/>元</p>
        			<input name="caseLotId" type="hidden" value="${caseLot.caseLotId }"/>
        			<input name="totalAmt" type="hidden" value="${caseLot.totalAmt }"/>
        			<input name="buyAmt" type="hidden" value="${caseLot.buyAmt }"/>
        			<input name="minAmt" type="hidden" value="${caseLot.minAmt }"/>
        			<input name="safeAmt" type="hidden" value="${caseLot.safeAmt }"/>
        			<p><input type="submit" value="立即购买" class="hemai-btn-left" /></p>
				</form>
    		</div>
		</c:when>
		<c:otherwise>
			<div class="user-moneychange hemai-pro" style="background:#fdfce0; height:45px; position:relative">
    			<a class="manyuan">方案${caseLot.displayStateMemo }</a>
    		</div>
		</c:otherwise>
	</c:choose>
	