package com.qinming.open.fastdfs.service;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.UploadCallback;
import org.csource.fastdfs.UploadStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;

/**
 * Created by QinMing on 2017/06/27.
 */
@Service
public class FastdfsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${IMAGE_ACCESS_URL}")
    private String IMAGE_ACCESS_URL;

    @PostConstruct
    private void initConfig() throws Exception {
        ClientGlobal.initByProperties("fastdfs-client.properties");
        logger.info("ClientGlobal.configInfo(): \n" + ClientGlobal.configInfo());
        logger.info(IMAGE_ACCESS_URL);
    }

    public String upload(InputStream ins, long fileSize, String extName) {
        StorageClient1 client = new StorageClient1();
        UploadCallback callback = new UploadStream(ins, fileSize);
        String result;
        try {
            result = client.upload_file1(null, fileSize, callback, extName, null);
        } catch (Exception e) {
            logger.error("FastdfsService 上传文件失败！", e);
            return null;
        }
        result = IMAGE_ACCESS_URL + result;
        return result;
    }
}
