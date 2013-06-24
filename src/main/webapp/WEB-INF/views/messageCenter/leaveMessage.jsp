<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  	  <div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span>我要留言</span></p>
    </div>
    <div class="wap-pro">
    	<form action="${pageContext.request.contextPath }/messageCenter/leaveMessage" method="post">
    		<p>*尊敬的用户，欢迎您来到如意彩购彩平台，如果您有什么建议或意见可以留言给我们。注：登录后留言才可获赠积分</p>
        	<p class="prompt-pro">${messageError }</p>
        	<p><textarea name="content" cols="" rows="" class="text-area">${content }</textarea></p>
        	<p>联系方式：<input name="detail" type="text" value="${detail }"/></p>
        	<p><input name="" type="submit" value="提交" class="tijiao-btn-blue" /></p>
    	</form>
    </div>