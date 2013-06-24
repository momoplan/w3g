 <%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><strong><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=1&wanFa=BSK001">竞彩篮球</a>-</strong><span>数据分析</span></p>
    </div>
    <c:if test="${not empty matchMessage }">
     <p class="h1-bot" style=" line-height:22px; border-bottom:none;">
     	${matchMessage.homeTeam }(主) vs ${matchMessage.guestTeam }<br />
     	<span style="font-weight:normal;">${matchMessage.sclassShortName } ${matchMessage.matchTime } (${matchMessage.matchState })</span>
	 </p>
	 </c:if>
<h1><a href="${pageContext.request.contextPath }/jcBasketballIndex/selectDataAnalysisRanking?day=${day }&weekId=${weekId}&teamId=${teamId}">排名</a><span>|</span><a class="tit-cur">历史</a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/selectDataAnalysisOuPei?day=${day }&weekId=${weekId}&teamId=${teamId}">欧指</a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/selectDataAnalysisLetScore?day=${day }&weekId=${weekId}&teamId=${teamId}">让分</a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/selectDataAnalysisTotalScore?day=${day }&weekId=${weekId}&teamId=${teamId}">总分</a></h1>
 <c:choose>
 	<c:when test="${'history_z10' eq type }">
 		 <h3><a class="tittwo-cur">主队近10场 </a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/selectDataAnalysisHistory?day=${day }&weekId=${weekId}&teamId=${teamId}&type=history_k10">客队近10场</a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/selectDataAnalysisHistory?day=${day }&weekId=${weekId}&teamId=${teamId}&type=history_z5">主队未来5场 </a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/selectDataAnalysisHistory?day=${day }&weekId=${weekId}&teamId=${teamId}&type=history_k5">客队未来5场</a></h3>
 	</c:when>
 	<c:when test="${'history_k10' eq type }">
 		 <h3><a href="${pageContext.request.contextPath }/jcBasketballIndex/selectDataAnalysisHistory?day=${day }&weekId=${weekId}&teamId=${teamId}&type=history_z10">主队近10场 </a><span>|</span><a class="tittwo-cur">客队近10场</a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/selectDataAnalysisHistory?day=${day }&weekId=${weekId}&teamId=${teamId}&type=history_z5">主队未来5场 </a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/selectDataAnalysisHistory?day=${day }&weekId=${weekId}&teamId=${teamId}&type=history_k5">客队未来5场</a></h3>
 	</c:when>
 	<c:when test="${'history_z5' eq type }">
 		 <h3><a href="${pageContext.request.contextPath }/jcBasketballIndex/selectDataAnalysisHistory?day=${day }&weekId=${weekId}&teamId=${teamId}&type=history_z10">主队近10场 </a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/selectDataAnalysisHistory?day=${day }&weekId=${weekId}&teamId=${teamId}&type=history_k10">客队近10场</a><span>|</span><a class="tittwo-cur">主队未来5场 </a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/selectDataAnalysisHistory?day=${day }&weekId=${weekId}&teamId=${teamId}&type=history_k5">客队未来5场</a></h3>
 	</c:when>
 	<c:when test="${'history_k5' eq type }">
 		 <h3><a href="${pageContext.request.contextPath }/jcBasketballIndex/selectDataAnalysisHistory?day=${day }&weekId=${weekId}&teamId=${teamId}&type=history_z10" >主队近10场 </a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/selectDataAnalysisHistory?day=${day }&weekId=${weekId}&teamId=${teamId}&type=history_k10">客队近10场</a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/selectDataAnalysisHistory?day=${day }&weekId=${weekId}&teamId=${teamId}&type=history_z5">主队未来5场 </a><span>|</span><a class="tittwo-cur">客队未来5场</a></h3>
 	</c:when>
 </c:choose>
   
<table class="jifen-table zucai-paihang" border="1" cellpadding="0" cellspacing="0" width="260" style="margin-left:0">
   		<tr bgcolor="#FDFCE0" class="tr-one">
        <td>赛事日期 </td>
            <td width="74">主队</td>
            <td>全场</td>
            <td width="74">客队</td>
            
		</tr>
		<c:if test="${not empty matchHistorys }" >
			<c:forEach items="${matchHistorys }" var="matchHistory" varStatus="history">
			<c:choose>
        		<c:when test="${(history.index+1)%2==0 }">
        			<tr bgcolor="#faf9f9">
        		</c:when>
        		<c:otherwise>
        			<tr >
        		</c:otherwise>
        	</c:choose>
        	<td>${matchHistory.matchTime }</td>
            <c:choose>
        		<c:when test="${matchHistory.homeTeamID eq matchMessage.homeTeamID }">
        			<td style="color:red">${matchHistory.homeTeam }</td>
        		</c:when>
        		<c:when test="${matchHistory.homeTeamID eq matchMessage.guestTeamID }">
        			<td style="color:blue">${matchHistory.homeTeam }</td>
        		</c:when>
        		<c:otherwise>
        			<td>${matchHistory.homeTeam }</td>
        		</c:otherwise>
        	</c:choose>
            <td>${matchHistory.homeScore }:${matchHistory.guestScore }</td>
                        <c:choose>
        		<c:when test="${matchHistory.guestTeamID eq matchMessage.homeTeamID }">
        			<td style="color:red">${matchHistory.guestTeam }</td>
        		</c:when>
        		<c:when test="${matchHistory.guestTeamID eq matchMessage.guestTeamID }">
        			<td style="color:blue">${matchHistory.guestTeam }</td>
        		</c:when>
        		<c:otherwise>
        			<td>${matchHistory.guestTeam }</td>
        		</c:otherwise>
        	</c:choose>
  		</tr>
			</c:forEach>
		</c:if>
       
</table>