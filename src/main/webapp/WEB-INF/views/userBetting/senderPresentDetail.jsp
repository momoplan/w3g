<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/userCenter">用户中心</a>-</strong><span>赠送查询</span></p>
    </div>
 <c:choose>
 	<c:when test="${'sender' eq presentType }">
 		 <div class="wap-pro-gray tixian">
 		 	<a href="${pageContext.request.contextPath }/select/selectSenderPresent.do" class="tixian-cur">赠送彩票</a><span>|</span><a href="${pageContext.request.contextPath }/select/selectReciverPresent.do">收到彩票</a>
 		 </div>
 	</c:when>
 	<c:when test="${'reciver' eq presentType }">
 		 <div class="wap-pro-gray tixian">
 		 	<a href="${pageContext.request.contextPath }/select/selectSenderPresent.do" >赠送彩票</a><span>|</span><a href="${pageContext.request.contextPath }/select/selectReciverPresent.do" class="tixian-cur">收到彩票</a>
 		 </div>
 	</c:when>
 </c:choose>   
    <div class="user-moneychange" style="background:none">
    	     <p>受赠人：${mobile }</p>
             <p>购买金额：<span>${amt }</span></p>
             <p>彩票期号：${batchCode }</p>
             <p>彩票种类：${lotName }</p>
             <p>注数：${zhuShu }</p>
             <p>倍数：${beiShu }</p>
             <p>赠彩日期：${createTime }</p>
             <div class="xuxian-line"></div>
            <p>方案内容：<br /></p>
            <p class="playball"><span>${betCode }</span></p>
             <c:choose>
        	<c:when test="${not empty winCode && ' ' != winCode}">
        		<p>开奖号码：${winCode }</p>
        	</c:when>
        	<c:otherwise>
        	</c:otherwise>
        </c:choose>
              <c:choose>
        		<c:when test="${'已中奖' eq prizeState }">
        			<p>中奖金额：<span>${orderPrize }元</span></p>
        		</c:when>
        		<c:otherwise>
        			<p>中奖金额：${prizeState }</p>
        		</c:otherwise>
        	</c:choose>
    </div>