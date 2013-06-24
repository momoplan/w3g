 <%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><span>双色球</span></p>
    </div>
    <h1><a href="${pageContext.request.contextPath }/doubleBallIndex/bySelf">自选</a><span>|</span><a  class="tit-cur">机选</a><span>|</span><a href="${pageContext.request.contextPath }/doubleBallIndex/byDanTuo">胆拖</a></h1>
    <div class="wap-banner">
    	<p>每周二、四、日晚开奖，单注最高奖金1000万元！</p>
        <p>代购截止时间：<em class="end-time">${betEndTime }</em></p>
        <p>合买截止时间：<em class="end-time">${heMaiEndTime }</em></p>
        <p class="prompt-pro">${messageError }</p>
    </div>
    <form action="${pageContext.request.contextPath }/doubleBall/byAuto" method="post">
    	<div class="wap-pro-goucai">
		<p style="font-weight: bold;">第${batchCode }期</p>
		</div>
		<input name="batchCode" value="${batchCode }" type="hidden"/>
    	<p class="wap-pro">机选：<input name="autoZhuShu" type="text"  class="num-ball" value="${autoZhuShu }" maxlength="2"/>注<input name="submitType" type="submit" value="机选" class="jx-button" /></p>
    	<c:if test="${not empty betCodeView }">
    		<div class="jixuan-xq">
    		<div class="xuxian-line"></div>
    		<p>注码：</p>
            <p>${betCodeView }</p>
            <input name="betCode" type="hidden" value="${betCode }"/>
            <input name="betCodeView" type="hidden" value="${betCodeView }"/>
            <input name="playType" type="hidden" value="${playType }"/>
    		</div>
    	</c:if>
    	<input name="zhuShu" type="hidden" value="${zhuShu }"/>
    	<div class="wap-pro-gray-goucai" style="border-top:1px solid #999; border-bottom:none">
     		<p>倍数：<input name="beiShu" type="text" class="num-ball" value="${beiShu }" maxlength="5"/>（最多10000倍）</p>
            <p>追号：<input name="addNumber" type="text" class="num-ball" value="${addNumber }" maxlength="2"/>期<input name="prizeend" type="checkbox" value="1" style="width:12px; height:12px; vertical-align:middle; margin-left:30px;" ${'1' eq prizeend ?'checked' :'' }/>中奖后停止</p>
    	</div>
     <p class="gx-tjiao"><input name="submitType" type="submit" value="提交投注" class="tz-chakan-btn" /></p>
    </form>
    