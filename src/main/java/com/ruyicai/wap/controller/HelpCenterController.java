package com.ruyicai.wap.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.service.HelpService;
import com.ruyicai.wap.vo.Help;

@Controller
@RequestMapping("/helpCenter")
public class HelpCenterController {
	private static final Logger logger = Logger.getLogger(HelpCenterController.class);
	@Autowired
	HelpService helpService;
	/**
	 * 查询帮助列表
	 * @param type
	 * @return
	 */
	@RequestMapping("/selectHelpList")
	public ModelAndView selectHelpList(
			@RequestParam(value ="type",defaultValue="1") String type){
		logger.info("HelpCenterController/selectHelpList查询帮助列表type："+type);
		ModelAndView modelAndView = new ModelAndView();
		List<Help> helpList = new ArrayList<Help>();
		helpList = helpService.selectHelpType(type);
		String typeName = getTypeName(type);
		modelAndView.addObject("helpList", helpList);
		modelAndView.addObject("type", type);
		modelAndView.addObject("typeName", typeName);
		modelAndView.setViewName("helpList");
		return modelAndView;
	}
	/**
	 * 查询帮助内容
	 * @param id
	 * @param type
	 * @param model
	 * @return
	 */
	@RequestMapping("/selectHelp")
	public ModelAndView selectHelp(
		@RequestParam(value="id",defaultValue="") String id,
		@RequestParam(value="type",defaultValue="") String type){
		logger.info("HelpCenterController/selectHelpList查询帮助内容参数id:"+id+",type:"+type);
		ModelAndView modelAndView = new ModelAndView();
		Help help = new Help();
		help = helpService.selectHelp(id);
		String typeName = getTypeName(type);
		modelAndView.addObject("type", type);
		modelAndView.addObject("typeName", typeName);
		modelAndView.addObject("help", help);
		modelAndView.setViewName("helpContent");
		return modelAndView;
	}
	
	public String getTypeName(String type){
		String typeName = "";
		if("1".equals(type)){
			typeName = "功能指引";
		}else if("2".equals(type)){
			typeName = "特色功能";
		}else if("3".equals(type)){
			typeName = "彩票玩法";
		}else if("4".equals(type)){
			typeName = "常见问题";
		}else if("5".equals(type)){
			typeName = "彩票术语";
		}
		return typeName;
	}
}
