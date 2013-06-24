<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
      <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/leaveOut_index">遗漏值</a>-</strong><span>22选五</span></p>
    </div>
    <h1>22选5</h1>
    <div class="wap-banner">
    <p>第${batchCode }期</p>
    <p>代购截止时间：<em class="end-time">${betEndTime }</em></p>
    </div>
    <form action="${pageContext.request.contextPath }/select5From22/byLottery" method="post">
   	<c:set var="leaveOut" value="${leaveOutList[0] }"></c:set>
   	<h2 class="goucai-title"><span class="red-ball-num">当前期遗漏值：</span></h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p">
        	<c:forEach var="item" varStatus="status" begin="1" end="22">
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
     <input name="batchCode" value="${batchCode }" type="hidden"/>
    <div class="wap-pro-gray-goucai" style="border-top:1px solid #999; border-bottom:none">
     		<p>倍数：<input name="beiShu" type="text" class="num-ball" value="1" maxlength="5"/>（最多10000倍）</p>
            <p>追号：<input name="addNumber" type="text" class="num-ball" value="1" maxlength="2"/>期<input name="prizeend" type="checkbox" value="1" style="width:12px; height:12px; vertical-align:middle; margin-left:30px;" ${'1' eq prizeend ?'checked' :'' }/>中奖后停止</p>
    </div>
 
     <p class="gx-tjiao"><input name="submitType" type="submit" value="提交投注" class="tz-chakan-btn" /></p>
     </form>