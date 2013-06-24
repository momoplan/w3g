 <%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><span>竞彩篮球</span></p>
    </div>
    
     <h1><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=1&wanFa=BSK001" >胜负</a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=1&wanFa=BSK002">让分胜负</a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=1&wanFa=BSK003" class="tit-cur">胜分差</a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=1&wanFa=BSK004">大小分</a></h1>
     <c:choose>
     	<c:when test="${'1' eq valueType }">
     		<h3><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=0&wanFa=BSK003">单关</a><span>|</span><a class="tittwo-cur">过关</a></h3>
     		<div class="wap-banner">
    	<p>猜比赛，赢百万奖金！</p>
        <p>销售时间：<em class="end-time">周一/二/五 09:00～22:45 周三/四 07:30～22:45 周六/日 09:00～00:45
	</em></p>
	<p class="prompt-pro">${messageError }</p>
	 </div>
	 <form action="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketballToSelect" method="post">
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
    		<p class="zucai"><em>${jcMatchAgainst.teamId }.</em>&nbsp;<span>${jcMatchAgainst.league }</span>&nbsp;&nbsp;${jcMatchAgainst.timeView }&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/jcBasketballIndex/selectDataAnalysisRanking?day=${jcMatchAgainst.day }&weekId=${jcMatchAgainst.weekId}&teamId=${jcMatchAgainst.teamId}" class="xi-btn">析</a></p>
        	<c:set var="checkName01">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|01</c:set>
        	<c:set var="checkName02">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|02</c:set>
        	<c:set var="checkName03">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|03</c:set>
        	<c:set var="checkName04">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|04</c:set>
        	<c:set var="checkName05">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|05</c:set>
        	<c:set var="checkName06">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|06</c:set>
        	<c:set var="checkName11">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|11</c:set>
        	<c:set var="checkName12">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|12</c:set>
        	<c:set var="checkName13">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|13</c:set>
        	<c:set var="checkName14">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|14</c:set>
        	<c:set var="checkName15">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|15</c:set>
        	<c:set var="checkName16">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|16</c:set>
        	
        	<c:choose>
        		<c:when test="${not empty jcMatchAgainst.peiLv }">
        			<table width="260" border="1" cellspacing="0" cellpadding="0" class="jifen-table playball-table">
  				<tr>
    			<td width="110">${jcMatchAgainst.guestTeam }</td>
    			<td><span>VS</span></td>
    			<td width="110">${jcMatchAgainst.homeTeam }(主)</td>
  			</tr>
			</table>
			<p class="zucai-kind">
					<select name="zhuMa" multiple="multiple" class="sel-opt">
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|01|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|主胜1-5|(${jcMatchAgainst.peiLv.diff.v01})" ${fn:indexOf(zhuMaStr,checkName01)>-1?'selected':'' }>主胜1-5(${jcMatchAgainst.peiLv.diff.v01})</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|02|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|主胜6-10|(${jcMatchAgainst.peiLv.diff.v02})" ${fn:indexOf(zhuMaStr,checkName02)>-1?'selected':'' }>主胜6-10(${jcMatchAgainst.peiLv.diff.v02})</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|03|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|主胜11-15|(${jcMatchAgainst.peiLv.diff.v03})" ${fn:indexOf(zhuMaStr,checkName03)>-1?'selected':'' }>主胜11-15(${jcMatchAgainst.peiLv.diff.v03})</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|04|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|主胜16-20|(${jcMatchAgainst.peiLv.diff.v04})" ${fn:indexOf(zhuMaStr,checkName04)>-1?'selected':'' }>主胜16-20(${jcMatchAgainst.peiLv.diff.v04})</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|05|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|主胜21-25|(${jcMatchAgainst.peiLv.diff.v05})" ${fn:indexOf(zhuMaStr,checkName05)>-1?'selected':'' }>主胜21-25(${jcMatchAgainst.peiLv.diff.v05})</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|06|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|主胜26+|(${jcMatchAgainst.peiLv.diff.v06})" ${fn:indexOf(zhuMaStr,checkName06)>-1?'selected':'' }>主胜26+(${jcMatchAgainst.peiLv.diff.v06})</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|11|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|客胜1-5|(${jcMatchAgainst.peiLv.diff.v11})" ${fn:indexOf(zhuMaStr,checkName11)>-1?'selected':'' }>客胜1-5(${jcMatchAgainst.peiLv.diff.v11})</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|12|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|客胜6-10|(${jcMatchAgainst.peiLv.diff.v12})" ${fn:indexOf(zhuMaStr,checkName12)>-1?'selected':'' }>客胜6-10(${jcMatchAgainst.peiLv.diff.v12})</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|13|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|客胜11-15|(${jcMatchAgainst.peiLv.diff.v13})" ${fn:indexOf(zhuMaStr,checkName13)>-1?'selected':'' }>客胜11-15(${jcMatchAgainst.peiLv.diff.v13})</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|14|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|客胜16-20|(${jcMatchAgainst.peiLv.diff.v14})" ${fn:indexOf(zhuMaStr,checkName14)>-1?'selected':'' }>客胜16-20(${jcMatchAgainst.peiLv.diff.v14})</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|15|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|客胜21-25|(${jcMatchAgainst.peiLv.diff.v15})" ${fn:indexOf(zhuMaStr,checkName15)>-1?'selected':'' }>客胜21-25(${jcMatchAgainst.peiLv.diff.v15})</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|16|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|客胜26+|(${jcMatchAgainst.peiLv.diff.v16})" ${fn:indexOf(zhuMaStr,checkName16)>-1?'selected':'' }>客胜26+(${jcMatchAgainst.peiLv.diff.v16})</option>
                 		
                 </select> 
                 </p>
				</c:when>
        		<c:otherwise>
        		<table width="260" border="1" cellspacing="0" cellpadding="0" class="jifen-table playball-table">
  				<tr>
    			<td width="110">${jcMatchAgainst.guestTeam }</td>
    			<td><span>VS</span></td>
    			<td width="110">${jcMatchAgainst.homeTeam }(主)</td>
  			</tr>
			</table>
			<p class="zucai-kind">
			<select name="zhuMa" multiple="multiple" class="sel-opt">
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|01|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|主胜1-5| " ${fn:indexOf(zhuMaStr,checkName01)>-1?'selected':'' }>主胜1-5</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|02|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|主胜6-10| " ${fn:indexOf(zhuMaStr,checkName02)>-1?'selected':'' }>主胜6-10</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|03|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|主胜11-15| " ${fn:indexOf(zhuMaStr,checkName03)>-1?'selected':'' }>主胜11-15</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|04|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|主胜16-20| " ${fn:indexOf(zhuMaStr,checkName04)>-1?'selected':'' }>主胜16-20</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|05|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|主胜21-25| " ${fn:indexOf(zhuMaStr,checkName05)>-1?'selected':'' }>主胜21-25</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|06|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|主胜26+| " ${fn:indexOf(zhuMaStr,checkName06)>-1?'selected':'' }>主胜26+</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|11|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|客胜1-5| " ${fn:indexOf(zhuMaStr,checkName11)>-1?'selected':'' }>客胜1-5</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|12|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|客胜6-10| " ${fn:indexOf(zhuMaStr,checkName12)>-1?'selected':'' }>客胜6-10</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|13|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|客胜11-15| " ${fn:indexOf(zhuMaStr,checkName13)>-1?'selected':'' }>客胜11-15</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|14|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|客胜16-20| " ${fn:indexOf(zhuMaStr,checkName14)>-1?'selected':'' }>客胜16-20</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|15|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|客胜21-25| " ${fn:indexOf(zhuMaStr,checkName15)>-1?'selected':'' }>客胜21-25</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|16|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|客胜26+| " ${fn:indexOf(zhuMaStr,checkName16)>-1?'selected':'' }>客胜26+</option>
                 		
                 </select> </p>
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
     	    <h3><a class="tittwo-cur">单关</a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=1&wanFa=BSK003">过关</a></h3>
     		<div class="wap-banner">
    	<p>猜比赛，赢百万奖金！</p>
        <p>销售时间：<em class="end-time">周一/二/五 09:00～22:45 周三/四 07:30～22:45 周六/日 09:00～00:45
	</em></p>
		<p class="prompt-pro">${messageError }</p>
	
	 </div>
	 <form action="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketballToSelect" method="post">
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
    		<p class="zucai"><em>${jcMatchAgainst.teamId }.</em>&nbsp;<span>${jcMatchAgainst.league }</span>&nbsp;&nbsp;${jcMatchAgainst.timeView }&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/jcBasketballIndex/selectDataAnalysisRanking?day=${jcMatchAgainst.day }&weekId=${jcMatchAgainst.weekId}&teamId=${jcMatchAgainst.teamId}" class="xi-btn">析</a></p>
        	<c:set var="checkName01">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|01</c:set>
        	<c:set var="checkName02">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|02</c:set>
        	<c:set var="checkName03">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|03</c:set>
        	<c:set var="checkName04">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|04</c:set>
        	<c:set var="checkName05">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|05</c:set>
        	<c:set var="checkName06">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|06</c:set>
        	<c:set var="checkName11">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|11</c:set>
        	<c:set var="checkName12">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|12</c:set>
        	<c:set var="checkName13">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|13</c:set>
        	<c:set var="checkName14">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|14</c:set>
        	<c:set var="checkName15">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|15</c:set>
        	<c:set var="checkName16">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|16</c:set>
        	<c:choose>
        		<c:when test="${not empty jcMatchAgainst.peiLv }">
        			<table width="260" border="1" cellspacing="0" cellpadding="0" class="jifen-table playball-table">
  				<tr>
    			<td width="110">${jcMatchAgainst.guestTeam }</td>
    			<td><span>VS</span></td>
    			<td width="110">${jcMatchAgainst.homeTeam }(主)</td>
  			</tr>
			</table>
			<p class="zucai-kind">
					<select name="zhuMa" multiple="multiple" class="sel-opt">
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|01|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|主胜1-5|(${jcMatchAgainst.peiLv.diff.v01})" ${fn:indexOf(zhuMaStr,checkName01)>-1?'selected':'' }>主胜1-5(${jcMatchAgainst.peiLv.diff.v01})</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|02|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|主胜6-10|(${jcMatchAgainst.peiLv.diff.v02})" ${fn:indexOf(zhuMaStr,checkName02)>-1?'selected':'' }>主胜6-10(${jcMatchAgainst.peiLv.diff.v02})</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|03|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|主胜11-15|(${jcMatchAgainst.peiLv.diff.v03})" ${fn:indexOf(zhuMaStr,checkName03)>-1?'selected':'' }>主胜11-15(${jcMatchAgainst.peiLv.diff.v03})</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|04|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|主胜16-20|(${jcMatchAgainst.peiLv.diff.v04})" ${fn:indexOf(zhuMaStr,checkName04)>-1?'selected':'' }>主胜16-20(${jcMatchAgainst.peiLv.diff.v04})</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|05|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|主胜21-25|(${jcMatchAgainst.peiLv.diff.v05})" ${fn:indexOf(zhuMaStr,checkName05)>-1?'selected':'' }>主胜21-25(${jcMatchAgainst.peiLv.diff.v05})</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|06|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|主胜26+|(${jcMatchAgainst.peiLv.diff.v06})" ${fn:indexOf(zhuMaStr,checkName06)>-1?'selected':'' }>主胜26+(${jcMatchAgainst.peiLv.diff.v06})</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|11|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|客胜1-5|(${jcMatchAgainst.peiLv.diff.v11})" ${fn:indexOf(zhuMaStr,checkName11)>-1?'selected':'' }>客胜1-5(${jcMatchAgainst.peiLv.diff.v11})</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|12|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|客胜6-10|(${jcMatchAgainst.peiLv.diff.v12})" ${fn:indexOf(zhuMaStr,checkName12)>-1?'selected':'' }>客胜6-10(${jcMatchAgainst.peiLv.diff.v12})</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|13|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|客胜11-15|(${jcMatchAgainst.peiLv.diff.v13})" ${fn:indexOf(zhuMaStr,checkName13)>-1?'selected':'' }>客胜11-15(${jcMatchAgainst.peiLv.diff.v13})</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|14|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|客胜16-20|(${jcMatchAgainst.peiLv.diff.v14})" ${fn:indexOf(zhuMaStr,checkName14)>-1?'selected':'' }>客胜16-20(${jcMatchAgainst.peiLv.diff.v14})</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|15|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|客胜21-25|(${jcMatchAgainst.peiLv.diff.v15})" ${fn:indexOf(zhuMaStr,checkName15)>-1?'selected':'' }>客胜21-25(${jcMatchAgainst.peiLv.diff.v15})</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|16|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|客胜26+|(${jcMatchAgainst.peiLv.diff.v16})" ${fn:indexOf(zhuMaStr,checkName16)>-1?'selected':'' }>客胜26+(${jcMatchAgainst.peiLv.diff.v16})</option>
                 		
                 </select> 
			</p>
        		</c:when>
        		<c:otherwise>
        		<table width="260" border="1" cellspacing="0" cellpadding="0" class="jifen-table playball-table">
  				<tr>
    			<td width="110">${jcMatchAgainst.guestTeam }</td>
    			<td><span>VS</span></td>
    			<td width="110">${jcMatchAgainst.homeTeam }(主)</td>
  			</tr>
			</table>
			<p class="zucai-kind">
<select name="zhuMa" multiple="multiple" class="sel-opt">
                    	<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|01|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|主胜1-5| " ${fn:indexOf(zhuMaStr,checkName01)>-1?'selected':'' }>主胜1-5</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|02|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|主胜6-10| " ${fn:indexOf(zhuMaStr,checkName02)>-1?'selected':'' }>主胜6-10</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|03|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|主胜11-15| " ${fn:indexOf(zhuMaStr,checkName03)>-1?'selected':'' }>主胜11-15</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|04|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|主胜16-20| " ${fn:indexOf(zhuMaStr,checkName04)>-1?'selected':'' }>主胜16-20</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|05|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|主胜21-25| " ${fn:indexOf(zhuMaStr,checkName05)>-1?'selected':'' }>主胜21-25</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|06|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|主胜26+| " ${fn:indexOf(zhuMaStr,checkName06)>-1?'selected':'' }>主胜26+</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|11|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|客胜1-5| " ${fn:indexOf(zhuMaStr,checkName11)>-1?'selected':'' }>客胜1-5</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|12|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|客胜6-10| " ${fn:indexOf(zhuMaStr,checkName12)>-1?'selected':'' }>客胜6-10</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|13|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|客胜11-15| " ${fn:indexOf(zhuMaStr,checkName13)>-1?'selected':'' }>客胜11-15</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|14|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|客胜16-20| " ${fn:indexOf(zhuMaStr,checkName14)>-1?'selected':'' }>客胜16-20</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|15|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|客胜21-25| " ${fn:indexOf(zhuMaStr,checkName15)>-1?'selected':'' }>客胜21-25</option>
                 		<option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|16|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|客胜26+| " ${fn:indexOf(zhuMaStr,checkName16)>-1?'selected':'' }>客胜26+</option>
                 		
                 </select></p>
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
    