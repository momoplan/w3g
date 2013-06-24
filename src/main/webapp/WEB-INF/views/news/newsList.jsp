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
    <div class="wap-pro-zixun">
    	<c:choose>
    		<c:when test="${not empty newsList }">
    			<c:forEach items="${newsList }" var="news">
    				<p><span>·</span><a href="${pageContext.request.contextPath }/news/selectNews?id=${news.id}&type=${type}">${news.vol_title }</a></p>
    			</c:forEach>
    			<p class="user-pages">
    			<c:if test="${nowPage >1 }">
					<a href="${pageContext.request.contextPath }/news/selectNewsList?type=${type}&nowPage=${nowPage-1}&startLine=${(nowPage-2)*maxLine}&endLine=${(nowPage-1)*maxLine}">上一页</a>
    			</c:if>
    			<c:choose>
    				<c:when test="${totalPage>1&&(totalPage<5 || totalPage==5) }">
    					<c:forEach var="item" varStatus="status" begin="1" end="${totalPage }">
    						<c:choose>
    							<c:when test="${status.index ==nowPage }">
    								<a class="cur-page">${ status.index}</a>
    							</c:when>
    							<c:otherwise>
    								<a href="${pageContext.request.contextPath }/news/selectNewsList?type=${type}&nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    							</c:otherwise>
    						
    						</c:choose> 
						</c:forEach> 
    				</c:when>
    				<c:when test="${totalPage>5}">
    					<c:choose>
    						<c:when test="${nowPage<totalPage-3 }">
    							<c:choose>
    								<c:when test="${nowPage>3 }">
    									<a href="${pageContext.request.contextPath }/news/selectNewsList?type=${type}&nowPage=1&startLine=0&endLine=${maxLine}">1</a><a 
    						   		   	  >…</a><a 
    						   		       href="${pageContext.request.contextPath }/news/selectNewsList?type=${type}&nowPage=${nowPage-1}&startLine=${(nowPage-3)*maxLine}&endLine=${(nowPage-2)*maxLine}">${nowPage-1 }</a><a class="cur-page">${nowPage }</a><a 
    						   		       href="${pageContext.request.contextPath }/news/selectNewsList?type=${type}&nowPage=${nowPage+1}&startLine=${(nowPage)*maxLine}&endLine=${(nowPage+1)*maxLine}">${nowPage+1 }</a><a 
    						   		      >…</a><a 
    						   		       href="${pageContext.request.contextPath }/news/selectNewsList?type=${type}&nowPage=${totalPage}&startLine=${(totalPage-1)*maxLine}&endLine=${totalPage*maxLine}">${totalPage }</a>
    								</c:when>
    								<c:otherwise>
    									<c:forEach var="item" varStatus="status" begin="1" end="5">
    										<c:choose>
    											<c:when test="${status.index ==nowPage }">
    												<a class="cur-page">${ status.index}</a>
    											</c:when>
    											<c:otherwise>
    												<a href="${pageContext.request.contextPath }/news/selectNewsList?type=${type}&nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    											</c:otherwise>
    										</c:choose> 
										</c:forEach>
										<a>…</a><a 
    						   		   	   href="${pageContext.request.contextPath }/news/selectNewsList?type=${type}&nowPage=${totalPage}&startLine=${(totalPage-1)*maxLine}&endLine=${totalPage*maxLine}">${totalPage }</a>
    								</c:otherwise>
    							</c:choose>
    						</c:when>
    						<c:otherwise>
    							<a href="${pageContext.request.contextPath }/news/selectNewsList?type=${type}&nowPage=1&startLine=0&endLine=${maxLine}">1</a><a 
    						   	  >…</a>
    							<c:forEach var="item" varStatus="status" begin="${totalPage-4 }" end="${totalPage }">
    								<c:choose>
    									<c:when test="${status.index ==nowPage }">
    										<a class="cur-page">${ status.index}</a>
    									</c:when>
    									<c:otherwise>
    									<a href="${pageContext.request.contextPath }/news/selectNewsList?type=${type}&nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    									</c:otherwise>
    								</c:choose> 
								</c:forEach>
    						</c:otherwise>
    					</c:choose>
    				</c:when>
    			</c:choose>
    			<c:if test="${totalPage >nowPage }">
    				<a href="${pageContext.request.contextPath }/news/selectNewsList?type=${type}&nowPage=${nowPage+1}&startLine=${nowPage*maxLine}&endLine=${(nowPage+1)*maxLine}">下一页</a>
    			</c:if>
    			</p>
    		</c:when>
    		<c:when test="${not empty activityNewsList }">
    			<c:forEach items="${activityNewsList }" var="activityNews">
    				<p><span>·</span><a href="${pageContext.request.contextPath }/news/selectActivityNews?id=${activityNews.id}&type=${type}">${activityNews.title }</a></p>
    			</c:forEach>
    			<p class="user-pages">
    			<c:if test="${nowPage >1 }">
					<a href="${pageContext.request.contextPath }/news/selectActivityNewsList?type=${type}&nowPage=${nowPage-1}&startLine=${(nowPage-2)*maxLine}&endLine=${(nowPage-1)*maxLine}">上一页</a>
    			</c:if>
    			<c:choose>
    				<c:when test="${totalPage>1&&(totalPage<5 || totalPage==5) }">
    					<c:forEach var="item" varStatus="status" begin="1" end="${totalPage }">
    						<c:choose>
    							<c:when test="${status.index ==nowPage }">
    								<a class="cur-page">${ status.index}</a>
    							</c:when>
    							<c:otherwise>
    								<a href="${pageContext.request.contextPath }/news/selectActivityNewsList?type=${type}&nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    							</c:otherwise>
    						</c:choose> 
						</c:forEach> 
    				</c:when>
    				<c:when test="${totalPage>5}">
    					<c:choose>
    						<c:when test="${nowPage<totalPage-3 }">
    							<c:choose>
    								<c:when test="${nowPage>3 }">
    									<a href="${pageContext.request.contextPath }/news/selectActivityNewsList?type=${type}&nowPage=1&startLine=0&endLine=${maxLine}">1</a><a 
    						   		   	  >…</a><a 
    						   		       href="${pageContext.request.contextPath }/news/selectActivityNewsList?type=${type}&nowPage=${nowPage-1}&startLine=${(nowPage-3)*maxLine}&endLine=${(nowPage-2)*maxLine}">${nowPage-1 }</a><a class="cur-page">${nowPage }</a><a 
    						   		       href="${pageContext.request.contextPath }/news/selectActivityNewsList?type=${type}&nowPage=${nowPage+1}&startLine=${(nowPage)*maxLine}&endLine=${(nowPage+1)*maxLine}">${nowPage+1 }</a><a 
    						   		      >…</a><a 
    						   		       href="${pageContext.request.contextPath }/news/selectActivityNewsList?type=${type}&nowPage=${totalPage}&startLine=${(totalPage-1)*maxLine}&endLine=${totalPage*maxLine}">${totalPage }</a>
    								</c:when>
    								<c:otherwise>
    									<c:forEach var="item" varStatus="status" begin="1" end="5">
    										<c:choose>
    											<c:when test="${status.index ==nowPage }">
    												<a class="cur-page">${ status.index}</a>
    											</c:when>
    											<c:otherwise>
    												<a href="${pageContext.request.contextPath }/news/selectActivityNewsList?type=${type}&nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    											</c:otherwise>
    										</c:choose> 
										</c:forEach>
										<a>…</a><a 
    						   		   	   href="${pageContext.request.contextPath }/news/selectActivityNewsList?type=${type}&nowPage=${totalPage}&startLine=${(totalPage-1)*maxLine}&endLine=${totalPage*maxLine}">${totalPage }</a>
    								</c:otherwise>
    							</c:choose>
    						</c:when>
    						<c:otherwise>
    							<a href="${pageContext.request.contextPath }/news/selectActivityNewsList?type=${type}&nowPage=1&startLine=0&endLine=${maxLine}">1</a><a 
    						   	  >…</a>
    							<c:forEach var="item" varStatus="status" begin="${totalPage-4 }" end="${totalPage }">
    								<c:choose>
    									<c:when test="${status.index ==nowPage }">
    										<a class="cur-page">${ status.index}</a>
    									</c:when>
    								 	<c:otherwise>
    										<a href="${pageContext.request.contextPath }/news/selectActivityNewsList?type=${type}&nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    									</c:otherwise>
    								</c:choose> 
								</c:forEach>
    						</c:otherwise>
    					</c:choose>
    				</c:when>
    			</c:choose>
    			<c:if test="${totalPage >nowPage }">
    				<a href="${pageContext.request.contextPath }/news/selectActivityNewsList?type=${type}&nowPage=${nowPage+1}&startLine=${nowPage*maxLine}&endLine=${(nowPage+1)*maxLine}">下一页</a>
    			</c:if>
    			</p>
    		</c:when>
    	</c:choose>
    </div>
