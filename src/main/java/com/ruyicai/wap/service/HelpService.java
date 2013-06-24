package com.ruyicai.wap.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ruyicai.wap.vo.Help;

@Service
public interface HelpService {
	/**
	 * 通帮助类型查询相应列表
	 * @param type
	 * @return
	 */
	public List<Help> selectHelpType(String type);
	/**
	 * 通过帮助ID查询单个帮助
	 * @param id
	 * @return
	 */
	public Help selectHelp(String id);
}
