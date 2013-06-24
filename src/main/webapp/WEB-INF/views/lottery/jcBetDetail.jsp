 <%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><span>确认投注</span></p>
    </div>
    <h1><a>确认投注</a></h1>
    <div class="user-moneychange" style="background:none;">
    	<p>您的投注</p>
        <div class="xuxian-line"></div>
        <p>彩种：${lotName }</p>
        <p>金额：<span>${amountView }</span>元</p>
        <p>注数：${zhuShu }注</p>
        <p>倍数：${beiShu }倍</p>
        </div>
        <h2 class="goucai-title">方案内容</h2>
          <div class="wap-pro-goucai">
            <span>${betCodeView }</span>
         </div>
    <form action="${pageContext.request.contextPath }/lottery/bet.do" method="post">
    			<div class="wap-pro-gray" style=" border-top:1px solid #dbdbdb; border-bottom:none">
    			温馨提示:<p class="prompt-pro">发起合买方案金额不能小于6元</p>
        	<c:choose>
        		<c:when test="${not empty buyWays }">
        			<input name="buyWays" type="radio" value="buy" class="touzhu-radio" ${'buy' eq buyWays ? 'checked':'' }/>普通投注<br />
            		<input name="buyWays" type="radio" value="caseLot" class="touzhu-radio" ${'caseLot' eq buyWays ? 'checked':'' }/>发起合买
        		</c:when>
        		<c:otherwise>
        			<input name="buyWays" type="radio" value="buy" class="touzhu-radio" checked="checked"/>普通投注<br />
            		<input name="buyWays" type="radio" value="caseLot" class="touzhu-radio" />发起合买
        		</c:otherwise>
        	</c:choose>
        </div>    	
        <input name="betCode" value="${betCode }" type="hidden"/>
        <input name="betCodeView" value="${betCodeView }" type="hidden"/>
        <input name="beiShu" value="${beiShu }" type="hidden"/>
        <input name="zhuShu" value="${zhuShu }" type="hidden"/>
        <input name="amount" value="${amount }" type="hidden"/>
        <input name="amountView" value="${amountView }" type="hidden"/>
        <input name="batchCode" value="${batchCode }" type="hidden"/>
        <input name="lotNo" value="${lotNo }" type="hidden"/>
        <input name="playType" value="${playType }" type="hidden"/>
        <input name="oneAmount" value="${oneAmount }" type="hidden"/>
        <input name="token" value="${token }" type="hidden"/>
        <div class="user-moneychange" style="background:#faf9f9;">
        <p><input name="" type="submit" value="确认投注" class="tz-chakan-btn" /></p>
        </div>
    </form>