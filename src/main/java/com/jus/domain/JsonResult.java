package com.jus.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @ClassName JsonResult.java
 * @author: JusChui
 * @Description:
 * @createTime: 2021/3/15 14:18
 */
@Data
public class JsonResult implements Serializable {

    private String returnCode;

    private String returnMessage;

    private Map<String, Object> bean;

    private List<Map<String, Object>> beans;

    private Object data;

}
