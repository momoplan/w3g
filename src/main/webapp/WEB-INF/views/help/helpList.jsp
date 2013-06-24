<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
  <div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><span>帮助中心</span></p>
    </div>
   <div class="wap-pro-gray">
    	<ul class="helpcenter-pro">
        	<li>
        	<c:choose>
        		<c:when test="${'1' eq type }">
        			<a href="${pageContext.request.contextPath }/helpCenter/selectHelpList?type=1" class="current">功能指引</a> 
        		</c:when>
        		<c:otherwise>
        			<a href="${pageContext.request.contextPath }/helpCenter/selectHelpList?type=1">功能指引</a> 
        		</c:otherwise>
        	</c:choose>
        	<c:choose>
        		<c:when test="${'2' eq type }">
        			<a href="${pageContext.request.contextPath }/helpCenter/selectHelpList?type=2" class="current">特色功能</a> 
        		</c:when>
        		<c:otherwise>
        			<a href="${pageContext.request.contextPath }/helpCenter/selectHelpList?type=2">特色功能</a>
        		</c:otherwise>
        	</c:choose>
        	<c:choose>
        		<c:when test="${'3' eq type }">
        			<a href="${pageContext.request.contextPath }/helpCenter/selectHelpList?type=3" class="current">彩票玩法</a> 
        		</c:when>
        		<c:otherwise>
        			<a href="${pageContext.request.contextPath }/helpCenter/selectHelpList?type=3">彩票玩法</a> 
        		</c:otherwise>
        	</c:choose>
        	</li>
        	<li>
        	<c:choose>
        		<c:when test="${'4' eq type }">
        			<a href="${pageContext.request.contextPath }/helpCenter/selectHelpList?type=4" class="current">常见问题</a>
        		</c:when>
        		<c:otherwise>
        			<a href="${pageContext.request.contextPath }/helpCenter/selectHelpList?type=4">常见问题</a>
        		</c:otherwise>
        	</c:choose>
        	<c:choose>
        		<c:when test="${'5' eq type }">
					<a href="${pageContext.request.contextPath }/helpCenter/selectHelpList?type=5" class="current">彩票术语</a>        		
				</c:when>
        		<c:otherwise>
					<a href="${pageContext.request.contextPath }/helpCenter/selectHelpList?type=5">彩票术语</a>
        		</c:otherwise>
        	</c:choose>
        	</li>
        </ul>
    </div>
   <div class="wap-pro" style="padding-left:1px">
   		<ul class="helpcenter-list">
   			 <c:if test="${not empty helpList }">
   				<c:forEach items="${helpList }" var="help" varStatus="helpIndex">
   					<c:choose>
   						<c:when test="${fn:length(helpList) == (helpIndex.index+1)}">
   							 <li class="endli"><a href="${pageContext.request.contextPath }/helpCenter/selectHelp?type=${help.type}&id=${help.id}">${help.title }</a></li>
   						</c:when>
   						<c:otherwise>
   							<li><a href="${pageContext.request.contextPath }/helpCenter/selectHelp?type=${help.type}&id=${help.id}">${help.title }</a></li>
   						</c:otherwise>
   					</c:choose>
   				</c:forEach>
   			 </c:if>
   		 </ul>    
   </div>

