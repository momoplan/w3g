<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><span>用户注册</span></p>
    </div>
    <div class="wap-pro" style="padding-top:8px">
    <p class="prompt-pro">${userName },恭喜您注册成功！</p>
    <p>为了您的账户安全，请先<a href="${pageContext.request.contextPath }/userInfo/selectUserInfo.do">完善用户信息</a>。</p>
    <p>以后再填，先做其他：</p>
     <div class="wap-pro-gray">
   		<ul class="helpcenter-pro">
        	<li>
        		<a href="${pageContext.request.contextPath }/chargeCenter">立即充值</a> 
        		<a href="${pageContext.request.contextPath }/lotteryCenter">立即购彩</a>
        	</li>
        	<li>
        		<a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLot">参与合买</a>
				<a href="${pageContext.request.contextPath }/downLoad">下载客户端</a>
        	</li>
        </ul>
   </div>
</div>

