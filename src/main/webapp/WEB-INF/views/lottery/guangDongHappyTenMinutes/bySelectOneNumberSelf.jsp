<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	 <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><span>广东快乐十分</span></p>
    </div>
    
    <h1><a href="${pageContext.request.contextPath }/guangDongHappyTenMinutesIndex/bySelectOneNumberSelf" class="tit-cur">任一</a><span>|</span><a href="${pageContext.request.contextPath }/guangDongHappyTenMinutesIndex/byOptionTwoSelf">任二</a><span>|</span><a href="${pageContext.request.contextPath }/guangDongHappyTenMinutesIndex/byOptionThreeSelf">任三</a><span>|</span><a href="${pageContext.request.contextPath }/guangDongHappyTenMinutesIndex/byOptionFourSelf">任四</a><span>|</span><a href="${pageContext.request.contextPath }/guangDongHappyTenMinutesIndex/byOptionFiveSelf">任五</a><span>|</span><a href="${pageContext.request.contextPath }/guangDongHappyTenMinutesIndex/bySelectTwoLinkDirectSelf">选二连直</a><span>|</span><a href="${pageContext.request.contextPath }/guangDongHappyTenMinutesIndex/bySelectTwoLinkGroupSelf">选二连组</a><span>|</span><a href="${pageContext.request.contextPath }/guangDongHappyTenMinutesIndex/byDirectSelectForwardThreeSelf">前三直选</a><span>|</span><a href="${pageContext.request.contextPath }/guangDongHappyTenMinutesIndex/byGroupSelectForwardThreeSelf">前三组选</a></h1>
    <h3><a href="${pageContext.request.contextPath }/guangDongHappyTenMinutesIndex/bySelectOneNumberSelf" class="tittwo-cur">前一数投</a><span>|</span><a href="${pageContext.request.contextPath }/guangDongHappyTenMinutesIndex/bySelectOneNumberSingleAuto">数投机选</a><span>|</span><a href="${pageContext.request.contextPath }/guangDongHappyTenMinutesIndex/bySelectOneRedSelf">前一红投</a><span>|</span><a href="${pageContext.request.contextPath }/guangDongHappyTenMinutesIndex/bySelectOneRedSingleAuto">红投机选</a></h3>
    <div class="wap-banner">
    	<p>10分钟一期，每天84期。</p>
        <p>剩余时间：<em class="end-time">${remainingTime }</em></p>
        <p class="prompt-pro">${messageError }</p>
    </div>
            <form action="${pageContext.request.contextPath }/guangDongHappyTenMinutes/byOptionToSelf" method="post">
    <div class="wap-pro-goucai">
		<p style="font-weight: bold;">第${batchCode }期</p>
	</div>
	<input name="batchCode" value="${batchCode }" type="hidden"/>
    
    <h2 class="goucai-title"><span class="red-ball-num">选择号码：</span>（至少选择1个号码）</h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p no-red">
    		        	<c:forEach var="item" varStatus="status" begin="1" end="18">
			<c:set var="index" value="ball_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="ball" id="ball_${status.index}" value="${status.index}" ${balls[index] !=null ? 'checked' :''}
			/><c:choose><c:when test="${ status.index<10}">0${status.index}</c:when
			><c:otherwise>${status.index}</c:otherwise></c:choose>
			</span>
			<c:if test="${status.index%7==0 }">
				<br/>
			</c:if>
			</c:forEach>          
        </p>
        <p>选择<input name="ballNumber" type="text" class="num-ball" value="${ballNumber }" maxlength="2"/>个<input name="submitType" type="submit" value="机选号码" class="jx-redball" /></p>
    </div>
   
     <div class="wap-pro-gray-goucai" style="border-top:1px solid #999; border-bottom:none">
     	<input name="ballType" value="S1" type="hidden"/>
     		<p>倍数：<input name="beiShu" type="text" class="num-ball" value="${beiShu }" maxlength="5"/>（最多10000倍）</p>
            <p>追号：<input name="addNumber" type="text" class="num-ball" value="${addNumber }" maxlength="2"/>期<input name="prizeend" type="checkbox" value="1" style="width:12px; height:12px; vertical-align:middle; margin-left:30px;" ${'1' eq prizeend ?'checked' :'' }/>中奖后停止</p>
    </div>
    <p class="moneey-dlt">与开奖号第一位数字相同一致，奖金<span>25</span>元</p>
<p class="gx-tjiao"><input name="submitType" type="submit" value="提交投注" class="tz-chakan-btn" /></p>
    </form>