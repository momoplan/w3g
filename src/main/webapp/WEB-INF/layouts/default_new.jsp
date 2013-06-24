<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta content="no-cache" http-equiv="Cache-control">
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<meta
	content="如意彩，双色球,彩票,福彩中心,体彩中心,中奖号码,开奖公告,手机彩票，手机购彩，手机投注，3d，福彩3d，彩票开奖，彩票合买,福利彩票"
	name="Keywords">
<meta
	content="如意彩-让手机彩票更精彩。拥有专业手机投注服务平台，全手机彩票牌照运营，手机客户端购彩，彩票送礼服务，中奖通知等多种彩票特色产品服务。如意彩还提供，彩票合买,手机彩票资讯，号码走势，开奖查询等增值服务，充分满足广大彩民的需求。"
	name="Description">
<title>如意彩-<tiles:insertAttribute name="title" ignore="true" /></title>
<link href="${pageContext.request.contextPath }/css/wo-pingtai.css" rel="stylesheet" type="text/css" />
<body>
	<tiles:insertAttribute name="header_new" ignore="true" />
	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="footer_new" ignore="true" />
</body>
</html>
