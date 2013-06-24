 <%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><strong><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=1&wanFa=BSK001">竞彩篮球</a>-</strong><span>数据分析</span></p>
    </div>
    
     <p class="h1-bot" style=" line-height:22px; border-bottom:none;">
     	${matchMessage.homeTeam }(主) vs ${matchMessage.guestTeam }<br />
     	<span style="font-weight:normal;">${matchMessage.sclassShortName } ${matchMessage.matchTime } (${matchMessage.matchState })</span>
	 </p>
<h1><a href="${pageContext.request.contextPath }/jcBasketballIndex/selectDataAnalysisRanking?day=${day }&weekId=${weekId}&teamId=${teamId}">排名</a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/selectDataAnalysisHistory?day=${day }&weekId=${weekId}&teamId=${teamId}&type=history_z10">历史</a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/selectDataAnalysisOuPei?day=${day }&weekId=${weekId}&teamId=${teamId}">欧指</a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/selectDataAnalysisLetScore?day=${day }&weekId=${weekId}&teamId=${teamId}">让分</a><span>|</span><a  class="tit-cur">总分</a></h1>
<table class="jifen-table zucai-paihang" border="1" cellpadding="0" cellspacing="0" width="260" style="margin-left:0">
   		<!-- <tr bgcolor="#FDFCE0" class="tr-one">
        	<td>公司</td>
            <td width="60">初盘<br />即时盘</td>
            <td width="50">大分</td>
            <td width="50">总分</td>
            <td width="50">小分</td>
            
		</tr> -->
		<c:if test="${not empty matchTotalScores }" >
			<c:forEach items="${matchTotalScores }" var="matchTotalScore" varStatus="score">
			<c:choose>
        		<c:when test="${(score.index+1)%2==0 }">
        			<tr bgcolor="#faf9f9">
        		</c:when>
        		<c:otherwise>
        			<tr >
        		</c:otherwise>
        	</c:choose>
        	<td>${matchTotalScore.companyName }</td>
            <td>初盘<br />即时盘</td>
            <td>${matchTotalScore.firstUpodds }<br />${matchTotalScore.upOdds }</td>
            <td>${matchTotalScore.firstGoal }<br />${matchTotalScore.goal }</td>
            <td>${matchTotalScore.firstDownodds }<br />${matchTotalScore.downOdds }</td>
  		</tr>
			</c:forEach>
		</c:if>
</table>