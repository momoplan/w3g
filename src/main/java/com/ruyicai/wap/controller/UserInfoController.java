package com.ruyicai.wap.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.service.UserInfoService;
import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.SelectAllUtil;
import com.ruyicai.wap.util.UserInfoUtil;
import com.ruyicai.wap.util.validate.ValidateUserInfoUtil;
import com.ruyicai.wap.vo.UserInfo;


@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
	Logger logger = Logger.getLogger(UserInfoController.class);
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	SelectAllUtil selectAllUtil;
	@Autowired
	UserInfoUtil userInfoUtil;
	@Autowired
	ValidateUserInfoUtil validateUserInfoUtil;
	@Autowired
	IndexController indexController;
	@Autowired
	CommonUtil commonUtil;
	@Value("${channel}")
	String channel;
	/**
	 * 用户登录
	 * @param userName
	 * @param passWord
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(
			@RequestParam(value="userName",defaultValue="")String userName,
			@RequestParam(value="passWord",defaultValue="")String passWord,
			HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		logger.info("UserInfoController/login用户登录参数userName:"+userName+",passWord:"+passWord);
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = validateUserInfoUtil.validateLogin(request, userName, passWord);
		if(!"".equals(validateResult)&&validateResult!=null){
			modelAndView.addObject("userName", userName);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("login");
			logger.info("UserInfoController/login用户登录参数验证失败返回validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("UserInfoController/login用户登录参数验证通过");
		Map<String, Object> map = userInfoUtil.toLogin(userName, passWord);
		String result = (String) map.get("result");
		if(!"".equals(result)&&result!=null){
			modelAndView.addObject("userName", userName);
			modelAndView.addObject("messageError", result);
			modelAndView.setViewName("login");
			logger.info("UserInfoController/login用户登录失败返回result:"+result);
			return modelAndView;
		}
		UserInfo userInfo = (UserInfo) map.get("userInfo");
		request.getSession().setAttribute("userInfo", userInfo);
		logger.info("UserInfoController/login用户登录成功userName:"+userName);
		logger.info("UserInfoController/login用户登录成功,每天第一次登录增加积分！开始增加...");
		try{
			String addUserScoreResult = userInfoUtil.addUserScore(userInfo.getUserNo(), 8);
			if(!"".equals(addUserScoreResult)&&addUserScoreResult!=null){
				logger.info("UserInfoController/login增加积分失败addUserScoreResult:"+addUserScoreResult);
			}else{
				logger.info("UserInfoController/login增加积分成功！");
			}
		}catch (Exception e) {
			logger.error("UserInfoController/login增加积分异常："+e.getMessage());
			indexController.getIndexModelAndView(modelAndView);
			modelAndView.setViewName("index");
			return modelAndView;
		}
		
		String loginOutUrl = (String) request.getSession().getAttribute("loginOutUrl");
		if(!"".equals(loginOutUrl)&&loginOutUrl!=null){
			int i=loginOutUrl.indexOf("/w3g/");
			try {
				request.getRequestDispatcher(loginOutUrl.substring(i+4)).forward(request,response);
			} catch (ServletException e) {
				logger.error("UserInfoController/login用户未登录进行操作时，登录成功跳转页面出现异常！");
				indexController.getIndexModelAndView(modelAndView);
				modelAndView.setViewName("index");
				return modelAndView;
			} catch (IOException e) {
				logger.error("UserInfoController/login用户未登录进行操作时，登录成功跳转页面出现异常！");
				indexController.getIndexModelAndView(modelAndView);
				modelAndView.setViewName("index");
				return modelAndView;
			}
			return null;

		}
		indexController.getIndexModelAndView(modelAndView);
		modelAndView.setViewName("index");
		return modelAndView;
	}
	/**
	 * 用户退出登录
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value="/loginOut.do",method=RequestMethod.GET)
	public ModelAndView loginOut(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		ModelAndView modelAndView = new ModelAndView();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		logger.info("UserInfoController/loginOut用户退出登录userName:"+userInfo.getUserName());
		request.getSession().removeAttribute("userInfo");
		String loginOutUrl = (String) request.getSession().getAttribute("loginOutUrl");
		if(!"".equals(loginOutUrl)&&loginOutUrl!=null){
			int i=loginOutUrl.indexOf("/w3g/");
			try {
				request.getRequestDispatcher(loginOutUrl.substring(i+4)).forward(request,response);
			} catch (ServletException e) {
				logger.error("UserInfoController/loginOut用户未登录进行操作时，登录成功跳转页面出现异常！");
				if(loginOutUrl!=null&&!"".equals(loginOutUrl)){
					request.getRequestDispatcher(loginOutUrl).forward(request,response);
					return null;
				}else{
					indexController.getIndexModelAndView(modelAndView);
					modelAndView.setViewName("index");
					return modelAndView;
				}
			} catch (IOException e) {
				logger.error("UserInfoController/loginOut用户未登录进行操作时，登录成功跳转页面出现异常！");
				if(loginOutUrl!=null&&!"".equals(loginOutUrl)){
					request.getRequestDispatcher(loginOutUrl).forward(request,response);
					return null;
				}else{
					indexController.getIndexModelAndView(modelAndView);
					modelAndView.setViewName("index");
					return modelAndView;
				}
			}
		}
		indexController.getIndexModelAndView(modelAndView);
		modelAndView.setViewName("index");
		return modelAndView;
	}
	@RequestMapping("/registerIndex")
	public ModelAndView registerIndex(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		commonUtil.setToken(request, modelAndView);
		modelAndView.setViewName("register");
		return modelAndView;
	}
	/**
	 * 用户注册
	 * @param userName
	 * @param passWord
	 * @param rePassWord
	 * @param checkBox
	 * @param token
	 * @param request
	 * @return
	 */
	@RequestMapping("/register")
	public ModelAndView register(
			@RequestParam(value="userName",defaultValue="")String userName,
			@RequestParam(value="passWord",defaultValue="")String passWord,
			@RequestParam(value="rePassWord",defaultValue="")String rePassWord,
			@RequestParam(value="checkBox",defaultValue="")String checkBox,
			@RequestParam(value="token",defaultValue="")String token,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		logger.info("UserInfoController/register用户注册参数userName:"+userName+",passWord:"
				+passWord+",rePassWord:"+rePassWord+",checkBox"+checkBox+",token:"+token);
		String validateResult = validateUserInfoUtil.validateRegister(userName, passWord, rePassWord, checkBox);
		if(validateResult!=null&&!"".equals(validateResult)){
			modelAndView.addObject("userName", userName);
			modelAndView.addObject("passWord", passWord);
			modelAndView.addObject("rePassWord", rePassWord);
			modelAndView.addObject("token", token);
			modelAndView.addObject("messageError", validateResult);
			logger.info("UserInfoController/register用户注册参数验证失败返回validateResult:"+validateResult);
			modelAndView.setViewName("register");
			return modelAndView;
		}
		logger.info("UserInfoController/register用户注册参数验证通过！");
		String isExecute = (String) request.getSession().getAttribute(token);
		if (!"false".equals(isExecute)) {
			modelAndView.addObject("messageError", "请勿重复提交！");
			modelAndView.addObject("userName", userName);
			modelAndView.addObject("passWord", passWord);
			modelAndView.addObject("rePassWord", rePassWord);
			commonUtil.setToken(request, modelAndView);
			modelAndView.setViewName("register");
			return modelAndView;

		}
		String errorCode = userInfoUtil.register(userName, passWord, channel);
		if("0".equals(errorCode)){
			UserInfo userInfo = selectAllUtil.selectUserInfoByUserName(userName);
			request.getSession().setAttribute("userInfo", userInfo);
			logger.info("UserInfoController/register用户注册后登录成功userName:"+userName);
			logger.info("UserInfoController/register用户注册后登录成功,每天第一次登录增加积分！开始增加...");
			String addUserScoreResult = userInfoUtil.addUserScore(userInfo.getUserNo(), 8);
			if(!"".equals(addUserScoreResult)&&addUserScoreResult!=null){
				logger.info("UserInfoController/register用户注册后登录成功增加积分失败addUserScoreResult:"+addUserScoreResult);
			}else{
				logger.info("UserInfoController/register用户注册后登录成功增加积分成功！");
			}
			request.getSession().setAttribute(token, "true");
			modelAndView.addObject("messageError", "注册成功！");
			modelAndView.addObject("userName", userName);
			modelAndView.setViewName("registerResult");
			commonUtil.setToken(request, modelAndView);
			logger.info("UserInfoController/register用户注册成功userName:"+userName);
			return modelAndView;

		}else if("500".equals(errorCode)){
			modelAndView.addObject("messageError", "服务器忙，请稍后再试！");
			modelAndView.addObject("userName", userName);
			modelAndView.addObject("passWord", passWord);
			modelAndView.addObject("rePassWord", rePassWord);
			commonUtil.setToken(request, modelAndView);
			logger.info("UserInfoController/register用户注册失败userName:"+userName+",errorCode:"+errorCode);
			modelAndView.setViewName("register");
			return modelAndView;

		}else if("0013".equals(errorCode)){
			modelAndView.addObject("messageError", "用户名已注册！");
			modelAndView.addObject("userName", userName);
			modelAndView.addObject("passWord", passWord);
			modelAndView.addObject("rePassWord", rePassWord);
			commonUtil.setToken(request, modelAndView);
			logger.info("UserInfoController/register用户注册失败userName:"+userName+",errorCode:"+errorCode);
			modelAndView.setViewName("register");
			return modelAndView;

		}else{
			modelAndView.addObject("messageError", "注册失败，请稍后再试！");
			modelAndView.addObject("userName", userName);
			modelAndView.addObject("passWord", passWord);
			modelAndView.addObject("rePassWord", rePassWord);
			commonUtil.setToken(request, modelAndView);
			logger.info("UserInfoController/register用户注册失败userName:"+userName+",errorCode:"+errorCode);
			modelAndView.setViewName("register");
			return modelAndView;

		}
	}
	/**
	 * 用户修改密码
	 * @param oldPassWord
	 * @param newPassWord
	 * @param reNewPassWord
	 * @param request
	 * @return
	 */
	@RequestMapping("/resetPassWord.do")
	public ModelAndView resetPassWord(
			@RequestParam(value="oldPassWord",defaultValue="")String oldPassWord,
			@RequestParam(value="newPassWord",defaultValue="")String newPassWord,
			@RequestParam(value="reNewPassWord",defaultValue="")String reNewPassWord,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		logger.info("UserInfoController/resetPassWord修改密码参数oldPassWord:"+oldPassWord+",newPassWord:"+newPassWord+",reNewPassWord");
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userName = userInfo.getUserName();
		String userNo = userInfo.getUserNo();
		String validateResult = validateUserInfoUtil.validateResetPassWord(oldPassWord, newPassWord, reNewPassWord, userName);
		if(!"".equals(validateResult)&&validateResult!=null){
			modelAndView.addObject("oldPassWord", oldPassWord);
			modelAndView.addObject("newPassWord", newPassWord);
			modelAndView.addObject("reNewPassWord", reNewPassWord);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("modifyPassWord");
			logger.info("UserInfoController/resetPassWord修改密码参数验证失败返回validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("UserInfoController/resetPassWord修改密码参数通过");
		String errorCode = userInfoUtil.resetPassWord(userNo, newPassWord);
		if("0".equals(errorCode)){
			modelAndView.addObject("messageError", "恭喜，修改密码成功！");
			logger.info("UserInfoController/resetPassWord修改密码成功！");
		}else{
			modelAndView.addObject("messageError", "抱歉，修改密码失败，请稍后再试！");
			logger.info("UserInfoController/resetPassWord修改密码失败errorCode:"+errorCode);
		}
		modelAndView.setViewName("modifyPassWord");

		return modelAndView;
	}
	/**
	 * 找回密码
	 * @param userName
	 * @param mobileId
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/retrievePassWord",method=RequestMethod.POST)
	public ModelAndView retrievePassWord(
			@RequestParam(value="userName",defaultValue="")String userName,
			@RequestParam(value="mobileId",defaultValue="")String mobileId,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		logger.info("UserInfoController/retrievePassWord找回密码参数userName:"+userName+",mobileId:"+mobileId);
		String validateResult = validateUserInfoUtil.validateRetrievePassWord(userName, mobileId);
		if(!"".equals(validateResult)&&validateResult!=null){
			modelAndView.addObject("userName", userName);
			modelAndView.addObject("mobileId", mobileId);
			modelAndView.addObject("messageError", validateResult);
			logger.info("UserInfoController/retrievePassWord找回密码参数验证失败返回validateResult:"+validateResult);
			modelAndView.setViewName("retrievePassWord");
			return modelAndView;
		}
		logger.info("UserInfoController/retrievePassWord找回密码参数验证通过");
		String newPassWord = CommonUtil.getUuid().substring(0, 12);
		logger.info("UserInfoController/retrievePassWord找回密码随机生成12位密码newPassWord:"+newPassWord);
		UserInfo userInfo = selectAllUtil.selectUserInfoByUserName(userName);
		String errorCode = userInfoUtil.resetPassWord(userInfo.getUserNo(), newPassWord);
		if("0".equals(errorCode)){
			String text = "尊敬的如意彩用户，您的如意彩账户新密码为："+newPassWord+"，请您登录后及时修改密码。";
			try {
				boolean flag = userInfoUtil.sendSms(mobileId, text);
				if(flag){
					modelAndView.addObject("userName", userName);
					modelAndView.addObject("messageError", "恭喜您，找回成功，密码已经发送到您的手机，请注意查收!");
					modelAndView.setViewName("retrievePassWord");
					logger.info("UserInfoController/retrievePassWord找回密码发送短信成功");
				}else{
					modelAndView.addObject("userName", userName);
					modelAndView.addObject("messageError", "密码找回失败，请稍后再试!");
					modelAndView.setViewName("retrievePassWord");
					logger.info("UserInfoController/retrievePassWord找回密码发送短信失败");
				}
			} catch (JSONException e) {
				modelAndView.addObject("userName", userName);
				modelAndView.addObject("messageError", "密码找回失败，请稍后再试!");
				modelAndView.setViewName("retrievePassWord");
				logger.error("UserInfoController/retrievePassWord找回密码发送短信出现异常");
				return modelAndView;
			} catch (UnsupportedEncodingException e) {
				modelAndView.addObject("userName", userName);
				modelAndView.addObject("messageError", "密码找回失败，请稍后再试!");
				modelAndView.setViewName("retrievePassWord");
				logger.error("UserInfoController/retrievePassWord找回密码发送短信出现异常");
				return modelAndView;
			}
		}else{
			modelAndView.addObject("messageError", "密码找回失败，请稍后再试！");
			modelAndView.addObject("userName", userName);
			modelAndView.setViewName("retrievePassWord");
			logger.info("UserInfoController/retrievePassWord找回密码失败errorCode:"+errorCode);
		}
		return modelAndView;
	}
	/**
	 * 查询用户信息是否完善
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectUserInfo.do")
	public ModelAndView selectUserInfo(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userName = userInfo.getUserName();
		userInfo = selectAllUtil.selectUserInfoByUserName(userName);
		String name = userInfo.getName();
		String nickName = userInfo.getNickName();
		String certId = userInfo.getCertId();
		if(!"".equals(name)&&name!=null&&!"null".equals(name)&&!"".equals(nickName)&&nickName!=null&&!"null".equals(nickName)&&!"".equals(certId)&&certId!=null){
			String newName = "";
			if(name.length()==2){
				newName = name.substring(0,1)+"*";
			}else if(name.length()==3){
				newName = name.substring(0,1)+"**";
			}else if(name.length()==4){
				newName = name.substring(0,1)+"***";
			}else{
				newName = name;
			}
			modelAndView.addObject("messageError", "您的信息已完善！");
			modelAndView.addObject("name", newName);
			modelAndView.addObject("nickName",nickName );
			modelAndView.addObject("certId", certId.trim().substring(0,certId.trim().length()-4)+"****");
			modelAndView.setViewName("userInfo");
		}else{
			if(!"".equals(name)&&name!=null&&!"null".equals(name)){
				String newName = "";
				if(name.length()==2){
					newName = name.substring(0,1)+"*";
				}else if(name.length()==3){
					newName = name.substring(0,1)+"**";
				}else if(name.length()==4){
					newName = name.substring(0,1)+"***";
				}else{
					newName = name;
				}
				modelAndView.addObject("nameView", newName);
				modelAndView.addObject("name", name);

			}
			if(!"".equals(nickName)&&nickName!=null&&!"null".equals(nickName)){
				modelAndView.addObject("nickNameView",nickName );
				modelAndView.addObject("nickName",nickName );
			}
			if(!"".equals(certId)&&certId!=null&&!"null".equals(certId)){
				modelAndView.addObject("certIdView", certId.trim().substring(0,certId.trim().length()-4)+"****");
				modelAndView.addObject("certId", certId);

			}
			modelAndView.setViewName("perfectUserInfo");
			commonUtil.setToken(request, modelAndView);
		}
		return modelAndView;
	}
	/**
	 * 完善用户信息
	 * @param name
	 * @param nickName
	 * @param certId
	 * @param request
	 * @return
	 */
	@RequestMapping("/perfectUserInfo.do")
	public ModelAndView perfectUserInfo(
			@RequestParam(value="name",defaultValue="")String name,
			@RequestParam(value="nickName",defaultValue="")String nickName,
			@RequestParam(value="nickNameView",defaultValue="")String nickNameView,
			@RequestParam(value="nameView",defaultValue="")String nameView,
			@RequestParam(value="certIdView",defaultValue="")String certIdView,
			@RequestParam(value="certId",defaultValue="")String certId,
			@RequestParam(value="token",defaultValue="")String token,
			@RequestParam(value="submitType",defaultValue="")String submitType,
			HttpServletRequest request){
		logger.info("UserInfoController/perfectUserInfo完善用户信息参数name:"+name+",nickName:"+nickName+",nickNameView:"+nickNameView+",certId:"+certId+"submitType:"+submitType);
		ModelAndView modelAndView = new ModelAndView();
		if("重 填".equals(submitType)){
			modelAndView = selectUserInfo(request);
			return modelAndView;
		}
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userName = userInfo.getUserName();
		String userNo= userInfo.getUserNo();
		String validateResult = "";
		if("".equals(nickNameView)||nickNameView==null){
			validateResult = validateUserInfoUtil.validatePerfectUserInfoByNickName(nickName);
		}else if("".equals(nameView)||nameView==null){
			validateResult = validateUserInfoUtil.validatePerfectUserInfoByName(name);
		}else if("".equals(certIdView)||certIdView==null){
			validateResult = validateUserInfoUtil.validatePerfectUserInfoByCertId(certId);
		}
		if(validateResult!=null&&!"".equals(validateResult)){
//			userInfo = selectAllUtil.selectUserInfoByUserName(userName);
//			commonUtil.setToken(request, modelAndView);
			modelAndView = selectUserInfo(request);
			modelAndView.addObject("name", name);
			modelAndView.addObject("nickName", nickName);
			modelAndView.addObject("certId", certId);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("perfectUserInfo");
			logger.info("UserInfoController/perfectUserInfo完善用户信息参数验证失败返回validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("UserInfoController/perfectUserInfo完善用户信息参数验证通过");
		String isExecute = (String) request.getSession().getAttribute(token);
		if ("false".equals(isExecute)) {
			request.getSession().setAttribute(token, "true");
		}else{
			
			userInfo = selectAllUtil.selectUserInfoByUserName(userName);
//			modelAndView.addObject("name", userInfo.getName());
//			modelAndView.addObject("nickName", userInfo.getNickName());
//			modelAndView.addObject("certId", userInfo.getCertId());
			if(!"".equals(userInfo.getName())&&userInfo.getName()!=null&&!"null".equals(userInfo.getName())&&!"".equals(userInfo.getNickName())&&userInfo.getNickName()!=null&&!"null".equals(userInfo.getNickName())&&!"".equals(userInfo.getCertId())&&userInfo.getCertId()!=null&&!"null".equals(userInfo.getCertId())){
//				modelAndView.addObject("name", userInfo.getName());
//				modelAndView.addObject("nickName",userInfo.getNickName());
//				modelAndView.addObject("certId", userInfo.getCertId());
				modelAndView = selectUserInfo(request);
				modelAndView.addObject("messageError", "您的信息已完善！");
				modelAndView.setViewName("userInfo");
			}else{
				modelAndView = selectUserInfo(request);
				modelAndView.addObject("messageError", "请勿重复提交！");
				modelAndView.addObject("name", name);
				modelAndView.addObject("nickName", nickName);
				modelAndView.addObject("certId", certId);
				modelAndView.setViewName("perfectUserInfo");
//				commonUtil.setToken(request, modelAndView);
			}
			return modelAndView;
		}
		String errorCode = "";
//		if(!"".equals(nickNameView)&&nickNameView!=null){
//			errorCode = userInfoUtil.perfectUserinfo(userNo, "", name, certId);
//		}else if(!"".equals(nameView)&&nameView!=null&&!"".equals(certIdView)&&certIdView!=null){
//			errorCode = userInfoUtil.perfectUserinfo(userNo, nickName, "", "");
//		}else{
//			errorCode = userInfoUtil.perfectUserinfo(userNo, nickName, name, certId);
//		}
		errorCode = userInfoUtil.perfectUserinfo(userNo, nickName, name, certId);

		if("0".equals(errorCode)){
			userInfo = selectAllUtil.selectUserInfoByUserName(userName);
			String newName = "";
			if(name.length()==2){
				newName = userInfo.getName().substring(0,1)+"*";
			}else if(name.length()==3){
				newName = userInfo.getName().substring(0,1)+"**";
			}else if(name.length()==4){
				newName = userInfo.getName().substring(0,1)+"***";
			}else{
				newName = userInfo.getName();
			}
			modelAndView.addObject("name", newName);
			modelAndView.addObject("nickName", userInfo.getNickName());
			modelAndView.addObject("certId", userInfo.getCertId().trim().substring(0,userInfo.getCertId().trim().length()-4)+"****");
			modelAndView.addObject("messageError", "完善信息成功！");
			request.getSession().removeAttribute("userInfo");
			request.getSession().setAttribute("userInfo",userInfo);
			modelAndView.setViewName("userInfo");
			logger.info("UserInfoController/perfectUserInfo完善用户信息成功");
			if(!"".equals(userInfo.getMobileId())&&userInfo.getMobileId()!=null){
				logger.info("UserInfoController/perfectUserInfo用户完善信息并绑定手机号增加积分！开始增加...");
				String addUserScoreResult = userInfoUtil.addUserScore(userInfo.getUserNo(), 1);
				if(!"".equals(addUserScoreResult)&&addUserScoreResult!=null){
					logger.info("UserInfoController/perfectUserInfo增加积分失败addUserScoreResult:"+addUserScoreResult);
				}else{
					logger.info("UserInfoController/perfectUserInfo增加积分成功！");
				}

			}
			return modelAndView;
		}else if("100105".equals(errorCode)){
//			userInfo = selectAllUtil.selectUserInfoByUserName(userName);
//			modelAndView.addObject("name", userInfo.getName());
//			modelAndView.addObject("nickName", userInfo.getNickName());
//			modelAndView.addObject("certId", userInfo.getCertId());
//			commonUtil.setToken(request, modelAndView);
			modelAndView = selectUserInfo(request);
			modelAndView.addObject("name", name);
			modelAndView.addObject("nickName", nickName);
			modelAndView.addObject("certId", certId);
			modelAndView.addObject("messageError", "昵称已存在！");
			logger.info("UserInfoController/perfectUserInfo完善用户信息失败，昵称已存在");
			modelAndView.setViewName("perfectUserInfo");

		}else{
//			userInfo = selectAllUtil.selectUserInfoByUserName(userName);
			modelAndView = selectUserInfo(request);
			modelAndView.addObject("name", name);
			modelAndView.addObject("nickName", nickName);
			modelAndView.addObject("certId", certId);

			modelAndView.addObject("messageError", "完善信息失败！");
//			commonUtil.setToken(request, modelAndView);
			logger.info("UserInfoController/perfectUserInfo完善用户信息成功errorCode:"+errorCode);
			modelAndView.setViewName("perfectUserInfo");

		}
		return modelAndView;
		
	}
	/**
	 * 是否绑定手机号
	 * @param request
	 * @return
	 */
	@RequestMapping("/mobileBandingIndex.do")
	public ModelAndView mobileBandingIndex(
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userName = userInfo.getUserName();
		userInfo = selectAllUtil.selectUserInfoByUserName(userName);
		String mobileId = userInfo.getMobileId();
		logger.info("UserInfoController/mobileBandingIndex是否绑定手机号mobileId:"+mobileId);
		modelAndView.addObject("userName", userName);
		if(mobileId!=null&&!"".equals(mobileId)&&!"null".equals(mobileId)){
			modelAndView.addObject("mobileId", mobileId);
			modelAndView.setViewName("mobileBandingDetail");
		}else{
			modelAndView.addObject("mobileId", "");
			modelAndView.setViewName("mobileBandingIndex");
		}
		return modelAndView;
	}
	/**
	 *绑定手机号发送短信
	 * @param mobileId
	 * @param request
	 * @return
	 * @throws JSONException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/mobileBandingMsg.do")
	public ModelAndView mobileBandingMsg(
			@RequestParam(value="mobileId",defaultValue="") String mobileId,
			HttpServletRequest request) throws JSONException, UnsupportedEncodingException{
		ModelAndView modelAndView = new ModelAndView();
		logger.info("UserInfoController/mobileBandingMsg绑定手机号发送短信参数mobileId:"+mobileId);
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo.getUserNo();
		String userName = userInfo.getUserName();
		if(!ValidateUserInfoUtil.validateMobileId(mobileId)){
			modelAndView.addObject("mobileId", mobileId);
			modelAndView.addObject("userName", userName);
			modelAndView.addObject("messageError", "手机号格式不正确");
			modelAndView.setViewName("mobileBandingIndex");
			logger.info("UserInfoController/mobileBandingMsg绑定手机号发送短信参数验证失败:手机号格式不正确");
			return modelAndView;
		}
		int count = userInfoService.selectMsgCount(userNo);
		logger.info("UserInfoController/mobileBandingMsg发送短信次数count"+count);
		if(count>=3){
			modelAndView.addObject("mobileId", mobileId);
			modelAndView.addObject("userName", userName);
			modelAndView.addObject("messageError", "你今天绑定手机号发送的短信次数已达到3次(每日3次)！");
			modelAndView.setViewName("mobileBandingIndex");
			return modelAndView;
		}
		String randCode = CommonUtil.getRandomValidateCode();
		String text = "尊敬的用户,您本次绑定手机号所使用的验证码是" + randCode
				+ "(验证码30分钟后失效).祝您幸运赢大奖!本条信息免费";
		boolean result = userInfoUtil.sendSms(mobileId, text);
		if(!result){
			modelAndView.addObject("mobileId", mobileId);
			modelAndView.addObject("messageError", "获取验证码失败，请稍后重试！");
			modelAndView.setViewName("mobileBandingMsg");
			logger.info("UserInfoController/mobileBandingMsg发送短信失败");
			return modelAndView;
		}
		logger.info("UserInfoController/mobileBandingMsg发送短信成功");
		modelAndView.addObject("messageError", "验证码已发送到您的手机，请注意查收(每天3次)！");
		int re = userInfoService.insertMsgInfo(userNo, userName, mobileId, randCode);
		if(re==1){
			logger.info("UserInfoController/mobileBandingMsg绑定手机号获取验证码。添加信息成功");
		}else{
			logger.info("UserInfoController/mobileBandingMsg绑定手机号获取验证码。添加信息失败");
		}
		request.getSession().setAttribute("randCode", randCode);
		modelAndView.addObject("mobileId", mobileId);
		modelAndView.setViewName("mobileBandingMsg");
		return modelAndView;
	}
	/**
	 * 手机绑定
	 * @param mobileId
	 * @param checkCode
	 * @param request
	 * @return
	 */
	@RequestMapping("/mobileBanding.do")
	public ModelAndView mobileBanding(
			@RequestParam(value="mobileId",defaultValue="") String mobileId,
			@RequestParam(value="checkCode",defaultValue="") String checkCode,
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		logger.info("UserInfoController/mobileBanding绑定手机号参数mobileId:"+mobileId+",checkCode:"+checkCode);
		String validateResult = validateUserInfoUtil.validateMobileBanding(checkCode, request);
		if(!"".equals(validateResult)&&validateResult!=null){
			modelAndView.addObject("mobileId", mobileId);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.setViewName("mobileBandingMsg");
			logger.info("UserInfoController/mobileBanding绑定手机号参数验证失败返回validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("UserInfoController/mobileBanding绑定手机号参数验证通过");
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo.getUserNo();
		String userName = userInfo.getUserName();
		String errorCode = userInfoUtil.bindMobile(userNo, mobileId);
		if("0".equals(errorCode)){
			modelAndView.addObject("messageError", "手机号绑定成功！");
			logger.info("UserInfoController/mobileBanding绑定手机号成功");
			userInfo = selectAllUtil.selectUserInfoByUserName(userName);
			String name = userInfo.getName();
			String nickName = userInfo.getNickName();
			String certId = userInfo.getCertId();
			if(!"".equals(name)&&name!=null&&!"".equals(nickName)&&nickName!=null&&!"".equals(certId)&&certId!=null){
				logger.info("UserInfoController/mobileBanding用户完善信息并绑定手机号增加积分！开始增加...");
				String addUserScoreResult = userInfoUtil.addUserScore(userInfo.getUserNo(), 1);
				if(!"".equals(addUserScoreResult)&&addUserScoreResult!=null){
					logger.info("UserInfoController/mobileBanding增加积分失败addUserScoreResult:"+addUserScoreResult);
				}else{
					logger.info("UserInfoController/mobileBanding增加积分成功！");
				}
			}
		}else{
			modelAndView.addObject("messageError", "手机号绑定失败，请稍后重试！");
			logger.info("UserInfoController/mobileBanding绑定手机号失败");
		}
		userInfo = selectAllUtil.selectUserInfoByUserName(userName);
		mobileId = userInfo.getMobileId();
		modelAndView.addObject("mobileId", mobileId);
		modelAndView.addObject("userName", userName);
		modelAndView.setViewName("mobileBandingDetail");
		return modelAndView;
	}
	/**
	 * 手机号解绑
	 * @param request
	 * @return
	 */
	@RequestMapping("/cancelMobileBanding.do")
	public ModelAndView cancelMobileBanding(
			HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		String userNo = userInfo.getUserNo();
		String userName = userInfo.getUserName();
		logger.info("UserInfoController/cancelMobileBanding手机号解绑userName:"+userName);
		String errorCode = userInfoUtil.bindMobile(userNo, " ");
		if("0".equals(errorCode)){
			modelAndView.addObject("messageError", "手机号解绑成功！");
			modelAndView.addObject("mobileId", "");
			modelAndView.addObject("userName", userName);
			modelAndView.setViewName("mobileBandingIndex");
			logger.info("UserInfoController/cancelMobileBanding手机号解绑成功");
		}else{
			modelAndView.addObject("messageError", "手机号解绑失败，请稍后重试！");
			userInfo = selectAllUtil.selectUserInfoByUserName(userName);
			String mobileId = userInfo.getMobileId();
			modelAndView.addObject("mobileId", mobileId);
			modelAndView.addObject("userName", userName);
			modelAndView.setViewName("mobileBandingDetail");
			logger.info("UserInfoController/cancelMobileBanding手机号解绑失败");
		}
		return modelAndView;
	}
}
