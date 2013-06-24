<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
 <div class="wap-banner yellowline">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/winInfo/selectWinInfoCenter">开奖公告</a>-</strong><span>开奖详情</span></p>
    </div>
    <div class="wap-pro">
    	<p>彩种：${winInfo.lotName }</p>
        <p>期号：${winInfo.batchCode }</p>
        <p>开奖时间：${winInfo.openTime }</p>
        <p>开奖号码：${winInfo.winCode }</p>
       
    </div>
    <c:if test="${not empty winInfo.info }">
    	<h2 class="goucai-title">中奖详情</h2>
   			<table width="245" border="0" cellspacing="0" cellpadding="0" class="jifen-table paihang-table" >
  				 ${winInfo.info }
			</table>
    </c:if>
   <div class="wap-pro">
        <p><a href="${pageContext.request.contextPath }/winInfo/selectWinInfoCenter" class="fanhui">返回上页</a><a href="${pageContext.request.contextPath }/lotteryCenter" class="fanhui">立即购彩</a></p>
    </div>