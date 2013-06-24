 <%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
    <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><span>福彩3D</span></p>
    </div>
    <h1><a href="${pageContext.request.contextPath }/fuCai3DIndex/byDirect">直选</a><span>|</span><a href="${pageContext.request.contextPath }/fuCai3DIndex/byAuto">机选</a><span>|</span><a href="${pageContext.request.contextPath }/fuCai3DIndex/byGroup3Self">组三</a><span>|</span><a href="${pageContext.request.contextPath }/fuCai3DIndex/byGroup6Self">组六</a><span>|</span><a href="${pageContext.request.contextPath }/fuCai3DIndex/byDirectHeZhi">和值</a><span>|</span><a class="tit-cur">胆拖</a></h1>
    <div class="wap-banner">
    	<p>每晚开奖，玩法简单，2元赢取1000元！</p>
        <p>代购截止时间：<em class="end-time">${betEndTime }</em></p>
        <p>合买截止时间：<em class="end-time">${heMaiEndTime }</em></p>
        <p class="prompt-pro">${messageError }</p>
    </div>
    <form action="${pageContext.request.contextPath }/fuCai3D/byDanTuo" method="post">
    <div class="wap-pro-goucai">
		<p style="font-weight: bold;">第${batchCode }期</p>
	</div>
	<input name="batchCode" value="${batchCode }" type="hidden"/>
    <h2 class="goucai-title"><span class="red-ball-num">选择胆码：</span>（选择1-2个胆码）</h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p no-red">
        	<c:forEach var="item" varStatus="status" begin="0" end="9">
			<c:set var="index" value="dball_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="dball" id="dball_${status.index}" value="${status.index}" ${dballs[index] !=null ? 'checked' :''}
			/>${status.index}
			</span>
			<c:if test="${(status.index+1)%7==0 }">
				<br/>
			</c:if>
			</c:forEach>
        </p>
    </div>
    <h2 class="goucai-title"><span class="red-ball-num">选择拖码：</span>（选择1-9个，胆+拖≥4，胆拖不重复）</h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p no-red">
        	<c:forEach var="item" varStatus="status" begin="0" end="9">
			<c:set var="index" value="tball_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="tball" id="tball_${status.index}" value="${status.index}" ${tballs[index] !=null ? 'checked' :''}
			/>${status.index}
			</span>
			<c:if test="${(status.index+1)%7==0 }">
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
     <p class="gx-tjiao"><input type="submit" value="提交投注" class="tz-chakan-btn" /></p>
 	</form>