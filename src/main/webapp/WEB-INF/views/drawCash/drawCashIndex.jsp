<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  	  <div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span>账户提现</span></p>
    </div>
    <div class="wap-pro-gray tixian"><a class="tixian-cur">账户提现</a><span>|</span><a href="${pageContext.request.contextPath }/drawCash/drawCashHistory.do">提现记录</a></div>
    <div class="wap-pro zhuangzhang-art">
    	 <p class="prompt-pro">${messageError }</p>
    	<form action="${pageContext.request.contextPath }/drawCash/drawCashDetail.do" method="post">
    	<p>可提现余额：<em>${drawAccount }</em>元</p>
    	<p>温馨提示：<em>持卡人名必须与用户信息绑定的真实姓名一致！</em></p>
    	<input name="drawAccount" type="hidden" value="${drawAccount }"/>
    	<c:choose>
    		<c:when test="${not empty drawCashName }">
    			<p>持卡人名：${drawCashName }</p>
    			<input name="name" type="hidden" value="${drawCashName }"/>
    		</c:when>
    		<c:otherwise>
    			<p>持卡人名：<input name="name" type="text" value="${name }"/></p>
    		</c:otherwise>
    	</c:choose>
        <p>提现金额：<input name="amount" type="text" value="${amount }"/>元</p>
        <p>登录密码：<input name="passWord" type="text" value="${passWord }" /><br /><span class="denglumima">您的如意彩登录密码</span></p>
        <p>银行卡号：<input name="bankNumber" type="text" value="${bankNumber }"/></p>
        <p>开卡银行：
        	<select name="bankName">
        		<option value="00">请选择银行</option>
            	<option value="china_gongshang">中国工商银行</option>
				<option value="china_nongye">中国农业银行</option>
				<option value="china_jianshe">中国建设银行</option>
				<option value="china_minsheng">中国民生银行</option>
				<option value="zhaoshang">招商银行</option>
				<option value="china_youzhengchuxu">中国邮政储蓄银行</option>
				<option value="jiaotong">交通银行</option>
				<option value="xingye">兴业银行</option>
				<option value="zhongxin">中信银行</option>
				<option value="china_guangda">中国光大银行</option>
				<option value="guangdong_fazhan">广东发展银行</option>
				<option value="shanghaipudong_fazhan">上海浦东发展银行</option>
				<option value="shenzhen_fazhan">深圳发展银行</option>
				<option value="hangzhou">杭州银行</option>
            </select>
        
        </p>
        <p><input type="submit" value="我要提现" class="tixian-btn" /></p>
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
