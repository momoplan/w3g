 <%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><span>时时彩</span></p>
    </div>
    
    <h1><a class="tit-cur">一星</a><span>|</span><a href="${pageContext.request.contextPath }/shiShiCaiIndex/byTwoStarDirect">二星</a><span>|</span><a href="${pageContext.request.contextPath }/shiShiCaiIndex/byThreeStarDirect">三星</a><span>|</span><a href="${pageContext.request.contextPath }/shiShiCaiIndex/byFiveStarDirect">五星</a><span>|</span><a href="${pageContext.request.contextPath }/shiShiCaiIndex/byDaXiaoDanShuangSelf">大小单双</a></h1>
    <h3><a class="tittwo-cur">直选</a><span>|</span><a href="${pageContext.request.contextPath }/shiShiCaiIndex/byOneStarAuto">机选</a></h3>
    <div class="wap-banner">
    	<p>白天 10:00至22:00　每10分钟一期，共72期　夜间22:00至次日01:55　每5分钟一期，共48期，单注最高奖金10万！</p>
        <p>剩余时间：<em class="end-time">${remainingTime }</em></p>
        <p class="prompt-pro">${messageError }</p>
    </div>
    <form action="${pageContext.request.contextPath }/shiShiCai/byOneStar" method="post">
    <div class="wap-pro-goucai">
		<p style="font-weight: bold;">第${batchCode }期</p>
	</div>
	<input name="batchCode" value="${batchCode }" type="hidden"/>
    <h2 class="goucai-title"><span class="red-ball-num">个位号码：</span>（选择1-5个号码）</h2>
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
        <p>选择<input name="ballNumber" type="text" class="num-ball" value="${ballNumber }" maxlength="2"/>个<input name="submitType" type="submit" value="机选号码" class="jx-redball" /></p>
    </div>
   
     <div class="wap-pro-gray-goucai" style="border-top:1px solid #999; border-bottom:none">
     		<p>倍数：<input name="beiShu" type="text" class="num-ball" value="${beiShu }" maxlength="5"/>（最多10000倍）</p>
            <p>追号：<input name="addNumber" type="text" class="num-ball" value="${addNumber }" maxlength="2"/>期<input name="prizeend" type="checkbox" value="1" style="width:12px; height:12px; vertical-align:middle; margin-left:30px;" ${'1' eq prizeend ?'checked' :'' }/>中奖后停止</p>
    </div>
    <p class="moneey-dlt">与开奖号的最后一位（即个位）相同中奖，单注奖金<span>10</span>元</p>
	<p class="gx-tjiao"><input name="submitType" type="submit" value="提交投注" class="tz-chakan-btn" /></p>
   </form>    
   