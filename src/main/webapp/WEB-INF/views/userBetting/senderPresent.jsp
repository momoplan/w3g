<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <div class="wap-banner yellowline">
    <p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span>赠送查询</span></p>
 </div>
 <c:choose>
 	<c:when test="${'sender' eq presentType }">
 		 <div class="wap-pro-gray tixian">
 		 	<a href="${pageContext.request.contextPath }/select/selectSenderPresent.do" class="tixian-cur">赠送彩票</a><span>|</span><a href="${pageContext.request.contextPath }/select/selectReciverPresent.do">收到彩票</a>
 		 </div>
 	</c:when>
 	<c:when test="${'reciver' eq presentType }">
 		 <div class="wap-pro-gray tixian">
 		 	<a href="${pageContext.request.contextPath }/select/selectSenderPresent.do" >赠送彩票</a><span>|</span><a href="${pageContext.request.contextPath }/select/selectReciverPresent.do" class="tixian-cur">收到彩票</a>
 		 </div>
 	</c:when>
 </c:choose> 
   	<c:if test="${not empty presents }">
   		<c:forEach items="${presents }" var="present">
   			<div class="user-moneychange">
    	     <p>受赠人：${present.mobile }</p>
             <p>彩票种类：${present.lotName }</p>
             <p>彩票期号：${present.batchCode }</p>
             <c:choose>
        		<c:when test="${'已中奖' eq present.prizeState }">
        			<p>中奖金额：<span>${present.orderPrize }元</span></p>
        		</c:when>
        		<c:otherwise>
        			<p>${present.prizeState }</p>
        		</c:otherwise>
        	</c:choose>
             <form action="${pageContext.request.contextPath }/select/selectSenderPresentDetail.do" method="post">
               	<input type="hidden" name="mobile" value="${present.mobile }"/>
               	<input type="hidden" name="amt" value="${present.amt }"/>
               	<input type="hidden" name="batchCode" value="${present.batchCode }"/>
               	<input type="hidden" name="lotName" value="${present.lotName }"/>
               	<input type="hidden" name="zhuShu" value="${present.zhuShu }"/>
               	<input type="hidden" name="beiShu" value="${present.beiShu }"/>
               	<input type="hidden" name="createTime" value="${present.createTime }"/>
               	<input type="hidden" name="betCode" value="${present.betCode }"/>
               	<input type="hidden" name="winCode" value="${present.winCode }"/>
               	<input type="hidden" name="orderPrize" value="${present.orderPrize }"/>
               	<input type="hidden" name="prizeState" value="${present.prizeState }"/>
               <p><input type="submit" value="查看详情" class="tz-chakan-btn" /></p>
             </form>
    		</div>
   			
   		</c:forEach>
   		<p class="user-pages">
   				<c:if test="${nowPage >1 }">
					<a href="${pageContext.request.contextPath }/select/selectSenderPresent.do?nowPage=${nowPage-1}&startLine=${(nowPage-2)*maxLine}&endLine=${(nowPage-1)*maxLine}">上一页</a>
    			</c:if>
    			<c:choose>
    				<c:when test="${totalPage>1&&(totalPage<5 || totalPage==5) }">
    					<c:forEach var="item" varStatus="status" begin="1" end="${totalPage }">
    						<c:choose>
    							<c:when test="${status.index ==nowPage }">
    								<a class="cur-page">${ status.index}</a>
    							</c:when>
    							<c:otherwise>
    								<a href="${pageContext.request.contextPath }/select/selectSenderPresent.do?nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    							</c:otherwise>
    						
    						</c:choose> 
						</c:forEach> 
    				</c:when>
    				<c:when test="${totalPage>5}">
    					<c:choose>
    						<c:when test="${nowPage<totalPage-3 }">
    							<c:choose>
    								<c:when test="${nowPage>3 }">
    									<a href="${pageContext.request.contextPath }/select/selectSenderPresent.do?nowPage=1&startLine=0&endLine=${maxLine}">1</a><a 
    						   		   	  >…</a><a 
    						   		       href="${pageContext.request.contextPath }/select/selectSenderPresent.do?nowPage=${nowPage-1}&startLine=${(nowPage-3)*maxLine}&endLine=${(nowPage-2)*maxLine}">${nowPage-1 }</a><a class="cur-page">${nowPage }</a><a 
    						   		       href="${pageContext.request.contextPath }/select/selectSenderPresent.do?nowPage=${nowPage+1}&startLine=${(nowPage)*maxLine}&endLine=${(nowPage+1)*maxLine}">${nowPage+1 }</a><a 
    						   		      >…</a><a 
    						   		       href="${pageContext.request.contextPath }/select/selectSenderPresent.do?nowPage=${totalPage}&startLine=${(totalPage-1)*maxLine}&endLine=${totalPage*maxLine}">${totalPage }</a>
    								</c:when>
    								<c:otherwise>
    									<c:forEach var="item" varStatus="status" begin="1" end="5">
    										<c:choose>
    											<c:when test="${status.index ==nowPage }">
    												<a class="cur-page">${ status.index}</a>
    											</c:when>
    											<c:otherwise>
    												<a href="${pageContext.request.contextPath }/select/selectSenderPresent.do?nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    											</c:otherwise>
    										</c:choose> 
										</c:forEach>
										<a>…</a><a 
    						   		   	   href="${pageContext.request.contextPath }/select/selectSenderPresent.do?nowPage=${totalPage}&startLine=${(totalPage-1)*maxLine}&endLine=${totalPage*maxLine}">${totalPage }</a>
    								</c:otherwise>
    							</c:choose>
    						</c:when>
    						<c:otherwise>
    							<a href="${pageContext.request.contextPath }/select/selectSenderPresent.do?nowPage=1&startLine=0&endLine=${maxLine}">1</a><a 
    						   	  >…</a>
    							<c:forEach var="item" varStatus="status" begin="${totalPage-4 }" end="${totalPage }">
    								<c:choose>
    									<c:when test="${status.index ==nowPage }">
    										<a class="cur-page">${ status.index}</a>
    									</c:when>
    									<c:otherwise>
    									<a href="${pageContext.request.contextPath }/select/selectSenderPresent.do?nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    									</c:otherwise>
    								</c:choose> 
								</c:forEach>
    						</c:otherwise>
    					</c:choose>
    				</c:when>
    			</c:choose>
    			<c:if test="${totalPage >nowPage }">
    				<a href="${pageContext.request.contextPath }/select/selectSenderPresent.do?nowPage=${nowPage+1}&startLine=${nowPage*maxLine}&endLine=${(nowPage+1)*maxLine}">下一页</a>
    			</c:if>
    		</p>
   	</c:if>
   