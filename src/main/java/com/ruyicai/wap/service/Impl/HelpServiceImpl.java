package com.ruyicai.wap.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruyicai.wap.dao.HelpDao;
import com.ruyicai.wap.service.HelpService;
import com.ruyicai.wap.vo.Help;
@Service
public class HelpServiceImpl implements HelpService {
	@Autowired
	HelpDao helpDao;
	@Override
	public List<Help> selectHelpType(String type) {
		return helpDao.selectHelpType(type);
	}

	@Override
	public Help selectHelp(String id) {
		return helpDao.selectHelp(id);
	}

}
