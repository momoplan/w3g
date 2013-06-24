<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>

  <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><span>开奖公告</span></p>
 </div>
    <h1><a>${typeName }</a></h1>
    <div class="wap-pro" style="background:#faf9f9; border-bottom:1px solid #D6D6D3">
    	<form action="${pageContext.request.contextPath }/winInfo/selectJcSaiGuo" method="get">
    	<p class="zoushi-liebiao">
        	<select name="date">
        		<c:forEach items="${queryTimes}" var="queryTime">
        			<option ${queryTime eq dateTime ? 'selected':'' }>${queryTime }</option>
        		</c:forEach>
            </select>
            <input name="type" type="hidden" value="${type }"/>
            <input name="" type="submit" value="切换" class="liebiao-qiehuan" />
        </p>
       </form>
    </div>
    <c:forEach items="${jingCaiSaiGuos}" var="jingCaiSaiGuo" varStatus="jingCaiSaiGuoIndex">
    	<c:choose>
    		<c:when test="${(jingCaiSaiGuoIndex.index+1)%2==0 }">
    			<div class="wap-pro-nogray">
    		</c:when>
    		<c:otherwise>
    			<div class="wap-pro-gray">
    		</c:otherwise>
    	</c:choose>
    	
    	<table width="245" border="1" cellspacing="0" cellpadding="0">
  		<tr>
    	<td>日期:${jingCaiSaiGuo.day }</td>
    	<td width="25">&nbsp;</td>
    	<td><span>编号:${jingCaiSaiGuo.week }${jingCaiSaiGuo.teamid }</span></td>
    	</tr>
 		<tr>
    	<td>${jingCaiSaiGuo.league } </td>
    	<td>&nbsp;</td>
    	<td>比赛时间${jingCaiSaiGuo.time }</td>
    	</tr>
  		
  		<c:choose>
  			<c:when test="${'0' eq type }">
  				<tr>
  				<td>${jingCaiSaiGuo.teamKe }</td>
    			<td><em>${jingCaiSaiGuo.letpoint }</em></td>
    			<td>${jingCaiSaiGuo.teamZhu }</td>
    			</tr>
    		 	<tr>
    			<td>赛果:<span>${jingCaiSaiGuo.saiGuo }</span></td>
    			<td>&nbsp;</td>
    			<td>比分:<span>${jingCaiSaiGuo.result }</span></td>
    			</tr>
  			</c:when>
  			<c:when test="${'1' eq type }">
  				<tr>
  				<td>${jingCaiSaiGuo.teamZhu }</td>
    			<td><em>${jingCaiSaiGuo.letpoint }</em></td>
    			<td>${jingCaiSaiGuo.teamKe }</td>
    			</tr>
    			<tr>
    			<td>赛果:<span>${jingCaiSaiGuo.saiGuo }</span></td>
    			<td>&nbsp;</td>
    			<td>比分:<span>（${jingCaiSaiGuo.firsthalfresult }）${jingCaiSaiGuo.result }</span></td>
    			</tr>
  			</c:when>
  		</c:choose>
  	
</table>
    </div>
    </c:forEach>
     