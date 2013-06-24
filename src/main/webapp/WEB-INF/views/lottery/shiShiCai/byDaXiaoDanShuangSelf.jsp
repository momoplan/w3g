 <%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><span>时时彩</span></p>
    </div>
    
    <h1><a href="${pageContext.request.contextPath }/shiShiCaiIndex/byOneStarSelf">一星</a><span>|</span><a href="${pageContext.request.contextPath }/shiShiCaiIndex/byTwoStarDirect">二星</a><span>|</span><a href="${pageContext.request.contextPath }/shiShiCaiIndex/byThreeStarDirect">三星</a><span>|</span><a href="${pageContext.request.contextPath }/shiShiCaiIndex/byFiveStarDirect">五星</a><span>|</span><a class="tit-cur">大小单双</a></h1>
    
    <div class="wap-banner">
    	<p>白天 10:00至22:00　每10分钟一期，共72期　夜间22:00至次日01:55　每5分钟一期，共48期，单注最高奖金10万！</p>
        <p>剩余时间：<em class="end-time">${remainingTime }</em></p>
        <p class="prompt-pro">${messageError }</p>
    </div>
    <form action="${pageContext.request.contextPath }/shiShiCai/byDaXiaoDanShuangSelf" method="post">
    <div class="wap-pro-goucai">
		<p style="font-weight: bold;">第${batchCode }期</p>
	</div>
	<input name="batchCode" value="${batchCode }" type="hidden"/>
    <h2 class="goucai-title"><span class="red-ball-num">十位号码：</span>（选择1个号码）</h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p no-red">
        	<span><input name="sball" type="checkbox" value="2" ${'2' eq sball ? 'checked':'' }/>大</span>
        	<span><input name="sball" type="checkbox" value="1" ${'1' eq sball ? 'checked':'' }/>小</span>
            <span><input name="sball" type="checkbox" value="5" ${'5' eq sball ? 'checked':'' }/>单</span>
            <span><input name="sball" type="checkbox" value="4" ${'4' eq sball ? 'checked':'' }/>双</span>
            
        </p>
    </div>
    <h2 class="goucai-title"><span class="red-ball-num">个位号码：</span>（选择1个号码）</h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p no-red">
        	<span><input name="gball" type="checkbox" value="2" ${'2' eq gball ? 'checked':'' }/>大</span>
        	<span><input name="gball" type="checkbox" value="1" ${'1' eq gball ? 'checked':'' }/>小</span>
            <span><input name="gball" type="checkbox" value="5" ${'5' eq gball ? 'checked':'' }/>单</span>
            <span><input name="gball" type="checkbox" value="4" ${'4' eq gball ? 'checked':'' }/>双</span>
            
        </p>
    </div>
   
    <div class="wap-pro-gray-goucai" style="border-top:1px solid #999; border-bottom:none">
     		<p>倍数：<input name="beiShu" type="text" class="num-ball" value="${beiShu }" maxlength="5"/>（最多10000倍）</p>
            <p>追号：<input name="addNumber" type="text" class="num-ball" value="${addNumber }" maxlength="2"/>期<input name="prizeend" type="checkbox" value="1" style="width:12px; height:12px; vertical-align:middle; margin-left:30px;" ${'1' eq prizeend ?'checked' :'' }/>中奖后停止</p>
   		</div>
    <p class="moneey-dlt">投注内容与开奖结果一致即中奖。奖金<span>4</span>元</p>
<p class="gx-tjiao"><input name="submitType" type="submit" value="提交投注" class="tz-chakan-btn" /></p>
   </form> 