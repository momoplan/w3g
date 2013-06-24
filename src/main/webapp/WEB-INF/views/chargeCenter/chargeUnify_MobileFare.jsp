<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span><a href="${pageContext.request.contextPath }/chargeCenter">账户充值</a></span></p>
    </div>
    <h1><a>移动手机话费充值</a></h1>
    <div class="wap-pro user-addmoney">
    	<img src="${pageContext.request.contextPath }/images/sj.png" width="120" height="30" />
   	    <p class="prompt-pro">${messageError }</p>
   	    <form action="${pageContext.request.contextPath }/chargeCenter/chargeUnify_MobileFare.do" method="post">
   	    	<p>1.填写移动手机号：</p>
   	    	<p>手机号:<input name="mobile" type="text" value="${mobile }"/></p>
   	        <p>2.请选择充值金额：</p>
   	        <p><input type="submit" name="submitType" class="user-czk-btn" style="width:150px;" value="30元话费充20元彩金" /></p>
        	<p><input type="submit" name="submitType" class="user-czk-btn" style="width:150px;" value="3元话费充2元彩金" /></p>
        </form>
    </div>
    <div class="wap-pro-gray" style="border-top:1px solid #d6d6d3; margin-top:15px;">
               <p>提醒：请用户充值时,认真查看.</p>  	
             <p>1、仅支持移动手机用户；</p>
             <p>2、充值有3元和30元两种，移动3元话费充值，得2元彩金， 移动30元话费充值，得20元彩金； </p>
             <p>3、额度：日限额50元，月限额100元；</p>
             <p>4、该充值彩金不可提现，中奖金额可提现；</p>
             <p>5、扣除话费与充值彩金之间差额由运营商或充值服务商收取，如意彩不收取任何费用；</p>
             <p>6、联动优势客服热线：400-612-5880；</p>
             <p>7、如意彩客服热线：400-665-1000。</p>

    
    </div>
   