<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong></strong><strong><a href="${pageContext.request.contextPath }/leaveOut_index">遗漏值</a>-</strong><span>七星彩</span></p>
    </div>
    <h1>七星彩</h1>
    <div class="wap-banner">
    <p>第${batchCode }期</p>
    <p>代购截止时间：<em class="end-time">${betEndTime }</em></p>
    </div>
    <form action="${pageContext.request.contextPath }/sevenStar/byLottery" method="post">
    <c:forEach items="${leaveOutList }" var="leaveOut" varStatus="leaveOutIndex">
    	<c:choose>
    		<c:when test="${leaveOutIndex.index==0 }">
   	<h2 class="goucai-title"><span class="red-ball-num">第一位当前期遗漏值：</span></h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p">
        <c:forEach var="item" varStatus="status" begin="0" end="9">
			<c:set var="index" value="ball1_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="ball1" id="ball1_${status.index}" value="${status.index}" ${ball1s[index] !=null ? 'checked' :''}
			/>${status.index}
			</span>
			<span class="yilou-num">[${leaveOut.leaveOutCodeList[status.index]<10?'&nbsp;':'' }${leaveOut.leaveOutCodeList[status.index] }]</span>
				<c:if test="${(status.index+1)%3==0 }">
					<br/>
				</c:if>
			</c:forEach>
            
      </p>
      
    </div>
    </c:when>
    		<c:when test="${leaveOutIndex.index==1 }">
     <h2 class="goucai-title"><span class="red-ball-num">第二位当前期遗漏值：</span></h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p">
        
        	<c:forEach var="item" varStatus="status" begin="0" end="9">
			<c:set var="index" value="ball2_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="ball2" id="ball2_${status.index}" value="${status.index}" ${ball2s[index] !=null ? 'checked' :''}
			/>${status.index}
			</span>
			<span class="yilou-num">[${leaveOut.leaveOutCodeList[status.index]<10?'&nbsp;':'' }${leaveOut.leaveOutCodeList[status.index] }]</span>
				<c:if test="${(status.index+1)%3==0 }">
					<br/>
				</c:if>
			</c:forEach>
            
      </p>
      
    </div>
    </c:when>
    		<c:when test="${leaveOutIndex.index==2 }">
    <h2 class="goucai-title"><span class="red-ball-num">第三位当前期遗漏值：</span></h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p">
        <c:forEach var="item" varStatus="status" begin="0" end="9">
			<c:set var="index" value="ball3_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="ball3" id="ball3_${status.index}" value="${status.index}" ${ball3s[index] !=null ? 'checked' :''}
			/>${status.index}
			</span>
			<span class="yilou-num">[${leaveOut.leaveOutCodeList[status.index]<10?'&nbsp;':'' }${leaveOut.leaveOutCodeList[status.index] }]</span>
				<c:if test="${(status.index+1)%3==0 }">
					<br/>
				</c:if>
			</c:forEach>
            
      </p>
      
    </div>
    </c:when>
    		<c:when test="${leaveOutIndex.index==3 }">
     <h2 class="goucai-title"><span class="red-ball-num">第四位当前期遗漏值：</span></h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p">
        <c:forEach var="item" varStatus="status" begin="0" end="9">
			<c:set var="index" value="ball4_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="ball4" id="ball4_${status.index}" value="${status.index}" ${ball4s[index] !=null ? 'checked' :''}
			/>${status.index}
			</span>
			<span class="yilou-num">[${leaveOut.leaveOutCodeList[status.index]<10?'&nbsp;':'' }${leaveOut.leaveOutCodeList[status.index] }]</span>
				<c:if test="${(status.index+1)%3==0 }">
					<br/>
				</c:if>
			</c:forEach>
            
      </p>
      
    </div>
    </c:when>
    		<c:when test="${leaveOutIndex.index==4 }">
     <h2 class="goucai-title"><span class="red-ball-num">第五位当前期遗漏值：</span></h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p">
        <c:forEach var="item" varStatus="status" begin="0" end="9">
			<c:set var="index" value="ball5_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="ball5" id="ball5_${status.index}" value="${status.index}" ${ball5s[index] !=null ? 'checked' :''}
			/>${status.index}
			</span>
			<span class="yilou-num">[${leaveOut.leaveOutCodeList[status.index]<10?'&nbsp;':'' }${leaveOut.leaveOutCodeList[status.index] }]</span>
				<c:if test="${(status.index+1)%3==0 }">
					<br/>
				</c:if>
			</c:forEach>
            
      </p>
      
    </div>
    </c:when>
    		<c:when test="${leaveOutIndex.index==5 }">
    <h2 class="goucai-title"><span class="red-ball-num">第六位当前期遗漏值：</span></h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p">
        <c:forEach var="item" varStatus="status" begin="0" end="9">
			<c:set var="index" value="ball6_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="ball6" id="ball6_${status.index}" value="${status.index}" ${ball6s[index] !=null ? 'checked' :''}
			/>${status.index}
			</span>
			<span class="yilou-num">[${leaveOut.leaveOutCodeList[status.index]<10?'&nbsp;':'' }${leaveOut.leaveOutCodeList[status.index] }]</span>
				<c:if test="${(status.index+1)%3==0 }">
					<br/>
				</c:if>
			</c:forEach>
            
      </p>
      
    </div>
    </c:when>
    		<c:when test="${leaveOutIndex.index==6 }">
     <h2 class="goucai-title"><span class="red-ball-num">第七位当前期遗漏值：</span></h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p">
        
        	<c:forEach var="item" varStatus="status" begin="0" end="9">
			<c:set var="index" value="ball7_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="ball7" id="ball7_${status.index}" value="${status.index}" ${ball7s[index] !=null ? 'checked' :''}
			/>${status.index}
			</span>
			<span class="yilou-num">[${leaveOut.leaveOutCodeList[status.index]<10?'&nbsp;':'' }${leaveOut.leaveOutCodeList[status.index] }]</span>
				<c:if test="${(status.index+1)%3==0 }">
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
            <p>追号：<input name="addNumber" type="text" class="num-ball" value="1" maxlength="2"/>期<input name="prizeend" type="checkbox" value="1" style="width:12px; height:12px; vertical-align:middle; margin-left:30px;" ${'1' eq prizeend ?'checked' :'' }/>中奖后停止</p>
    </div>
 
 <p class="gx-tjiao"><input name="submitType" type="submit" value="提交投注" class="tz-chakan-btn" /></p>
   </form> 