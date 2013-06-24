 <%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><span>十一运夺金</span></p>
    </div>
    
    <h1><a href="${pageContext.request.contextPath }/shanDongElevenDuoJinIndex/byOptionOneSelf" >任一</a><span>|</span><a href="${pageContext.request.contextPath }/shanDongElevenDuoJinIndex/byOptionTwoSelf">任二</a><span>|</span><a class="tit-cur">任三</a><span>|</span><a href="${pageContext.request.contextPath }/shanDongElevenDuoJinIndex/byOptionFourSelf">任四</a><span>|</span><a href="${pageContext.request.contextPath }/shanDongElevenDuoJinIndex/byOptionFiveSelf">任五</a><span>|</span><a href="${pageContext.request.contextPath }/shanDongElevenDuoJinIndex/byOptionSixSelf">任六</a><span>|</span><a href="${pageContext.request.contextPath }/shanDongElevenDuoJinIndex/byOptionSevenSelf">任七</a><span>|</span><a href="${pageContext.request.contextPath }/shanDongElevenDuoJinIndex/byOptionEightSelf">任八</a></h1>
    <h3><a href="${pageContext.request.contextPath }/shanDongElevenDuoJinIndex/byOptionThreeSelf"> 直选</a><span>|</span><a href="${pageContext.request.contextPath }/shanDongElevenDuoJinIndex/byForwardThreeDirectSelect">前三直选</a><span>|</span><a href="${pageContext.request.contextPath }/shanDongElevenDuoJinIndex/byForwardThreeGroupSelect">前三组选</a><span>|</span><a class="tittwo-cur">胆拖</a><span>|</span><a href="${pageContext.request.contextPath }/shanDongElevenDuoJinIndex/byForwardThreeGroupSelectDanTuo">组选胆拖</a><span>|</span><a href="${pageContext.request.contextPath }/shanDongElevenDuoJinIndex/byOptionThreeAuto">机选</a></h3>
    <div class="wap-banner">
    	<p>10分钟一期，每天78期。</p>
        <p>剩余时间：<em class="end-time">${remainingTime }</em></p>
        <p class="prompt-pro">${messageError }</p>
    </div>
    <form action="${pageContext.request.contextPath }/shanDongElevenDuoJin/byDanTuo" method="post">
    <div class="wap-pro-goucai">
		<p style="font-weight: bold;">第${batchCode }期</p>
	</div>
	<input name="batchCode" value="${batchCode }" type="hidden"/>
    <h2 class="goucai-title"><span class="red-ball-num">选择胆码：</span>（选择1-2个号码）</h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p no-red">
        	<c:forEach var="item" varStatus="status" begin="1" end="11">
			<c:set var="index" value="dball_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="dball" id="dball_${status.index}" value="${status.index}" ${dballs[index] !=null ? 'checked' :''}
			/><c:choose><c:when test="${ status.index<10}">0${status.index}</c:when
			><c:otherwise>${status.index}</c:otherwise></c:choose>
			</span>
			<c:if test="${status.index%7==0 }">
				<br/>
			</c:if>
			</c:forEach>
           
            
        </p>
    </div>
    <h2 class="goucai-title"><span class="red-ball-num">选择拖码：</span>（至少选择1个号码，12>胆+拖>3，胆、拖不重复）</h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p no-red">
        	<c:forEach var="item" varStatus="status" begin="1" end="11">
			<c:set var="index" value="tball_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="tball" id="tball_${status.index}" value="${status.index}" ${tballs[index] !=null ? 'checked' :''}
			/><c:choose><c:when test="${ status.index<10}">0${status.index}</c:when
			><c:otherwise>${status.index}</c:otherwise></c:choose>
			</span>
			<c:if test="${status.index%7==0 }">
				<br/>
			</c:if>
			</c:forEach>
           
            
        </p>
    </div>
   
   
     <div class="wap-pro-gray-goucai" style="border-top:1px solid #999; border-bottom:none">
     	<input name="ballType" value="R3" type="hidden"/>
     		<p>倍数：<input name="beiShu" type="text" class="num-ball" value="${beiShu }" maxlength="5"/>（最多10000倍）</p>
            <p>追号：<input name="addNumber" type="text" class="num-ball" value="${addNumber }" maxlength="2"/>期<input name="prizeend" type="checkbox" value="1" style="width:12px; height:12px; vertical-align:middle; margin-left:30px;" ${'1' eq prizeend ?'checked' :'' }/>中奖后停止</p>
    </div>
   
<p class="gx-tjiao"><input name="submitType" type="submit" value="提交投注" class="tz-chakan-btn" /></p>
</form>