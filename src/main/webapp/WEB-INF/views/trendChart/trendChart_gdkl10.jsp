<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/trendChart_index">走势图表</a>-</strong><span>快乐10分</span></p>
    </div>
    <h1><a>快乐十分号码走势</a></h1>
<div class="wap-pro zoushi-liebiao">
	<form action="${pageContext.request.contextPath }/trendChart/trendChartDetail" method="post">
    	<select name="type">
    		<option value="1" ${'1' eq type ? 'selected':'' }>红球一区</option>
    		<option value="2" ${'2' eq type ? 'selected':'' }>红球二区</option>
    	</select>
    	<input name="imgName" type="hidden" value="kl10Red1"/>
    	<input name="lotno" type="hidden" value="T01015"/>
    	<input name="" type="submit" value="切换" class="liebiao-qiehuan" />
    </form>    
</div>
<div class="wap-pro"><p><a href="${pageContext.request.contextPath }/guangDongHappyTenMinutesIndex/bySelectOneNumberSelf" class="liebiao-touzhu">立即投注</a><a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01015&key=T01015MV_S1&type=s1"  class="liebiao-yilou">遗漏</a></p></div>
    <div class="wap-pro" style="margin-bottom:10px;">
    	<c:choose>
    		<c:when test="${'1' eq type || '1' eq param.type }">
    			<p><img src="${pageContext.request.contextPath }/trendChartImage/kl10Red1.jpeg" /></p>
    		</c:when>
    		<c:when test="${'2' eq type || '2' eq param.type}">
    			<p><img src="${pageContext.request.contextPath }/trendChartImage/kl10Red2.jpeg" /></p>
    		</c:when>
    	</c:choose>
 	</div>