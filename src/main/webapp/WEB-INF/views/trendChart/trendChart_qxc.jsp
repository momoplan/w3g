<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/trendChart_index">走势图表</a>-</strong><span>七星彩</span></p>
    </div>
    <h1><a href="#">七星彩号码走势</a></h1>
<div class="wap-pro zoushi-liebiao">
	<form action="${pageContext.request.contextPath }/trendChart/trendChartIndex" method="post">
    	<select name="type">
    		<option value="R1" ${'R1' eq type ? 'selected':'' }>第一位</option>
    		<option value="R2" ${'R2' eq type ? 'selected':'' }>第二位</option>
            <option value="R3" ${'R3' eq type ? 'selected':'' }>第三位</option>
            <option value="R4" ${'R3' eq type ? 'selected':'' }>第四位</option>
            <option value="R5" ${'R3' eq type ? 'selected':'' }>第五位</option>
            <option value="R6" ${'R3' eq type ? 'selected':'' }>第六位</option>
            <option value="R7" ${'R3' eq type ? 'selected':'' }>第七位</option>
    	</select>
    	<input name="lotno" type="hidden" value="T01009"/>
    	<input name="" type="submit" value="切换" class="liebiao-qiehuan" />
    </form>    
</div>
<div class="wap-pro"><p><a href="${pageContext.request.contextPath }/sevenStarIndex/bySelf" class="liebiao-touzhu">立即投注</a><a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01009&key=T01009MV_ZX"  class="liebiao-yilou">遗漏</a></p></div>
    <div class="wap-pro" style="margin-bottom:10px;">
    	<c:choose>
    		<c:when test="${'R1' eq type || 'R1' eq param.type }">
    			<p><img src="${pageContext.request.contextPath }/trendChartImage/qxcRed1.jpeg" /></p>
    		</c:when>
    		<c:when test="${'R2' eq type || 'R2' eq param.type}">
    			<p><img src="${pageContext.request.contextPath }/trendChartImage/qxcRed2.jpeg" /></p>
    		</c:when>
    		<c:when test="${'R3' eq type || 'R3' eq param.type}">
    			<p><img src="${pageContext.request.contextPath }/trendChartImage/qxcRed3.jpeg" /></p>
    		</c:when>
    		<c:when test="${'R4' eq type || 'R4' eq param.type}">
    			<p><img src="${pageContext.request.contextPath }/trendChartImage/qxcRed4.jpeg" /></p>
    		</c:when>
    		<c:when test="${'R5' eq type || 'R5' eq param.type}">
    			<p><img src="${pageContext.request.contextPath }/trendChartImage/qxcRed5.jpeg" /></p>
    		</c:when>
    		<c:when test="${'R6' eq type || 'R6' eq param.type}">
    			<p><img src="${pageContext.request.contextPath }/trendChartImage/qxcRed6.jpeg" /></p>
    		</c:when>
    		<c:when test="${'R7' eq type || 'R7' eq param.type}">
    			<p><img src="${pageContext.request.contextPath }/trendChartImage/qxcRed7.jpeg" /></p>
    		</c:when>
    		
    	</c:choose>
 	</div>