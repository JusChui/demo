package com.jus.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

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

    /* 教师信息操作 */

    /**
     * 查询教师信息
     *
     * @param params
     * @return
     */
    List<Map<String, Object>> getTeacherInfo(Map<String, Object> params);

    int updateTeacherInfo(Map<String, Object> params);

    List<Map<String, Object>> getStudentInfo(Map<String, Object> params);
}
