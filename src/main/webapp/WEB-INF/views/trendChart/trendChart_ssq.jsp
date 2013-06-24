<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/trendChart_index">走势图表</a>-</strong><span>双色球</span></p>
    </div>
    <h1><a>双色球号码走势</a></h1>
<div class="wap-pro zoushi-liebiao">
	<form action="${pageContext.request.contextPath }/trendChart/trendChartIndex" method="post">
    	<select name="type">
    		<option value="R1" ${'R1' eq type ? 'selected':'' }>红球一区</option>
    		<option value="R2" ${'R2' eq type ? 'selected':'' }>红球二区</option>
            <option value="R3" ${'R3' eq type ? 'selected':'' }>红球三区</option>
            <option value="B1" ${'B1' eq type ? 'selected':'' }>蓝球一区</option>
            <option value="B2" ${'B2' eq type ? 'selected':'' }>蓝球二区</option>
    	</select>
    	<input name="lotno" type="hidden" value="F47104"/>
    	<input name="" type="submit" value="切换" class="liebiao-qiehuan" />
    </form>    
</div>
<div class="wap-pro"><p><a href="${pageContext.request.contextPath }/doubleBallIndex/bySelf" class="liebiao-touzhu">立即投注</a><a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=F47104&key=F47104MV_X"  class="liebiao-yilou">遗漏</a></p></div>
    <div class="wap-pro" style="margin-bottom:10px;">
    	<c:choose>
    		<c:when test="${'R1' eq type || 'R1' eq param.type }">
    			<p><img src="${pageContext.request.contextPath }/trendChartImage/ssqRed1.jpeg" /></p>
    		</c:when>
    		<c:when test="${'R2' eq type || 'R2' eq param.type}">
    			<p><img src="${pageContext.request.contextPath }/trendChartImage/ssqRed2.jpeg" /></p>
    		</c:when>
    		<c:when test="${'R3' eq type || 'R3' eq param.type}">
    			<p><img src="${pageContext.request.contextPath }/trendChartImage/ssqRed3.jpeg" /></p>
    		</c:when>
    		<c:when test="${'B1' eq type || 'B1' eq param.type}">
    			<p><img src="${pageContext.request.contextPath }/trendChartImage/ssqBlue1.jpeg" /></p>
    		</c:when>
    		<c:when test="${'B2' eq type || 'B2' eq param.type}">
    			<p><img src="${pageContext.request.contextPath }/trendChartImage/ssqBlue2.jpeg" /></p>
    		</c:when>
    		
    	</c:choose>
 	</div>