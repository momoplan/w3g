<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span>我的积分</span></p>
    </div>
    <div class="wap-pro-gray zhuangzhang-art">
    	<p>我现有的积分为：<em>${score.score }</em>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/howScore">如何获得更多积分</a></p>
    	<form action="${pageContext.request.contextPath }/scoreCenter/transScore.do" method="post">
    		<input name="totalScore" type="hidden" value="${score.score }"/>
    		<p>我要用<input name="tradScore" type="text" class="jifen-btn" value="${tradScore }"/>个积分兑换<input name="handsel" type="text" class="jifen-btn" value="${handsel }"/>元购彩金<input type="submit" value="兑换" class="duihuan-btn" /></p>
    	</form>
        <p>注：500个积分可以兑换1元购彩金（请输入500的倍数），存入您的如意彩账户。</p>
    </div>
   <div class="wap-pro jifen-mxi"><p>积分明细</p></div>
   <div class="xuxian-line">&nbsp;</div>
   
   <c:if test="${not empty scores }">
   <table width="250" border="0" cellspacing="0" cellpadding="0" class="jifen-table">
   		<tr>
    		<td>序号</td>
    		<td>时间</td>
   			<td>积分</td>
    		<td>说明</td>
  		</tr>
   		<c:forEach items="${scores }" var="score" varStatus="scoreIndex">
   			<tr>
    			<td>${(nowPage-1)*maxLine+scoreIndex.index+1 }</td>
    			<td>${score.createTime }</td>
    			<c:choose>
    				<c:when test="${'-1' eq score.scoreType }">
    					<td>-<span>${score.score }</span></td>
    				</c:when>
    				<c:otherwise>
    					<td>+<span>${score.score }</span></td>
    				</c:otherwise>
    			</c:choose>
    			<td>${score.scoreTypeName }</td>
  			</tr>
   		</c:forEach>
   	</table>
   	<div class="xuxian-line">&nbsp;</div>
   	<p class="user-pages">
   	<c:if test="${nowPage >1 }">
					<a href="${pageContext.request.contextPath }/scoreCenter/selectScoreDetail.do?nowPage=${nowPage-1}&startLine=${(nowPage-2)*maxLine}&endLine=${(nowPage-1)*maxLine}">上一页</a>
    			</c:if>
    			<c:choose>
    				<c:when test="${totalPage>1&&(totalPage<5 || totalPage==5) }">
    					<c:forEach var="item" varStatus="status" begin="1" end="${totalPage }">
    						<c:choose>
    							<c:when test="${status.index ==nowPage }">
    								<a class="cur-page">${ status.index}</a>
    							</c:when>
    							<c:otherwise>
    								<a href="${pageContext.request.contextPath }/scoreCenter/selectScoreDetail.do?nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    							</c:otherwise>
    						
    						</c:choose> 
						</c:forEach> 
    				</c:when>
    				<c:when test="${totalPage>5}">
    					<c:choose>
    						<c:when test="${nowPage<totalPage-3 }">
    							<c:choose>
    								<c:when test="${nowPage>3 }">
    									<a href="${pageContext.request.contextPath }/scoreCenter/selectScoreDetail.do?nowPage=1&startLine=0&endLine=${maxLine}">1</a><a 
    						   		   	  >…</a><a 
    						   		       href="${pageContext.request.contextPath }/scoreCenter/selectScoreDetail.do?nowPage=${nowPage-1}&startLine=${(nowPage-3)*maxLine}&endLine=${(nowPage-2)*maxLine}">${nowPage-1 }</a><a class="cur-page">${nowPage }</a><a 
    						   		       href="${pageContext.request.contextPath }/scoreCenter/selectScoreDetail.do?nowPage=${nowPage+1}&startLine=${(nowPage)*maxLine}&endLine=${(nowPage+1)*maxLine}">${nowPage+1 }</a><a 
    						   		      >…</a><a 
    						   		       href="${pageContext.request.contextPath }/scoreCenter/selectScoreDetail.do?nowPage=${totalPage}&startLine=${(totalPage-1)*maxLine}&endLine=${totalPage*maxLine}">${totalPage }</a>
    								</c:when>
    								<c:otherwise>
    									<c:forEach var="item" varStatus="status" begin="1" end="5">
    										<c:choose>
    											<c:when test="${status.index ==nowPage }">
    												<a class="cur-page">${ status.index}</a>
    											</c:when>
    											<c:otherwise>
    												<a href="${pageContext.request.contextPath }/scoreCenter/selectScoreDetail.do?nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    											</c:otherwise>
    										</c:choose> 
										</c:forEach>
										<a>…</a><a 
    						   		   	   href="${pageContext.request.contextPath }/scoreCenter/selectScoreDetail.do?nowPage=${totalPage}&startLine=${(totalPage-1)*maxLine}&endLine=${totalPage*maxLine}">${totalPage }</a>
    								</c:otherwise>
    							</c:choose>
    						</c:when>
    						<c:otherwise>
    							<a href="${pageContext.request.contextPath }/scoreCenter/selectScoreDetail.do?nowPage=1&startLine=0&endLine=${maxLine}">1</a><a 
    						   	  >…</a>
    							<c:forEach var="item" varStatus="status" begin="${totalPage-4 }" end="${totalPage }">
    								<c:choose>
    									<c:when test="${status.index ==nowPage }">
    										<a class="cur-page">${ status.index}</a>
    									</c:when>
    									<c:otherwise>
    									<a href="${pageContext.request.contextPath }/scoreCenter/selectScoreDetail.do?nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    									</c:otherwise>
    								</c:choose> 
								</c:forEach>
    						</c:otherwise>
    					</c:choose>
    				</c:when>
    			</c:choose>
    			<c:if test="${totalPage >nowPage }">
    				<a href="${pageContext.request.contextPath }/scoreCenter/selectScoreDetail.do?nowPage=${nowPage+1}&startLine=${nowPage*maxLine}&endLine=${(nowPage+1)*maxLine}">下一页</a>
    			</c:if>
    		</p>
   </c:if>

	
