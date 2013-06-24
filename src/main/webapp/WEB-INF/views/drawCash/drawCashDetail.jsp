<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  	<div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span>账户提现</span></p>
    </div>
    <div class="wap-pro zhuangzhang-art">
    	<form action="${pageContext.request.contextPath }/drawCash/drawCash.do" method="post">
    	<p>可提现余额：<em>${drawAccount }</em>元</p>
    	<input name="drawAccount" type="hidden" value="${drawAccount }"/>
        <p>持卡人名：${name }</p>
        <input name="name" type="hidden" value="${name }"/>
        <p>提现金额：${amount }元</p>
        <input name="amount" type="hidden" value="${amount }"/>
        <p>银行卡号：${bankNumber }</p>
        <input name="bankNumber" type="hidden" value="${bankNumber }"/>
        <p>开卡银行：${bankName }</p>
        <input name="bankName" type="hidden" value="${bankName }"/>
        <p><input type="submit" value="确认提现" class="tixian-btn" /></p>
        </form>
    </div>
    
    <div class="wap-pro">
    	<p>提款须知：<br /> 
 （1）提现记录中的字段分别为：提现时间、提现金额、提现状态、操作（web上有订单号）；提现状态有五种，分别为：待审核、已审核、驳回、成功、取消；待审核状态的提现可以做撤销操作，被驳回的提现会以短信的形式通知用户。 <br />
 （2）提现金额精确到分，如可以提现14.59元或1.58元。 <br />
 （3）可提现金额小于10元时，申请提现时，需要一次性提清。 <br />
 提现只能提到银行卡上，暂不支持信用卡提现。 
 "为了防止少数用户利用信用卡套现和洗钱行为，保证正常用户的资金安全，本软件针对提款做出以下规定：累计充值资金消费未满30%，可提现金额为累计充值资金的70%；累计充值资金消费达到30%，不受此限制。" <br />
 （4）提现没有次数限制。 <br />
 （5）提现受理银行有：中国工商银行、中国农业银行、中国建设银行、中国民生银行、招商银行、中国邮政储蓄银行、交通银行、兴业银行、中信银行、中国光大银行、广东发展银行、上海浦东发展银行、深圳发展银行、杭州银行，共14家。<br />
</p>
    </div>
