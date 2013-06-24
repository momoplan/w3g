<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><span>赠送信息</span></p>
    </div>
    <h1><a>确认投注</a></h1>
    <div class="user-moneychange" style="background:none;">
    	<form action="${pageContext.request.contextPath }/lottery/present.do" method="post">
    	<p class="prompt-pro">${messageError }</p>
    	<p>好友电话号码: （多个电话号码间用逗号分隔）</p>
        <textarea name="reciverMobiles" cols="" rows="" class="text-area" style=" margin:10px 0 10px 10px">${reciverMobiles }</textarea>	
        <p>您的赠言:</p>
        <textarea name="blessing" cols="" rows="" class="text-area" style=" margin:10px 0 10px 10px">${blessing }</textarea>        
        <p>彩种：${lotName }</p>
        <p>期号：${batchCode }</p>
        <p>赠送注数：${zhuShu }注</p>
        <p>赠送倍数：${beiShu }倍</p>
        <p>赠送金额：<span>${amountView }</span>元</p>
        <div class="xuxian-line"></div>
        <p>玩法：${playType }</p>
        <p>投注号码：</p>
        <p class="playball">
            <span>${betCodeView }</span>
        </p>
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
        <input name="token" value="${token }" type="hidden"/>
        <p><input name="" type="submit" value="确认投注" class="tz-chakan-btn" /></p>
    	</form>
    </div>