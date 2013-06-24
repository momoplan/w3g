<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>
<link href="${pageContext.request.contextPath }/css/wap-ruyicai.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath }/images/favicon.ico" media="screen" />
</head>

<body>
    <div class="logo-top"></div>
   <div class="wap-logo">
   		<c:choose>
   			<c:when test="${not empty viewTopName }">
   			<span><a>${viewTopName }您好！</a><em>|</em><a href="${pageContext.request.contextPath }/userInfo/loginOut.do">退出</a></span>
   			</c:when>
   			<c:otherwise>
   				<span><a href="${pageContext.request.contextPath }/login">登录</a><em>|</em><a href="${pageContext.request.contextPath }/userInfo/registerIndex">注册</a></span>
   			</c:otherwise>
   		</c:choose>
    	<a href="${pageContext.request.contextPath }"><img src="${pageContext.request.contextPath }/images/3g_ruyicai.png" width="120" height="30" alt="手机如意彩" /></a>
    </div>
    <c:if test="${not empty viewTopName }">
    	<ul class="money-cur"><li class="Left">可用余额：<span>${betBalance }</span>元</li><li class="Right">冻结金额：<span>${freezeBalance }</span>元</li></ul>
    </c:if>
     <div class="wap-nav">
        <p><a href="${pageContext.request.contextPath }/lotteryCenter">购彩</a><span>|</span><a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLot">合买</a><span>|</span><a href="${pageContext.request.contextPath }/chargeCenter">充值</a><span>|</span><a href="${pageContext.request.contextPath }/downLoad">下载</a><span>|</span><a href="${pageContext.request.contextPath }/helpCenter/selectHelpList">帮助</a><span>|</span><a href="${pageContext.request.contextPath }/leaveMessage">留言</a></p>
        
        <p class="line-two"><a href="${pageContext.request.contextPath }/winInfo/selectWinInfoCenter">开奖</a><span>|</span><a href="${pageContext.request.contextPath }/trendChart_index">走势</a><span>|</span><a href="${pageContext.request.contextPath }/leaveOut_index">遗漏</a><span>|</span><a href="${pageContext.request.contextPath }/winningRankingHistory/userRankingHistory">排行</a><span>|</span><a href="${pageContext.request.contextPath }/news/selectNewsList">资讯</a><span>|</span><a href="${pageContext.request.contextPath }/userCenter">账户</a></p>
	</div>