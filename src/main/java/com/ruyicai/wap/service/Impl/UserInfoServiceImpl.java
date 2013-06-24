package com.ruyicai.wap.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruyicai.wap.dao.UserInfoDao;
import com.ruyicai.wap.service.UserInfoService;
@Service
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	UserInfoDao userInfoDao;

	@Override
	public int insertMsgInfo(String userNo, String userName, String mobileid,
			String bingCode) {
		return userInfoDao.insertMsgInfo(userNo, userName, mobileid, bingCode);
	}
	@Override
	public int selectMsgCount(String userNo) {
		return userInfoDao.selectMsgCount(userNo);

	}
}
