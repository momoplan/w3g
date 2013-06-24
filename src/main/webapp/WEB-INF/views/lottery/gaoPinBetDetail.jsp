 <%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><span>确认投注</span></p>
    </div>
    <h1><a>确认投注</a></h1>
    <div class="user-moneychange" style="background:none;">
    	<p>您的投注</p>
        <div class="xuxian-line"></div>
        <p>${lotName } 第${batchCode }期</p>
        <p>金额：<span>${amountView }</span>元</p>
        <p>注数：${zhuShu }注</p>
        <p>倍数：${beiShu }倍</p>
        <p>追号：${addNumber }期</p>
        <div class="xuxian-line"></div>
        <p>玩法：${playType }</p>
        <p>投注号码：</p>
        <p class="playball">
            <span>${betCodeView }</span>
        </p>
    </div>
    <form action="${pageContext.request.contextPath }/lottery/bet.do" method="post">
        <input name="betCode" value="${betCode }" type="hidden"/>
        <input name="betCodeView" value="${betCodeView }" type="hidden"/>
        <input name="beiShu" value="${beiShu }" type="hidden"/>
        <input name="zhuShu" value="${zhuShu }" type="hidden"/>
        <input name="addNumber" value="${addNumber }" type="hidden"/>
        <input name="amount" value="${amount }" type="hidden"/>
        <input name="amountView" value="${amountView }" type="hidden"/>
        <input name="batchCode" value="${batchCode }" type="hidden"/>
        <input name="lotNo" value="${lotNo }" type="hidden"/>
        <input name="playType" value="${playType }" type="hidden"/>
        <input name="oneAmount" value="${oneAmount }" type="hidden"/>
        <input name="prizeend" value="${prizeend }" type="hidden"/>
        <input name="token" value="${token }" type="hidden"/>
        <!--presentType 0-Open 1-Close  -->
        <input name="presentType" value="" type="hidden"/>
        <div class="user-moneychange" style="background:#faf9f9;">
        <p><input name="" type="submit" value="确认投注" class="tz-chakan-btn" /></p>
        </div>
    </form>