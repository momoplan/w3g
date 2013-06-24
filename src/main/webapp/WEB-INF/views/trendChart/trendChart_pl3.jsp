<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/trendChart_index">走势图表</a>-</strong><span>排列三</span></p>
    </div>
    <h1><a>排列三号码走势</a></h1>
<div class="wap-pro zoushi-liebiao">
	<form action="${pageContext.request.contextPath }/trendChart/trendChartIndex" method="post">
    	<select name="type">
    		<option value="R1" ${'R1' eq type ? 'selected':'' }>百位</option>
    		<option value="R2" ${'R2' eq type ? 'selected':'' }>十位</option>
            <option value="R3" ${'R3' eq type ? 'selected':'' }>个位</option>
    	</select>
    	<input name="lotno" type="hidden" value="T01002"/>
    	<input name="" type="submit" value="切换" class="liebiao-qiehuan" />
    </form>    
</div>
<div class="wap-pro"><p><a href="${pageContext.request.contextPath }/array3Index/byDirect" class="liebiao-touzhu">立即投注</a><a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01002&key=T01002MV_ZX"  class="liebiao-yilou">遗漏</a></p></div>
    <div class="wap-pro" style="margin-bottom:10px;">
    	<c:choose>
    		<c:when test="${'R1' eq type || 'R1' eq param.type }">
    			<p><img src="${pageContext.request.contextPath }/trendChartImage/plsRed1.jpeg" /></p>
    		</c:when>
    		<c:when test="${'R2' eq type || 'R2' eq param.type}">
    			<p><img src="${pageContext.request.contextPath }/trendChartImage/plsRed2.jpeg" /></p>
    		</c:when>
    		<c:when test="${'R3' eq type || 'R3' eq param.type}">
    			<p><img src="${pageContext.request.contextPath }/trendChartImage/plsRed3.jpeg" /></p>
    		</c:when>
    		
    	</c:choose>
 	</div>