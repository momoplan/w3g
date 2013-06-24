<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong></strong><strong><a href="${pageContext.request.contextPath }/leaveOut_index">遗漏值</a>-</strong><span>双色球</span></p>
    </div>
    <h1>双色球</h1>
    <div class="wap-banner">
    <p>第${batchCode }期</p>
    <p>代购截止时间：<em class="end-time">${betEndTime }</em></p>
    </div>
   <form action="${pageContext.request.contextPath }/doubleBall/byLottery" method="post">
    <c:forEach items="${leaveOutList }" var="leaveOut" varStatus="leaveOutIndex">
    	<c:choose>
    		<c:when test="${leaveOutIndex.index==0 }">
    			<h2 class="goucai-title"><span class="red-ball-num">红球区遗漏值：</span></h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p">
        	<c:forEach var="item" varStatus="status" begin="1" end="33">
				<c:set var="index" value="rball_${status.index }"></c:set>
				<span>
					<input type="checkbox" name="rball" id="rball_${status.index}" value="${status.index}" ${rballs[index] !=null ? 'checked' :''}
					/>${ status.index<10 ? '0':''}${status.index}
				</span>
				<span class="yilou-num">[${leaveOut.leaveOutCodeList[status.index-1]<10?'&nbsp;':'' }${leaveOut.leaveOutCodeList[status.index-1] }]</span>
				<c:if test="${status.index%3==0 }">
					<br/>
				</c:if>
			</c:forEach>
        	
      </p>
      
    </div>
    		</c:when>
    		<c:when test="${leaveOutIndex.index==1 }">
    			<h2 class="goucai-title"><span class="blue-ball-num">蓝球区遗漏值：</span></h2>
    <div class="wap-pro-goucai">
    	<p class="blueball-p">
        	<c:forEach var="item" varStatus="status" begin="1" end="16">
		<c:set var="index" value="bball_${status.index }"></c:set>
		<span>
		<input type="checkbox" name="bball" id="bball_${status.index}" value="${status.index}" ${bballs[index]!=null ? 'checked' : ''}
			/>${ status.index<10 ? '0':''}${status.index}
		</span>
		<span class="yilou-num">[${leaveOut.leaveOutCodeList[status.index-1]<10?'&nbsp;':'' }${leaveOut.leaveOutCodeList[status.index-1] }]</span>
		<c:if test="${status.index%3==0 }">
			<br/>
		</c:if>
			</c:forEach>
      </p>
      
    </div>
    		</c:when>
    	</c:choose>
    
    </c:forEach>
    <input name="batchCode" value="${batchCode }" type="hidden"/>
     <div class="wap-pro-gray-goucai" style="border-top:1px solid #999; border-bottom:none">
     	<p>倍数：<input name="beiShu" type="text" class="num-ball" value="1" maxlength="5"/>（最多10000倍）</p>
        <p>追号：<input name="addNumber" type="text" class="num-ball" value="1" maxlength="2"/>期<input name="prizeend" type="checkbox" value="1" style="width:12px; height:12px; vertical-align:middle; margin-left:30px;" />中奖后停止</p>
    </div>
 
     <p class="gx-tjiao"><input name="submitType" type="submit" value="提交投注" class="tz-chakan-btn" /></p>
  </form> 	