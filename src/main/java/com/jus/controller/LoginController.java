package com.jus.controller;

import com.jus.service.impl.LoginServiceImpl;
import com.jus.util.result.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LoginController.java
 * @author: JusChui
 * @Description:
 * @createTime: 2021/3/16 16:43
 */
@RestController
public class LoginController {

    private LoginServiceImpl loginService;

    @Autowired
    public LoginController(LoginServiceImpl loginService) {
        this.loginService = loginService;
    }

    @RequestMapping(path = "/login", method = {RequestMethod.POST})
    public JsonResult teacherLogin(@RequestParam Map<String, Object> params) {
        params.forEach((s, o) -> System.out.println(s + ":" + o));
        List<Map<String, Object>> list = loginService.getTeacherInfo();
        JsonResult jsonResult = new JsonResult();
        jsonResult.setReturnCode("200");
        jsonResult.setReturnMessage("SUCCESS");
        jsonResult.setBeans(list);
        return jsonResult;
    }
}
