 <%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><strong><a href="${pageContext.request.contextPath }/jcFootballIndex/byJcFootBall?type=1&valueType=1&wanFa=FT001">竞彩足球</a>-</strong><span>数据分析</span></p>
    </div>
    <c:if test="${not empty matchMessage }">
     <p class="h1-bot" style=" line-height:22px; border-bottom:none;">
     	${matchMessage.homeTeam }(主) vs ${matchMessage.guestTeam }<br />
     	<span style="font-weight:normal;">${matchMessage.sclassName_j } ${matchMessage.matchTime } (${matchMessage.matchState })</span>
	 </p>
	 </c:if>
<h1><a href="${pageContext.request.contextPath }/jcFootballIndex/selectDataAnalysisRanking?day=${day }&weekId=${weekId}&teamId=${teamId}">排名</a><span>|</span><a href="${pageContext.request.contextPath }/jcFootballIndex/selectDataAnalysisHistory?day=${day }&weekId=${weekId}&teamId=${teamId}&type=history_z10">历史</a><span>|</span><a href="${pageContext.request.contextPath }/jcFootballIndex/selectDataAnalysisOuPei?day=${day }&weekId=${weekId}&teamId=${teamId}">欧指</a><span>|</span><a class="tit-cur">亚盘</a></h1>
<table class="jifen-table zucai-paihang" border="1" cellpadding="0" cellspacing="0" width="260" style="margin-left:0">
   		<tr bgcolor="#FDFCE0" class="tr-one">
        	<td>公司</td>
            <td>初盘胜水位<br />初盘亚盘<br />初盘负水位</td>
            <td width="120">即时受注盘胜水位<br />即时受注盘亚盘<br />即时受注盘负水盘</td>
		</tr>
		<c:if test="${not empty matchYaPans }">
			<c:forEach items="${matchYaPans }" var="matchYaPan" varStatus="yaPan">
			<c:choose>
        		<c:when test="${(yaPan.index+1)%2==0 }">
        			<tr bgcolor="#faf9f9">
        		</c:when>
        		<c:otherwise>
        			<tr >
        		</c:otherwise>
        	</c:choose>
        		<td>${matchYaPan.companyName }</td>
            	<td> ${matchYaPan.firstUpodds }<br />${matchYaPan.firstGoal_name }<br />${matchYaPan.firstDownodds }</td>
            	<td> ${matchYaPan.upOdds }<br />${matchYaPan.goal_name }<br />${matchYaPan.downOdds }</td>
				</tr>
        	
        	</c:forEach>
		</c:if>
       
</table>