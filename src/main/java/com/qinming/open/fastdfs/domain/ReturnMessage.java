package com.qinming.open.fastdfs.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by QinMing on 2017/10/20.
 */
public class ReturnMessage {

    private String code;
    private String message;
    private Map<String, Object> context;

    public ReturnMessage() {
        this(ReturnCode.SUCCESS);
    }

    public ReturnMessage(ReturnCode returnCode) {
        this(returnCode, new HashMap<>());
    }

    public ReturnMessage(ReturnCode returnCode, Map<String, Object> context) {
        this.code = returnCode.getValue();
        this.message = returnCode.getName();
        this.context = context;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

}
