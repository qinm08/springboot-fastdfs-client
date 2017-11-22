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
import java.io.IOException;
import java.io.InputStream;
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
        InputStream ins = null;
        try {
            ins = file.getInputStream();
            long fileSize = file.getSize();
            String filename = file.getOriginalFilename();
            String extName = filename.substring(filename.lastIndexOf(".") + 1);

            String result = fastdfsService.upload(ins, fileSize, extName);
            if (StringUtils.isEmpty(result)) {
                return new ReturnMessage(ReturnCode.FDFS_UPLOAD_FAILED);
            }

            Map<String, Object> context = new HashMap<>();
            context.put("fileURL", result);
            return new ReturnMessage(ReturnCode.SUCCESS, context);
        } catch (Exception e) {
            logger.error("FastdfsController 接收文件失败！", e);
            return new ReturnMessage(ReturnCode.FDFS_SERVERS_ERROR);
        } finally {
            if (ins != null)
                try {
                    ins.close();
                } catch (IOException e) {
                    // do nothing
                }
        }
    }
}
