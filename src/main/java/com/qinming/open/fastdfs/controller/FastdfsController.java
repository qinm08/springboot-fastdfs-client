package com.qinming.open.fastdfs.controller;

import com.qinming.open.fastdfs.domain.ReturnCode;
import com.qinming.open.fastdfs.domain.ReturnMessage;
import com.qinming.open.fastdfs.service.FastdfsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by QinMing on 2017/06/27.
 */
@RestController
@RequestMapping("/fdfs")
@Api(value = "1.上传图片接口", description = " ")
public class FastdfsController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private FastdfsService fastdfsService;

    @ApiOperation("1.1 上传图片")
    @PostMapping(value = "/upload", produces = "application/json")
    public ReturnMessage upload(@RequestParam MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return new ReturnMessage(ReturnCode.FDFS_FILE_IS_EMPTY);
        }

        String result = fastdfsService.upload(file);
        logger.info("fileURL={}", result);

        Map<String, Object> context = new HashMap<>();
        context.put("fileURL", result);
        return new ReturnMessage(ReturnCode.SUCCESS, context);
    }

    @ApiOperation("1.2 上传图片Base64编码")
    @PostMapping(value = "/uploadBase64Str", produces = "application/json")
    public ReturnMessage uploadBase64Str(@RequestParam String base64Str, @RequestParam String fileExt) {
        if (StringUtils.isEmpty(base64Str) || StringUtils.isEmpty(fileExt)) {
            return new ReturnMessage(ReturnCode.FDFS_FILE_IS_EMPTY);
        }

        String result = fastdfsService.upload(base64Str, fileExt);
        logger.info("fileURL={}", result);

        Map<String, Object> context = new HashMap<>();
        context.put("fileURL", result);
        return new ReturnMessage(ReturnCode.SUCCESS, context);
    }
}
