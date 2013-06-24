<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span>中奖查询</span></p>
    </div>
    <c:if test="${not empty orders }">
    	<c:forEach items="${orders }" var="order">
    		<form action="${pageContext.request.contextPath }/select/userWinningDetail.do" method="post">
    			<div class="user-moneychange">
    				<p>彩种：${order.lotName }</p>
    				<c:if test="${not empty order.batchCode && 'null' != order.batchCode }">
    					<p>期号：<span>${order.batchCode }</span></p>
    				</c:if>
        			<p>金额：<span>${order.amount }元</span></p>
        			<p>中奖金额：<span>${order.orderPrize }元</span></p>
        			<input type="hidden" name="lotName" value="${order.lotName }"/>
        			<input type="hidden" name="batchCode" value="${order.batchCode }"/>
        			<input type="hidden" name="orderId" value="${order.orderId }"/>
        			<input type="hidden" name="createTime" value="${order.createTime }"/>
        			<input type="hidden" name="encashTime" value="${order.encashTime }"/>
        			<input type="hidden" name="beiShu" value="${order.beiShu }"/>
        			<input type="hidden" name="zhuShu" value="${order.zhuShu }"/>
        			<input type="hidden" name="amount" value="${order.amount }"/>
        			<input type="hidden" name="lotNo" value="${order.lotNo }"/>
        			<input type="hidden" name="betCode" value="${order.betCode }"/>
        			<input type="hidden" name="orderPrize" value="${order.orderPrize }"/>
        			<input type="hidden" name="winBaseCode" value="${order.winBaseCode }"/>
        			<p><input type="submit" value="查看详情" class="tz-chakan-btn" /></p>
        		</div>
    		</form>
    	</c:forEach>
    	<p class="user-pages">
   				<c:if test="${nowPage >1 }">
					<a href="${pageContext.request.contextPath }/select/userWinning.do?nowPage=${nowPage-1}&startLine=${(nowPage-2)*maxLine}&endLine=${(nowPage-1)*maxLine}">上一页</a>
    			</c:if>
    			<c:choose>
    				<c:when test="${totalPage>1&&(totalPage<5 || totalPage==5) }">
    					<c:forEach var="item" varStatus="status" begin="1" end="${totalPage }">
    						<c:choose>
    							<c:when test="${status.index ==nowPage }">
    								<a class="cur-page">${ status.index}</a>
    							</c:when>
    							<c:otherwise>
    								<a href="${pageContext.request.contextPath }/select/userWinning.do?nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    							</c:otherwise>
    						
    						</c:choose> 
						</c:forEach> 
    				</c:when>
    				<c:when test="${totalPage>5}">
    					<c:choose>
    						<c:when test="${nowPage<totalPage-3 }">
    							<c:choose>
    								<c:when test="${nowPage>3 }">
    									<a href="${pageContext.request.contextPath }/select/userWinning.do?nowPage=1&startLine=0&endLine=${maxLine}">1</a><a 
    						   		   	  >…</a><a 
    						   		       href="${pageContext.request.contextPath }/select/userWinning.do?nowPage=${nowPage-1}&startLine=${(nowPage-3)*maxLine}&endLine=${(nowPage-2)*maxLine}">${nowPage-1 }</a><a class="cur-page">${nowPage }</a><a 
    						   		       href="${pageContext.request.contextPath }/select/userWinning.do?nowPage=${nowPage+1}&startLine=${(nowPage)*maxLine}&endLine=${(nowPage+1)*maxLine}">${nowPage+1 }</a><a 
    						   		      >…</a><a 
    						   		       href="${pageContext.request.contextPath }/select/userWinning.do?nowPage=${totalPage}&startLine=${(totalPage-1)*maxLine}&endLine=${totalPage*maxLine}">${totalPage }</a>
    								</c:when>
    								<c:otherwise>
    									<c:forEach var="item" varStatus="status" begin="1" end="5">
    										<c:choose>
    											<c:when test="${status.index ==nowPage }">
    												<a class="cur-page">${ status.index}</a>
    											</c:when>
    											<c:otherwise>
    												<a href="${pageContext.request.contextPath }/select/userWinning.do?nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    											</c:otherwise>
    										</c:choose> 
										</c:forEach>
										<a>…</a><a 
    						   		   	   href="${pageContext.request.contextPath }/select/userWinning.do?nowPage=${totalPage}&startLine=${(totalPage-1)*maxLine}&endLine=${totalPage*maxLine}">${totalPage }</a>
    								</c:otherwise>
    							</c:choose>
    						</c:when>
    						<c:otherwise>
    							<a href="${pageContext.request.contextPath }/select/userWinning.do?nowPage=1&startLine=0&endLine=${maxLine}">1</a><a 
    						   	  >…</a>
    							<c:forEach var="item" varStatus="status" begin="${totalPage-4 }" end="${totalPage }">
    								<c:choose>
    									<c:when test="${status.index ==nowPage }">
    										<a class="cur-page">${ status.index}</a>
    									</c:when>
    									<c:otherwise>
    									<a href="${pageContext.request.contextPath }/select/userWinning.do?nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    									</c:otherwise>
    								</c:choose> 
								</c:forEach>
    						</c:otherwise>
    					</c:choose>
    				</c:when>
    			</c:choose>
    			<c:if test="${totalPage >nowPage }">
    				<a href="${pageContext.request.contextPath }/select/userWinning.do?nowPage=${nowPage+1}&startLine=${nowPage*maxLine}&endLine=${(nowPage+1)*maxLine}">下一页</a>
    			</c:if>
    		</p>
    </c:if>