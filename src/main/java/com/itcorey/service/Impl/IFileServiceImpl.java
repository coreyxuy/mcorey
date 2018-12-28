package com.itcorey.service.Impl;

import com.google.common.collect.Lists;
import com.itcorey.service.IFileService;
import com.itcorey.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by ：Corey
 * 10:48 2018/12/27
 * 文件处理实现
 */
@Service("IFileService")
public class IFileServiceImpl implements IFileService {

    private static Logger logger = LoggerFactory.getLogger(IFileService.class);

    public String upload(MultipartFile file,String path){
        String filename = file.getOriginalFilename();
        //获取扩展名
        String fileExtensionName = filename.substring(filename.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID().toString()+","+fileExtensionName;
        logger.info("开始上传文件,上传文件的文件:{},上传的路径为{},新文件名为{}",filename,path,uploadFileName);

        File filedir = new File(path);
        if (!filedir.exists()){
            filedir.setWritable(true);
            filedir.mkdir();
        }
        File targetFile = new File(path,uploadFileName);
        try {
            file.transferTo(filedir);
            //文件上传成功
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            targetFile.delete();
        } catch (IOException e) {
            logger.error("上传文件异常!",e);
        }
        return targetFile.getName();
    }

}
