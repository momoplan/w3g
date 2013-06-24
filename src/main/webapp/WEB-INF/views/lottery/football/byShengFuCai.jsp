 <%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.net.URLEncoder;" %>
    <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><span>足彩</span></p>
    </div>
    
     <h1><a class="tit-cur">胜负彩</a><span>|</span><a href="${pageContext.request.contextPath }/footballIndex/byFootball?lotNo=T01004">任选九场</a><span>|</span><a href="${pageContext.request.contextPath }/footballIndex/byFootball?lotNo=T01006">六场半</a><span>|</span><a href="${pageContext.request.contextPath }/footballIndex/byFootball?lotNo=T01005">四场进球</a></h1>
    
     <form action="${pageContext.request.contextPath }/footballIndex/byFootball" method="post">
     	 <p class="h1-bot">
     	投注期号：<select name="batchCode">
     				<c:forEach items="${batchCodes }" var="batch">
     					<option ${batch.batchCode eq batchCode ? 'selected':''}>${batch.batchCode }</option>
     				</c:forEach>
     			 </select>
     			      	<input name="lotNo" type="hidden" value="T01003">
     	<input name="" type="submit" value="查看" class="jx-button" />
     			 
     			 </p>
     	</form>
     	
    <div class="wap-banner">
    	<p>猜比赛，赢百万奖金！</p>
    	<p style="font-weight: bold;">当前${batchCode }期</p>
        <p>代购截止时间：<em class="end-time">${betEndTime }</em></p>
        <p>合买截止时间：<em class="end-time">${heMaiEndTime }</em></p>
        <p class="prompt-pro">${messageError }</p>
    </div>
    <h2 class="goucai-title"><span class="red-ball-num">选择赛果：</span>（至少选择1个赛果） </h2>
    <form action="${pageContext.request.contextPath }/football/byShengFuCai" method="post">
    <div class="wap-pro-goucai">
    	<c:if test="${not empty duizhens }">
    		<c:set var="zhuMa" value="${zhuMaList }"></c:set>
    		<c:forEach items="${duizhens }" var="duizhen" varStatus="duizhenIndex">
    			<p class="zucai"><em>${duizhen.tempId }.</em><span>[${duizhen.name }]</span>${duizhen.HTeam }vs${duizhen.VTeam }</p>
    			<c:set var="checkName">zhuMa${duizhenIndex.index+1 }</c:set>
    			<c:set var="selectCheck">${zhuMa[duizhenIndex.index] }</c:set>
    			<c:choose>
    				<c:when test="${duizhen.avgOdds != '||'&&duizhen.avgOdds !=''}">
    					 <p class="zucai-kind"><input name="${checkName }" type="checkbox" value="3" class="input-zucai" ${fn:indexOf(selectCheck,'3')>-1?'checked':'' }/><strong style=" color:red">3</strong>:${fn:split(duizhen.avgOdds, '|')[0]}&nbsp;<input name="${checkName }" type="checkbox" value="1" class="input-zucai" ${fn:indexOf(selectCheck,'1')>-1?'checked':'' }/><strong style=" color:red">1</strong>:${fn:split(duizhen.avgOdds, '|')[1]}&nbsp;<input name="${checkName }" type="checkbox" value="0" class="input-zucai" ${fn:indexOf(selectCheck,'0')>-1?'checked':'' }/><strong style=" color:red">0</strong>:${fn:split(duizhen.avgOdds, '|')[2]}&nbsp; <a href="${pageContext.request.contextPath }/footballIndex/selectDataAnalysisRanking?lotNo=T01003&batchCode=${batchCode}&tempId=${duizhen.tempId}" class="xi-btn">析</a></p>
    				</c:when>
    				<c:otherwise>
    					<p class="zucai-kind"><input name="${checkName }" type="checkbox" value="3" class="input-zucai" ${fn:indexOf(selectCheck,'3')>-1?'checked':'' }/><strong style=" color:red">3</strong>:胜&nbsp;<input name="${checkName }" type="checkbox" value="1" class="input-zucai" ${fn:indexOf(selectCheck,'1')>-1?'checked':'' }/><strong style=" color:red">1</strong>:平&nbsp;<input name="${checkName }" type="checkbox" value="0" class="input-zucai" ${fn:indexOf(selectCheck,'0')>-1?'checked':'' }/><strong style=" color:red">0</strong>:负&nbsp;<a href="${pageContext.request.contextPath }/footballIndex/selectDataAnalysisRanking?lotNo=T01003&batchCode=${batchCode}&tempId=${duizhen.tempId}" class="xi-btn">析</a></p>
    				</c:otherwise>
    			</c:choose>
    		</c:forEach>
    	</c:if>
    </div>
    <input name="batchCode" type="hidden" value="${batchCode }"/>
    <input name="lotNo" type="hidden" value="T01003">
     <div class="wap-pro-gray-goucai" style="border-top:none; border-bottom:none">
     	<p>倍数：<input name="beiShu" type="text" class="num-ball" maxlength="5" value="${beiShu }"/>（最多10000倍）</p>
    </div>
   
     <p class="gx-tjiao"><input type="submit" value="提交投注" class="tz-chakan-btn" /></p>
     </form>