<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	 <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span><a href="${pageContext.request.contextPath }/chargeCenter">账户充值</a></span></p>
    </div>
    <h1><a>充值卡充值</a></h1>
   	<div class="wap-pro-gray czhika">
   	<c:choose>
   		<c:when test="${'YD' eq param.type }">
   			<a class="czhika-cur">移动充值卡</a>
   		</c:when>
   		<c:otherwise>
   			<a href="${pageContext.request.contextPath }/chargeMobileCard?type=YD">移动充值卡</a>
   		</c:otherwise>
   	</c:choose>
   	 | 
   	<c:choose>
   		<c:when test="${'LT' eq param.type }">
   			<a class="czhika-cur">联通充值卡</a>
   		</c:when>
   		<c:otherwise>
   			<a href="${pageContext.request.contextPath }/chargeMobileCard?type=LT">联通充值卡</a>
   		</c:otherwise>
   	</c:choose>
   	 | 
   	<c:choose>
   		<c:when test="${'DX' eq param.type }">
   			<a class="czhika-cur">电信充值卡</a>
   		</c:when>
   		<c:otherwise>
   			<a href="${pageContext.request.contextPath }/chargeMobileCard?type=DX">电信充值卡</a>
   		</c:otherwise>
   	</c:choose>
	</div>
	<c:choose>
		<c:when test="${'YD' eq param.type }">
			 <div class="wap-pro">
			 	<p><img src="${pageContext.request.contextPath }/images/YD.png" width="120" height="30" /></p>
    			<p>请选择充值卡的面额：</p>
        		<p><a href="${pageContext.request.contextPath }/chargeMobileCard_YD?type=YD&totalAmount=30"  class="user-czk-btn">移动30元充值卡</a></p>
        		<p><a href="${pageContext.request.contextPath }/chargeMobileCard_YD?type=YD&totalAmount=50"  class="user-czk-btn">移动50元充值卡</a></p>
        		<p><a href="${pageContext.request.contextPath }/chargeMobileCard_YD?type=YD&totalAmount=100"  class="user-czk-btn">移动100元充值卡</a></p>
        		<p><a href="${pageContext.request.contextPath }/chargeMobileCard_YD?type=YD&totalAmount=300"  class="user-czk-btn">移动300元充值卡</a></p>
        		<p><a href="${pageContext.request.contextPath }/chargeMobileCard_YD?type=YD&totalAmount=500"  class="user-czk-btn">移动500元充值卡</a></p>
        		<p>提醒：请用户充值时,认真查看</p>
        		<p>1.移动充值卡支付目前支持:移动全国通用神州行充值卡，江苏、辽宁、浙江、福建移动本地充值卡.<br /> 
				   2.江苏移动充值卡:序列号为16位，密码为17位；面值是50或100元. <br /> 
				   3.辽宁移动充值卡:序列号为16位，密码为21位；面值是50或100元. <br /> 
				   4.浙江移动缴费券:序列号为10位，密码为8位；面值是50或100元.<br />  
				   5.福建移动呱呱通充值卡:序列号为16位，密码为17位；面值是50或100元. 
				</p>
    		</div>
		</c:when>
		<c:when test="${'LT' eq param.type }">
			 <div class="wap-pro">
			 	<p><img src="${pageContext.request.contextPath }/images/LT.png" width="120" height="30" /></p>
    			<p>请选择充值卡的面额：</p>
        		<p><a href="${pageContext.request.contextPath }/chargeMobileCard_LT?type=LT&totalAmount=20"  class="user-czk-btn">联通20元充值卡</a></p>
        		<p><a href="${pageContext.request.contextPath }/chargeMobileCard_LT?type=LT&totalAmount=30"  class="user-czk-btn">联通30元充值卡</a></p>
        		<p><a href="${pageContext.request.contextPath }/chargeMobileCard_LT?type=LT&totalAmount=50"  class="user-czk-btn">联通50元充值卡</a></p>
        		<p><a href="${pageContext.request.contextPath }/chargeMobileCard_LT?type=LT&totalAmount=100"  class="user-czk-btn">联通100元充值卡</a></p>
        		<p><a href="${pageContext.request.contextPath }/chargeMobileCard_LT?type=LT&totalAmount=300"  class="user-czk-btn">联通300元充值卡</a></p>
        		<p><a href="${pageContext.request.contextPath }/chargeMobileCard_LT?type=LT&totalAmount=500"  class="user-czk-btn">联通500元充值卡</a></p>
        		<p>提醒：请用户充值时,认真查看</p>
        		<p>联通充值卡支付目前支持：联通一卡充,卡号15位，密码19位；面值：20、30、50、100、300、500元.<br /> 
				</p>
    		</div>
		</c:when>
		<c:when test="${'DX' eq param.type }">
			 <div class="wap-pro">
			 	<p><img src="${pageContext.request.contextPath }/images/DX.png" width="120" height="30" /></p>
    			<p>请选择充值卡的面额：</p>
        		<p><a href="${pageContext.request.contextPath }/chargeMobileCard_DX?type=DX&totalAmount=10"  class="user-czk-btn">电信10元充值卡</a></p>
        		<p><a href="${pageContext.request.contextPath }/chargeMobileCard_DX?type=DX&totalAmount=20""  class="user-czk-btn">电信20元充值卡</a></p>
        		<p><a href="${pageContext.request.contextPath }/chargeMobileCard_DX?type=DX&totalAmount=30""  class="user-czk-btn">电信30元充值卡</a></p>
        		<p><a href="${pageContext.request.contextPath }/chargeMobileCard_DX?type=DX&totalAmount=50""  class="user-czk-btn">电信50元充值卡</a></p>
        		<p><a href="${pageContext.request.contextPath }/chargeMobileCard_DX?type=DX&totalAmount=100""  class="user-czk-btn">电信100元充值卡</a></p>
        		<p><a href="${pageContext.request.contextPath }/chargeMobileCard_DX?type=DX&totalAmount=200""  class="user-czk-btn">电信200元充值卡</a></p>
        		<p><a href="${pageContext.request.contextPath }/chargeMobileCard_DX?type=DX&totalAmount=300""  class="user-czk-btn">电信300元充值卡</a></p>
        		<p><a href="${pageContext.request.contextPath }/chargeMobileCard_DX?type=DX&totalAmount=500""  class="user-czk-btn">电信500元充值卡</a></p>
        		<p>提醒：请用户充值时,认真查看</p>
        		<p>电信全国通用卡，充值卡序列号19位，密码18位,支持面额10元,20元,30元,50元,100元,200元,300元,500元.<br /> 
				</p>
    		</div>
		</c:when>
	</c:choose>
   
    
   