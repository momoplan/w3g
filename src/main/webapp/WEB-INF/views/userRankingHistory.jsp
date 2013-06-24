<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	    <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><span>中奖排行</span></p>
    </div>
    <h1>
    <c:choose>
    	<c:when test="${'5' eq type }">
    		<a href="${pageContext.request.contextPath }/winningRankingHistory/userRankingHistory?type=5" class="tit-cur">总排行</a>
    	</c:when>
    	<c:otherwise>
    		<a href="${pageContext.request.contextPath }/winningRankingHistory/userRankingHistory?type=5" >总排行</a>
    	</c:otherwise>
    </c:choose>
    <span>|</span>
    <c:choose>
    	<c:when test="${'4' eq type }">
    		<a href="${pageContext.request.contextPath }/winningRankingHistory/userRankingHistory?type=4" class="tit-cur">年排行</a>
    	</c:when>
    	<c:otherwise>
    		<a href="${pageContext.request.contextPath }/winningRankingHistory/userRankingHistory?type=4" >年排行</a>
    	</c:otherwise>
    </c:choose>
    <span>|</span>
    <c:choose>
    	<c:when test="${'3' eq type }">
    		<a href="${pageContext.request.contextPath }/winningRankingHistory/userRankingHistory?type=3" class="tit-cur">月排行</a>
    	</c:when>
    	<c:otherwise>
    		<a href="${pageContext.request.contextPath }/winningRankingHistory/userRankingHistory?type=3" >月排行</a>
    	</c:otherwise>
    </c:choose>
    <span>|</span>
    <c:choose>
    	<c:when test="${'2' eq type }">
    		<a href="${pageContext.request.contextPath }/winningRankingHistory/userRankingHistory?type=2" class="tit-cur">周排行</a>
    	</c:when>
    	<c:otherwise>
    		<a href="${pageContext.request.contextPath }/winningRankingHistory/userRankingHistory?type=2" >周排行</a>
    	</c:otherwise>
    </c:choose></h1>
    <table width="245" border="1" cellspacing="0" cellpadding="0" class="jifen-table paihang-table">
    <c:if test="${not empty userRankingHistoryList }">
    	<c:forEach items="${userRankingHistoryList }" var="userRanking" varStatus="userRankingIndex">
    		<tr>
    			<td>${userRankingIndex.index+1 }</td>
    			<td>${userRanking.nickName }</td>
    			<td><span>${userRanking.prizeAmt }</span>元</td>
  			</tr>
    	</c:forEach>
    </c:if>
</table>
   