<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	 <div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span>账户充值</span></p>
    </div>
    <div class="wap-pro-gray wap-pro-nolinegray">
    	<p class="prompt-pro">${messageError }</p>
    	<p>完成充值,即可感受彩票激情魅力.百万大奖,正在降临!</p>
    </div>
    <h1><a>支付宝充值</a></h1>
    <div class="wap-pro"><p><a href="${pageContext.request.contextPath }/chargeAlipay_Wap">支付宝wap充值</a><em>|</em><a href="${pageContext.request.contextPath }/chargeAlipay_KJ">支付宝快捷充值</a>(无支付宝账户也可以充值)</p></div>
    <h1><a>银联语音充值</a></h1>
    <div class="wap-pro"><p><a href="${pageContext.request.contextPath }/chargeCenter/findDNAtoCharge.do">支持工、农、建、招、邮政、华夏、兴业、中信、深发银行</a></p></div>
    <h1><a>联动优势充值</a></h1>
    <div class="wap-pro"><p><a href="${pageContext.request.contextPath }/chargeUnify_Wap">联动优势wap充值</a><em>|</em><a href="${pageContext.request.contextPath }/chargeUnify_MobileFare">移动手机话费充值</a></p></div>
    <h1><a>充值卡充值</a></h1>
    <div class="wap-pro"><p><a href="${pageContext.request.contextPath }/chargeMobileCard?type=YD">移动充值卡</a><em>|</em><a href="${pageContext.request.contextPath }/chargeMobileCard?type=LT">联通充值卡</a><em>|</em><a href="${pageContext.request.contextPath }/chargeMobileCard?type=DX">电信充值卡</a></p></div>
    <h1><a>银行转账</a></h1>
    <div class="wap-pro"><p><a href="${pageContext.request.contextPath }/chargeBankPayment">通过银行柜台、ATM或者网上银行转帐</a></p></div>
	<div class="wap-pro-gray" style="border-top:1px solid #d6d6d3; border-bottom:none">
    	<p>小提示:目前支付宝和招行手机银行暂不支持部分手机,如无法使用请改用其它充值方式或登录如意彩web网站www.ruyicai.com进行充值</p>
        <p>1.如意彩是中国领先的电子彩票投注平台,拥有雄厚的实力和先进的彩票投注技术,并为用户提供一贯的诚信服务.</p>
        <p>2.您的账户资金存放于国家福彩中心指定的银行账户,由福彩中心资金帐户管理系统进行管理,切实保障您的资金安全.</p>
        <p>3.如意彩为支付宝信任商家和签约合作伙伴,请放心充值.</p>
        <p>4.如意彩为中国银联手机支付合作伙伴,请放心充值.</p>
        <p>5.银行卡电话充值,支付宝充值,手机银行充值免手续费.</p>
        <p>6.移动充值卡、联通充值卡、电信充值卡4%手续费.</p>
    </div>
   <h1><a href="${pageContext.request.contextPath }/helpCenter/selectHelpList">帮助中心</a></h1>
   <div class="wap-pro-gray">
   		<ul class="helpcenter-pro">
        	<li>
        		<a href="${pageContext.request.contextPath }/helpCenter/selectHelpList?type=1">功能指引</a> 
        		<a href="${pageContext.request.contextPath }/helpCenter/selectHelpList?type=2">特色功能</a>
        		<a href="${pageContext.request.contextPath }/helpCenter/selectHelpList?type=3">彩票玩法</a> 
        	</li>
        	<li>
        		<a href="${pageContext.request.contextPath }/helpCenter/selectHelpList?type=4">常见问题</a>
				<a href="${pageContext.request.contextPath }/helpCenter/selectHelpList?type=5">彩票术语</a>
        	</li>
        </ul>
   </div>
   