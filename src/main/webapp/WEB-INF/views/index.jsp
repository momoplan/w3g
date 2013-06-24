<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div class="wap-banner">
		<p><span><a>[活动]</a></span><a href="${pageContext.request.contextPath }/hot" style="font-weight: bold;">如意彩新用户充20元送10元</a><img src="${pageContext.request.contextPath }/images/hot.png" /></p>
		<!-- <p><span style="color:#FC9400">【新】</span><a href="${pageContext.request.contextPath }/downLoadServlet">点击下载网站桌面快捷方式</a></p> -->
		<c:if test="${not empty announcementNewsList }">
			<c:forEach items="${announcementNewsList }" var="announcementNews">
				<p><span><a>[公告]</a></span><a href="${pageContext.request.contextPath }/news/selectNews?id=${announcementNews.id}&type=4">${announcementNews.vol_title }</a></p>
			</c:forEach>
		</c:if>
		<c:if test="${not empty activityNewsList }">
			<c:forEach items="${activityNewsList }" var="activityNews">
				<p><span><a>[活动]</a></span><a href="${pageContext.request.contextPath }/news/selectActivityNews?id=${activityNews.id}">${activityNews.title}</a></p>
			</c:forEach>
		</c:if>
        <p class="pingtai"><span class="Android"><em><a href="client/down/android/ruyicai_android_3.8.1_533_wapguanwang.apk">Android</a></em></span><span class="win8"><em><a href="http://www.windowsphone.com/zh-cn/store/app/%E5%A6%82%E6%84%8F%E5%BD%A9/b0bda9c5-2887-47e2-a487-ac9f481e8632">Wp8</a></em></span><br /><span class="iphone"><em><a href="http://itunes.apple.com/cn/app/ru-yi-cai-nin-shen-bian-cai/id492164095?mt=8">Iphone</a></em></span><span class="saiban"><em><a href="client/down/symbian/ruyicai_S60_V5_1.2_779_gwwap.sisx">S60_v5</a></em></span></p>
    </div>
    <h1><a href="${pageContext.request.contextPath }/lotteryCenter">购彩</a><span>|</span><a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLot">合买</a></h1>
    <div class="wap-pro">
    	<p><span class="pro-name">福彩：</span><a href="${pageContext.request.contextPath }/doubleBallIndex/bySelf">双色球</a><img alt="" src="${pageContext.request.contextPath }/word_images/re_d.gif"><em>|</em><a href="${pageContext.request.contextPath }/fuCai3DIndex/byDirect">福彩3D</a><img alt="" src="${pageContext.request.contextPath }/word_images/song_d.gif"><em>|</em><a href="${pageContext.request.contextPath }/qiLeCaiIndex/bySelf">七乐彩</a></p>
        <p><span class="pro-name">体彩：</span><a href="${pageContext.request.contextPath }/daLeTouIndex/bySelf">大乐透</a><img alt="" src="${pageContext.request.contextPath }/word_images/jiajiang_d.gif"><em>|</em><a href="${pageContext.request.contextPath }/array3Index/byDirect">排列三</a><em>|</em><a href="${pageContext.request.contextPath }/array5Index/bySelf">排列五</a><em>|</em><a href="${pageContext.request.contextPath }/sevenStarIndex/bySelf">七星彩</a><em>|</em><a href="${pageContext.request.contextPath }/select5From22Index/bySelf">22选5</a></p>
       <p><span class="pro-name">高频：</span><a href="${pageContext.request.contextPath }/shiShiCaiIndex/byOneStarSelf">时时彩</a><img alt="" src="${pageContext.request.contextPath }/word_images/song_d.gif"><em>|</em><a href="${pageContext.request.contextPath }/jiangXiElevenSelectFiveIndex/byOptionOneSelf">江西11选5</a><em>|</em><a href="${pageContext.request.contextPath }/shanDongElevenDuoJinIndex/byOptionThreeSelf">11运夺金</a><em>|</em><a href="${pageContext.request.contextPath }/guangDongElevenSelectFiveIndex/byOptionOneSelf">广东11选5</a><em>|</em><a href="${pageContext.request.contextPath }/guangDongHappyTenMinutesIndex/bySelectOneNumberSelf">广东快乐十分</a><img alt="" src="${pageContext.request.contextPath }/word_images/new_d.gif"></p>
        <p><span class="pro-name">足彩：</span><a href="${pageContext.request.contextPath }/footballIndex/byFootball?lotNo=T01003">胜负彩</a><em>|</em><a href="${pageContext.request.contextPath }/footballIndex/byFootball?lotNo=T01004">任选九场</a><em>|</em><a href="${pageContext.request.contextPath }/footballIndex/byFootball?lotNo=T01006">六场半</a><em>|</em><a href="${pageContext.request.contextPath }/footballIndex/byFootball?lotNo=T01005">四场进球</a></p>
        <p><span class="pro-name">竞彩：</span><a href="${pageContext.request.contextPath }/jcFootballIndex/byJcFootBall?type=1&valueType=1&wanFa=FT001">竞彩足球</a><img alt="" src="${pageContext.request.contextPath }/word_images/song_d.gif"><em>|</em><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=1&wanFa=BSK001">竞彩篮球</a><img alt="" src="${pageContext.request.contextPath }/word_images/song_d.gif"></p>
    </div>
   <h1><a href="${pageContext.request.contextPath }/winInfo/selectWinInfoCenter">开奖</a><span>|</span><a href="${pageContext.request.contextPath }/winningRankingHistory/userRankingHistory">排行</a><span>|</span><a href="${pageContext.request.contextPath }/trendChart_index">走势</a><span>|</span><a href="${pageContext.request.contextPath }/leaveOut_index">遗漏</a></h1>
   <div class="wap-pro cur-pro">
   		<c:if test="${not empty ssqWinInfo }">
   			 <p><span>[双色球]</span>${ssqWinInfo.winCode }</p>
   		</c:if>
        <c:if test="${not empty fc3dWinInfo }">
        	<p><span>[福彩3D]</span>${fc3dWinInfo.winCode }</p>
        </c:if>
         <c:if test="${not empty dltWinInfo }">
         	<p><span>[大乐透]</span>${dltWinInfo.winCode }</p>
         </c:if>
         
     </div>
   <h1><a href="${pageContext.request.contextPath }/chargeCenter">在线充值</a></h1>
   <div class="wap-pro">
     	 <p>
             <a href="${pageContext.request.contextPath }/chargeCenter/findDNAtoCharge.do" style="padding-right:30px">银联语音</a><a href="${pageContext.request.contextPath }/chargeAlipay_Wap">支付宝</a><br />
             <a href="${pageContext.request.contextPath }/chargeUnify_Wap" style="padding-right:30px">联动优势</a><a href="${pageContext.request.contextPath }/chargeMobileCard?type=YD">充值卡</a><br />
         	 <a href="${pageContext.request.contextPath }/chargeBankPayment" style="padding-right:30px">银行转账</a><a href="${pageContext.request.contextPath }/chargeUnify_MobileFare">手机话费</a><img alt="" src="${pageContext.request.contextPath }/word_images/new_d.gif">
         	 <br />
         </p>
   </div>
   <h1><a href="${pageContext.request.contextPath }/news/selectNewsList">资讯</a><span>|</span><a href="${pageContext.request.contextPath }/news/selectActivityNewsList?type=6">活动</a><span>|</span><a href="${pageContext.request.contextPath }/news/selectNewsList?type=2">推荐</a></h1>
   <div class="wap-pro">
   	  <c:if test="${not empty informationNewsList }">
   	  	<c:forEach items="${informationNewsList }" var="informationNews">
   	  		<p><a href="${pageContext.request.contextPath }/news/selectNews?id=${informationNews.id}&type=1">${informationNews.vol_title}</a></p>
   	  	</c:forEach>
   	  </c:if>
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
   <div class="wap-banner" style="border-top:1px solid #d6d6d6;">
    	<p>您当前位置为<span style="color:#FC9400">新版如意彩</span>&nbsp;&nbsp;&nbsp;&nbsp;<a href="http://wap.ruyicai.com" style="font-weight:bold">【旧版如意彩】</a></p>
    </div>