 <%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
   <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><strong><a href="${pageContext.request.contextPath }/lotteryCenter">购彩大厅</a>-</strong><span>竞彩篮球</span></p>
    </div>
    <c:choose>
    	<c:when test="${'BSK001' eq wanFa }">
     <h1><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=1&wanFa=BSK001" class="tit-cur">胜负</a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=1&wanFa=BSK002">让分胜负</a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=1&wanFa=BSK003">胜分差</a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=1&wanFa=BSK004">大小分</a></h1>
    	</c:when>
    	<c:when test="${'BSK002' eq wanFa }">
     <h1><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=1&wanFa=BSK001">胜负</a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=1&wanFa=BSK002" class="tit-cur">让分胜负</a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=1&wanFa=BSK003">胜分差</a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=1&wanFa=BSK004">大小分</a></h1>
    	</c:when>
    	<c:when test="${'BSK003' eq wanFa }">
     <h1><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=1&wanFa=BSK001" >胜负</a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=1&wanFa=BSK002">让分胜负</a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=1&wanFa=BSK003" class="tit-cur">胜分差</a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=1&wanFa=BSK004">大小分</a></h1>
    	</c:when>
    	<c:when test="${'BSK004' eq wanFa }">
     <h1><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=1&wanFa=BSK001" >胜负</a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=1&wanFa=BSK002">让分胜负</a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=1&wanFa=BSK003">胜分差</a><span>|</span><a href="${pageContext.request.contextPath }/jcBasketballIndex/byJcBasketBall?type=0&valueType=1&wanFa=BSK004" class="tit-cur">大小分</a></h1>
    	</c:when>
    </c:choose>
    <form action="${pageContext.request.contextPath }/jcBasketball/betDetail" method="post">
    	<p class="prompt-pro">${messageError }</p>
    <h2 class="goucai-title">方案内容</h2>
    <div class="wap-pro-goucai">
    	${betCodeViewArrayToString }
    	<input name="betCodeViewArrayToString" type="hidden" value="${betCodeViewArrayToString }"/>
    </div>
    <input name="submitType" type="hidden" value="${submitType }"/>
    <input name="wanFa" type="hidden" value="${wanFa }"/>
    <input name="type" type="hidden" value="${type }"/>
    <input name="valueType" type="hidden" value="${valueType }"/>
    <input name="changCi" type="hidden" value="${changCi }"/>
    <input name="betCode" type="hidden" value="${betCode }"/>
    <c:choose>
    	<c:when test="${'1' eq valueType }">
    		<div class="wap-pro-gray-goucai" style="padding-top:10px;">
    			<c:if test="${'自由过关' eq submitType }">
    				<strong>过关方式：</strong>自由过关<br />
    				<p style="line-height:25px;">
    					<c:if test="${changCi>1}">
    						<input name="play" type="checkbox" value="502" ${fn:indexOf(play,'502')>-1?'checked':'' }/>2串1
    					</c:if>
    					<c:if test="${changCi>2}">
    						<input name="play" type="checkbox" value="503" ${fn:indexOf(play,'503')>-1?'checked':'' }/>3串1
    					</c:if>
    					<c:if test="${changCi>3}">
    						<input name="play" type="checkbox" value="504" ${fn:indexOf(play,'504')>-1?'checked':'' }/>4串1<br/>
    					</c:if>
    					<c:if test="${changCi>4&& wanFa != 'BSK003'}">
    						<input name="play" type="checkbox" value="505" ${fn:indexOf(play,'505')>-1?'checked':'' }/>5串1
    					</c:if>
    					<c:if test="${changCi>5&& wanFa != 'BSK003'}">
    						<input name="play" type="checkbox" value="506" ${fn:indexOf(play,'506')>-1?'checked':'' }/>6串1
    					</c:if>
    					<c:if test="${changCi>6&& wanFa != 'BSK003'}">
    						<input name="play" type="checkbox" value="507" ${fn:indexOf(play,'507')>-1?'checked':'' }/>7串1<br/>
    					</c:if>
    					<c:if test="${changCi>7&& wanFa != 'BSK003'}">
    						<input name="play" type="checkbox" value="508" ${fn:indexOf(play,'508')>-1?'checked':'' }/>8串1
    					</c:if>
    				</p>
    				
    			</c:if>
    			<c:if test="${'多串过关' eq submitType }">
    				<strong>过关方式：</strong>多串过关<br />
    				<p style="line-height:25px;">
    					<c:if test="${changCi>2}">
    						<input name="play" type="radio" value="526" ${fn:indexOf(play,'526')>-1?'checked':'' }/>3串3
    						<input name="play" type="radio" value="527" ${fn:indexOf(play,'527')>-1?'checked':'' }/>3串4
    					</c:if>
    					<c:if test="${changCi>3}">
    						<input name="play" type="radio" value="539" ${fn:indexOf(play,'539')>-1?'checked':'' }/>4串4<br/>
    						<input name="play" type="radio" value="540" ${fn:indexOf(play,'540')>-1?'checked':'' }/>4串5
    						<input name="play" type="radio" value="528" ${fn:indexOf(play,'528')>-1?'checked':'' }/>4串6
    						<input name="play" type="radio" value="529" ${fn:indexOf(play,'529')>-1?'checked':'' }/>4串11<br/>
    					</c:if>
    					<c:if test="${changCi>4&& wanFa != 'BSK003'}">
    						<input name="play" type="radio" value="544" ${fn:indexOf(play,'544')>-1?'checked':'' }/>5串5
    						<input name="play" type="radio" value="545" ${fn:indexOf(play,'545')>-1?'checked':'' }/>5串6
    						<input name="play" type="radio" value="530" ${fn:indexOf(play,'530')>-1?'checked':'' }/>5串10<br/>
    						<input name="play" type="radio" value="541" ${fn:indexOf(play,'541')>-1?'checked':'' }/>5串16
    						<input name="play" type="radio" value="531" ${fn:indexOf(play,'531')>-1?'checked':'' }/>5串20
    						<input name="play" type="radio" value="532" ${fn:indexOf(play,'532')>-1?'checked':'' }/>5串26<br/>
    					</c:if>
    					<c:if test="${changCi>5&& wanFa != 'BSK003'}">
    						<input name="play" type="radio" value="549" ${fn:indexOf(play,'549')>-1?'checked':'' }/>6串6
    						<input name="play" type="radio" value="550" ${fn:indexOf(play,'550')>-1?'checked':'' }/>6串7
    						<input name="play" type="radio" value="533" ${fn:indexOf(play,'533')>-1?'checked':'' }/>6串15<br/>
    						<input name="play" type="radio" value="542" ${fn:indexOf(play,'542')>-1?'checked':'' }/>6串20
    						<input name="play" type="radio" value="546" ${fn:indexOf(play,'546')>-1?'checked':'' }/>6串22
    						<input name="play" type="radio" value="534" ${fn:indexOf(play,'534')>-1?'checked':'' }/>6串35<br/>
    						<input name="play" type="radio" value="543" ${fn:indexOf(play,'543')>-1?'checked':'' }/>6串42
    						<input name="play" type="radio" value="535" ${fn:indexOf(play,'535')>-1?'checked':'' }/>6串50
    						<input name="play" type="radio" value="536" ${fn:indexOf(play,'536')>-1?'checked':'' }/>6串57<br/>
    					</c:if>
    					<c:if test="${changCi>6&& wanFa != 'BSK003'}">
    						<input name="play" type="radio" value="553" ${fn:indexOf(play,'553')>-1?'checked':'' }/>7串7
    						<input name="play" type="radio" value="554" ${fn:indexOf(play,'554')>-1?'checked':'' }/>7串8
    						<input name="play" type="radio" value="551" ${fn:indexOf(play,'551')>-1?'checked':'' }/>7串21
    						<input name="play" type="radio" value="547" ${fn:indexOf(play,'547')>-1?'checked':'' }/>7串35<br/>
    						<input name="play" type="radio" value="537" ${fn:indexOf(play,'537')>-1?'checked':'' }/>7串120
    					</c:if>
    					<c:if test="${changCi>7&& wanFa != 'BSK003'}">
    						<input name="play" type="radio" value="556" ${fn:indexOf(play,'556')>-1?'checked':'' }/>8串8
    						<input name="play" type="radio" value="557" ${fn:indexOf(play,'557')>-1?'checked':'' }/>8串9
    						<input name="play" type="radio" value="555" ${fn:indexOf(play,'555')>-1?'checked':'' }/>8串28<br/>
    						<input name="play" type="radio" value="552" ${fn:indexOf(play,'552')>-1?'checked':'' }/>8串56
    						<input name="play" type="radio" value="548" ${fn:indexOf(play,'548')>-1?'checked':'' }/>8串70
    						<input name="play" type="radio" value="538" ${fn:indexOf(play,'538')>-1?'checked':'' }/>8串247<br/>
    					</c:if>
    				</p>
    			</c:if>
    		</div>
    	</c:when>
    	<c:when test="${'0' eq valueType }">
    		<div class="wap-pro-gray-goucai" style="padding-top:10px;">
    			<strong>过关方式：</strong><br />
       			 <p style="line-height:25px; border-bottom:1px solid #999;"><input name="play" type="radio" value="500" checked="checked" readonly="readonly"/>单关</p>
   			 </div>
    	</c:when>
    </c:choose>
     <div class="wap-pro-gray-goucai" style="border-top:1px solid #999; border-bottom:none">
     		<p>倍数：<input name="beiShu" type="text" class="num-ball" maxlength="6" value="${beiShu }"/>（最多100000倍）</p>
           
    </div>
    <p class="gx-tjiao"><input type="submit" value="提交投注" class="tz-chakan-btn" /></p>
	</form>





