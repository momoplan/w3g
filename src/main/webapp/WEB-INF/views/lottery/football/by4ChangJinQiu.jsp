 <%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><span>足彩</span></p>
    </div>
    
     <h1><a href="${pageContext.request.contextPath }/footballIndex/byFootball?lotNo=T01003">胜负彩</a><span>|</span><a href="${pageContext.request.contextPath }/footballIndex/byFootball?lotNo=T01004">任选九场</a><span>|</span><a href="${pageContext.request.contextPath }/footballIndex/byFootball?lotNo=T01006">六场半</a><span>|</span><a class="tit-cur">四场进球</a></h1>
    <form action="${pageContext.request.contextPath }/footballIndex/byFootball" method="post">
     	 <p class="h1-bot">
     	投注期号：<select name="batchCode">
     				<c:forEach items="${batchCodes }" var="batch">
     					<option ${batch.batchCode eq batchCode ? 'selected':''}>${batch.batchCode }</option>
     				</c:forEach>
     			 </select>
     			      	<input name="lotNo" type="hidden" value="T01005">
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
   <form action="${pageContext.request.contextPath }/football/by4ChangJinQiu" method="post">
    
    <div class="wap-pro-goucai">
    	<c:if test="${not empty duizhens }">
    		<c:set var="zhuMaZ" value="${zhuMaZList }"></c:set>
    		<c:set var="zhuMaK" value="${zhuMaKList }"></c:set>
    		<c:forEach items="${duizhens }" var="duizhen" varStatus="duizhenIndex">
    			<p class="zucai"><em>${duizhen.tempId }.</em><span>[${duizhen.name }]</span>${duizhen.HTeam }vs${duizhen.VTeam }</p>
    			<c:set var="checkNameZ">zhuMaZ${duizhenIndex.index+1 }</c:set>
    			<c:set var="checkNameK">zhuMaK${duizhenIndex.index+1 }</c:set>
    			<c:set var="selectCheckZ">${zhuMaZ[duizhenIndex.index] }</c:set>
    			<c:set var="selectCheckK">${zhuMaK[duizhenIndex.index] }</c:set>
    			<p class="zucai-kind">均值：${duizhen.avgOdds } <a href="${pageContext.request.contextPath }/footballIndex/selectDataAnalysisRanking?lotNo=T01005&batchCode=${batchCode}&tempId=${duizhen.tempId}" class="xi-btn">析</a><br />
    			<em>主：</em><input name="${checkNameZ }" type="checkbox" value="0" class="input-zucai" ${fn:indexOf(selectCheckZ,'0')>-1?'checked':'' }/>0&nbsp;<input name="${checkNameZ }" type="checkbox" value="1" class="input-zucai" ${fn:indexOf(selectCheckZ,'1')>-1?'checked':'' }/>1&nbsp;<input name="${checkNameZ }" type="checkbox" value="2" class="input-zucai" ${fn:indexOf(selectCheckZ,'2')>-1?'checked':'' }/>2&nbsp;<input name="${checkNameZ }" type="checkbox" value="3" class="input-zucai" ${fn:indexOf(selectCheckZ,'3')>-1?'checked':'' }/>3+<br />
    			<em>客：</em><input name="${checkNameK }" type="checkbox" value="0" class="input-zucai" ${fn:indexOf(selectCheckK,'0')>-1?'checked':'' }/>0&nbsp;<input name="${checkNameK }" type="checkbox" value="1" class="input-zucai" ${fn:indexOf(selectCheckK,'1')>-1?'checked':'' }/>1&nbsp;<input name="${checkNameK }" type="checkbox" value="2" class="input-zucai" ${fn:indexOf(selectCheckK,'2')>-1?'checked':'' }/>2&nbsp;<input name="${checkNameK }" type="checkbox" value="3" class="input-zucai" ${fn:indexOf(selectCheckK,'3')>-1?'checked':'' }/>3+</p>
    		</c:forEach>
    	</c:if>
      
    </div>
    
    
<input name="batchCode" type="hidden" value="${batchCode }"/>
    <input name="lotNo" type="hidden" value="T01005">
     <div class="wap-pro-gray-goucai" style="border-top:none; border-bottom:none">
     	<p>倍数：<input name="beiShu" type="text" class="num-ball" maxlength="5" value="${beiShu }"/>（最多10000倍）</p>
    </div>
   
     <p class="gx-tjiao"><input type="submit" value="提交投注" class="tz-chakan-btn" /></p>
     </form> 