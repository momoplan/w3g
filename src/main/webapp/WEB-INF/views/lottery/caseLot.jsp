<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

  <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><span>发起合买</span></p>
    </div>
    <h1><a>发起合买</a></h1>
    <form action="${pageContext.request.contextPath }/lottery/caseLot.do" method="post">
    <div class="user-moneychange" style="background:none;">
        <p>彩种：${lotName }</p>
        <c:choose>
   			<c:when test="${fn:startsWith(lotName, '竞彩')}">
   			</c:when>
   			<c:otherwise>
   				<p>期号：${batchCode }</p>
   			</c:otherwise>
   		</c:choose>
        <p>倍数：${beiShu }</p>
        <p>注数：${zhuShu }</p>
        <p>注码：</p>
        <p>${betCodeView }</p>
        <p>方案总额：<span>${amountView }</span>元</p>
        <p class="prompt-pro">${messageError }</p>
        <p>我要认购：<input name="buyAmt" type="text" class="touzhu-text" value="${buyAmt }" />元</p>
        <p>最低参与：<input name="minAmt" type="text" class="touzhu-text" value="${minAmt }"/>元</p>
        <p>我要保底：<input name="safeAmt" type="text" class="touzhu-text" value="${safeAmt }"/>元</p>
        <p>我的提成：<select name="commisionRatio" class="touzhu-text" >
        	<option ${commisionRatio eq '1' ? 'selected' : ''}>1</option>
        	<option ${commisionRatio eq '2' ? 'selected' : ''}>2</option>
        	<option ${commisionRatio eq '3' ? 'selected' : ''}>3</option>
        	<option ${commisionRatio eq '4' ? 'selected' : ''}>4</option>
        	<option ${commisionRatio eq '5' ? 'selected' : ''}>5</option>
        	<option ${commisionRatio eq '6' ? 'selected' : ''}>6</option>
        	<option ${commisionRatio eq '7' ? 'selected' : ''}>7</option>
        	<option ${commisionRatio eq '8' ? 'selected' : ''}>8</option>
        	<option ${commisionRatio eq '9' ? 'selected' : ''}>9</option>
        	<option ${commisionRatio eq '10'||commisionRatio ==null ? 'selected' : ''}>10</option>
        	</select>%</p>
        <p>是否公开：</p>
        	<p><input name="visibility" type="radio" value="0" class="touzhu-radio" ${visibility eq '0' ||visibility ==null ? 'checked' : ''}/>对所有人公开</p>
            <p><input name="visibility" type="radio" value="2" class="touzhu-radio" ${visibility eq '2' ? 'checked' : ''}/>对所有人截止后公开</p>
            <p><input name="visibility" type="radio" value="3" class="touzhu-radio" ${visibility eq '3' ? 'checked' : ''}/>对参与者立即公开</p>
            <p><input name="visibility" type="radio" value="4" class="touzhu-radio" ${visibility eq '4' ? 'checked' : ''}/>对参与者截止后公开</p>
            <p><input name="visibility" type="radio" value="1" class="touzhu-radio" ${visibility eq '1' ? 'checked' : ''}/>保密</p>
        <p>方案描述：<br /><textarea name="desc" cols="" rows="" class="touzhu-textarea">${desc }</textarea></p>
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
        <p><input name="" type="submit" value="发起合买" class="tz-chakan-btn" /></p>
    </div>
    	
        
    </form>