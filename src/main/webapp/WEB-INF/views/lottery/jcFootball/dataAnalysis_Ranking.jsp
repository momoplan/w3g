 <%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><strong><a href="${pageContext.request.contextPath }/jcFootballIndex/byJcFootBall?type=1&valueType=1&wanFa=FT001">竞彩足球</a>-</strong><span>数据分析</span></p>
    </div>
    <c:if test="${not empty matchMessage }">
     <p class="h1-bot" style=" line-height:22px; border-bottom:none;">
     	${matchMessage.homeTeam }(主) vs ${matchMessage.guestTeam }<br />
     	<span style="font-weight:normal;">${matchMessage.sclassName_j } ${matchMessage.matchTime } (${matchMessage.matchState })</span>
	 </p>
	 </c:if>
<h1><a class="tit-cur">排名</a><span>|</span><a href="${pageContext.request.contextPath }/jcFootballIndex/selectDataAnalysisHistory?day=${day }&weekId=${weekId}&teamId=${teamId}&type=history_z10">历史</a><span>|</span><a href="${pageContext.request.contextPath }/jcFootballIndex/selectDataAnalysisOuPei?day=${day }&weekId=${weekId}&teamId=${teamId}">欧指</a><span>|</span><a href="${pageContext.request.contextPath }/jcFootballIndex/selectDataAnalysisYaPan?day=${day }&weekId=${weekId}&teamId=${teamId}">亚盘</a></h1>
   
<table class="jifen-table zucai-paihang" border="1" cellpadding="0" cellspacing="0" width="260" style="margin-left:0">
   		<tr bgcolor="#FDFCE0" class="tr-one">
        	<td>排名</td>
            <td width="60">球队</td>
            <td>赛</td>
            <td>胜</td>
            <td>平</td>
            <td>负</td>
            <td>净</td>
            <td>积分</td>
        </tr>
        <c:if test="${not empty matchRankings }">
        	<c:forEach items="${matchRankings }" var="matchRanking" varStatus ="ranking">
        		<c:choose>
        			<c:when test="${(ranking.index+1)%2==0 }">
        				<tr bgcolor="#faf9f9">
        			</c:when>
        			<c:otherwise>
        				<tr >
        			</c:otherwise>
        		</c:choose>
        		
        		<td>${matchRanking.ranking}</td>
        		<c:choose>
        			<c:when test="${matchRanking.teamID eq matchMessage.homeTeamID }">
        				<td style="color: red">${matchRanking.teamName }</td>
        			</c:when>
        			<c:when test="${matchRanking.teamID eq matchMessage.guestTeamID }">
        				<td style="color: blue">${matchRanking.teamName }</td>
        			</c:when>
        			<c:otherwise>
        				<td >${matchRanking.teamName }</td>
        			</c:otherwise>
        		</c:choose>
            	
            	<td>${matchRanking.matchCount }</td>
            	<td>${matchRanking.win }</td>
            	<td>${matchRanking.standoff }</td>
           	 	<td>${matchRanking.lose }</td>
            	<td>${matchRanking.goalDifference }</td>
            	<td>${matchRanking.integral }</td>
       			</tr>
        	</c:forEach>
        </c:if>
   </table>