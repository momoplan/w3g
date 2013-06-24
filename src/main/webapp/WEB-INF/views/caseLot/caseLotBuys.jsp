<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span>合买查询</span></p>
  </div>
  <c:if test="${not empty caseLots }">
    <c:forEach items="${caseLots }" var="caseLot">
    	<div class="user-moneychange">
    		<p>发起人：${caseLot.starter }</p>
    		<p>彩种：${caseLot.lotName }</p>
        	<p>状态：${caseLot.displayStateMemo }</p>
        	<p>投注时间：${caseLot.buyTime }</p>
        	<p>认购金额：<span>${caseLot.buyAmt }元</span></p>
        	<form action="${pageContext.request.contextPath }/caseLotCenter/selectCaseLotBuysDetail.do" method="post">
        		<input type="hidden" name="caseLotId" value="${caseLot.caseLotId }"/>
        	   <p><input type="submit" value="查看详情" class="tz-chakan-btn" /></p>
        	</form>
    	</div>
    </c:forEach>
	<c:if test="${totalPage>1 }">
		<p class="user-pages">
   		<c:if test="${nowPage >1 }">
					<a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLotBuys.do?nowPage=${nowPage-1}&startLine=${(nowPage-2)*maxLine}&endLine=${(nowPage-1)*maxLine}">上一页</a>
    			</c:if>
    			<c:choose>
    				<c:when test="${totalPage>1&&(totalPage<5 || totalPage==5) }">
    					<c:forEach var="item" varStatus="status" begin="1" end="${totalPage }">
    						<c:choose>
    							<c:when test="${status.index ==nowPage }">
    								<a class="cur-page">${ status.index}</a>
    							</c:when>
    							<c:otherwise>
    								<a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLotBuys.do?nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    							</c:otherwise>
    						
    						</c:choose> 
						</c:forEach> 
    				</c:when>
    				<c:when test="${totalPage>5}">
    					<c:choose>
    						<c:when test="${nowPage<totalPage-3 }">
    							<c:choose>
    								<c:when test="${nowPage>3 }">
    									<a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLotBuys.do?nowPage=1&startLine=0&endLine=${maxLine}">1</a><a 
    						   		   	  >…</a><a 
    						   		       href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLotBuys.do?nowPage=${nowPage-1}&startLine=${(nowPage-3)*maxLine}&endLine=${(nowPage-2)*maxLine}">${nowPage-1 }</a><a class="cur-page">${nowPage }</a><a 
    						   		       href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLotBuys.do?nowPage=${nowPage+1}&startLine=${(nowPage)*maxLine}&endLine=${(nowPage+1)*maxLine}">${nowPage+1 }</a><a 
    						   		      >…</a><a 
    						   		       href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLotBuys.do?nowPage=${totalPage}&startLine=${(totalPage-1)*maxLine}&endLine=${totalPage*maxLine}">${totalPage }</a>
    								</c:when>
    								<c:otherwise>
    									<c:forEach var="item" varStatus="status" begin="1" end="5">
    										<c:choose>
    											<c:when test="${status.index ==nowPage }">
    												<a class="cur-page">${ status.index}</a>
    											</c:when>
    											<c:otherwise>
    												<a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLotBuys.do?nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    											</c:otherwise>
    										</c:choose> 
										</c:forEach>
										<a>…</a><a 
    						   		   	   href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLotBuys.do?nowPage=${totalPage}&startLine=${(totalPage-1)*maxLine}&endLine=${totalPage*maxLine}">${totalPage }</a>
    								</c:otherwise>
    							</c:choose>
    						</c:when>
    						<c:otherwise>
    							<a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLotBuys.do?nowPage=1&startLine=0&endLine=${maxLine}">1</a><a 
    						   	  >…</a>
    							<c:forEach var="item" varStatus="status" begin="${totalPage-4 }" end="${totalPage }">
    								<c:choose>
    									<c:when test="${status.index ==nowPage }">
    										<a class="cur-page">${ status.index}</a>
    									</c:when>
    									<c:otherwise>
    									<a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLotBuys.do?nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    									</c:otherwise>
    								</c:choose> 
								</c:forEach>
    						</c:otherwise>
    					</c:choose>
    				</c:when>
    			</c:choose>
    			<c:if test="${totalPage >nowPage }">
    				<a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLotBuys.do?nowPage=${nowPage+1}&startLine=${nowPage*maxLine}&endLine=${(nowPage+1)*maxLine}">下一页</a>
    			</c:if>
    		</p>
</c:if>

</c:if>
