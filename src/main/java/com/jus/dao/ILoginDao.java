package com.jus.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ILoginDao.java
 * @author: JusChui
 * @Description:
 * @createTime: 2021/3/16 16:19
 */
@Mapper
public interface ILoginDao {

    List<Map<String,Object>> getTeacherInfo();

    List<Map<String,Object>> getStudentInfo();
}
