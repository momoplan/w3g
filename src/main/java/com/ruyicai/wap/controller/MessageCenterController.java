package com.ruyicai.wap.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.service.UserInfoService;
import com.ruyicai.wap.util.SelectAllUtil;
import com.ruyicai.wap.util.UserInfoUtil;
import com.ruyicai.wap.util.validate.ValidateUserInfoUtil;
import com.ruyicai.wap.vo.MsgRequest;
import com.ruyicai.wap.vo.UserInfo;


@Controller
@RequestMapping("/messageCenter")
public class MessageCenterController {
	Logger logger = Logger.getLogger(MessageCenterController.class);
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	SelectAllUtil selectAllUtil;
	@Autowired
	UserInfoUtil userInfoUtil;
	@Autowired
	ValidateUserInfoUtil validateUserInfoUtil;
	/**
	 * 用户留言
	 * @param content内容
	 * @param detail联系方式
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/leaveMessage")
	public ModelAndView leaveMessage(
			@RequestParam(value="content",defaultValue="")String content,
			@RequestParam(value="detail",defaultValue="")String detail,
			HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView modelAndView = new ModelAndView();
		logger.info("MessageCenterController/leaveMessage用户留言参数content:"+content+",detail:"+detail);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = "";
		if(userInfo!=null){
			userNo = userInfo.getUserNo();
		}else{
			userNo = "";
		}		
				
		String validateResult = validateUserInfoUtil.validateMsg(content, detail);
		if(!"".equals(validateResult)&&validateResult!=null){
			modelAndView.addObject("content", content);
			modelAndView.addObject("detail", detail);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("leaveMessage");
			logger.info("MessageCenterController/leaveMessage用户留言参数验证失败返回validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("MessageCenterController/leaveMessage用户留言参数验证通过");
		JSONObject jsonObject = new JSONObject();
		content = URLEncoder.encode(content, "UTF-8");
		jsonObject.put("userno", userNo);
		jsonObject.put("content", content);
		jsonObject.put("detail", detail);
		String errorCode = userInfoUtil.saveMsg(jsonObject);
		if("0".equals(errorCode)){
			modelAndView.addObject("messageError", "留言成功！");
			logger.info("MessageCenterController/leaveMessage用户留言成功！userNo:"+userNo);
			logger.info("MessageCenterController/leaveMessage用户留言成功,每天前3次增加积分！开始增加...");
			try{
				if(userInfo.getUserNo()!=null&&!"".equals(userInfo.getUserNo())){
					String addUserScoreResult = userInfoUtil.addUserScore(userInfo.getUserNo(), 7);
					if(!"".equals(addUserScoreResult)&&addUserScoreResult!=null){
						logger.info("MessageCenterController/leaveMessage增加积分失败addUserScoreResult:"+addUserScoreResult);
					}else{
						logger.info("MessageCenterController/leaveMessage增加积分成功！");
					}
				}
				
			}catch (Exception e) {
				logger.error("MessageCenterController/leaveMessage增加积分异常："+e.getMessage());
				modelAndView.setViewName("leaveMessage");
				return modelAndView;
			}
		}else if("000002".equals(errorCode)){
			modelAndView.addObject("messageError", "10分钟内不要重复提交留言！");
			logger.info("MessageCenterController/leaveMessage10分钟内不要重复提交留言！userNo:"+userNo+",errorCode:"+errorCode);
		}else{
			modelAndView.addObject("messageError", "留言失败！");
			logger.info("MessageCenterController/leaveMessage用户留言失败！userNo:"+userNo+",errorCode:"+errorCode);
		}
		modelAndView.setViewName("leaveMessage");
		return modelAndView;
	}
	/**
	 * 留言查询
	 * @param content
	 * @param detail
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/selectMsgs.do")
	public ModelAndView selectMsgs(
			@RequestParam(value="startLine",defaultValue="0")String startLine,
			@RequestParam(value="endLine",defaultValue="5")String endLine,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		int maxLine = 5;
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo.getUserNo();
		JSONObject condition = new JSONObject();
		condition.put("EQS_userno", userNo);
		Map<String,Object> map=	selectAllUtil.selectMsgs(condition, startLine, maxLine+"");
		if(map!=null){
			List<MsgRequest> msgRequests = (List<MsgRequest>) map.get("msgRequests");
			String totalPage = (String) map.get("totalPage");
			String currentPageNo = (String) map.get("currentPageNo");
			modelAndView.addObject("startLine",startLine);
			modelAndView.addObject("endLine",Integer.parseInt(endLine));
			modelAndView.addObject("nowPage",Integer.parseInt(currentPageNo));
			modelAndView.addObject("maxLine",maxLine);
			modelAndView.addObject("totalPage",Integer.parseInt(totalPage));
			modelAndView.addObject("msgRequests",msgRequests);
		}
		modelAndView.setViewName("messageCenter");
		return modelAndView;
		
	}
}
