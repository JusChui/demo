package com.jus.service.impl;

import com.jus.dao.ILoginDao;
import com.jus.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName LoginServiceImpl.java
 * @author: JusChui
 * @Description:
 * @createTime: 2021/3/16 16:22
 */
@Service
public class LoginServiceImpl implements LoginService {

    private ILoginDao loginDao;

    @Autowired
    public LoginServiceImpl(ILoginDao loginDao) {
        this.loginDao = loginDao;
    }

    @Override
    public List<Map<String, Object>> getTeacherInfo() {
        return loginDao.getTeacherInfo();
    }

    @Override
    public List<Map<String, Object>> getStudentInfo() {
        return loginDao.getStudentInfo();
    }
}
