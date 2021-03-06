 <%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
      <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><span>广东11选5</span></p>
    </div>
    
    <h1><a href="${pageContext.request.contextPath }/guangDongElevenSelectFiveIndex/byOptionOneSelf" >任一</a><span>|</span><a class="tit-cur">任二</a><span>|</span><a href="${pageContext.request.contextPath }/guangDongElevenSelectFiveIndex/byOptionThreeSelf">任三</a><span>|</span><a href="${pageContext.request.contextPath }/guangDongElevenSelectFiveIndex/byOptionFourSelf">任四</a><span>|</span><a href="${pageContext.request.contextPath }/guangDongElevenSelectFiveIndex/byOptionFiveSelf">任五</a><span>|</span><a href="${pageContext.request.contextPath }/guangDongElevenSelectFiveIndex/byOptionSixSelf">任六</a><span>|</span><a href="${pageContext.request.contextPath }/guangDongElevenSelectFiveIndex/byOptionSevenSelf">任七</a><span>|</span><a href="${pageContext.request.contextPath }/guangDongElevenSelectFiveIndex/byOptionEightSelf">任八</a></h1>
    <h3><a href="${pageContext.request.contextPath }/guangDongElevenSelectFiveIndex/byOptionTwoSelf"> 直选</a><span>|</span><a  class="tittwo-cur">前二直选</a><span>|</span><a href="${pageContext.request.contextPath }/guangDongElevenSelectFiveIndex/byForwardTwoGroupSelect">前二组选</a><span>|</span><a href="${pageContext.request.contextPath }/guangDongElevenSelectFiveIndex/byOptionTwoDanTuo">胆拖</a><span>|</span><a href="${pageContext.request.contextPath }/guangDongElevenSelectFiveIndex/byForwardTwoGroupSelectDanTuo">组选胆拖</a><span>|</span><a href="${pageContext.request.contextPath }/guangDongElevenSelectFiveIndex/byOptionTwoAuto">机选</a></h3>
    <div class="wap-banner">
    	<p>10分钟一期，每天84期。</p>
        <p>剩余时间：<em class="end-time">${remainingTime }</em></p>
        <p class="prompt-pro">${messageError }</p>
    </div>
            <form action="${pageContext.request.contextPath }/guangDongElevenSelectFive/byForwardTwoDirectToSelect" method="post">
    <div class="wap-pro-goucai">
		<p style="font-weight: bold;">第${batchCode }期</p>
	</div>
	<input name="batchCode" value="${batchCode }" type="hidden"/>
    
    
    <h2 class="goucai-title"><span class="red-ball-num">第一位号码：</span>（至少选择1个号码）</h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p no-red">
<c:forEach var="item" varStatus="status" begin="1" end="11">
			<c:set var="index" value="ball1_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="ball1" id="ball1_${status.index}" value="${status.index}" ${ball1s[index] !=null ? 'checked' :''}
			/><c:choose><c:when test="${ status.index<10}">0${status.index}</c:when
			><c:otherwise>${status.index}</c:otherwise></c:choose>
			</span>
			<c:if test="${status.index%7==0 }">
				<br/>
			</c:if>
			</c:forEach>           
            
        </p>
        <p>选择<input name="ballNumber1" type="text" class="num-ball" value="${ballNumber1 }" maxlength="2"/>个<input name="submitType" type="submit" value="机选一位码" class="jx-redball" /></p>
    </div>
    <h2 class="goucai-title"><span class="red-ball-num">第二位号码：</span>（至少选择1个号码，两位数量和不超过11）</h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p no-red">
<c:forEach var="item" varStatus="status" begin="1" end="11">
			<c:set var="index" value="ball2_${status.index }"></c:set>
			<span>
			<input type="checkbox" name="ball2" id="ball2_${status.index}" value="${status.index}" ${ball2s[index] !=null ? 'checked' :''}
			/><c:choose><c:when test="${ status.index<10}">0${status.index}</c:when
			><c:otherwise>${status.index}</c:otherwise></c:choose>
			</span>
			<c:if test="${status.index%7==0 }">
				<br/>
			</c:if>
			</c:forEach>            
            
        </p>
        <p>选择<input name="ballNumber2" type="text" class="num-ball" value="${ballNumber2 }" maxlength="2"/>个<input name="submitType" type="submit" value="机选二位码" class="jx-redball" /></p>
    </div>
   
   
     <div class="wap-pro-gray-goucai" style="border-top:1px solid #999; border-bottom:none">
     		<p>倍数：<input name="beiShu" type="text" class="num-ball" value="${beiShu }" maxlength="5"/>（最多10000倍）</p>
            <p>追号：<input name="addNumber" type="text" class="num-ball" value="${addNumber }" maxlength="2"/>期<input name="prizeend" type="checkbox" value="1" style="width:12px; height:12px; vertical-align:middle; margin-left:30px;" ${'1' eq prizeend ?'checked' :'' }/>中奖后停止</p>
    </div>
    <p class="moneey-dlt">投注号码与开奖号码前两位相同中<span>130</span>元</p>
<p class="gx-tjiao"><input name="submitType" type="submit" value="提交投注" class="tz-chakan-btn" /></p>
   </form> 