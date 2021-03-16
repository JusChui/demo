package com.jus.service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName LoginService.java
 * @author: JusChui
 * @Description:
 * @createTime: 2021/3/16 16:14
 */
public interface LoginService {

    List<Map<String,Object>> getTeacherInfo();

    List<Map<String,Object>> getStudentInfo();
}
