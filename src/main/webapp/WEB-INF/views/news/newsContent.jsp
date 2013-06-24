<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><span>资讯中心</span></p>
    </div>
    <h1>
    <c:choose>
    	<c:when test="${'1' eq type }">
    		<a href="${pageContext.request.contextPath }/news/selectNewsList?type=1" class="tit-cur">彩民趣闻</a>
    	</c:when>
    	<c:otherwise>
    		<a href="${pageContext.request.contextPath }/news/selectNewsList?type=1">彩民趣闻</a>
    	</c:otherwise>
    </c:choose>
    <span>|</span>
     <c:choose>
    	<c:when test="${'2' eq type }">
    		<a href="${pageContext.request.contextPath }/news/selectNewsList?type=2" class="tit-cur">专家推荐</a>
    	</c:when>
    	<c:otherwise>
    		<a href="${pageContext.request.contextPath }/news/selectNewsList?type=2">专家推荐</a>
    	</c:otherwise>
    </c:choose>
    <span>|</span>
     <c:choose>
    	<c:when test="${'3' eq type }">
    		<a href="${pageContext.request.contextPath }/news/selectNewsList?type=3" class="tit-cur">足彩天地</a>
    	</c:when>
    	<c:otherwise>
    		<a href="${pageContext.request.contextPath }/news/selectNewsList?type=3">足彩天地</a>
    	</c:otherwise>
    </c:choose>
    <span>|</span>
    <c:choose>
    	<c:when test="${'6' eq type }">
    		<a href="${pageContext.request.contextPath }/news/selectActivityNewsList?type=6" class="tit-cur">如意活动</a>
    	</c:when>
    	<c:otherwise>
    		<a href="${pageContext.request.contextPath }/news/selectActivityNewsList?type=6">如意活动</a>
    	</c:otherwise>
    </c:choose>
    <span>|</span>
    <c:choose>
    	<c:when test="${'4' eq type }">
    		<a href="${pageContext.request.contextPath }/news/selectNewsList?type=4" class="tit-cur">如意公告</a>
    	</c:when>
    	<c:otherwise>
    		<a href="${pageContext.request.contextPath }/news/selectNewsList?type=4">如意公告</a>
    	</c:otherwise>
    </c:choose>
    </h1>
    <div class="wap-pro" style="padding-left:1px">
    	<c:choose>
    		<c:when test="${not empty news }">
    			<h3 class="helpcenter-title">${news.vol_title }</h3>
    			<p class="news-article">${news.createtime }</p>
        		<p class="helpcenter-article">${news.vol_content }</p>
    		</c:when>
    		<c:when test="${not empty activityNews }">
    			<h3 class="helpcenter-title">${activityNews.title }</h3>
    			<p class="news-article">${activityNews.createtime }</p>
        		<p class="helpcenter-article">${activityNews.content }</p>
    		</c:when>
    	</c:choose>
   </div>