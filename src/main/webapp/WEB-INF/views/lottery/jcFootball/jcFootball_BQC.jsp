 <%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><span>竞彩足球</span></p>
    </div>
    
     <h1><a href="${pageContext.request.contextPath }/jcFootballIndex/byJcFootBall?type=1&valueType=1&wanFa=FT001" >胜平负</a><span>|</span><a href="${pageContext.request.contextPath }/jcFootballIndex/byJcFootBall?type=1&valueType=1&wanFa=J00013">让球胜平负</a><span>|</span><a href="${pageContext.request.contextPath }/jcFootballIndex/byJcFootBall?type=1&valueType=1&wanFa=FT002">比分</a><span>|</span><a href="${pageContext.request.contextPath }/jcFootballIndex/byJcFootBall?type=1&valueType=1&wanFa=FT003" >总进球</a><span>|</span><a href="${pageContext.request.contextPath }/jcFootballIndex/byJcFootBall?type=1&valueType=1&wanFa=FT004" class="tit-cur">半全场</a></h1>
          <c:choose>
     	<c:when test="${'1' eq valueType }">
     		<h3><a href="${pageContext.request.contextPath }/jcFootballIndex/byJcFootBall?type=1&valueType=0&wanFa=FT004">单关</a><span>|</span><a class="tittwo-cur">过关</a></h3>
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
        	<c:set var="checkName33">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|33</c:set>
        	<c:set var="checkName31">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|31</c:set>
        	<c:set var="checkName30">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|30</c:set>
        	<c:set var="checkName13">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|13</c:set>
        	<c:set var="checkName11">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|11</c:set>
        	<c:set var="checkName10">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|10</c:set>
        	<c:set var="checkName03">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|03</c:set>
        	<c:set var="checkName01">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|01</c:set>
        	<c:set var="checkName00">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|00</c:set>

        	<c:choose>
        		<c:when test="${not empty jcMatchAgainst.peiLv }">
        			<table width="260" border="1" cellspacing="0" cellpadding="0" class="jifen-table playball-table">
  				<tr>
    			<td width="110">${jcMatchAgainst.homeTeam }(主)</td>
    			<td><span>VS</span></td>
    			<td width="110">${jcMatchAgainst.guestTeam }</td>
  			</tr>
			</table>
			<p class="zucai-kind">
					<select name="zhuMa" multiple="multiple" class="sel-opt">
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|33|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|胜-胜|(${jcMatchAgainst.peiLv.half.v33})" ${fn:indexOf(zhuMaStr,checkName33)>-1?'selected':'' }>胜-胜(${jcMatchAgainst.peiLv.half.v33})</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|31|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|胜-平|(${jcMatchAgainst.peiLv.half.v31})" ${fn:indexOf(zhuMaStr,checkName31)>-1?'selected':'' }>胜-平(${jcMatchAgainst.peiLv.half.v31})</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|30|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|胜-负|(${jcMatchAgainst.peiLv.half.v30})" ${fn:indexOf(zhuMaStr,checkName30)>-1?'selected':'' }>胜-负(${jcMatchAgainst.peiLv.half.v30})</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|13|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|平-胜|(${jcMatchAgainst.peiLv.half.v13})" ${fn:indexOf(zhuMaStr,checkName13)>-1?'selected':'' }>平-胜(${jcMatchAgainst.peiLv.half.v13})</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|11|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|平-平|(${jcMatchAgainst.peiLv.half.v11})" ${fn:indexOf(zhuMaStr,checkName11)>-1?'selected':'' }>平-平(${jcMatchAgainst.peiLv.half.v11})</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|10|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|平-负|(${jcMatchAgainst.peiLv.half.v10})" ${fn:indexOf(zhuMaStr,checkName10)>-1?'selected':'' }>平-负(${jcMatchAgainst.peiLv.half.v10})</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|03|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|负-胜|(${jcMatchAgainst.peiLv.half.v03})" ${fn:indexOf(zhuMaStr,checkName03)>-1?'selected':'' }>负-胜(${jcMatchAgainst.peiLv.half.v03})</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|01|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|负-平|(${jcMatchAgainst.peiLv.half.v01})" ${fn:indexOf(zhuMaStr,checkName01)>-1?'selected':'' }>负-平(${jcMatchAgainst.peiLv.half.v01})</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|00|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|负-负|(${jcMatchAgainst.peiLv.half.v00})" ${fn:indexOf(zhuMaStr,checkName00)>-1?'selected':'' }>负-负(${jcMatchAgainst.peiLv.half.v00})</option>

                 </select> 
                 </p>
                 </c:when>
        		<c:otherwise>
        		<table width="260" border="1" cellspacing="0" cellpadding="0" class="jifen-table playball-table">
  				<tr>
    			<td width="110">${jcMatchAgainst.homeTeam }(主)</td>
    			<td><span>VS</span></td>
    			<td width="110">${jcMatchAgainst.guestTeam }</td>
  			</tr>
			</table>
			<p class="zucai-kind">
				<select name="zhuMa" multiple="multiple" class="sel-opt">
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|33|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|胜-胜|(${jcMatchAgainst.peiLv.half.v33})" ${fn:indexOf(zhuMaStr,checkName33)>-1?'selected':'' }>胜-胜</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|31|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|胜-平|(${jcMatchAgainst.peiLv.half.v31})" ${fn:indexOf(zhuMaStr,checkName31)>-1?'selected':'' }>胜-平</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|30|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|胜-负|(${jcMatchAgainst.peiLv.half.v30})" ${fn:indexOf(zhuMaStr,checkName30)>-1?'selected':'' }>胜-负</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|13|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|平-胜|(${jcMatchAgainst.peiLv.half.v13})" ${fn:indexOf(zhuMaStr,checkName13)>-1?'selected':'' }>平-胜</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|11|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|平-平|(${jcMatchAgainst.peiLv.half.v11})" ${fn:indexOf(zhuMaStr,checkName11)>-1?'selected':'' }>平-平</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|10|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|平-负|(${jcMatchAgainst.peiLv.half.v10})" ${fn:indexOf(zhuMaStr,checkName10)>-1?'selected':'' }>平-负</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|03|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|负-胜|(${jcMatchAgainst.peiLv.half.v03})" ${fn:indexOf(zhuMaStr,checkName03)>-1?'selected':'' }>负-胜</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|01|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|负-平|(${jcMatchAgainst.peiLv.half.v01})" ${fn:indexOf(zhuMaStr,checkName01)>-1?'selected':'' }>负-平</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|00|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|负-负|(${jcMatchAgainst.peiLv.half.v00})" ${fn:indexOf(zhuMaStr,checkName00)>-1?'selected':'' }>负-负</option>
                 </select>
                 </p>
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
     <h3><a  class="tittwo-cur">单关</a><span>|</span><a href="${pageContext.request.contextPath }/jcFootballIndex/byJcFootBall?type=1&valueType=1&wanFa=FT004">过关</a></h3>
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
        	<c:set var="checkName33">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|33</c:set>
        	<c:set var="checkName31">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|31</c:set>
        	<c:set var="checkName30">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|30</c:set>
        	<c:set var="checkName13">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|13</c:set>
        	<c:set var="checkName11">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|11</c:set>
        	<c:set var="checkName10">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|10</c:set>
        	<c:set var="checkName03">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|03</c:set>
        	<c:set var="checkName01">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|01</c:set>
        	<c:set var="checkName00">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|00</c:set>
        	<c:choose>
        		<c:when test="${not empty jcMatchAgainst.peiLv }">
        			<table width="260" border="1" cellspacing="0" cellpadding="0" class="jifen-table playball-table">
  				<tr>
    			<td width="110">${jcMatchAgainst.homeTeam }(主)</td>
    			<td><span>VS</span></td>
    			<td width="110">${jcMatchAgainst.guestTeam }</td>
  			</tr>
			</table>
			<p class="zucai-kind">
					<select name="zhuMa" multiple="multiple" class="sel-opt">
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|33|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|胜-胜|(${jcMatchAgainst.peiLv.half.v33})" ${fn:indexOf(zhuMaStr,checkName33)>-1?'selected':'' }>胜-胜(${jcMatchAgainst.peiLv.half.v33})</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|31|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|胜-平|(${jcMatchAgainst.peiLv.half.v31})" ${fn:indexOf(zhuMaStr,checkName31)>-1?'selected':'' }>胜-平(${jcMatchAgainst.peiLv.half.v31})</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|30|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|胜-负|(${jcMatchAgainst.peiLv.half.v30})" ${fn:indexOf(zhuMaStr,checkName30)>-1?'selected':'' }>胜-负(${jcMatchAgainst.peiLv.half.v30})</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|13|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|平-胜|(${jcMatchAgainst.peiLv.half.v13})" ${fn:indexOf(zhuMaStr,checkName13)>-1?'selected':'' }>平-胜(${jcMatchAgainst.peiLv.half.v13})</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|11|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|平-平|(${jcMatchAgainst.peiLv.half.v11})" ${fn:indexOf(zhuMaStr,checkName11)>-1?'selected':'' }>平-平(${jcMatchAgainst.peiLv.half.v11})</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|10|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|平-负|(${jcMatchAgainst.peiLv.half.v10})" ${fn:indexOf(zhuMaStr,checkName10)>-1?'selected':'' }>平-负(${jcMatchAgainst.peiLv.half.v10})</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|03|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|负-胜|(${jcMatchAgainst.peiLv.half.v03})" ${fn:indexOf(zhuMaStr,checkName03)>-1?'selected':'' }>负-胜(${jcMatchAgainst.peiLv.half.v03})</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|01|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|负-平|(${jcMatchAgainst.peiLv.half.v01})" ${fn:indexOf(zhuMaStr,checkName01)>-1?'selected':'' }>负-平(${jcMatchAgainst.peiLv.half.v01})</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|00|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|负-负|(${jcMatchAgainst.peiLv.half.v00})" ${fn:indexOf(zhuMaStr,checkName00)>-1?'selected':'' }>负-负(${jcMatchAgainst.peiLv.half.v00})</option>
                 </select> 
                 </p>
                 </c:when>
        		<c:otherwise>
        		<table width="260" border="1" cellspacing="0" cellpadding="0" class="jifen-table playball-table">
  				<tr>
    			<td width="110">${jcMatchAgainst.homeTeam }(主)</td>
    			<td><span>VS</span></td>
    			<td width="110">${jcMatchAgainst.guestTeam }</td>
  			</tr>
			</table>
			<p class="zucai-kind">
				<select name="zhuMa" multiple="multiple" class="sel-opt">
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|33|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|胜-胜|(${jcMatchAgainst.peiLv.half.v33})" ${fn:indexOf(zhuMaStr,checkName33)>-1?'selected':'' }>胜-胜</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|31|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|胜-平|(${jcMatchAgainst.peiLv.half.v31})" ${fn:indexOf(zhuMaStr,checkName31)>-1?'selected':'' }>胜-平</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|30|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|胜-负|(${jcMatchAgainst.peiLv.half.v30})" ${fn:indexOf(zhuMaStr,checkName30)>-1?'selected':'' }>胜-负</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|13|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|平-胜|(${jcMatchAgainst.peiLv.half.v13})" ${fn:indexOf(zhuMaStr,checkName13)>-1?'selected':'' }>平-胜</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|11|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|平-平|(${jcMatchAgainst.peiLv.half.v11})" ${fn:indexOf(zhuMaStr,checkName11)>-1?'selected':'' }>平-平</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|10|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|平-负|(${jcMatchAgainst.peiLv.half.v10})" ${fn:indexOf(zhuMaStr,checkName10)>-1?'selected':'' }>平-负</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|03|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|负-胜|(${jcMatchAgainst.peiLv.half.v03})" ${fn:indexOf(zhuMaStr,checkName03)>-1?'selected':'' }>负-胜</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|01|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|负-平|(${jcMatchAgainst.peiLv.half.v01})" ${fn:indexOf(zhuMaStr,checkName01)>-1?'selected':'' }>负-平</option>
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|00|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|负-负|(${jcMatchAgainst.peiLv.half.v00})" ${fn:indexOf(zhuMaStr,checkName00)>-1?'selected':'' }>负-负</option>
                 </select>
                 </p>
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