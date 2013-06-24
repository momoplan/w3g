<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><span>合买大厅</span></p>
    </div>
    <h1>
    <c:choose>
    	<c:when test="${'participantCount' eq orderBy}">
    		<a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLot?orderBy=participantCount" class="tit-cur">人气&darr;</a>
    	</c:when>
    	<c:otherwise>
    	    <a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLot?orderBy=participantCount">人气</a>
    	</c:otherwise>
    </c:choose>
    <span>|</span>
    <c:choose>
    	<c:when test="${'achievement' eq orderBy}">
    		<a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLot?orderBy=achievement" class="tit-cur">战绩&darr;</a>
    	</c:when>
    	<c:otherwise>
    		<a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLot?orderBy=achievement">战绩</a>
    	</c:otherwise>
    </c:choose>
    <span>|</span>
    <c:choose>
    	<c:when test="${'progress' eq orderBy}">
    		<a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLot?orderBy=progress" class="tit-cur">进度&darr;</a>
    	</c:when>
    	<c:otherwise>
    		<a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLot?orderBy=progress">进度</a>
    	</c:otherwise>
    </c:choose>
    <span>|</span>
    <c:choose>
    	<c:when test="${'totalAmt' eq orderBy}">
    		<a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLot?orderBy=totalAmt" class="tit-cur">总额&darr;</a>
    	</c:when>
    	<c:otherwise>
    		<a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLot?orderBy=totalAmt">总额</a>
    	</c:otherwise>
    </c:choose>
    </h1>
    <div class="wap-pro-gray" style=" padding-top:8px">
    	<form action="${pageContext.request.contextPath }/caseLotCenter/selectCaseLot" method="post">
    		<p>发起人：<input name="search" type="text" value="${search }"/><input type="submit" class="find-but" value="搜索" /></p>
			<p>彩&nbsp;&nbsp;种：<select name="lotNo" class="caizhong">
				<c:choose>
					<c:when test="">
					</c:when>
				</c:choose>
				<option value="">全部</option>
				<option value="F47104" ${'F47104' eq lotNo ? 'selected':'' }>双色球</option>
				<option value="F47103" ${'F47103' eq lotNo ? 'selected':'' }>福彩3D</option>
				<option value="F47102" ${'F47102' eq lotNo ? 'selected':'' }>七乐彩</option>
				<option value="T01001" ${'T01001' eq lotNo ? 'selected':'' }>大乐透</option>
				<option value="T01002" ${'T01002' eq lotNo ? 'selected':'' }>排列三</option>
				<option value="T01003" ${'T01003' eq lotNo ? 'selected':'' }>胜负彩14场</option>
				<option value="T01004" ${'T01004' eq lotNo ? 'selected':'' }>任九场</option>
				<option value="T01005" ${'T01005' eq lotNo ? 'selected':'' }>四场进球彩</option>
				<option value="T01006" ${'T01006' eq lotNo ? 'selected':'' }>六场半全场</option>
				<option value="T01009" ${'T01009' eq lotNo ? 'selected':'' }>七星彩</option>
				<option value="T01011" ${'T01011' eq lotNo ? 'selected':'' }>排列五</option>
				<option value="T01013" ${'T01013' eq lotNo ? 'selected':'' }>22选5</option>
				<option value="J00001" ${'J00001' eq lotNo ? 'selected':'' }>竞彩足球胜平负</option>
				<option value="J00013" ${'J00013' eq lotNo ? 'selected':'' }>竞彩足球让球胜平负</option>
				<option value="J00002" ${'J00002' eq lotNo ? 'selected':'' }>竞彩足球比分</option>
				<option value="J00003" ${'J00003' eq lotNo ? 'selected':'' }>竞彩足球总进球</option>
				<option value="J00004" ${'J00004' eq lotNo ? 'selected':'' }>竞彩足球半场胜平负</option>
				<option value="J00005" ${'J00005' eq lotNo ? 'selected':'' }>竞彩篮球胜负</option>
				<option value="J00006" ${'J00006' eq lotNo ? 'selected':'' }>竞彩篮球让分胜负</option>
				<option value="J00007" ${'J00007' eq lotNo ? 'selected':'' }>竞彩篮球胜分差</option>
				<option value="J00008" ${'J00008' eq lotNo ? 'selected':'' }>竞彩篮球大小分</option>
				<option value="J00012" ${'J00012' eq lotNo ? 'selected':'' }>竞彩篮球混合过关</option>
				<option value="J00011" ${'J00011' eq lotNo ? 'selected':'' }>竞彩足球混合过关</option>
			</select></p>    
    	</form>
	</div>
    <c:if test="${not empty caseLotList }">
    	<c:forEach items="${caseLotList}" var="caseLot" varStatus="caseLotIndex">
    		<div class="${(caseLotIndex.index+1)%2==0 ? 'wap-pro-nogray':'wap-pro-gray' }">
    			<form action="${pageContext.request.contextPath }/caseLotCenter/selectCaseLotDetail" method="post">
    			<p>${caseLot.lotName }<a class="red">&nbsp;
    			<c:if test="${'8' eq caseLot.sortState || '9' eq caseLot.sortState }">
    				顶
    			</c:if>
    			</a></p>
        		<p>发起人:<a class="red">${caseLot.starter }</a></p>
        		<c:if test="${not empty caseLot.achievement }">
        			<p class="zhanji">战&nbsp;&nbsp;绩:${caseLot.achievement }</p>
        		</c:if>
        		<p>总金额:<a class="red">￥${caseLot.totalAmt }</a>&nbsp;进度：<a class="red">${caseLot.progress }</a></p>
        		<input type="hidden" name="caseLotId" value="${caseLot.caseLotId }"/>
         		<p><input type="submit" class="check-btn" value="查看" /></p>
    			</form>
    		</div>
    	</c:forEach>
    	<p class="user-pages">
   	<c:if test="${nowPage >1 }">
					<a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLot?orderBy=${orderBy }&lotNo=${lotNo }&search=${search }&nowPage=${nowPage-1}&startLine=${(nowPage-2)*maxLine}&endLine=${(nowPage-1)*maxLine}">上一页</a>
    			</c:if>
    			<c:choose>
    				<c:when test="${totalPage>1&&(totalPage<5 || totalPage==5) }">
    					<c:forEach var="item" varStatus="status" begin="1" end="${totalPage }">
    						<c:choose>
    							<c:when test="${status.index ==nowPage }">
    								<a class="cur-page">${ status.index}</a>
    							</c:when>
    							<c:otherwise>
    								<a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLot?orderBy=${orderBy }&lotNo=${lotNo }&search=${search }&nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    							</c:otherwise>
    						
    						</c:choose> 
						</c:forEach> 
    				</c:when>
    				<c:when test="${totalPage>5}">
    					<c:choose>
    						<c:when test="${nowPage<totalPage-3 }">
    							<c:choose>
    								<c:when test="${nowPage>3 }">
    									<a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLot?orderBy=${orderBy }&lotNo=${lotNo }&search=${search }&nowPage=1&startLine=0&endLine=${maxLine}">1</a><a 
    						   		   	  >…</a><a 
    						   		       href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLot?orderBy=${orderBy }&lotNo=${lotNo }&search=${search }&nowPage=${nowPage-1}&startLine=${(nowPage-3)*maxLine}&endLine=${(nowPage-2)*maxLine}">${nowPage-1 }</a><a class="cur-page">${nowPage }</a><a 
    						   		       href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLot?orderBy=${orderBy }&lotNo=${lotNo }&search=${search }&nowPage=${nowPage+1}&startLine=${(nowPage)*maxLine}&endLine=${(nowPage+1)*maxLine}">${nowPage+1 }</a><a 
    						   		      >…</a><a 
    						   		       href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLot?orderBy=${orderBy }&lotNo=${lotNo }&search=${search }&nowPage=${totalPage}&startLine=${(totalPage-1)*maxLine}&endLine=${totalPage*maxLine}">${totalPage }</a>
    								</c:when>
    								<c:otherwise>
    									<c:forEach var="item" varStatus="status" begin="1" end="5">
    										<c:choose>
    											<c:when test="${status.index ==nowPage }">
    												<a class="cur-page">${ status.index}</a>
    											</c:when>
    											<c:otherwise>
    												<a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLot?orderBy=${orderBy }&lotNo=${lotNo }&search=${search }&nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    											</c:otherwise>
    										</c:choose> 
										</c:forEach>
										<a>…</a><a 
    						   		   	   href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLot?orderBy=${orderBy }&lotNo=${lotNo }&search=${search }&nowPage=${totalPage}&startLine=${(totalPage-1)*maxLine}&endLine=${totalPage*maxLine}">${totalPage }</a>
    								</c:otherwise>
    							</c:choose>
    						</c:when>
    						<c:otherwise>
    							<a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLot?orderBy=${orderBy }&lotNo=${lotNo }&search=${search }&nowPage=1&startLine=0&endLine=${maxLine}">1</a><a 
    						   	  >…</a>
    							<c:forEach var="item" varStatus="status" begin="${totalPage-4 }" end="${totalPage }">
    								<c:choose>
    									<c:when test="${status.index ==nowPage }">
    										<a class="cur-page">${ status.index}</a>
    									</c:when>
    									<c:otherwise>
    									<a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLot?orderBy=${orderBy }&lotNo=${lotNo }&search=${search }&nowPage=${status.index}&startLine=${(status.index-1)*maxLine}&endLine=${status.index*maxLine}">${ status.index}</a>
    									</c:otherwise>
    								</c:choose> 
								</c:forEach>
    						</c:otherwise>
    					</c:choose>
    				</c:when>
    			</c:choose>
    			<c:if test="${totalPage >nowPage }">
    				<a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLot?orderBy=${orderBy }&lotNo=${lotNo }&search=${search }&nowPage=${nowPage+1}&startLine=${nowPage*maxLine}&endLine=${(nowPage+1)*maxLine}">下一页</a>
    			</c:if>
    		</p>
    </c:if>
   <h1><a>合买彩种</a></h1>
    <div class="wap-pro">
    	<p><span class="pro-name">福彩：</span><a href="${pageContext.request.contextPath }/doubleBallIndex/bySelf">双色球</a><em>|</em><a href="${pageContext.request.contextPath }/fuCai3DIndex/byDirect">福彩3D</a><em>|</em><a href="${pageContext.request.contextPath }/qiLeCaiIndex/bySelf">七乐彩</a></p>
        <p><span class="pro-name">体彩：</span><a href="${pageContext.request.contextPath }/daLeTouIndex/bySelf">大乐透</a><em>|</em><a href="${pageContext.request.contextPath }/array3Index/byDirect">排列三</a><em>|</em><a href="${pageContext.request.contextPath }/array5Index/bySelf">排列五</a><em>|</em><a href="${pageContext.request.contextPath }/sevenStarIndex/bySelf">七星彩</a><em>|</em><a href="${pageContext.request.contextPath }/select5From22Index/bySelf">22选5</a></p>
        <p><span class="pro-name">足彩：</span><a href="${pageContext.request.contextPath }/footballIndex/byFootball?lotNo=T01003">胜负彩</a><em>|</em><a href="${pageContext.request.contextPath }/footballIndex/byFootball?lotNo=T01004">任选九场</a><em>|</em><a href="${pageContext.request.contextPath }/footballIndex/byFootball?lotNo=T01006">六场半</a><em>|</em><a href="${pageContext.request.contextPath }/footballIndex/byFootball?lotNo=T01005">四场进球</a></p>
        <p><span class="pro-name">竞彩：</span><a href="${pageContext.request.contextPath }/jcFootballIndex/byJcFootBall?type=1&valueType=1&wanFa=FT001">竞彩足球</a><em>|</em><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=1&wanFa=BSK001">竞彩篮球</a></p>
    </div>
