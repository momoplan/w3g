<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	  <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span><a href="${pageContext.request.contextPath }/chargeCenter">账户充值</a></span></p>
    </div>
    <h1><a>银联语音充值</a></h1>
   	
    <div class="wap-pro">
      <p><img src="${pageContext.request.contextPath }/images/YL.png" width="120" height="30" />（即时入账，无手续费）</p>
    	 <p class="prompt-pro">${messageError }</p>
    	<form action="${pageContext.request.contextPath }/chargeCenter/chargeUnionPay_DNA.do" method="post">
    		<p>充值金额：<input name="amount" type="text" value="${amount }"/>元</p>
        	<p>银行卡号：${dnaBand.bankCardNo }</p>
        	<input name="cardNo" type="hidden" value="${dnaBand.bankCardNo }"/>
        	<p>电话号码：<input name="mobileId" type="text" value="${mobileId }"/></p>
        	<c:choose>
        		<c:when test="${not empty dnaBand.bankName }">
        			<p>开卡银行：${dnaBand.bankName }</p>
        			<input name="bankName" type="hidden" value="${dnaBand.bankName }"/>
        		</c:when>
        		<c:otherwise>
        			<p>开卡银行：</p>
        			<p><select name="bankName">
        				<option value="00">请选择银行</option>
            			<option value="china_gongshang">中国工商银行</option>
						<option value="china_nongye">中国农业银行</option>
						<option value="china_jianshe">中国建设银行</option>
						<option value="zhaoshang">招商银行</option>
						<option value="china_youzhengchuxu">中国邮政储蓄银行</option>
						<option value="huaxia">华夏银行</option>
						<option value="xingye">兴业银行</option>
						<option value="zhongxin">中信银行</option>
						<option value="shenzhen_fazhan">深圳发展银行</option>
						<option value="china_guangda">中国光大银行</option>
						<option value="shanghaipudong_fazhan">上海浦东发展银行</option>
       				</select></p>
        		</c:otherwise>
        	</c:choose>
        	<p><input name="" type="submit" value="立即充值" class="tz-chakan-btn" /></p>
    	</form>
       
    </div>