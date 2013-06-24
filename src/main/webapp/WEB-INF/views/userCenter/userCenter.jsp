<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <div class="wap-banner">
    	<p class="area-cur"><strong><a href="${pageContext.request.contextPath }">首页</a>-</strong><span>用户中心</span></p>
    </div>
    <h1><a>账户信息</a></h1>
    <div class="wap-pro">
    	<p><a href="${pageContext.request.contextPath }/select/userAccount.do" class="left-one">账户余额</a><a href="${pageContext.request.contextPath }/chargeCenter">账户充值</a><br /><a href="${pageContext.request.contextPath }/userAccountIndex" class="left-one">账户明细</a><a href="${pageContext.request.contextPath }/drawCash/findDNAtoCash.do">账户提现</a><br /><a href="${pageContext.request.contextPath }/scoreCenter/selectScoreDetail.do" class="left-one-end">我的积分</a></p>
    </div>
     <h1><a>投注查询</a></h1>
    <div class="wap-pro">
    	<p><a href="${pageContext.request.contextPath }/select/userBetting.do" class="left-one">投注查询</a><a href="${pageContext.request.contextPath }/caseLotCenter/selectCaseLotBuys.do">合买查询</a><br /><a href="${pageContext.request.contextPath }/select/userAddNumber.do" class="left-one">追号查询</a><a href="${pageContext.request.contextPath }/select/userWinning.do">中奖查询</a><br /><a href="${pageContext.request.contextPath }/select/selectSenderPresent.do" class="left-one-end">赠送查询</a></p>
    </div>
     <h1><a>用户资料</a></h1>
    <div class="wap-pro">
    	<p><a href="${pageContext.request.contextPath }/messageCenter/selectMsgs.do" class="left-one">我的留言</a><a href="${pageContext.request.contextPath }/userInfo/selectUserInfo.do">完善信息</a><br /><a href="${pageContext.request.contextPath }/modifyPassWord" class="left-one">密码修改</a><a href="${pageContext.request.contextPath }/userInfo/mobileBandingIndex.do">手机绑定</a><br /></p>
    </div>
