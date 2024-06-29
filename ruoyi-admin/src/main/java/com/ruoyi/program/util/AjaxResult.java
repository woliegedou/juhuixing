package com.ruoyi.program.util;

import lombok.Data;

@Data
public class AjaxResult {
    private String msg;
    private int code;
    private Object data;

    public static AjaxResult success(Object data) {
        AjaxResult result = new AjaxResult();
        result.setCode(200);
        result.setMsg("成功");
        result.setData(data);
        return result;
    }

    public static AjaxResult warn(String msg) {
        AjaxResult result = new AjaxResult();
        result.setCode(400);
        result.setMsg(msg);
        return result;
    }

    public static AjaxResult error(String msg) {
        AjaxResult result = new AjaxResult();
        result.setCode(500);
        result.setMsg(msg);
        return result;
    }

    // Getters and setters
}
