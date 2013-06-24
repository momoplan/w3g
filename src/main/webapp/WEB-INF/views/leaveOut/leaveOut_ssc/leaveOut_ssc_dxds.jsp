<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/leaveOut_index">遗漏值</a>-</strong><span>时时彩</span></p>
    </div>
    <h1><a>时时彩</a></h1>
   	<h5>五星：<a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01007&key=T01007MV_5X&type=WXZhX">五星直选</a><span>|</span><a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01007&key=T01007MV_5X&type=WXTX">五星通选</a></h5>
    <h5>三星：<a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01007&key=T01007MV_5X&type=SXZhX">三星直选</a></h5>
    <h5>二星：<a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01007&key=T01007MV_5X&type=EXZhX">二星直选</a><span>|</span><a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01007&key=T01007MV_2ZXHZ&type=EXHZh">二星和值</a><span>|</span><a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01007&key=T01007MV_2ZX&type=EXZX">二星组选</a></h5>
    <h5>其它：<a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01007&key=T01007MV_DD&type=DXDS"  class="tit-cur-h5">大小单双</a><span>|</span><a href="${pageContext.request.contextPath }/leaveOut/leaveOutDetail?lotNo=T01007&key=T01007MV_5X&type=YX">一星</a></h5>
   	<div class="xuxian-line"></div>
    <div class="wap-banner">
    <p>第${batchCode }期</p>
    <p>剩余时间：<em class="end-time">${remainingTime }</em></p>
    </div>
   
        <form action="${pageContext.request.contextPath }/shiShiCai/byDaXiaoDanShuangSelf" method="post">
        <input name="batchCode" value="${batchCode }" type="hidden"/>
        <c:forEach items="${leaveOutList }" var="leaveOut" varStatus="leaveOutIndex">
    	<c:choose>
    		<c:when test="${leaveOutIndex.index==0 }">
    
    <h2 class="goucai-title"><span class="red-ball-num">大小单双个位当前期遗漏值：</span></h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p">
        	<span><input name="sball" type="checkbox" value="2" ${'2' eq sball ? 'checked':'' }/>大</span>
        	<span class="yilou-num">[${leaveOut.leaveOutCodeList[0]<10?'&nbsp;':'' }${leaveOut.leaveOutCodeList[0] }]</span>
        	<span><input name="sball" type="checkbox" value="1" ${'1' eq sball ? 'checked':'' }/>小</span>
        	<span class="yilou-num">[${leaveOut.leaveOutCodeList[1]<10?'&nbsp;':'' }${leaveOut.leaveOutCodeList[1] }]</span>
            <span><input name="sball" type="checkbox" value="5" ${'5' eq sball ? 'checked':'' }/>单</span>
        	<span class="yilou-num">[${leaveOut.leaveOutCodeList[2]<10?'&nbsp;':'' }${leaveOut.leaveOutCodeList[2] }]</span><br/>
            <span><input name="sball" type="checkbox" value="4" ${'4' eq sball ? 'checked':'' }/>双</span>
        	<span class="yilou-num">[${leaveOut.leaveOutCodeList[3]<10?'&nbsp;':'' }${leaveOut.leaveOutCodeList[3] }]</span>
      </p>
      
    </div>
    </c:when>
    		<c:when test="${leaveOutIndex.index==1 }">
     <h2 class="goucai-title"><span class="red-ball-num">大小单双十位当前期遗漏值：</span></h2>
    <div class="wap-pro-goucai">
    	<p class="redball-p">
        	<span><input name="gball" type="checkbox" value="2" ${'2' eq gball ? 'checked':'' }/>大</span>
        	<span class="yilou-num">[${leaveOut.leaveOutCodeList[0]<10?'&nbsp;':'' }${leaveOut.leaveOutCodeList[0] }]</span>
        	<span><input name="gball" type="checkbox" value="1" ${'1' eq gball ? 'checked':'' }/>小</span>
        	<span class="yilou-num">[${leaveOut.leaveOutCodeList[1]<10?'&nbsp;':'' }${leaveOut.leaveOutCodeList[1] }]</span>
            <span><input name="gball" type="checkbox" value="5" ${'5' eq gball ? 'checked':'' }/>单</span>
        	<span class="yilou-num">[${leaveOut.leaveOutCodeList[2]<10?'&nbsp;':'' }${leaveOut.leaveOutCodeList[2] }]</span><br/>
            <span><input name="gball" type="checkbox" value="4" ${'4' eq gball ? 'checked':'' }/>双</span>
        	<span class="yilou-num">[${leaveOut.leaveOutCodeList[3]<10?'&nbsp;':'' }${leaveOut.leaveOutCodeList[3] }]</span>
      </p>
      
    </div>
   </c:when>
    	</c:choose>
    
    </c:forEach>
    <div class="wap-pro-gray-goucai" style="border-top:1px solid #999; border-bottom:none">
     		<p>倍数：<input name="beiShu" type="text" class="num-ball" value="1" maxlength="5"/>（最多10000倍）</p>
            <p>追号：<input name="addNumber" type="text" class="num-ball" value="1" maxlength="2"/>期<input name="prizeend" type="checkbox" value="1" style="width:12px; height:12px; vertical-align:middle; margin-left:30px;" ${'1' eq prizeend ?'checked' :'' }/>中奖后停止</p>
   		</div>
    <p class="moneey-dlt">投注内容与开奖结果一致即中奖。奖金<span>4</span>元</p>
<p class="gx-tjiao"><input name="submitType" type="submit" value="提交投注" class="tz-chakan-btn" /></p>
   </form> 