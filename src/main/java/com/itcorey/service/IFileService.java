package com.itcorey.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by ：Corey
 * 10:47 2018/12/27
 * 文件处理接口
 */
public interface IFileService {

    /**
     * 文件上传
     * @param file
     * @param path
     * @return
     */
    String upload(MultipartFile file, String path);

}
