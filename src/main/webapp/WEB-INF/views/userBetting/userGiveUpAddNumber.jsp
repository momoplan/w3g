<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span>停止追号</span></p>
    </div>
    <div class="wap-pro">
    	<p>是否停止追号？</p>
    	<div style="position:relative"><p>
    		<form action="${pageContext.request.contextPath }/select/userGiveUpAddNumber.do" method="post">
    		<input type="hidden" name="flowNo" value="${flowNo }"/>
    		<input type="submit" class="tz-chakan-btn" value="确认" />
    		</form>
    		<form action="${pageContext.request.contextPath }/select/userAddNumber.do" method="post">
    		<input type="submit" class="tz-chakan-btn" value="取消" style="position:absolute; left:90px; top:0;" />
    		</form></p>
        </div>
   </div>