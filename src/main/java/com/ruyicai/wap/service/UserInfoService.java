package com.ruyicai.wap.service;

import org.springframework.stereotype.Service;

@Service
public interface UserInfoService {
	public int selectMsgCount(String userNo);

	public int insertMsgInfo(String userNo, String userName, String mobileid,
			String bingCode);
}
