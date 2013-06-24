 <%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><span>竞彩足球</span></p>
    </div>
    
     <h1><a href="${pageContext.request.contextPath }/jcFootballIndex/byJcFootBall?type=1&valueType=1&wanFa=FT001" >胜平负</a><span>|</span><a href="${pageContext.request.contextPath }/jcFootballIndex/byJcFootBall?type=1&valueType=1&wanFa=J00013">让球胜平负</a><span>|</span><a href="${pageContext.request.contextPath }/jcFootballIndex/byJcFootBall?type=1&valueType=1&wanFa=FT002" class="tit-cur">比分</a><span>|</span><a href="${pageContext.request.contextPath }/jcFootballIndex/byJcFootBall?type=1&valueType=1&wanFa=FT003">总进球</a><span>|</span><a href="${pageContext.request.contextPath }/jcFootballIndex/byJcFootBall?type=1&valueType=1&wanFa=FT004">半全场</a></h1>
     <c:choose>
     	<c:when test="${'1' eq valueType }">
     		<h3><a href="${pageContext.request.contextPath }/jcFootballIndex/byJcFootBall?type=1&valueType=0&wanFa=FT002" >单关</a><span>|</span><a class="tittwo-cur">过关</a></h3>
    
    <div class="wap-banner">
    	<p>猜比赛，赢百万奖金！</p>
        <p>销售时间：<em class="end-time">周一/二/五 09:00～22:45 周三/四 07:30～22:45 周六/日 09:00～00:45</em></p>
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
        	<c:set var="checkName90">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|90</c:set>
        	<c:set var="checkName10">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|10</c:set>
        	<c:set var="checkName20">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|20</c:set>
        	<c:set var="checkName21">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|21</c:set>
        	<c:set var="checkName30">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|30</c:set>
        	<c:set var="checkName31">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|31</c:set>
        	<c:set var="checkName32">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|32</c:set>
        	<c:set var="checkName40">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|40</c:set>
        	<c:set var="checkName41">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|41</c:set>
        	<c:set var="checkName42">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|42</c:set>
        	<c:set var="checkName50">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|50</c:set>
        	<c:set var="checkName51">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|51</c:set>
        	<c:set var="checkName52">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|52</c:set>
        	<c:set var="checkName99">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|99</c:set>
        	<c:set var="checkName00">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|00</c:set>
        	<c:set var="checkName11">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|11</c:set>
        	<c:set var="checkName22">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|22</c:set>
        	<c:set var="checkName33">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|33</c:set>
        	<c:set var="checkName09">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|09</c:set>
        	<c:set var="checkName01">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|01</c:set>
        	<c:set var="checkName02">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|02</c:set>
        	<c:set var="checkName12">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|12</c:set>
        	<c:set var="checkName03">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|03</c:set>
        	<c:set var="checkName13">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|13</c:set>
        	<c:set var="checkName23">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|23</c:set>
        	<c:set var="checkName04">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|04</c:set>
        	<c:set var="checkName14">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|14</c:set>
        	<c:set var="checkName24">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|24</c:set>
        	<c:set var="checkName05">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|05</c:set>
        	<c:set var="checkName15">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|15</c:set>
        	<c:set var="checkName25">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|25</c:set>
        	
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
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|90|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|胜其它|(${jcMatchAgainst.peiLv.score.v90})" ${fn:indexOf(zhuMaStr,checkName90)>-1?'selected':'' }>胜其他(${jcMatchAgainst.peiLv.score.v90})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|10|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|1:0|(${jcMatchAgainst.peiLv.score.v10})" ${fn:indexOf(zhuMaStr,checkName10)>-1?'selected':'' }>1:0(${jcMatchAgainst.peiLv.score.v10})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|20|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|2:0|(${jcMatchAgainst.peiLv.score.v20})" ${fn:indexOf(zhuMaStr,checkName20)>-1?'selected':'' }>2:0(${jcMatchAgainst.peiLv.score.v20})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|21|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|2:1|(${jcMatchAgainst.peiLv.score.v21})" ${fn:indexOf(zhuMaStr,checkName21)>-1?'selected':'' }>2:1(${jcMatchAgainst.peiLv.score.v21})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|30|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|3:0|(${jcMatchAgainst.peiLv.score.v30})" ${fn:indexOf(zhuMaStr,checkName30)>-1?'selected':'' }>3:0(${jcMatchAgainst.peiLv.score.v30})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|31|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|3:1|(${jcMatchAgainst.peiLv.score.v31})" ${fn:indexOf(zhuMaStr,checkName31)>-1?'selected':'' }>3:1(${jcMatchAgainst.peiLv.score.v31})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|32|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|3:2|(${jcMatchAgainst.peiLv.score.v32})" ${fn:indexOf(zhuMaStr,checkName32)>-1?'selected':'' }>3:2(${jcMatchAgainst.peiLv.score.v32})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|40|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|4:0|(${jcMatchAgainst.peiLv.score.v40})" ${fn:indexOf(zhuMaStr,checkName40)>-1?'selected':'' }>4:0(${jcMatchAgainst.peiLv.score.v40})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|41|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|4:1|(${jcMatchAgainst.peiLv.score.v41})" ${fn:indexOf(zhuMaStr,checkName41)>-1?'selected':'' }>4:1(${jcMatchAgainst.peiLv.score.v41})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|42|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|4:2|(${jcMatchAgainst.peiLv.score.v42})" ${fn:indexOf(zhuMaStr,checkName42)>-1?'selected':'' }>4:2(${jcMatchAgainst.peiLv.score.v42})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|50|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|5:0|(${jcMatchAgainst.peiLv.score.v50})" ${fn:indexOf(zhuMaStr,checkName50)>-1?'selected':'' }>5:0(${jcMatchAgainst.peiLv.score.v50})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|51|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|5:1|(${jcMatchAgainst.peiLv.score.v51})" ${fn:indexOf(zhuMaStr,checkName51)>-1?'selected':'' }>5:1(${jcMatchAgainst.peiLv.score.v51})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|52|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|5:2|(${jcMatchAgainst.peiLv.score.v52})" ${fn:indexOf(zhuMaStr,checkName52)>-1?'selected':'' }>5:2(${jcMatchAgainst.peiLv.score.v52})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|99|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|平其他|(${jcMatchAgainst.peiLv.score.v99})" ${fn:indexOf(zhuMaStr,checkName99)>-1?'selected':'' }>平其他(${jcMatchAgainst.peiLv.score.v99})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|00|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|0:0|(${jcMatchAgainst.peiLv.score.v00})" ${fn:indexOf(zhuMaStr,checkName00)>-1?'selected':'' }>0:0(${jcMatchAgainst.peiLv.score.v00})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|11|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|1:1|(${jcMatchAgainst.peiLv.score.v11})" ${fn:indexOf(zhuMaStr,checkName11)>-1?'selected':'' }>1:1(${jcMatchAgainst.peiLv.score.v11})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|22|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|2:2|(${jcMatchAgainst.peiLv.score.v22})" ${fn:indexOf(zhuMaStr,checkName22)>-1?'selected':'' }>2:2(${jcMatchAgainst.peiLv.score.v22})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|33|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|3:3|(${jcMatchAgainst.peiLv.score.v33})" ${fn:indexOf(zhuMaStr,checkName33)>-1?'selected':'' }>3:3(${jcMatchAgainst.peiLv.score.v33})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|09|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|负其他|(${jcMatchAgainst.peiLv.score.v09})" ${fn:indexOf(zhuMaStr,checkName09)>-1?'selected':'' }>负其他(${jcMatchAgainst.peiLv.score.v09})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|01|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|0:1|(${jcMatchAgainst.peiLv.score.v01})" ${fn:indexOf(zhuMaStr,checkName01)>-1?'selected':'' }>0:1(${jcMatchAgainst.peiLv.score.v01})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|02|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|0:2|(${jcMatchAgainst.peiLv.score.v02})" ${fn:indexOf(zhuMaStr,checkName02)>-1?'selected':'' }>0:2(${jcMatchAgainst.peiLv.score.v02})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|12|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|1:2|(${jcMatchAgainst.peiLv.score.v12})" ${fn:indexOf(zhuMaStr,checkName12)>-1?'selected':'' }>1:2(${jcMatchAgainst.peiLv.score.v12})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|03|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|0:3|(${jcMatchAgainst.peiLv.score.v03})" ${fn:indexOf(zhuMaStr,checkName03)>-1?'selected':'' }>0:3(${jcMatchAgainst.peiLv.score.v03})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|13|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|1:3|(${jcMatchAgainst.peiLv.score.v13})" ${fn:indexOf(zhuMaStr,checkName13)>-1?'selected':'' }>1:3(${jcMatchAgainst.peiLv.score.v13})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|23|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|2:3|(${jcMatchAgainst.peiLv.score.v23})" ${fn:indexOf(zhuMaStr,checkName23)>-1?'selected':'' }>2:3(${jcMatchAgainst.peiLv.score.v23})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|04|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|0:4|(${jcMatchAgainst.peiLv.score.v04})" ${fn:indexOf(zhuMaStr,checkName04)>-1?'selected':'' }>0:4(${jcMatchAgainst.peiLv.score.v04})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|14|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|1:4|(${jcMatchAgainst.peiLv.score.v14})" ${fn:indexOf(zhuMaStr,checkName14)>-1?'selected':'' }>1:4(${jcMatchAgainst.peiLv.score.v14})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|24|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|2:4|(${jcMatchAgainst.peiLv.score.v24})" ${fn:indexOf(zhuMaStr,checkName24)>-1?'selected':'' }>2:4(${jcMatchAgainst.peiLv.score.v24})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|05|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|0:5|(${jcMatchAgainst.peiLv.score.v05})" ${fn:indexOf(zhuMaStr,checkName05)>-1?'selected':'' }>0:5(${jcMatchAgainst.peiLv.score.v05})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|15|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|1:5|(${jcMatchAgainst.peiLv.score.v15})" ${fn:indexOf(zhuMaStr,checkName15)>-1?'selected':'' }>1:5(${jcMatchAgainst.peiLv.score.v15})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|25|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|2:5|(${jcMatchAgainst.peiLv.score.v25})" ${fn:indexOf(zhuMaStr,checkName25)>-1?'selected':'' }>2:5(${jcMatchAgainst.peiLv.score.v25})</option>
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
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|90|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|胜其它| " ${fn:indexOf(zhuMaStr,checkName90)>-1?'selected':'' }>胜其他</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|10|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|1:0| " ${fn:indexOf(zhuMaStr,checkName10)>-1?'selected':'' }>1:0</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|20|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|2:0| " ${fn:indexOf(zhuMaStr,checkName20)>-1?'selected':'' }>2:0</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|21|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|2:1| " ${fn:indexOf(zhuMaStr,checkName21)>-1?'selected':'' }>2:1</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|30|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|3:0| " ${fn:indexOf(zhuMaStr,checkName30)>-1?'selected':'' }>3:0</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|31|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|3:1| " ${fn:indexOf(zhuMaStr,checkName31)>-1?'selected':'' }>3:1</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|32|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|3:2| " ${fn:indexOf(zhuMaStr,checkName32)>-1?'selected':'' }>3:2</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|40|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|4:0| " ${fn:indexOf(zhuMaStr,checkName40)>-1?'selected':'' }>4:0</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|41|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|4:1| " ${fn:indexOf(zhuMaStr,checkName41)>-1?'selected':'' }>4:1</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|42|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|4:2| " ${fn:indexOf(zhuMaStr,checkName42)>-1?'selected':'' }>4:2</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|50|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|5:0| " ${fn:indexOf(zhuMaStr,checkName50)>-1?'selected':'' }>5:0</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|51|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|5:1| " ${fn:indexOf(zhuMaStr,checkName51)>-1?'selected':'' }>5:1</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|52|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|5:2| " ${fn:indexOf(zhuMaStr,checkName52)>-1?'selected':'' }>5:2</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|99|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|平其他| " ${fn:indexOf(zhuMaStr,checkName99)>-1?'selected':'' }>平其他</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|00|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|0:0| " ${fn:indexOf(zhuMaStr,checkName00)>-1?'selected':'' }>0:0</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|11|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|1:1| " ${fn:indexOf(zhuMaStr,checkName11)>-1?'selected':'' }>1:1</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|22|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|2:2| " ${fn:indexOf(zhuMaStr,checkName22)>-1?'selected':'' }>2:2</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|33|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|3:3| " ${fn:indexOf(zhuMaStr,checkName33)>-1?'selected':'' }>3:3</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|09|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|负其他| " ${fn:indexOf(zhuMaStr,checkName09)>-1?'selected':'' }>负其他</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|01|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|0:1| " ${fn:indexOf(zhuMaStr,checkName01)>-1?'selected':'' }>0:1</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|02|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|0:2| " ${fn:indexOf(zhuMaStr,checkName02)>-1?'selected':'' }>0:2</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|12|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|1:2| " ${fn:indexOf(zhuMaStr,checkName12)>-1?'selected':'' }>1:2</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|03|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|0:3| " ${fn:indexOf(zhuMaStr,checkName03)>-1?'selected':'' }>0:3</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|13|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|1:3| " ${fn:indexOf(zhuMaStr,checkName13)>-1?'selected':'' }>1:3</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|23|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|2:3| " ${fn:indexOf(zhuMaStr,checkName23)>-1?'selected':'' }>2:3</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|04|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|0:4| " ${fn:indexOf(zhuMaStr,checkName04)>-1?'selected':'' }>0:4</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|14|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|1:4| " ${fn:indexOf(zhuMaStr,checkName14)>-1?'selected':'' }>1:4</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|24|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|2:4| " ${fn:indexOf(zhuMaStr,checkName24)>-1?'selected':'' }>2:4</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|05|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|0:5| " ${fn:indexOf(zhuMaStr,checkName05)>-1?'selected':'' }>0:5</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|15|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|1:5| " ${fn:indexOf(zhuMaStr,checkName15)>-1?'selected':'' }>1:5</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|25|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|2:5| " ${fn:indexOf(zhuMaStr,checkName25)>-1?'selected':'' }>2:5</option>
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
     	<h3><a class="tittwo-cur">单关</a><span>|</span><a href="${pageContext.request.contextPath }/jcFootballIndex/byJcFootBall?type=1&valueType=1&wanFa=FT002">过关</a></h3>
    
    <div class="wap-banner">
    	<p>猜比赛，赢百万奖金！</p>
        <p>销售时间：<em class="end-time">周一/二/五 09:00～22:45 周三/四 07:30～22:45 周六/日 09:00～00:45</em></p>
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
        	<c:set var="checkName90">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|90</c:set>
        	<c:set var="checkName10">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|10</c:set>
        	<c:set var="checkName20">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|20</c:set>
        	<c:set var="checkName21">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|21</c:set>
        	<c:set var="checkName30">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|30</c:set>
        	<c:set var="checkName31">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|31</c:set>
        	<c:set var="checkName32">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|32</c:set>
        	<c:set var="checkName40">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|40</c:set>
        	<c:set var="checkName41">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|41</c:set>
        	<c:set var="checkName42">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|42</c:set>
        	<c:set var="checkName50">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|50</c:set>
        	<c:set var="checkName51">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|51</c:set>
        	<c:set var="checkName52">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|52</c:set>
        	<c:set var="checkName99">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|99</c:set>
        	<c:set var="checkName00">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|00</c:set>
        	<c:set var="checkName11">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|11</c:set>
        	<c:set var="checkName22">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|22</c:set>
        	<c:set var="checkName33">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|33</c:set>
        	<c:set var="checkName09">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|09</c:set>
        	<c:set var="checkName01">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|01</c:set>
        	<c:set var="checkName02">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|02</c:set>
        	<c:set var="checkName12">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|12</c:set>
        	<c:set var="checkName03">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|03</c:set>
        	<c:set var="checkName13">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|13</c:set>
        	<c:set var="checkName23">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|23</c:set>
        	<c:set var="checkName04">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|04</c:set>
        	<c:set var="checkName14">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|14</c:set>
        	<c:set var="checkName24">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|24</c:set>
        	<c:set var="checkName05">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|05</c:set>
        	<c:set var="checkName15">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|15</c:set>
        	<c:set var="checkName25">${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|25</c:set>
        	
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
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|90|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|胜其它|(${jcMatchAgainst.peiLv.score.v90})" ${fn:indexOf(zhuMaStr,checkName90)>-1?'selected':'' }>胜其他(${jcMatchAgainst.peiLv.score.v90})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|10|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|1:0|(${jcMatchAgainst.peiLv.score.v10})" ${fn:indexOf(zhuMaStr,checkName10)>-1?'selected':'' }>1:0(${jcMatchAgainst.peiLv.score.v10})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|20|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|2:0|(${jcMatchAgainst.peiLv.score.v20})" ${fn:indexOf(zhuMaStr,checkName20)>-1?'selected':'' }>2:0(${jcMatchAgainst.peiLv.score.v20})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|21|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|2:1|(${jcMatchAgainst.peiLv.score.v21})" ${fn:indexOf(zhuMaStr,checkName21)>-1?'selected':'' }>2:1(${jcMatchAgainst.peiLv.score.v21})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|30|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|3:0|(${jcMatchAgainst.peiLv.score.v30})" ${fn:indexOf(zhuMaStr,checkName30)>-1?'selected':'' }>3:0(${jcMatchAgainst.peiLv.score.v30})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|31|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|3:1|(${jcMatchAgainst.peiLv.score.v31})" ${fn:indexOf(zhuMaStr,checkName31)>-1?'selected':'' }>3:1(${jcMatchAgainst.peiLv.score.v31})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|32|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|3:2|(${jcMatchAgainst.peiLv.score.v32})" ${fn:indexOf(zhuMaStr,checkName32)>-1?'selected':'' }>3:2(${jcMatchAgainst.peiLv.score.v32})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|40|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|4:0|(${jcMatchAgainst.peiLv.score.v40})" ${fn:indexOf(zhuMaStr,checkName40)>-1?'selected':'' }>4:0(${jcMatchAgainst.peiLv.score.v40})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|41|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|4:1|(${jcMatchAgainst.peiLv.score.v41})" ${fn:indexOf(zhuMaStr,checkName41)>-1?'selected':'' }>4:1(${jcMatchAgainst.peiLv.score.v41})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|42|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|4:2|(${jcMatchAgainst.peiLv.score.v42})" ${fn:indexOf(zhuMaStr,checkName42)>-1?'selected':'' }>4:2(${jcMatchAgainst.peiLv.score.v42})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|50|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|5:0|(${jcMatchAgainst.peiLv.score.v50})" ${fn:indexOf(zhuMaStr,checkName50)>-1?'selected':'' }>5:0(${jcMatchAgainst.peiLv.score.v50})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|51|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|5:1|(${jcMatchAgainst.peiLv.score.v51})" ${fn:indexOf(zhuMaStr,checkName51)>-1?'selected':'' }>5:1(${jcMatchAgainst.peiLv.score.v51})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|52|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|5:2|(${jcMatchAgainst.peiLv.score.v52})" ${fn:indexOf(zhuMaStr,checkName52)>-1?'selected':'' }>5:2(${jcMatchAgainst.peiLv.score.v52})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|99|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|平其他|(${jcMatchAgainst.peiLv.score.v99})" ${fn:indexOf(zhuMaStr,checkName99)>-1?'selected':'' }>平其他(${jcMatchAgainst.peiLv.score.v99})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|00|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|0:0|(${jcMatchAgainst.peiLv.score.v00})" ${fn:indexOf(zhuMaStr,checkName00)>-1?'selected':'' }>0:0(${jcMatchAgainst.peiLv.score.v00})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|11|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|1:1|(${jcMatchAgainst.peiLv.score.v11})" ${fn:indexOf(zhuMaStr,checkName11)>-1?'selected':'' }>1:1(${jcMatchAgainst.peiLv.score.v11})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|22|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|2:2|(${jcMatchAgainst.peiLv.score.v22})" ${fn:indexOf(zhuMaStr,checkName22)>-1?'selected':'' }>2:2(${jcMatchAgainst.peiLv.score.v22})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|33|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|3:3|(${jcMatchAgainst.peiLv.score.v33})" ${fn:indexOf(zhuMaStr,checkName33)>-1?'selected':'' }>3:3(${jcMatchAgainst.peiLv.score.v33})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|09|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|负其他|(${jcMatchAgainst.peiLv.score.v09})" ${fn:indexOf(zhuMaStr,checkName09)>-1?'selected':'' }>负其他(${jcMatchAgainst.peiLv.score.v09})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|01|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|0:1|(${jcMatchAgainst.peiLv.score.v01})" ${fn:indexOf(zhuMaStr,checkName01)>-1?'selected':'' }>0:1(${jcMatchAgainst.peiLv.score.v01})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|02|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|0:2|(${jcMatchAgainst.peiLv.score.v02})" ${fn:indexOf(zhuMaStr,checkName02)>-1?'selected':'' }>0:2(${jcMatchAgainst.peiLv.score.v02})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|12|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|1:2|(${jcMatchAgainst.peiLv.score.v12})" ${fn:indexOf(zhuMaStr,checkName12)>-1?'selected':'' }>1:2(${jcMatchAgainst.peiLv.score.v12})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|03|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|0:3|(${jcMatchAgainst.peiLv.score.v03})" ${fn:indexOf(zhuMaStr,checkName03)>-1?'selected':'' }>0:3(${jcMatchAgainst.peiLv.score.v03})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|13|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|1:3|(${jcMatchAgainst.peiLv.score.v13})" ${fn:indexOf(zhuMaStr,checkName13)>-1?'selected':'' }>1:3(${jcMatchAgainst.peiLv.score.v13})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|23|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|2:3|(${jcMatchAgainst.peiLv.score.v23})" ${fn:indexOf(zhuMaStr,checkName23)>-1?'selected':'' }>2:3(${jcMatchAgainst.peiLv.score.v23})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|04|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|0:4|(${jcMatchAgainst.peiLv.score.v04})" ${fn:indexOf(zhuMaStr,checkName04)>-1?'selected':'' }>0:4(${jcMatchAgainst.peiLv.score.v04})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|14|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|1:4|(${jcMatchAgainst.peiLv.score.v14})" ${fn:indexOf(zhuMaStr,checkName14)>-1?'selected':'' }>1:4(${jcMatchAgainst.peiLv.score.v14})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|24|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|2:4|(${jcMatchAgainst.peiLv.score.v24})" ${fn:indexOf(zhuMaStr,checkName24)>-1?'selected':'' }>2:4(${jcMatchAgainst.peiLv.score.v24})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|05|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|0:5|(${jcMatchAgainst.peiLv.score.v05})" ${fn:indexOf(zhuMaStr,checkName05)>-1?'selected':'' }>0:5(${jcMatchAgainst.peiLv.score.v05})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|15|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|1:5|(${jcMatchAgainst.peiLv.score.v15})" ${fn:indexOf(zhuMaStr,checkName15)>-1?'selected':'' }>1:5(${jcMatchAgainst.peiLv.score.v15})</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|25|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|2:5|(${jcMatchAgainst.peiLv.score.v25})" ${fn:indexOf(zhuMaStr,checkName25)>-1?'selected':'' }>2:5(${jcMatchAgainst.peiLv.score.v25})</option>
                </select>
			</p>
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
				<select name="zhuMa" multiple="multiple" class="sel-opt">
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|90|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|胜其它| " ${fn:indexOf(zhuMaStr,checkName90)>-1?'selected':'' }>胜其他</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|10|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|1:0| " ${fn:indexOf(zhuMaStr,checkName10)>-1?'selected':'' }>1:0</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|20|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|2:0| " ${fn:indexOf(zhuMaStr,checkName20)>-1?'selected':'' }>2:0</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|21|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|2:1| " ${fn:indexOf(zhuMaStr,checkName21)>-1?'selected':'' }>2:1</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|30|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|3:0| " ${fn:indexOf(zhuMaStr,checkName30)>-1?'selected':'' }>3:0</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|31|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|3:1| " ${fn:indexOf(zhuMaStr,checkName31)>-1?'selected':'' }>3:1</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|32|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|3:2| " ${fn:indexOf(zhuMaStr,checkName32)>-1?'selected':'' }>3:2</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|40|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|4:0| " ${fn:indexOf(zhuMaStr,checkName40)>-1?'selected':'' }>4:0</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|41|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|4:1| " ${fn:indexOf(zhuMaStr,checkName41)>-1?'selected':'' }>4:1</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|42|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|4:2| " ${fn:indexOf(zhuMaStr,checkName42)>-1?'selected':'' }>4:2</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|50|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|5:0| " ${fn:indexOf(zhuMaStr,checkName50)>-1?'selected':'' }>5:0</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|51|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|5:1| " ${fn:indexOf(zhuMaStr,checkName51)>-1?'selected':'' }>5:1</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|52|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|5:2| " ${fn:indexOf(zhuMaStr,checkName52)>-1?'selected':'' }>5:2</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|99|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|平其他| " ${fn:indexOf(zhuMaStr,checkName99)>-1?'selected':'' }>平其他</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|00|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|0:0| " ${fn:indexOf(zhuMaStr,checkName00)>-1?'selected':'' }>0:0</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|11|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|1:1| " ${fn:indexOf(zhuMaStr,checkName11)>-1?'selected':'' }>1:1</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|22|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|2:2| " ${fn:indexOf(zhuMaStr,checkName22)>-1?'selected':'' }>2:2</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|33|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|3:3| " ${fn:indexOf(zhuMaStr,checkName33)>-1?'selected':'' }>3:3</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|09|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|负其他| " ${fn:indexOf(zhuMaStr,checkName09)>-1?'selected':'' }>负其他</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|01|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|0:1| " ${fn:indexOf(zhuMaStr,checkName01)>-1?'selected':'' }>0:1</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|02|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|0:2| " ${fn:indexOf(zhuMaStr,checkName02)>-1?'selected':'' }>0:2</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|12|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|1:2| " ${fn:indexOf(zhuMaStr,checkName12)>-1?'selected':'' }>1:2</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|03|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|0:3| " ${fn:indexOf(zhuMaStr,checkName03)>-1?'selected':'' }>0:3</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|13|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|1:3| " ${fn:indexOf(zhuMaStr,checkName13)>-1?'selected':'' }>1:3</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|23|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|2:3| " ${fn:indexOf(zhuMaStr,checkName23)>-1?'selected':'' }>2:3</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|04|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|0:4| " ${fn:indexOf(zhuMaStr,checkName04)>-1?'selected':'' }>0:4</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|14|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|1:4| " ${fn:indexOf(zhuMaStr,checkName14)>-1?'selected':'' }>1:4</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|24|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|2:4| " ${fn:indexOf(zhuMaStr,checkName24)>-1?'selected':'' }>2:4</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|05|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|0:5| " ${fn:indexOf(zhuMaStr,checkName05)>-1?'selected':'' }>0:5</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|15|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|1:5| " ${fn:indexOf(zhuMaStr,checkName15)>-1?'selected':'' }>1:5</option>
                    <option value="${jcMatchAgainst.day}|${jcMatchAgainst.weekId}|${jcMatchAgainst.teamId }|25|${jcMatchAgainst.week }|${jcMatchAgainst.league}|${jcMatchAgainst.homeTeam }|VS|${jcMatchAgainst.guestTeam}|2:5| " ${fn:indexOf(zhuMaStr,checkName25)>-1?'selected':'' }>2:5</option>
                </select>
			</p>
        			
        		</c:otherwise>
        	</c:choose>
       		</div>
		</c:forEach>
	</c:forEach>
    </c:if>
     <p class="gx-tjiao" style="padding-top:10px;">
    		<strong>过关方式：</strong><br />
            <p style="line-height:25px; padding-left:10px;"><input name="500" type="radio" value="单关" checked="checked" readonly="readonly"/>单关</p>
     </p>
          <p class="gx-tjiao"><input type="submit" value="提交投注" name="submitType" class="tz-chakan-btn" /></p>
     
     </form>
     	</c:otherwise>
     	
     </c:choose>
