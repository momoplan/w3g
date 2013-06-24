 <%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><span>竞彩足球</span></p>
    </div>
    
     <h1><a href="${pageContext.request.contextPath }/jcFootballIndex/byJcFootBall?type=1&valueType=1&wanFa=FT001" >胜平负</a><span>|</span><a href="${pageContext.request.contextPath }/jcFootballIndex/byJcFootBall?type=1&valueType=1&wanFa=J00013" class="tit-cur">让球胜平负</a><span>|</span><a href="${pageContext.request.contextPath }/jcFootballIndex/byJcFootBall?type=1&valueType=1&wanFa=FT002">比分</a><span>|</span><a href="${pageContext.request.contextPath }/jcFootballIndex/byJcFootBall?type=1&valueType=1&wanFa=FT003">总进球</a><span>|</span><a href="${pageContext.request.contextPath }/jcFootballIndex/byJcFootBall?type=1&valueType=1&wanFa=FT004">半全场</a></h1>
     <c:choose>
     	<c:when test="${'1' eq valueType }">
     		<h3><a href="${pageContext.request.contextPath }/jcFootballIndex/byJcFootBall?type=1&valueType=0&wanFa=J00013">单关</a><span>|</span><a class="tittwo-cur">过关</a></h3>
     		<div class="wap-banner">
    	<p>猜比赛，赢百万奖金！</p>
        <p>销售时间：<em class="end-time">周一/二/五 09:00～22:45 周三/四 07:30～22:45 周六/日 09:00～00:45
	</em></p>
	<p class="prompt-pro">${messageError }</p>
	 </div>
	 <form action="${pageContext.request.contextPath }/jcFootballIndex/byJcFootballToSelect" method="post">
	 <input name="type" type="hidden" value="${type }"/>
	 <input name="valueType" type="hidden" value="${valueType }"/>
	 <input name="wanFa" type="hidden" value="${wanFa }"/>
    <c:if test="${not empty jcMatchAgainstsMaps }">
    	<c:forEach items="${jcMatchAgainstsMaps }" var="jcMatchAgainstsMap">
    	<c:choose>
    		<c:when test="${fn:indexOf(hiddenStr,jcMatchAgainstsMap.dayTop)>-1 }">
    			<h2 class="goucai-title">${jcMatchAgainstsMap.weekTop }&nbsp;&nbsp; ${jcMatchAgainstsMap.totalMatch }&nbsp;&nbsp;<input name="submitType" type="submit" value="${jcMatchAgainstsMap.dayTop }|隐藏" class="jx-button-two"/></h2>
    			<input name="hiddenType" type="hidden" value="${jcMatchAgainstsMap.dayTop }"/>
    		</c:when>    		
    		<c:otherwise>
    			<h2 class="goucai-title">${jcMatchAgainstsMap.weekTop }&nbsp;&nbsp; ${jcMatchAgainstsMap.totalMatch }&nbsp;&nbsp;<input name="submitType" type="submit" value="${jcMatchAgainstsMap.dayTop }|赛事" class="jx-button-two" /></h2>
    			<input name="hiddenType" type="hidden" value=""/>
    			
    		</c:otherwise>
    	</c:choose>
    	<c:forEach items="${jcMatchAgainstsMap.jcMatchAgainsts }" var="jcMatchAgainst">
    		<div style="display: ${fn:indexOf(hiddenStr,jcMatchAgainstsMap.dayTop)>-1 ? 'block':'none'}" class="wap-pro-goucai">
    		<p class="zucai"><em>${jcMatchAgainst.teamId }.</em>&nbsp;<span>${jcMatchAgainst.league }</span>&nbsp;&nbsp;${jcMatchAgainst.endTimeView }&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/jcFootballIndex/selectDataAnalysisRanking?day=${jcMatchAgainst.day }&weekId=${jcMatchAgainst.weekId}&teamId=${jcMatchAgainst.teamId}" class="xi-btn">析</a></p>
        	<c:set var="checkName3">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|3</c:set>
        	<c:set var="checkName1">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|1</c:set>
        	<c:set var="checkName0">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|0</c:set>
        	<c:choose>
        		<c:when test="${not empty jcMatchAgainst.peiLv }">
        			<table width="260" border="1" cellspacing="0" cellpadding="0" class="jifen-table playball-table">
  				<tr>
    			<td width="110">${jcMatchAgainst.homeTeam }(主)</td>
    			<td><span>${jcMatchAgainst.peiLv.letVs.letPoint}</span></td>
    			<td width="110">${jcMatchAgainst.guestTeam }</td>
  			</tr>
			</table>
			<p class="zucai-kind">
			<input name="zhuMa" type="checkbox" value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|3|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|${jcMatchAgainst.peiLv.letVs.letPoint}|${jcMatchAgainst.guestTeam}|胜|(${jcMatchAgainst.peiLv.letVs.v3})" class="input-zucai" ${fn:indexOf(zhuMaStr,checkName3)>-1?'checked':'' }/>胜(${jcMatchAgainst.peiLv.letVs.v3})&nbsp;&nbsp;
			
			<input name="zhuMa" type="checkbox" value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|1|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|${jcMatchAgainst.peiLv.letVs.letPoint}|${jcMatchAgainst.guestTeam}|平|(${jcMatchAgainst.peiLv.letVs.v1})" class="input-zucai" ${fn:indexOf(zhuMaStr,checkName1)>-1?'checked':'' }/>平(${jcMatchAgainst.peiLv.letVs.v1})&nbsp;&nbsp;
			<input name="zhuMa" type="checkbox" value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|0|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|${jcMatchAgainst.peiLv.letVs.letPoint}|${jcMatchAgainst.guestTeam}|负|(${jcMatchAgainst.peiLv.letVs.v0})" class="input-zucai" ${fn:indexOf(zhuMaStr,checkName0)>-1?'checked':'' }/>负(${jcMatchAgainst.peiLv.letVs.v0})</p>
        		</c:when>
        		<c:otherwise>
        		<table width="260" border="1" cellspacing="0" cellpadding="0" class="jifen-table playball-table">
  				<tr>
    			<td width="110">${jcMatchAgainst.homeTeam }(主)</td>
    			<td><span></span></td>
    			<td width="110">${jcMatchAgainst.guestTeam }</td>
  			</tr>
			</table>
			<p class="zucai-kind">
			<input name="zhuMa" type="checkbox" value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|3|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }| |${jcMatchAgainst.guestTeam}|胜| " class="input-zucai" ${fn:indexOf(zhuMaStr,checkName3)>-1?'checked':'' }/>胜&nbsp;&nbsp;
			<input name="zhuMa" type="checkbox" value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|1|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }| |${jcMatchAgainst.guestTeam}|平| " class="input-zucai" ${fn:indexOf(zhuMaStr,checkName1)>-1?'checked':'' }/>平&nbsp;&nbsp;
			<input name="zhuMa" type="checkbox" value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|0|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }| |${jcMatchAgainst.guestTeam}|负| " class="input-zucai" ${fn:indexOf(zhuMaStr,checkName0)>-1?'checked':'' }/>负</p>
        			
        		</c:otherwise>
        	</c:choose>
       		</div>
		</c:forEach>
	</c:forEach>
    </c:if>
 
     <p class="gx-tjiao" style="padding-top:10px;"><strong>选择过关方式：</strong><br /><input type="submit" name="submitType" value="自由过关" class="tz-chakan-btn" /><input type="submit" name="submitType" value="多串过关" class="tz-chakan-btn" style="margin-left:10px;" /></p>
   	</form>
     	</c:when>
     	<c:otherwise>
     	    <h3><a class="tittwo-cur">单关</a><span>|</span><a href="${pageContext.request.contextPath }/jcFootballIndex/byJcFootBall?type=1&valueType=1&wanFa=J00013">过关</a></h3>
     		<div class="wap-banner">
    	<p>猜比赛，赢百万奖金！</p>
        <p>销售时间：<em class="end-time">周一/二/五 09:00～22:45 周三/四 07:30～22:45 周六/日 09:00～00:45
	</em></p>
	<p class="prompt-pro">${messageError }</p>
	 </div>
	 <form action="${pageContext.request.contextPath }/jcFootballIndex/byJcFootballToSelect" method="post">
	 <input name="type" type="hidden" value="${type }"/>
	 <input name="valueType" type="hidden" value="${valueType }"/>
	 <input name="wanFa" type="hidden" value="${wanFa }"/>
    <c:if test="${not empty jcMatchAgainstsMaps }">
    	<c:forEach items="${jcMatchAgainstsMaps }" var="jcMatchAgainstsMap">
    	<c:choose>
    		<c:when test="${fn:indexOf(hiddenStr,jcMatchAgainstsMap.dayTop)>-1 }">
    			<h2 class="goucai-title">${jcMatchAgainstsMap.weekTop }&nbsp;&nbsp; ${jcMatchAgainstsMap.totalMatch }&nbsp;&nbsp;<input name="submitType" type="submit" value="${jcMatchAgainstsMap.dayTop }|隐藏" class="jx-button-two"/></h2>
    			<input name="hiddenType" type="hidden" value="${jcMatchAgainstsMap.dayTop }"/>
    		</c:when>    		
    		<c:otherwise>
    			<h2 class="goucai-title">${jcMatchAgainstsMap.weekTop }&nbsp;&nbsp; ${jcMatchAgainstsMap.totalMatch }&nbsp;&nbsp;<input name="submitType" type="submit" value="${jcMatchAgainstsMap.dayTop }|赛事" class="jx-button-two" /></h2>
    			<input name="hiddenType" type="hidden" value=""/>
    			
    		</c:otherwise>
    	</c:choose>
    	<c:forEach items="${jcMatchAgainstsMap.jcMatchAgainsts }" var="jcMatchAgainst">
    		<div  style="display: ${fn:indexOf(hiddenStr,jcMatchAgainstsMap.dayTop)>-1 ? 'block':'none'}" class="wap-pro-goucai">
    		<p class="zucai"><em>${jcMatchAgainst.teamId }.</em>&nbsp;<span>${jcMatchAgainst.league }</span>&nbsp;&nbsp;${jcMatchAgainst.endTimeView }&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/jcFootballIndex/selectDataAnalysisRanking?day=${jcMatchAgainst.day }&weekId=${jcMatchAgainst.weekId}&teamId=${jcMatchAgainst.teamId}" class="xi-btn">析</a></p>
        	<c:set var="checkName3">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|3</c:set>
        	<c:set var="checkName1">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|1</c:set>
        	<c:set var="checkName0">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|0</c:set>
        	<c:choose>
        		<c:when test="${not empty jcMatchAgainst.peiLv }">
        			<table width="260" border="1" cellspacing="0" cellpadding="0" class="jifen-table playball-table">
  				<tr>
    			<td width="110">${jcMatchAgainst.homeTeam }(主)</td>
    			<td><span>${jcMatchAgainst.peiLv.letVs.letPoint}</span></td>
    			<td width="110">${jcMatchAgainst.guestTeam }</td>
  			</tr>
			</table>
			<p class="zucai-kind">
			<input name="zhuMa" type="checkbox" value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|3|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|${jcMatchAgainst.peiLv.letVs.letPoint}|${jcMatchAgainst.guestTeam}|胜|(${jcMatchAgainst.peiLv.letVs.v3})" class="input-zucai" ${fn:indexOf(zhuMaStr,checkName3)>-1?'checked':'' }/>胜(${jcMatchAgainst.peiLv.letVs.v3})&nbsp;&nbsp;
			
			<input name="zhuMa" type="checkbox" value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|1|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|${jcMatchAgainst.peiLv.letVs.letPoint}|${jcMatchAgainst.guestTeam}|平|(${jcMatchAgainst.peiLv.letVs.v1})" class="input-zucai" ${fn:indexOf(zhuMaStr,checkName1)>-1?'checked':'' }/>平(${jcMatchAgainst.peiLv.letVs.v1})&nbsp;&nbsp;
			<input name="zhuMa" type="checkbox" value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|0|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|${jcMatchAgainst.peiLv.letVs.letPoint}|${jcMatchAgainst.guestTeam}|负|(${jcMatchAgainst.peiLv.letVs.v0})" class="input-zucai" ${fn:indexOf(zhuMaStr,checkName0)>-1?'checked':'' }/>负(${jcMatchAgainst.peiLv.letVs.v0})</p>
        		</c:when>
        		<c:otherwise>
        		<table width="260" border="1" cellspacing="0" cellpadding="0" class="jifen-table playball-table">
  				<tr>
    			<td width="110">${jcMatchAgainst.homeTeam }(主)</td>
    			<td><span></span></td>
    			<td width="110">${jcMatchAgainst.guestTeam }</td>
  			</tr>
			</table>
			<p class="zucai-kind">
			<input name="zhuMa" type="checkbox" value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|3|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }| |${jcMatchAgainst.guestTeam}|胜| " class="input-zucai" ${fn:indexOf(zhuMaStr,checkName3)>-1?'checked':'' }/>胜&nbsp;&nbsp;
			<input name="zhuMa" type="checkbox" value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|1|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }| |${jcMatchAgainst.guestTeam}|平| " class="input-zucai" ${fn:indexOf(zhuMaStr,checkName1)>-1?'checked':'' }/>平&nbsp;&nbsp;
			<input name="zhuMa" type="checkbox" value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|0|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }| |${jcMatchAgainst.guestTeam}|负| " class="input-zucai" ${fn:indexOf(zhuMaStr,checkName0)>-1?'checked':'' }/>负</p>
        			
        		</c:otherwise>
        	</c:choose>
       		</div>
		</c:forEach>
	</c:forEach>
    </c:if>
 	<div class="wap-pro-gray-goucai" style="padding-top:10px;">
    		<strong>过关方式：</strong><br />
            <p style="line-height:25px; padding-left:10px;"><input name="500" type="radio" value="单关" checked="checked" readonly="readonly"/>单关</p>
     	
    </div>
     <p class="gx-tjiao"><input type="submit" value="提交投注" name="submitType" class="tz-chakan-btn" /></p>
   	</form>
     	</c:otherwise>
     </c:choose>
    