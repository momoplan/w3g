 <%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><span>双色球</span></p>
    </div>
    <h1><a href="${pageContext.request.contextPath }/doubleBallIndex/bySelf">自选</a><span>|</span><a href="${pageContext.request.contextPath }/doubleBallIndex/byAuto">机选</a><span>|</span><a  class="tit-cur">胆拖</a></h1>
    <div class="wap-banner">
    	<p>每周二、四、日晚开奖，单注最高奖金1000万元！</p>
        <p>代购截止时间：<em class="end-time">${betEndTime }</em></p>
        <p>合买截止时间：<em class="end-time">${heMaiEndTime }</em></p>
        <p class="prompt-pro">${messageError }</p>
    </div>
    <form action="${pageContext.request.contextPath }/doubleBall/byDanTuo" method="post">
		<div class="wap-pro-goucai">
		<p style="font-weight: bold;">第${batchCode }期</p>
		</div>
		<input name="batchCode" value="${batchCode }" type="hidden"/>
		<h2 class="goucai-title"><span class="red-ball-num">红球胆码：</span>（选择1-5个胆码）</h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p">
        	<c:forEach var="item" varStatus="status" begin="1" end="33">
			<c:set var="index" value="rdball_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="rdball" id="rdball_${status.index}" value="${status.index}" ${rdballs[index] !=null ? 'checked' :''}
			/><c:choose><c:when test="${ status.index<10}">0${status.index}</c:when
			><c:otherwise>${status.index}</c:otherwise></c:choose>
			</span>
			<c:if test="${status.index%7==0 }">
				<br/>
			</c:if>
			</c:forEach>
        </p>
       
    </div>
    <h2 class="goucai-title"><span class="red-ball-num">红球拖码：</span>（选择2-20个，7≤胆+拖≤25，胆拖不重复）</h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p">
        	<c:forEach var="item" varStatus="status" begin="1" end="33">
			<c:set var="index" value="rtball_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="rtball" id="rtball_${status.index}" value="${status.index}" ${rtballs[index] !=null ? 'checked' :''}
			/><c:choose><c:when test="${ status.index<10}">0${status.index}</c:when
			><c:otherwise>${status.index}</c:otherwise></c:choose>
			</span>
			<c:if test="${status.index%7==0 }">
				<br/>
			</c:if>
			</c:forEach>
        </p>
       
    </div>
    <h2 class="goucai-title"><span class="blue-ball-num">蓝球号码：</span>（至少选择1个蓝球）</h2>
     <div class="wap-pro-goucai">
    	<p class="blueball-p">
        	<c:forEach var="item" varStatus="status" begin="1" end="16">
			<c:set var="index" value="bball_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="bball" id="bball_${status.index}" value="${status.index}" ${bballs[index]!=null ? 'checked' : ''}
			/><c:choose><c:when test="${status.index<10}">0${status.index}</c:when
			 ><c:otherwise>${status.index}</c:otherwise></c:choose>
			</span>
			<c:if test="${status.index%7==0 }">
				<br/>
			</c:if>
			</c:forEach>
        </p>
       
    </div>
    <input name="playType" type="hidden" value="${playType }"/>
    <div class="wap-pro-gray-goucai" style="border-top:1px solid #999; border-bottom:none">
     	<p>倍数：<input name="beiShu" type="text" class="num-ball" value="${beiShu }" maxlength="5"/>（最多10000倍）</p>
        <p>追号：<input name="addNumber" type="text" class="num-ball" value="${addNumber }" maxlength="2"/>期<input name="prizeend" type="checkbox" value="1" style="width:12px; height:12px; vertical-align:middle; margin-left:30px;" ${'1' eq prizeend ?'checked' :'' }/>中奖后停止</p>
    </div>
     <p class="gx-tjiao"><input type="submit" name="submitType" value="提交投注" class="tz-chakan-btn" /></p>
    </form>
    