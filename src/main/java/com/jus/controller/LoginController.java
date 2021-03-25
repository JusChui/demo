package com.jus.controller;

import com.jus.service.impl.LoginServiceImpl;
import com.jus.util.result.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LoginController.java
 * @author: JusChui
 * @Description:
 * @createTime: 2021/3/16 16:43
 */
@RestController
@Slf4j
public class LoginController {

    private LoginServiceImpl loginService;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    public LoginController(LoginServiceImpl loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(path = "/login", method = {RequestMethod.POST})
    // @Transactional
    public JsonResult doLogin(@RequestBody Map<String, Object> params) {
        logger.info("LoginController_doLogin-->入参" + params);
        JsonResult jsonResult = new JsonResult();
        if (StringUtils.equals((String) params.get("status"), "teacher")) {
            jsonResult = teacherLogin(params);
        } else if (StringUtils.equals("student", (String) params.get("status"))) {
            jsonResult = studentLogin(params);
        } else {
            jsonResult.setReturnCode("500");
            jsonResult.setReturnMessage("身份不详,登录失败");
        }
        return jsonResult;
    }

    public JsonResult studentLogin(Map<String, Object> params) {
        JsonResult jsonResult = new JsonResult();
        try {
            String password = (String) params.get("password");
            params.put("s_id",params.get("username"));
            params.remove("t_id");
            List<Map<String, Object>> list = loginService.getStudentInfo(params);
            logger.info("LoginController_studentLogin-->查询用户：" + list);
            if (CollectionUtils.isEmpty(list)) {
                jsonResult.setReturnCode("412");
                jsonResult.setReturnMessage("用户不存在");
            } else if (list.size() == 1) {
                if (StringUtils.equals((String) list.get(0).get("password"), password)) {
                    // 账号密码匹配成功
                    jsonResult.setReturnCode("200");
                    jsonResult.setReturnMessage("登录成功");
                    list.get(0).remove("password");
                    list.get(0).put("status", "同学");
                    // list.get(0).put("role","teacher");
                    jsonResult.setBean(list.get(0));
                    Map<String, Object> p = new HashMap<>();
                    p.put("id", list.get(0).get("id"));
                    p.put("ext_1", "login");
                    loginService.updateStudentInfo(p);
                } else {
                    jsonResult.setReturnCode("412");
                    jsonResult.setReturnMessage("账号密码不匹配");
                }
            } else {
                jsonResult.setReturnCode("400");
                jsonResult.setReturnMessage("用户异常");
                logger.warn("查询到多条数据");
            }
        } catch (Exception e) {
            jsonResult.setReturnCode("503");
            jsonResult.setReturnMessage("系统异常");
            logger.error(e.getMessage() == null ? e.toString() : e.getMessage());
        }
        logger.info("LoginController_teacherLogin-->返回报文：" + jsonResult);
        return jsonResult;
    }

    /*教师登录*/
    public JsonResult teacherLogin(Map<String, Object> params) {
        JsonResult jsonResult = new JsonResult();
        try {
            String password = (String) params.get("password");
            List<Map<String, Object>> list = loginService.getTeacherInfo(params);
            logger.info("LoginController_teacherLogin-->查询用户：" + list);
            if (CollectionUtils.isEmpty(list)) {
                jsonResult.setReturnCode("412");
                jsonResult.setReturnMessage("用户不存在");
            } else if (list.size() == 1) {
                if (StringUtils.equals((String) list.get(0).get("password"), password)) {
                    // 账号密码匹配成功
                    jsonResult.setReturnCode("200");
                    jsonResult.setReturnMessage("登录成功");
                    list.get(0).remove("password");
                    list.get(0).put("status", "老师");
                    // list.get(0).put("role","teacher");
                    jsonResult.setBean(list.get(0));
                    Map<String, Object> p = new HashMap<>();
                    p.put("id", list.get(0).get("id"));
                    p.put("ext_1", "login");
                    loginService.updateTeacherInfo(p);
                } else {
                    jsonResult.setReturnCode("412");
                    jsonResult.setReturnMessage("账号密码不匹配");
                }
            } else {
                jsonResult.setReturnCode("400");
                jsonResult.setReturnMessage("用户异常");
                logger.warn("查询到多条数据");
            }
        } catch (Exception e) {
            jsonResult.setReturnCode("503");
            jsonResult.setReturnMessage("系统异常");
            logger.error(e.getMessage() == null ? e.toString() : e.getMessage());
        }
        logger.info("LoginController_teacherLogin-->返回报文：" + jsonResult);
        return jsonResult;
    }

}
