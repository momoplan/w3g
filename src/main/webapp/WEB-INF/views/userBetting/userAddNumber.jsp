<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span>追号查询</span></p>
    </div>
     <div class="wap-pro">
    	<p>日期格式：yyyymmdd（如20120212）</p>
        <p>解&nbsp;&nbsp;&nbsp;&nbsp;释：2012年02月12日</p>
        <p class="prompt-pro">${messageError }</p>
        <form action="${pageContext.request.contextPath }/select/userAddNumber.do" method="get">
        	 <p>起始时间：<input name="beginTime" type="text" value="${beginTime }"/></p>
        	 <p>终止时间：<input name="endTime" type="text" value="${endTime }"/></p>
        	 <p><input type="submit" value="查&nbsp;询" class="user-check-btn" /></p>
        </form>
    </div>
    <div class="xuxian-line">&nbsp;</div>
    <c:if test="${not empty orders }">
    	<c:forEach items="${orders }" var="order">
    		<div class="user-moneychange">
    			<p>彩种：${order.lotName }</p>
    			<p>追号期数：<span>${order.batchNum }</span>期</p>
        		<p>追号金额：<span>${order.totalAmount }元</span></p>
        		<p>已追期数：<span>${order.nowNum }</span>期</p>
        		<p>当前状态：${order.state }</p>
        		<form action="${pageContext.request.contextPath }/select/userAddNumberDetail.do" method="post">
        			<input type="hidden" name="flowNo" value="${order.flowNo }"/>
        			<input type="hidden" name="state" value="${order.state }"/>
        			<input type="hidden" name="prizeend" value="${order.prizeend }"/>
        			<p><input type="submit" value="查看详情" class="tz-chakan-btn" /></p>
        		</form>
    		</div>
    	</c:forEach>
    	<p class="user-pages">
   				<c:if test="${nowPage >1 }">
					<a href="${pageContext.request.contextPath }/select/userAddNumber.do?beginTime=${beginTime }&endTime=${endTime }&nowPage=${nowPage-1}&startLine=${(nowPage-2)*maxLine}&endLine=${(nowPage-1)*maxLine}">上一页</a>
    			</c:if>
    			<c:choose>
    				<c:when test="${totalPage>1&&(totalPage<5 || totalPage==5) }">
    					<c:forEach var="item" varStatus="status" begin="1" end="${totalPage }">
    						<c:choose>
    							<c:when test="${status.index ==nowPage }">
    								<a class="cur-page">${ status.index}</a>
    							</c:when>
    							<c:otherwise>
    								<a href="${pageContext.request.contextPath }/select/userAddNumber.do?beginTime=${beginTime }&endTime=${endTime }&nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    							</c:otherwise>
    						
    						</c:choose> 
						</c:forEach> 
    				</c:when>
    				<c:when test="${totalPage>5}">
    					<c:choose>
    						<c:when test="${nowPage<totalPage-3 }">
    							<c:choose>
    								<c:when test="${nowPage>3 }">
    									<a href="${pageContext.request.contextPath }/select/userAddNumber.do?beginTime=${beginTime }&endTime=${endTime }&nowPage=1&startLine=0&endLine=${maxLine}">1</a><a 
    						   		   	  >…</a><a 
    						   		       href="${pageContext.request.contextPath }/select/userAddNumber.do?beginTime=${beginTime }&endTime=${endTime }&nowPage=${nowPage-1}&startLine=${(nowPage-3)*maxLine}&endLine=${(nowPage-2)*maxLine}">${nowPage-1 }</a><a class="cur-page">${nowPage }</a><a 
    						   		       href="${pageContext.request.contextPath }/select/userAddNumber.do?beginTime=${beginTime }&endTime=${endTime }&nowPage=${nowPage+1}&startLine=${(nowPage)*maxLine}&endLine=${(nowPage+1)*maxLine}">${nowPage+1 }</a><a 
    						   		      >…</a><a 
    						   		       href="${pageContext.request.contextPath }/select/userAddNumber.do?beginTime=${beginTime }&endTime=${endTime }&nowPage=${totalPage}&startLine=${(totalPage-1)*maxLine}&endLine=${totalPage*maxLine}">${totalPage }</a>
    								</c:when>
    								<c:otherwise>
    									<c:forEach var="item" varStatus="status" begin="1" end="5">
    										<c:choose>
    											<c:when test="${status.index ==nowPage }">
    												<a class="cur-page">${ status.index}</a>
    											</c:when>
    											<c:otherwise>
    												<a href="${pageContext.request.contextPath }/select/userAddNumber.do?beginTime=${beginTime }&endTime=${endTime }&nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    											</c:otherwise>
    										</c:choose> 
										</c:forEach>
										<a>…</a><a 
    						   		   	   href="${pageContext.request.contextPath }/select/userAddNumber.do?beginTime=${beginTime }&endTime=${endTime }&nowPage=${totalPage}&startLine=${(totalPage-1)*maxLine}&endLine=${totalPage*maxLine}">${totalPage }</a>
    								</c:otherwise>
    							</c:choose>
    						</c:when>
    						<c:otherwise>
    							<a href="${pageContext.request.contextPath }/select/userAddNumber.do?beginTime=${beginTime }&endTime=${endTime }&nowPage=1&startLine=0&endLine=${maxLine}">1</a><a 
    						   	  >…</a>
    							<c:forEach var="item" varStatus="status" begin="${totalPage-4 }" end="${totalPage }">
    								<c:choose>
    									<c:when test="${status.index ==nowPage }">
    										<a class="cur-page">${ status.index}</a>
    									</c:when>
    									<c:otherwise>
    									<a href="${pageContext.request.contextPath }/select/userAddNumber.do?beginTime=${beginTime }&endTime=${endTime }&nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    									</c:otherwise>
    								</c:choose> 
								</c:forEach>
    						</c:otherwise>
    					</c:choose>
    				</c:when>
    			</c:choose>
    			<c:if test="${totalPage >nowPage }">
    				<a href="${pageContext.request.contextPath }/select/userAddNumber.do?beginTime=${beginTime }&endTime=${endTime }&nowPage=${nowPage+1}&startLine=${nowPage*maxLine}&endLine=${(nowPage+1)*maxLine}">下一页</a>
    			</c:if>
    		</p>
    </c:if>