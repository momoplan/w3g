<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	 <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span><a href="${pageContext.request.contextPath }/chargeCenter">账户充值</a></span></p>
    </div>
    <h1><a>银联语音充值</a></h1>
    <p class="prompt-pro">${messageError }</p>
	<form action="${pageContext.request.contextPath }/chargeCenter/chargeUnionPay.do" method="post">
    <table width="245"  cellspacing="0" cellpadding="0" class="user-table">
  <tr>
    <td width="108">充值金额：</td>
    <td><input name="amount" type="text" class="tr-1" value="${amount }"/>元</td>
  </tr>
  <tr>
    <td>选择银行：</td>
    <td>
    	<select name="bankType">
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
			
       </select>
    </td>
  </tr>
  <tr class="coltwo">
    <td colspan="2">(仅支持以上银行的借记卡)</td>
    
    </tr>
   <tr>
    <td>银行卡号：</td>
    <td><input name="cardNo" type="text" value="${cardNo }"/></td>
  </tr>
  <tr>
    <td>开卡人姓名：</td>
    <c:choose>
    	<c:when test="${not empty chargeName }">
    		<td>${chargeName }</td>
    		<input name="name" type="hidden" class="tr-1" value="${chargeName }" />
    	</c:when>
    	<c:otherwise>
    		<td><input name="name" type="text" class="tr-1" value="${name }" /></td>
    	</c:otherwise>
    </c:choose>
  </tr>
  <tr>
    <td>开卡人身份证号：</td>
    <td><input name="documentNumber" type="text" value="${documentNumber }"/></td>
  </tr>
  <tr>
    <td>身份证户籍所在地：</td>
    <td><input name="documentAddress" type="text" value="${documentAddress }"/></td>
  </tr>
  <tr class="coltwo">
    <td colspan="2">(山东省青岛市XX街XX路XX号)</td>
  </tr>
  <tr>
  <tr>
    <td>银行卡开户省份：</td>
    <td><input name="accountAddress1" type="text" value="${accountAddress1 }"/>省</td>
  </tr>
  <tr>
    <td>银行卡开户城市：</td>
    <td><input name="accountAddress2" type="text" value="${accountAddress2 }"/>市</td>
  </tr>
  <tr>
    <td>电话号码：</td>
    <td><input name="mobileId" type="text" value="${mobileId }"/></td>
  </tr>
   <tr class="coltwo">
    <td colspan="2">(为确保接听银联电话,请填写真实号码)</td>
   </tr>
  
</table>
<p><input type="submit" value="提交信息" class="tijiao-btn" /></p>
</form>
   