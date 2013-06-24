<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/winInfo/selectWinInfoCenter">开奖公告</a>-</strong><span>历史开奖</span></p>
    </div>
    <div class="wap-pro-gray tixian">
    	<c:choose>
    		<c:when test="${'10' eq issueNum }">
    			<a class="tixian-cur">10期</a>
    		</c:when>
    		<c:otherwise>
    			<a href="${pageContext.request.contextPath }/winInfo/selectWinInfoList?lotNo=${lotNo}&issueNum=10">10期</a>
    		</c:otherwise>
    	</c:choose>
    <span>|</span>
    	<c:choose>
    		<c:when test="${'20' eq issueNum }">
    			<a class="tixian-cur">20期</a>
    		</c:when>
    		<c:otherwise>
    			<a href="${pageContext.request.contextPath }/winInfo/selectWinInfoList?lotNo=${lotNo}&issueNum=20">20期</a>
    		</c:otherwise>
    	</c:choose>
    <span>|</span>
    	<c:choose>
    		<c:when test="${'30' eq issueNum }">
    			<a class="tixian-cur">30期</a>
    		</c:when>
    		<c:otherwise>
    			<a href="${pageContext.request.contextPath }/winInfo/selectWinInfoList?lotNo=${lotNo}&issueNum=30">30期</a>
    		</c:otherwise>
    	</c:choose>
    <span>|</span>
    	<c:choose>
    		<c:when test="${'50' eq issueNum }">
    			<a class="tixian-cur">50期</a>
    		</c:when>
    		<c:otherwise>
    			<a href="${pageContext.request.contextPath }/winInfo/selectWinInfoList?lotNo=${lotNo}&issueNum=50">50期</a>
    		</c:otherwise>
    	</c:choose>
	</div>
    <div class="wap-pro"><p>彩种：${lotName }</p><div class="xuxian-line"></div></div>
   <div class="wap-pro">
   		<c:if test="${not empty winInfos }">
   			<c:forEach items="${winInfos }" var="winInfo">
   				<p><a href="${pageContext.request.contextPath }/winInfo/selectWinInfo?lotNo=${lotNo}&issue=${winInfo.batchCode}">第${winInfo.batchCode }期：${winInfo.winCode }</a></p>
   			</c:forEach>
   		
   		</c:if>
   </div>
   <div class="wap-pro">
    	
        <p><a href="${pageContext.request.contextPath }/winInfo/selectWinInfoCenter" class="fanhui">返回上页</a><a href="${pageContext.request.contextPath }/lotteryCenter" class="fanhui">立即购彩</a></p>
    </div>