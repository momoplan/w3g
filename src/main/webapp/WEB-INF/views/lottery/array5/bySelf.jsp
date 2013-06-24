 <%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><span>排列五</span></p>
    </div>
    <h1><a class="tit-cur">自选</a><span>|</span><a href="${pageContext.request.contextPath }/array5Index/byAuto">机选</a></h1>
    <div class="wap-banner">
    	<p>天天开奖，固定奖金10万元！</p>
        <p>代购截止时间：<em class="end-time">${betEndTime }</em></p>
        <p>合买截止时间：<em class="end-time">${heMaiEndTime }</em></p>
        <p class="prompt-pro">${messageError }</p>
    </div>
    <form action="${pageContext.request.contextPath }/array5/byLottery" method="post">
    <div class="wap-pro-goucai">
		<p style="font-weight: bold;">第${batchCode }期</p>
	</div>
	<input name="batchCode" value="${batchCode }" type="hidden"/>
    <h2 class="goucai-title"><span class="red-ball-num">万位号码：</span>（至少选择1个号码）</h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p no-red">
        	<c:forEach var="item" varStatus="status" begin="0" end="9">
			<c:set var="index" value="wball_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="wball" id="wball_${status.index}" value="${status.index}" ${wballs[index] !=null ? 'checked' :''}
			/>${status.index}
			</span>
			<c:if test="${(status.index+1)%7==0 }">
				<br/>
			</c:if>
			</c:forEach>
            
        </p>
        <p>选择<input name="wballNumber" type="text" class="num-ball" value="${wballNumber }" maxlength="2"/>个<input name="submitType" type="submit" value="机选万位" class="jx-redball" /></p>
    </div>
    <h2 class="goucai-title"><span class="red-ball-num">千位号码：</span>（至少选择1个号码）</h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p no-red">
        	<c:forEach var="item" varStatus="status" begin="0" end="9">
			<c:set var="index" value="qball_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="qball" id="qball_${status.index}" value="${status.index}" ${qballs[index] !=null ? 'checked' :''}
			/>${status.index}
			</span>
			<c:if test="${(status.index+1)%7==0 }">
				<br/>
			</c:if>
			</c:forEach>
            
        </p>
        <p>选择<input name="qballNumber" type="text" class="num-ball" value="${qballNumber }" maxlength="2"/>个<input name="submitType" type="submit" value="机选千位" class="jx-redball" /></p>
    </div>
    <h2 class="goucai-title"><span class="red-ball-num">百位号码：</span>（至少选择1个号码）</h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p no-red">
        	<c:forEach var="item" varStatus="status" begin="0" end="9">
			<c:set var="index" value="bball_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="bball" id="bball_${status.index}" value="${status.index}" ${bballs[index] !=null ? 'checked' :''}
			/>${status.index}
			</span>
			<c:if test="${(status.index+1)%7==0 }">
				<br/>
			</c:if>
			</c:forEach>
            
        </p>
        <p>选择<input name="bballNumber" type="text" class="num-ball" value="${bballNumber }" maxlength="2"/>个<input name="submitType" type="submit" value="机选百位" class="jx-redball" /></p>
    </div>
    <h2 class="goucai-title"><span class="red-ball-num">十位号码：</span>（至少选择1个号码）</h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p no-red">
        	<c:forEach var="item" varStatus="status" begin="0" end="9">
			<c:set var="index" value="sball_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="sball" id="sball_${status.index}" value="${status.index}" ${sballs[index] !=null ? 'checked' :''}
			/>${status.index}
			</span>
			<c:if test="${(status.index+1)%7==0 }">
				<br/>
			</c:if>
			</c:forEach>
            
        </p>
        <p>选择<input name="sballNumber" type="text" class="num-ball" value="${sballNumber }" maxlength="2"/>个<input name="submitType" type="submit" value="机选十位" class="jx-redball" /></p>
    </div>
    <h2 class="goucai-title"><span class="red-ball-num">个位号码：</span>（至少选择1个号码）</h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p no-red">
        	<c:forEach var="item" varStatus="status" begin="0" end="9">
			<c:set var="index" value="gball_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="gball" id="gball_${status.index}" value="${status.index}" ${gballs[index] !=null ? 'checked' :''}
			/>${status.index}
			</span>
			<c:if test="${(status.index+1)%7==0 }">
				<br/>
			</c:if>
			</c:forEach>
            
        </p>
        <p>选择<input name="gballNumber" type="text" class="num-ball" value="${gballNumber }" maxlength="2"/>个<input name="submitType" type="submit" value="机选个位" class="jx-redball" /></p>
    </div>
    <input name="playType" type="hidden" value="${playType }"/>
     <div class="wap-pro-gray-goucai" style="border-top:1px solid #999; border-bottom:none">
     		<p>倍数：<input name="beiShu" type="text" class="num-ball" value="${beiShu }" maxlength="5"/>（最多10000倍）</p>
            <p>追号：<input name="addNumber" type="text" class="num-ball" value="${addNumber }" maxlength="2"/>期<input name="prizeend" type="checkbox" value="1" style="width:12px; height:12px; vertical-align:middle; margin-left:30px;" ${'1' eq prizeend ?'checked' :'' }/>中奖后停止</p>
    </div>
   
     <p class="gx-tjiao"><input name="submitType" type="submit" value="提交投注" class="tz-chakan-btn" /></p>
     </form>