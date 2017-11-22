package com.qinming.open.fastdfs.domain;

/**
 * Created by QinMing on 2017/10/22.
 */
public enum ReturnCode {

    SUCCESS("000000", "SUCCESS"),
    SERVER_ERROR("999999", "服务繁忙，请稍后再试"),

    FDFS_FILE_IS_EMPTY("000001", "失败：没有上传文件"),
    FDFS_UPLOAD_FAILED("000002", "失败：文件上传失败"),
    FDFS_SERVERS_ERROR("000003", "失败：文件服务器错误"),

    ;

    private String value;
    private String name;

    ReturnCode(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
