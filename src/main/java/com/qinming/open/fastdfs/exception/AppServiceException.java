package com.qinming.open.fastdfs.exception;

import com.qinming.open.fastdfs.domain.ReturnCode;

/**
 * Created by QinMing on 2017/10/26.
 */
public class AppServiceException extends RuntimeException {

    private ReturnCode returnCode;

    public AppServiceException(ReturnCode returnCode) {
        super(returnCode.getName());
        this.returnCode = returnCode;
    }

    public ReturnCode getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(ReturnCode returnCode) {
        this.returnCode = returnCode;
    }
}
