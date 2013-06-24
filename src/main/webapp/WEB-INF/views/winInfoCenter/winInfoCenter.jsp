<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
  <div class="wap-banner" style="border-bottom:none">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><span>开奖公告</span></p>
    </div>
    <h1><a>福彩</a></h1>
    <div class="wap-pro-gray">
    	<c:set var="ssq" value="${fn:indexOf(ssqWinInfo.winCode, '|')}"></c:set>
    	<c:set var="ssq_end" value="${fn:length(ssqWinInfo.winCode)}"></c:set>
    	<p><a href="${pageContext.request.contextPath }/doubleBallIndex/bySelf">双色球</a>：${ssqWinInfo.batchCode }期（${ssqWinInfo.openTime }）</p>
        <p><span class="red-ball">${fn:substring(ssqWinInfo.winCode,0,ssq)}</span>|<span class="blue-ball">${fn:substring(ssqWinInfo.winCode,ssq+1,ssq_end)}</span></p>
        <p><a href="${pageContext.request.contextPath }/winInfo/selectWinInfo?lotNo=${ssqWinInfo.lotNo}&issue=${ssqWinInfo.batchCode}" class="jiange">详情</a>·<a href="${pageContext.request.contextPath }/winInfo/selectWinInfoList?lotNo=${ssqWinInfo.lotNo}&issueNum=10" class="jiange">历史</a>·<a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=${ssqWinInfo.lotNo }&batchCode=${ssqWinInfo.batchCode}&key=F47104MV_X" class="jiange">遗漏</a>·<a href="${pageContext.request.contextPath }/trendChart/trendChartIndex?type=R1&lotno=F47104" class="jiange">走势</a>·<a href="${pageContext.request.contextPath }/doubleBallIndex/bySelf" class="jiange">购买</a></p>
    </div>
    <div class="wap-pro-nogray">
    	<p><a href="${pageContext.request.contextPath }/fuCai3DIndex/byDirect">福彩3D</a>：${fc3dWinInfo.batchCode }期（${fc3dWinInfo.openTime }）</p>
        <p><span class="red-ball">${fc3dWinInfo.winCode }</span></p>
        <p><a href="${pageContext.request.contextPath }/winInfo/selectWinInfo?lotNo=${fc3dWinInfo.lotNo}&issue=${fc3dWinInfo.batchCode}" class="jiange">详情</a>·<a href="${pageContext.request.contextPath }/winInfo/selectWinInfoList?lotNo=${fc3dWinInfo.lotNo}&issueNum=10" class="jiange">历史</a>·<a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=${fc3dWinInfo.lotNo }&batchCode=${fc3dWinInfo.batchCode}&key=F47103MV_ZX" class="jiange">遗漏</a>·<a href="${pageContext.request.contextPath }/trendChart/trendChartIndex?type=R1&lotno=F47103" class="jiange">走势</a>·<a href="${pageContext.request.contextPath }/fuCai3DIndex/byDirect" class="jiange">购买</a></p>
    </div>
     <div class="wap-pro-gray">
     	<c:set var="qlc" value="${fn:indexOf(qlcWinInfo.winCode, '|')}"></c:set>
    	<c:set var="qlc_end" value="${fn:length(qlcWinInfo.winCode)}"></c:set>
    	<p><a href="${pageContext.request.contextPath }/qiLeCaiIndex/bySelf">七乐彩</a>：${qlcWinInfo.batchCode }期（${qlcWinInfo.openTime }）</p>
        <p><span class="red-ball">${fn:substring(qlcWinInfo.winCode,0,qlc)}</span>|<span class="blue-ball">${fn:substring(qlcWinInfo.winCode,qlc+1,qlc_end)}</span></p>
        <p><a href="${pageContext.request.contextPath }/winInfo/selectWinInfo?lotNo=${qlcWinInfo.lotNo}&issue=${qlcWinInfo.batchCode}" class="jiange">详情</a>·<a href="${pageContext.request.contextPath }/winInfo/selectWinInfoList?lotNo=${qlcWinInfo.lotNo}&issueNum=10" class="jiange">历史</a>·<a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=${qlcWinInfo.lotNo }&batchCode=${qlcWinInfo.batchCode}&key=F47102MV_X" class="jiange">遗漏</a>·<a href="${pageContext.request.contextPath }/trendChart/trendChartIndex?type=R1&lotno=F47102" class="jiange">走势</a>·<a href="${pageContext.request.contextPath }/qiLeCaiIndex/bySelf" class="jiange">购买</a></p>
    </div>
    <h1><a>体彩</a></h1>
    <div class="wap-pro-gray">
    	<p><a>大乐透</a>：${dltWinInfo.batchCode }期</p>
    	<c:set var="dlt" value="${fn:indexOf(dltWinInfo.winCode, '|')}"></c:set>
    	<c:set var="dlt_end" value="${fn:length(dltWinInfo.winCode)}"></c:set>
        <p><span class="red-ball">${fn:substring(dltWinInfo.winCode,0,dlt)}</span>|<span class="blue-ball">${fn:substring(dltWinInfo.winCode,dlt+1,dlt_end)}</span></p>
        <p><a href="${pageContext.request.contextPath }/winInfo/selectWinInfo?lotNo=${dltWinInfo.lotNo}&issue=${dltWinInfo.batchCode}" class="jiange">详情</a>·<a href="${pageContext.request.contextPath }/winInfo/selectWinInfoList?lotNo=${dltWinInfo.lotNo}&issueNum=10" class="jiange">历史</a>·<a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01001&key=T01001MV_X" class="jiange">遗漏</a>·<a href="${pageContext.request.contextPath }/trendChart/trendChartIndex?type=R1&lotno=T01001" class="jiange">走势</a>·<a href="${pageContext.request.contextPath }/daLeTouIndex/bySelf" class="jiange">购买</a></p>
    </div>
    <div class="wap-pro-nogray">
    	<p><a>排列三</a>：${plsWinInfo.batchCode }期</p>
        <p><span class="red-ball">${plsWinInfo.winCode }</span></p>
        <p><a href="${pageContext.request.contextPath }/winInfo/selectWinInfo?lotNo=${plsWinInfo.lotNo}&issue=${plsWinInfo.batchCode}" class="jiange">详情</a>·<a href="${pageContext.request.contextPath }/winInfo/selectWinInfoList?lotNo=${plsWinInfo.lotNo}&issueNum=10" class="jiange">历史</a>·<a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01002&key=T01002MV_ZX" class="jiange">遗漏</a>·<a href="${pageContext.request.contextPath }/trendChart/trendChartIndex?type=R1&lotno=T01002" class="jiange">走势</a>·<a href="${pageContext.request.contextPath }/array3Index/byDirect" class="jiange">购买</a></p>
    </div>
     <div class="wap-pro-gray">
    	<p><a>排列五</a>：${plwWinInfo.batchCode }期</p>
        <p><span class="red-ball">${plwWinInfo.winCode }</span></p>
        <p><a href="${pageContext.request.contextPath }/winInfo/selectWinInfo?lotNo=${plwWinInfo.lotNo}&issue=${plwWinInfo.batchCode}" class="jiange">详情</a>·<a href="${pageContext.request.contextPath }/winInfo/selectWinInfoList?lotNo=${plwWinInfo.lotNo}&issueNum=10" class="jiange">历史</a>·<a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01011&key=T01011MV_ZX" class="jiange">遗漏</a>·<a href="${pageContext.request.contextPath }/trendChart/trendChartIndex?type=R1&lotno=T01011" class="jiange">走势</a>·<a href="${pageContext.request.contextPath }/array5Index/bySelf" class="jiange">购买</a></p>
    </div>
     <div class="wap-pro-nogray">
    	<p><a>七星彩</a>：${qxcWinInfo.batchCode }期</p>
        <p><span class="red-ball">${qxcWinInfo.winCode }</span></p>
        <p><a href="${pageContext.request.contextPath }/winInfo/selectWinInfo?lotNo=${qxcWinInfo.lotNo}&issue=${qxcWinInfo.batchCode}" class="jiange">详情</a>·<a href="${pageContext.request.contextPath }/winInfo/selectWinInfoList?lotNo=${qxcWinInfo.lotNo}&issueNum=10" class="jiange">历史</a>·<a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01009&key=T01009MV_ZX" class="jiange">遗漏</a>·<a href="${pageContext.request.contextPath }/trendChart/trendChartIndex?type=R1&lotno=T01009" class="jiange">走势</a>·<a href="${pageContext.request.contextPath }/sevenStarIndex/bySelf" class="jiange">购买</a></p>
    </div>
     <div class="wap-pro-gray">
    	<p><a>22选5</a>：${s5f22WinInfo.batchCode }期</p>
        <p><span class="red-ball">${s5f22WinInfo.winCode }</span></p>
        <p><a href="${pageContext.request.contextPath }/winInfo/selectWinInfo?lotNo=${s5f22WinInfo.lotNo}&issue=${s5f22WinInfo.batchCode}" class="jiange">详情</a>·<a href="${pageContext.request.contextPath }/winInfo/selectWinInfoList?lotNo=${s5f22WinInfo.lotNo}&issueNum=10" class="jiange">历史</a>·<a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01013&key=T01013MV_X" class="jiange">遗漏</a>·<a href="${pageContext.request.contextPath }/trendChart/trendChartIndex?type=R1&lotno=T01013" class="jiange">走势</a>·<a href="${pageContext.request.contextPath }/select5From22Index/bySelf" class="jiange">购买</a></p>
    </div>
     <h1><a>高频彩</a></h1>
     <div class="wap-pro-gray">
    	<p><a>时时彩</a>：${sscWinInfo.batchCode }期</p>
        <p><span class="red-ball">${sscWinInfo.winCode }</span></p>
        <p><a href="${pageContext.request.contextPath }/winInfo/selectWinInfo?lotNo=${sscWinInfo.lotNo}&issue=${sscWinInfo.batchCode}" class="jiange">详情</a>·<a href="${pageContext.request.contextPath }/winInfo/selectWinInfoList?lotNo=${sscWinInfo.lotNo}&issueNum=10" class="jiange">历史</a>·<a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01007&key=T01007MV_5X&type=YX" class="jiange">遗漏</a>·<a href="${pageContext.request.contextPath }/shiShiCaiIndex/byOneStarSelf" class="jiange">购买</a></p>
    </div>
    <div class="wap-pro-nogray">
    	<p><a>江西11选5</a>：${jx11x5WinInfo.batchCode }期</p>
        <p><span class="red-ball">${jx11x5WinInfo.winCode }</span></p>
        <p><a href="${pageContext.request.contextPath }/winInfo/selectWinInfo?lotNo=${jx11x5WinInfo.lotNo}&issue=${jx11x5WinInfo.batchCode}" class="jiange">详情</a>·<a href="${pageContext.request.contextPath }/winInfo/selectWinInfoList?lotNo=${jx11x5WinInfo.lotNo}&issueNum=10" class="jiange">历史</a>·<a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01010&key=T01010MV_RX&type=rx2" class="jiange">遗漏</a>·<a href="${pageContext.request.contextPath }/trendChart/trendChartDetail?lotno=T01010&imgName=dlcRed1&type=1" class="jiange">走势</a>·<a href="${pageContext.request.contextPath }/jiangXiElevenSelectFiveIndex/byOptionOneSelf" class="jiange">购买</a></p>
    </div>
     <div class="wap-pro-gray">
    	<p><a>山东十一运夺金</a>：${sd11x5WinInfo.batchCode }期</p>
        <p><span class="red-ball">${sd11x5WinInfo.winCode }</span></p>
        <p><a href="${pageContext.request.contextPath }/winInfo/selectWinInfo?lotNo=${sd11x5WinInfo.lotNo}&issue=${sd11x5WinInfo.batchCode}" class="jiange">详情</a>·<a href="${pageContext.request.contextPath }/winInfo/selectWinInfoList?lotNo=${sd11x5WinInfo.lotNo}&issueNum=10" class="jiange">历史</a>·<a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01012&key=T01012MV_RX&type=rx2" class="jiange">遗漏</a>·<a href="${pageContext.request.contextPath }/trendChart/trendChartDetail?lotno=T01012&imgName=11ydjRed1&type=1" class="jiange">走势</a>·<a href="${pageContext.request.contextPath }/shanDongElevenDuoJinIndex/byOptionThreeSelf" class="jiange">购买</a></p>
    </div>
     <div class="wap-pro-nogray">
    	<p><a>广东11选5</a>：${gd11x5WinInfo.batchCode }期</p>
        <p><span class="red-ball">${gd11x5WinInfo.winCode }</span></p>
        <p><a href="${pageContext.request.contextPath }/winInfo/selectWinInfo?lotNo=${gd11x5WinInfo.lotNo}&issue=${gd11x5WinInfo.batchCode}" class="jiange">详情</a>·<a href="${pageContext.request.contextPath }/winInfo/selectWinInfoList?lotNo=${gd11x5WinInfo.lotNo}&issueNum=10" class="jiange">历史</a>·<a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01014&key=T01014MV_RX&type=rx2" class="jiange">遗漏</a>·<a href="${pageContext.request.contextPath }/trendChart/trendChartDetail?lotno=T01014&imgName=gd11x5Red1&type=1" class="jiange">走势</a>·<a href="${pageContext.request.contextPath }/guangDongElevenSelectFiveIndex/byOptionOneSelf" class="jiange">购买</a></p>
    </div>
    <div class="wap-pro-gray">
    	<p><a>广东快乐十分</a>：${gdkl10fWinInfo.batchCode }期</p>
        <p><span class="red-ball">${gdkl10fWinInfo.winCode }</span></p>
        <p><a href="${pageContext.request.contextPath }/winInfo/selectWinInfo?lotNo=${gdkl10fWinInfo.lotNo}&issue=${gdkl10fWinInfo.batchCode}" class="jiange">详情</a>·<a href="${pageContext.request.contextPath }/winInfo/selectWinInfoList?lotNo=${gdkl10fWinInfo.lotNo}&issueNum=10" class="jiange">历史</a> ·<a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01015&key=T01015MV_S1&type=s1" class="jiange">遗漏</a>·<a href="${pageContext.request.contextPath }/trendChart/trendChartDetail?lotno=T01015&imgName=kl10Red1&type=1" class="jiange">走势</a>·<a href="${pageContext.request.contextPath }/guangDongHappyTenMinutesIndex/bySelectOneNumberSelf" class="jiange">购买</a> </p>
    </div>
   <h1><a>竞技彩</a></h1>
   <div class="wap-pro-gray">
    	<p><a>足彩胜负彩</a>：${zcsfcWinInfo.batchCode }期</p>
        <p><span class="red-ball">${zcsfcWinInfo.winCode }</span></p>
        <p><a href="${pageContext.request.contextPath }/winInfo/selectWinInfo?lotNo=${zcsfcWinInfo.lotNo}&issue=${zcsfcWinInfo.batchCode}" class="jiange">详情</a>·<a href="${pageContext.request.contextPath }/winInfo/selectWinInfoList?lotNo=${zcsfcWinInfo.lotNo}&issueNum=10" class="jiange">历史</a>·<a href="${pageContext.request.contextPath }/footballIndex/byFootball?lotNo=T01003" class="jiange">购买</a></p>
    </div>
    <div class="wap-pro-nogray">
    	<p><a>足彩任选九</a>：${zcrx9WinInfo.batchCode }期</p>
        <p><span class="red-ball">${zcrx9WinInfo.winCode }</span></p>
        <p><a href="${pageContext.request.contextPath }/winInfo/selectWinInfo?lotNo=${zcrx9WinInfo.lotNo}&issue=${zcrx9WinInfo.batchCode}" class="jiange">详情</a>·<a href="${pageContext.request.contextPath }/winInfo/selectWinInfoList?lotNo=${zcrx9WinInfo.lotNo}&issueNum=10" class="jiange">历史</a>·<a href="${pageContext.request.contextPath }/footballIndex/byFootball?lotNo=T01004" class="jiange">购买</a></p>
    </div>
     <div class="wap-pro-gray">
    	<p><a>足彩六场半</a>：${zc6cbWinInfo.batchCode }期</p>
        <p><span class="red-ball">${zc6cbWinInfo.winCode }</span></p>
        <p><a href="${pageContext.request.contextPath }/winInfo/selectWinInfo?lotNo=${zc6cbWinInfo.lotNo}&issue=${zc6cbWinInfo.batchCode}" class="jiange">详情</a>·<a href="${pageContext.request.contextPath }/winInfo/selectWinInfoList?lotNo=${zc6cbWinInfo.lotNo}&issueNum=10" class="jiange">历史</a>·<a href="${pageContext.request.contextPath }/footballIndex/byFootball?lotNo=T01006" class="jiange">购买</a></p>
    </div>
     <div class="wap-pro-nogray">
    	<p><a>足彩四场进球</a>：${zcjqcWinInfo.batchCode }期</p>
        <p><span class="red-ball">${zcjqcWinInfo.winCode }</span></p>
        <p><a href="${pageContext.request.contextPath }/winInfo/selectWinInfo?lotNo=${zcjqcWinInfo.lotNo}&issue=${zcjqcWinInfo.batchCode}" class="jiange">详情</a>·<a href="${pageContext.request.contextPath }/winInfo/selectWinInfoList?lotNo=${zcjqcWinInfo.lotNo}&issueNum=10" class="jiange">历史</a>·<a href="${pageContext.request.contextPath }/footballIndex/byFootball?lotNo=T01005" class="jiange">购买</a></p>
    </div>
     <div class="wap-pro-gray"><p><a href="${pageContext.request.contextPath }/winInfo/selectJcSaiGuo?type=1" style="padding-left:20px">竞彩足球</a><a href="${pageContext.request.contextPath }/winInfo/selectJcSaiGuo?type=0" style="padding-left:20px">竞彩篮球</a></p></div>
