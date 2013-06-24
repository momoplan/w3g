 <%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
     <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><span>大乐透</span></p>
    </div>
    <h1><a href="${pageContext.request.contextPath }/daLeTouIndex/bySelf">自选</a><span>|</span><a href="${pageContext.request.contextPath }/daLeTouIndex/byAuto">机选</a><span>|</span><a href="${pageContext.request.contextPath }/daLeTouIndex/byDanTuo">胆拖</a><span>|</span><a class="tit-cur">12选2</a></h1>
    <div class="wap-banner">
    	<p>每周一、三、六晚开奖，3元可赢取1600万元！</p>
        <p>代购截止时间：<em class="end-time">${betEndTime }</em></p>
        <p>合买截止时间：<em class="end-time">${heMaiEndTime }</em></p>
        <p class="prompt-pro">${messageError }</p>
    </div>
      <form action="${pageContext.request.contextPath }/daLeTou/byShengXiaoLeLottery" method="post">
    <div class="wap-pro-goucai">
		<p style="font-weight: bold;">第${batchCode }期</p>
	</div>
	<input name="batchCode" value="${batchCode }" type="hidden"/>
    
    <h2 class="goucai-title"><span class="red-ball-num">选择号码：</span>（至少选择2个号码）</h2>
     <div class="wap-pro-goucai">
    	<p class="redball-p no-red">
        	<c:forEach var="item" varStatus="status" begin="1" end="12">
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
      	 <p>选择<input name="ballNumber" type="text" class="num-ball" value="${rballNumber }"/>个<input name="submitType" type="submit" value="机选号码" class="jx-blueball" /></p>
    </div>
     <input name="playType" type="hidden" value="${playType }"/>
     <div class="wap-pro-gray-goucai" style="border-top:1px solid #999; border-bottom:none">
     		<p>倍数：<input name="beiShu" type="text" class="num-ball" value="${beiShu }" maxlength="5"/>（最多10000倍）</p>
            <p>追号：<input name="addNumber" type="text" class="num-ball" value="${addNumber }" maxlength="2"/>期<input name="prizeend" type="checkbox" value="1" style="width:12px; height:12px; vertical-align:middle; margin-left:30px;" ${'1' eq prizeend ?'checked' :'' }/>中奖后停止</p>
    </div>
    <p class="moneey-dlt">选择两个以上号码，中奖可获得<span>60</span>元。</p>
     <p class="gx-tjiao"><input name="submitType" type="submit" value="提交投注" class="tz-chakan-btn" /></p>
	</form>