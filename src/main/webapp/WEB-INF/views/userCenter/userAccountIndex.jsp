<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  	<div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span>账户明细</span></p>
    </div>
    <div class="wap-pro">
    	<p>日期格式：yyyymmdd（如20120212）</p>
        <p>解&nbsp;&nbsp;&nbsp;&nbsp;释：2012年02月12日</p>
        <form action="${pageContext.request.contextPath }/select/userAccountDetail.do" method="post">
        	<p>起始时间：<input name="beginTime" type="text" value="${beginTime }"/></p>
       		<p>终止时间：<input name="endTime" type="text" value="${endTime }"/></p>
       		<input name="nowPage" type="hidden" value="1"/>
        	<p><input type="submit" value="查&nbsp;询" class="user-check-btn" /></p>
        	
        </form>
    </div>
