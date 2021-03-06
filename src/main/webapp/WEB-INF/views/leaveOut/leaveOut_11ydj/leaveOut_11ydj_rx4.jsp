<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/leaveOut_index">遗漏值</a>-</strong><span>十一运夺金</span></p>
    </div>
    <h1><a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01012&key=T01012MV_RX&type=rx2" >任二</a><span>|</span><a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01012&key=T01012MV_RX&type=rx3">任三</a><span>|</span><a class="tit-cur">任四</a><span>|</span><a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01012&key=T01012MV_RX&type=rx5">任五</a><span>|</span><a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01012&key=T01012MV_RX&type=rx6">任六</a><span>|</span><a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01012&key=T01012MV_RX&type=rx7">任七</a><span>|</span><a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01012&key=T01012MV_RX&type=rx8">任八</a></h1>
   
    <div class="wap-banner">
    <p>第${batchCode }期</p>
    <p>剩余时间：<em class="end-time">${remainingTime }</em></p>
    </div>
        <form action="${pageContext.request.contextPath }/shanDongElevenDuoJin/byOptionToSelf" method="post">
    <div class="wap-pro-goucai">
		<p>第${batchCode }期</p>
	</div>
	<input name="batchCode" value="${batchCode }" type="hidden"/>
    
       	<c:set var="leaveOut" value="${leaveOutList[0] }"></c:set>
    
   	<h2 class="goucai-title"><span class="red-ball-num">任四当前期遗漏值：</span></h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p">
        
        <c:forEach var="item" varStatus="status" begin="1" end="11">
			<c:set var="index" value="ball_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="ball" id="ball_${status.index}" value="${status.index}" ${balls[index] !=null ? 'checked' :''}
			/><c:choose><c:when test="${ status.index<10}">0${status.index}</c:when
			><c:otherwise>${status.index}</c:otherwise></c:choose>
			</span>
			<span class="yilou-num">[${leaveOut.leaveOutCodeList[status.index-1]<10?'&nbsp;':'' }${leaveOut.leaveOutCodeList[status.index-1] }]</span>
			
			<c:if test="${status.index%3==0 }">
				<br/>
			</c:if>
			</c:forEach>
      </p>
      
    </div>
     
     <div class="wap-pro-gray-goucai" style="border-top:1px solid #999; border-bottom:none">
     	<input name="ballType" value="R4" type="hidden"/>
     		<p>倍数：<input name="beiShu" type="text" class="num-ball" value="${beiShu }" maxlength="5"/>（最多10000倍）</p>
            <p>追号：<input name="addNumber" type="text" class="num-ball" value="${addNumber }" maxlength="2"/>期<input name="prizeend" type="checkbox" value="1" style="width:12px; height:12px; vertical-align:middle; margin-left:30px;" ${'1' eq prizeend ?'checked' :'' }/>中奖后停止</p>
    </div>
<p class="gx-tjiao"><input name="submitType" type="submit" value="提交投注" class="tz-chakan-btn" /></p>
   </form> 