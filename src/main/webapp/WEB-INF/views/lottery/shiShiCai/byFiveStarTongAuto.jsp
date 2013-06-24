 <%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><span>七星彩</span></p>
    </div>
    <h1><a class="tit-cur">自选</a><span>|</span><a href="${pageContext.request.contextPath }/sevenStarIndex/byAuto">机选</a></h1>
    <div class="wap-banner">
    	<p>白天 10:00至22:00　每10分钟一期，共72期　夜间22:00至次日01:55　每5分钟一期，共48期，单注最高奖金10万！</p>
        <p>截止时间：<em class="end-time">${endTime }</em></p>
        <p class="prompt-pro">${messageError }</p>
    </div>
    <form action="${pageContext.request.contextPath }/sevenStar/byLottery" method="post">
    <div class="wap-pro-goucai">
		<p style="font-weight: bold;">第${batchCode }期</p>
	</div>
	<input name="batchCode" value="${batchCode }" type="hidden"/>
    <h2 class="goucai-title"><span class="red-ball-num">一位号码：</span>（至少选择1个号码）</h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p no-red">
        	<c:forEach var="item" varStatus="status" begin="0" end="9">
			<c:set var="index" value="ball1_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="ball1" id="ball1_${status.index}" value="${status.index}" ${ball1s[index] !=null ? 'checked' :''}
			/>${status.index}
			</span>
			<c:if test="${(status.index+1)%7==0 }">
				<br/>
			</c:if>
			</c:forEach>
            
        </p>
        <p>选择<input name="ballNumber1" type="text" class="num-ball" value="${ballNumber1 }" maxlength="2"/>个<input name="submitType" type="submit" value="机选一位码" class="jx-redball" /></p>
    </div>
    <h2 class="goucai-title"><span class="red-ball-num">二位号码：</span>（至少选择1个号码）</h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p no-red">
        	<c:forEach var="item" varStatus="status" begin="0" end="9">
			<c:set var="index" value="ball2_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="ball2" id="ball2_${status.index}" value="${status.index}" ${ball2s[index] !=null ? 'checked' :''}
			/>${status.index}
			</span>
			<c:if test="${(status.index+1)%7==0 }">
				<br/>
			</c:if>
			</c:forEach>
            
        </p>
        <p>选择<input name="ballNumber2" type="text" class="num-ball" value="${ballNumber2 }" maxlength="2"/>个<input name="submitType" type="submit" value="机选二位码" class="jx-redball" /></p>
    </div>
    <h2 class="goucai-title"><span class="red-ball-num">三位号码：</span>（至少选择1个号码）</h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p no-red">
        	<c:forEach var="item" varStatus="status" begin="0" end="9">
			<c:set var="index" value="ball3_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="ball3" id="ball3_${status.index}" value="${status.index}" ${ball3s[index] !=null ? 'checked' :''}
			/>${status.index}
			</span>
			<c:if test="${(status.index+1)%7==0 }">
				<br/>
			</c:if>
			</c:forEach>
            
        </p>
        <p>选择<input name="ballNumber3" type="text" class="num-ball" value="${ballNumber3 }" maxlength="2"/>个<input name="submitType" type="submit" value="机选三位码" class="jx-redball" /></p>
    </div>
    <h2 class="goucai-title"><span class="red-ball-num">四位号码：</span>（至少选择1个号码）</h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p no-red">
        	<c:forEach var="item" varStatus="status" begin="0" end="9">
			<c:set var="index" value="ball4_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="ball4" id="ball4_${status.index}" value="${status.index}" ${ball4s[index] !=null ? 'checked' :''}
			/>${status.index}
			</span>
			<c:if test="${(status.index+1)%7==0 }">
				<br/>
			</c:if>
			</c:forEach>
            
        </p>
        <p>选择<input name="ballNumber4" type="text" class="num-ball" value="${ballNumber4 }" maxlength="2"/>个<input name="submitType" type="submit" value="机选四位码" class="jx-redball" /></p>
    </div>
    <h2 class="goucai-title"><span class="red-ball-num">五位号码：</span>（至少选择1个号码）</h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p no-red">
        	<c:forEach var="item" varStatus="status" begin="0" end="9">
			<c:set var="index" value="ball5_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="ball5" id="ball5_${status.index}" value="${status.index}" ${ball5s[index] !=null ? 'checked' :''}
			/>${status.index}
			</span>
			<c:if test="${(status.index+1)%7==0 }">
				<br/>
			</c:if>
			</c:forEach>
            
        </p>
        <p>选择<input name="ballNumber5" type="text" class="num-ball" value="${ballNumber5 }" maxlength="2"/>个<input name="submitType" type="submit" value="机选五位码" class="jx-redball" /></p>
    </div>
    <h2 class="goucai-title"><span class="red-ball-num">六位号码：</span>（至少选择1个号码）</h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p no-red">
        	<c:forEach var="item" varStatus="status" begin="0" end="9">
			<c:set var="index" value="ball6_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="ball6" id="ball6_${status.index}" value="${status.index}" ${ball6s[index] !=null ? 'checked' :''}
			/>${status.index}
			</span>
			<c:if test="${(status.index+1)+1%7==0 }">
				<br/>
			</c:if>
			</c:forEach>
            
        </p>
        <p>选择<input name="ballNumber6" type="text" class="num-ball" value="${ballNumber6 }" maxlength="2"/>个<input name="submitType" type="submit" value="机选六位码" class="jx-redball" /></p>
    </div>
    <h2 class="goucai-title"><span class="red-ball-num">七位号码：</span>（至少选择1个号码）</h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p no-red">
        	<c:forEach var="item" varStatus="status" begin="0" end="9">
			<c:set var="index" value="ball7_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="ball7" id="ball7_${status.index}" value="${status.index}" ${ball7s[index] !=null ? 'checked' :''}
			/>${status.index}
			</span>
			<c:if test="${(status.index+1)+1%7==0 }">
				<br/>
			</c:if>
			</c:forEach>
            
        </p>
        <p>选择<input name="ballNumber7" type="text" class="num-ball" value="${ballNumber7 }" maxlength="2"/>个<input name="submitType" type="submit" value="机选七位码" class="jx-redball" /></p>
    </div>
    
     <div class="wap-pro-gray-goucai" style="border-top:1px solid #999; border-bottom:none">
     		<p>倍数：<input name="beiShu" type="text" class="num-ball" value="${beiShu }" maxlength="5"/>（最多10000倍）</p>
            <p>追号：<input name="addNumber" type="text" class="num-ball" value="${addNumber }" maxlength="2"/>期<input name="prizeend" type="checkbox" value="1" style="width:12px; height:12px; vertical-align:middle; margin-left:30px;" ${'1' eq prizeend ?'checked' :'' }/>中奖后停止</p>
    </div>
     <p class="gx-tjiao"><input name="submitType" type="submit" value="提交投注" class="tz-chakan-btn" /></p>
   </form> 
    
    