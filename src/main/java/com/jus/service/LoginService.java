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

    List<Map<String, Object>> getTeacherInfo(Map<String, Object> params);

    int updateTeacherInfo(Map<String, Object> params);

    List<Map<String, Object>> getStudentInfo(Map<String, Object> params);

    int updateStudentInfo(Map<String, Object> params);
}
