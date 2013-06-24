<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  	<div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span>账户提现</span></p>
    </div>
    <div class="wap-pro-gray tixian"><a href="${pageContext.request.contextPath }/drawCash/findDNAtoCash.do">账户提现</a><span>|</span><a class="tixian-cur">提现记录</a></div>
    <c:if test="${not empty drawCashList }">
    	<c:forEach items="${drawCashList }" var="cash">
    		<div class="user-moneychange">
    			<p>提现金额:<span>${cash.amt  }</span>元</p>
        		<p>提现时间:${cash.platTime }</p>
        		<c:choose>
        			<c:when test="${cash.state eq '1' }">
        				<p>提现状态:<span class="a-2">待审核</span><a href="${pageContext.request.contextPath }/drawCash/cancelDrawCash.do?id=${cash.id }" class="cx">撤销提现</a></p>
        			</c:when>
        			<c:when test="${cash.state eq '103' ||cash.state eq '108'||cash.state eq '109'||cash.state eq '110'}">
        				<p>提现状态:<span class="a-1">已审核</span></p>
        			</c:when>
        			<c:when test="${cash.state eq '105' }">
        				<p>提现状态:<span class="a-1">成功</span></p>
        			</c:when>
        			<c:when test="${cash.state eq '106' }">
        				<p>提现状态:<span class="a-4">已取消</span></p>
        			</c:when>
        			<c:when test="${cash.state eq '104' }">
        				<p>提现状态:<span class="a-3">驳回</span>
        				<c:if test="${!cash.rejectReason eq 'null' }">
        					${cash.rejectReason }
        				</c:if>
        				</p>
        			</c:when>
        		</c:choose>
        		
  			</div>
    	</c:forEach>
    	<p class="user-pages">
   	<c:if test="${nowPage >1 }">
					<a href="${pageContext.request.contextPath }/drawCash/drawCashHistory.do?nowPage=${nowPage-1}&startLine=${(nowPage-2)*maxLine}&endLine=${(nowPage-1)*maxLine}">上一页</a>
    			</c:if>
    			<c:choose>
    				<c:when test="${totalPage>1&&(totalPage<5 || totalPage==5) }">
    					<c:forEach var="item" varStatus="status" begin="1" end="${totalPage }">
    						<c:choose>
    							<c:when test="${status.index ==nowPage }">
    								<a class="cur-page">${ status.index}</a>
    							</c:when>
    							<c:otherwise>
    								<a href="${pageContext.request.contextPath }/drawCash/drawCashHistory.do?nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    							</c:otherwise>
    						
    						</c:choose> 
						</c:forEach> 
    				</c:when>
    				<c:when test="${totalPage>5}">
    					<c:choose>
    						<c:when test="${nowPage<totalPage-3 }">
    							<c:choose>
    								<c:when test="${nowPage>3 }">
    									<a href="${pageContext.request.contextPath }/drawCash/drawCashHistory.do?nowPage=1&startLine=0&endLine=${maxLine}">1</a><a 
    						   		   	  >…</a><a 
    						   		       href="${pageContext.request.contextPath }/drawCash/drawCashHistory.do?nowPage=${nowPage-1}&startLine=${(nowPage-3)*maxLine}&endLine=${(nowPage-2)*maxLine}">${nowPage-1 }</a><a class="cur-page">${nowPage }</a><a 
    						   		       href="${pageContext.request.contextPath }/drawCash/drawCashHistory.do?nowPage=${nowPage+1}&startLine=${(nowPage)*maxLine}&endLine=${(nowPage+1)*maxLine}">${nowPage+1 }</a><a 
    						   		      >…</a><a 
    						   		       href="${pageContext.request.contextPath }/drawCash/drawCashHistory.do?nowPage=${totalPage}&startLine=${(totalPage-1)*maxLine}&endLine=${totalPage*maxLine}">${totalPage }</a>
    								</c:when>
    								<c:otherwise>
    									<c:forEach var="item" varStatus="status" begin="1" end="5">
    										<c:choose>
    											<c:when test="${status.index ==nowPage }">
    												<a class="cur-page">${ status.index}</a>
    											</c:when>
    											<c:otherwise>
    												<a href="${pageContext.request.contextPath }/drawCash/drawCashHistory.do?nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    											</c:otherwise>
    										</c:choose> 
										</c:forEach>
										<a>…</a><a 
    						   		   	   href="${pageContext.request.contextPath }/drawCash/drawCashHistory.do?nowPage=${totalPage}&startLine=${(totalPage-1)*maxLine}&endLine=${totalPage*maxLine}">${totalPage }</a>
    								</c:otherwise>
    							</c:choose>
    						</c:when>
    						<c:otherwise>
    							<a href="${pageContext.request.contextPath }/drawCash/drawCashHistory.do?nowPage=1&startLine=0&endLine=${maxLine}">1</a><a 
    						   	  >…</a>
    							<c:forEach var="item" varStatus="status" begin="${totalPage-4 }" end="${totalPage }">
    								<c:choose>
    									<c:when test="${status.index ==nowPage }">
    										<a class="cur-page">${ status.index}</a>
    									</c:when>
    									<c:otherwise>
    									<a href="${pageContext.request.contextPath }/drawCash/drawCashHistory.do?nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    									</c:otherwise>
    								</c:choose> 
								</c:forEach>
    						</c:otherwise>
    					</c:choose>
    				</c:when>
    			</c:choose>
    			<c:if test="${totalPage >nowPage }">
    				<a href="${pageContext.request.contextPath }/drawCash/drawCashHistory.do?nowPage=${nowPage+1}&startLine=${nowPage*maxLine}&endLine=${(nowPage+1)*maxLine}">下一页</a>
    			</c:if>
    		</p>
    </c:if>
    <c:if test="${empty drawCashList }">
    	 <p class="prompt-pro">没有提现记录！</p>
    </c:if>
