<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/leaveOut_index">遗漏值</a>-</strong><span>时时彩</span></p>
    </div>
    <h1><a>时时彩</a></h1>
   	<h5>五星：<a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01007&key=T01007MV_5X&type=WXZhX">五星直选</a><span>|</span><a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01007&key=T01007MV_5X&type=WXTX">五星通选</a></h5>
    <h5>三星：<a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01007&key=T01007MV_5X&type=SXZhX">三星直选</a></h5>
    <h5>二星：<a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01007&key=T01007MV_5X&type=EXZhX">二星直选</a><span>|</span><a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01007&key=T01007MV_2ZXHZ&type=EXHZh">二星和值</a><span>|</span><a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01007&key=T01007MV_2ZX&type=EXZX"  class="tit-cur-h5">二星组选</a></h5>
    <h5>其它：<a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01007&key=T01007MV_DD&type=DXDS">大小单双</a><span>|</span><a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01007&key=T01007MV_5X&type=YX">一星</a></h5>
   	<div class="xuxian-line"></div>
    <div class="wap-banner">
    <p>第${batchCode }期</p>
    <p>剩余时间：<em class="end-time">${remainingTime }</em></p>
    </div>
   
        <form action="${pageContext.request.contextPath }/shiShiCai/byTwoStarToGroup" method="post">
       	<c:set var="leaveOut" value="${leaveOutList[0] }"></c:set>
    
    <input name="batchCode" value="${batchCode }" type="hidden"/>
    <h2 class="goucai-title"><span class="red-ball-num">二星组选当前期遗漏值：</span></h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p">
        
        	<c:forEach var="item" varStatus="status" begin="0" end="9">
			<c:set var="index" value="ball_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="ball" id="ball_${status.index}" value="${status.index}" ${balls[index] !=null ? 'checked' :''}
			/>${status.index}
			</span>
			<span class="yilou-num">[${leaveOut.leaveOutCodeList[status.index]<10?'&nbsp;':'' }${leaveOut.leaveOutCodeList[status.index] }]</span>
			
			<c:if test="${(status.index+1)%3==0 }">
				<br/>
			</c:if>
			</c:forEach> 
            
      </p>
      
    </div>
 <div class="wap-pro-gray-goucai" style="border-top:1px solid #999; border-bottom:none">
     		<p>倍数：<input name="beiShu" type="text" class="num-ball" value="1" maxlength="5"/>（最多10000倍）</p>
            <p>追号：<input name="addNumber" type="text" class="num-ball" value="1" maxlength="2"/>期<input name="prizeend" type="checkbox" value="1" style="width:12px; height:12px; vertical-align:middle; margin-left:30px;" ${'1' eq prizeend ?'checked' :'' }/>中奖后停止</p>
    </div>
<p class="gx-tjiao"><input name="submitType" type="submit" value="提交投注" class="tz-chakan-btn" /></p>
   </form> 