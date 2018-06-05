package com.qinming.open.fastdfs.service;

import com.qinming.open.fastdfs.domain.ReturnCode;
import com.qinming.open.fastdfs.exception.AppServiceException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.UploadCallback;
import org.csource.fastdfs.UploadStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

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

    public String upload(MultipartFile file) {
        try {
            InputStream ins = file.getInputStream();
            long fileSize = file.getSize();
            String filename = file.getOriginalFilename();
            String fileExt = filename.substring(filename.lastIndexOf(".") + 1);

            StorageClient1 client = new StorageClient1();
            UploadCallback callback = new UploadStream(ins, fileSize);
            String result = client.upload_file1(null, fileSize, callback, fileExt, null);
            return IMAGE_ACCESS_URL + result;
        } catch (Exception e) {
            logger.error("上传文件失败！", e);
            throw new AppServiceException(ReturnCode.FDFS_UPLOAD_FAILED);
        }
    }

    public String upload(String base64Str, String fileExt) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = decoder.decodeBuffer(base64Str);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            StorageClient1 client = new StorageClient1();
            String result = client.upload_file1(b, fileExt, null);
            return IMAGE_ACCESS_URL + result;
        } catch (Exception e) {
            logger.error("上传Base64编码失败！", e);
            throw new AppServiceException(ReturnCode.FDFS_UPLOAD_FAILED);
        }
    }
}
